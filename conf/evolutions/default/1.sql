# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  id                        bigserial not null,
  body                      varchar(255),
  poster                    varchar(255),
  tool_id                   bigint,
  datetime_posted           varchar(255),
  constraint pk_comment primary key (id))
;

create table tool (
  id                        bigserial not null,
  name                      varchar(255),
  description               varchar(255),
  img_url                   varchar(255),
  available                 boolean,
  user_id                   bigint,
  tool_category_id          bigint,
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

alter table comment add constraint fk_comment_tool_1 foreign key (tool_id) references tool (id);
create index ix_comment_tool_1 on comment (tool_id);
alter table tool add constraint fk_tool_owner_2 foreign key (user_id) references users (id);
create index ix_tool_owner_2 on tool (user_id);
alter table tool add constraint fk_tool_toolcategory_3 foreign key (tool_category_id) references tool_category (id);
create index ix_tool_toolcategory_3 on tool (tool_category_id);



# --- !Downs

drop table if exists comment cascade;

drop table if exists tool cascade;

drop table if exists tool_category cascade;

drop table if exists users cascade;

