 package com.LicenseAPI.LicenseAPI.service;
import com.LicenseAPI.LicenseAPI.model.License;
import com.LicenseAPI.LicenseAPI.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class LicenseService {
    @Value("${license.updates-allowed}")
    private boolean updatesAllowed;
    private final LicenseRepository licenseepository;

    public LicenseService(LicenseRepository licenseepository) {
        this.licenseepository = licenseepository;
    }
    public List<License> getAllLicense() {

        return licenseepository.findAll();
    }
    public Optional<License> getLicenseById(Integer id) {

        return licenseepository.findById(id);
    }

    public License createLicense(License license) {

        return licenseepository.save(license);
    }

    public License updateLicense(Integer id, License license) {
        if (licenseepository.existsById(id)) {
            license.setId(id);
            return licenseepository.save(license);
        }
        return null;
    }
    public void deleteLicense(Integer id) {
        licenseepository.deleteById(id);
    }
public License activatelicense(Integer id)
{
    Optional<License> optionalLicense = licenseepository.findById(id);

    if (optionalLicense.isEmpty()) {
        throw new IllegalArgumentException("License with ID " + id + " not found.");
    }

    // Activate the license
    License license = optionalLicense.get();
    license.setActive(true);
    license.setOneTimeUse(true);

    // Save the updated license back to the database
    return licenseepository.save(license);
}
}



