package uz.pdp.ui;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import uz.pdp.ui.handlers.BotHandler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final String TOKEN = "6789038344:AAHbE6duttOiyH7W6u0MVbYQgtzjpbNsrfk";

    public static final TelegramBot BOT = new TelegramBot(TOKEN);

    public static final ThreadLocal<BotHandler> BOT_HANDLER_THREAD_LOCAL = ThreadLocal.withInitial(BotHandler::new);
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {
        BOT.setUpdatesListener(list -> {
            for (Update update : list) {
                CompletableFuture.runAsync(() -> {
                    BOT_HANDLER_THREAD_LOCAL.get().handle(update);
                }, EXECUTOR_SERVICE);

            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}