package bot.bot.discord.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;


public class InfoServer extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("infoserver")){
            String typeOption = event.getOption("type").getAsString();
            if (typeOption.equalsIgnoreCase("vanilla")){
                int onlinePlayers = Bukkit.getOnlinePlayers().size();
                String playerList = "";
                String version = Bukkit.getVersion();

                if(onlinePlayers != 0){
                    for(Player p: Bukkit.getOnlinePlayers()) {
                        playerList += p.getName() + "\n";
                    }
                }

                EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setTitle("Информация о сервере")
                        .setColor(Color.decode("#9966CC"))
                        .setDescription("**Название: **Vanilla" +
                                "\n**Версия: **1.19.2" +
                                "\n**Общий онлайн: **" + String.valueOf(onlinePlayers) + "/50")
                        .addField("Список играков", playerList, false);

                event.replyEmbeds(embedBuilder.build()).queue();
            }else if(typeOption.equalsIgnoreCase("discord")){
                Guild guild = event.getGuild();
                int membersCount = guild.getMemberCount();
                int emotesCount = guild.getEmojis().size();
                int stickersCount = guild.getStickers().size();

                EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setTitle("Информация о discord сервере **RodoCraft**")
                        .setColor(Color.decode("#9966CC"))
                        .setDescription("**Создатель: **" + guild.getOwner().getUser().getName() +
                                "\n**Участников: **" + String.valueOf(membersCount) +
                                "\n**Ботов: **" + String.valueOf(guild.getMembers().stream().filter(m -> m.getUser().isBot()).count()) +
                                "\n**Смайликов: **" + String.valueOf(emotesCount) +
                                "\n**Стикеров: **" + String.valueOf(stickersCount) +
                                "\n**Дата создания: ** 20 августа 2022 года");

                event.replyEmbeds(embedBuilder.build()).queue();


            }
        }
    }
}
