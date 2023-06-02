# Enterprised Web-Development (Bindo Thorpe)

Dit is een examen taak voor het vak Enterprised Web-Development.


## Requirements

Have Java JRE 17 or later installed.

Have MySQL installed.

## How to run

The first time you run this project you first need to load the database.

Create a new Schema and call it whatever you want. We will call it ```ewd_bibliotheek```.

Now add the following to ```application.properties```:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ewd_bibliotheek?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=<usernane>
spring.datasource.password=<password>
```

Now open MySQL and run the following query:

```sql
create table users (   
  username varchar(50) not null primary key,
  password varchar(255) not null,
  enabled boolean not null) ;

create table authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  foreign key (username) references users (username),
  unique index authorities_idx_1 (username, authority));

INSERT INTO users(username,password,enabled)
VALUES('nameUser', '$2y$10$E.442wS/c9QXLpkLcXaOY.Bet9jTm/aoOUi65yvtuvmJuBJJu1KcG', '1'),('admin', '$2y$10$xT2EKeAP.Ey84iy5dOwuOe5hxtRhvGVk6aLIpgAIpAzzu8xfJWPpO', '1');

INSERT INTO authorities(username,authority) 
VALUES ('nameUser', 'ROLE_USER'),('admin', 'ROLE_ADMIN');
```


Run ```EwdExamenopdrachtApplication```

Open http://localhost:8080

Log in with username ```nameUser``` and password ```12345678```