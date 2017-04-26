package com.github.jtendermint.configgen.yaml;

public class NetworkTopology {

    public static final String NONE = "none";
    public static final String VALIDATOR = "validator";
    public static final String FULL = "full";
    public static final String RANDOM_VALIDATOR = "random_val";
    public static final String RANDOM_FULL = "random_full";

    public String validator;
    public String nonvalidator;
}