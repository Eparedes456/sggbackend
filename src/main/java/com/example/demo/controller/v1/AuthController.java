package com.example.demo.controller.v1;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.exception.RestException;
import com.example.demo.config.exception.SeguridadException;
import com.example.demo.config.security.model.JwtAuthenticationRequest;
import com.example.demo.config.security.model.JwtUser;
import com.example.demo.config.security.token.JwtTokenUtil;
import com.example.demo.models.Token;
import com.example.demo.models.Usuarios;
import com.example.demo.service.EmpleadoService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UsuarioService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 200, message = "La solicitud ha tenido éxito"),
        @ApiResponse(code = 401, message = "El servidor no ha encontrado nada que coincida con el URI de solicitud")})
@Api(description = "Microservicio de autentificación")
public class AuthController {
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private UserDetailsService jwtUserDetailService;

    @Autowired
    private TokenService tokenService;
	
	
    @CrossOrigin(origins = "*")
    @PostMapping("/auth")
    @ApiOperation(value = "CREA EL TOKEN, NO ES NECESARIO ESTAR AUTENTICADO")
    public ResponseEntity<?> createToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                         HttpServletRequest request) {
        // verificamos la autentificación de nuestra configuración
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            // obtenemos el detalle del usuario
            UserDetails userDetails = jwtUserDetailService.loadUserByUsername(authenticationRequest.getUsername());

            // creamos la variable que sea nuestro token
            String token;
            if (checkUsuario(((JwtUser) userDetails).getId())) {
                JwtUser jwtUser = (JwtUser) userDetails;
                // definimos el tiempo de expiración de nuestro token 1 dia laborable 8 horas
                jwtTokenUtil.setExpiration(28800 * 1000L);

                // obtenemos el token con los datos pasados
                String userAgent = request.getHeader("User-Agent");
                token = jwtTokenUtil.generateToken(userDetails, userAgent);
                Usuarios usuarioBean = usuarioService.findByLogin(jwtUser.getUsername().toUpperCase());
                try {
                    InetAddress remoteInetAddress = InetAddress.getByName(request.getRemoteAddr());
                    Token tokenBean = new Token();
                    tokenBean.setHostname(remoteInetAddress.getHostAddress());
                    tokenBean.setNombreEquipo(userAgent);
                    tokenBean.setUsuario(usuarioBean);
                    tokenBean.setHash(token);
                    tokenService.insert(tokenBean);
                } catch (UnknownHostException e) {

                }
                HashMap<String, Object> result = new HashMap<String, Object>();
                result.put("tokenType", "Bearer");
                result.put("user", jwtUser);
                result.put("token", token);
                result.put("empleado", empleadoService.findById(usuarioBean.getId_empleado()));
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new RestException("El usuario no tiene acceso"), HttpStatus.NOT_FOUND);
            }

        } catch (SeguridadException | UnsupportedEncodingException e) {
            return new ResponseEntity<>(new RestException(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


    @ApiOperation(value = "VERIFICA EL TOKEN", authorizations = {@Authorization(value = "apiKey")})
    @GetMapping("/check")
    public ResponseEntity<?> checkToken(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer idUser = 0;
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            JwtUser userDetails = (JwtUser) auth.getPrincipal();
            idUser = userDetails.getId();

        } else {
            return new ResponseEntity<>(new RestException("no autorizado"), HttpStatus.UNAUTHORIZED);
        }
        Usuarios user = usuarioService.findById(idUser);
        //Oficina ofic = oficinaService.findByRepresentante(idUser);

        String requestHeader = request.getHeader("Authorization");
        String token = requestHeader.substring(7);
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            result.put("tokenValid", true);
            result.put("tokenUser", username);
            boolean asist = false;

            result.put("tokenExpired", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a")
                    .format(jwtTokenUtil.getExpirationDateFromToken(token)));

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
                | IllegalArgumentException | UnsupportedEncodingException e) {
            //System.out.println(e.getMessage());
            result.put("token_valid", false);
            result.put("token_user", "");
            result.put("token_expired", "");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ApiOperation(value = "DESTRUYE EL TOKEN", authorizations = {@Authorization(value = "apiKey")})
    @DeleteMapping("/logout")
    public ResponseEntity<?> destroyToken(HttpServletRequest request) {
        String requestHeader = request.getHeader("Authorization");
        String token = requestHeader.substring(7);
        try {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            Usuarios usuario = usuarioService.findByLogin(username.toUpperCase());
            Token tokenBean = tokenService.findTokenByUsuario(token, usuario.getIdUsuario(),
                    request.getHeader("User-Agent"));
            tokenService.delete(tokenBean);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
                | IllegalArgumentException | UnsupportedEncodingException e) {
            return new ResponseEntity<>(new RestException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void authenticate(String username, String password) throws SeguridadException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        // le pasamos los datos del formulario
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new SeguridadException("User is disabled!");

        } catch (BadCredentialsException e) {
            throw new SeguridadException("Datos incorrectos!");
        }
    }

    private boolean checkUsuario(Integer idUsuario) {
        Usuarios usuario = usuarioService.findById(idUsuario);
        if (usuario == null) {
            return false;
        } else {
            return usuario.isEstado();
        }
    }
    
    
    
    

}
