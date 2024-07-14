package com.discoverme.backend.wallet;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {
    @Expose
    private String name;
    @Expose
    private String email;
    @Expose
    private String phone_number;
}