package bot.bot.discord.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.HexFormat;

public class ModalInteraction extends ListenerAdapter {

    @Override
    public void onModalInteraction(@Nonnull ModalInteractionEvent event) {
        if (event.getModalId().equals("court")) {
            String profile = event.getValue("profile").getAsString();
            String reason = event.getValue("reason").getAsString();
            String decision = event.getValue("decision").getAsString();
            String punishment = event.getValue("punishment").getAsString();

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Решение суда");
            embedBuilder.setDescription("**Номер заявки:** " + event.getChannel().getName() + "\n**Подал:** " + event.getGuild().getMemberById(profile).getUser().getName() + "\n**Судья:** " + event.getMember().getUser().getName() + "\n**Решение:** " + decision + "\n**Причина:** " + reason + "\n**Наказание:** " + punishment);
            embedBuilder.setColor(Color.decode("#9966CC"));

            TextChannel channel = event.getGuild().getTextChannelById("1007324147111706734");
            channel.sendMessageEmbeds(embedBuilder.build()).queue();
            event.reply("Заявка теперь в архиве");
            event.getChannel().delete().queue();
        }
    }
}
