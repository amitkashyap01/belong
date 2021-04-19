package com.telecom.belong.entity;

import javax.persistence.*;

@Entity
@Table(name="Phone")
public class Phone {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SEQ_ID", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ID")
    private int id;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ACTIVE", columnDefinition = "char(1) default 'N'")
    private char active;

    public Phone() {
    }

    public Phone(int id, String customerId, String phoneNumber, char active) {
        this.id = id;
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public char getActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = active;
    }
}
