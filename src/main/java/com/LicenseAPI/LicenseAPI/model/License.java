package com.LicenseAPI.LicenseAPI.model;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String licenseCode;
    @Column(unique = true,nullable = false)
    private String binNumber; // Identifier for the entity receiving the license
    @Column(nullable = false)
    private boolean isActive; // Default is false (inactive)
    @Column(nullable = false)
    private boolean isOneTimeUse; // Specifies if the license can only be used once
    @Column(nullable = false)
    private LocalDate issuedDate;
    @Column
    private LocalDate expirationDate;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getLicenseCode() {

        return licenseCode;
    }

    public void setLicenseCode(String licenseCode)
    {
        this.licenseCode = licenseCode;
    }

    public String getBinNumber()
    {
        return binNumber;
    }

    public void setBinNumber(String binNumber) {

        this.binNumber = binNumber;
    }

    public boolean isActive() {

        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isOneTimeUse() {

        return isOneTimeUse;
    }

    public void setOneTimeUse(boolean oneTimeUse) {
        isOneTimeUse = oneTimeUse;
    }

    public LocalDate getIssuedDate() {
        return LocalDate.now();
    }

    public void setIssuedDate(LocalDate issuedDate) {

        this.issuedDate = issuedDate;
    }

    public LocalDate getExpirationDate() {

        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {

        this.expirationDate = expirationDate;
    }
}
