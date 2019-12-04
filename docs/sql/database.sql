drop database if exists db_pwdmng;
create database db_pwdmng;


use db_pwdmng;
show variables like '%time_zone%';
set global time_zone='+8:00';

drop table if exists system_user;
create table system_user (
  user_id bigint not null auto_increment primary key comment  '用户表主键-自增',
  user_info bigint comment '用户信息表外键',
  login_name varchar(50) not null comment '登录名，英文字母，非空',
  passsword varchar(50) not null comment '密码，非空',
  is_admin char(1) default 'N' comment 'Y：超级管理员，N：角色用户',
  create_date date comment '创建日期',
  created_by varchar(50) comment '创建者',
  last_update_date date comment '更新时间',
  last_updated_by varchar(50) comment '更新者'
);

insert into system_user(
  login_name ,  passsword ,is_admin ,create_date ,created_by , last_update_date ,last_updated_by
) values (
  'admin' , 'e10adc3949ba59abbe56e057f20f883e' , 'Y', current_date  , 'SYSTEM' , current_date , 'SYSTEM');


insert into system_user(
  login_name ,  passsword ,is_admin ,create_date ,created_by , last_update_date ,last_updated_by
) values (
  'fwj' , 'e10adc3949ba59abbe56e057f20f883e' , 'Y', current_date  , 'SYSTEM' , current_date , 'SYSTEM');

  insert into system_user(
  login_name ,  passsword ,is_admin ,create_date ,created_by , last_update_date ,last_updated_by
) values (
  'qzj' , 'e10adc3949ba59abbe56e057f20f883e' , 'Y', current_date  , 'SYSTEM' , current_date , 'SYSTEM');


drop table if exists system_role;
create table system_role (
  role_id bigint not null  auto_increment primary key comment  '角色表主键-自增',
  role_name varchar(50) not null comment '角色名',
  role_desc varchar(200) comment '角色描述',
  enable char(1) default 'Y' comment '是否启用， Y启用 ，N禁用',
  create_date date comment '创建日期',
  created_by varchar(50) comment '创建者',
  last_update_date date comment '更新时间',
  last_updated_by varchar(50) comment '更新者'
);
insert into system_role(
  role_name ,  role_desc  , enable ,create_date  ,created_by , last_update_date ,last_updated_by
) values (
  'administrator' , 'System Manager , but cant operate user , role , menu.' , 'Y' , current_date  , 'SYSTEM' , current_date , 'SYSTEM');


drop table if exists system_menu;
create table system_menu (
  menu_id bigint not null  auto_increment primary key comment  '菜单表主键-自增',
  menu_name varchar(50) not null comment '菜单名',
  menu_desc varchar(200) comment '菜单描述',
  menu_path varchar(200) comment '菜单链接',
  menu_parent bigint comment '父级菜单主键',
  menu_order int default 1 comment '同级别的序列',
  has_child char(1) not null comment '是否有子级菜单， Y有， N无',
  level int not null comment '所在层级 ，-1 为最底层',
  enable char(1) not null default 'Y' comment '是否启用， Y启用 ，N禁用',
  create_date date comment '创建日期',
  created_by varchar(50) comment '创建者',
  last_update_date date comment '更新时间',
  last_updated_by varchar(50) comment '更新者'
);

insert into system_menu(
  menu_name ,  menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'root' , 'Root Menu', null , null , 1 , 'Y' , -1 , 'Y' , current_date  , 'SYSTEM' , current_date , 'SYSTEM');

insert into system_menu(
  menu_name ,  menu_desc  , menu_path ,
  menu_parent ,
  menu_order , has_child , level , enable  , create_date ,created_by , last_update_date ,last_updated_by
) values (
  'sys_fun' , 'System Function', null ,
  ( select t.menu_id from ( select menu_id from system_menu where menu_parent is null )t )
   , 1 , 'Y' , 0 , 'Y' , current_date  , 'SYSTEM' , current_date , 'SYSTEM');

insert into system_menu(
  menu_name ,  menu_desc  , menu_path ,
  menu_parent ,
  menu_order , has_child , level , enable  , create_date ,created_by , last_update_date ,last_updated_by
) values (
  'main_fun' , 'Main Function', null ,
     ( select t.menu_id from ( select menu_id from system_menu where menu_parent is null )t )
   , 2 , 'N' , 0 , 'Y' , current_date  , 'SYSTEM' , current_date , 'SYSTEM');


   insert into system_menu(
  menu_name ,  menu_desc  , menu_path ,
  menu_parent ,
  menu_order , has_child , level , enable  , create_date ,created_by , last_update_date ,last_updated_by
) values (
  'logs' , 'Logs', null ,
     ( select t.menu_id from ( select menu_id from system_menu where menu_parent is null )t )
   , 3 , 'N' , 0 , 'Y' , current_date  , 'SYSTEM' , current_date , 'SYSTEM');

