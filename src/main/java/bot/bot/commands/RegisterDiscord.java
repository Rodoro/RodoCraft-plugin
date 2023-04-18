package bot.bot.commands;

import bot.bot.database.Database;
import bot.bot.discord.Bot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RegisterDiscord implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){

        if (!(sender instanceof Player)) {
            sender.sendMessage("Команда только для игрока!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Ты не указал свой Discord ID");
            return true;
        }

        if (Bot.getInstance().getUserInGuild(args[0])){
            player.sendMessage("Тебя нету на сервере discord. Ссылка https://discord.gg/6tTKhYHZMm");
            return true;
        }

        Database db = new Database();
        if (db.searchMemberByName(args[0]) != null){
            player.sendMessage("Ваш клиент уже привязан к discord акаунту с никнеймом" + Bot.getInstance().getUserToId(args[0]));
            return true;
        }
        db.close();

        player.sendMessage("Вам отправлен запрос на верификацию, нажмите на соответствующую реакцию под сообщением в Discord!");
        Bot.getInstance().sendRegistration(player.getUniqueId(), args[0]);

        return true;
    }
}
