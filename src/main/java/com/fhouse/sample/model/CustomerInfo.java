package com.fhouse.sample.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class CustomerInfo {

    private String title;
    private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String postcode;
    private String state;
    private String country;
    private String phone;
    private String fax;

}
