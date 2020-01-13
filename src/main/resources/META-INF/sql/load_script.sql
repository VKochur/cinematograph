--not used
--see persistence.xml

insert into users (id, login) values (1, 'anonymous')

insert into movies (id, description, name, url, owner_id) values (1, 'Советская комедия', 'Брилиантовая рука','https://ru.wikipedia.org/wiki/', 1)
insert into movies (id, description, name, url, owner_id) values (2, 'Советская комедия по рассказу Ильфа и Петрова, 4 серии', '12 стульев','https://ru.wikipedia.org/wiki/', 1)
insert into movies (id, description, name, url, owner_id) values (3, 'Ильф и Петров, 2 серии', '12 стульев','https://ru.wikipedia.org/wiki/', 1)
insert into movies (id, description, name, url, owner_id) values (4, 'Советское кино. По рассказу Булгакова', 'Собачье сердце','https://ru.wikipedia.org/wiki/', 1)
insert into movies (id, description, name, url, owner_id) values (5, 'Советское кино', 'Старики разбойники','https://ru.wikipedia.org/wiki/', 1)
insert into movies (id, description, name, url, owner_id) values (6, 'USA, 1984', 'Терминатор','https://ru.wikipedia.org/wiki/', 1)

insert into actors (id, info_url, name) values (1,'https://ru.wikipedia.org/wiki/','Миронов Андрей')
insert into actors (id, info_url, name) values (2,'https://ru.wikipedia.org/wiki/','Юрий Никулин')
insert into actors (id, info_url, name) values (3,'https://ru.wikipedia.org/wiki/','Анатолий Папанов')
insert into actors (id, info_url, name) values (4,'https://ru.wikipedia.org/wiki/','Евстигнеев')
insert into actors (id, info_url, name) values (5,'https://ru.wikipedia.org/wiki/','Гомиашвили')
insert into actors (id, info_url, name) values (6,'https://ru.wikipedia.org/wiki/','Арнольд Шварценнегер')
insert into actors (id, info_url, name) values (7,'https://ru.wikipedia.org/wiki/','Крачковская')

insert into tags (id, name)  values (1, 'Советское')
insert into tags (id, name)  values (2, 'Зарубежное')
insert into tags (id, name)  values (3, 'Фантастика')
insert into tags (id, name)  values (4, 'Комедия')
insert into tags (id, name)  values (5, 'Боевик')
insert into tags (id, name)  values (6, 'Драма')

insert into movie_actor_relations (movie_id, actor_id) values (1, 1)
insert into movie_actor_relations (movie_id, actor_id) values (1, 2)
insert into movie_actor_relations (movie_id, actor_id) values (1, 3)
insert into movie_actor_relations (movie_id, actor_id) values (1, 4)

insert into movie_actor_relations (movie_id, actor_id) values (2, 1)
insert into movie_actor_relations (movie_id, actor_id) values (2, 3)

insert into movie_actor_relations (movie_id, actor_id) values (3, 5)
insert into movie_actor_relations (movie_id, actor_id) values (3, 7)

insert into movie_actor_relations (movie_id, actor_id) values (4, 4)

insert into movie_actor_relations (movie_id, actor_id) values (5, 1)
insert into movie_actor_relations (movie_id, actor_id) values (5, 2)
insert into movie_actor_relations (movie_id, actor_id) values (5, 4)

insert into movie_actor_relations (movie_id, actor_id) values (6, 6)

insert into tag_movie_relations (tag_id, movie_id) values (1, 1)
insert into tag_movie_relations (tag_id, movie_id) values (1, 2)
insert into tag_movie_relations (tag_id, movie_id) values (1, 3)
insert into tag_movie_relations (tag_id, movie_id) values (1, 4)
insert into tag_movie_relations (tag_id, movie_id) values (1, 5)

insert into tag_movie_relations (tag_id, movie_id) values (2, 6)

insert into tag_movie_relations (tag_id, movie_id) values (3, 6)

insert into tag_movie_relations (tag_id, movie_id) values (4, 1)
insert into tag_movie_relations (tag_id, movie_id) values (4, 2)
insert into tag_movie_relations (tag_id, movie_id) values (4, 3)
insert into tag_movie_relations (tag_id, movie_id) values (4, 4)
insert into tag_movie_relations (tag_id, movie_id) values (4, 5)

insert into tag_movie_relations (tag_id, movie_id) values (5, 6)

insert into tag_movie_relations (tag_id, movie_id) values (6, 4)

