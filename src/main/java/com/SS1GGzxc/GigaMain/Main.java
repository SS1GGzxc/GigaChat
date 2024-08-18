package com.SS1GGzxc.GigaMain;

import com.SS1GGzxc.Commands.CommandExec;
import com.SS1GGzxc.Commands.SetKeyExec;
import com.SS1GGzxc.Commands.SetLang;
import com.SS1GGzxc.Constants.Constants;
import com.SS1GGzxc.Settings.Settings;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Settings.getInstance().load();

        this.getCommand("Ask").setExecutor(new CommandExec());

        this.getCommand("SetKey").setExecutor(new SetKeyExec());

        final SetLang lang = new SetLang();
        this.getCommand("SetLanguage").setExecutor(lang);
        this.getCommand("SetLanguage").setTabCompleter(lang);
    }

    public static Main getInstance() {
        return getPlugin(Main.class);
    }
}
