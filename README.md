 # Java Filmorare Project
 ## Диаграмма базы данных
![Untitled](https://github.com/ionow-arteom/java-filmorate/assets/119430057/3fd89621-c332-4c6b-b008-f68963a30260)
## Примеры запросов базы данных
- Получение информации о фильме по его идентификатору: SELECT * FROM film WHERE id = 1;
- Получение информации о пользователе по его идентификатору: SELECT * FROM user WHERE id = 1;
- Получить список фильмов, лайкнутых конкретным пользователем: SELECT film.* FROM film JOIN likes ON film.id = likes.film_id WHERE likes.user_id = 1;
- Получить список друзей пользователя: SELECT user.* FROM user JOIN user_friends ON user.id = user_friends.friend_id WHERE user_friends.user_id = 1;

