package bot.bot;

import bot.bot.commands.RegisterDiscord;
import bot.bot.discord.Bot;
import bot.bot.events.ChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("MyPlugin has been enabled!");

        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getCommand("registerDiscord").setExecutor(new RegisterDiscord());

        Bot.getInstance().start();

        Bot.getInstance().sendMessageStart();

        TabManager tab = new TabManager();
        tab.addHeaderLine("          \uE001          ");
        tab.addHeaderLine("&6");
        tab.addHeaderLine("&6");
        tab.addHeaderLine("&6");
        tab.addHeaderLine("&6");
        tab.addHeaderLine("&8================");
        tab.addHeaderLine("&6");
        tab.addFooterLine("&6");
        tab.addFooterLine("&8================");

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            for(Player player : Bukkit.getOnlinePlayers()){
                tab.update(player);
            }
        }, 0L, 20L);
    }

    @Override
    public void onDisable() {
        getLogger().info("MyPlugin has been disabled!");

        Bot.getInstance().sendMessageStop();
    }
}
