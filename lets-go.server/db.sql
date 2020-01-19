drop database if exists LetsGoDB;

drop user if exists "hib"@"localhost";

create database LetsGoDB;
use LetsGoDB;

create user "hib"@"localhost" identified by "hib";
grant all privileges on * to "hib"@"localhost";

create table Games (
    id int not null,
    boardSize int not null,
    winner int,
    gameDate date not null,
    
    primary key (id)
);

create table Turns (
    id int not null auto_increment,
    gameId int not null,
    turnNumber int not null,
    
    primary key (id),
    foreign key fk_Turns_gameId (gameId) references Games (id)
);

create table Changes (
    id int not null auto_increment,
    turnId int not null,
    x int not null,
    y int not null,
    occupancy int not null,
    
    primary key (id),
    foreign key fk_Changes_turnId (turnId) references Turns (id)
);
