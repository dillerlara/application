package br.com.application.model.client;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.time.LocalDate;


import br.com.application.model.utilstables.Cnae;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, unique = true)
    private Integer id;

    @Column(nullable = false)
    private Boolean isClientCpf;

    @Column(nullable = false)
    private String name;

    @Column
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "cane_id")
    private Cnae cnae;

    @Column
    private String phone;

    @Column(nullable = false, unique = true)
    private String federalId;

    @Column
    private String cep;

    @Column
    private String state;

    @Column
    private String city;

    @Column
    private String complement;

    @Column
    private String address;

    @Column
    private String addressNumber;

    @Column
    private BigDecimal latitude;

    @Column
    private BigDecimal longitude;

    @Column
    private String certificate;

    @Column
    private String photo;

    public Client() {
    }

    public Client(Integer id, Boolean isClientCpf, String name, LocalDate birthDate, Cnae cnae, String phone,
            String federalId, String cep, String state, String city, String complement, String address,
            String addressNumber, BigDecimal latitude, BigDecimal longitude, String certificate, String photo) {
        this.id = id;
        this.isClientCpf = isClientCpf;
        this.name = name;
        this.birthDate = birthDate;
        this.cnae = cnae;
        this.phone = phone;
        this.federalId = federalId;
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.complement = complement;
        this.address = address;
        this.addressNumber = addressNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.certificate = certificate;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsClientCpf() {
        return isClientCpf;
    }

    public void setIsClientCpf(Boolean isClientCpf) {
        this.isClientCpf = isClientCpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Cnae getCnae() {
        return cnae;
    }

    public void setCnae(Cnae cnae) {
        this.cnae = cnae;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFederalId() {
        return federalId;
    }

    public void setFederalId(String federalId) {
        this.federalId = federalId;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    

}
