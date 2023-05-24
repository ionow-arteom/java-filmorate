package tests;

import org.testng.annotations.Test;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.ValidateService;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilmTest {
    @Test
    void nameFilm() {
        ValidateService validateService = new ValidateService();
        Film film = new Film();
        film.setName(" ");
        assertThrows(RuntimeException.class, () -> ValidateService.validateFilm(film));
    }

    @Test
    void description() {
        ValidateService validateService = new ValidateService();
        Film film = new Film();
        film.setName("name film");
        film.setDescription("F".repeat(201));
        assertThrows(RuntimeException.class, () -> ValidateService.validateFilm(film));
    }

    @Test
    void realiseDate() {
        ValidateService validateService = new ValidateService();
        Film film = new Film();
        film.setName("name film");
        film.setDescription("test test test test kat test test");
        film.setReleaseDate(LocalDate.of(1845, 5, 5));
        assertThrows(RuntimeException.class, () -> ValidateService.validateFilm(film));
    }

    @Test
    void duration() {
        ValidateService validateService = new ValidateService();
        Film film = new Film();
        film.setName("name film");
        film.setDescription("harry potter play with mom");
        film.setReleaseDate(LocalDate.of(1945, 5, 5));
        film.setDuration(-2L);
        assertThrows(RuntimeException.class, () -> ValidateService.validateFilm(film));
    }
}