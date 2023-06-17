package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired(required = false) UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<User> findAll() {
        log.info("Получен запрос GET к эндпоинту: /users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}/friends")
    public Collection<User> findFriends(@PathVariable String id) {
        log.info("Получен запрос GET к эндпоинту: /users/{}/friends", id);
        return userService.getFriends(id);
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable String id) {
        log.info("Получен запрос GET к эндпоинту: /users/{}/", id);
        return userService.getUser(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public Collection<User> findCommonFriends(@PathVariable String id, @PathVariable String otherId) {
        log.info("Получен запрос GET к эндпоинту: /users/{}/friends/common/{}", id, otherId);
        return userService.getCommonFriends(id, otherId);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        log.info("Получен запрос POST. Данные тела запроса: {}", user);
        final User validUser = userService.add(user);
        log.info("Создан объект {} с id {}", User.class.getSimpleName(), validUser.getId());
        return validUser;
    }

    @PutMapping
    public User put(@RequestBody User user) {
        log.info("Получен запрос PUT. Данные тела запроса: {}", user);
        final User validUser = userService.update(user);
        log.info("Обновлен объект {} с id {}", User.class.getSimpleName(), validUser.getId());
        return validUser;
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable String id, @PathVariable String friendId) {
        log.info("Получен запрос PUT к эндпоинту: /users/{}/friends/{}", id, friendId);
        userService.addFriend(id, friendId);
        log.info("Обновлен объект {} с id {}. Добавлен друг {}",
                User.class.getSimpleName(), id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable String id, @PathVariable String friendId) {
        log.info("Получен запрос DELETE к эндпоинту: /users/{}/friends/{}", id, friendId);
        userService.deleteFriend(id, friendId);
        log.info("Обновлен объект {} с id {}. Удален друг {}",
                User.class.getSimpleName(), id, friendId);
    }
}