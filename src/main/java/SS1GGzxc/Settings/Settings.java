package SS1GGzxc.Settings;

import SS1GGzxc.GigaMain.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Settings {
    private final static Settings instance = new Settings();

    private File file;
    private YamlConfiguration config;

    private String AccessKey;
    private int MaxTokens;

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
}