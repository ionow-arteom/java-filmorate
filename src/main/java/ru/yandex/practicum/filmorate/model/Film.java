package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.annotation.ReleaseDateValidation;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    private int id;
    @NotBlank(message = "name не может быть пустым")
    private String name;
    @Size(max = 200, message = "максимальная длина description — 200 символов")
    private String description;
    @PastOrPresent(message = "realiseData не может быть в будущем")
    @ReleaseDateValidation
    private LocalDate releaseDate;
    @Positive(message = "duration не может быть отрицательной")
    private long duration;
    private int rate;
    private Mpa mpa;
            //= new Mpa(5, "NC-17", "Лицам до 18 лет просмотр запрещен");
    private List<Genre> genres = new ArrayList<>();
    private List<Integer> likes = new ArrayList<>();

    public boolean addLike(Integer userId) {
        return likes.add(userId);
    }

    public boolean deleteLike(Integer userId) {
        return likes.remove(userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return getId() == film.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
