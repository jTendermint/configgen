package com.github.jtendermint.configgen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Map.Entry;

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

    private static void writeTOML(File parent, ConfigToml toml)
            throws IOException, IllegalArgumentException, IllegalAccessException {

        File outfile = new File(parent, CONFIG);
        FileWriter writer = new FileWriter(outfile);

        writer.append(
                "# This is a TOML config file.\n# For more information, see https://github.com/toml-lang/toml\n\n");

        for (Field f : ConfigToml.class.getDeclaredFields()) {
            boolean isTransient = Modifier.isTransient(f.getModifiers());
            boolean isMap = f.getType().isAssignableFrom(Map.class);
            String name = f.getName();
            Object value = f.get(toml);
            if (!isTransient & !isMap) {
                writeKV(writer, name, value);
            } else if (!isTransient & isMap) {
                writeMap(writer, name, value);
            }
        }

        for (Entry<String, Object> entry : toml.other.entrySet()) {
            writeKV(writer, entry.getKey(), entry.getValue());
        }

        writer.close();
    }

    private static void writeKV(FileWriter writer, String key, Object value) throws IOException {
        writer.append(key).append(" = ");

        if (value instanceof String) {
            value = "\"" + value + "\"";
        }
        writer.append(value.toString()).append("\n");
    }

    private static void writeMap(FileWriter writer, String name, Object value) throws IOException {
        writer.append("\n[" + name + "]\n");
        if (value instanceof Map<?, ?>) {
            Map<Object, Object> map = (Map<Object, Object>) value;
            for (Entry<Object, Object> entry : map.entrySet()) {
                Object entryName = entry.getKey();
                Object entryValue = entry.getValue();

                if (entryValue instanceof String) {
                    entryValue = "\"" + entryValue + "\"";
                }
                writer.append(entryName.toString()).append(" = ").append(entryValue.toString()).append("\n");
            }
            writer.append("\n");
        }
    }

}
