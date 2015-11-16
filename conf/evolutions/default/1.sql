# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tool (
  id                        varchar(255) not null,
  name                      varchar(255),
  description               varchar(255),
  owner                     varchar(255),
  borrower                  varchar(255),
  constraint pk_tool primary key (id))
;

create table users (
  id                        bigserial not null,
  user_name                 varchar(255),
  password_hash             varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  address                   varchar(255),
  phone_num                 varchar(255),
  email                     varchar(255),
  constraint uq_users_user_name unique (user_name),
  constraint pk_users primary key (id))
;




# --- !Downs

drop table if exists tool cascade;

drop table if exists users cascade;

