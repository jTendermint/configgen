package com.github.jtendermint.configgen.items;

import java.util.Map;

import com.github.jtendermint.configgen.yaml.Node;

//# This is a TOML config file.
//# For more information, see https://github.com/toml-lang/toml
//
//proxy_app = "tcp://127.0.0.1:46658"
//moniker = "anonymous"
//node_laddr = "tcp://0.0.0.0:46656"
//seeds = ""
//fast_sync = true
//db_backend = "leveldb"
//log_level = "notice"
//rpc_laddr = "tcp://0.0.0.0:46657"

public class ConfigToml {
    public String proxy_app = "tcp://127.0.0.1:46658";
    public String moniker = "anonymous";
    public String node_laddr = "tcp://0.0.0.0:46656";

    public Boolean fast_sync = true;
    public String db_backend = "leveldb";
    public String log_level = "notice";
    public String rpc_laddr = "tcp://0.0.0.0:46657";

    public String seeds = ""; // fill later
    public Map<String, Object> other;

    public ConfigToml() {
    }

    public void initWith(Node n) {
        proxy_app = n.proxy_app;
        moniker = n.name;
        node_laddr = n.node_laddr;

        fast_sync = n.fast_sync;
        db_backend = n.db_backend;
        log_level = n.log_level;
        rpc_laddr = n.rpc_laddr;
        
        other = n.otherValues;

        final StringBuilder toSeed = new StringBuilder();

        n.getSeeds().forEach(seedNode -> {
            toSeed.append(seedNode.extip);
            toSeed.append(":");
            toSeed.append(seedNode.extport);
            toSeed.append(", ");
        });
        
        int lastIndexComma = toSeed.lastIndexOf(", ");
        if (lastIndexComma != -1) {
            seeds = toSeed.substring(0, lastIndexComma);
        }
    }

}
