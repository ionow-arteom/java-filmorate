package ru.yandex.practicum.filmorate.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ReleaseDateValidator implements ConstraintValidator<ReleaseDateValidation, LocalDate> {
    private static final LocalDate MIN_RELEASE_DATE = LocalDate.of(1895, 12, 28);

    public boolean isValid(LocalDate releaseDate, ConstraintValidatorContext context) {
        return releaseDate != null && !releaseDate.isBefore(MIN_RELEASE_DATE);
    }
}