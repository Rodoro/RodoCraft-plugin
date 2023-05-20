package bot.bot.discord.events;

import bot.bot.discord.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import javax.annotation.Nonnull;
import java.awt.*;

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
        } else if(event.getModalId().equals("guild")){
            String name = event.getValue("name").getAsString();
            String prefix = event.getValue("prefix").getAsString();
            String motto = event.getValue("motto").getAsString();
            String available = event.getValue("available").getAsString();
            String img = event.getValue("img").getAsString();

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("🔄 | Заявка на создание гильдии")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Кто подал", event.getMember().getUser().getName(), false)
                    .addField("ID кто подал", event.getMember().getUser().getId(), false)
                    .addField("Название", name, false)
                    .addField("Префикс", prefix, false)
                    .addField("Описание", motto, false)
                    .addField("Приватность", available, false)
                    .addField("Иконка", img, false);

            Button acceptGuildAButton = Button.primary("acceptGuildA", "✔ Принять");
            Button rejectionGuildAButton = Button.secondary("rejectionGuildA", "❌ Отказ");

            TextChannel channel = event.getGuild().getTextChannelById("1007324147111706734");
            channel.sendMessageEmbeds(embedBuilder.build()).setActionRow(acceptGuildAButton, rejectionGuildAButton).queue();
            event.reply("Заявка на создание гильди была отправлена").setEphemeral(true).queue();
        } else if(event.getModalId().equals("rejectionGuildA")){
            String reason = event.getValue("reason").getAsString();
            MessageEmbed messageEmbed = event.getMessage().getEmbeds().get(0);


            EmbedBuilder embedBuilder1 = new EmbedBuilder()
                    .setTitle("⛔ | Отказ заявки на создание гильдии")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Причина", reason, false)
                    .addField("Кто подал", String.valueOf(messageEmbed.getFields().get(0).getValue()), false)
                    .addField("ID кто подал", String.valueOf(messageEmbed.getFields().get(1).getValue()), false)
                    .addField("Название", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Префикс", String.valueOf(messageEmbed.getFields().get(3).getValue()), false)
                    .addField("Описание", String.valueOf(messageEmbed.getFields().get(4).getValue()), false)
                    .addField("Приватность", String.valueOf(messageEmbed.getFields().get(5).getValue()), false)
                    .addField("Иконка", String.valueOf(messageEmbed.getFields().get(6).getValue()), false);

            event.getMessage().delete().queue();
            event.replyEmbeds(embedBuilder1.build()).queue();

            EmbedBuilder embedBuilder2 = new EmbedBuilder()
                    .setTitle("⛔ | Отказ заявки на создание гильдии")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Причина", reason, false)
                    .addField("Название", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Префикс", String.valueOf(messageEmbed.getFields().get(3).getValue()), false)
                    .addField("Описание", String.valueOf(messageEmbed.getFields().get(4).getValue()), false)
                    .addField("Приватность", String.valueOf(messageEmbed.getFields().get(5).getValue()), false)
                    .addField("Иконка", String.valueOf(messageEmbed.getFields().get(6).getValue()), false);

            Bot.getInstance().sendEmbedIsUserById(String.valueOf(messageEmbed.getFields().get(1).getValue()), embedBuilder2);

        } else if(event.getModalId().equals("courtM")){
            String why = event.getValue("why").getAsString();
            String experience = event.getValue("experience").getAsString();

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("🔄 | Заявка на судью")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Кто подал", event.getMember().getUser().getName(), false)
                    .addField("ID кто подал", event.getMember().getUser().getId(), false)
                    .addField("Почему", why, false)
                    .addField("Опыт", experience, false);

            Button acceptGuildAButton = Button.primary("acceptСourtA", "✔ Принять");
            Button rejectionGuildAButton = Button.secondary("rejectionСourtA", "❌ Отказ");

            TextChannel channel = event.getGuild().getTextChannelById("1007324147111706734");
            channel.sendMessageEmbeds(embedBuilder.build()).setActionRow(acceptGuildAButton, rejectionGuildAButton).queue();
            event.reply("Заявка на судью была отправлена").setEphemeral(true).queue();
        } else if(event.getModalId().equals("rejectionСourtA")){
            String reason = event.getValue("reason").getAsString();
            MessageEmbed messageEmbed = event.getMessage().getEmbeds().get(0);


            EmbedBuilder embedBuilder1 = new EmbedBuilder()
                    .setTitle("⛔ | Отказ заявки на судью")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Причина", reason, false)
                    .addField("Кто подал", String.valueOf(messageEmbed.getFields().get(0).getValue()), false)
                    .addField("ID кто подал", String.valueOf(messageEmbed.getFields().get(1).getValue()), false)
                    .addField("Почему", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Опыт", String.valueOf(messageEmbed.getFields().get(3).getValue()), false);


            event.getMessage().delete().queue();
            event.replyEmbeds(embedBuilder1.build()).queue();

            EmbedBuilder embedBuilder2 = new EmbedBuilder()
                    .setTitle("⛔ | Отказ заявки на судью")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Причина", reason, false)
                    .addField("Почему", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Опыт", String.valueOf(messageEmbed.getFields().get(3).getValue()), false);

            Bot.getInstance().sendEmbedIsUserById(String.valueOf(messageEmbed.getFields().get(1).getValue()), embedBuilder2);

        } else if(event.getModalId().equals("contentmaker")){
            String url = event.getValue("url").getAsString();
            String frequency = event.getValue("frequency").getAsString();
            String connection = event.getValue("connection").getAsString();

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("🔄 | Заявка на контент-мейкера")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Кто подал", event.getMember().getUser().getName(), false)
                    .addField("ID кто подал", event.getMember().getUser().getId(), false)
                    .addField("Ссылка", url, false)
                    .addField("Частота", frequency, false)
                    .addField("Доп. связь", connection, false);

            Button acceptGuildAButton = Button.primary("acceptСontentmakerA", "✔ Принять");
            Button rejectionGuildAButton = Button.secondary("rejectionСontentmakerA", "❌ Отказ");

            TextChannel channel = event.getGuild().getTextChannelById("1007324147111706734");
            channel.sendMessageEmbeds(embedBuilder.build()).setActionRow(acceptGuildAButton, rejectionGuildAButton).queue();
            event.reply("Заявка на контент-мейкера была отправлена").setEphemeral(true).queue();
        } else if(event.getModalId().equals("rejectionСontentmakerA")){
            String reason = event.getValue("reason").getAsString();
            MessageEmbed messageEmbed = event.getMessage().getEmbeds().get(0);


            EmbedBuilder embedBuilder1 = new EmbedBuilder()
                    .setTitle("⛔ | Отказ заявки на контент-мейкера")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Причина", reason, false)
                    .addField("Кто подал", String.valueOf(messageEmbed.getFields().get(0).getValue()), false)
                    .addField("ID кто подал", String.valueOf(messageEmbed.getFields().get(1).getValue()), false)
                    .addField("Ссылка", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Частота", String.valueOf(messageEmbed.getFields().get(3).getValue()), false)
                    .addField("Доп. связь", String.valueOf(messageEmbed.getFields().get(4).getValue()), false);


            event.getMessage().delete().queue();
            event.replyEmbeds(embedBuilder1.build()).queue();

            EmbedBuilder embedBuilder2 = new EmbedBuilder()
                    .setTitle("⛔ | Отказ заявки на контент-мейкера")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Причина", reason, false)
                    .addField("Ссылка", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Частота", String.valueOf(messageEmbed.getFields().get(3).getValue()), false)
                    .addField("Доп. связь", String.valueOf(messageEmbed.getFields().get(4).getValue()), false);

            Bot.getInstance().sendEmbedIsUserById(String.valueOf(messageEmbed.getFields().get(1).getValue()), embedBuilder2);

        } else if(event.getModalId().equals("moderator")){
            String why = event.getValue("why").getAsString();
            String experience = event.getValue("experience").getAsString();
            String connection = event.getValue("connection").getAsString();

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("🔄 | Заявка на модератора")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Кто подал", event.getMember().getUser().getName(), false)
                    .addField("ID кто подал", event.getMember().getUser().getId(), false)
                    .addField("Почему", why, false)
                    .addField("Опыт", experience, false)
                    .addField("Доп. связь", connection, false);

            Button acceptGuildAButton = Button.primary("acceptModeratorA", "✔ Принять");
            Button rejectionGuildAButton = Button.secondary("rejectionModeratorA", "❌ Отказ");

            TextChannel channel = event.getGuild().getTextChannelById("1007324147111706734");
            channel.sendMessageEmbeds(embedBuilder.build()).setActionRow(acceptGuildAButton, rejectionGuildAButton).queue();
            event.reply("Заявка на модератора была отправлена").setEphemeral(true).queue();
        } else if(event.getModalId().equals("rejectionModeratorA")){
            String reason = event.getValue("reason").getAsString();
            MessageEmbed messageEmbed = event.getMessage().getEmbeds().get(0);


            EmbedBuilder embedBuilder1 = new EmbedBuilder()
                    .setTitle("⛔ | Отказ заявки на модератора")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Причина", reason, false)
                    .addField("Кто подал", String.valueOf(messageEmbed.getFields().get(0).getValue()), false)
                    .addField("ID кто подал", String.valueOf(messageEmbed.getFields().get(1).getValue()), false)
                    .addField("Почему", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Опыт", String.valueOf(messageEmbed.getFields().get(3).getValue()), false)
                    .addField("Доп. связь", String.valueOf(messageEmbed.getFields().get(4).getValue()), false);


            event.getMessage().delete().queue();
            event.replyEmbeds(embedBuilder1.build()).queue();

            EmbedBuilder embedBuilder2 = new EmbedBuilder()
                    .setTitle("⛔ | Отказ заявки на модерации")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Причина", reason, false)
                    .addField("Почему", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Опыт", String.valueOf(messageEmbed.getFields().get(3).getValue()), false)
                    .addField("Доп. связь", String.valueOf(messageEmbed.getFields().get(4).getValue()), false);

            Bot.getInstance().sendEmbedIsUserById(String.valueOf(messageEmbed.getFields().get(1).getValue()), embedBuilder2);

        } else if(event.getModalId().equals("event")){
            String data = event.getValue("data").getAsString();
            String place = event.getValue("place").getAsString();
            String point = event.getValue("point").getAsString();
            String prizes = event.getValue("prizes").getAsString();

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("🔄 | Заявка на модератора")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Кто подал", event.getMember().getUser().getName(), false)
                    .addField("ID кто подал", event.getMember().getUser().getId(), false)
                    .addField("Дата и время", data, false)
                    .addField("Место", place, false)
                    .addField("Описание", point, false)
                    .addField("Призы", prizes, false);

            Button acceptGuildAButton = Button.primary("acceptEventA", "✔ Принять");
            Button rejectionGuildAButton = Button.secondary("rejectionEventA", "❌ Отказ");

            TextChannel channel = event.getGuild().getTextChannelById("1007324147111706734");
            channel.sendMessageEmbeds(embedBuilder.build()).setActionRow(acceptGuildAButton, rejectionGuildAButton).queue();
            event.reply("Заявка на создания ивента была отправлена").setEphemeral(true).queue();
        } else if(event.getModalId().equals("rejectionEventA")){
            String reason = event.getValue("reason").getAsString();
            MessageEmbed messageEmbed = event.getMessage().getEmbeds().get(0);


            EmbedBuilder embedBuilder1 = new EmbedBuilder()
                    .setTitle("⛔ | Отказ заявки на создания ивента")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Причина", reason, false)
                    .addField("Кто подал", String.valueOf(messageEmbed.getFields().get(0).getValue()), false)
                    .addField("ID кто подал", String.valueOf(messageEmbed.getFields().get(1).getValue()), false)
                    .addField("Дата и время", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Место", String.valueOf(messageEmbed.getFields().get(3).getValue()), false)
                    .addField("Описание", String.valueOf(messageEmbed.getFields().get(4).getValue()), false)
                    .addField("Призы", String.valueOf(messageEmbed.getFields().get(5).getValue()), false);


            event.getMessage().delete().queue();
            event.replyEmbeds(embedBuilder1.build()).queue();

            EmbedBuilder embedBuilder2 = new EmbedBuilder()
                    .setTitle("⛔ | Отказ заявки на создания ивента")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Причина", reason, false)
                    .addField("Дата и время", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Место", String.valueOf(messageEmbed.getFields().get(3).getValue()), false)
                    .addField("Описание", String.valueOf(messageEmbed.getFields().get(4).getValue()), false)
                    .addField("Призы", String.valueOf(messageEmbed.getFields().get(5).getValue()), false);

            Bot.getInstance().sendEmbedIsUserById(String.valueOf(messageEmbed.getFields().get(1).getValue()), embedBuilder2);

        }
    }
}
