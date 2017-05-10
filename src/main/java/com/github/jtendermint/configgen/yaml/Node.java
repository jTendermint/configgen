package com.github.jtendermint.configgen.yaml;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.jtendermint.configgen.crypto.Crypto;
import com.github.jtendermint.configgen.crypto.CryptoException;

public class Node extends DefaultValues {

    public String name;
    public String extip;
    public int extport;
    public boolean validator;
    public List<Node> seeds = new ArrayList<>();

    public final KeyPair keypair;
    public byte[] pubkey;
    public byte[] privkey;
    public byte[] address;

    public Map<String, Object> other;

    public Node() {
        KeyPair p = null;
        try {
            p = Crypto.generateKeys();
        } catch (CryptoException e) {
            e.printStackTrace();
        }
        keypair = p;

        try {
            if (keypair != null) {
                pubkey = Crypto.compressedKey(keypair.getPublic());
                privkey = Crypto.compressedKey(keypair.getPrivate());
                address = Crypto.ripemd160(pubkey);
            }
        } catch (CryptoException e) {
            e.printStackTrace();
        }
    }

    public void update(DefaultValues def) {
        if (proxy_app == null)
            proxy_app = def.proxy_app;

        if (node_laddr == null)
            node_laddr = def.node_laddr;

        if (fast_sync == null)
            fast_sync = def.fast_sync;

        if (db_backend == null)
            db_backend = def.db_backend;

        if (log_level == null)
            log_level = def.log_level;

        if (rpc_laddr == null)
            rpc_laddr = def.rpc_laddr;

        if (name == null) {
            name = Crypto.randomString(15);
        }

        if (other == null) {
            other = def.getOther();
        } else {
            def.getOther().forEach((key, value) -> {
                if (!other.containsKey(key))
                    other.put(key, value);
            });
        }
    }

    @Override
    public String toString() {
        return "Node [extaddr=" + extip + ":" + extport + ", validator=" + validator + ", seeds=" + seeds + ", proxy_app=" + proxy_app
                + ", node_laddr=" + node_laddr + ", fast_sync=" + fast_sync + ", db_backend=" + db_backend + ", log_level=" + log_level
                + ", rpc_laddr=" + rpc_laddr + "]";
    }

    public String getExtip() {
        return extip;
    }

    public void setExtip(String extip) {
        this.extip = extip;
    }

    public boolean isValidator() {
        return validator;
    }

    public void setValidator(boolean validator) {
        this.validator = validator;
    }

    public List<Node> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<Node> seeds) {
        this.seeds = seeds;
    }

    public int getExtport() {
        return extport;
    }

    public void setExtport(int extport) {
        this.extport = extport;
    }

}
