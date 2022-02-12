create table if not exists clients
(
    id         int auto_increment primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null
);

create table if not exists banks
(
    id   int auto_increment primary key,
    name varchar(255) not null
);

create table if not exists clients_banks
(
    client_id int not null,
    bank_id int not null,
    primary key (client_id, bank_id),
    foreign key (client_id) references clients (id),
    foreign key (bank_id) references banks (id)
);