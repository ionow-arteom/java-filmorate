package ru.yandex.practicum.filmorate.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.UserValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    void shouldAddUserWhenValidUserData() {
        User user = new User(0,
                "pupkin.email@mail.ru",
                "lupkin",
                "Игорь",
                LocalDate.of(2001, 1, 1),
                new ArrayList<>());
        User addedUser = service.add(user);
        assertNotEquals(0, addedUser.getId());
        assertTrue(service.getAllUsers().contains(addedUser));
    }

    @Test
    void shouldSetUserNameWhenEmptyUserName() {
        User user = new User(0,
                "pupkin1.email@mail.ru",
                "lupkin1",
                "",
                LocalDate.of(2001, 1, 1),
                new ArrayList<>());
        User addedUser = service.add(user);
        assertNotEquals(0, addedUser.getId());
        assertEquals(addedUser.getLogin(), addedUser.getName());
        assertTrue(service.getAllUsers().contains(addedUser));
    }

    @Test
    void shouldThrowExceptionWhenFailedUserLogin() {
        User user = new User(0,
                "pupkin.email@mail.ru",
                "lup kin",
                "Игорь",
                LocalDate.of(2001, 1, 1),
                new ArrayList<>());
        UserValidationException ex = assertThrows(UserValidationException.class, () -> service.add(user));
        assertEquals("Ошибка валидации пользователя: " +
                "Логин не может содержать пробелы.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenFailedUserEmail() {
        User user = new User(0,
                "pupkin.email",
                "lupkin",
                "Игорь",
                LocalDate.of(2001, 1, 1),
                new ArrayList<>());
        UserValidationException ex = assertThrows(UserValidationException.class, () -> service.add(user));
        assertEquals("Ошибка валидации пользователя: " +
                "Введенное значение не является адресом электронной почты.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenFailedUserBirthDate() {
        User user = new User(0,
                "pupkin.email@mail.ru",
                "lupkin",
                "Игорь",
                LocalDate.now().plusDays(1),
                new ArrayList<>());
        UserValidationException ex = assertThrows(UserValidationException.class, () -> service.add(user));
        assertEquals("Ошибка валидации пользователя: " +
                "Дата рождения не может быть в будущем.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUpdateFailedUserId() {
        User user = new User(99,
                "pupkin.email@mail.ru",
                "lupkin",
                "Игорь",
                LocalDate.now().plusYears(-33),
                new ArrayList<>());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> service.update(user));
        assertEquals("Пользователь с id 99 не зарегистрирован!", ex.getMessage());
    }
}