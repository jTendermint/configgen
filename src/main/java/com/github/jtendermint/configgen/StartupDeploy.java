package com.github.jtendermint.configgen;

import java.util.ArrayList;

import com.github.jtendermint.configgen.crypto.Crypto;
import com.github.jtendermint.configgen.items.ConfigToml;
import com.github.jtendermint.configgen.items.Genesis;
import com.github.jtendermint.configgen.items.PrivValidator;
import com.github.jtendermint.configgen.items.Validator;
import com.github.jtendermint.configgen.yaml.Config;
import com.github.jtendermint.configgen.yaml.Node;

public class StartupDeploy {

    public static void main(String[] args) {

        String yamlFilePath = "example.yaml";
        String outpath = "configs/";
        if (args.length == 2) {
            yamlFilePath = args[0];
            outpath = args[1];
        }

        System.out.println("Loading YAML from: " + yamlFilePath);
        Config cfg = Config.loadFile(yamlFilePath);

        final Genesis genesis = setupGenesis(cfg);

        int i = 0;
        for (Node nodex : cfg.getNodes()) {
            PrivValidator privval = setupPrivValidator(nodex);
            ConfigToml toml = setupConfigToml(nodex);
            try {
                Writer.write(outpath, i, genesis, privval, toml);
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }

    }

    public static Genesis setupGenesis(Config cfg) {
        Genesis genesis = new Genesis();
        genesis.setAppHash("");
        genesis.setChainId(cfg.getChainid());
        genesis.setGenesisTime("0001-01-01T00:00:00.000Z");

        ArrayList<Validator> validators = new ArrayList<>();

        cfg.getNodes().forEach(node -> {
            if (node.isValidator()) {
                Validator val = new Validator();
                val.setAmount(10);
                val.setName(node.name);
                ArrayList<Object> pubKey = new ArrayList<>();
                pubKey.add(new Integer(1));
                pubKey.add(Crypto.toString00(node.pubkey));
                val.setPubKey(pubKey);
                validators.add(val);
            }
        });
        genesis.setValidators(validators);
        return genesis;
    }

    public static PrivValidator setupPrivValidator(Node n) {
        String address = Crypto.toString00(n.address);
        String pubkey = Crypto.toString00(n.pubkey);
        String privkey = Crypto.toString00(n.privkey);
        return new PrivValidator(address, privkey, pubkey);
    }

    public static ConfigToml setupConfigToml(Node n) {
        ConfigToml toml = new ConfigToml();
        toml.initWith(n);
        return toml;
    }

}
