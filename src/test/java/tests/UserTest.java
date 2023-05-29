package tests;

import org.testng.annotations.Test;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.ValidateService;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void email() {
        ValidateService validateService = new ValidateService();
        User user = new User();
        user.setEmail(" ");
        assertThrows(RuntimeException.class, () -> ValidateService.validateId(user));
    }

    @Test
    void login() {
        ValidateService validateService = new ValidateService();
        User user = new User();
        user.setEmail("email@list.ru");
        user.setLogin(" ");
        assertThrows(RuntimeException.class, () -> ValidateService.validateId(user));
    }

    @Test
    void nameUser() {
        ValidateService validateService = new ValidateService();
        User user = new User();
        user.setEmail("email@list.ru");
        user.setLogin("login");
        user.setName(" ");
        assertThrows(RuntimeException.class, () -> ValidateService.validateId(user));
    }

    @Test
    void birthday() {
        ValidateService validateService = new ValidateService();
        User user = new User();
        user.setEmail("email@list.ru");
        user.setLogin("login");
        user.setName("");
        user.setBirthday(LocalDate.of(2045, 5, 5));
        assertThrows(RuntimeException.class, () -> ValidateService.validateId(user));
    }
}