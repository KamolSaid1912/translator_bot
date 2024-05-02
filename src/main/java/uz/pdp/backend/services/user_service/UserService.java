package uz.pdp.backend.services.user_service;

import com.pengrad.telegrambot.model.Chat;
import uz.pdp.backend.models.bot_user.BotUser;

public interface UserService {
    BotUser getOrCreateUser(Chat chat);

    BotUser getUserById(Long id);

    void addUser(BotUser botUser);
}
