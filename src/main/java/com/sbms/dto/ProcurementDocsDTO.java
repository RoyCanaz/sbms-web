/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.dto;

import com.sbms.domain.Client;
import com.sbms.domain.RequiredDocuments;
import com.sbms.domain.User;

/**
 *
 * @author user
 */
public class ProcurementDocsDTO {
    
  
    
    
     private Long id; 
    private Long realId;  
    private String applicationLetter;
    private String companyProfile;
    private String certOfIncorporation;
    private String mou;
    private String crFourteen;
    private String vat;
    private String itf;
    private String tradeLicense;
    private String traceableReference; 
    private String other;
    private Client client;
    private Long clientId;
    private Long createdBy;
    private User user;

    public ProcurementDocsDTO() {
    }

    public ProcurementDocsDTO(Long id, Long realId, Long clientId) {
        this.id = id;
        this.realId = realId;
        this.clientId = clientId;
    }
    
    

    public ProcurementDocsDTO(Long realId, String applicationLetter, String companyProfile, String certOfIncorporation, String mou, String crFourteen, String vat, String itf, String tradeLicense, String traceableReference, String other, Client client, Long clientId, Long createdBy, User user) {
        this.realId = realId;
        this.applicationLetter = applicationLetter;
        this.companyProfile = companyProfile;
        this.certOfIncorporation = certOfIncorporation;
        this.mou = mou;
        this.crFourteen = crFourteen;
        this.vat = vat;
        this.itf = itf;
        this.tradeLicense = tradeLicense;
        this.traceableReference = traceableReference;
        this.other = other;
        this.client = client;
        this.clientId = clientId;
        this.createdBy = createdBy;
        this.user = user;
    }

    public ProcurementDocsDTO(Long realId, String applicationLetter, String companyProfile, String certOfIncorporation, String mou, String crFourteen, String vat, String itf, String tradeLicense, String traceableReference, String other, Long clientId, Long createdBy) {
        this.realId = realId;
        this.applicationLetter = applicationLetter;
        this.companyProfile = companyProfile;
        this.certOfIncorporation = certOfIncorporation;
        this.mou = mou;
        this.crFourteen = crFourteen;
        this.vat = vat;
        this.itf = itf;
        this.tradeLicense = tradeLicense;
        this.traceableReference = traceableReference;
        this.other = other;
        this.clientId = clientId;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public Long getRealId() {
        return realId;
    }

    public void setRealId(Long realId) {
        this.realId = realId;
    }

    public String getApplicationLetter() {
        return applicationLetter;
    }

    public void setApplicationLetter(String applicationLetter) {
        this.applicationLetter = applicationLetter;
    }

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
    }

    public String getCertOfIncorporation() {
        return certOfIncorporation;
    }

    public void setCertOfIncorporation(String certOfIncorporation) {
        this.certOfIncorporation = certOfIncorporation;
    }

    public String getMou() {
        return mou;
    }

    public void setMou(String mou) {
        this.mou = mou;
    }

    public String getCrFourteen() {
        return crFourteen;
    }

    public void setCrFourteen(String crFourteen) {
        this.crFourteen = crFourteen;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getItf() {
        return itf;
    }

    public void setItf(String itf) {
        this.itf = itf;
    }

    public String getTradeLicense() {
        return tradeLicense;
    }

    public void setTradeLicense(String tradeLicense) {
        this.tradeLicense = tradeLicense;
    }

    public String getTraceableReference() {
        return traceableReference;
    }

    public void setTraceableReference(String traceableReference) {
        this.traceableReference = traceableReference;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
      
    public RequiredDocuments getInstance(ProcurementDocsDTO dTO){
        RequiredDocuments rd = new RequiredDocuments(applicationLetter, companyProfile, certOfIncorporation, mou, crFourteen, vat, itf, tradeLicense, traceableReference, other, client, clientId, realId, user);
        return rd;
        
    }
      
}
