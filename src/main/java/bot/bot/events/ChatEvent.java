package bot.bot.events;

import bot.bot.database.Database;
import bot.bot.discord.Bot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class ChatEvent  implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String member;
        Database db = new Database();
        if (db.searchMemberById(event.getPlayer().getUniqueId().toString()) == null){
            member = event.getPlayer().getName();
        } else {
            member = Bot.getInstance().getUserToId(db.searchMemberById(event.getPlayer().getUniqueId().toString()));
        }
        db.close();
        Bot.getInstance().sendMessageChanel(member + ": " + event.getMessage());
    }

    @EventHandler
    public void PlayerCommandPreprocessEvent(ServerCommandEvent event) {
        String message = event.getCommand();
        if(message.startsWith("say ")) {
            message = message.replace("say", " ").trim();
            Bot.getInstance().sendMessageChanelSay(message);
        }
    }

    @EventHandler
    public void playerJoinEvent(PlayerLoginEvent event) {
        String player = event.getPlayer().getDisplayName();
        Bot.getInstance().sendMessageJoin(player);
    }

    @EventHandler
    public void playerLeftEvent(PlayerQuitEvent event) {
        String player = event.getPlayer().getDisplayName();
        Bot.getInstance().sendMessageLeft(player);
    }

    @EventHandler
    public void OnDeath(PlayerDeathEvent event) {
        String player = event.getEntity().getDisplayName();
        Bot.getInstance().sendMessageDeath(event.getDeathMessage());
    }
}
