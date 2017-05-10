package com.github.jtendermint.configgen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Collectors;

import com.github.jtendermint.configgen.crypto.Crypto;
import com.github.jtendermint.configgen.items.ConfigToml;
import com.github.jtendermint.configgen.items.Genesis;
import com.github.jtendermint.configgen.items.PrivValidator;
import com.github.jtendermint.configgen.items.Validator;
import com.github.jtendermint.configgen.yaml.Config;
import com.github.jtendermint.configgen.yaml.NetworkTopology;
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
            ConfigToml toml = setupConfigToml(nodex, cfg);
            try {
                Writer.write(outpath, i, genesis, privval, toml);
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        System.out.println("Done.");
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

    public static ConfigToml setupConfigToml(Node n, Config cfg) {
        ConfigToml toml = new ConfigToml();
        toml.initWith(n);

        final String topology = n.isValidator() ? cfg.getNetworktopology().validator : cfg.getNetworktopology().nonvalidator;

        switch (topology) {
        case NetworkTopology.VALIDATOR: {
            List<Node> validators = cfg.getNodes().stream().filter(t -> t.isValidator()).collect(Collectors.toList());
            toml.setSeeds(validators);
            break;
        }
        case NetworkTopology.FULL: {
            toml.setSeeds(cfg.getNodes());
            break;
        }
        case NetworkTopology.RANDOM_FULL: {
            toml.setSeeds(randomSubset(cfg.getNodes()));
            break;
        }
        case NetworkTopology.RANDOM_VALIDATOR: {
            List<Node> validators = cfg.getNodes().stream().filter(t -> t.isValidator()).collect(Collectors.toList());
            toml.setSeeds(randomSubset(validators));
            break;
        }
        case NetworkTopology.NONE:
        default:
            toml.setSeeds(n.getSeeds());
        }

        return toml;
    }

    private static List<Node> randomSubset(List<Node> nodes) {
        Vector<Node> result = new Vector<>(nodes);
        Collections.shuffle(result);
        Random r = new Random();
        int size = 1 + r.nextInt(result.size());
        result.setSize(size);
        return result;
    }

}
