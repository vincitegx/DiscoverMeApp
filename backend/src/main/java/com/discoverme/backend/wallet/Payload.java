package com.discoverme.backend.wallet;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.io.Serializable;

@Data
public class Payload implements Serializable {
    @Expose
    private String amount;
    @Expose
    private String currency;
    @Expose
    private String country;
    @Expose
    private String description;
    @Expose
    private String payment_method;
    @Expose
    private String public_key;
    @Expose
    private String tx_ref;
    @Expose
    private String redirect_url;
    @Expose
    private Customer customer;
}