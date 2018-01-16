package com.github.jtendermint.configgen.items;

import com.google.gson.annotations.SerializedName;

public class Validator {

    private Integer power;
    private String name;

    @SerializedName("pub_key")
    private Key pubKey = null;

    public Integer getAmount() {
        return power;
    }

    public void setAmount(Integer amount) {
        this.power = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Key getPubKey() {
        return pubKey;
    }

    public void setPubKey(Key pubKey) {
        this.pubKey = pubKey;
    }

}