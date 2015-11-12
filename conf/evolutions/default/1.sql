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

create table user (
  id                        varchar(255) not null,
  user_name                 varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  address                   varchar(255),
  phone_num                 varchar(255),
  constraint pk_user primary key (id))
;

create sequence tool_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists tool;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists tool_seq;

drop sequence if exists user_seq;

