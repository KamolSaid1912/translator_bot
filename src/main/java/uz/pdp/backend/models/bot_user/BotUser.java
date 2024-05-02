package uz.pdp.backend.models.bot_user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.backend.models.enums.bot_state.BotState;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BotUser {
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private BotState state;
}
