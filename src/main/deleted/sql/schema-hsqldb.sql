DROP TABLE IF EXISTS concert_playlist;
DROP TABLE IF EXISTS song_genre;
DROP TABLE IF EXISTS song;
DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS concert;
DROP TABLE IF EXISTS genre;

CREATE TABLE album(
    id INTEGER IDENTITY,
    title VARCHAR(30) NOT NULL UNIQUE,
    release DATE NOT NULL,
    description VARCHAR(5000),

    PRIMARY KEY (id),
    CONSTRAINT album_title_ch CHECK (LENGTH(title) > 3),
    CONSTRAINT album_release_ch CHECK(release <= CURRENT_DATE)
);
CREATE TABLE song(
    id INTEGER IDENTITY,
    title VARCHAR(30) NOT NULL UNIQUE,
    length DECIMAL NOT NULL,
    finished_recording DATE NOT NULL,
    album_id INTEGER NOT NULL,
    description VARCHAR(5000),

    PRIMARY KEY (id),
    FOREIGN KEY (album_id) REFERENCES album(id) ON DELETE CASCADE,
    CONSTRAINT song_title_ch CHECK(LENGTH(title) > 3),
    CONSTRAINT song_FINISHED_RECORDING_ch CHECK(finished_recording <= CURRENT_DATE)
);

CREATE TABLE concert(
    id INTEGER IDENTITY,
    name VARCHAR(30) NOT NULL UNIQUE,
    LOCATION VARCHAR(80) NOT NULL,
    DATE DATE NOT NULL,
    description VARCHAR(5000),

    PRIMARY KEY (id),
    CONSTRAINT concert_date_ch CHECK(date <= CURRENT_DATE)
);

CREATE TABLE genre(
    id INTEGER NOT NULL IDENTITY,
    name VARCHAR(20) NOT NULL UNIQUE,

    PRIMARY KEY(id)
);

CREATE TABLE concert_playlist(
    id INTEGER IDENTITY,
    song_id INTEGER NOT NULL,
    concert_id INTEGER NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(song_id) REFERENCES song(id) ON DELETE CASCADE,
    FOREIGN KEY(concert_id) REFERENCES concert(id) ON DELETE CASCADE
);

CREATE TABLE song_genre(
    id INTEGER IDENTITY,
    song_id INTEGER NOT NULL,
    genre_id INTEGER NOT NULL,

    PRIMARY KEY(id),
    FOREIGN KEY(song_id) REFERENCES song(id) ON DELETE CASCADE,
    FOREIGN KEY(genre_id) REFERENCES genre(id) ON DELETE CASCADE
);
