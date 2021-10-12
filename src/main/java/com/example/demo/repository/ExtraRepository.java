package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Extra;

@Repository
public interface ExtraRepository extends JpaRepository<Extra, Integer> {

}
