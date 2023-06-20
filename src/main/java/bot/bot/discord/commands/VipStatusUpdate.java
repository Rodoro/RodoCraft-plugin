package bot.bot.discord.commands;

import bot.bot.database.VipStatusDB;
import bot.bot.model.VipStatus;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class VipStatusUpdate extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(event.getName().equals("vipstatus")){
            User user = event.getOption("user").getAsUser();
            int exp = event.getOption("exp").getAsInt();
            int money = event.getOption("money").getAsInt();

            int[] expToLvl = new int[4];
            expToLvl[0] = 100;
            expToLvl[1] = 400;
            expToLvl[2] = 1000;
            expToLvl[3] = 2500;

            VipStatus db = VipStatusDB.getInstance().getVipStatusByName(user.getId());
            if(db == null){
                VipStatusDB.getInstance().addVipStatus(new VipStatus(user.getId(), money, exp, 1 ));
                event.getGuild().addRoleToMember(event.getGuild().getMemberById(user.getId()), event.getGuild().getRoleById("1120767474527379557")).queue();
            }else{
                VipStatusDB.getInstance().updateVipStatusParameter(user.getId(), "exp", exp+db.getExp());
                VipStatusDB.getInstance().updateVipStatusParameter(user.getId(), "money", money+db.getMoney());
                if(db.getExp() + exp >= expToLvl[db.getLvl() - 1]){
                    VipStatusDB.getInstance().updateVipStatusParameter(user.getId(), "lvl", db.getLvl() + 1);
                    event.reply("Незабудте выдать привелегии за " + (db.getLvl() + 1) + " лвл:\n" +
                            "1. Vip чат + роль в дс\n" +
                            "2. Уникальный префикс в майнкрафте + 1 кастомный предмет\n" +
                            "3. Автоматический доступ к креатив миру + новостной чат в который публикуются новые идеи и разработки по проекту\n" +
                            "4. Ежедневное получение крафтТокенов в размере 20шт").setEphemeral(true).queue();
                    //TODO Авто выдача за лвл
                }else{
                    event.reply("Успешно измененно").setEphemeral(true).queue();
                }
            }
        }
    }
}
