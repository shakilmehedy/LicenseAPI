package com.LicenseAPI.LicenseAPI.repository;

import com.LicenseAPI.LicenseAPI.model.License;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LicenseRepository extends JpaRepository<License,Integer> {

}
