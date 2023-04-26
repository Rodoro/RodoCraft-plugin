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
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

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
            embedBuilder.setDescription("Вы подали заявку. Ждите ответа.");
            embedBuilder.setColor(65535);

            Button primaryButton = Button.secondary("take", "Забрать");
            Button secondaryButton = Button.danger("reject", "Рассмотрено");

            event.getGuild().createTextChannel(channelName)
                    .setParent(category)
                    .addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(event.getGuild().getRoleById("1004383854032846930"), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(event.getGuild().getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .complete().sendMessageEmbeds(embedBuilder.build()).setActionRow(primaryButton, secondaryButton).queue();
        }
    }
}
