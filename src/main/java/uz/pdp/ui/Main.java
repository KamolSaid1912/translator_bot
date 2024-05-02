package uz.pdp.ui;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.ui.handlers.BotHandler;

public class Main {
    public static final String TOKEN = "6789038344:AAHbE6duttOiyH7W6u0MVbYQgtzjpbNsrfk";

    public static final TelegramBot BOT = new TelegramBot(TOKEN);

    public static final ThreadLocal<BotHandler> BOT_HANDLER_THREAD_LOCAL = ThreadLocal.withInitial(BotHandler::new);

    public static void main(String[] args) {
        BOT.setUpdatesListener(list -> {
            try {
                for (Update update : list) {
                    BOT_HANDLER_THREAD_LOCAL.get().handle(update);
                }
            } finally {
                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }

        });
    }
}