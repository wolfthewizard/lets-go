drop database if exists LetsGoDB;

create database LetsGoDB;
use LetsGoDB;

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
    turnId int not null,
    occupancy int not null,
    coordinates int not null,
    
    foreign key fk_Changes_turnId (turnId) references Turns (id)
);
