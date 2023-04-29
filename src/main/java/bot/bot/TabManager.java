package bot.bot;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabManager {
    private List<String> header = new ArrayList<>();
    private List<String> footer = new ArrayList<>();

    public void update(Player player){
        player.setPlayerListFooter(color(convert(footer)));
        player.setPlayerListHeader(color(convert(header)));
    }

    private String convert(List<String> list){
        return String.join("\n", list);
    }

    public void addHeaderLine(String str){
        header.add(str);
    }

    public void addFooterLine(String str){
        footer.add(str);
    }

    private String color(String str){
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
