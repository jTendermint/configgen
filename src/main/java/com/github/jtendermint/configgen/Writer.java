package com.github.jtendermint.configgen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import com.github.jtendermint.configgen.items.ConfigToml;
import com.github.jtendermint.configgen.items.Genesis;
import com.github.jtendermint.configgen.items.PrivValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class Writer {

    public static String PRIV_VALIDATOR = "priv_validator.json";
    public static String GENESIS = "genesis.json";
    public static String CONFIG = "config.toml";

    public static void write(String basefolder, int i, Genesis genesis, PrivValidator privval, ConfigToml toml)
            throws JsonIOException, IOException, IllegalArgumentException, IllegalAccessException {

        File folder = new File(basefolder, "config" + i);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        System.out.println("Writing to basedir: " + folder.getAbsolutePath());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter w = new FileWriter(new File(folder, GENESIS))) {
            gson.toJson(genesis, w);
        }

        try (FileWriter w = new FileWriter(new File(folder, PRIV_VALIDATOR))) {
            gson.toJson(privval, w);
        }

        writeTOML(folder, toml);

    }

    private static void writeTOML(File parent, ConfigToml toml) throws IOException, IllegalArgumentException, IllegalAccessException {

        File outfile = new File(parent, CONFIG);
        FileWriter writer = new FileWriter(outfile);

        writer.append("# This is a TOML config file.\n# For more information, see https://github.com/toml-lang/toml\n");

        for (Field f : ConfigToml.class.getDeclaredFields()) {
            String name = f.getName();
            Object value = f.get(toml);

            writer.append(name).append(" = ");

            if (value instanceof String) {
                value = "\"" + value + "\"";
            }
            writer.append(value.toString()).append("\n");

        }

        writer.close();
    }

}
