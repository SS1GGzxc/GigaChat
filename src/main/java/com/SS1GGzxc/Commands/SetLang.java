package com.SS1GGzxc.Commands;

import com.SS1GGzxc.Language.Phrases;
import com.SS1GGzxc.Settings.Settings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class SetLang implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings[0].isEmpty()) {
            commandSender.sendMessage(ChatColor.RED + Phrases.getChooseLang());
            return true;
        }
        Settings.getInstance().setLanguage(strings[0]);
        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return List.of("ru", "en");
    }
}
