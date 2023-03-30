DROP DATABASE IF EXISTS game;
CREATE DATABASE game;
USE game;

CREATE TABLE `user` (
firstName VARCHAR(50) not null,
lastName VARCHAR(50) not null,
patronymic VARCHAR(50),
login VARCHAR(50) not null,
password VARCHAR(50) not null,
primary key (login)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE teacher (
login VARCHAR(50) not null,
email VARCHAR(50) not null,
primary key (login),
foreign key(login) references `user` (login) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE teacher_class (
id INT(11) not null auto_increment,
loginTeacher VARCHAR(50) not null,
class INT not null,
primary key (id),
foreign key(loginTeacher) references teacher (login) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE student (
login VARCHAR(50) not null,
class INT not null,
primary key (login),
foreign key(login) references `user` (login) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE token_user (
token VARCHAR(50) not null,
loginUser VARCHAR(50) not null,
primary key (token),
foreign key(loginUser) references `user` (login) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE task (
id INT(11) not null auto_increment,
class INT not null,
topic VARCHAR(50) not null,
`condition` VARCHAR(50) not null,
answer VARCHAR(50) not null,
loginTeacher VARCHAR(50) not null,
primary key (id),
foreign key(loginTeacher) references teacher (login) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE room(
password VARCHAR(50) not null,
name VARCHAR(50) not null,
loginTeacher VARCHAR(50) not null,
class INT not null,
`date` DATE not null,
roundTime INT not null,
primary key (password),
foreign key(loginTeacher) references teacher (login) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE room_student(
id INT(11) not null auto_increment,
loginStudent VARCHAR(50) not null,
passwordRoom VARCHAR(50) not null,
primary key (id),
foreign key(loginStudent) references student (login) on delete cascade,
foreign key(passwordRoom) references room (password) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE room_task(
id INT(11) not null auto_increment,
idTask INT(11) not null,
passwordRoom VARCHAR(50) not null,
primary key (id),
foreign key(passwordRoom) references room (password) on delete cascade,
foreign key(idTask) references task (id) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE game_tasks_info(
id INT(11) not null auto_increment,
loginStudent VARCHAR(50) not null,
passwordRoom VARCHAR(50) not null,
idTask INT(11) not null,
isRightTask BIT not null,
primary key (id),
foreign key(passwordRoom) references room (password) on delete cascade,
foreign key(idTask) references task (id) on delete cascade,
foreign key(loginStudent) references student (login) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE round_info(
id INT(11) not null auto_increment,
loginStudent VARCHAR(50) not null,
passwordRoom VARCHAR(50) not null,
numberRound INT(11) not null ,
`level` INT(11) not null,
countShots INT(11) not null,
countMedicine INT(11) not null,
primary key (id),
foreign key(passwordRoom) references room (password) on delete cascade,
foreign key(loginStudent) references student (login) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;
