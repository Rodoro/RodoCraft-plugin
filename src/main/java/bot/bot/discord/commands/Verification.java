package bot.bot.discord.commands;

import bot.bot.database.Database;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Verification extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("verification")){
            Database db = new Database();
            String uuid = db.searchMemberByName(event.getInteraction().getUser().getId());
            if(uuid != null){
                EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setTitle("Верификация майнкрафт к discord")
                        .setColor(65535)
                        .setDescription("Ваш акаунт discord уже привязан к майнкрафт клиенту с никнеймом **" + Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName() + "**.");

                Button dangerButton = Button.danger("remove", "🗑 Удалить привяку");

                event.replyEmbeds(embedBuilder.build()).setActionRow(dangerButton).setEphemeral(true).queue();
            }else {
                event.reply("Вы еще не зарегистрированы.").setEphemeral(true).queue();
            }
            db.close();
        }
    }
}
