package com.SS1GGzxc.Settings;

import com.SS1GGzxc.GigaMain.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Settings {
    private final static Settings instance = new Settings();

    private File file;
    private YamlConfiguration config;

    private String AccessKey;
    private int MaxTokens;
    private String Language;

    public void load() {
        file = new File(Main.getInstance().getDataFolder(), "settings.yml");

        if(!file.exists()) {
            Main.getInstance().saveResource("settings.yml", false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException ex) {
            ex.printStackTrace();
        }

        MaxTokens = config.getInt("GigaChat.Max_Tokens", 128);
        AccessKey = config.getString("GigaChat.Access_Key");
        Language = config.getString("GigaChat.Language");
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void set(String path, Object value) {
        config.set(path, value);

        save();
    }

    public static Settings getInstance() {
        return instance;
    }

    public String getAccessKey() {
        return AccessKey;
    }

    public void setAccessKey(String accessKey) {
        AccessKey = accessKey;
        set("GigaChat.Access_Key", accessKey);
    }

    public int getMaxTokens() {
        return MaxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        MaxTokens = maxTokens;
        set("GigaChat.Max_Tokens", maxTokens);
    }

    public String getLanguage() {
        return Language;
    }
    public boolean isEn() {
        return Objects.equals(Language, "en");
    }

    public void setLanguage(String language) {
        Language = language;
        set("GigaChat.Language", language);
    }
}