INSERT INTO ALBUM(ID, TITLE, RELEASE)
VALUES(nextval('entity_pk_seq'), 'Queen', '1973-7-13'),
      (nextval('entity_pk_seq'), 'Queen II', '1974-3-8'),
      (nextval('entity_pk_seq'), 'Sheer Heart Attack', '1974-11-8'),
      (nextval('entity_pk_seq'), 'Jazz', '1978-11-10');

INSERT INTO GENRE(NAME)
VALUES('Art rock'), ('Avant pop'), ('Glam rock'), ('Hard rock'), ('Heavy metal'),
      ('Opera'), ('Power pop'), ('Progressive rock'), ('Soft rock');

INSERT INTO SONG(ID, TITLE, LENGTH, FINISHED_RECORDING, ALBUM_ID)
WITH vals (ID, TITLE, LENGTH, FINISHED_RECORDING, ALBUM_TITLE)
        AS (
        VALUES
            (nextval('entity_pk_seq'), 'Keep Yourself Alive', 3.47, '1972-11-01', 'Queen'),
            (nextval('entity_pk_seq'), 'Doing ALl Right', 4.09, '1971-08-01', 'Queen'),
            (nextval('entity_pk_seq'), 'Great King Rat', 5.41, '1971-09-01', 'Queen'),
            (nextval('entity_pk_seq'), 'My Fairy King', 4.07, '1971-10-01', 'Queen'),
            (nextval('entity_pk_seq'), 'Procession', 1.12, '1973-08-01', 'Queen II'),
            (nextval('entity_pk_seq'), 'Father to Son', 6.14, '1973-08-01', 'Queen II'),
            (nextval('entity_pk_seq'), 'White Queen (As It Began)', 4.34, '1974-02-01', 'Queen II'),
            (nextval('entity_pk_seq'), 'Some Day One Day', 4.23, '1974-02-01', 'Queen II'),
            (nextval('entity_pk_seq'), 'The Loser in the End', 4.02, '1974-04-01', 'Queen II'),
            (nextval('entity_pk_seq'), 'Brighton Rock', 5.11, '1974-10-01', 'Sheer Heart Attack'),
            (nextval('entity_pk_seq'), 'Killer Queen', 3.0, '1974-08-01', 'Sheer Heart Attack'),
            (nextval('entity_pk_seq'), 'Flick of the Wrist', 3.18, '1974-08-01', 'Sheer Heart Attack'),
            (nextval('entity_pk_seq'), 'Mustapha', 5.11, '1978-10-01', 'Jazz'),
            (nextval('entity_pk_seq'), 'Fat Bottomed Girls', 3.0, '1978-10-01', 'Jazz'),
            (nextval('entity_pk_seq'), 'Jealousy', 3.18, '1978-10-01', 'Jazz')
    )
SELECT v.ID, v.TITLE, v.LENGTH, TO_DATE(v.FINISHED_RECORDING, 'YYYY-MM-DD'), a.ID
FROM vals v JOIN ALBUM a ON v.ALBUM_TITLE = a.TITLE;