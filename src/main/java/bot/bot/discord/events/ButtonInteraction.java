package bot.bot.discord.events;

import bot.bot.database.Database;
import bot.bot.discord.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.EnumSet;

public class ButtonInteraction extends ListenerAdapter {


    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getButton().getId().equals("rejection")) {
            event.getMessage().delete().queue();

        }else if (event.getButton().getId().equals("accept")) {
            event.reply("Ваш discord теперь привязан к майнкрафт.").queue();
            MessageEmbed eb = event.getMessage().getEmbeds().get(0);
            for (MessageEmbed.Field field : eb.getFields()) {
                System.out.println(field.getValue());
                Database db = new Database();
                db.addMember(field.getValue(), event.getUser().getId());
                db.displayAllMembers();
                db.close();
            }
            event.getMessage().delete().queue();
        }else if(event.getButton().getId().equals("remove")){
            Database db = new Database();
            String member = db.searchMemberByName(event.getInteraction().getUser().getId());
            db.deleteMember(member);
            db.close();
            event.reply("Ваша привязка была удалина.").setEphemeral(true).queue();
        }else if(event.getButton().getId().equals("application")){
            Member member = event.getMember();
            Category category = Bot.getInstance().getCategory("1004450739386585148");
            Bot.getInstance().numberChat++;
            String channelName = "заявка-№" + Bot.getInstance().numberChat;
            event.reply("Ваша заявка была создана.").setEphemeral(true).queue();

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Заявка");
            embedBuilder.setDescription("Вы подали заявку. Ждите ответа. Чтобы отменить ее нажмите на кнопку **Забрать** ");
            embedBuilder.setColor(Color.decode("#9966CC"));

            Button primaryButton = Button.secondary("take", "Забрать");
            Button secondaryButton = Button.danger("reject", "Рассмотрено");

            event.getGuild().createTextChannel(channelName)
                    .setParent(category)
                    .addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(event.getGuild().getRoleById("1004383854032846930"), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(event.getGuild().getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .complete().sendMessageEmbeds(embedBuilder.build()).setActionRow(primaryButton, secondaryButton).queue();
        }else if(event.getButton().getId().equals("take")){
            event.getChannel().delete().queue();
        }else if(event.getButton().getId().equals("reject")){
            if(event.getMember().getRoles().contains(event.getGuild().getRoleById("1004383854032846930"))){
                TextInput profile = TextInput.create("profile", "Участник", TextInputStyle.SHORT)
                        .setPlaceholder("ID кто подал заявку")
                        .setMinLength(5)
                        .setMaxLength(20)
                        .build();

                TextInput reason = TextInput.create("reason", "Причина", TextInputStyle.PARAGRAPH)
                        .setPlaceholder("Причина подачи заявки")
                        .setMinLength(30)
                        .setMaxLength(1000)
                        .build();

                TextInput decision = TextInput.create("decision", "Решение", TextInputStyle.SHORT)
                        .setPlaceholder("Решено/отложено")
                        .setMinLength(5)
                        .setMaxLength(20)
                        .build();

                TextInput punishment = TextInput.create("punishment", "Наказание", TextInputStyle.PARAGRAPH)
                        .setPlaceholder("Установленое наказание")
                        .setMinLength(30)
                        .setMaxLength(1000)
                        .build();

                Modal modal = Modal.create("court", "Принятое решение").addActionRows(ActionRow.of(profile), ActionRow.of(reason), ActionRow.of(decision), ActionRow.of(punishment))
                        .build();

                event.replyModal(modal).queue();
            }else{
                event.reply("У вас нету роли судьи.").setEphemeral(true).queue();
            }
        }else if(event.getButton().getId().equals("rejectionGuildA")){
            TextInput profile = TextInput.create("reason", "Причина отказа", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Причина отказа заявка на создания гильдии")
                    .setMinLength(10)
                    .setMaxLength(1000)
                    .build();


            Modal modal = Modal.create("rejectionGuildA", "Отказ").addActionRows(ActionRow.of(profile))
                    .build();

            event.replyModal(modal).queue();
        }else if(event.getButton().getId().equals("acceptGuildA")){
            MessageEmbed messageEmbed = event.getMessage().getEmbeds().get(0);


            EmbedBuilder embedBuilder1 = new EmbedBuilder()
                    .setTitle("✅ | Принятие заявки на создание гильдии")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Кто подал", String.valueOf(messageEmbed.getFields().get(0).getValue()), false)
                    .addField("ID кто подал", String.valueOf(messageEmbed.getFields().get(1).getValue()), false)
                    .addField("Название", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Префикс", String.valueOf(messageEmbed.getFields().get(3).getValue()), false)
                    .addField("Описание", String.valueOf(messageEmbed.getFields().get(4).getValue()), false)
                    .addField("Приватность", String.valueOf(messageEmbed.getFields().get(5).getValue()), false)
                    .addField("Иконка", String.valueOf(messageEmbed.getFields().get(6).getValue()), false);

            event.getMessage().delete().queue();
            event.replyEmbeds(embedBuilder1.build()).queue();
        }else if(event.getButton().getId().equals("rejectionСourtA")){
            TextInput profile = TextInput.create("reason", "Причина отказа", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Причина отказа заявка на судью")
                    .setMinLength(10)
                    .setMaxLength(1000)
                    .build();


            Modal modal = Modal.create("rejectionСourtA", "Отказ").addActionRows(ActionRow.of(profile))
                    .build();

            event.replyModal(modal).queue();
        }else if(event.getButton().getId().equals("acceptСourtA")){
            MessageEmbed messageEmbed = event.getMessage().getEmbeds().get(0);

            event.getGuild().addRoleToMember(event.getGuild().getMemberById(String.valueOf(messageEmbed.getFields().get(1).getValue())), event.getGuild().getRoleById("1004383854032846930")).queue();

            EmbedBuilder embedBuilder1 = new EmbedBuilder()
                    .setTitle("✅ | Принятие заявки на судью")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Кто подал", String.valueOf(messageEmbed.getFields().get(0).getValue()), false)
                    .addField("ID кто подал", String.valueOf(messageEmbed.getFields().get(1).getValue()), false)
                    .addField("Почему", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Опыт", String.valueOf(messageEmbed.getFields().get(3).getValue()), false);

            event.getMessage().delete().queue();
            event.replyEmbeds(embedBuilder1.build()).queue();
        }else if(event.getButton().getId().equals("rejectionСontentmakerA")){
            TextInput profile = TextInput.create("reason", "Причина отказа", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Причина отказа заявка на контент-мейкера")
                    .setMinLength(10)
                    .setMaxLength(1000)
                    .build();


            Modal modal = Modal.create("rejectionСontentmakerA", "Отказ").addActionRows(ActionRow.of(profile))
                    .build();

            event.replyModal(modal).queue();
        }else if(event.getButton().getId().equals("acceptСontentmakerA")){
            MessageEmbed messageEmbed = event.getMessage().getEmbeds().get(0);

            event.getGuild().addRoleToMember(event.getGuild().getMemberById(String.valueOf(messageEmbed.getFields().get(1).getValue())), event.getGuild().getRoleById("1007629610298642493")).queue();

            EmbedBuilder embedBuilder1 = new EmbedBuilder()
                    .setTitle("✅ | Принятие заявки на контент-мейкера")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Кто подал", String.valueOf(messageEmbed.getFields().get(0).getValue()), false)
                    .addField("ID кто подал", String.valueOf(messageEmbed.getFields().get(1).getValue()), false)
                    .addField("Ссылка", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Частота", String.valueOf(messageEmbed.getFields().get(3).getValue()), false)
                    .addField("Доп. связь", String.valueOf(messageEmbed.getFields().get(4).getValue()), false);

            event.getMessage().delete().queue();
            event.replyEmbeds(embedBuilder1.build()).queue();
        }else if(event.getButton().getId().equals("rejectionModeratorA")){
            TextInput profile = TextInput.create("reason", "Причина отказа", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Причина отказа заявка на модератора")
                    .setMinLength(10)
                    .setMaxLength(1000)
                    .build();


            Modal modal = Modal.create("rejectionModeratorA", "Отказ").addActionRows(ActionRow.of(profile))
                    .build();

            event.replyModal(modal).queue();
        }else if(event.getButton().getId().equals("acceptModeratorA")){
            MessageEmbed messageEmbed = event.getMessage().getEmbeds().get(0);

            event.getGuild().addRoleToMember(event.getGuild().getMemberById(String.valueOf(messageEmbed.getFields().get(1).getValue())), event.getGuild().getRoleById("1007686462944317581")).queue();

            EmbedBuilder embedBuilder1 = new EmbedBuilder()
                    .setTitle("✅ | Принятие заявки на модератора")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Кто подал", String.valueOf(messageEmbed.getFields().get(0).getValue()), false)
                    .addField("ID кто подал", String.valueOf(messageEmbed.getFields().get(1).getValue()), false)
                    .addField("Почему", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Опыт", String.valueOf(messageEmbed.getFields().get(3).getValue()), false)
                    .addField("Доп. связь", String.valueOf(messageEmbed.getFields().get(4).getValue()), false);

            event.getMessage().delete().queue();
            event.replyEmbeds(embedBuilder1.build()).queue();
        }else if(event.getButton().getId().equals("rejectionEventA")){
            TextInput profile = TextInput.create("reason", "Причина отказа", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Причина отказа заявка на создание ивента")
                    .setMinLength(10)
                    .setMaxLength(1000)
                    .build();


            Modal modal = Modal.create("rejectionEventA", "Отказ").addActionRows(ActionRow.of(profile))
                    .build();

            event.replyModal(modal).queue();
        }else if(event.getButton().getId().equals("acceptEventA")){
            MessageEmbed messageEmbed = event.getMessage().getEmbeds().get(0);

            EmbedBuilder embedBuilder1 = new EmbedBuilder()
                    .setTitle("✅ | Принятие заявки на создание ивента")
                    .setColor(Color.decode("#9966CC"))
                    .addField("Кто подал", String.valueOf(messageEmbed.getFields().get(0).getValue()), false)
                    .addField("ID кто подал", String.valueOf(messageEmbed.getFields().get(1).getValue()), false)
                    .addField("Дата и время", String.valueOf(messageEmbed.getFields().get(2).getValue()), false)
                    .addField("Место", String.valueOf(messageEmbed.getFields().get(3).getValue()), false)
                    .addField("Описание", String.valueOf(messageEmbed.getFields().get(4).getValue()), false)
                    .addField("Призы", String.valueOf(messageEmbed.getFields().get(5).getValue()), false);

            event.getMessage().delete().queue();
            event.replyEmbeds(embedBuilder1.build()).queue();
        }
    }
}
