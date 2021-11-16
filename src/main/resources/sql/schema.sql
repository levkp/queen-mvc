DROP TABLE IF EXISTS ALBUM;
CREATE TABLE ALBUM(
    ID INTEGER NOT NULL IDENTITY,
    TITLE VARCHAR(40) NOT NULL,
    RELEASE DATE NOT NULL,
    COMMENT VARCHAR(5000),

    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS SONG;
CREATE TABLE SONG(
    ID INTEGER NOT NULL IDENTITY,
    TITLE VARCHAR(40) NOT NULL,
    GENRES VARCHAR(200) NOT NULL,
    FINISHED_RECORDING DATE NOT NULL,
    ALBUM_ID INTEGER NOT NULL,
    COMMENT VARCHAR(5000),

    PRIMARY KEY (ID),
    FOREIGN KEY (ID) REFERENCES ALBUM(ID)
);

DROP TABLE IF EXISTS CONCERT;
CREATE TABLE CONCERT(
    ID INTEGER NOT NULL IDENTITY,
    NAME VARCHAR(40),
    LOCATION VARCHAR(80),
    DATE DATE,

    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS CONCERT_PLAYLIST;
CREATE TABLE CONCERT_PLAYLIST(
    ID INTEGER NOT NULL IDENTITY,
    SONG_ID INTEGER NOT NULL,
    CONCERT_ID INTEGER NOT NULL,

    PRIMARY KEY (ID)
)