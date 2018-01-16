package com.github.jtendermint.configgen.items;

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
    private Key privKey = null;
    @SerializedName("pub_key")
    @Expose
    private Key pubKey = null;

    public PrivValidator() {

    }

    public PrivValidator(String address, String privkey, String pubkey) {
        lastHeight = 0;
        lastRound = 0;
        lastSignature = null;
        lastSignbytes = "";
        lastStep = 0;

        this.address = address;

        privKey = new Key();
        privKey.setType("ed25519"); // TODO dynamisch anpassen?
        privKey.setData(privkey + pubkey);

        pubKey = new Key();
        pubKey.setType("ed25519"); // TODO dynamisch anpassen?
        pubKey.setData(pubkey);

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

    public Key getPrivKey() {
        return privKey;
    }

    public void setPrivKey(Key privKey) {
        this.privKey = privKey;
    }

    public Key getPubKey() {
        return pubKey;
    }

    public void setPubKey(Key pubKey) {
        this.pubKey = pubKey;
    }

}