package uz.pdp.backend.services.user_service;

import com.pengrad.telegrambot.model.Chat;
import uz.pdp.backend.models.bot_user.BotUser;
import uz.pdp.file.service.FileWriterOrLoader;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final List<BotUser> botUsers;

    private final FileWriterOrLoader<BotUser> fileWriterOrLoader = new FileWriterOrLoader<>("src/main/resources/users_db.txt");

    public UserServiceImpl() {
        this.botUsers = fileWriterOrLoader.load();
    }

    static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }

        return userService;
    }

    @Override
    public BotUser getOrCreateUser(Chat chat) {
        BotUser botUser = getUserById(chat.id());

        if (botUser == null) {
            botUser = BotUser.builder()
                    .firstName(chat.firstName())
                    .lastName(chat.lastName())
                    .userName(chat.username())
                    .build();

            addUser(botUser);
        }

        return botUser;
    }

    @Override
    public BotUser getUserById(Long id) {
        for (BotUser botUser : botUsers) {
            if (botUser.getId().equals(id)) {
                return botUser;
            }
        }
        return null;
    }

    @Override
    public void addUser(BotUser botUser) {
        botUsers.add(botUser);

        fileWriterOrLoader.write(botUsers);
    }
}
