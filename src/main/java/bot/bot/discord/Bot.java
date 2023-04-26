package bot.bot.discord;

import bot.bot.Secret;
import bot.bot.discord.commands.Verification;
import bot.bot.discord.events.ButtonInteraction;
import bot.bot.discord.events.MessageDuplicator;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;

import java.awt.*;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class Bot {

    private static Bot instance = null;

    public static Bot getInstance() {
        if (instance == null) {
            instance = new Bot();
        }

        return instance;
    }
    private final JDA jda = JDABuilder.createDefault(Secret.token)
            .setActivity(Activity.playing("Minecraft"))
            .addEventListeners(
                    new ButtonInteraction(),
                    new Verification(),
                    new MessageDuplicator())
            .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
            .build();


    public Guild guild = jda.getGuildById("955504354792730624");
    public int numberChat = 0;

    public void start(){
        try {
            jda.awaitReady();

            guild = jda.getGuildById("955504354792730624");

            guild.upsertCommand("verification", "Взаимодействие с верификацией").queue();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRegistration(UUID requester, String discord_id){
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("Верификация майнкрафт к discord")
                .setColor(65535)
                .setDescription("Пользователь с никнеймом **" + Bukkit.getPlayer(requester).getName() + "** хочет привязать майнкрафт акаунт к вашему discord")
                .addField("Запросил c uuid", requester.toString(), false);

        Button primaryButton = Button.primary("accept", "✔ Подтвердить");
        Button secondaryButton = Button.secondary("rejection", "❌ Отмена");

        try {
            jda.retrieveUserById(discord_id).queue(currentUser -> {
                currentUser.openPrivateChannel()
                        .flatMap(
                                channel -> channel.sendMessageEmbeds(embedBuilder.build()).setActionRow(primaryButton, secondaryButton)
                        ).queue();
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Boolean getUserInGuild(String id){
        if(guild.getMemberById(id) == null) {
            return true;
        } else {
            return false;
        }
    }

    public String getUserToId(String id){
        return  jda.getUserById(id).getName();
    }

    public void sendMessageChanel(String message ){
        TextChannel channel = guild.getTextChannelById("1017809728761495612");
        channel.sendMessage(message).queue();
    }

    public void sendMessageChanelSay(String message ){
        TextChannel channel = guild.getTextChannelById("1017809728761495612");

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("❗ Server ❗")
                .setColor(65535)
                .setDescription(message);

        channel.sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public void sendMessageJoin(String player){
        TextChannel channel = guild.getTextChannelById("1017809728761495612");

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("**" + player + "** присоединился")
                .setColor(Color.green);

        channel.sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public void sendMessageLeft(String player){
        TextChannel channel = guild.getTextChannelById("1017809728761495612");

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("**" + player + "** отключился")
                .setColor(Color.red);

        channel.sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public void sendMessageDeath(String message){
        TextChannel channel = guild.getTextChannelById("1017809728761495612");

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle(message)
                .setColor(Color.black);

        channel.sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public void sendMessageStart(){
        TextChannel channel = guild.getTextChannelById("1017809728761495612");

        channel.sendMessage("✅ **Сервер запущен!**").queue();
    }

    public void sendMessageStop(){
        TextChannel channel = guild.getTextChannelById("1017809728761495612");

        channel.sendMessage("🛑 **Сервер остановил работу**").queue();
    }

    public Category getCategory(String id){
        return jda.getCategoryById(id);
    }
}
