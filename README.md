# Programming 2.3 project: Queen App

## Student
Levente Katai-Pal

levente.katai-pal@student.kdg.be

## Run information

I use OpenJDK 17 for development.

Lombok needs to be enabled.

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

Concert was removed from the project for Programming 2.3 to make the relationships a bit easier to work with.
So, now there is a many-to-many relationship between Song and Genre (if it makes sense to say that about an enum) and a
one-to many relationship between Album and Song.

## Assignments for week 1

### Request to retrieve all albums
```http request
GET http://localhost:8080/api/albums HTTP/1.1
Accept: application/json
```

### Request to retrieve an album by its id
```http request
GET http://localhost:8080/api/albums/6 HTTP/1.1
Accept: application/json
```

### Request to delete an album by its id
```http request
DELETE http://localhost:8080/api/albums/12 HTTP/1.1
```

### Example responses

#### Requesting an existing album - OK
```http request
HTTP/1.1 200 
Content-Type: application/json

{
  "id": 6,
  "title": "Queen II",
  "description": null,
  "release": "1974-03-08",
  "songIds": [7, 8, 9, 10, 11]
}
```

#### Requesting or deleting a non-existing album - NOT FOUND
```http request
HTTP/1.1 404 
Content-Type: application/json

Unable to find album with id 1123
```

#### Requesting all albums but there are none - NO CONTENT
```http request
HTTP/1.1 204 

<Response body is empty>
```
## Assignments for week 2
### Creating a new album
```http request
POST http://localhost:8080/api/albums HTTP/1.1
Content-Type: application/json

{
  "title": A New Album",
  "release": "1981-03-03",
  "description": "Some text",
  "songIds": [
    1, 4, 5 
  ]
}
```

### Updating an album

### Example responses
#### Submitting valid data - OK

```http request
HTTP/1.1 200 
Content-Type: application/json

{
  "id": 21,
  "title": "A New Album",
  "description": "Some text",
  "release": "1981-03-03",
  "songIds": [
    1, 4, 5
  ],
}
```

#### Mandatory attribute is null - BAD REQUEST
```http request
HTTP/1.1 400 
Content-Type: application/json

[
  "Title is mandatory"
]
```
