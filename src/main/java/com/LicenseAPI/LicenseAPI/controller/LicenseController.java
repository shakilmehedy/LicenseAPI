package com.LicenseAPI.LicenseAPI.controller;
import com.LicenseAPI.LicenseAPI.model.License;
import com.LicenseAPI.LicenseAPI.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/license")
public class LicenseController {
    @Autowired
    private final LicenseService service;

    public LicenseController(LicenseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<License> createLicense(
            @RequestBody License license) {
        return new ResponseEntity<>(service.createLicense(license),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateLicense(@PathVariable Integer id) {
        try {
            License activatedLicense = service.activatelicense(id);
            return ResponseEntity.ok(activatedLicense);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<License> getAllLicense() {
        return service.getAllLicense();
    }

    @GetMapping("/{id}")
    public ResponseEntity<License> getLicenseById(@PathVariable Integer id) {
        return service.getLicenseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<License> updateLicense(@PathVariable Integer
                                                         id, @RequestBody License license) {
        License updated = service.updateLicense(id, license);
        return updated != null ? ResponseEntity.ok(updated) :
                ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicense(@PathVariable Integer id) {
        service.deleteLicense(id);
        return ResponseEntity.noContent().build();
    }

}

