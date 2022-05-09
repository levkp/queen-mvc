# Programming 2.3 project: Queen App

## Student
Levente Katai-Pal

levente.katai-pal@student.kdg.be

## Run information

I use OpenJDK 17 for development.

Gradle: 7.2

The project depends on the Lombok annotations (e.g. `@Getter` , `@Setter`) library.

For this, in IntelliJ, annotation processing and the bundled Lombok extension need to be enabled.

Annotation processing can be turned on here: 
Build, Execution, Deployment - Annotation Processors - Enable annotation processing

The Gradle task `bootRun` will first run `npm run build` to compile frontend resources, then it will start the Spring 
application.
    
The application works with the following Spring profiles:

`prod` makes use of the PostgreSQL database running on localhost

`dev` makes use of the embedded HyperSQL database

`test` disables the initial data seeding

## Domain
The project's entities are based on the early years of the rock band Queen.

| Album        | Song             |
|--------------|-------------------|
| title        | title             |
| description  | length            |
| release      | genres            |
| songs        | finishedRecording |
|              | album             |

An album has several songs, but a song only belongs to one album. 

A song has several genres. Genre is an enumeration.

So, there is a many-to-many relationship between Song and Genre (if it makes sense to say that about an enum) and a
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
  "title": "A New Album",
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

#### Attribute fails validation - BAD REQUEST
```http request
HTTP/1.1 400 
Content-Type: application/json

[
  "Title is mandatory"
]
```

#### Attribute with unique constraint already exists - CONFLICT
```http request
HTTP/1.1 409 
Content-Type: text/plain;charset=UTF-8

ERROR: duplicate key value violates unique constraint "uk_3a47omptijvdn5qupc38c4dfi"
  Detail: Key (title)=(Queen) already exists.
```

## Assignments for week 3
Two bootstrap icons on `/albums`: `bi-plus-square` and `bi-trash`. The source code is located in albums.html. 

Two new npm dependencies: owoify and axios


To see the effect of owoify, create a new album on `/albums` with a long description. Here is a sample text:

```
The album was influenced by heavy metal and progressive rock. The lyrics are based on a variety of topics, including 
folklore and religion. Lead singer Freddie Mercury wrote five of the ten tracks, lead guitarist Brian May wrote four 
songs (including "Doing All Right", which he co-wrote with Tim Staffell while in the band Smile), and drummer Roger
Taylor both wrote and sang "Modern Times Rock and Roll". The final song on the album is a short instrumental version of
 "Seven Seas of Rhye", the full version of which would appear on the band's second album, Queen II.
```

After submitting, click the details button to see all attributes of the album.

## Assignments for week 4/5

There are two user logins: admin and standard. The password for both users is 'queen'.

The Spring security roles for these two profiles are `ROLE_ADMIN` and `ROLE_STANDARD`.

### Overview of permissions

<table>
    <thead>
        <td><b>Action</b></td>
        <td><b>Unauthenticated</b></td>
        <td>Standard</td>
        <td>Admin</td>
    </thead>
    <tbody>
        <tr>
            <td>Create via API/web</td>
            <td>no</td>
            <td>yes</td>
            <td>yes</td>
        </tr>
        <tr>
            <td>Read via API</td>        
            <td>all</td>
            <td>all</td>
            <td>all</td>
        </tr>
    </tbody>
</table>

| Action             | Unauthenticated | Standard | Admin |
|--------------------|-----------------|----------| ----- |
| Create via API/web | no              | yes      | yes   |
| Read via API       | all             | all      | all   |
| Update via API/web | no              | only own | all   |
| Delete via API/web | no              | only own | all   |
| Read via web       | no              | all      | all   |
| Access admin page  | no              | no       | yes   |

Unauthenticated users can only access the following pages: `/`, `/login` and `/register`.

When an authenticated user creates a new entity, they become the owner of it. They can only delete and update
their own entities. 

## Assignments for week 6
