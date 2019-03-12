-- auto-generated definition
create table user_info
(
  id       int auto_increment
    primary key,
  userName varchar(20)       null,
  password varchar(125)      null,
  tel      varchar(11)       null,
  isEnable tinyint default 0 null comment '0正常, 1冻结',
  isDelete tinyint default 0 null comment '0正常, 已删除',
  constraint userName
    unique (userName)
);

