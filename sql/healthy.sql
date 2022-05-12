drop database if exists healthy;

create database healthy;

use healthy;

create table user (
    username varchar(16) not null,
    password varchar(64) not null,
    height float,
    weight float,
    bmi float,
    goals mediumtext,

    primary key (username)
);

create table exercise (
    exercise_title varchar(16),
    exercise_date varchar(16),
    exercise_time timestamp,
    exercise_calories int,
    username varchar(16),

    primary key (exercise_time),
    constraint fk_username
        foreign key(username)
        references user(username)
        on delete cascade
);

create table exercise_set (
    step_num int auto_increment not null,
    step_description varchar(64) not null,
    step_count float,
    set_count int,
    set_rest_interval float,
    exercise_time timestamp,

    primary key (step_num),
    constraint fk_exercise_time
        foreign key(exercise_time)
        references exercise(exercise_time)
        on delete cascade
);