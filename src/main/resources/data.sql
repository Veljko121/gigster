INSERT INTO _user(username, email, password, role)
VALUES
    ('veljko', 'nikolicveljko01@gmail.com', '$2a$10$GvBT2HXPqMdOTKDbnjxEVOkvnABMUzNclJ3u91Qhcxysi8vxOK7/W', 0);

INSERT INTO registered_user(id, first_name, last_name)
VALUES
    (1, 'Veljko', 'Nikolić');

INSERT INTO genre(name)
VALUES
    ('Pop'),
    ('Rock'),
    ('Folk');

INSERT INTO band(owner_id, type, name)
VALUES
    (1, 0, 'Duo Perfetto'),
    (1, 0, 'Ritmico Acoustic'),
    (1, 0, 'Melodico Acoustic');

INSERT INTO band_genres(band_id, genres_id)
VALUES
    (1, 1),
    (1, 3),
    (2, 1),
    (2, 3),
    (3, 1),
    (3, 3);