insert into system_menu(
  menu_name ,  menu_desc  , menu_path ,
  menu_parent ,
  menu_order , has_child , level , enable  , create_date ,created_by , last_update_date ,last_updated_by
) values (
  'manager_user' , 'User Manager', '/sys/manager/user' ,
   ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'sys_fun' )t )
   , 1 , 'N' , 1 , 'Y' , current_date  , 'SYSTEM' , current_date , 'SYSTEM');

insert into system_menu(
  menu_name ,  menu_desc  , menu_path ,
  menu_parent ,
  menu_order , has_child , level , enable  , create_date ,created_by , last_update_date ,last_updated_by
) values (
  'manager_role' , 'Role Manager', '/sys/manager/role' ,
   ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'sys_fun' )t )
   , 2 , 'N' , 1 , 'Y' , current_date  , 'SYSTEM' , current_date , 'SYSTEM');

insert into system_menu(
  menu_name ,  menu_desc  , menu_path ,
  menu_parent ,
  menu_order , has_child , level , enable  , create_date ,created_by , last_update_date ,last_updated_by
) values (
  'manager_menu' , 'Menu Manager', '/sys/manager/menu' ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'sys_fun' )t )
   , 3 , 'N' , 1 , 'Y' , current_date  , 'SYSTEM' , current_date , 'SYSTEM');

   insert into system_menu(
  menu_name ,  menu_desc  , menu_path ,
  menu_parent ,
  menu_order , has_child , level , enable  , create_date ,created_by , last_update_date ,last_updated_by
) values (
  'mainfun_game' , 'Game', null ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'main_fun' ) t )
   , 1 , 'N' , 1 , 'Y' , current_date  , 'SYSTEM' , current_date , 'SYSTEM');

  insert into system_menu(
  menu_name ,  menu_desc  , menu_path ,
  menu_parent ,
  menu_order , has_child , level , enable  , create_date ,created_by , last_update_date ,last_updated_by
) values (
  'mainfun_game' , 'Novel', null ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'main_fun' ) t )
   , 2 , 'N' , 1 , 'Y' , current_date  , 'SYSTEM' , current_date , 'SYSTEM');

  insert into system_menu(
  menu_name ,  menu_desc  , menu_path ,
  menu_parent ,
  menu_order , has_child , level , enable  , create_date ,created_by , last_update_date ,last_updated_by
) values (
  'mainfun_game' , 'Apis', null ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'main_fun' ) t )
   , 3 , 'N' , 1 , 'Y' , current_date  , 'SYSTEM' , current_date , 'SYSTEM');


    insert into system_menu(
  menu_name ,  menu_desc  , menu_path ,
  menu_parent ,
  menu_order , has_child , level , enable  , create_date ,created_by , last_update_date ,last_updated_by
) values (
  'mainfun_game' , 'Tools', null ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'main_fun' ) t )
   , 4 , 'N' , 1 , 'Y' , current_date  , 'SYSTEM' , current_date , 'SYSTEM');


drop table if exists system_user_role;
create table system_user_role (
  user_id bigint not null comment  '用户角色表-用户id',
  role_id bigint not null comment  '用户角色表-角色id',
  lastUpdateDate date comment '更新时间',
  lastUpdatedBy varchar(50) comment '更新者'
);

 insert into system_user_role(
  user_id ,  role_id  , lastUpdateDate ,lastUpdatedBy
) values (
    ( select t.user_id from ( select user_id from system_user where login_name = 'admin' ) t ),
    ( select t.role_id from ( select role_id from system_role where role_name = 'administrator' ) t ),
     current_date , 'SYSTEM'
   );



drop table if exists system_role_menu;
create table system_role_menu (
  role_id bigint not null comment  '角色菜单表-角色id',
  menu_id bigint not null comment  '角色菜单表-菜单id',
  lastUpdateDate date comment '更新时间',
  lastUpdatedBy varchar(50) comment '更新者'
);


 insert into system_role_menu(
  role_id ,  menu_id  , lastUpdateDate ,lastUpdatedBy
) values (
    ( select t.role_id from ( select role_id from system_role where role_name = 'administrator' ) t ),
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'sys_fun' ) t ),
     current_date , 'SYSTEM'
   );

