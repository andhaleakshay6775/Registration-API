package com.Akshay.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Akshay.Entities.CountryEntity;
@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Serializable> {

	
}
