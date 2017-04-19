package com.github.jtendermint.configgen.items;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrivValidator {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("last_height")
    @Expose
    private Integer lastHeight;
    @SerializedName("last_round")
    @Expose
    private Integer lastRound;
    @SerializedName("last_signature")
    @Expose
    private String lastSignature;
    @SerializedName("last_signbytes")
    @Expose
    private String lastSignbytes;
    @SerializedName("last_step")
    @Expose
    private Integer lastStep;
    @SerializedName("priv_key")
    @Expose
    private List<Object> privKey = null;
    @SerializedName("pub_key")
    @Expose
    private List<Object> pubKey = null;

    public PrivValidator() {

    }

    public PrivValidator(String address, String privkey, String pubkey) {
        lastHeight = 0;
        lastRound = 0;
        lastSignature = null;
        lastSignbytes = "";
        lastStep = 0;

        this.address = address;

        privKey = new ArrayList<>();
        privKey.add(1);
        privKey.add(privkey);

        pubKey = new ArrayList<>();
        pubKey.add(1);
        pubKey.add(pubkey);

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLastHeight() {
        return lastHeight;
    }

    public void setLastHeight(Integer lastHeight) {
        this.lastHeight = lastHeight;
    }

    public Integer getLastRound() {
        return lastRound;
    }

    public void setLastRound(Integer lastRound) {
        this.lastRound = lastRound;
    }

    public Object getLastSignature() {
        return lastSignature;
    }

    public void setLastSignature(String lastSignature) {
        this.lastSignature = lastSignature;
    }

    public String getLastSignbytes() {
        return lastSignbytes;
    }

    public void setLastSignbytes(String lastSignbytes) {
        this.lastSignbytes = lastSignbytes;
    }

    public Integer getLastStep() {
        return lastStep;
    }

    public void setLastStep(Integer lastStep) {
        this.lastStep = lastStep;
    }

    public List<Object> getPrivKey() {
        return privKey;
    }

    public void setPrivKey(List<Object> privKey) {
        this.privKey = privKey;
    }

    public List<Object> getPubKey() {
        return pubKey;
    }

    public void setPubKey(List<Object> pubKey) {
        this.pubKey = pubKey;
    }

}