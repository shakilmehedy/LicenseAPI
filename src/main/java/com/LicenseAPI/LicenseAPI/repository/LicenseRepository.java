package com.LicenseAPI.LicenseAPI.repository;

import com.LicenseAPI.LicenseAPI.model.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LicenseRepository extends JpaRepository<License,Integer> {
Optional<License> findByBinNumber (String binNumber);
}
