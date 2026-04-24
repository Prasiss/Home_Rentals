package com.homerental.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class DealerApplication implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer applicationNo;
    private Integer userNo;
    private String applicantName;
    private String applicantEmail;
    private String applicantPhone;
    private String companyName;
    private String businessLicense;
    private Integer yearsExperience;
    private Integer propertiesCount;
    private String propertyTypes;
    private String aboutBusiness;
    private String applicationStatus;
    private Timestamp submittedDate;
    
    public DealerApplication() {}

    public Integer getApplicationNo() { return applicationNo; }
    public void setApplicationNo(Integer applicationNo) { this.applicationNo = applicationNo; }
    
    public Integer getUserNo() { return userNo; }
    public void setUserNo(Integer userNo) { this.userNo = userNo; }
    
    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }
    
    public String getApplicantEmail() { return applicantEmail; }
    public void setApplicantEmail(String applicantEmail) { this.applicantEmail = applicantEmail; }
    
    public String getApplicantPhone() { return applicantPhone; }
    public void setApplicantPhone(String applicantPhone) { this.applicantPhone = applicantPhone; }
    
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    
    public String getBusinessLicense() { return businessLicense; }
    public void setBusinessLicense(String businessLicense) { this.businessLicense = businessLicense; }
    
    public Integer getYearsExperience() { return yearsExperience; }
    public void setYearsExperience(Integer yearsExperience) { this.yearsExperience = yearsExperience; }
    
    public Integer getPropertiesCount() { return propertiesCount; }
    public void setPropertiesCount(Integer propertiesCount) { this.propertiesCount = propertiesCount; }
    
    public String getPropertyTypes() { return propertyTypes; }
    public void setPropertyTypes(String propertyTypes) { this.propertyTypes = propertyTypes; }
    
    public String getAboutBusiness() { return aboutBusiness; }
    public void setAboutBusiness(String aboutBusiness) { this.aboutBusiness = aboutBusiness; }
    
    public String getApplicationStatus() { return applicationStatus; }
    public void setApplicationStatus(String applicationStatus) { this.applicationStatus = applicationStatus; }
    
    public Timestamp getSubmittedDate() { return submittedDate; }
    public void setSubmittedDate(Timestamp submittedDate) { this.submittedDate = submittedDate; }
}