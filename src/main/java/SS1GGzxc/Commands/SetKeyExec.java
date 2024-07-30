package SS1GGzxc.Commands;

import SS1GGzxc.Constants.Constants;
import SS1GGzxc.Settings.Settings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetKeyExec implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings[0].isEmpty()) {
            commandSender.sendMessage(ChatColor.RED + "Вы не указали ключ");
            return true;
        }
        Constants.Access_Key = strings[0];
        Settings.getInstance().setAccessKey(strings[0]);

        return true;
    }
}
