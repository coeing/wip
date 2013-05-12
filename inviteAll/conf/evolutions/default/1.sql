# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table guest (
  id                        integer auto_increment not null,
  name                      varchar(255),
  code                      varchar(255),
  is_group                  tinyint(1) default 0,
  decision                  integer,
  constraint ck_guest_decision check (decision in (0,1,2)),
  constraint uq_guest_code unique (code),
  constraint pk_guest primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table guest;

SET FOREIGN_KEY_CHECKS=1;

