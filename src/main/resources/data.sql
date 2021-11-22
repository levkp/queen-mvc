INSERT INTO DUAL(DUMMY)
VALUES 'X';

INSERT INTO ALBUM(TITLE, RELEASE)
VALUES('Queen', '1973-7-13'),
      ('Queen II', '1974-3-8'),
      ('Sheer Heart Attack', '1974-11-8'),
      ('Jazz', '1978-11-10');

INSERT INTO SONG(TITLE, LENGTH, FINISHED_RECORDING, ALBUM_ID)
WITH vals (TITLE, LENGTH, FINISHED_RECORDING, ALBUM_TITLE)
         AS (
        VALUES
            ('Keep Yourself Alive', 3.47, '1972-11-01', 'Queen'),
            ('Doing ALl Right', 4.09, '1971-08-01', 'Queen'),
            ('Great King Rat', 5.41, '1971-09-01', 'Queen'),
            ('My Fairy King', 4.07, '1971-10-01', 'Queen'),
            ('Procession', 1.12, '1973-08-01', 'Queen II'),
            ('Father to Son', 6.14, '1973-08-01', 'Queen II'),
            ('White Queen (As It Began)', 4.34, '1974-02-01', 'Queen II'),
            ('Some Day One Day', 4.23, '1974-02-01', 'Queen II'),
            ('The Loser in the End', 4.02, '1974-04-01', 'Queen II'),
            ('Brighton Rock', 5.11, '1974-10-01', 'Sheer Heart Attack'),
            ('Killer Queen', 3.0, '1974-08-01', 'Sheer Heart Attack'),
            ('Flick of the Wrist', 3.18, '1974-08-01', 'Sheer Heart Attack'),
            ('Mustapha', 5.11, '1978-10-01', 'Jazz'),
            ('Fat Bottomed Girls', 3.0, '1978-10-01', 'Jazz'),
            ('Jealousy', 3.18, '1978-10-01', 'Jazz')
    )
SELECT v.TITLE, v.LENGTH, v.FINISHED_RECORDING, a.ID
FROM vals v JOIN ALBUM a ON v.ALBUM_TITLE = a.TITLE;

INSERT INTO GENRE(NAME)
VALUES('Art rock'), ('Avant pop'), ('Glam rock'), ('Hard rock'), ('Heavy metal'),
      ('Opera'), ('Power pop'), ('Progressive rock'), ('Soft rock');

-- INSERT INTO SONG_GENRE(SONG_ID, GENRE_ID)

