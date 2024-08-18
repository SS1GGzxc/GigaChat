package com.SS1GGzxc.Commands;

import com.SS1GGzxc.Constants.Constants;
import com.SS1GGzxc.Language.Phrases;
import com.SS1GGzxc.Requests.GenerateRequest;
import com.SS1GGzxc.Settings.Settings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CommandExec implements CommandExecutor {
    private final GenerateRequest request = new GenerateRequest();
    private final ExecutorService service = Executors.newSingleThreadExecutor();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if(Settings.getInstance().getAccessKey().isEmpty()) {
                commandSender.sendMessage(ChatColor.RED + Phrases.getErrorSetKey());
                return true;
            }
            commandSender.sendMessage(ChatColor.GREEN + Phrases.getGettingKey());
            final Future<Integer> UpdateKey = service.submit(request::UpdateKey);

            while (!UpdateKey.isDone()) {
                try {
                    UpdateKey.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            commandSender.sendMessage(ChatColor.GREEN + Phrases.getSuccessKey());

            final Future<String> future = service.submit(() -> request.CreateRequest(String.join(" ", strings)));

            while (!future.isDone()) {
                try {
                    commandSender.sendMessage(ChatColor.GREEN + "[GigaChat]: " + ChatColor.WHITE + future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } else {
            commandSender.sendMessage(Phrases.getConsoleError());
        }
        return true;
    }
}
