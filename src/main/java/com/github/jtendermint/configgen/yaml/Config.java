package com.github.jtendermint.configgen.yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class Config {

    private String chainid;

    private DefaultValues defaultvalues;

    private List<Node> nodes;
    
    private NetworkTopology networktopology;

    public static Config loadFile(String filepath) {
        // TypeDescription typeDesc = new TypeDescription(Config.class);
        // typeDesc.putListPropertyType("nodes", Node.class);
        // constructor.addTypeDescription(typeDesc);
        // PropertyUtils property = new PropertyUtils();
        // property.setSkipMissingProperties(true);
        // constructor.setPropertyUtils(property);

        Yaml yaml = new Yaml(new Constructor(Config.class));
        try {
            Config cfg = (Config) yaml.load(new FileReader(new File(filepath)));

            cfg.nodes.forEach(node -> {
                node.update(cfg.defaultvalues);
            });

            return cfg;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public DefaultValues getDefaultvalues() {
        return defaultvalues;
    }

    public void setDefaultvalues(DefaultValues defaultvalues) {
        this.defaultvalues = defaultvalues;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "Config [defaultvalues=" + defaultvalues + ", nodes=" + nodes + "]";
    }

    public String getChainid() {
        return chainid;
    }

    public void setChainid(String chainid) {
        this.chainid = chainid;
    }
    
    public void setNetworktopology(NetworkTopology networktopology) {
        this.networktopology = networktopology;
    }
    
    public NetworkTopology getNetworktopology() {
        return networktopology;
    }
}
