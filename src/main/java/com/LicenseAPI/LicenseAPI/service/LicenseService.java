 package com.LicenseAPI.LicenseAPI.service;
import com.LicenseAPI.LicenseAPI.model.License;
import com.LicenseAPI.LicenseAPI.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public boolean verifyBinNumberWithLicense(String binNumber, String licenseCode) {
//        return licenseepository.findByBinNumber(binNumber)
//                .map(binLicense -> binLicense.getLicenseCode().equals(licenseCode))
//                .orElse(false); // Return false if binNumber is not found
        Optional<License> optionalBinLicense = licenseepository.findByBinNumber(binNumber);

        if (optionalBinLicense.isPresent()) {
            License binLicense = optionalBinLicense.get();

            // Check if the license matches
            if (!binLicense.getLicenseCode().equals(licenseCode)) {
                return false;
            }

            // Check if the expiration date is present and not expired
            if (binLicense.getExpirationDate() != null) {
                return binLicense.getExpirationDate().isAfter(LocalDate.now());
            }

            // If no expiration date, assume valid
            return true;
        }

        // If binNumber not found
        return false;
    }  }








