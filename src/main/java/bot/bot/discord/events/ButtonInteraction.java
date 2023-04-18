package bot.bot.discord.events;

import bot.bot.database.Database;
import bot.bot.discord.Bot;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

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
            Category category = Bot.getInstance().getCategory("1004450739386585148");
            int counter = 1;
            for (GuildChannel channel : category.getChannels()) {
                if (channel.getName().matches("\\d+")) {
                    counter = Math.max(counter, Integer.parseInt(channel.getName()) + 1);
                }
            }
            String channelName = String.valueOf(counter);
            category.createTextChannel(channelName).queue(textChannel -> textChannel.sendMessage("Заявка"));
        }

    }
}
