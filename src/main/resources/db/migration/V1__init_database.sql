create table events (
    id bigserial not null,
    total_tables integer,
    seats_available integer,
    primary key (id)
);

create table guests (
    id bigserial not null,
    accompanying_guests integer,
    name varchar(255),
    table_number integer,
    time_arrived varchar(255),
    event_id bigint,
    primary key (id)
);

alter table if exists guests add constraint FKlh0yk4obxangboybk2b6u49b9 foreign key (event_id) references events;
