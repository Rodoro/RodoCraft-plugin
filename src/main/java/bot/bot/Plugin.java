package bot.bot;

import bot.bot.commands.RegisterDiscord;
import bot.bot.discord.Bot;
import bot.bot.events.ChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("MyPlugin has been enabled!");

        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getCommand("registerDiscord").setExecutor(new RegisterDiscord());

        Bot.getInstance().start();

        Bot.getInstance().sendMessageStart();
    }

    @Override
    public void onDisable() {
        getLogger().info("MyPlugin has been disabled!");

        Bot.getInstance().sendMessageStop();
    }
}
