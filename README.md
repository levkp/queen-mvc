# Programming 2.3 project: Queen App

## Student
Levente Katai-Pal

levente.katai-pal@student.kdg.be

## Run information

I use OpenJDK 17 for development.

The application works with 2 kinds of Spring profiles:

### Data sources

`prod` makes use of the PostgreSQL database running on localhost

`dev` makes use of the embedded HyperSQL database

`dev2` makes use of Java collections

### Method of CRUD operations

`jpa` uses the Jakarta Persistence API

**`jpa_rep` and `jdbc` are not present in this implementation.**

For example, to use HyperSQL with JPA, this line needs to be set in the application.properties file:

`spring.profiles.active=prod,jpa`

If `dev2` is active, there is obviously no need to set a second profile.

## Domain
The project's entities are based on the early years of the rock band Queen.

| Album       | Song              | Concert       |
| ----------- | ----------------- | ------------- |
| title       | title             | name          |
| description | length            | attendance    |
| release     | genres            | location      |
| songs       | finishedRecording | date          |
|             | album             | playlist      |

An album has several songs, but a song only belongs to one album. 

A song has several genres. Genre is an enumeration.

A concert's playlist consists of multiple songs, and a song can be played 
on several concerts.

**Concert** was removed from the project for Programming 2.3 to make the relationships a bit easier to work with.
So, now there is a many-to-many relationship between Song and Genre (if it makes sense to say that about an enum) and a
one-to many relationship between Album and Song.


## Assignment for week 1

### To retrieve all albums

```http request
GET http://localhost:8080/api/albums HTTP/1.1
Accept: application/json
```










