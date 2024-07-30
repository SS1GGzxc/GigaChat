package SS1GGzxc.GigaMain;

import SS1GGzxc.Commands.CommandExec;
import SS1GGzxc.Commands.SetKeyExec;
import SS1GGzxc.Constants.Constants;
import SS1GGzxc.Settings.Settings;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Settings.getInstance().load();

        Constants.Access_Key = Settings.getInstance().getAccessKey();

        this.getCommand("Ask").setExecutor(new CommandExec());

        this.getCommand("SetKey").setExecutor(new SetKeyExec());
    }

    public static Main getInstance() {
        return getPlugin(Main.class);
    }
}
