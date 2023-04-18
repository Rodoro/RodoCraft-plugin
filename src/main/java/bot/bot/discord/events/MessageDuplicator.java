package bot.bot.discord.events;

import bot.bot.database.Database;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.UUID;

public class MessageDuplicator extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getChannel().getId().equals("1017809728761495612")) {
            return;
        }
        if (event.getAuthor().isBot()){
            return;
        }
        String member;
        Database db = new Database();
        if (db.searchMemberByName(event.getAuthor().getId()) == null){
            member = event.getAuthor().getName();
        } else {
            member = Bukkit.getOfflinePlayer(UUID.fromString(db.searchMemberByName(event.getAuthor().getId()))).getName();
        }
        db.close();

        String message = " ";
        message += event.getMessage().getContentStripped();

        if (event.getMessage().getAttachments().stream().anyMatch(Message.Attachment::isImage)) {
            message = " (img)" + message;
        }

        List<Message.Attachment> attachments = event.getMessage().getAttachments();
        if (!attachments.isEmpty()) {
            message = " (file)" + message;
        }

        Bukkit.getServer().broadcastMessage("<" + member + ">" + message);
    }
}
