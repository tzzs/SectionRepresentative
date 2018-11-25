/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/5/1 18:15:45                            */
/*==============================================================*/


drop table if exists file;

drop table if exists homework;

drop table if exists user;

/*==============================================================*/
/* Table: file                                                  */
/*==============================================================*/
create table file
(
   account              varchar(100) not null,
   hno                  varchar(200) not null,
   file                 varchar(200),
   primary key (account, hno)
);

/*==============================================================*/
/* Table: homework                                              */
/*==============================================================*/
create table homework
(
   hno                  varchar(200) not null,
   hcontent             varchar(2000),
   hdir                 varchar(200),
   hfile                varchar(200),
   subinfo              varchar(200),
   issuer               varchar(200),
   begintime            datetime,
   endtime              datetime,
   primary key (hno)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   account              varchar(100) not null,
   password             varchar(100),
   name                 varchar(100),
   attention            varchar(100),
   email                varchar(100),
   school               varchar(100),
   identity             varchar(100),
   primary key (account)
);

alter table file add constraint FK_file foreign key (account)
      references user (account) on delete restrict on update restrict;

alter table file add constraint FK_file2 foreign key (hno)
      references homework (hno) on delete restrict on update restrict;

