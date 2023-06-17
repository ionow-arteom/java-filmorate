package ru.yandex.practicum.filmorate.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReleaseDateValidator.class)
public @interface ReleaseDateValidation {
    String message() default "Дата релиза не может быть раньше 28 Декабря 1895г. Тогда еще не было кино!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}