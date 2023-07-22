-- Borrar la tabla si ya existe
DROP TABLE IF EXISTS test.auth_user_roles;

-- Borrar la tabla si ya existe
DROP TABLE IF EXISTS test.auth_roles;

-- Borrar la tabla si ya existe
DROP TABLE IF EXISTS test.auth_users;

create table test.auth_users
(
    id               bigint       not null
        primary key auto_increment,
    activation_token varchar(255) null,
    active           bit          null,
    email            varchar(255) null,
    password         varchar(255) null,
    type             varchar(255) null,
    username         varchar(255) null
);

create table test.auth_roles
(
    id   bigint auto_increment
        primary key,
    name varchar(20) null
);

create table test.auth_user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references test.auth_users (id),
    foreign key (role_id) references test.auth_roles (id)
);







