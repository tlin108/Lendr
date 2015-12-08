# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tool (
  id                        bigserial not null,
  name                      varchar(255),
  description               varchar(255),
  owner_id                  bigint,
  toolcategory_id           bigint,
  constraint pk_tool primary key (id))
;

create table tool_category (
  id                        bigserial not null,
  name                      varchar(255),
  constraint pk_tool_category primary key (id))
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

alter table tool add constraint fk_tool_owner_1 foreign key (owner_id) references users (id);
create index ix_tool_owner_1 on tool (owner_id);
alter table tool add constraint fk_tool_toolcategory_2 foreign key (toolcategory_id) references tool_category (id);
create index ix_tool_toolcategory_2 on tool (toolcategory_id);



# --- !Downs

drop table if exists tool cascade;

drop table if exists tool_category cascade;

drop table if exists users cascade;

