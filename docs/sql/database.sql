drop database if exists db_pwdmng;
create database db_pwdmng;


use db_pwdmng;
show variables like '%time_zone%';
set global time_zone='+8:00';

drop table if exists system_user;
create table system_user (
  user_id bigint(20) not null auto_increment primary key comment  '用户表主键-自增',
  user_info bigint(20) comment '用户信息表外键',
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
  role_id bigint(20) not null  auto_increment primary key comment  '角色表主键-自增',
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
  menu_id bigint(20) not null  auto_increment primary key comment  '菜单表主键-自增',
  info_id bigint(20) not null comment  '菜单信息表-外键',
  menu_name varchar(50) not null comment '菜单名',
  menu_desc varchar(200) comment '菜单描述',
  menu_path varchar(200) comment '菜单链接',
  menu_parent bigint(20) comment '父级菜单主键',
  menu_order int default 1 comment '同级别的序列',
  has_child char(1) not null comment '是否有子级菜单， Y有， N无',
  level int not null comment '所在层级 ，-1 为最底层',
  enable char(1) not null default 'Y' comment '是否启用， Y启用 ，N禁用',
  create_date datetime comment '创建日期',
  created_by varchar(50) comment '创建者',
  last_update_date datetime comment '更新时间',
  last_updated_by varchar(50) comment '更新者'
);

insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'root', null , 'Root Menu', null , null , 1 , 'Y' , -1 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');

insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'sys_fun' , null , 'System Function', null ,
  ( select t.menu_id from ( select menu_id from system_menu where menu_parent is null )t )
   , 1  , 'Y' , 0 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');

insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'main_fun' , null, 'Main Function', null ,
     ( select t.menu_id from ( select menu_id from system_menu where menu_parent is null )t )
   , 2 , 'Y' , 0 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');


   insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'logs', null , 'Logs', null ,
     ( select t.menu_id from ( select menu_id from system_menu where menu_parent is null )t )
   , 3 , 'N' , 0 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');

insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'manager_user' , null , 'User Manager', '/sys/manager/user' ,
   ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'sys_fun' )t )
   , 1 , 'N' , 1 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');

insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'manager_role', null , 'Role Manager', '/sys/manager/role' ,
   ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'sys_fun' )t )
   , 2 , 'N' , 1 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');

insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'manager_menu' , null , 'Menu Manager', '/sys/manager/menu' ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'sys_fun' )t )
   , 3 , 'N' , 1 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');

   insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'main_fun_game', null , 'Game', null ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'main_fun' ) t )
   , 1 , 'N' , 1 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');

  insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'game_tic_tac_toe' , null, 'Tic Tac Toe', '/fun/game/tictactoe' ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'main_fun_game' ) t )
   , 1 , 'N' , 2 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');

    insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'game_flight_chess', null , 'Flight Chess', '/fun/game/flightchess' ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'main_fun_game' ) t )
   , 2 , 'N' , 2 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');

       insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'game_doushouqi' , null, 'DouShouQi', '/fun/game/doushouqi' ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'main_fun_game' ) t )
   , 3 , 'N' , 2 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');

  insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'main_fun_novel', null , 'Novel', null ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'main_fun' ) t )
   , 2 , 'N' , 1 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');

  insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'main_fun_apis' , null, 'Apis', null ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'main_fun' ) t )
   , 3 , 'N' , 1 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');


    insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'main_fun_tools', null , 'Tools', null ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'main_fun' ) t )
   , 4 , 'N' , 1 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');

    insert into system_menu(
  menu_name , info_id, menu_desc  , menu_path ,
  menu_parent , menu_order , has_child , level , enable  ,
  create_date ,created_by , last_update_date ,last_updated_by
) values (
  'tools_lottery' , null, 'Lottery', '/fun/tools/lottery' ,
    ( select t.menu_id from ( select menu_id from system_menu where menu_name = 'main_fun_tools' ) t )
   , 1 , 'N' , 2 , 'Y' , now()  , 'SYSTEM' , now() , 'SYSTEM');



DROP TABLE IF EXISTS `system_menu_info`;
CREATE TABLE `system_menu_info`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单信息表主键',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单表外键',
  `menu_name_cn` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '菜单中文名',
  `menu_name_en` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '菜单英文名',
  `created_by` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '添加人',
  `create_date` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `last_updated_by` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '修改人',
  `last_update_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;


-- Table structure for system_menu_info
-- ----------------------------
DROP TABLE IF EXISTS `system_menu_info`;
CREATE TABLE `system_menu_info`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单信息表主键',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单表主键',
  `menu_name_cn` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '菜单中文名',
  `menu_name_en` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '菜单英文名',
  `created_by` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '添加人',
  `create_date` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `last_updated_by` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '修改人',
  `last_update_date` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of system_menu_info
-- ----------------------------
INSERT INTO `system_menu_info` VALUES (1, 1, '根目录', 'Root', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (2, 2, '系统功能', 'System Function', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (3, 3, '主功能', 'Main Function', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (4, 4, '后台日志', 'Logs', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (5, 5, '用户管理', 'User Manage', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (6, 6, '角色管理', 'Role Manage', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (7, 7, '菜单管理', 'Menu Manage', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (8, 8, '游戏', 'Game', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (9, 9, '井字棋', 'Tic Tac Toe', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (10, 10, '飞行棋', 'Flight Chess', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (11, 11, '斗兽棋', 'Dou Shou Qi', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (12, 12, '小说', 'Novel', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (13, 13, 'API工具', 'Api Tools', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (14, 14, '工具', 'Tools', 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO `system_menu_info` VALUES (15, 15, '彩票模拟系统', 'Lottery', 'SYSTEM', now(), 'SYSTEM', now());

drop table if exists system_user_role;
create table system_user_role (
  user_id bigint(20) not null comment  '用户角色表-用户id',
  role_id bigint(20) not null comment  '用户角色表-角色id',
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
  role_id bigint(20) not null comment  '角色菜单表-角色id',
  menu_id bigint(20) not null comment  '角色菜单表-菜单id',
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


