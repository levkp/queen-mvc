DROP TABLE IF EXISTS CONCERT_PLAYLIST;
DROP TABLE IF EXISTS SONG_GENRE;
DROP TABLE IF EXISTS SONG;
DROP TABLE IF EXISTS ALBUM;
DROP TABLE IF EXISTS CONCERT;
DROP TABLE IF EXISTS GENRE;
DROP TABLE IF EXISTS DUAL;

CREATE TABLE ALBUM(
    ID INTEGER GENERATED ALWAYS AS IDENTITY,
    TITLE VARCHAR(30) NOT NULL UNIQUE,
    RELEASE DATE NOT NULL,
    DESCRIPTION VARCHAR(5000),

    PRIMARY KEY (ID),
    CONSTRAINT ALBUM_TITLE_CH CHECK (LENGTH(TITLE) > 3),
    CONSTRAINT ALBUM_RELEASE_CH CHECK(RELEASE <= CURRENT_DATE)
);
CREATE TABLE SONG(
    ID INTEGER GENERATED ALWAYS AS IDENTITY,
    TITLE VARCHAR(30) NOT NULL UNIQUE,
    LENGTH DECIMAL NOT NULL,
    FINISHED_RECORDING DATE NOT NULL,
    ALBUM_ID INTEGER NOT NULL,
    DESCRIPTION VARCHAR(5000),

    PRIMARY KEY (ID),
    FOREIGN KEY (ALBUM_ID) REFERENCES ALBUM(ID) ON DELETE CASCADE,
    CONSTRAINT SONG_TITLE_CH CHECK(LENGTH(TITLE) > 3),
    CONSTRAINT SONG_FINISHED_RECORDING_CH CHECK(FINISHED_RECORDING <= CURRENT_DATE)
);

CREATE TABLE CONCERT(
    ID INTEGER GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(30) NOT NULL UNIQUE,
    LOCATION VARCHAR(80) NOT NULL,
    DATE DATE NOT NULL,
    DESCRIPTION VARCHAR(5000),

    PRIMARY KEY (ID),
    CONSTRAINT CONCERT_DATE_CH CHECK(DATE <= CURRENT_DATE)
);

CREATE TABLE GENRE(
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(20) NOT NULL UNIQUE,

    PRIMARY KEY(ID)
);

CREATE TABLE CONCERT_PLAYLIST(
    ID INTEGER GENERATED ALWAYS AS IDENTITY,
    SONG_ID INTEGER NOT NULL,
    CONCERT_ID INTEGER NOT NULL,

    PRIMARY KEY(ID),
    FOREIGN KEY(SONG_ID) REFERENCES SONG(ID) ON DELETE CASCADE,
    FOREIGN KEY(CONCERT_ID) REFERENCES CONCERT(ID) ON DELETE CASCADE
);

CREATE TABLE SONG_GENRE(
    ID INTEGER GENERATED ALWAYS AS IDENTITY,
    SONG_ID INTEGER NOT NULL,
    GENRE_ID INTEGER NOT NULL,

    PRIMARY KEY(ID),
    FOREIGN KEY(SONG_ID) REFERENCES SONG(ID) ON DELETE CASCADE,
    FOREIGN KEY(GENRE_ID) REFERENCES GENRE(ID) ON DELETE CASCADE
);

CREATE TABLE DUAL(
    DUMMY VARCHAR(1)
);

