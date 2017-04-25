package com.github.jtendermint.configgen.items;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Validator {

    private Integer amount;
    private String name;
    
    @SerializedName("pub_key")
    private List<Object> pubKey = null;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getPubKey() {
        return pubKey;
    }

    public void setPubKey(List<Object> pubKey) {
        this.pubKey = pubKey;
    }

}