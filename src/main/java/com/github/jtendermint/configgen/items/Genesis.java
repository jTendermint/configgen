package com.github.jtendermint.configgen.items;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Genesis {

    @SerializedName("app_hash")
    private String appHash;

    @SerializedName("chain_id")
    private String chainId;

    @SerializedName("genesis_time")
    private String genesisTime;

    private List<Validator> validators = null;

    public String getAppHash() {
        return appHash;
    }

    public void setAppHash(String appHash) {
        this.appHash = appHash;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public String getGenesisTime() {
        return genesisTime;
    }

    public void setGenesisTime(String genesisTime) {
        this.genesisTime = genesisTime;
    }

    public List<Validator> getValidators() {
        return validators;
    }

    public void setValidators(List<Validator> validators) {
        this.validators = validators;
    }

}