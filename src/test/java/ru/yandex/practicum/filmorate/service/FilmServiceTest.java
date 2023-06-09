package ru.yandex.practicum.filmorate.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.yandex.practicum.filmorate.exception.FilmValidationException;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FilmServiceTest {
    @Autowired
    private FilmService service;

    @Test
    void shouldAddWhenAddValidFilmData() {

        Film film = new Film();
        film.setName("Шрек");
        film.setDescription("Куда же без него");
        film.setReleaseDate(LocalDate.of(2001,5,18));
        film.setDuration(200L);
        film.setRate(0);
        film.setMpa(new Mpa(1, "mpa", "description"));
        Film addedFilm = service.add(film);
        assertNotEquals(0, addedFilm.getId());
    }

    @Test
    void shouldThrowExceptionWhenAddFailedFilmNameEmpty() {
        Film film = new Film();
        film.setName("");
        film.setDescription("Куда же без него");
        film.setReleaseDate(LocalDate.of(2001,5,18));
        film.setDuration(200L);
        film.setMpa(new Mpa(1, "mpa", "description"));
        FilmValidationException ex = assertThrows(FilmValidationException.class, () -> service.add(film));
        assertEquals("Ошибка валидации фильма: " +
                "name не может быть пустым", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAddFailedFilmNameBlank() {
        Film film = new Film();
        film.setName("  ");
        film.setDescription("Куда же без него");
        film.setReleaseDate(LocalDate.of(2001,5,18));
        film.setDuration(200L);
        film.setMpa(new Mpa(1, "mpa", "description"));
        FilmValidationException ex = assertThrows(FilmValidationException.class, () -> service.add(film));
        assertEquals("Ошибка валидации фильма: " +
                "name не может быть пустым", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAddFailedFilmDuration() {
        Film film = new Film();
        film.setName("Шрек");
        film.setDescription("Куда же без него");
        film.setReleaseDate(LocalDate.of(2001,5,18));
        film.setDuration(-100L);
        film.setMpa(new Mpa(1, "mpa", "description"));
        FilmValidationException ex = assertThrows(FilmValidationException.class, () -> service.add(film));
        assertEquals("Ошибка валидации фильма: " +
                "duration не может быть отрицательной", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAddFailedFilmReleaseDate() {
        Film film = new Film();
        film.setName("Шрек");
        film.setDescription("Куда же без него");
        film.setReleaseDate(LocalDate.of(1895,12,27));
        film.setDuration(200L);
        film.setMpa(new Mpa(1, "mpa", "description"));
        FilmValidationException ex = assertThrows(FilmValidationException.class, () -> service.add(film));
        assertEquals("Ошибка валидации фильма: " +
                "Дата релиза не может быть раньше 28 Декабря 1895г. Тогда еще не было кино!", ex.getMessage());
    }

    @Test
    void shouldAddWhenAddValidFilmReleaseDateBoundary() {
        Film film = new Film();
        film.setName("Шрек");
        film.setDescription("Куда же без него");
        film.setReleaseDate(LocalDate.of(1895,12,28));
        film.setDuration(200L);
        film.setMpa(new Mpa(1, "mpa", "description"));
        Film addedFilm = service.add(film);
        assertNotEquals(0, addedFilm.getId());
    }

    @Test
    void shouldThrowExceptionWhenAddFailedFilmDescription() {
        Film film = new Film();
        film.setName("Шрек");
        film.setDescription("F".repeat(201));
        film.setReleaseDate(LocalDate.of(2001,5,18));
        film.setDuration(200L);
        film.setMpa(new Mpa(1, "mpa", "description"));
        FilmValidationException ex = assertThrows(FilmValidationException.class, () -> service.add(film));
        assertEquals("Ошибка валидации фильма: " +
                "максимальная длина description — 200 символов", ex.getMessage());
    }

    @Test
    void shouldAddWhenAddFilmDescriptionBoundary() {
        Film film = new Film();
        film.setName("Шрек");
        film.setDescription("F".repeat(199));
        film.setReleaseDate(LocalDate.of(2001,5,18));
        film.setDuration(200L);
        film.setMpa(new Mpa(1, "mpa", "description"));
        Film addedFilm = service.add(film);
        assertNotEquals(0, addedFilm.getId());
    }

    @Test
    void shouldThrowExceptionWhenUpdateFailedFilmId() {
        Film film = new Film();
        film.setId(123);
        film.setName("Шрек");
        film.setDescription("Куда же без него");
        film.setReleaseDate(LocalDate.of(2001,5,18));
        film.setDuration(200L);
        film.setMpa(new Mpa(1, "mpa", "description"));
        NotFoundException ex = assertThrows(NotFoundException.class, () -> service.update(film));
        assertEquals("Фильм с id 123 не зарегистрирован! =(", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUpdateNotMpa() {
        Film film = new Film();
        film.setName("Неизвестный фильм");
        film.setDescription("Морская пехота на обложке");
        film.setReleaseDate(LocalDate.of(2001,5,22));
        film.setDuration(200L);
        Film addedFilm = service.add(film);
        assertNotEquals(0, addedFilm.getId());
    }
}
