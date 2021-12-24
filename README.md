## Programming 2.1 Project Assignment: Queen App

### Name
Levente Katai-Pal

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

--

As it's clearly visible, the program is barely finished. Most concepts we learnt are touched, but only some
weekly assignments are fully finished. This is because of my busy schedule and my occasionally poor study habits.
I know it won't affect the grading anymore, but I'll try to finish it anyway, because honestly this was one of the two
classes I enjoyed during the semester.
