package uz.pdp.ui.handlers;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import uz.pdp.backend.models.bot_user.BotUser;
import uz.pdp.backend.services.user_service.UserService;
import uz.pdp.backend.services.user_service.UserServiceImpl;

import java.util.Objects;

import static uz.pdp.ui.Main.TOKEN;

public class BotHandler {
    private final UserService userService = UserServiceImpl.getInstance();

    private final TelegramBot bot = new TelegramBot(TOKEN);

    public void handle(Update update) {
        Message message = update.message();

        if (message != null) {
            Chat chat = message.chat();
            if (chat != null) {
                String text = message.text();
                BotUser currentBotUser = userService.getOrCreateUser(chat);

                if (text != null) {
                    System.out.println(currentBotUser.getUserName() + " : " + text);
                    if (Objects.equals("/start",text)) {

                        bot.execute(new SendMessage(chat.id(), "Welcome to bot!"));

                    }
                }
            }
        }
    }

    private void sentText(Long id, String text) {
        SendMessage sendMessage = new SendMessage(id, text);

        bot.execute(sendMessage);
    }
}
