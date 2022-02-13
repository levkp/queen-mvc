## Programming 2.3 Assignment: Queen App

### Student
Levente Katai-Pal
levente.katai-pal@student.kdg.be


### Domain
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

### JDK version used
OpenJDK 17

### Extra information

The application is using two kinds of Spring profiles:

#### Data sources

`prod` makes use of the PostgreSQL database running on localhost

`dev` makes use of the embedded HyperSQL database

`dev2` makes use of Java collections

#### Method of CRUD operations

`jpa` uses the Jakarta Persistence API 

`jpa_rep` uses JPA's `JpaRepository` interface

`jdbc` would use JDBC templates, but well, it's not implemented

If `dev2` is active, there is obviously no need to set a second profile
