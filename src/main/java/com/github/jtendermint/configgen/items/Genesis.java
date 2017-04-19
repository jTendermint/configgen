package com.github.jtendermint.configgen.items;

import java.util.List;

public class Genesis {

    private String appHash;
    private String chainId;
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