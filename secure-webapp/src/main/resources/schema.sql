create table if not exists app_user
(
    user_id  serial primary key,
    username varchar(500) unique not null,
    password varchar(5000)       not null,
    role     varchar(5000)       not null
);

create table if not exists room
(
    room_id          serial primary key,
    room_number      smallint      not null,
    level            smallint      not null,
    room_description varchar(5000) not null
);

create table if not exists living_space
(
    living_space_id serial primary key,
    bedroom_id      integer not null,
    foreign key (bedroom_id) references room (room_id),
    kitchen_id      integer not null,
    foreign key (kitchen_id) references room (room_id),
    bathroom_id     integer not null,
    foreign key (bathroom_id) references room (room_id),
    storage_id      integer not null,
    foreign key (storage_id) references room (room_id)
);

create table if not exists flatmate
(
    flatmate_id     serial primary key,
    user_id         integer,
    foreign key (user_id) references app_user (user_id),
    living_space_id integer,
    foreign key (living_space_id) references living_space (living_space_id),
    firstname       varchar(500) not null,
    surname         varchar(500) not null,
    birthday        date         not null
);

create table if not exists item
(
    item_id        serial primary key,
    item_count     smallint     not null,
    item_name      varchar(500) not null,
    destination_id integer,
    foreign key (destination_id) references room (room_id),
    status         boolean
);

create table if not exists item_preset
(
    preset_id       serial primary key,
    preset_name     varchar(500) not null,
    supply_category varchar(30)  not null
);

create table if not exists persistent_logins
(
    username  varchar(500) not null,
    series    varchar(64) primary key,
    token     varchar(64)  not null,
    last_used timestamp    not null
);
