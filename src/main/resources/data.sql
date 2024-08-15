INSERT INTO _user(username, email, password, role)
VALUES
    ('veljko', 'nikolicveljko01@gmail.com', '$2a$10$GvBT2HXPqMdOTKDbnjxEVOkvnABMUzNclJ3u91Qhcxysi8vxOK7/W', 0),
    ('marko', 'nikolicmarko1243@gmail.com', '$2a$10$aRzJxhDfBJdbvM8L7G366eZ5opg6/aXUNvJJYBmGYfNkSGTA0AYQy', 0);

INSERT INTO registered_user(id, first_name, last_name, profile_picture_path)
VALUES
    (1, 'Veljko', 'Nikolić', '1.png'),
    (2, 'Marko', 'Nikolić', '2.png');

INSERT INTO genre(name)
VALUES
    ('Pop'),
    ('Rock'),
    ('Folk'),
    ('Jazz'),
    ('Blues'),
    ('Country');

INSERT INTO band(owner_id, name, type, description)
VALUES
    (1, 'Duo Perfetto', 0, 'Osnovan 2021. godine jedan je od najpoznatijih akustičnih dvojaca u Novom Sadu i okolini. Mladi, entuzijastični muzičari koji vole dobru atmosferu.'),
    (1, 'Ritmico Acoustic', 0, 'Novi bend u Novom Sadu, već dobro poznat ljubiteljima akustične muzike.'),
    (2, 'Melodico Band', 1, 'Kako samo ime kaže, ako ste željni dobre melodije, našli ste pravi bend.');

INSERT INTO band_genres(band_id, genres_id)
VALUES
    (1, 1),
    (1, 3),
    (2, 1),
    (2, 3),
    (3, 1),
    (3, 3);

INSERT INTO listing(type, status)
VALUES
    (DEFAULT, DEFAULT),
    (DEFAULT, DEFAULT),
    (DEFAULT, DEFAULT);

INSERT INTO gig_listing(id, band_id, starting_price, price_per_additional_hour, minimum_duration_hours, maximum_additional_hours)
VALUES
    (1, 1, 12000, 3000, 3, 2),
    (2, 2, 18000, 4000, 3, 3),
    (3, 3, 24000, 6000, 3, 5);