create sequence account_id_sequence start with 1 increment by 1;
create sequence bank_id_sequence start with 1 increment by 1;
create sequence rewards_user_id_sequence start with 1 increment by 1;
create sequence user_activity_id_sequence start with 1 increment by 1;

create table bank (
        id bigint not null DEFAULT (NEXT VALUE FOR bank_id_sequence),
        conversion_rate numeric(19,2) not null,
        name varchar(255) not null unique ,
        primary key (id)
);

create table rewards_user (
        id bigint not null DEFAULT (NEXT VALUE FOR rewards_user_id_sequence),
        first_name varchar(255),
        last_name varchar(255),
        total_cash numeric(19,2),
        total_points bigint not null,
        user_name varchar(255) not null unique,
        primary key (id)
);

create table account (
       id bigint not null DEFAULT (NEXT VALUE FOR account_id_sequence),
        account_identifier varchar(255) unique ,
        cash numeric(19,2),
        points bigint not null,
        bank_id bigint,
        user_id bigint,
        primary key (id),
        constraint ac_bank_fk_id foreign key (bank_id) references bank,
        constraint ac_user_fk_id foreign key (user_id) references rewards_user
);

create table user_activity (
        id bigint not null DEFAULT (NEXT VALUE FOR user_activity_id_sequence),
        activity_date datetime2,
        description varchar(255),
        type int,
        rewards_user_id bigint,
        primary key (id),
        constraint user_activity_fk_rewards_user_id foreign key (rewards_user_id) references rewards_user
);


INSERT INTO bank(conversion_rate, name)
VALUES (0.2, 'Chase');

INSERT INTO bank(conversion_rate, name)
VALUES (0.3, 'WellsFargo');

INSERT INTO bank(conversion_rate, name)
VALUES (0.3, 'USBank');