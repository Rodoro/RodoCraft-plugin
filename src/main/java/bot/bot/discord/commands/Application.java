package bot.bot.discord.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

public class Application extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("application")){
            String typeOption = event.getOption("type").getAsString();
            if (typeOption.equalsIgnoreCase("guild")){

                //TODO проверка на наличие состояние в гильдии

                TextInput name = TextInput.create("name", "Название", TextInputStyle.SHORT)
                        .setPlaceholder("Название вашей гильдии")
                        .setMinLength(5)
                        .setMaxLength(20)
                        .build();

                TextInput prefix = TextInput.create("prefix", "Префикс", TextInputStyle.SHORT)
                        .setPlaceholder("[Ваш отоброжаемый префикс]")
                        .setMinLength(2)
                        .setMaxLength(10)
                        .build();

                TextInput motto = TextInput.create("motto", "Описание", TextInputStyle.PARAGRAPH)
                        .setPlaceholder("Описание вашей гильдии")
                        .setMinLength(30)
                        .setMaxLength(1000)
                        .build();

                TextInput available = TextInput.create("available", "Приватная?", TextInputStyle.SHORT)
                        .setPlaceholder("Напишите 'Да' или 'Нет'")
                        .setMinLength(2)
                        .setMaxLength(3)
                        .build();


                TextInput emoji = TextInput.create("img", "Иконка", TextInputStyle.PARAGRAPH)
                        .setPlaceholder("Эмблема вашей гильдии")
                        .setMinLength(1)
                        .setMaxLength(3000)
                        .build();

                Modal modal = Modal.create("guild", "Создание гильдии").addActionRows(ActionRow.of(name), ActionRow.of(prefix), ActionRow.of(motto), ActionRow.of(available), ActionRow.of(emoji))
                        .build();

                event.replyModal(modal).queue();
            } else  if(typeOption.equalsIgnoreCase("court")){
                TextInput why = TextInput.create("why", "Почему", TextInputStyle.SHORT)
                        .setPlaceholder("Причина становления судьей")
                        .setMinLength(5)
                        .setMaxLength(100)
                        .build();

                TextInput experience = TextInput.create("experience", "Опыт", TextInputStyle.SHORT)
                        .setPlaceholder("Ваш опыт в этом деле")
                        .setMinLength(2)
                        .setMaxLength(1000)
                        .build();

                Modal modal = Modal.create("courtM", "Заявка на судью").addActionRows(ActionRow.of(why), ActionRow.of(experience))
                        .build();

                event.replyModal(modal).queue();
            } else if(typeOption.equalsIgnoreCase("contentmaker")){
                TextInput url = TextInput.create("url", "Ссылка", TextInputStyle.SHORT)
                        .setPlaceholder("Ссылка на ваше творчество")
                        .setMinLength(5)
                        .setMaxLength(100)
                        .build();

                TextInput frequency = TextInput.create("frequency", "Частота", TextInputStyle.SHORT)
                        .setPlaceholder("Частота выхода")
                        .setMinLength(2)
                        .setMaxLength(1000)
                        .build();

                TextInput connection = TextInput.create("connection", "Доп. связь", TextInputStyle.SHORT)
                        .setPlaceholder("Дополнительная связь с вами")
                        .setMinLength(2)
                        .setMaxLength(1000)
                        .build();

                Modal modal = Modal.create("contentmaker", "Заявка на контент-мейкера").addActionRows(ActionRow.of(url), ActionRow.of(frequency), ActionRow.of(connection))
                        .build();

                event.replyModal(modal).queue();
            } else if (typeOption.equalsIgnoreCase("moderator")) {
                TextInput why = TextInput.create("why", "Почему", TextInputStyle.SHORT)
                        .setPlaceholder("Причина становления судьей")
                        .setMinLength(5)
                        .setMaxLength(100)
                        .build();

                TextInput experience = TextInput.create("experience", "Опыт", TextInputStyle.SHORT)
                        .setPlaceholder("Ваш опыт в этом деле")
                        .setMinLength(2)
                        .setMaxLength(1000)
                        .build();

                TextInput connection = TextInput.create("connection", "Доп. связь", TextInputStyle.SHORT)
                        .setPlaceholder("Дополнительная связь с вами")
                        .setMinLength(2)
                        .setMaxLength(1000)
                        .build();

                Modal modal = Modal.create("moderator", "Заявка на модератора").addActionRows(ActionRow.of(why), ActionRow.of(experience), ActionRow.of(connection))
                        .build();

                event.replyModal(modal).queue();
            } else if (typeOption.equalsIgnoreCase("event")) {
                TextInput data = TextInput.create("data", "Дата и время", TextInputStyle.SHORT)
                        .setPlaceholder("Дата и время проведение ивента")
                        .setMinLength(5)
                        .setMaxLength(100)
                        .build();

                TextInput place = TextInput.create("place", "Место проведение", TextInputStyle.SHORT)
                        .setPlaceholder("Координаты или ориентир")
                        .setMinLength(5)
                        .setMaxLength(100)
                        .build();

                TextInput point = TextInput.create("point", "Описание ивента", TextInputStyle.PARAGRAPH)
                        .setPlaceholder("Описание")
                        .setMinLength(2)
                        .setMaxLength(1000)
                        .build();

                TextInput prizes = TextInput.create("prizes", "Призы в ивенте", TextInputStyle.SHORT)
                        .setPlaceholder("Что получат участники и победители")
                        .setMinLength(2)
                        .setMaxLength(1000)
                        .build();

                Modal modal = Modal.create("event", "Заявка на проведение ивента").addActionRows(ActionRow.of(data), ActionRow.of(point), ActionRow.of(prizes), ActionRow.of(place))
                        .build();

                event.replyModal(modal).queue();
            }
        }
    }
}
