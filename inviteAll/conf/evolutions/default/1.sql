# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table guest (
  id                        integer not null,
  name                      varchar(255),
  code                      varchar(255),
  is_group                  boolean,
  decision                  integer,
  constraint ck_guest_decision check (decision in (0,1,2)),
  constraint uq_guest_code unique (code),
  constraint pk_guest primary key (id))
;

create sequence guest_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists guest;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists guest_seq;

