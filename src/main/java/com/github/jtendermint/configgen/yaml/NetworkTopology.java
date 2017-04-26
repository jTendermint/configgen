package com.github.jtendermint.configgen.yaml;

public class NetworkTopology {

    /** Check individually for every node */
    public static final String NONE = "none";

    /** Knows all Validators */
    public static final String VALIDATOR = "validator";

    /** Knows everyone */
    public static final String FULL = "full";

    /** Knows Random Validators 1-N */
    public static final String RANDOM_VALIDATOR = "random_val";

    /** Knows Random Nodes 1-N */
    public static final String RANDOM_FULL = "random_full";

    public String validator;
    public String nonvalidator;
}