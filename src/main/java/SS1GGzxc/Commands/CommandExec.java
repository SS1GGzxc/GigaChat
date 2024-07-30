package SS1GGzxc.Commands;

import SS1GGzxc.Constants.Constants;
import SS1GGzxc.Requests.GenerateRequest;
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
            if(Constants.Access_Key.isEmpty()) {
                commandSender.sendMessage(ChatColor.RED + "Ошибка: Укажите ключ авторизации!");
                return true;
            }
            commandSender.sendMessage(ChatColor.GREEN + "Получение ключа доступа\nОжижайте ответа.");
            final Future<Integer> UpdateKey = service.submit(request::UpdateKey);

            while (!UpdateKey.isDone()) {
                try {
                    UpdateKey.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            commandSender.sendMessage(ChatColor.GREEN + "Ключ доступа обновлен!\nСоздается ответ на ваш вопрос!");

            final Future<String> future = service.submit(() -> request.CreateRequest(String.join(" ", strings)));

            while (!future.isDone()) {
                try {
                    commandSender.sendMessage(ChatColor.GREEN + "[GigaChat]: " + ChatColor.WHITE + future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } else {
            commandSender.sendMessage("Данную комманду можно использовать только игрокам!");
        }
        return true;
    }
}
