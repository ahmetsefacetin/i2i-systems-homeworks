package com.example.springUI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "SUBSCRIBER")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBSC_ID")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SUBSC_NAME")
    private String name;

    @Size(min = 1, max = 100)
    @Column(name = "SUBSC_SURNAME", nullable = false)
    private String surname;

    @Size(min = 1, max = 100)
    @Column(name = "MSISDN", nullable = false)
    private String msisdn;

    @Column(name = "TARIFF_ID")
    private Long tariffId;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    public Subscriber(Long id, String name, String surname, String msisdn, Long tariffId, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.msisdn = msisdn;
        this.tariffId = tariffId;
        this.startDate = startDate;
    }

    public Subscriber() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
