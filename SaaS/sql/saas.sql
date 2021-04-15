/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : saas

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 25/02/2021 15:21:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ft_user_account
-- ----------------------------
DROP TABLE IF EXISTS `ft_user_account`;
CREATE TABLE `ft_user_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(8) DEFAULT NULL COMMENT '创建人ID',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标记',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态标记',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(8) DEFAULT NULL COMMENT '更新人ID',
  `saas_id` bigint(8) DEFAULT NULL COMMENT 'SAAS账号',
  `bind_flag` bit(1) NOT NULL COMMENT '绑定标记',
  `birthday` datetime(6) DEFAULT NULL COMMENT '生日',
  `channel_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '渠道编码',
  `email` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `invite` bigint(20) DEFAULT NULL COMMENT '邀请人',
  `is_lock_invite` int(1) NOT NULL DEFAULT '0' COMMENT '是否锁定邀请人',
  `is_real_name` int(11) DEFAULT NULL COMMENT '是否实名',
  `last_device_account_id` bigint(20) DEFAULT NULL COMMENT '上次登陆账号ID',
  `last_device_id` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '上次设备码',
  `last_device_type` int(2) DEFAULT NULL COMMENT '上次设备类型',
  `last_login_time` datetime(6) DEFAULT NULL COMMENT '上次登陆时间',
  `last_sns_account_id` bigint(20) DEFAULT NULL COMMENT '上次社交账号ID',
  `last_sns_type` int(2) DEFAULT NULL COMMENT '上次登陆社交账号类型',
  `mobile` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号码',
  `real_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '实名',
  `real_name_time` datetime(6) DEFAULT NULL COMMENT '实名时间',
  `region` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地区',
  `register_device_account_id` bigint(20) DEFAULT NULL COMMENT '注册时设备账号ID',
  `register_device_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '注册时设备ID',
  `scene_no` int(11) DEFAULT NULL COMMENT '场景ID',
  `sex` int(1) NOT NULL COMMENT '性别',
  `user_desc` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `user_rank` int(2) NOT NULL DEFAULT '0' COMMENT '会员等级',
  `user_type` int(1) DEFAULT NULL COMMENT '用户类型（导入还是真实）',
  `wx_number` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信号',
  `wx_unionid` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信UnionId',
  `city` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '城市',
  `country` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国家',
  `head_img_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `language` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '语言',
  `lock_invite` bit(1) NOT NULL COMMENT '是否锁定邀请人',
  `nickname` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `province` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '省份',
  `subscribe` bit(1) DEFAULT NULL COMMENT '是否订阅',
  `subscribe_time` bigint(20) DEFAULT NULL COMMENT '订阅时间',
  `union_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '唯一ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='前端用户表';

-- ----------------------------
-- Records of ft_user_account
-- ----------------------------
BEGIN;
INSERT INTO `ft_user_account` VALUES (5, 'front/前端用户', '2021-01-28 11:29:59.982000', 0, 0, 1, 'front/前端用户', '2021-01-28 14:04:19.930000', 0, 1, b'1', NULL, '11', NULL, NULL, 0, 0, NULL, 'A11112sssaaaadasdsd', NULL, '2021-01-28 14:04:19.907000', 1, 4, NULL, NULL, NULL, NULL, NULL, 'W11112sssaaaadasdsd', NULL, 0, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, b'0', NULL, 'assdsdsd');
COMMIT;

-- ----------------------------
-- Table structure for ft_user_device
-- ----------------------------
DROP TABLE IF EXISTS `ft_user_device`;
CREATE TABLE `ft_user_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(8) DEFAULT NULL COMMENT '创建人ID',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标记',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态标记',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(8) DEFAULT NULL COMMENT '更新人ID',
  `saas_id` bigint(8) DEFAULT NULL COMMENT 'SAASID',
  `certificates` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '凭证',
  `device_id` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '设备ID',
  `device_type` int(2) DEFAULT NULL COMMENT '设备类型',
  `device_version` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '设备型号',
  `idfa` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '苹果手机IDFA',
  `last_login_ip` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '上次登陆IP',
  `last_login_time` datetime(6) DEFAULT NULL COMMENT '上次登陆时间',
  `login_ip` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登陆IP',
  `login_time` datetime(6) DEFAULT NULL COMMENT '登陆时间',
  `push_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '极光push使用的ID',
  `user_account_id` bigint(20) DEFAULT NULL COMMENT '用户账号ID',
  `user_agent` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '浏览器信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户设备信息';

-- ----------------------------
-- Records of ft_user_device
-- ----------------------------
BEGIN;
INSERT INTO `ft_user_device` VALUES (16, 'front/前端用户', '2021-01-28 12:12:40.530000', 0, 0, 0, 'front/前端用户', '2021-01-28 12:12:40.731000', 0, 1, 'xubvbMsF', NULL, 0, NULL, NULL, '127.0.0.1', '2021-01-28 12:12:39.728000', NULL, NULL, NULL, 5, NULL);
INSERT INTO `ft_user_device` VALUES (17, 'front/前端用户', '2021-01-28 12:12:42.835000', 0, 0, 0, 'front/前端用户', '2021-01-28 12:12:42.847000', 0, 1, 'zcWYchQc', NULL, 0, NULL, NULL, '127.0.0.1', '2021-01-28 12:12:42.803000', NULL, NULL, NULL, 5, NULL);
INSERT INTO `ft_user_device` VALUES (18, 'front/前端用户', '2021-01-28 12:12:44.899000', 0, 0, 0, 'front/前端用户', '2021-01-28 12:12:44.906000', 0, 1, 'TuTYGCVV', NULL, 0, NULL, NULL, '127.0.0.1', '2021-01-28 12:12:44.876000', NULL, NULL, NULL, 5, NULL);
INSERT INTO `ft_user_device` VALUES (19, 'front/前端用户', '2021-01-28 12:12:46.946000', 0, 0, 0, 'front/前端用户', '2021-01-28 12:12:46.953000', 0, 1, 'BdGmQDJY', NULL, 0, NULL, NULL, '127.0.0.1', '2021-01-28 12:12:46.928000', NULL, NULL, NULL, 5, NULL);
INSERT INTO `ft_user_device` VALUES (20, 'front/前端用户', '2021-01-28 12:26:38.649000', 0, 0, 0, 'front/前端用户', '2021-01-28 14:04:19.949000', 0, 1, 'pEYzlHCn', 'A11112sssaaaadasdsd', 0, NULL, NULL, '127.0.0.1', '2021-01-28 14:04:19.907000', '127.0.0.1', NULL, NULL, 5, 'Paw/3.1.2 (Macintosh; OS X/10.15.7) GCDHTTPRequest');
COMMIT;

-- ----------------------------
-- Table structure for ft_user_sns
-- ----------------------------
DROP TABLE IF EXISTS `ft_user_sns`;
CREATE TABLE `ft_user_sns` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(8) DEFAULT NULL COMMENT '创建人ID',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标记',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态标记',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(8) DEFAULT NULL COMMENT '更新人ID',
  `saas_id` bigint(8) DEFAULT NULL COMMENT 'SAASID',
  `auth_flag` bit(1) NOT NULL COMMENT '是否授权',
  `icon_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `openid` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'OPENID',
  `sns_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '社交账号名称',
  `sns_type` int(2) DEFAULT NULL COMMENT '社交账号类型',
  `subscribe` bit(1) NOT NULL COMMENT '是否关注',
  `union_id` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'UNIONID',
  `user_account_id` bigint(20) DEFAULT NULL COMMENT '用户账号ID',
  `subscribe_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='社交账号表';

-- ----------------------------
-- Records of ft_user_sns
-- ----------------------------
BEGIN;
INSERT INTO `ft_user_sns` VALUES (1, 'front/前端用户', '2021-01-28 11:30:00.237000', 0, 0, 1, 'front/前端用户', '2021-01-28 11:30:00.237000', 0, 1, b'0', NULL, '11112sssaaaadasdsd', NULL, 4, b'0', 'assdsdsd', 5, NULL);
COMMIT;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(8) DEFAULT NULL COMMENT '创建者ID',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_user_id` int(8) DEFAULT NULL COMMENT '更新者ID',
  `business_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务名称',
  `class_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类名',
  `function_author` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '功能作者',
  `function_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '功能名称',
  `gen_path` varchar(200) COLLATE utf8mb4_bin DEFAULT '' COMMENT '生成路径不填默认项目路径',
  `gen_type` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '生成代码方式0zip压缩包 1自定义路径',
  `module_name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '生成模块名',
  `options` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '其它生成选项',
  `package_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '包名',
  `parent_menu_id` int(8) DEFAULT NULL COMMENT '父菜单ID',
  `parent_menu_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '父菜单名称',
  `table_comment` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表备注',
  `table_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '表名',
  `tpl_category` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '使用的模板crud单表操作 tree树表操作',
  `tree_code` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '树编码字段',
  `tree_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '树名称',
  `tree_parent_code` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '树父编码字段',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者名称',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='代码生成业务表';

-- ----------------------------
-- Records of gen_table
-- ----------------------------
BEGIN;
INSERT INTO `gen_table` VALUES (8, '2021-01-05 00:00:00.000000', 1, 0, 0, '2021-01-06 13:58:19.701000', 1, 'dept', 'SysDept', 'bruce', '部门', '/', '0', 'system', '{\"parentMenuId\":\"1\"}', 'com.bruce.system', 1, NULL, '部门表', 'sys_dept', 'crud', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `gen_table` VALUES (11, '2021-01-07 00:00:00.000000', 1, 0, 0, '2021-01-07 15:11:33.660000', 1, 'category', 'SysCategory', 'bruce', '系统目录', NULL, '0', 'system', '{\"treeCode\":\"id\",\"treeName\":\"name\",\"treeParentCode\":\"parent_id\",\"parentMenuId\":\"1\"}', 'com.saas.system', 1, NULL, '系统目录表', 'sys_category', 'tree', 'id', 'name', 'parent_id', NULL, NULL);
INSERT INTO `gen_table` VALUES (12, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:58.996000', 1, 'config', 'SysConfig', 'bruce', '参数配置', NULL, '0', 'system', '{}', 'com.saas.system', NULL, NULL, '参数配置表', 'sys_config', 'crud', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `gen_table` VALUES (13, '2021-01-08 12:29:54.839000', 1, 0, 0, '2021-01-08 12:29:54.839000', 1, 'log', 'SysOperLog', 'bruce', '操作日志记录', NULL, NULL, 'system', NULL, 'com.saas.system', NULL, NULL, '操作日志记录', 'sys_oper_log', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `gen_table` VALUES (14, '2021-01-15 11:22:50.266000', 1, 0, 0, '2021-01-15 11:22:50.266000', 1, 'logininfor', 'SysLogininfor', 'bruce', '系统访问记录', NULL, NULL, 'system', NULL, 'com.saas.system', NULL, NULL, '系统访问记录', 'sys_logininfor', NULL, NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (15, '2021-01-15 11:22:50.344000', 1, 0, 0, '2021-01-15 11:22:50.344000', 1, 'menu', 'SysMenu', 'bruce', '菜单权限', NULL, NULL, 'system', NULL, 'com.saas.system', NULL, NULL, '菜单权限表', 'sys_menu', NULL, NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (16, '2021-01-15 11:22:50.415000', 1, 0, 0, '2021-01-15 11:22:50.415000', 1, 'privilege', 'SysPrivilege', 'bruce', '权限', NULL, NULL, 'system', NULL, 'com.saas.system', NULL, NULL, '权限表', 'sys_privilege', NULL, NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (17, '2021-01-15 11:22:50.450000', 1, 0, 0, '2021-01-15 11:22:50.450000', 1, 'role', 'SysRole', 'bruce', '角色', NULL, NULL, 'system', NULL, 'com.saas.system', NULL, NULL, '角色表', 'sys_role', NULL, NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (19, '2021-01-15 11:22:50.491000', 1, 0, 0, '2021-01-15 11:22:50.491000', 1, 'user', 'SysUser', 'bruce', '用户', NULL, NULL, 'system', NULL, 'com.saas.system', NULL, NULL, '用户表', 'sys_user', NULL, NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (21, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.091000', 1, 'account', 'SysSaasAccount', 'bruce', 'SAAS账号', NULL, NULL, 'system', '{\"parentMenuId\":1}', 'com.saas.system', 1, NULL, 'SAAS账号', 'sys_saas_account', 'crud', NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (22, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.148000', 1, 'account', 'WxAuthAccount', 'bruce', '微信授权账号信息', NULL, NULL, 'system', '{\"parentMenuId\":1065}', 'com.saas.system', 1065, NULL, '微信授权账号信息', 'wx_auth_account', 'crud', NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (23, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.696000', 1, 'account', 'FtUserAccount', 'bruce', '前端用户', NULL, NULL, 'system', '{\"parentMenuId\":1067}', 'com.saas.system', 1067, NULL, '会员信息', 'ft_user_account', 'crud', NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (24, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.008000', 1, 'device', 'FtUserDevice', 'bruce', '用户设备信息', NULL, NULL, 'system', '{\"parentMenuId\":1067}', 'com.saas.system', 1067, NULL, '设备信息', 'ft_user_device', 'crud', NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (25, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.236000', 1, 'sns', 'FtUserSns', 'bruce', '社交账号', NULL, NULL, 'system', '{\"parentMenuId\":1067}', 'com.saas.system', 1067, NULL, '社交账号', 'ft_user_sns', 'crud', NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (26, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.666000', 1, 'article', 'WxArticle', 'bruce', '图文素材', NULL, NULL, 'wx', '{\"parentMenuId\":1065}', 'com.saas.wx', 1065, NULL, '图文素材', 'wx_article', 'crud', NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (27, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.821000', 1, 'media', 'WxMedia', 'bruce', '媒体素材', NULL, NULL, 'wx', '{\"parentMenuId\":1065}', 'com.saas.wx', 1065, NULL, '媒体素材', 'wx_media', 'crud', NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (28, '2021-01-29 12:59:59.970000', 1, 0, 0, '2021-01-29 12:59:59.970000', 1, 'menu', 'WxMenu', 'bruce', '微信菜单', NULL, NULL, 'wx', NULL, 'com.saas.wx', NULL, NULL, '微信菜单', 'wx_menu', NULL, NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (29, '2021-01-29 13:00:00.080000', 1, 0, 0, '2021-01-29 13:00:00.080000', 1, 'reply', 'WxReply', 'bruce', '微信回复规则', NULL, NULL, 'wx', NULL, 'com.saas.wx', NULL, NULL, '微信回复规则', 'wx_reply', NULL, NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table` VALUES (30, '2021-01-29 13:00:00.163000', 1, 0, 0, '2021-01-29 13:00:00.163000', 1, 'template', 'WxTemplate', 'bruce', '微信模版消息', NULL, NULL, 'wx', NULL, 'com.saas.wx', NULL, NULL, '微信模版消息', 'wx_template', NULL, NULL, NULL, NULL, 'admin/管理员', 'admin/管理员');
COMMIT;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(8) DEFAULT NULL COMMENT '创建者ID',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识',
  `status_flag` int(11) DEFAULT NULL COMMENT '状态',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_user_id` int(8) DEFAULT NULL COMMENT '更新者ID',
  `column_comment` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字段备注',
  `column_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字段名称',
  `column_type` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字段类型',
  `dict_type` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '字典类型',
  `html_type` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `is_edit` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否可编辑',
  `is_increment` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否自增',
  `is_insert` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否可新增',
  `is_list` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否列表显示',
  `is_pk` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否主键',
  `is_query` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否可查询',
  `is_required` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '是否必填',
  `java_field` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'java字段名',
  `java_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'java类型',
  `query_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '查询方式（等于、不等于、大于、小于、范围',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `table_id` int(8) DEFAULT NULL COMMENT '归属表ID',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者名称',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=400 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='代码生成业务表字段';

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
BEGIN;
INSERT INTO `gen_table_column` VALUES (43, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.750000', 1, '主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', NULL, NULL, '1', NULL, NULL, 'id', 'Long', NULL, 1, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (44, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.746000', 1, '创建时间', 'create_time', 'datetime(6)', NULL, 'datetime', NULL, '0', NULL, '1', '0', '1', NULL, 'createTime', 'Date', 'BETWEEN', 2, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (45, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.742000', 1, '创建人', 'create_user_id', 'bigint(20)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'createUserId', 'Long', NULL, 3, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (46, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.739000', 1, '删除标志', 'del_flag', 'int(11)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'delFlag', 'Long', NULL, 4, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (47, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.736000', 1, '状态标志', 'status_flag', 'int(11)', 'statusOptions', 'select', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Long', 'EQ', 5, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (48, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.732000', 1, '更新时间', 'update_time', 'datetime(6)', NULL, 'datetime', NULL, '0', NULL, NULL, '0', NULL, NULL, 'updateTime', 'Date', NULL, 6, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (49, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.728000', 1, '更新人', 'update_user_id', 'bigint(20)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'updateUserId', 'Long', NULL, 7, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (50, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.725000', 1, '祖级列表', 'ancestors', 'varchar(255)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'ancestors', 'String', NULL, 8, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (51, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.722000', 1, '部门名称', 'dept_name', 'varchar(30)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'deptName', 'String', 'LIKE', 9, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (52, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.719000', 1, '邮箱', 'email', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'email', 'String', 'LIKE', 10, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (53, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.716000', 1, '负责人', 'leader', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'leader', 'String', 'LIKE', 11, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (54, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.712000', 1, '排序', 'order_num', 'varchar(255)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'orderNum', 'String', NULL, 12, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (55, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.709000', 1, '父ID', 'parent_id', 'bigint(20)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'parentId', 'Long', NULL, 13, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (56, '2021-01-05 16:33:51.000000', 1, 0, 0, '2021-01-06 13:58:19.706000', 1, '手机号码', 'phone', 'varchar(11)', NULL, 'input', '1', '0', '1', '1', '0', '1', '1', 'phone', 'String', 'LIKE', 14, 8, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (77, '2021-01-07 14:39:22.000000', 1, 0, 0, '2021-01-07 15:11:33.694000', 1, '主键', 'id', 'int(5)', NULL, 'input', NULL, '1', '1', '1', '1', NULL, NULL, 'id', 'Integer', 'EQ', 1, 11, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (78, '2021-01-07 14:39:22.000000', 1, 0, 0, '2021-01-07 15:11:33.692000', 1, '名称', 'name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', '1', 'name', 'String', 'LIKE', 2, 11, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (79, '2021-01-07 14:39:22.000000', 1, 0, 0, '2021-01-07 15:11:33.689000', 1, '父目录ID', 'parent_id', 'int(5)', NULL, 'select', '1', '0', '1', '1', '0', '1', NULL, 'parentId', 'Integer', 'EQ', 3, 11, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (80, '2021-01-07 14:39:22.000000', 1, 0, 0, '2021-01-07 15:11:33.687000', 1, '创建时间', 'create_time', 'datetime', NULL, 'datetime', NULL, '0', NULL, '1', '0', '1', NULL, 'createTime', 'Date', 'BETWEEN', 4, 11, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (81, '2021-01-07 14:39:22.000000', 1, 0, 0, '2021-01-07 15:11:33.685000', 1, '创建人', 'create_user_id', 'int(6)', NULL, 'input', NULL, '0', NULL, '1', '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 5, 11, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (82, '2021-01-07 14:39:22.000000', 1, 0, 0, '2021-01-07 15:11:33.682000', 1, '删除标识', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 6, 11, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (83, '2021-01-07 14:39:22.000000', 1, 0, 0, '2021-01-07 15:11:33.679000', 1, '状态标识', 'status_flag', 'int(1)', 'statusOptions', 'select', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 7, 11, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (84, '2021-01-07 14:39:22.000000', 1, 0, 0, '2021-01-07 15:11:33.674000', 1, '修改时间', 'update_time', 'datetime', NULL, 'datetime', NULL, '0', NULL, '1', '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 8, 11, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (85, '2021-01-07 14:39:22.000000', 1, 0, 0, '2021-01-07 15:11:33.672000', 1, '修改人', 'update_user_id', 'int(6)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 9, 11, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (86, '2021-01-07 14:39:22.000000', 1, 0, 0, '2021-01-07 15:11:33.670000', 1, '祖级列表', 'ancestors', 'varchar(255)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'ancestors', 'String', 'EQ', 10, 11, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (87, '2021-01-07 14:39:22.000000', 1, 0, 0, '2021-01-07 15:11:33.667000', 1, '排序', 'order_num', 'int(5)', NULL, 'input', '1', '0', '1', '1', '0', NULL, NULL, 'orderNum', 'Integer', 'EQ', 11, 11, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (88, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:59.077000', 1, '参数主键', 'id', 'int(5)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Integer', 'EQ', 1, 12, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (89, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:59.072000', 1, '参数名称', 'config_name', 'varchar(100)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'configName', 'String', 'LIKE', 2, 12, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (90, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:59.068000', 1, '参数键名', 'config_key', 'varchar(100)', NULL, 'fileUpload', '1', '0', '1', '1', '0', '1', NULL, 'configKey', 'String', 'EQ', 3, 12, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (91, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:59.055000', 1, '参数键值', 'config_value', 'varchar(500)', NULL, 'imageUpload', '1', '0', '1', '1', '0', NULL, NULL, 'configValue', 'String', 'EQ', 4, 12, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (92, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:59.052000', 1, '系统内置（Y是 N否）', 'config_type', 'bit(1)', NULL, 'select', '1', '0', '1', '1', '0', '1', NULL, 'configType', 'Integer', 'EQ', 5, 12, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (93, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:59.032000', 1, '创建者', 'create_user_id', 'int(6)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 6, 12, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (94, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:59.025000', 1, '创建时间', 'create_time', 'datetime', NULL, 'datetime', NULL, '0', NULL, NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 7, 12, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (95, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:59.021000', 1, '更新者', 'update_user_id', 'int(6)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 8, 12, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (96, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:59.017000', 1, '更新时间', 'update_time', 'datetime', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 9, 12, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (97, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:59.012000', 1, '备注', 'remark', 'varchar(500)', NULL, 'textarea', '1', '0', '1', '1', '0', NULL, NULL, 'remark', 'String', 'EQ', 10, 12, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (98, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:59.006000', 1, '软删除标志', 'del_flag', 'int(1)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 11, 12, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (99, '2021-01-07 17:20:07.000000', 1, 0, 0, '2021-01-07 17:24:59.001000', 1, '状态', 'status_flag', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 12, 12, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (100, '2021-01-08 12:29:55.053000', 1, 0, 0, '2021-01-08 12:29:55.053000', 1, '日志主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (101, '2021-01-08 12:29:55.060000', 1, 0, 0, '2021-01-08 12:29:55.060000', 1, '模块标题', 'title', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'title', 'String', 'EQ', 2, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (102, '2021-01-08 12:29:55.064000', 1, 0, 0, '2021-01-08 12:29:55.064000', 1, '业务类型（0其它 1新增 2修改 3删除）', 'business_type', 'int(2)', NULL, 'select', '1', '0', '1', '1', '0', '1', NULL, 'businessType', 'Integer', 'EQ', 3, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (103, '2021-01-08 12:29:55.068000', 1, 0, 0, '2021-01-08 12:29:55.068000', 1, '方法名称', 'method', 'varchar(100)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'method', 'String', 'EQ', 4, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (104, '2021-01-08 12:29:55.071000', 1, 0, 0, '2021-01-08 12:29:55.071000', 1, '请求方式', 'request_method', 'varchar(10)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'requestMethod', 'String', 'EQ', 5, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (105, '2021-01-08 12:29:55.075000', 1, 0, 0, '2021-01-08 12:29:55.075000', 1, '操作类别（0其它 1后台用户 2手机端用户）', 'operator_type', 'int(1)', NULL, 'select', '1', '0', '1', '1', '0', '1', NULL, 'operatorType', 'Integer', 'EQ', 6, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (106, '2021-01-08 12:29:55.078000', 1, 0, 0, '2021-01-08 12:29:55.078000', 1, '操作人员', 'oper_name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'operName', 'String', 'LIKE', 7, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (107, '2021-01-08 12:29:55.082000', 1, 0, 0, '2021-01-08 12:29:55.082000', 1, '部门名称', 'dept_name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'deptName', 'String', 'LIKE', 8, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (108, '2021-01-08 12:29:55.085000', 1, 0, 0, '2021-01-08 12:29:55.085000', 1, '请求URL', 'oper_url', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'operUrl', 'String', 'EQ', 9, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (109, '2021-01-08 12:29:55.088000', 1, 0, 0, '2021-01-08 12:29:55.088000', 1, '主机地址', 'oper_ip', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'operIp', 'String', 'EQ', 10, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (110, '2021-01-08 12:29:55.090000', 1, 0, 0, '2021-01-08 12:29:55.090000', 1, '操作地点', 'oper_location', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'operLocation', 'String', 'EQ', 11, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (111, '2021-01-08 12:29:55.095000', 1, 0, 0, '2021-01-08 12:29:55.095000', 1, '请求参数', 'oper_param', 'varchar(2000)', NULL, 'textarea', '1', '0', '1', '1', '0', '1', NULL, 'operParam', 'String', 'EQ', 12, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (112, '2021-01-08 12:29:55.098000', 1, 0, 0, '2021-01-08 12:29:55.098000', 1, '返回参数', 'json_result', 'varchar(2000)', NULL, 'textarea', '1', '0', '1', '1', '0', '1', NULL, 'jsonResult', 'String', 'EQ', 13, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (113, '2021-01-08 12:29:55.101000', 1, 0, 0, '2021-01-08 12:29:55.101000', 1, '操作状态（0正常 1异常）', 'status', 'int(1)', NULL, 'radio', '1', '0', '1', '1', '0', '1', NULL, 'status', 'Integer', 'EQ', 14, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (114, '2021-01-08 12:29:55.103000', 1, 0, 0, '2021-01-08 12:29:55.103000', 1, '错误消息', 'error_msg', 'varchar(2000)', NULL, 'textarea', '1', '0', '1', '1', '0', '1', NULL, 'errorMsg', 'String', 'EQ', 15, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (115, '2021-01-08 12:29:55.107000', 1, 0, 0, '2021-01-08 12:29:55.107000', 1, '操作时间', 'oper_time', 'datetime', NULL, 'datetime', '1', '0', '1', '1', '0', '1', NULL, 'operTime', 'Date', 'EQ', 16, 13, NULL, NULL);
INSERT INTO `gen_table_column` VALUES (116, '2021-01-15 11:22:50.311000', 1, 0, 0, '2021-01-15 11:22:50.311000', 1, '访问ID', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 14, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (117, '2021-01-15 11:22:50.318000', 1, 0, 0, '2021-01-15 11:22:50.318000', 1, '用户账号', 'user_name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'userName', 'String', 'LIKE', 2, 14, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (118, '2021-01-15 11:22:50.320000', 1, 0, 0, '2021-01-15 11:22:50.320000', 1, '登录IP地址', 'ipaddr', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'ipaddr', 'String', 'EQ', 3, 14, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (119, '2021-01-15 11:22:50.323000', 1, 0, 0, '2021-01-15 11:22:50.323000', 1, '登录地点', 'login_location', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'loginLocation', 'String', 'EQ', 4, 14, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (120, '2021-01-15 11:22:50.326000', 1, 0, 0, '2021-01-15 11:22:50.326000', 1, '浏览器类型', 'browser', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'browser', 'String', 'EQ', 5, 14, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (121, '2021-01-15 11:22:50.329000', 1, 0, 0, '2021-01-15 11:22:50.329000', 1, '操作系统', 'os', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'os', 'String', 'EQ', 6, 14, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (122, '2021-01-15 11:22:50.332000', 1, 0, 0, '2021-01-15 11:22:50.332000', 1, '登录状态（0成功 1失败）', 'status', 'char(1)', NULL, 'radio', '1', '0', '1', '1', '0', '1', NULL, 'status', 'String', 'EQ', 7, 14, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (123, '2021-01-15 11:22:50.336000', 1, 0, 0, '2021-01-15 11:22:50.336000', 1, '提示消息', 'msg', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'msg', 'String', 'EQ', 8, 14, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (124, '2021-01-15 11:22:50.339000', 1, 0, 0, '2021-01-15 11:22:50.339000', 1, '访问时间', 'login_time', 'datetime', NULL, 'datetime', '1', '0', '1', '1', '0', '1', NULL, 'loginTime', 'Date', 'EQ', 9, 14, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (125, '2021-01-15 11:22:50.356000', 1, 0, 0, '2021-01-15 11:22:50.356000', 1, '', 'id', 'int(8)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Integer', 'EQ', 1, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (126, '2021-01-15 11:22:50.358000', 1, 0, 0, '2021-01-15 11:22:50.358000', 1, '菜单名称', 'menu_name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'menuName', 'String', 'LIKE', 2, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (127, '2021-01-15 11:22:50.361000', 1, 0, 0, '2021-01-15 11:22:50.361000', 1, '父菜单ID', 'parent_id', 'int(8)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'parentId', 'Integer', 'EQ', 3, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (128, '2021-01-15 11:22:50.364000', 1, 0, 0, '2021-01-15 11:22:50.364000', 1, '显示顺序', 'order_num', 'int(4)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'orderNum', 'Integer', 'EQ', 4, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (129, '2021-01-15 11:22:50.368000', 1, 0, 0, '2021-01-15 11:22:50.368000', 1, '路由地址', 'path', 'varchar(200)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'path', 'String', 'EQ', 5, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (130, '2021-01-15 11:22:50.370000', 1, 0, 0, '2021-01-15 11:22:50.370000', 1, '组件路径', 'component', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'component', 'String', 'EQ', 6, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (131, '2021-01-15 11:22:50.373000', 1, 0, 0, '2021-01-15 11:22:50.373000', 1, '是否为外链（1是 0否）', 'frame', 'bit(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', '1', 'frame', 'Integer', 'EQ', 7, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (132, '2021-01-15 11:22:50.378000', 1, 0, 0, '2021-01-15 11:22:50.378000', 1, '是否缓存（1缓存 0不缓存）', 'cache', 'bit(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', '1', 'cache', 'Integer', 'EQ', 8, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (133, '2021-01-15 11:22:50.380000', 1, 0, 0, '2021-01-15 11:22:50.380000', 1, '菜单类型（M目录 C菜单 F按钮）', 'menu_type', 'varchar(255)', NULL, 'select', '1', '0', '1', '1', '0', '1', NULL, 'menuType', 'String', 'EQ', 9, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (134, '2021-01-15 11:22:50.383000', 1, 0, 0, '2021-01-15 11:22:50.383000', 1, '菜单状态（1显示 0隐藏）', 'visible', 'bit(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', '1', 'visible', 'Integer', 'EQ', 10, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (135, '2021-01-15 11:22:50.385000', 1, 0, 0, '2021-01-15 11:22:50.385000', 1, '菜单状态（1正常 -1停用）', 'status_flag', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 11, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (136, '2021-01-15 11:22:50.388000', 1, 0, 0, '2021-01-15 11:22:50.388000', 1, '权限标识', 'perms', 'varchar(100)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'perms', 'String', 'EQ', 12, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (137, '2021-01-15 11:22:50.390000', 1, 0, 0, '2021-01-15 11:22:50.390000', 1, '菜单图标', 'icon', 'varchar(100)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'icon', 'String', 'EQ', 13, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (138, '2021-01-15 11:22:50.393000', 1, 0, 0, '2021-01-15 11:22:50.393000', 1, '', 'create_user_id', 'int(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 14, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (139, '2021-01-15 11:22:50.395000', 1, 0, 0, '2021-01-15 11:22:50.395000', 1, '', 'create_time', 'datetime', NULL, 'datetime', NULL, '0', '1', NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 15, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (140, '2021-01-15 11:22:50.397000', 1, 0, 0, '2021-01-15 11:22:50.397000', 1, '', 'update_user_id', 'int(8)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 16, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (141, '2021-01-15 11:22:50.399000', 1, 0, 0, '2021-01-15 11:22:50.399000', 1, '', 'update_time', 'datetime', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 17, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (142, '2021-01-15 11:22:50.401000', 1, 0, 0, '2021-01-15 11:22:50.401000', 1, '备注', 'remark', 'varchar(500)', NULL, 'textarea', '1', '0', '1', '1', '0', NULL, NULL, 'remark', 'String', 'EQ', 18, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (143, '2021-01-15 11:22:50.405000', 1, 0, 0, '2021-01-15 11:22:50.405000', 1, '删除标志', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, '1', 'delFlag', 'Integer', 'EQ', 19, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (144, '2021-01-15 11:22:50.408000', 1, 0, 0, '2021-01-15 11:22:50.408000', 1, '创建人', 'create_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'createBy', 'String', 'EQ', 20, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (145, '2021-01-15 11:22:50.411000', 1, 0, 0, '2021-01-15 11:22:50.411000', 1, '修改人', 'update_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 21, 15, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (146, '2021-01-15 11:22:50.422000', 1, 0, 0, '2021-01-15 11:22:50.422000', 1, '主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 16, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (147, '2021-01-15 11:22:50.425000', 1, 0, 0, '2021-01-15 11:22:50.425000', 1, '', 'create_time', 'datetime(6)', NULL, 'datetime', NULL, '0', '1', NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 2, 16, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (148, '2021-01-15 11:22:50.427000', 1, 0, 0, '2021-01-15 11:22:50.427000', 1, '', 'create_user_id', 'bigint(20)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'createUserId', 'Long', 'EQ', 3, 16, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (149, '2021-01-15 11:22:50.430000', 1, 0, 0, '2021-01-15 11:22:50.430000', 1, '删除标', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 4, 16, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (150, '2021-01-15 11:22:50.433000', 1, 0, 0, '2021-01-15 11:22:50.433000', 1, '状态', 'status_flag', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 5, 16, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (151, '2021-01-15 11:22:50.436000', 1, 0, 0, '2021-01-15 11:22:50.436000', 1, '', 'update_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 6, 16, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (152, '2021-01-15 11:22:50.438000', 1, 0, 0, '2021-01-15 11:22:50.438000', 1, '', 'update_user_id', 'bigint(20)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Long', 'EQ', 7, 16, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (153, '2021-01-15 11:22:50.441000', 1, 0, 0, '2021-01-15 11:22:50.441000', 1, '名称', 'name', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'name', 'String', 'LIKE', 8, 16, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (154, '2021-01-15 11:22:50.443000', 1, 0, 0, '2021-01-15 11:22:50.443000', 1, '权限字符串用逗号隔开', 'permissions', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'permissions', 'String', 'EQ', 9, 16, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (155, '2021-01-15 11:22:50.446000', 1, 0, 0, '2021-01-15 11:22:50.446000', 1, '', 'create_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'createBy', 'String', 'EQ', 10, 16, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (156, '2021-01-15 11:22:50.448000', 1, 0, 0, '2021-01-15 11:22:50.448000', 1, '', 'update_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 11, 16, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (157, '2021-01-15 11:22:50.455000', 1, 0, 0, '2021-01-15 11:22:50.455000', 1, '主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 17, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (158, '2021-01-15 11:22:50.457000', 1, 0, 0, '2021-01-15 11:22:50.457000', 1, '', 'create_time', 'datetime(6)', NULL, 'datetime', NULL, '0', '1', NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 2, 17, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (159, '2021-01-15 11:22:50.459000', 1, 0, 0, '2021-01-15 11:22:50.459000', 1, '', 'create_user_id', 'bigint(20)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'createUserId', 'Long', 'EQ', 3, 17, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (160, '2021-01-15 11:22:50.461000', 1, 0, 0, '2021-01-15 11:22:50.461000', 1, '软删除', 'del_flag', 'int(11)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'delFlag', 'Long', 'EQ', 4, 17, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (161, '2021-01-15 11:22:50.463000', 1, 0, 0, '2021-01-15 11:22:50.463000', 1, '状态', 'status_flag', 'int(11)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Long', 'EQ', 5, 17, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (162, '2021-01-15 11:22:50.464000', 1, 0, 0, '2021-01-15 11:22:50.464000', 1, '', 'update_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 6, 17, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (163, '2021-01-15 11:22:50.467000', 1, 0, 0, '2021-01-15 11:22:50.467000', 1, '', 'update_user_id', 'bigint(20)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Long', 'EQ', 7, 17, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (164, '2021-01-15 11:22:50.469000', 1, 0, 0, '2021-01-15 11:22:50.469000', 1, '名称', 'name', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'name', 'String', 'LIKE', 8, 17, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (165, '2021-01-15 11:22:50.471000', 1, 0, 0, '2021-01-15 11:22:50.471000', 1, '显示名称', 'show_name', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'showName', 'String', 'LIKE', 9, 17, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (166, '2021-01-15 11:22:50.473000', 1, 0, 0, '2021-01-15 11:22:50.473000', 1, '排序', 'order_num', 'int(11)', NULL, 'input', '1', '0', '1', '1', '0', '1', '1', 'orderNum', 'Long', 'EQ', 10, 17, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (167, '2021-01-15 11:22:50.475000', 1, 0, 0, '2021-01-15 11:22:50.475000', 1, '', 'create_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'createBy', 'String', 'EQ', 11, 17, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (168, '2021-01-15 11:22:50.476000', 1, 0, 0, '2021-01-15 11:22:50.476000', 1, '', 'update_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 12, 17, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (171, '2021-01-15 11:22:50.499000', 1, 0, 0, '2021-01-15 11:22:50.499000', 1, '主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (172, '2021-01-15 11:22:50.502000', 1, 0, 0, '2021-01-15 11:22:50.502000', 1, '', 'create_time', 'datetime(6)', NULL, 'datetime', NULL, '0', '1', NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 2, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (173, '2021-01-15 11:22:50.504000', 1, 0, 0, '2021-01-15 11:22:50.504000', 1, '', 'create_user_id', 'int(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 3, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (174, '2021-01-15 11:22:50.507000', 1, 0, 0, '2021-01-15 11:22:50.507000', 1, '软删除', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 4, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (175, '2021-01-15 11:22:50.509000', 1, 0, 0, '2021-01-15 11:22:50.509000', 1, '状态', 'status_flag', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 5, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (176, '2021-01-15 11:22:50.511000', 1, 0, 0, '2021-01-15 11:22:50.511000', 1, '', 'update_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 6, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (177, '2021-01-15 11:22:50.514000', 1, 0, 0, '2021-01-15 11:22:50.514000', 1, '', 'update_user_id', 'int(8)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 7, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (178, '2021-01-15 11:22:50.516000', 1, 0, 0, '2021-01-15 11:22:50.516000', 1, '邮箱', 'email', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'email', 'String', 'EQ', 8, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (179, '2021-01-15 11:22:50.518000', 1, 0, 0, '2021-01-15 11:22:50.518000', 1, '登陆时间', 'login_date', 'datetime(6)', NULL, 'datetime', '1', '0', '1', '1', '0', '1', NULL, 'loginDate', 'Date', 'EQ', 9, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (180, '2021-01-15 11:22:50.520000', 1, 0, 0, '2021-01-15 11:22:50.520000', 1, '登陆IP', 'login_ip', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'loginIp', 'String', 'EQ', 10, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (181, '2021-01-15 11:22:50.522000', 1, 0, 0, '2021-01-15 11:22:50.522000', 1, '登陆用户名', 'login_name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'loginName', 'String', 'LIKE', 11, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (182, '2021-01-15 11:22:50.524000', 1, 0, 0, '2021-01-15 11:22:50.524000', 1, '手机号', 'mobile', 'varchar(20)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'mobile', 'String', 'EQ', 12, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (183, '2021-01-15 11:22:50.526000', 1, 0, 0, '2021-01-15 11:22:50.526000', 1, '昵称', 'name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'name', 'String', 'LIKE', 13, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (184, '2021-01-15 11:22:50.529000', 1, 0, 0, '2021-01-15 11:22:50.529000', 1, '密码', 'password', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'password', 'String', 'EQ', 14, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (185, '2021-01-15 11:22:50.531000', 1, 0, 0, '2021-01-15 11:22:50.531000', 1, '', 'salt', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'salt', 'String', 'EQ', 15, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (186, '2021-01-15 11:22:50.533000', 1, 0, 0, '2021-01-15 11:22:50.533000', 1, '部门', 'dept_id', 'bigint(20)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'deptId', 'Long', 'EQ', 16, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (187, '2021-01-15 11:22:50.535000', 1, 0, 0, '2021-01-15 11:22:50.535000', 1, '备注', 'remark', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', NULL, NULL, 'remark', 'String', 'EQ', 17, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (188, '2021-01-15 11:22:50.537000', 1, 0, 0, '2021-01-15 11:22:50.537000', 1, '性别', 'sex', 'int(1)', NULL, 'select', '1', '0', '1', '1', '0', '1', '1', 'sex', 'Integer', 'EQ', 18, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (189, '2021-01-15 11:22:50.539000', 1, 0, 0, '2021-01-15 11:22:50.539000', 1, '', 'create_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'createBy', 'String', 'EQ', 19, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (190, '2021-01-15 11:22:50.541000', 1, 0, 0, '2021-01-15 11:22:50.541000', 1, '', 'update_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 20, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (191, '2021-01-15 11:22:50.543000', 1, 0, 0, '2021-01-15 11:22:50.543000', 1, '头像', 'avatar', 'varchar(500)', NULL, 'textarea', '1', '0', '1', '1', '0', '1', NULL, 'avatar', 'String', 'EQ', 21, 19, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (194, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.155000', 1, 'ID', 'id', 'int(8)', NULL, 'input', NULL, '1', NULL, '1', '1', '1', NULL, 'id', 'Integer', 'EQ', 1, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (195, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.152000', 1, '名称', 'name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'name', 'String', 'LIKE', 2, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (196, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.148000', 1, '公司全称', 'long_name', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'longName', 'String', 'LIKE', 3, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (197, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.145000', 1, '联系电话', 'mobile', 'varchar(20)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'mobile', 'String', 'LIKE', 4, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (198, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.140000', 1, '联系人', 'contact_name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'contactName', 'String', 'LIKE', 5, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (199, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.135000', 1, '创建', 'create_user_id', 'int(8)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 6, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (200, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.132000', 1, '更新', 'update_user_id', 'int(8)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 7, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (201, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.129000', 1, '创建时间', 'create_time', 'datetime', NULL, 'datetime', NULL, '0', NULL, '1', '0', NULL, NULL, 'createTime', 'Date', 'EQ', 8, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (202, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.125000', 1, '更新时间', 'update_time', 'datetime', NULL, 'datetime', NULL, '0', NULL, '1', '0', '1', NULL, 'updateTime', 'Date', 'BETWEEN', 9, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (203, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.122000', 1, '删除', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 10, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (204, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.119000', 1, '状态', 'status_flag', 'int(1)', 'statusOptions', 'select', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 11, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (205, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.116000', 1, '创建人', 'create_by', 'varchar(50)', NULL, 'input', NULL, '0', NULL, '1', '0', NULL, NULL, 'createBy', 'String', 'EQ', 12, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (206, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 15:44:04.110000', 1, '更新人', 'update_by', 'varchar(50)', NULL, 'input', NULL, '0', NULL, '1', '0', '1', NULL, 'updateBy', 'String', 'LIKE', 13, 21, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (207, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.253000', 1, '主键', 'id', 'int(8)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Integer', 'EQ', 1, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (208, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.250000', 1, '类型', 'service_type', 'int(1)', NULL, 'select', '1', '0', '1', '1', '0', '1', '1', 'serviceType', 'Integer', 'EQ', 2, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (209, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.247000', 1, 'APPID', 'authorizer_app_id', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'authorizerAppId', 'String', 'EQ', 3, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (210, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.245000', 1, '归属公司ID', 'saas_id', 'int(8)', NULL, 'input', NULL, '0', NULL, '1', '0', NULL, NULL, 'saasId', 'Integer', 'EQ', 4, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (211, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.241000', 1, 'Token', 'authorizer_fresh_token', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'authorizerFreshToken', 'String', 'EQ', 5, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (212, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.238000', 1, '别名', 'alias', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'alias', 'String', 'EQ', 6, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (213, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.234000', 1, '头像', 'head_img', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'headImg', 'String', 'EQ', 7, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (214, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.230000', 1, '昵称', 'nick_name', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'nickName', 'String', 'LIKE', 8, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (215, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.226000', 1, '公司名称', 'principal_name', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'principalName', 'String', 'LIKE', 9, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (216, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.219000', 1, '二维码', 'qrcode_url', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'qrcodeUrl', 'String', 'EQ', 10, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (217, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.215000', 1, '签名', 'signature', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'signature', 'String', 'EQ', 11, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (218, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.210000', 1, '名称', 'user_name', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'userName', 'String', 'LIKE', 12, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (219, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.205000', 1, '其他信息', 'other_info', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'otherInfo', 'String', 'EQ', 13, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (220, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.202000', 1, '创建人', 'create_user_id', 'int(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 14, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (221, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.197000', 1, '更新人', 'update_user_id', 'int(8)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 15, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (222, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.191000', 1, '', 'create_time', 'datetime', NULL, 'datetime', NULL, '0', '1', NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 16, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (223, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.186000', 1, '', 'update_time', 'datetime', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 17, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (224, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.182000', 1, '', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 18, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (225, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.179000', 1, '', 'status_flag', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 19, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (226, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.174000', 1, '', 'create_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'createBy', 'String', 'EQ', 20, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (227, '2021-01-21 15:40:34.000000', 1, 0, 0, '2021-01-21 16:52:59.162000', 1, '', 'update_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 21, 22, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (228, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.820000', 1, '主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (229, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.817000', 1, '创建人', 'create_by', 'varchar(50)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'createBy', 'String', 'EQ', 2, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (230, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.815000', 1, '创建时间', 'create_time', 'datetime(6)', NULL, 'datetime', NULL, '0', NULL, NULL, '0', '1', NULL, 'createTime', 'Date', 'EQ', 3, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (231, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.811000', 1, '创建人ID', 'create_user_id', 'bigint(8)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 4, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (232, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.809000', 1, '删除标记', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 5, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (233, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.806000', 1, '状态标记', 'status_flag', 'int(1)', 'statusOptions', 'select', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 6, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (234, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.803000', 1, '更新人', 'update_by', 'varchar(50)', NULL, 'input', NULL, '0', NULL, '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 7, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (235, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.800000', 1, '更新时间', 'update_time', 'datetime(6)', NULL, 'datetime', NULL, '0', NULL, '1', '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 8, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (236, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.797000', 1, '更新人ID', 'update_user_id', 'bigint(8)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 9, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (237, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.795000', 1, 'SAAS账号', 'saas_id', 'bigint(8)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'saasId', 'Integer', 'EQ', 10, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (238, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.792000', 1, '绑定标记', 'bind_flag', 'bit(1)', 'yesOrNoOptions', 'select', '1', '0', NULL, '1', '0', '1', '1', 'bindFlag', 'Integer', 'EQ', 11, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (239, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.789000', 1, '生日', 'birthday', 'datetime(6)', NULL, 'datetime', '1', '0', NULL, '1', '0', NULL, NULL, 'birthday', 'Date', 'EQ', 12, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (240, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.786000', 1, '渠道编码', 'channel_code', 'varchar(50)', NULL, 'input', '1', '0', NULL, '1', '0', '1', NULL, 'channelCode', 'String', 'EQ', 13, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (241, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.782000', 1, '邮箱', 'email', 'varchar(50)', NULL, 'input', '1', '0', NULL, '1', '0', '1', NULL, 'email', 'String', 'EQ', 14, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (242, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.778000', 1, '头像', 'icon_url', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', NULL, NULL, 'iconUrl', 'String', 'EQ', 15, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (243, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.774000', 1, '邀请人', 'invite', 'bigint(20)', NULL, 'input', '1', '0', NULL, '1', '0', '1', NULL, 'invite', 'Long', 'EQ', 16, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (244, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.771000', 1, '是否锁定邀请人', 'is_lock_invite', 'int(11)', 'yesOrNoOptions', 'select', '1', '0', NULL, '1', '0', NULL, NULL, 'isLockInvite', 'Long', 'EQ', 17, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (245, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.769000', 1, '是否实名', 'is_real_name', 'int(11)', 'yesOrNoOptions', 'select', '1', '0', NULL, '1', '0', '1', NULL, 'isRealName', 'Long', 'EQ', 18, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (246, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.765000', 1, '上次登陆账号ID', 'last_device_account_id', 'bigint(20)', NULL, 'input', NULL, '0', NULL, '1', '0', '1', NULL, 'lastDeviceAccountId', 'Long', 'EQ', 19, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (247, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.761000', 1, '上次设备码', 'last_device_id', 'varchar(80)', NULL, 'input', NULL, '0', NULL, '1', '0', '1', NULL, 'lastDeviceId', 'String', 'EQ', 20, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (248, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.758000', 1, '上次设备类型', 'last_device_type', 'int(2)', NULL, 'select', NULL, '0', NULL, '1', '0', '1', NULL, 'lastDeviceType', 'Integer', 'EQ', 21, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (249, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.755000', 1, '上次登陆时间', 'last_login_time', 'datetime(6)', NULL, 'datetime', NULL, '0', NULL, '1', '0', '1', NULL, 'lastLoginTime', 'Date', 'EQ', 22, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (250, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.752000', 1, '上次社交账号ID', 'last_sns_account_id', 'bigint(20)', NULL, 'input', NULL, '0', NULL, '1', '0', '1', NULL, 'lastSnsAccountId', 'Long', 'EQ', 23, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (251, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.749000', 1, '上次登陆社交账号类型', 'last_sns_type', 'int(2)', NULL, 'select', NULL, '0', NULL, '1', '0', '1', NULL, 'lastSnsType', 'Integer', 'EQ', 24, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (252, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.745000', 1, '手机号码', 'mobile', 'varchar(20)', NULL, 'input', '1', '0', NULL, '1', '0', '1', NULL, 'mobile', 'String', 'EQ', 25, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (253, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.743000', 1, '名字', 'name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'name', 'String', 'LIKE', 26, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (254, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.739000', 1, '实名', 'real_name', 'varchar(20)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'realName', 'String', 'LIKE', 27, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (255, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.737000', 1, '实名时间', 'real_name_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', '1', '0', '1', NULL, 'realNameTime', 'Date', 'EQ', 28, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (256, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.734000', 1, '地区', 'region', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'region', 'String', 'EQ', 29, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (257, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.724000', 1, '注册时设备账号ID', 'register_device_account_id', 'bigint(20)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'registerDeviceAccountId', 'Long', 'EQ', 30, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (258, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.721000', 1, '注册时设备ID', 'register_device_id', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'registerDeviceId', 'String', 'EQ', 31, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (259, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.719000', 1, '场景ID', 'scene_no', 'int(11)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'sceneNo', 'Long', 'EQ', 32, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (260, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.715000', 1, '性别', 'sex', 'int(1)', NULL, 'select', '1', '0', '1', '1', '0', '1', '1', 'sex', 'Integer', 'EQ', 33, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (261, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.712000', 1, '描述', 'user_desc', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'userDesc', 'String', 'EQ', 34, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (262, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.709000', 1, '会员等级', 'user_rank', 'int(2)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'userRank', 'Integer', 'EQ', 35, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (263, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.706000', 1, '用户类型（导入还是真实）', 'user_type', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'userType', 'Integer', 'EQ', 36, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (264, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.703000', 1, '微信号', 'wx_number', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'wxNumber', 'String', 'EQ', 37, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (265, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:04.700000', 1, 'unionId', 'wx_unionid', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'wxUnionid', 'String', 'EQ', 38, 23, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (266, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.081000', 1, '主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (267, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.078000', 1, '创建人', 'create_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'createBy', 'String', 'EQ', 2, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (268, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.075000', 1, '创建时间', 'create_time', 'datetime(6)', NULL, 'datetime', NULL, '0', '1', NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 3, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (269, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.071000', 1, '创建人ID', 'create_user_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 4, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (270, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.067000', 1, '删除标记', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 5, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (271, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.063000', 1, '状态标记', 'status_flag', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 6, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (272, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.059000', 1, '更新人', 'update_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 7, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (273, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.054000', 1, '更新时间', 'update_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 8, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (274, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.050000', 1, '更新人ID', 'update_user_id', 'bigint(8)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 9, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (275, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.047000', 1, 'SAASID', 'saas_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'saasId', 'Integer', 'EQ', 10, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (276, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.044000', 1, '凭证', 'certificates', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'certificates', 'String', 'EQ', 11, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (277, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.041000', 1, '设备ID', 'device_id', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'deviceId', 'String', 'EQ', 12, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (278, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.039000', 1, '设备类型', 'device_type', 'int(2)', NULL, 'select', '1', '0', '1', '1', '0', '1', NULL, 'deviceType', 'Integer', 'EQ', 13, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (279, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.036000', 1, '设备型号', 'device_version', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'deviceVersion', 'String', 'EQ', 14, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (280, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.033000', 1, '苹果手机IDFA', 'idfa', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'idfa', 'String', 'EQ', 15, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (281, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.030000', 1, '上次登陆IP', 'last_login_ip', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'lastLoginIp', 'String', 'EQ', 16, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (282, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.026000', 1, '上次登陆时间', 'last_login_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', '1', '0', '1', NULL, 'lastLoginTime', 'Date', 'EQ', 17, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (283, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.024000', 1, '登陆IP', 'login_ip', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'loginIp', 'String', 'EQ', 18, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (284, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.021000', 1, '登陆时间', 'login_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', '1', '0', '1', NULL, 'loginTime', 'Date', 'EQ', 19, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (285, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.017000', 1, '极光push使用的ID', 'push_id', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'pushId', 'String', 'EQ', 20, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (286, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.015000', 1, '用户账号ID', 'user_account_id', 'bigint(20)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'userAccountId', 'Long', 'EQ', 21, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (287, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:37.011000', 1, '浏览器信息', 'user_agent', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'userAgent', 'String', 'EQ', 22, 24, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (288, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.289000', 1, '主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (289, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.287000', 1, '创建人', 'create_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'createBy', 'String', 'EQ', 2, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (290, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.284000', 1, '创建时间', 'create_time', 'datetime(6)', NULL, 'datetime', NULL, '0', '1', NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 3, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (291, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.280000', 1, '创建人ID', 'create_user_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 4, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (292, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.276000', 1, '删除标记', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 5, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (293, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.271000', 1, '状态标记', 'status_flag', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 6, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (294, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.268000', 1, '更新人', 'update_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 7, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (295, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.265000', 1, '更新时间', 'update_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 8, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (296, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.262000', 1, '更新人ID', 'update_user_id', 'bigint(8)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 9, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (297, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.260000', 1, 'SAASID', 'saas_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'saasId', 'Integer', 'EQ', 10, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (298, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.257000', 1, '是否授权', 'auth_flag', 'bit(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', '1', 'authFlag', 'Integer', 'EQ', 11, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (299, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.254000', 1, '头像', 'icon_url', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'iconUrl', 'String', 'EQ', 12, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (300, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.252000', 1, 'OPENID', 'openid', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'openid', 'String', 'EQ', 13, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (301, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.250000', 1, '社交账号名称', 'sns_name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'snsName', 'String', 'LIKE', 14, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (302, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.247000', 1, '社交账号类型', 'sns_type', 'int(2)', NULL, 'select', '1', '0', '1', '1', '0', '1', NULL, 'snsType', 'Integer', 'EQ', 15, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (303, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.245000', 1, '是否关注', 'subscribe', 'bit(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', '1', 'subscribe', 'Integer', 'EQ', 16, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (304, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.242000', 1, 'UNIONID', 'union_id', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'unionId', 'String', 'EQ', 17, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (305, '2021-01-25 15:33:53.000000', 1, 0, 0, '2021-01-25 15:40:55.238000', 1, '用户账号ID', 'user_account_id', 'bigint(20)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'userAccountId', 'Long', 'EQ', 18, 25, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (306, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.864000', 1, '主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (307, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.859000', 1, '创建人', 'create_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'createBy', 'String', 'EQ', 2, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (308, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.851000', 1, '创建时间', 'create_time', 'datetime(6)', NULL, 'datetime', NULL, '0', '1', NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 3, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (309, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.844000', 1, '创建人ID', 'create_user_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 4, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (310, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.837000', 1, '删除标记', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 5, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (311, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.831000', 1, '状态标记', 'status_flag', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 6, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (312, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.825000', 1, '更新人', 'update_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 7, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (313, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.817000', 1, '更新时间', 'update_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 8, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (314, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.811000', 1, '更人ID', 'update_user_id', 'bigint(8)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 9, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (315, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.803000', 1, 'Saas账号', 'saas_id', 'bigint(8)', NULL, 'input', NULL, '0', NULL, NULL, '0', NULL, NULL, 'saasId', 'Integer', 'EQ', 10, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (316, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.797000', 1, '作者', 'author', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'author', 'String', 'EQ', 11, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (317, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.788000', 1, '渠道', 'channel_code', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'channelCode', 'String', 'EQ', 12, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (318, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.780000', 1, '封面图', 'cover_img', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'coverImg', 'String', 'EQ', 13, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (319, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.772000', 1, '封面微信素材ID', 'cover_media_id', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'coverMediaId', 'String', 'EQ', 14, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (320, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.764000', 1, '扩展信息', 'extension_profile', 'varchar(255)', NULL, 'fileUpload', '1', '0', '1', '1', '0', '1', NULL, 'extensionProfile', 'String', 'EQ', 15, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (321, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.757000', 1, '内容', 'main_body', 'text', NULL, 'textarea', '1', '0', '1', '1', '0', '1', NULL, 'mainBody', 'String', 'EQ', 16, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (322, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.750000', 1, '微信素材ID', 'media_id', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'mediaId', 'String', 'EQ', 17, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (323, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.744000', 1, '标题', 'name', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'name', 'String', 'LIKE', 18, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (324, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.738000', 1, '仅粉丝评论', 'only_fans_comment_flag', 'bit(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', '1', 'onlyFansCommentFlag', 'Integer', 'EQ', 19, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (325, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.733000', 1, '开放评论', 'open_comment_flag', 'bit(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', '1', 'openCommentFlag', 'Integer', 'EQ', 20, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (326, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.727000', 1, '原创地址链接', 'original_link', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'originalLink', 'String', 'EQ', 21, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (327, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.722000', 1, '是否原创', 'original_link_flag', 'bit(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', '1', 'originalLinkFlag', 'Integer', 'EQ', 22, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (328, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.717000', 1, '内容替换', 'replace_url_cotent', 'text', NULL, 'textarea', '1', '0', '1', '1', '0', '1', NULL, 'replaceUrlCotent', 'String', 'EQ', 23, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (329, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.711000', 1, '资源列表ID集合', 'resource_codes', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'resourceCodes', 'String', 'EQ', 24, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (330, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:01:17.705000', 1, '关联资源类型', 'resource_type', 'int(1)', NULL, 'select', '1', '0', '1', '1', '0', '1', NULL, 'resourceType', 'Integer', 'EQ', 25, 26, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (331, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.931000', 1, '主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (332, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.925000', 1, '创建人', 'create_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'createBy', 'String', 'EQ', 2, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (333, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.917000', 1, '创建时间', 'create_time', 'datetime(6)', NULL, 'datetime', NULL, '0', '1', NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 3, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (334, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.910000', 1, '创建用户', 'create_user_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 4, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (335, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.903000', 1, '删除标记', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 5, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (336, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.897000', 1, '状态标记', 'status_flag', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 6, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (337, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.892000', 1, '更新人', 'update_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 7, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (338, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.886000', 1, '更新时间', 'update_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 8, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (339, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.881000', 1, '更新ID', 'update_user_id', 'bigint(8)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 9, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (340, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.872000', 1, 'Saas账号', 'saas_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'saasId', 'Integer', 'EQ', 10, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (341, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.865000', 1, '介绍', 'introduction', 'varchar(100)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'introduction', 'String', 'EQ', 11, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (342, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.858000', 1, '素材ID', 'media_id', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'mediaId', 'String', 'EQ', 12, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (343, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.849000', 1, '名称', 'name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'name', 'String', 'LIKE', 13, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (344, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.841000', 1, '类型', 'type', 'int(2)', NULL, 'select', '1', '0', '1', '1', '0', '1', '1', 'type', 'Integer', 'EQ', 14, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (345, '2021-01-29 12:59:59.000000', 1, 0, 0, '2021-01-29 13:48:28.834000', 1, '链接', 'url', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'url', 'String', 'EQ', 15, 27, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (346, '2021-01-29 12:59:59.982000', 1, 0, 0, '2021-01-29 12:59:59.982000', 1, '主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (347, '2021-01-29 12:59:59.988000', 1, 0, 0, '2021-01-29 12:59:59.988000', 1, '创建人', 'create_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'createBy', 'String', 'EQ', 2, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (348, '2021-01-29 12:59:59.994000', 1, 0, 0, '2021-01-29 12:59:59.994000', 1, '创建时间', 'create_time', 'datetime(6)', NULL, 'datetime', NULL, '0', '1', NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 3, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (349, '2021-01-29 13:00:00.002000', 1, 0, 0, '2021-01-29 13:00:00.002000', 1, '创建人ID', 'create_user_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 4, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (350, '2021-01-29 13:00:00.007000', 1, 0, 0, '2021-01-29 13:00:00.007000', 1, '删除标记', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 5, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (351, '2021-01-29 13:00:00.012000', 1, 0, 0, '2021-01-29 13:00:00.012000', 1, '状态标记', 'status_flag', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 6, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (352, '2021-01-29 13:00:00.017000', 1, 0, 0, '2021-01-29 13:00:00.017000', 1, '更新人', 'update_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 7, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (353, '2021-01-29 13:00:00.021000', 1, 0, 0, '2021-01-29 13:00:00.021000', 1, '更新时间', 'update_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 8, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (354, '2021-01-29 13:00:00.026000', 1, 0, 0, '2021-01-29 13:00:00.026000', 1, '更新用户ID', 'update_user_id', 'bigint(8)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 9, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (355, '2021-01-29 13:00:00.032000', 1, 0, 0, '2021-01-29 13:00:00.032000', 1, 'saas账号', 'saas_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'saasId', 'Integer', 'EQ', 10, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (356, '2021-01-29 13:00:00.037000', 1, 0, 0, '2021-01-29 13:00:00.037000', 1, '类型(1 普通菜单 2小程序菜单 3发送消息)', 'action_type', 'int(2)', NULL, 'select', '1', '0', '1', '1', '0', '1', NULL, 'actionType', 'Integer', 'EQ', 11, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (357, '2021-01-29 13:00:00.042000', 1, 0, 0, '2021-01-29 13:00:00.042000', 1, '渠道', 'channel_code', 'varchar(80)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'channelCode', 'String', 'EQ', 12, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (358, '2021-01-29 13:00:00.047000', 1, 0, 0, '2021-01-29 13:00:00.047000', 1, '链接', 'link_url', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'linkUrl', 'String', 'EQ', 13, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (359, '2021-01-29 13:00:00.053000', 1, 0, 0, '2021-01-29 13:00:00.053000', 1, '名称', 'name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'name', 'String', 'LIKE', 14, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (360, '2021-01-29 13:00:00.058000', 1, 0, 0, '2021-01-29 13:00:00.058000', 1, '小程序路径', 'page_path', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'pagePath', 'String', 'EQ', 15, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (361, '2021-01-29 13:00:00.064000', 1, 0, 0, '2021-01-29 13:00:00.064000', 1, '父菜单', 'parent_id', 'bigint(20)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'parentId', 'Long', 'EQ', 16, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (362, '2021-01-29 13:00:00.069000', 1, 0, 0, '2021-01-29 13:00:00.069000', 1, '内容', 'target_content', 'varchar(255)', NULL, 'editor', '1', '0', '1', '1', '0', '1', NULL, 'targetContent', 'String', 'EQ', 17, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (363, '2021-01-29 13:00:00.074000', 1, 0, 0, '2021-01-29 13:00:00.074000', 1, '内容类型(1.消息 2.微页面 3.商品 4.文章 5.普通链接)', 'target_type', 'int(2)', NULL, 'select', '1', '0', '1', '1', '0', '1', NULL, 'targetType', 'Integer', 'EQ', 18, 28, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (364, '2021-01-29 13:00:00.090000', 1, 0, 0, '2021-01-29 13:00:00.090000', 1, '主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (365, '2021-01-29 13:00:00.096000', 1, 0, 0, '2021-01-29 13:00:00.096000', 1, '创建人', 'create_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'createBy', 'String', 'EQ', 2, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (366, '2021-01-29 13:00:00.101000', 1, 0, 0, '2021-01-29 13:00:00.101000', 1, '创建时间', 'create_time', 'datetime(6)', NULL, 'datetime', NULL, '0', '1', NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 3, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (367, '2021-01-29 13:00:00.106000', 1, 0, 0, '2021-01-29 13:00:00.106000', 1, '创建人ID', 'create_user_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 4, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (368, '2021-01-29 13:00:00.111000', 1, 0, 0, '2021-01-29 13:00:00.111000', 1, '删除标记', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 5, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (369, '2021-01-29 13:00:00.114000', 1, 0, 0, '2021-01-29 13:00:00.114000', 1, '状态标记', 'status_flag', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 6, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (370, '2021-01-29 13:00:00.117000', 1, 0, 0, '2021-01-29 13:00:00.117000', 1, '更新人', 'update_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 7, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (371, '2021-01-29 13:00:00.121000', 1, 0, 0, '2021-01-29 13:00:00.121000', 1, '更新时间', 'update_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 8, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (372, '2021-01-29 13:00:00.125000', 1, 0, 0, '2021-01-29 13:00:00.125000', 1, '更新用户ID', 'update_user_id', 'bigint(8)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 9, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (373, '2021-01-29 13:00:00.130000', 1, 0, 0, '2021-01-29 13:00:00.130000', 1, 'Saas账号', 'saas_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'saasId', 'Integer', 'EQ', 10, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (374, '2021-01-29 13:00:00.133000', 1, 0, 0, '2021-01-29 13:00:00.133000', 1, '关键字集合', 'key_words', 'varchar(255)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'keyWords', 'String', 'EQ', 11, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (375, '2021-01-29 13:00:00.136000', 1, 0, 0, '2021-01-29 13:00:00.136000', 1, '匹配类型', 'match_type', 'int(1)', NULL, 'select', '1', '0', '1', '1', '0', '1', '1', 'matchType', 'Integer', 'EQ', 12, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (376, '2021-01-29 13:00:00.140000', 1, 0, 0, '2021-01-29 13:00:00.140000', 1, '优先级', 'order_num', 'int(5)', NULL, 'input', '1', '0', '1', '1', '0', '1', '1', 'orderNum', 'Integer', 'EQ', 13, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (377, '2021-01-29 13:00:00.145000', 1, 0, 0, '2021-01-29 13:00:00.145000', 1, '接收消息类型', 'receive_type', 'int(1)', NULL, 'select', '1', '0', '1', '1', '0', '1', '1', 'receiveType', 'Integer', 'EQ', 14, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (378, '2021-01-29 13:00:00.149000', 1, 0, 0, '2021-01-29 13:00:00.149000', 1, '回复内容', 'reply_content', 'varchar(255)', NULL, 'editor', '1', '0', '1', '1', '0', '1', NULL, 'replyContent', 'String', 'EQ', 15, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (379, '2021-01-29 13:00:00.153000', 1, 0, 0, '2021-01-29 13:00:00.153000', 1, '回复类型', 'reply_type', 'int(1)', NULL, 'select', '1', '0', '1', '1', '0', '1', '1', 'replyType', 'Integer', 'EQ', 16, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (380, '2021-01-29 13:00:00.158000', 1, 0, 0, '2021-01-29 13:00:00.158000', 1, '规则名称', 'rule_name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'ruleName', 'String', 'LIKE', 17, 29, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (381, '2021-01-29 13:00:00.171000', 1, 0, 0, '2021-01-29 13:00:00.171000', 1, '主键', 'id', 'bigint(20)', NULL, 'input', NULL, '1', '1', NULL, '1', NULL, NULL, 'id', 'Long', 'EQ', 1, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (382, '2021-01-29 13:00:00.175000', 1, 0, 0, '2021-01-29 13:00:00.175000', 1, '创建人', 'create_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'createBy', 'String', 'EQ', 2, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (383, '2021-01-29 13:00:00.187000', 1, 0, 0, '2021-01-29 13:00:00.187000', 1, '创建时间', 'create_time', 'datetime(6)', NULL, 'datetime', NULL, '0', '1', NULL, '0', NULL, NULL, 'createTime', 'Date', 'EQ', 3, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (384, '2021-01-29 13:00:00.191000', 1, 0, 0, '2021-01-29 13:00:00.191000', 1, '创建用户ID', 'create_user_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'createUserId', 'Integer', 'EQ', 4, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (385, '2021-01-29 13:00:00.199000', 1, 0, 0, '2021-01-29 13:00:00.199000', 1, '删除标记', 'del_flag', 'int(1)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'delFlag', 'Integer', 'EQ', 5, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (386, '2021-01-29 13:00:00.204000', 1, 0, 0, '2021-01-29 13:00:00.204000', 1, '状态标记', 'status_flag', 'int(1)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'statusFlag', 'Integer', 'EQ', 6, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (387, '2021-01-29 13:00:00.209000', 1, 0, 0, '2021-01-29 13:00:00.209000', 1, '更新人', 'update_by', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'updateBy', 'String', 'EQ', 7, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (388, '2021-01-29 13:00:00.214000', 1, 0, 0, '2021-01-29 13:00:00.214000', 1, '更新时间', 'update_time', 'datetime(6)', NULL, 'datetime', '1', '0', '1', NULL, '0', NULL, NULL, 'updateTime', 'Date', 'EQ', 8, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (389, '2021-01-29 13:00:00.218000', 1, 0, 0, '2021-01-29 13:00:00.218000', 1, '更新用户ID', 'update_user_id', 'bigint(8)', NULL, 'input', '1', '0', '1', NULL, '0', NULL, NULL, 'updateUserId', 'Integer', 'EQ', 9, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (390, '2021-01-29 13:00:00.223000', 1, 0, 0, '2021-01-29 13:00:00.223000', 1, 'Saas账号', 'saas_id', 'bigint(8)', NULL, 'input', NULL, '0', '1', NULL, '0', NULL, NULL, 'saasId', 'Integer', 'EQ', 10, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (391, '2021-01-29 13:00:00.229000', 1, 0, 0, '2021-01-29 13:00:00.229000', 1, '渠道', 'channel_code', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'channelCode', 'String', 'EQ', 11, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (392, '2021-01-29 13:00:00.235000', 1, 0, 0, '2021-01-29 13:00:00.235000', 1, '首替', 'first', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'first', 'String', 'EQ', 12, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (393, '2021-01-29 13:00:00.239000', 1, 0, 0, '2021-01-29 13:00:00.239000', 1, '二替', 'keyword1', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'keyword1', 'String', 'EQ', 13, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (394, '2021-01-29 13:00:00.243000', 1, 0, 0, '2021-01-29 13:00:00.243000', 1, '三替', 'keyword2', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'keyword2', 'String', 'EQ', 14, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (395, '2021-01-29 13:00:00.247000', 1, 0, 0, '2021-01-29 13:00:00.247000', 1, '四替', 'keyword3', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'keyword3', 'String', 'EQ', 15, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (396, '2021-01-29 13:00:00.251000', 1, 0, 0, '2021-01-29 13:00:00.251000', 1, '名称', 'name', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'name', 'String', 'LIKE', 16, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (397, '2021-01-29 13:00:00.255000', 1, 0, 0, '2021-01-29 13:00:00.255000', 1, '备注', 'remark', 'varchar(50)', NULL, 'input', '1', '0', '1', '1', '0', NULL, NULL, 'remark', 'String', 'EQ', 17, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (398, '2021-01-29 13:00:00.259000', 1, 0, 0, '2021-01-29 13:00:00.259000', 1, '场景', 'scene_type', 'int(2)', NULL, 'select', '1', '0', '1', '1', '0', '1', '1', 'sceneType', 'Integer', 'EQ', 18, 30, 'admin/管理员', 'admin/管理员');
INSERT INTO `gen_table_column` VALUES (399, '2021-01-29 13:00:00.262000', 1, 0, 0, '2021-01-29 13:00:00.262000', 1, '微信模版ID', 'wx_template_id', 'varchar(100)', NULL, 'input', '1', '0', '1', '1', '0', '1', NULL, 'wxTemplateId', 'String', 'EQ', 19, 30, 'admin/管理员', 'admin/管理员');
COMMIT;

-- ----------------------------
-- Table structure for sys_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `parent_id` int(5) DEFAULT '0' COMMENT '父目录ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(6) DEFAULT NULL COMMENT '创建人ID',
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标识',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态标识',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` int(6) DEFAULT NULL COMMENT '修改人ID',
  `ancestors` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '祖级列表',
  `order_num` int(5) DEFAULT '0' COMMENT '排序',
  `parent_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '父名称',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统目录表';

-- ----------------------------
-- Records of sys_category
-- ----------------------------
BEGIN;
INSERT INTO `sys_category` VALUES (2, '1234', 0, NULL, 1, 0, 1, NULL, NULL, NULL, 2, NULL, NULL, NULL);
INSERT INTO `sys_category` VALUES (3, '12343', 0, '2021-01-07 16:35:41', 1, 0, 1, '2021-01-14 16:56:43', 1, NULL, 12, NULL, NULL, 'admin/管理员');
INSERT INTO `sys_category` VALUES (4, '121233', 3, '2021-01-07 16:39:02', 1, 1, 1, '2021-01-08 12:43:48', 1, NULL, 12213, NULL, NULL, NULL);
INSERT INTO `sys_category` VALUES (5, '233', 0, '2021-01-07 16:39:17', 1, 0, 1, '2021-01-20 12:07:08', 1, NULL, 22, NULL, NULL, 'admin/管理员');
INSERT INTO `sys_category` VALUES (6, '22222', 5, '2021-01-07 16:39:39', 1, 0, 1, '2021-01-14 16:56:39', 1, NULL, 23, NULL, NULL, 'admin/管理员');
INSERT INTO `sys_category` VALUES (7, '4566', 0, '2021-01-08 12:44:05', 1, 0, 1, '2021-01-20 15:27:13', 1, NULL, 23, NULL, NULL, 'admin/管理员');
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) COLLATE utf8mb4_bin DEFAULT '' COMMENT '参数键值',
  `config_type` bit(1) DEFAULT b'0' COMMENT '系统内置（Y是 N否）',
  `create_user_id` int(6) DEFAULT '0' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` int(6) DEFAULT '0' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `del_flag` int(1) DEFAULT '0' COMMENT '软删除标志',
  `status_flag` int(1) DEFAULT '1' COMMENT '状态',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', b'1', 0, '2021-01-06 16:07:49', 0, NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow', 0, 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', b'1', 0, '2021-01-06 16:07:49', 0, NULL, '初始化密码 123456', 0, 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-light', b'1', 0, '2021-01-06 16:07:49', 1, '2021-01-06 17:27:42', '深色主题theme-dark，浅色主题theme-light', 0, 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (100, '122', '222', '2223', b'1', 1, '2021-01-06 17:07:58', 1, '2021-01-06 17:08:05', '111', 1, 0, NULL, NULL);
INSERT INTO `sys_config` VALUES (101, '1234', 'http://localhost:8080/profile/upload/2021/01/07/a6d887f4-e82f-4333-b2bf-7ba894d5f84d.pdf', 'http://localhost:8080/profile/upload/2021/01/07/fea22976-78d4-4e86-adc7-0d7ddda06488.jpg', b'1', 1, '2021-01-07 17:29:34', 1, '2021-01-07 17:30:22', 'asdsdsa', 1, 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标志',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态标志',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_user_id` int(8) DEFAULT NULL COMMENT '更新人',
  `ancestors` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '祖级列表',
  `dept_name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门名称',
  `email` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `leader` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '负责人',
  `order_num` int(8) DEFAULT NULL COMMENT '排序',
  `parent_id` int(8) DEFAULT NULL COMMENT '父ID',
  `phone` varchar(11) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号码',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1, '2020-12-29 17:30:33.000000', 1, 0, 1, '2020-12-29 17:30:42.000000', 1, NULL, 'XXXX公司', 'aa@qq.com', '11', 1, 0, '18700098765', NULL, NULL);
INSERT INTO `sys_dept` VALUES (2, '2020-12-30 12:34:42.000000', 1, 0, 1, '2021-01-04 16:52:32.717000', 1, NULL, '人事部', 'aa@ss.com', '例三', 3, 1, '18700092817', NULL, NULL);
INSERT INTO `sys_dept` VALUES (3, '2020-12-30 12:51:06.917000', 1, 0, 1, '2020-12-30 12:51:06.917000', NULL, NULL, '招聘部', 'qq@qq.com', '例三是', 2, 2, '19877261234', NULL, NULL);
INSERT INTO `sys_dept` VALUES (4, '2020-12-30 12:51:51.000000', 1, 0, 1, '2021-02-25 15:15:56.922000', 1, NULL, '运营部', '11@11.com', '雨伞', 4, 1, '19820394762', NULL, 'admin/管理员');
INSERT INTO `sys_dept` VALUES (5, '2021-02-25 15:16:43.568000', 1, 0, 1, '2021-02-25 15:16:43.568000', 1, NULL, '市场部', '123@qw.com', '例三', 6, 1, '18700098761', 'admin/管理员', 'admin/管理员');
COMMIT;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '操作系统',
  `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
BEGIN;
INSERT INTO `sys_logininfor` VALUES (100, '管理员', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', NULL);
INSERT INTO `sys_logininfor` VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', NULL);
INSERT INTO `sys_logininfor` VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-12 10:22:12');
INSERT INTO `sys_logininfor` VALUES (103, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-14 14:57:52');
INSERT INTO `sys_logininfor` VALUES (104, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-14 15:45:31');
INSERT INTO `sys_logininfor` VALUES (105, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-14 16:51:49');
INSERT INTO `sys_logininfor` VALUES (106, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-15 11:16:13');
INSERT INTO `sys_logininfor` VALUES (107, '管理员', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-01-15 11:27:07');
INSERT INTO `sys_logininfor` VALUES (108, '12345', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-15 11:27:16');
INSERT INTO `sys_logininfor` VALUES (109, '12345', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-01-15 11:37:09');
INSERT INTO `sys_logininfor` VALUES (110, '12345', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-01-15 11:37:13');
INSERT INTO `sys_logininfor` VALUES (111, '12345', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-15 11:37:17');
INSERT INTO `sys_logininfor` VALUES (112, '12345', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-01-15 11:44:12');
INSERT INTO `sys_logininfor` VALUES (113, '12345', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '用户不存在/密码错误', '2021-01-15 11:44:16');
INSERT INTO `sys_logininfor` VALUES (114, '12345', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '用户不存在/密码错误', '2021-01-15 11:44:23');
INSERT INTO `sys_logininfor` VALUES (115, '12345', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '用户不存在/密码错误', '2021-01-15 11:44:32');
INSERT INTO `sys_logininfor` VALUES (116, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-15 11:44:41');
INSERT INTO `sys_logininfor` VALUES (117, '管理员', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-01-15 11:45:02');
INSERT INTO `sys_logininfor` VALUES (118, '12345', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-15 11:45:12');
INSERT INTO `sys_logininfor` VALUES (119, '12345', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-01-15 11:50:26');
INSERT INTO `sys_logininfor` VALUES (120, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-15 11:50:36');
INSERT INTO `sys_logininfor` VALUES (121, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '0', '登录成功', '2021-01-15 11:53:08');
INSERT INTO `sys_logininfor` VALUES (122, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '0', '退出成功', '2021-01-15 11:54:03');
INSERT INTO `sys_logininfor` VALUES (123, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '0', '登录成功', '2021-01-15 11:54:15');
INSERT INTO `sys_logininfor` VALUES (124, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '0', '退出成功', '2021-01-15 12:04:26');
INSERT INTO `sys_logininfor` VALUES (125, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '0', '登录成功', '2021-01-15 12:04:31');
INSERT INTO `sys_logininfor` VALUES (126, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '0', '退出成功', '2021-01-15 12:04:57');
INSERT INTO `sys_logininfor` VALUES (127, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '0', '登录成功', '2021-01-15 12:05:06');
INSERT INTO `sys_logininfor` VALUES (128, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-01-15 15:21:20');
INSERT INTO `sys_logininfor` VALUES (129, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-01-15 15:21:23');
INSERT INTO `sys_logininfor` VALUES (130, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-15 15:21:27');
INSERT INTO `sys_logininfor` VALUES (131, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码错误', '2021-01-15 15:52:57');
INSERT INTO `sys_logininfor` VALUES (132, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-15 15:53:01');
INSERT INTO `sys_logininfor` VALUES (133, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-15 17:47:02');
INSERT INTO `sys_logininfor` VALUES (134, '管理员', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-01-15 17:47:24');
INSERT INTO `sys_logininfor` VALUES (135, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-15 17:47:30');
INSERT INTO `sys_logininfor` VALUES (136, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', '2021-01-18 14:51:38');
INSERT INTO `sys_logininfor` VALUES (137, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-18 14:51:44');
INSERT INTO `sys_logininfor` VALUES (138, '管理员', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-01-20 12:06:32');
INSERT INTO `sys_logininfor` VALUES (139, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-20 12:06:39');
INSERT INTO `sys_logininfor` VALUES (140, '管理员', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-01-20 12:12:23');
INSERT INTO `sys_logininfor` VALUES (141, 'admin', '127.0.0.1,127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-20 12:45:34');
INSERT INTO `sys_logininfor` VALUES (142, 'admin', '127.0.0.1,127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-20 12:55:30');
INSERT INTO `sys_logininfor` VALUES (143, '管理员', '127.0.0.1,127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-01-20 12:55:31');
INSERT INTO `sys_logininfor` VALUES (144, 'admin', '127.0.0.1,127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-20 13:35:34');
INSERT INTO `sys_logininfor` VALUES (145, '管理员', '127.0.0.1,127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-01-20 13:35:35');
INSERT INTO `sys_logininfor` VALUES (146, 'admin', '127.0.0.1,127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-20 13:48:55');
INSERT INTO `sys_logininfor` VALUES (147, '管理员', '127.0.0.1,127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-01-20 15:25:20');
INSERT INTO `sys_logininfor` VALUES (148, 'admin', '127.0.0.1,127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-20 15:25:33');
INSERT INTO `sys_logininfor` VALUES (149, '12345', '127.0.0.1,127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '0', '登录成功', '2021-01-20 15:28:33');
INSERT INTO `sys_logininfor` VALUES (150, '12345', '127.0.0.1,127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '0', '退出成功', '2021-01-20 15:33:03');
INSERT INTO `sys_logininfor` VALUES (151, '12345', '127.0.0.1,127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '0', '登录成功', '2021-01-20 15:33:09');
INSERT INTO `sys_logininfor` VALUES (152, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '获取用户信息异常', '2021-01-21 14:49:42');
INSERT INTO `sys_logininfor` VALUES (153, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '获取用户信息异常', '2021-01-21 14:50:24');
INSERT INTO `sys_logininfor` VALUES (154, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', '2021-01-21 14:54:17');
INSERT INTO `sys_logininfor` VALUES (155, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '获取用户信息异常', '2021-01-21 14:54:22');
INSERT INTO `sys_logininfor` VALUES (156, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', '2021-01-21 14:57:59');
INSERT INTO `sys_logininfor` VALUES (157, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '获取用户信息异常', '2021-01-21 14:58:03');
INSERT INTO `sys_logininfor` VALUES (158, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '1', '验证码已失效', '2021-01-21 15:06:45');
INSERT INTO `sys_logininfor` VALUES (159, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '1', '获取用户信息异常', '2021-01-21 15:06:50');
INSERT INTO `sys_logininfor` VALUES (160, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '获取用户信息异常', '2021-01-21 15:12:03');
INSERT INTO `sys_logininfor` VALUES (161, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '1', '验证码已失效', '2021-01-21 15:12:48');
INSERT INTO `sys_logininfor` VALUES (162, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '1', '验证码已失效', '2021-01-21 15:21:18');
INSERT INTO `sys_logininfor` VALUES (163, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '1', '获取用户信息异常', '2021-01-21 15:21:22');
INSERT INTO `sys_logininfor` VALUES (164, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '1', '验证码已失效', '2021-01-21 15:28:48');
INSERT INTO `sys_logininfor` VALUES (165, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '1', '获取用户信息异常', '2021-01-21 15:28:52');
INSERT INTO `sys_logininfor` VALUES (166, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '1', '验证码已失效', '2021-01-21 15:31:57');
INSERT INTO `sys_logininfor` VALUES (167, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '1', '获取用户信息异常', '2021-01-21 15:32:22');
INSERT INTO `sys_logininfor` VALUES (168, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '1', '验证码已失效', '2021-01-21 15:39:49');
INSERT INTO `sys_logininfor` VALUES (169, '12345', '127.0.0.1', '内网IP', 'Safari', 'Mac OS X', '0', '登录成功', '2021-01-21 15:39:54');
INSERT INTO `sys_logininfor` VALUES (170, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '1', '验证码已失效', '2021-01-21 15:40:12');
INSERT INTO `sys_logininfor` VALUES (171, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-21 15:40:15');
INSERT INTO `sys_logininfor` VALUES (172, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-25 15:33:28');
INSERT INTO `sys_logininfor` VALUES (173, 'admin', '127.0.0.1,127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-27 17:34:35');
INSERT INTO `sys_logininfor` VALUES (174, 'admin', '127.0.0.1,127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-01-29 12:32:20');
INSERT INTO `sys_logininfor` VALUES (175, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-02-04 13:33:47');
INSERT INTO `sys_logininfor` VALUES (176, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-02-04 15:01:07');
INSERT INTO `sys_logininfor` VALUES (177, '管理员', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '退出成功', '2021-02-04 15:06:34');
INSERT INTO `sys_logininfor` VALUES (178, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-02-04 15:06:38');
INSERT INTO `sys_logininfor` VALUES (179, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-02-04 17:24:45');
INSERT INTO `sys_logininfor` VALUES (180, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-02-05 10:12:19');
INSERT INTO `sys_logininfor` VALUES (181, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-02-05 10:46:38');
INSERT INTO `sys_logininfor` VALUES (182, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-02-05 16:08:51');
INSERT INTO `sys_logininfor` VALUES (183, 'admin', '127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-02-06 10:07:18');
INSERT INTO `sys_logininfor` VALUES (184, 'admin', '127.0.0.1,127.0.0.1', '内网IP', 'Chrome 8', 'Mac OS X', '0', '登录成功', '2021-02-25 14:54:25');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单名称',
  `parent_id` int(8) DEFAULT NULL COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) COLLATE utf8mb4_bin DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组件路径',
  `frame` bit(1) NOT NULL COMMENT '是否为外链（1是 0否）',
  `cache` bit(1) NOT NULL COMMENT '是否缓存（1缓存 0不缓存）',
  `menu_type` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` bit(1) NOT NULL COMMENT '菜单状态（1显示 0隐藏）',
  `status_flag` int(1) DEFAULT '1' COMMENT '菜单状态（1正常 -1停用）',
  `perms` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) COLLATE utf8mb4_bin DEFAULT '#' COMMENT '菜单图标',
  `create_user_id` int(8) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(8) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT '' COMMENT '备注',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1069 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, b'0', b'1', 'M', b'1', 1, '', 'system', 1, '2020-12-29 12:54:14', 1, NULL, '系统管理目录', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, b'0', b'1', 'M', b'1', 1, '', 'monitor', 1, '2020-12-29 12:54:14', 1, NULL, '系统监控目录', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, b'0', b'1', 'M', b'1', 1, '', 'tool', 1, '2020-12-29 12:54:14', 1, NULL, '系统工具目录', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (4, '官网', 0, 10, 'http://www.qz-z.com', NULL, b'1', b'1', 'M', b'1', 1, '', 'guide', 1, '2020-12-29 12:54:14', 1, '2021-02-05 10:46:53', '若依官网地址', 0, NULL, 'admin/管理员');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', b'0', b'1', 'C', b'1', 1, 'system:user:list', 'user', 1, '2020-12-29 12:54:14', 1, NULL, '用户管理菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', b'0', b'1', 'C', b'1', 1, 'system:role:list', 'peoples', 1, '2020-12-29 12:54:14', 1, NULL, '角色管理菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', b'0', b'1', 'C', b'1', 1, 'system:menu:list', 'tree-table', 1, '2020-12-29 12:54:14', 1, NULL, '菜单管理菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', b'0', b'1', 'C', b'1', 1, 'system:dept:list', 'tree', 1, '2020-12-29 12:54:14', 1, NULL, '部门管理菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', b'0', b'1', 'C', b'1', 1, 'system:post:list', 'post', 1, '2020-12-29 12:54:14', 1, '2021-01-14 16:57:12', '岗位管理菜单', 1, NULL, '管理员');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', b'0', b'1', 'C', b'1', 1, 'system:dict:list', 'dict', 1, '2020-12-29 12:54:14', 1, '2021-01-14 16:57:18', '字典管理菜单', 1, NULL, '管理员');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', b'0', b'1', 'C', b'1', 1, 'system:config:list', 'edit', 1, '2020-12-29 12:54:14', 1, NULL, '参数设置菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', b'0', b'1', 'C', b'1', 1, 'system:notice:list', 'message', 1, '2020-12-29 12:54:14', 1, '2021-01-14 16:57:23', '通知公告菜单', 1, NULL, '管理员');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', b'0', b'1', 'M', b'1', 1, '', 'log', 1, '2020-12-29 12:54:14', 1, NULL, '日志管理菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', b'0', b'1', 'C', b'1', 1, 'monitor:online:list', 'online', 1, '2020-12-29 12:54:14', 1, NULL, '在线用户菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', b'0', b'1', 'C', b'1', 1, 'monitor:job:list', 'job', 1, '2020-12-29 12:54:14', 1, '2021-01-14 16:57:32', '定时任务菜单', 1, NULL, '管理员');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', b'0', b'1', 'C', b'1', 1, 'monitor:druid:list', 'druid', 1, '2020-12-29 12:54:14', 1, NULL, '数据监控菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', b'0', b'1', 'C', b'1', 1, 'monitor:server:list', 'server', 1, '2020-12-29 12:54:14', 1, NULL, '服务监控菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', b'0', b'1', 'C', b'1', 1, 'monitor:cache:list', 'redis', 1, '2020-12-29 12:54:14', 1, NULL, '缓存监控菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, 'build', 'tool/build/index', b'0', b'1', 'C', b'1', 1, 'tool:build:list', 'build', 1, '2020-12-29 12:54:14', 1, NULL, '表单构建菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', b'0', b'1', 'C', b'1', 1, 'tool:gen:list', 'code', 1, '2020-12-29 12:54:14', 1, NULL, '代码生成菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', b'0', b'1', 'C', b'1', 1, 'tool:swagger:list', 'swagger', 1, '2020-12-29 12:54:14', 1, NULL, '系统接口菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', b'0', b'1', 'C', b'1', 1, 'monitor:operlog:list', 'form', 1, '2020-12-29 12:54:14', 1, NULL, '操作日志菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', b'0', b'1', 'C', b'1', 1, 'monitor:logininfor:list', 'logininfor', 1, '2020-12-29 12:54:14', 1, NULL, '登录日志菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (1061, '1232', 4, 1, '111', NULL, b'0', b'1', 'M', b'0', 0, NULL, 'list', 1, '2020-12-30 15:11:24', 1, '2020-12-30 15:14:51', '', 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (1063, '权限管理', 1, 3, 'privilege', 'system/privilege/index', b'0', b'1', 'C', b'1', 1, 'system:privilege:list', 'skill', 1, '2020-12-30 15:43:06', 1, '2020-12-30 15:43:55', '', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (1064, '系统目录', 1, 1, 'category', 'system/category/index', b'0', b'1', 'C', b'1', 1, 'system:category:list', 'cascader', 1, '2021-01-07 15:29:08', 1, '2021-01-07 16:38:40', '系统目录菜单', 0, NULL, NULL);
INSERT INTO `sys_menu` VALUES (1065, '微信配置', 0, 5, 'wechat', NULL, b'0', b'1', 'M', b'1', 1, NULL, 'wechat', 1, '2021-01-21 15:48:45', 1, '2021-02-04 15:39:26', '', 0, 'admin/管理员', 'admin/管理员');
INSERT INTO `sys_menu` VALUES (1066, 'SAAS账号', 1, 1, 'account', 'system/saasaccount/index', b'0', b'1', 'C', b'1', 1, 'system:account:list', 'nested', 1, '2021-01-21 15:55:54', 1, '2021-01-21 16:00:36', 'SAAS账号菜单', 0, NULL, 'admin/管理员');
INSERT INTO `sys_menu` VALUES (1067, '用户中心', 0, 6, 'usercenter', NULL, b'0', b'1', 'M', b'1', 1, NULL, 'peoples', 1, '2021-01-25 15:39:38', 1, '2021-01-25 15:39:38', '', 0, 'admin/管理员', 'admin/管理员');
INSERT INTO `sys_menu` VALUES (1068, '账号配置', 1065, 100, 'authaccount', 'wechat/authaccount/index', b'0', b'1', 'C', b'1', 1, 'wechat:authaccount:list', 'dashboard', 1, '2021-02-04 15:02:42', 1, '2021-02-04 15:39:40', '', 0, 'admin/管理员', 'admin/管理员');
COMMIT;

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) COLLATE utf8mb4_bin DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) COLLATE utf8mb4_bin DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) COLLATE utf8mb4_bin DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) COLLATE utf8mb4_bin DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) COLLATE utf8mb4_bin DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) COLLATE utf8mb4_bin DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_oper_log` VALUES (103, '操作日志', 9, 'com.saas.admin.controller.monitor.SysOperlogController.clean()', 'DELETE', 1, '管理员', NULL, '/monitor/operlog/clean', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, NULL);
INSERT INTO `sys_oper_log` VALUES (104, '系统目录', 2, 'com.saas.system.controller.SysCategoryController.edit()', 'PUT', 1, '管理员', NULL, '/system/category', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"orderNum\":12,\"updateTime\":1610084781936,\"delFlag\":0,\"parentId\":0,\"children\":[],\"createTime\":1610008541000,\"name\":\"12343\",\"id\":3,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, NULL);
INSERT INTO `sys_oper_log` VALUES (105, '用户管理', 2, 'com.saas.admin.controller.system.SysUserController.changeStatus()', 'PUT', 1, '管理员', NULL, '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"sex\":0,\"admin\":false,\"updateTime\":1610084790327,\"delFlag\":0,\"roleList\":[],\"createTime\":1610084790327,\"id\":3,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, NULL);
INSERT INTO `sys_oper_log` VALUES (106, '角色管理', 2, 'com.saas.admin.controller.system.SysRoleController.edit()', 'PUT', 1, '管理员', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"showName\":\"admin\",\"createTime\":1609324250000,\"updateUserId\":1,\"name\":\"admin\",\"orderNum\":1,\"updateTime\":1610084799421,\"id\":1,\"delFlag\":0,\"privilegeIds\":[3,5,6,7],\"privilegeList\":[{\"createTime\":1610084799421,\"updateTime\":1610084799421,\"id\":3,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610084799421,\"updateTime\":1610084799421,\"id\":5,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610084799421,\"updateTime\":1610084799421,\"id\":6,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610084799421,\"updateTime\":1610084799421,\"id\":7,\"delFlag\":0,\"statusFlag\":0}],\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, NULL);
INSERT INTO `sys_oper_log` VALUES (107, '角色管理', 2, 'com.saas.admin.controller.system.SysRoleController.edit()', 'PUT', 1, '管理员', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"showName\":\"admin\",\"createTime\":1609324250000,\"updateUserId\":1,\"name\":\"admin\",\"orderNum\":1,\"updateTime\":1610084804881,\"id\":1,\"delFlag\":0,\"privilegeIds\":[3,5,6,7,8,9],\"privilegeList\":[{\"createTime\":1610084804881,\"updateTime\":1610084804881,\"id\":3,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610084804881,\"updateTime\":1610084804881,\"id\":5,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610084804881,\"updateTime\":1610084804881,\"id\":6,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610084804881,\"updateTime\":1610084804881,\"id\":7,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610084804881,\"updateTime\":1610084804881,\"id\":8,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610084804881,\"updateTime\":1610084804881,\"id\":9,\"delFlag\":0,\"statusFlag\":0}],\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, NULL);
INSERT INTO `sys_oper_log` VALUES (108, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, '管理员', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"guide\",\"orderNum\":4,\"menuName\":\"官网\",\"updateTime\":1610084842587,\"delFlag\":0,\"parentId\":0,\"path\":\"http://www.qz-z.cn\",\"createTime\":1609217654000,\"menuType\":\"M\",\"perms\":\"\",\"id\":4,\"frame\":true,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, NULL);
INSERT INTO `sys_oper_log` VALUES (109, '系统权限', 3, 'com.saas.admin.controller.system.SysPrivilegeController.remove()', 'DELETE', 1, '管理员', NULL, '/system/privilege/7', '127.0.0.1', '内网IP', '{privilegeIds=7}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-08 13:49:06');
INSERT INTO `sys_oper_log` VALUES (110, '系统权限', 3, 'com.saas.admin.controller.system.SysPrivilegeController.remove()', 'DELETE', 1, '管理员', NULL, '/system/privilege/6', '127.0.0.1', '内网IP', '{privilegeIds=6}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-08 13:49:11');
INSERT INTO `sys_oper_log` VALUES (111, '系统权限', 3, 'com.saas.admin.controller.system.SysPrivilegeController.remove()', 'DELETE', 1, '管理员', NULL, '/system/privilege/5', '127.0.0.1', '内网IP', '{privilegeIds=5}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-08 13:49:16');
INSERT INTO `sys_oper_log` VALUES (112, '系统权限', 3, 'com.saas.admin.controller.system.SysPrivilegeController.remove()', 'DELETE', 1, '管理员', NULL, '/system/privilege/3', '127.0.0.1', '内网IP', '{privilegeIds=3}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-08 13:49:19');
INSERT INTO `sys_oper_log` VALUES (113, '用户管理', 2, 'com.saas.admin.controller.system.SysUserController.edit()', 'PUT', 1, '管理员', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"sex\":1,\"deptId\":2,\"mobile\":\"13545678909\",\"admin\":false,\"updateTime\":1610084976435,\"delFlag\":0,\"roleList\":[{\"createTime\":1610084976428,\"orderNum\":0,\"updateTime\":1610084976428,\"id\":1,\"delFlag\":0,\"privilegeList\":[],\"statusFlag\":0},{\"createTime\":1610084976428,\"orderNum\":0,\"updateTime\":1610084976428,\"id\":2,\"delFlag\":0,\"privilegeList\":[],\"statusFlag\":0}],\"password\":\"$2a$10$FVgY/wSidJec6GcwKkRW8.iOrhPbAheJFAMmV.MKj0zmOBAW24z0.\",\"roleIds\":[1,2],\"createTime\":1609989954000,\"loginName\":\"12345\",\"name\":\"12345\",\"id\":3,\"email\":\"123@qq.com\",\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-08 13:49:36');
INSERT INTO `sys_oper_log` VALUES (114, '系统目录', 2, 'com.saas.system.controller.SysCategoryController.edit()', 'PUT', 1, '管理员', NULL, '/system/category', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"orderNum\":22,\"updateTime\":1610614428574,\"delFlag\":0,\"parentId\":0,\"children\":[],\"createTime\":1610008757000,\"updateBy\":\"管理员\",\"name\":\"233\",\"id\":5,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-14 16:53:49');
INSERT INTO `sys_oper_log` VALUES (115, '系统目录', 2, 'com.saas.system.controller.SysCategoryController.edit()', 'PUT', 1, '管理员', NULL, '/system/category', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"orderNum\":22,\"updateTime\":1610614440780,\"delFlag\":0,\"parentId\":0,\"children\":[],\"createTime\":1610008757000,\"updateBy\":\"管理员\",\"name\":\"233\",\"id\":5,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-14 16:54:01');
INSERT INTO `sys_oper_log` VALUES (116, '系统目录', 2, 'com.saas.system.controller.SysCategoryController.edit()', 'PUT', 1, '管理员', NULL, '/system/category', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"orderNum\":22,\"updateTime\":1610614454606,\"delFlag\":0,\"parentId\":0,\"children\":[],\"createTime\":1610008757000,\"updateBy\":\"管理员\",\"name\":\"233\",\"id\":5,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-14 16:54:15');
INSERT INTO `sys_oper_log` VALUES (117, '系统目录', 2, 'com.saas.system.controller.SysCategoryController.edit()', 'PUT', 1, '管理员', NULL, '/system/category', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"orderNum\":22,\"updateTime\":1610614595651,\"delFlag\":0,\"parentId\":0,\"children\":[],\"createTime\":1610008757000,\"updateBy\":\"admin/管理员\",\"name\":\"233\",\"id\":5,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-14 16:56:36');
INSERT INTO `sys_oper_log` VALUES (118, '系统目录', 2, 'com.saas.system.controller.SysCategoryController.edit()', 'PUT', 1, '管理员', NULL, '/system/category', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"orderNum\":23,\"updateTime\":1610614599299,\"delFlag\":0,\"parentId\":5,\"children\":[],\"createTime\":1610008779000,\"updateBy\":\"admin/管理员\",\"name\":\"22222\",\"id\":6,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-14 16:56:39');
INSERT INTO `sys_oper_log` VALUES (119, '系统目录', 2, 'com.saas.system.controller.SysCategoryController.edit()', 'PUT', 1, '管理员', NULL, '/system/category', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"orderNum\":12,\"updateTime\":1610614602875,\"delFlag\":0,\"parentId\":0,\"children\":[],\"createTime\":1610008541000,\"updateBy\":\"admin/管理员\",\"name\":\"12343\",\"id\":3,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-14 16:56:43');
INSERT INTO `sys_oper_log` VALUES (120, '系统目录', 2, 'com.saas.system.controller.SysCategoryController.edit()', 'PUT', 1, '管理员', NULL, '/system/category', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"orderNum\":23,\"updateTime\":1610614605934,\"delFlag\":0,\"parentId\":3,\"children\":[],\"createTime\":1610081045000,\"updateBy\":\"admin/管理员\",\"name\":\"4566\",\"id\":7,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-14 16:56:46');
INSERT INTO `sys_oper_log` VALUES (121, '菜单管理', 3, 'com.saas.admin.controller.system.SysMenuController.remove()', 'DELETE', 1, '管理员', NULL, '/system/menu/104', '127.0.0.1', '内网IP', '{menuId=104}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-14 16:57:12');
INSERT INTO `sys_oper_log` VALUES (122, '菜单管理', 3, 'com.saas.admin.controller.system.SysMenuController.remove()', 'DELETE', 1, '管理员', NULL, '/system/menu/105', '127.0.0.1', '内网IP', '{menuId=105}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-14 16:57:18');
INSERT INTO `sys_oper_log` VALUES (123, '菜单管理', 3, 'com.saas.admin.controller.system.SysMenuController.remove()', 'DELETE', 1, '管理员', NULL, '/system/menu/107', '127.0.0.1', '内网IP', '{menuId=107}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-14 16:57:23');
INSERT INTO `sys_oper_log` VALUES (124, '菜单管理', 3, 'com.saas.admin.controller.system.SysMenuController.remove()', 'DELETE', 1, '管理员', NULL, '/system/menu/110', '127.0.0.1', '内网IP', '{menuId=110}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-14 16:57:32');
INSERT INTO `sys_oper_log` VALUES (125, '个人信息', 2, 'com.saas.admin.controller.system.SysProfileController.updateProfile()', 'PUT', 1, '管理员', NULL, '/system/user/profile', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"sex\":1,\"mobile\":\"18700092817\",\"admin\":true,\"loginDate\":1609143084000,\"updateTime\":1609143070000,\"delFlag\":0,\"roleList\":[],\"roleIds\":[1],\"createTime\":1609143060000,\"loginIp\":\"1.1.1.1\",\"loginName\":\"admin\",\"name\":\"管理员\",\"id\":1,\"email\":\"admin@saas.com\",\"statusFlag\":1}', 'null', 1, '不能修改超级管理员用户', '2021-01-14 17:31:40');
INSERT INTO `sys_oper_log` VALUES (126, '个人信息', 2, 'com.saas.admin.controller.system.SysProfileController.updatePwd()', 'PUT', 1, '管理员', NULL, '/system/user/profile/updatePwd', '127.0.0.1', '内网IP', '123456 123456', '{\"msg\":\"新密码不能与旧密码相同\",\"code\":500}', 0, NULL, '2021-01-14 17:32:31');
INSERT INTO `sys_oper_log` VALUES (127, '个人信息', 2, 'com.saas.admin.controller.system.SysProfileController.updatePwd()', 'PUT', 1, '管理员', NULL, '/system/user/profile/updatePwd', '127.0.0.1', '内网IP', '123456 1234563', 'null', 1, '不能修改超级管理员用户', '2021-01-14 17:32:39');
INSERT INTO `sys_oper_log` VALUES (128, '用户头像', 2, 'com.saas.admin.controller.system.SysProfileController.avatar()', 'POST', 1, '管理员', NULL, '/system/user/profile/avatar', '127.0.0.1', '内网IP', '', 'null', 1, '不能修改超级管理员用户', '2021-01-14 17:38:36');
INSERT INTO `sys_oper_log` VALUES (129, '系统权限', 2, 'com.saas.admin.controller.system.SysPrivilegeController.edit()', 'PUT', 1, '管理员', NULL, '/system/privilege', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"createTime\":1610080301000,\"updateBy\":\"admin/管理员\",\"updateUserId\":1,\"permissions\":\"system:log:query,system:log:list,system:log:add,system:log:edit,system:log:remove,system:log:export\",\"name\":\"操作日志记录权限\",\"updateTime\":1610680934621,\"id\":9,\"delFlag\":0,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:22:15');
INSERT INTO `sys_oper_log` VALUES (130, '系统权限', 2, 'com.saas.admin.controller.system.SysPrivilegeController.edit()', 'PUT', 1, '管理员', NULL, '/system/privilege', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"createTime\":1610004548000,\"updateBy\":\"admin/管理员\",\"updateUserId\":1,\"permissions\":\"system:category:query,system:category:list,system:category:add,system:category:edit,system:category:remove,system:category:export\",\"name\":\"系统目录权限\",\"updateTime\":1610680937471,\"id\":8,\"delFlag\":0,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:22:17');
INSERT INTO `sys_oper_log` VALUES (131, '代码生成', 6, 'com.saas.generator.controller.GenController.importTableSave()', 'POST', 1, '管理员', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'sys_logininfor,sys_menu,sys_privilege,sys_role,sys_role_privilege,sys_user,sys_user_role', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:22:51');
INSERT INTO `sys_oper_log` VALUES (132, '代码生成', 3, 'com.saas.generator.controller.GenController.remove()', 'DELETE', 1, '管理员', NULL, '/tool/gen/20', '127.0.0.1', '内网IP', '{tableIds=20}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:25:32');
INSERT INTO `sys_oper_log` VALUES (133, '代码生成', 3, 'com.saas.generator.controller.GenController.remove()', 'DELETE', 1, '管理员', NULL, '/tool/gen/18', '127.0.0.1', '内网IP', '{tableIds=18}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:25:35');
INSERT INTO `sys_oper_log` VALUES (134, '系统权限', 2, 'com.saas.admin.controller.system.SysPrivilegeController.edit()', 'PUT', 1, '管理员', NULL, '/system/privilege', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"createTime\":1610681088000,\"updateBy\":\"admin/管理员\",\"updateUserId\":1,\"permissions\":\"system:privilege:query,system:privilege:list,system:privilege:add,system:privilege:edit,system:privilege:remove,system:privilege:export\",\"name\":\"权限配置\",\"updateTime\":1610681160750,\"id\":14,\"delFlag\":0,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:26:01');
INSERT INTO `sys_oper_log` VALUES (135, '角色管理', 2, 'com.saas.admin.controller.system.SysRoleController.edit()', 'PUT', 1, '管理员', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"showName\":\"admin\",\"updateUserId\":1,\"orderNum\":1,\"updateTime\":1610681197724,\"delFlag\":0,\"privilegeIds\":[16,15,14,12,13,11,10,8,9],\"createTime\":1609324250000,\"updateBy\":\"admin/管理员\",\"name\":\"admin\",\"id\":1,\"privilegeList\":[{\"createTime\":1610681197724,\"updateTime\":1610681197724,\"id\":16,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681197724,\"updateTime\":1610681197724,\"id\":15,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681197724,\"updateTime\":1610681197724,\"id\":14,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681197724,\"updateTime\":1610681197724,\"id\":12,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681197724,\"updateTime\":1610681197724,\"id\":13,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681197724,\"updateTime\":1610681197724,\"id\":11,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681197724,\"updateTime\":1610681197724,\"id\":10,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681197724,\"updateTime\":1610681197724,\"id\":8,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681197724,\"updateTime\":1610681197724,\"id\":9,\"delFlag\":0,\"statusFlag\":0}],\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:26:38');
INSERT INTO `sys_oper_log` VALUES (136, '角色管理', 2, 'com.saas.admin.controller.system.SysRoleController.edit()', 'PUT', 1, '管理员', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"showName\":\"部门管理\",\"updateUserId\":1,\"orderNum\":2,\"updateTime\":1610681206557,\"delFlag\":0,\"privilegeIds\":[16,15,14,12,13,11],\"createTime\":1609326023000,\"updateBy\":\"admin/管理员\",\"name\":\"dept\",\"id\":2,\"privilegeList\":[{\"createTime\":1610681206557,\"updateTime\":1610681206557,\"id\":16,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681206557,\"updateTime\":1610681206557,\"id\":15,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681206557,\"updateTime\":1610681206557,\"id\":14,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681206557,\"updateTime\":1610681206557,\"id\":12,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681206557,\"updateTime\":1610681206557,\"id\":13,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610681206557,\"updateTime\":1610681206557,\"id\":11,\"delFlag\":0,\"statusFlag\":0}],\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:26:47');
INSERT INTO `sys_oper_log` VALUES (137, '个人信息', 2, 'com.saas.admin.controller.system.SysProfileController.updatePwd()', 'PUT', 1, '12345', NULL, '/system/user/profile/updatePwd', '127.0.0.1', '内网IP', '12345 123456', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:37:40');
INSERT INTO `sys_oper_log` VALUES (138, '个人信息', 2, 'com.saas.admin.controller.system.SysProfileController.updateProfile()', 'PUT', 1, '12345', NULL, '/system/user/profile', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":3,\"sex\":0,\"deptId\":2,\"mobile\":\"13545678909\",\"admin\":false,\"updateTime\":1610681868204,\"delFlag\":0,\"roleList\":[],\"roleIds\":[1,2],\"createTime\":1609989954000,\"updateBy\":\"12345/12345\",\"loginName\":\"12345\",\"name\":\"12345\",\"id\":3,\"email\":\"123@qq.com\",\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:37:48');
INSERT INTO `sys_oper_log` VALUES (139, '用户头像', 2, 'com.saas.admin.controller.system.SysProfileController.avatar()', 'POST', 1, '12345', NULL, '/system/user/profile/avatar', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"imgUrl\":\"/profile/avatar/2021/01/15/02296bbf-876c-48e0-88f8-cb274308321e.jpeg\",\"code\":200}', 0, NULL, '2021-01-15 11:38:03');
INSERT INTO `sys_oper_log` VALUES (140, '系统权限', 1, 'com.saas.admin.controller.system.SysPrivilegeController.add()', 'POST', 1, '12345', NULL, '/system/privilege', '127.0.0.1', '内网IP', '{\"createBy\":\"12345/12345\",\"createUserId\":3,\"createTime\":1610681923549,\"updateBy\":\"12345/12345\",\"updateUserId\":3,\"permissions\":\"monitor:cache:list\",\"name\":\"系统监控\",\"updateTime\":1610681923549,\"id\":17,\"delFlag\":0,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:38:44');
INSERT INTO `sys_oper_log` VALUES (141, '系统权限', 2, 'com.saas.admin.controller.system.SysPrivilegeController.edit()', 'PUT', 1, '12345', NULL, '/system/privilege', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"createTime\":1610080301000,\"updateBy\":\"12345/12345\",\"updateUserId\":3,\"permissions\":\"monitor:operlog:query,monitor:operlog:list,monitor:operlog:add,monitor:operlog:edit,monitor:operlog:remove,monitor:operlog:export\",\"name\":\"操作日志记录\",\"updateTime\":1610682201238,\"id\":9,\"delFlag\":0,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:43:21');
INSERT INTO `sys_oper_log` VALUES (142, '系统权限', 2, 'com.saas.admin.controller.system.SysPrivilegeController.edit()', 'PUT', 1, '12345', NULL, '/system/privilege', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"createTime\":1610681060000,\"updateBy\":\"12345/12345\",\"updateUserId\":3,\"permissions\":\"monitor:logininfor:query,monitor:logininfor:list,monitor:logininfor:add,monitor:logininfor:edit,monitor:logininfor:remove,monitor:logininfor:export\",\"name\":\"访问记录\",\"updateTime\":1610682246685,\"id\":12,\"delFlag\":0,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:44:07');
INSERT INTO `sys_oper_log` VALUES (143, '用户管理', 2, 'com.saas.admin.controller.system.SysUserController.resetPwd()', 'PUT', 1, '管理员', NULL, '/system/user/resetPwd', '127.0.0.1', '内网IP', '{\"sex\":0,\"admin\":false,\"updateTime\":1610682295931,\"delFlag\":0,\"roleList\":[],\"createTime\":1610682295931,\"id\":3,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:44:56');
INSERT INTO `sys_oper_log` VALUES (144, '角色管理', 2, 'com.saas.admin.controller.system.SysRoleController.edit()', 'PUT', 1, '管理员', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"showName\":\"admin\",\"updateUserId\":1,\"orderNum\":1,\"updateTime\":1610682655668,\"delFlag\":0,\"privilegeIds\":[8,9,10,11,12,13,14,15,16,17],\"createTime\":1609324250000,\"updateBy\":\"admin/管理员\",\"name\":\"admin\",\"id\":1,\"privilegeList\":[{\"createTime\":1610682655668,\"updateTime\":1610682655668,\"id\":8,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682655668,\"updateTime\":1610682655668,\"id\":9,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682655668,\"updateTime\":1610682655668,\"id\":10,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682655668,\"updateTime\":1610682655668,\"id\":11,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682655668,\"updateTime\":1610682655668,\"id\":12,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682655668,\"updateTime\":1610682655668,\"id\":13,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682655668,\"updateTime\":1610682655668,\"id\":14,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682655668,\"updateTime\":1610682655668,\"id\":15,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682655668,\"updateTime\":1610682655668,\"id\":16,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682655668,\"updateTime\":1610682655668,\"id\":17,\"delFlag\":0,\"statusFlag\":0}],\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:50:56');
INSERT INTO `sys_oper_log` VALUES (145, '系统权限', 2, 'com.saas.admin.controller.system.SysPrivilegeController.edit()', 'PUT', 1, '管理员', NULL, '/system/privilege', '127.0.0.1', '内网IP', '{\"createBy\":\"12345/12345\",\"createUserId\":3,\"createTime\":1610681923000,\"updateBy\":\"admin/管理员\",\"updateUserId\":1,\"permissions\":\"monitor:cache:list,monitor:druid:list,monitor:server:list,monitor:online:list\",\"name\":\"系统监控\",\"updateTime\":1610682750022,\"id\":17,\"delFlag\":0,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:52:30');
INSERT INTO `sys_oper_log` VALUES (146, '系统权限', 1, 'com.saas.admin.controller.system.SysPrivilegeController.add()', 'POST', 1, '管理员', NULL, '/system/privilege', '127.0.0.1', '内网IP', '{\"createBy\":\"admin/管理员\",\"createUserId\":1,\"createTime\":1610682770966,\"updateBy\":\"admin/管理员\",\"updateUserId\":1,\"permissions\":\"tool:build:list,tool:gen:list,tool:swagger:list\",\"name\":\"系统工具\",\"updateTime\":1610682770966,\"id\":18,\"delFlag\":0,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:52:51');
INSERT INTO `sys_oper_log` VALUES (147, '用户管理', 2, 'com.saas.admin.controller.system.SysUserController.edit()', 'PUT', 1, '管理员', NULL, '/system/user', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"sex\":0,\"deptId\":2,\"mobile\":\"13545678909\",\"admin\":false,\"updateTime\":1610682810012,\"avatar\":\"/profile/avatar/2021/01/15/02296bbf-876c-48e0-88f8-cb274308321e.jpeg\",\"delFlag\":0,\"roleList\":[{\"orderNum\":0,\"updateTime\":1610682810010,\"delFlag\":0,\"createTime\":1610682810010,\"id\":1,\"privilegeList\":[],\"statusFlag\":0}],\"password\":\"$2a$10$W3CKl/tIsu2drl4t1A4cEOlFP78wYBNpaATHYzsjue58XxKdzO4DW\",\"roleIds\":[1],\"createTime\":1609989954000,\"updateBy\":\"admin/管理员\",\"loginName\":\"12345\",\"name\":\"12345\",\"id\":3,\"email\":\"123@qq.com\",\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:53:30');
INSERT INTO `sys_oper_log` VALUES (148, '角色管理', 2, 'com.saas.admin.controller.system.SysRoleController.edit()', 'PUT', 1, '管理员', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"showName\":\"admin\",\"updateUserId\":1,\"orderNum\":1,\"updateTime\":1610682870032,\"delFlag\":0,\"privilegeIds\":[8,9,10,11,12,13,14,15,16,17,18],\"createTime\":1609324250000,\"updateBy\":\"admin/管理员\",\"name\":\"admin\",\"id\":1,\"privilegeList\":[{\"createTime\":1610682870031,\"updateTime\":1610682870031,\"id\":8,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682870031,\"updateTime\":1610682870031,\"id\":9,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682870031,\"updateTime\":1610682870031,\"id\":10,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682870032,\"updateTime\":1610682870032,\"id\":11,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682870032,\"updateTime\":1610682870032,\"id\":12,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682870032,\"updateTime\":1610682870032,\"id\":13,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682870032,\"updateTime\":1610682870032,\"id\":14,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682870032,\"updateTime\":1610682870032,\"id\":15,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682870032,\"updateTime\":1610682870032,\"id\":16,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682870032,\"updateTime\":1610682870032,\"id\":17,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1610682870032,\"updateTime\":1610682870032,\"id\":18,\"delFlag\":0,\"statusFlag\":0}],\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 11:54:30');
INSERT INTO `sys_oper_log` VALUES (150, '用户头像', 2, 'com.saas.admin.controller.system.SysProfileController.avatar()', 'POST', 1, '12345', NULL, '/system/user/profile/avatar', '127.0.0.1', '内网IP', '', '{\"msg\":\"操作成功\",\"imgUrl\":\"/profile/avatar/2021/01/15/6973168b-5a62-4d97-a6f9-047308c934de.jpeg\",\"code\":200}', 0, NULL, '2021-01-15 12:04:11');
INSERT INTO `sys_oper_log` VALUES (151, '个人信息', 2, 'com.saas.admin.controller.system.SysProfileController.updatePwd()', 'PUT', 1, '12345', NULL, '/system/user/profile/updatePwd', '127.0.0.1', '内网IP', '123456 1234567', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 12:04:51');
INSERT INTO `sys_oper_log` VALUES (152, '用户管理', 2, 'com.saas.admin.controller.system.SysUserController.resetPwd()', 'PUT', 1, '管理员', NULL, '/system/user/resetPwd', '127.0.0.1', '内网IP', '{\"sex\":0,\"admin\":false,\"updateTime\":1610683529994,\"delFlag\":0,\"roleList\":[],\"createTime\":1610683529994,\"id\":3,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 12:05:30');
INSERT INTO `sys_oper_log` VALUES (153, '系统权限', 2, 'com.saas.admin.controller.system.SysPrivilegeController.edit()', 'PUT', 1, '管理员', NULL, '/system/privilege', '127.0.0.1', '内网IP', '{\"createBy\":\"12345/12345\",\"createUserId\":3,\"createTime\":1610681923000,\"updateBy\":\"admin/管理员\",\"updateUserId\":1,\"permissions\":\"monitor:cache:list,monitor:druid:list,monitor:server:list,monitor:online:list\",\"name\":\"系统监控\",\"updateTime\":1610697479123,\"id\":17,\"delFlag\":0,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 15:57:59');
INSERT INTO `sys_oper_log` VALUES (154, '系统权限', 2, 'com.saas.admin.controller.system.SysPrivilegeController.edit()', 'PUT', 1, '管理员', NULL, '/system/privilege', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"createTime\":1610681100000,\"updateBy\":\"admin/管理员\",\"updateUserId\":1,\"permissions\":\"monitor:cache:list,system:role:query,system:role:list,system:role:add,system:role:edit,system:role:remove,system:role:export\",\"name\":\"角色权限\",\"updateTime\":1610698753334,\"id\":15,\"delFlag\":0,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-15 16:19:13');
INSERT INTO `sys_oper_log` VALUES (155, '系统目录', 2, 'com.saas.admin.controller.system.SysCategoryController.edit()', 'PUT', 1, 'admin', NULL, '/system/category', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"orderNum\":22,\"updateTime\":1611115628308,\"delFlag\":0,\"parentId\":5,\"children\":[],\"createTime\":1610008757000,\"updateBy\":\"admin/管理员\",\"name\":\"233\",\"id\":5,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-20 12:07:08');
INSERT INTO `sys_oper_log` VALUES (156, '系统目录', 2, 'com.saas.admin.controller.system.SysCategoryController.edit()', 'PUT', 1, 'admin', NULL, '/system/category', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"orderNum\":23,\"updateTime\":1611115647069,\"delFlag\":0,\"parentId\":0,\"children\":[],\"createTime\":1610081045000,\"updateBy\":\"admin/管理员\",\"name\":\"4566\",\"id\":7,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-20 12:07:27');
INSERT INTO `sys_oper_log` VALUES (157, '系统目录', 2, 'com.saas.system.controller.SysCategoryController.edit()', 'PUT', 1, 'admin', NULL, '/category', '127.0.0.1,127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"orderNum\":23,\"updateTime\":1611127632632,\"delFlag\":0,\"parentId\":0,\"children\":[],\"createTime\":1610081045000,\"updateBy\":\"admin/管理员\",\"name\":\"4566\",\"id\":7,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-20 15:27:13');
INSERT INTO `sys_oper_log` VALUES (158, '用户管理', 2, 'com.saas.system.controller.SysUserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/user/changeStatus', '127.0.0.1,127.0.0.1', '内网IP', '{\"sex\":0,\"admin\":false,\"updateTime\":1611127684241,\"delFlag\":0,\"roleList\":[],\"createTime\":1611127684241,\"id\":3,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-20 15:28:04');
INSERT INTO `sys_oper_log` VALUES (159, '用户管理', 2, 'com.saas.system.controller.SysUserController.changeStatus()', 'PUT', 1, 'admin', NULL, '/user/changeStatus', '127.0.0.1,127.0.0.1', '内网IP', '{\"sex\":0,\"admin\":false,\"updateTime\":1611127687449,\"delFlag\":0,\"roleList\":[],\"createTime\":1611127687449,\"id\":3,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-20 15:28:07');
INSERT INTO `sys_oper_log` VALUES (160, '用户管理', 2, 'com.saas.system.controller.SysUserController.edit()', 'PUT', 1, '12345', NULL, '/user', '127.0.0.1,127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":3,\"sex\":1,\"deptId\":2,\"mobile\":\"13545678909\",\"admin\":false,\"updateTime\":1611127786291,\"avatar\":\"/profile/avatar/2021/01/15/6973168b-5a62-4d97-a6f9-047308c934de.jpeg\",\"delFlag\":0,\"roleList\":[{\"orderNum\":0,\"updateTime\":1611127786275,\"delFlag\":0,\"createTime\":1611127786275,\"id\":2,\"privilegeList\":[],\"statusFlag\":0}],\"password\":\"$2a$10$KMCbfHM5xZ8YUIi6nQ5xMuanoiHXKTpCnwKoN7Te2bpudQ/Avf29q\",\"roleIds\":[2],\"createTime\":1609989954000,\"updateBy\":\"12345/12345\",\"loginName\":\"12345\",\"name\":\"12345\",\"id\":3,\"email\":\"123@qq.com\",\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-20 15:29:46');
INSERT INTO `sys_oper_log` VALUES (161, '系统权限', 2, 'com.saas.system.controller.SysPrivilegeController.edit()', 'PUT', 1, 'admin', NULL, '/privilege', '127.0.0.1,127.0.0.1', '内网IP', '{\"createBy\":\"admin/管理员\",\"createUserId\":1,\"createTime\":1610682770000,\"updateBy\":\"admin/管理员\",\"updateUserId\":1,\"permissions\":\"tool:build:list,tool:gen:list,tool:swagger:list\",\"name\":\"系统工具\",\"updateTime\":1611130102268,\"id\":18,\"delFlag\":0,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-20 16:08:22');
INSERT INTO `sys_oper_log` VALUES (162, '系统权限', 2, 'com.saas.system.controller.SysPrivilegeController.edit()', 'PUT', 1, '12345', NULL, '/privilege', '127.0.0.1,127.0.0.1', '内网IP', '{\"createBy\":\"admin/管理员\",\"createUserId\":1,\"createTime\":1610682770000,\"updateBy\":\"admin/管理员\",\"updateUserId\":1,\"permissions\":\"tool:build:list,tool:gen:list,tool:swagger:list\",\"name\":\"系统工具\",\"updateTime\":1611130102000,\"id\":18,\"delFlag\":0,\"statusFlag\":0}', 'null', 1, NULL, '2021-01-20 16:08:36');
INSERT INTO `sys_oper_log` VALUES (163, '在线用户', 7, 'com.saas.system.controller.SysUserOnlineController.forceLogout()', 'DELETE', 1, 'admin', NULL, '/monitor/online/bcaf605a-2ce4-4b2e-8954-8bc830f13ae0', '127.0.0.1,127.0.0.1', '内网IP', '{tokenId=bcaf605a-2ce4-4b2e-8954-8bc830f13ae0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-20 16:11:04');
INSERT INTO `sys_oper_log` VALUES (164, '代码生成', 6, 'com.saas.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'sys_saas_account,wx_auth_account', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 15:40:35');
INSERT INTO `sys_oper_log` VALUES (165, '代码生成', 2, 'com.saas.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"functionAuthor\":\"bruce\",\"businessName\":\"account\",\"moduleName\":\"system\",\"className\":\"SysSaasAccount\",\"delFlag\":0,\"tableName\":\"sys_saas_account\",\"updateBy\":\"admin/管理员\",\"options\":\"{\\\"parentMenuId\\\":1}\",\"id\":21,\"packageName\":\"com.saas.system\",\"functionName\":\"SAAS账号\",\"updateUserId\":1,\"tableComment\":\"SAAS账号\",\"updateTime\":1611215044091,\"tplCategory\":\"crud\",\"createBy\":\"admin/管理员\",\"parentMenuId\":1,\"createTime\":1611214834000,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 15:44:04');
INSERT INTO `sys_oper_log` VALUES (166, '代码生成', 2, 'com.saas.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"functionAuthor\":\"bruce\",\"businessName\":\"account\",\"moduleName\":\"system\",\"className\":\"WxAuthAccount\",\"delFlag\":0,\"tableName\":\"wx_auth_account\",\"updateBy\":\"admin/管理员\",\"options\":\"{\\\"parentMenuId\\\":1}\",\"id\":22,\"packageName\":\"com.saas.system\",\"functionName\":\"微信授权账号信息\",\"updateUserId\":1,\"tableComment\":\"微信授权账号信息\",\"updateTime\":1611215126154,\"tplCategory\":\"crud\",\"createBy\":\"admin/管理员\",\"parentMenuId\":1,\"createTime\":1611214834000,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 15:45:26');
INSERT INTO `sys_oper_log` VALUES (167, '菜单管理', 1, 'com.saas.admin.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"wechat\",\"orderNum\":6,\"menuName\":\"微信账号\",\"updateTime\":1611215324816,\"delFlag\":0,\"parentId\":0,\"path\":\"/weixin\",\"createBy\":\"admin/管理员\",\"createTime\":1611215324816,\"updateBy\":\"admin/管理员\",\"menuType\":\"M\",\"id\":1065,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 15:48:45');
INSERT INTO `sys_oper_log` VALUES (168, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"wechat\",\"orderNum\":5,\"menuName\":\"微信账号\",\"updateTime\":1611215341306,\"delFlag\":0,\"parentId\":0,\"path\":\"weixin\",\"createBy\":\"admin/管理员\",\"createTime\":1611215325000,\"updateBy\":\"admin/管理员\",\"menuType\":\"M\",\"id\":1065,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 15:49:01');
INSERT INTO `sys_oper_log` VALUES (169, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"guide\",\"orderNum\":6,\"menuName\":\"官网\",\"updateTime\":1611215350384,\"delFlag\":0,\"parentId\":0,\"path\":\"http://www.qz-z.cn\",\"createTime\":1609217654000,\"updateBy\":\"admin/管理员\",\"menuType\":\"M\",\"perms\":\"\",\"id\":4,\"frame\":true,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 15:49:10');
INSERT INTO `sys_oper_log` VALUES (170, '代码生成', 8, 'com.saas.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2021-01-21 15:49:28');
INSERT INTO `sys_oper_log` VALUES (171, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"nested\",\"orderNum\":1,\"menuName\":\"SAAS账号\",\"updateTime\":1611216012510,\"delFlag\":0,\"parentId\":1,\"path\":\"account\",\"component\":\"system/account/index\",\"createTime\":1611215754000,\"updateBy\":\"admin/管理员\",\"menuType\":\"C\",\"perms\":\"system:account:list\",\"id\":1066,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 16:00:13');
INSERT INTO `sys_oper_log` VALUES (172, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"nested\",\"orderNum\":1,\"menuName\":\"SAAS账号\",\"updateTime\":1611216035564,\"delFlag\":0,\"parentId\":1,\"path\":\"account\",\"component\":\"system/saasaccount/index\",\"createTime\":1611215754000,\"updateBy\":\"admin/管理员\",\"menuType\":\"C\",\"perms\":\"system:account:list\",\"id\":1066,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 16:00:36');
INSERT INTO `sys_oper_log` VALUES (173, 'SAAS账号', 1, 'com.saas.system.controller.SysSaasAccountController.add()', 'POST', 1, 'admin', NULL, '/system/account', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"contactName\":\"张三\",\"mobile\":\"18600982716\",\"updateTime\":1611216222959,\"delFlag\":0,\"createBy\":\"admin/管理员\",\"createTime\":1611216222959,\"updateBy\":\"admin/管理员\",\"name\":\"智联招聘\",\"id\":1,\"longName\":\"北京智联招聘有限公司\",\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 16:03:43');
INSERT INTO `sys_oper_log` VALUES (174, 'SAAS账号', 2, 'com.saas.system.controller.SysSaasAccountController.edit()', 'PUT', 1, 'admin', NULL, '/system/account', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"contactName\":\"张三\",\"mobile\":\"18600982716\",\"updateTime\":1611216537266,\"delFlag\":0,\"createBy\":\"admin/管理员\",\"createTime\":1611216223000,\"updateBy\":\"admin/管理员\",\"name\":\"智联招聘\",\"id\":1,\"longName\":\"北京智联招聘有限公司\",\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 16:08:57');
INSERT INTO `sys_oper_log` VALUES (175, 'SAAS账号', 2, 'com.saas.system.controller.SysSaasAccountController.edit()', 'PUT', 1, 'admin', NULL, '/system/account', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"contactName\":\"张三\",\"mobile\":\"18600982716\",\"updateTime\":1611216550658,\"delFlag\":0,\"createBy\":\"admin/管理员\",\"createTime\":1611216223000,\"updateBy\":\"admin/管理员\",\"name\":\"智联招聘\",\"id\":1,\"longName\":\"北京智联招聘有限公司\",\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 16:09:11');
INSERT INTO `sys_oper_log` VALUES (176, 'SAAS账号', 1, 'com.saas.system.controller.SysSaasAccountController.add()', 'POST', 1, 'admin', NULL, '/system/account', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"contactName\":\"里斯\",\"mobile\":\"18600098765\",\"updateTime\":1611216619740,\"delFlag\":0,\"createBy\":\"admin/管理员\",\"createTime\":1611216619740,\"updateBy\":\"admin/管理员\",\"name\":\"开心网\",\"id\":2,\"longName\":\"北京开心网科技有限公司\",\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 16:10:20');
INSERT INTO `sys_oper_log` VALUES (177, 'SAAS账号', 2, 'com.saas.system.controller.SysSaasAccountController.edit()', 'PUT', 1, 'admin', NULL, '/system/account', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"updateUserId\":1,\"contactName\":\"里斯\",\"mobile\":\"18600098761\",\"updateTime\":1611218222154,\"delFlag\":0,\"createBy\":\"admin/管理员\",\"createTime\":1611216620000,\"updateBy\":\"admin/管理员\",\"name\":\"开心网\",\"id\":2,\"longName\":\"北京开心网科技有限公司\",\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 16:37:02');
INSERT INTO `sys_oper_log` VALUES (178, '代码生成', 2, 'com.saas.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"functionAuthor\":\"bruce\",\"businessName\":\"account\",\"moduleName\":\"system\",\"className\":\"WxAuthAccount\",\"delFlag\":0,\"tableName\":\"wx_auth_account\",\"updateBy\":\"admin/管理员\",\"options\":\"{\\\"parentMenuId\\\":1065}\",\"id\":22,\"packageName\":\"com.saas.system\",\"functionName\":\"微信授权账号信息\",\"updateUserId\":1,\"tableComment\":\"微信授权账号信息\",\"updateTime\":1611219179148,\"tplCategory\":\"crud\",\"createBy\":\"admin/管理员\",\"parentMenuId\":1065,\"createTime\":1611214834000,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-21 16:52:59');
INSERT INTO `sys_oper_log` VALUES (179, '代码生成', 6, 'com.saas.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/tool/gen/importTable', '127.0.0.1', '内网IP', 'ft_user_account,ft_user_device,ft_user_sns', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-25 15:33:54');
INSERT INTO `sys_oper_log` VALUES (180, '代码生成', 2, 'com.saas.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"functionAuthor\":\"bruce\",\"businessName\":\"account\",\"moduleName\":\"system\",\"className\":\"FtUserAccount\",\"delFlag\":0,\"tableName\":\"ft_user_account\",\"updateBy\":\"admin/管理员\",\"options\":\"{\\\"parentMenuId\\\":1065}\",\"id\":23,\"packageName\":\"com.saas.system\",\"functionName\":\"前端用户\",\"updateUserId\":1,\"tableComment\":\"会员信息\",\"updateTime\":1611560326146,\"tplCategory\":\"crud\",\"createBy\":\"admin/管理员\",\"parentMenuId\":1065,\"createTime\":1611560033000,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-25 15:38:46');
INSERT INTO `sys_oper_log` VALUES (181, '菜单管理', 1, 'com.saas.admin.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"peoples\",\"orderNum\":6,\"menuName\":\"用户中心\",\"updateTime\":1611560378216,\"delFlag\":0,\"parentId\":0,\"path\":\"usercenter\",\"createBy\":\"admin/管理员\",\"createTime\":1611560378216,\"updateBy\":\"admin/管理员\",\"menuType\":\"M\",\"id\":1067,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-25 15:39:38');
INSERT INTO `sys_oper_log` VALUES (182, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"guide\",\"orderNum\":10,\"menuName\":\"官网\",\"updateTime\":1611560387016,\"delFlag\":0,\"parentId\":0,\"path\":\"http://www.qz-z.cn\",\"createTime\":1609217654000,\"updateBy\":\"admin/管理员\",\"menuType\":\"M\",\"perms\":\"\",\"id\":4,\"frame\":true,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-25 15:39:47');
INSERT INTO `sys_oper_log` VALUES (183, '代码生成', 2, 'com.saas.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"functionAuthor\":\"bruce\",\"businessName\":\"account\",\"moduleName\":\"system\",\"className\":\"FtUserAccount\",\"delFlag\":0,\"tableName\":\"ft_user_account\",\"updateBy\":\"admin/管理员\",\"options\":\"{\\\"parentMenuId\\\":1067}\",\"id\":23,\"packageName\":\"com.saas.system\",\"functionName\":\"前端用户\",\"updateUserId\":1,\"tableComment\":\"会员信息\",\"updateTime\":1611560404696,\"tplCategory\":\"crud\",\"createBy\":\"admin/管理员\",\"parentMenuId\":1067,\"createTime\":1611560033000,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-25 15:40:05');
INSERT INTO `sys_oper_log` VALUES (184, '代码生成', 2, 'com.saas.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"functionAuthor\":\"bruce\",\"businessName\":\"device\",\"moduleName\":\"system\",\"className\":\"FtUserDevice\",\"delFlag\":0,\"tableName\":\"ft_user_device\",\"updateBy\":\"admin/管理员\",\"options\":\"{\\\"parentMenuId\\\":1067}\",\"id\":24,\"packageName\":\"com.saas.system\",\"functionName\":\"用户设备信息\",\"updateUserId\":1,\"tableComment\":\"设备信息\",\"updateTime\":1611560437008,\"tplCategory\":\"crud\",\"createBy\":\"admin/管理员\",\"parentMenuId\":1067,\"createTime\":1611560033000,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-25 15:40:37');
INSERT INTO `sys_oper_log` VALUES (185, '代码生成', 2, 'com.saas.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/tool/gen', '127.0.0.1', '内网IP', '{\"createUserId\":1,\"functionAuthor\":\"bruce\",\"businessName\":\"sns\",\"moduleName\":\"system\",\"className\":\"FtUserSns\",\"delFlag\":0,\"tableName\":\"ft_user_sns\",\"updateBy\":\"admin/管理员\",\"options\":\"{\\\"parentMenuId\\\":1067}\",\"id\":25,\"packageName\":\"com.saas.system\",\"functionName\":\"社交账号\",\"updateUserId\":1,\"tableComment\":\"社交账号\",\"updateTime\":1611560455236,\"tplCategory\":\"crud\",\"createBy\":\"admin/管理员\",\"parentMenuId\":1067,\"createTime\":1611560033000,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-25 15:40:55');
INSERT INTO `sys_oper_log` VALUES (186, '代码生成', 6, 'com.saas.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', NULL, '/gen/importTable', '127.0.0.1,127.0.0.1', '内网IP', 'wx_article,wx_media,wx_menu,wx_reply,wx_template', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-29 13:00:00');
INSERT INTO `sys_oper_log` VALUES (187, '代码生成', 2, 'com.saas.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/gen', '127.0.0.1,127.0.0.1', '内网IP', '{\"createUserId\":1,\"functionAuthor\":\"bruce\",\"businessName\":\"article\",\"moduleName\":\"wx\",\"className\":\"WxArticle\",\"delFlag\":0,\"tableName\":\"wx_article\",\"updateBy\":\"admin/管理员\",\"options\":\"{\\\"parentMenuId\\\":1065}\",\"id\":26,\"packageName\":\"com.saas.wx\",\"functionName\":\"图文素材\",\"updateUserId\":1,\"tableComment\":\"图文素材\",\"updateTime\":1611896477666,\"tplCategory\":\"crud\",\"createBy\":\"admin/管理员\",\"parentMenuId\":1065,\"createTime\":1611896399000,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-29 13:01:18');
INSERT INTO `sys_oper_log` VALUES (188, '代码生成', 2, 'com.saas.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', NULL, '/gen', '127.0.0.1,127.0.0.1', '内网IP', '{\"createUserId\":1,\"functionAuthor\":\"bruce\",\"businessName\":\"media\",\"moduleName\":\"wx\",\"className\":\"WxMedia\",\"delFlag\":0,\"tableName\":\"wx_media\",\"updateBy\":\"admin/管理员\",\"options\":\"{\\\"parentMenuId\\\":1065}\",\"id\":27,\"packageName\":\"com.saas.wx\",\"functionName\":\"媒体素材\",\"updateUserId\":1,\"tableComment\":\"媒体素材\",\"updateTime\":1611899308821,\"tplCategory\":\"crud\",\"createBy\":\"admin/管理员\",\"parentMenuId\":1065,\"createTime\":1611896399000,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-29 13:48:29');
INSERT INTO `sys_oper_log` VALUES (189, '代码生成', 8, 'com.saas.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', NULL, '/gen/batchGenCode', '127.0.0.1,127.0.0.1', '内网IP', '{}', 'null', 0, NULL, '2021-01-29 13:52:05');
INSERT INTO `sys_oper_log` VALUES (190, '系统权限', 2, 'com.saas.system.controller.SysPrivilegeController.edit()', 'PUT', 1, 'admin', NULL, '/privilege', '127.0.0.1,127.0.0.1', '内网IP', '{\"createUserId\":1,\"createTime\":1611215754000,\"updateBy\":\"admin/管理员\",\"updateUserId\":1,\"permissions\":\"system:account:query,system:account:list,system:account:add,system:account:edit,system:account:remove,system:account:export\",\"name\":\"SAAS账号权限\",\"updateTime\":1611912984196,\"id\":19,\"delFlag\":0,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-01-29 17:36:24');
INSERT INTO `sys_oper_log` VALUES (191, '菜单管理', 1, 'com.saas.admin.controller.system.SysMenuController.add()', 'POST', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"dashboard\",\"orderNum\":10,\"menuName\":\"账号配置\",\"updateTime\":1612422161673,\"delFlag\":0,\"parentId\":1065,\"path\":\"wechat/account\",\"createBy\":\"admin/管理员\",\"createTime\":1612422161673,\"updateBy\":\"admin/管理员\",\"menuType\":\"M\",\"id\":1068,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-04 15:02:42');
INSERT INTO `sys_oper_log` VALUES (192, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"wechat\",\"orderNum\":5,\"menuName\":\"微信配置\",\"updateTime\":1612422199159,\"delFlag\":0,\"parentId\":0,\"path\":\"weixin\",\"createBy\":\"admin/管理员\",\"createTime\":1611215325000,\"updateBy\":\"admin/管理员\",\"menuType\":\"M\",\"id\":1065,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-04 15:03:19');
INSERT INTO `sys_oper_log` VALUES (193, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"dashboard\",\"orderNum\":10,\"menuName\":\"账号配置\",\"updateTime\":1612422220471,\"delFlag\":0,\"parentId\":1065,\"path\":\"wechat/account/index\",\"createBy\":\"admin/管理员\",\"createTime\":1612422162000,\"updateBy\":\"admin/管理员\",\"menuType\":\"C\",\"id\":1068,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-04 15:03:40');
INSERT INTO `sys_oper_log` VALUES (194, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"dashboard\",\"orderNum\":10,\"menuName\":\"账号配置\",\"updateTime\":1612422263835,\"delFlag\":0,\"parentId\":1065,\"path\":\"wechat\",\"component\":\"wechat/account/index\",\"createBy\":\"admin/管理员\",\"createTime\":1612422162000,\"updateBy\":\"admin/管理员\",\"menuType\":\"C\",\"perms\":\"wechat:account:list\",\"id\":1068,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-04 15:04:24');
INSERT INTO `sys_oper_log` VALUES (195, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"dashboard\",\"orderNum\":10,\"menuName\":\"账号配置\",\"updateTime\":1612422322829,\"delFlag\":0,\"parentId\":1065,\"path\":\"authaccount\",\"component\":\"wechat/authaccount/index\",\"createBy\":\"admin/管理员\",\"createTime\":1612422162000,\"updateBy\":\"admin/管理员\",\"menuType\":\"C\",\"perms\":\"wechat:account:list\",\"id\":1068,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-04 15:05:23');
INSERT INTO `sys_oper_log` VALUES (196, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"dashboard\",\"orderNum\":10,\"menuName\":\"账号配置\",\"updateTime\":1612422334869,\"delFlag\":0,\"parentId\":1065,\"path\":\"authaccount\",\"component\":\"wechat/authaccount/index\",\"createBy\":\"admin/管理员\",\"createTime\":1612422162000,\"updateBy\":\"admin/管理员\",\"menuType\":\"C\",\"perms\":\"wechat:authaccount:list\",\"id\":1068,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-04 15:05:35');
INSERT INTO `sys_oper_log` VALUES (197, '系统权限', 1, 'com.saas.admin.controller.system.SysPrivilegeController.add()', 'POST', 1, 'admin', NULL, '/system/privilege', '127.0.0.1', '内网IP', '{\"createBy\":\"admin/管理员\",\"createUserId\":1,\"createTime\":1612422480119,\"updateBy\":\"admin/管理员\",\"updateUserId\":1,\"permissions\":\"wechat:authaccount:list,wechat:authaccount:edit,wechat:authaccount:query,wechat:authaccount:add,wechat:authaccount:remove,wechat:authaccount:export\",\"name\":\"微信账号配置\",\"updateTime\":1612422480119,\"id\":20,\"delFlag\":0,\"statusFlag\":0}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-04 15:08:00');
INSERT INTO `sys_oper_log` VALUES (198, '角色管理', 2, 'com.saas.admin.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', NULL, '/system/role', '127.0.0.1', '内网IP', '{\"showName\":\"admin\",\"updateUserId\":1,\"orderNum\":1,\"updateTime\":1612422491775,\"delFlag\":0,\"privilegeIds\":[8,9,10,11,12,13,14,15,16,17,18,19,20],\"createTime\":1609324250000,\"updateBy\":\"admin/管理员\",\"name\":\"admin\",\"id\":1,\"privilegeList\":[{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":8,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":9,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":10,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":11,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":12,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":13,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":14,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":15,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":16,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":17,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":18,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":19,\"delFlag\":0,\"statusFlag\":0},{\"createTime\":1612422491773,\"updateTime\":1612422491773,\"id\":20,\"delFlag\":0,\"statusFlag\":0}],\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-04 15:08:12');
INSERT INTO `sys_oper_log` VALUES (199, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"dashboard\",\"orderNum\":100,\"menuName\":\"账号配置\",\"updateTime\":1612422901240,\"delFlag\":0,\"parentId\":1065,\"path\":\"authaccount\",\"component\":\"wechat/authaccount/index\",\"createBy\":\"admin/管理员\",\"createTime\":1612422162000,\"updateBy\":\"admin/管理员\",\"menuType\":\"C\",\"perms\":\"wechat:authaccount:list\",\"id\":1068,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-04 15:15:01');
INSERT INTO `sys_oper_log` VALUES (200, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"wechat\",\"orderNum\":5,\"menuName\":\"微信配置\",\"updateTime\":1612424365651,\"delFlag\":0,\"parentId\":0,\"path\":\"wechat\",\"createBy\":\"admin/管理员\",\"createTime\":1611215325000,\"updateBy\":\"admin/管理员\",\"menuType\":\"M\",\"id\":1065,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-04 15:39:26');
INSERT INTO `sys_oper_log` VALUES (201, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"dashboard\",\"orderNum\":100,\"menuName\":\"账号配置\",\"updateTime\":1612424380420,\"delFlag\":0,\"parentId\":1065,\"path\":\"authaccount\",\"component\":\"wechat/authaccount/index\",\"createBy\":\"admin/管理员\",\"createTime\":1612422162000,\"updateBy\":\"admin/管理员\",\"menuType\":\"C\",\"perms\":\"wechat:authaccount:list\",\"id\":1068,\"frame\":false,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-04 15:39:40');
INSERT INTO `sys_oper_log` VALUES (202, '菜单管理', 2, 'com.saas.admin.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', NULL, '/system/menu', '127.0.0.1', '内网IP', '{\"cache\":true,\"createUserId\":1,\"visible\":true,\"updateUserId\":1,\"icon\":\"guide\",\"orderNum\":10,\"menuName\":\"官网\",\"updateTime\":1612493212561,\"delFlag\":0,\"parentId\":0,\"path\":\"http://www.qz-z.com\",\"createTime\":1609217654000,\"updateBy\":\"admin/管理员\",\"menuType\":\"M\",\"perms\":\"\",\"id\":4,\"frame\":true,\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-05 10:46:53');
INSERT INTO `sys_oper_log` VALUES (203, '部门管理', 2, 'com.saas.system.controller.SysDeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1,127.0.0.1', '内网IP', '{\"deptName\":\"运营部\",\"leader\":\"雨伞\",\"createUserId\":1,\"orderNum\":\"4\",\"updateTime\":1609303911000,\"delFlag\":0,\"parentId\":1,\"children\":[],\"createTime\":1609303911000,\"phone\":\"19820394762\",\"id\":4,\"email\":\"11@11.com\",\"statusFlag\":1}', 'null', 1, 'Expression [hasPermi(\'system:dept:edit\'+${dept.deptName}] @29: EL1043E: Unexpected token. Expected \'rparen())\' but was \'lcurly({)\'', '2021-02-25 14:59:12');
INSERT INTO `sys_oper_log` VALUES (204, '部门管理', 2, 'com.saas.system.controller.SysDeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1,127.0.0.1', '内网IP', '{\"deptName\":\"运营部\",\"leader\":\"雨伞\",\"createUserId\":1,\"orderNum\":\"4\",\"updateTime\":1609303911000,\"delFlag\":0,\"parentId\":1,\"children\":[],\"createTime\":1609303911000,\"phone\":\"19820394762\",\"id\":4,\"email\":\"11@11.com\",\"statusFlag\":1}', 'null', 1, 'Expression [hasPermi(\'system:dept:edit\'+${dept.deptName})] @29: EL1043E: Unexpected token. Expected \'rparen())\' but was \'lcurly({)\'', '2021-02-25 15:02:23');
INSERT INTO `sys_oper_log` VALUES (205, '部门管理', 2, 'com.saas.system.controller.SysDeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1,127.0.0.1', '内网IP', '{\"deptName\":\"运营部\",\"leader\":\"雨伞\",\"createUserId\":1,\"orderNum\":\"4\",\"updateTime\":1609303911000,\"delFlag\":0,\"parentId\":1,\"children\":[],\"createTime\":1609303911000,\"phone\":\"19820394762\",\"id\":4,\"email\":\"11@11.com\",\"statusFlag\":1}', 'null', 1, 'Expression [hasPermi(${dept.deptName})] @10: EL1043E: Unexpected token. Expected \'rparen())\' but was \'lcurly({)\'', '2021-02-25 15:03:50');
INSERT INTO `sys_oper_log` VALUES (206, '部门管理', 2, 'com.saas.system.controller.SysDeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1,127.0.0.1', '内网IP', '{\"deptName\":\"运营部\",\"leader\":\"雨伞\",\"createUserId\":1,\"orderNum\":\"4\",\"updateTime\":1609303911000,\"delFlag\":0,\"parentId\":1,\"children\":[],\"createTime\":1609303911000,\"phone\":\"19820394762\",\"id\":4,\"email\":\"11@11.com\",\"statusFlag\":1}', 'null', 1, 'Expression [hasPermi(#{dept.deptName})] @10: EL1043E: Unexpected token. Expected \'identifier\' but was \'lcurly({)\'', '2021-02-25 15:06:31');
INSERT INTO `sys_oper_log` VALUES (207, '部门管理', 2, 'com.saas.system.controller.SysDeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1,127.0.0.1', '内网IP', '{\"deptName\":\"运营部\",\"leader\":\"雨伞\",\"createUserId\":1,\"updateUserId\":1,\"orderNum\":\"4\",\"updateTime\":1614237314309,\"delFlag\":0,\"parentId\":1,\"children\":[],\"createTime\":1609303911000,\"phone\":\"19820394762\",\"updateBy\":\"admin/管理员\",\"id\":4,\"email\":\"11@11.com\",\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 15:15:15');
INSERT INTO `sys_oper_log` VALUES (208, '部门管理', 2, 'com.saas.system.controller.SysDeptController.edit()', 'PUT', 1, 'admin', NULL, '/dept', '127.0.0.1,127.0.0.1', '内网IP', '{\"deptName\":\"运营部\",\"leader\":\"雨伞\",\"createUserId\":1,\"updateUserId\":1,\"orderNum\":\"4\",\"updateTime\":1614237356922,\"delFlag\":0,\"parentId\":1,\"children\":[],\"createTime\":1609303911000,\"phone\":\"19820394762\",\"updateBy\":\"admin/管理员\",\"id\":4,\"email\":\"11@11.com\",\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 15:15:57');
INSERT INTO `sys_oper_log` VALUES (209, '部门管理', 1, 'com.saas.system.controller.SysDeptController.add()', 'POST', 1, 'admin', NULL, '/dept', '127.0.0.1,127.0.0.1', '内网IP', '{\"deptName\":\"市场部\",\"leader\":\"例三\",\"createUserId\":1,\"updateUserId\":1,\"orderNum\":\"6\",\"updateTime\":1614237403568,\"delFlag\":0,\"parentId\":1,\"createBy\":\"admin/管理员\",\"children\":[],\"createTime\":1614237403568,\"phone\":\"18700098761\",\"updateBy\":\"admin/管理员\",\"id\":5,\"email\":\"123@qw.com\",\"statusFlag\":1}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2021-02-25 15:16:44');
COMMIT;

-- ----------------------------
-- Table structure for sys_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态',
  `update_time` datetime(6) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `permissions` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限字符串用逗号隔开',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='权限表';

-- ----------------------------
-- Records of sys_privilege
-- ----------------------------
BEGIN;
INSERT INTO `sys_privilege` VALUES (8, '2021-01-07 15:29:08.000000', 1, 0, 1, '2021-01-15 11:22:17.471000', 1, '系统目录', 'system:category:query,system:category:list,system:category:add,system:category:edit,system:category:remove,system:category:export', NULL, 'admin/管理员');
INSERT INTO `sys_privilege` VALUES (9, '2021-01-08 12:31:41.000000', 1, 0, 1, '2021-01-15 11:43:21.238000', 3, '操作日志记录', 'monitor:operlog:query,monitor:operlog:list,monitor:operlog:add,monitor:operlog:edit,monitor:operlog:remove,monitor:operlog:export', NULL, '12345/12345');
INSERT INTO `sys_privilege` VALUES (10, '2021-01-15 11:23:20.000000', 1, 0, 1, NULL, 0, '部门', 'system:dept:query,system:dept:list,system:dept:add,system:dept:edit,system:dept:remove,system:dept:export', NULL, NULL);
INSERT INTO `sys_privilege` VALUES (11, '2021-01-15 11:23:52.000000', 1, 0, 1, NULL, 0, '参数配置', 'system:config:query,system:config:list,system:config:add,system:config:edit,system:config:remove,system:config:export', NULL, NULL);
INSERT INTO `sys_privilege` VALUES (12, '2021-01-15 11:24:20.000000', 1, 0, 1, '2021-01-15 11:44:06.685000', 3, '访问记录', 'monitor:logininfor:query,monitor:logininfor:list,monitor:logininfor:add,monitor:logininfor:edit,monitor:logininfor:remove,monitor:logininfor:export', NULL, '12345/12345');
INSERT INTO `sys_privilege` VALUES (13, '2021-01-15 11:24:33.000000', 1, 0, 1, NULL, 0, '菜单权限权限', 'system:menu:query,system:menu:list,system:menu:add,system:menu:edit,system:menu:remove,system:menu:export', NULL, NULL);
INSERT INTO `sys_privilege` VALUES (14, '2021-01-15 11:24:48.000000', 1, 0, 1, '2021-01-15 11:26:00.750000', 1, '权限配置', 'system:privilege:query,system:privilege:list,system:privilege:add,system:privilege:edit,system:privilege:remove,system:privilege:export', NULL, 'admin/管理员');
INSERT INTO `sys_privilege` VALUES (15, '2021-01-15 11:25:00.000000', 1, 0, 1, '2021-01-15 16:19:13.334000', 1, '角色权限', 'monitor:cache:list,system:role:query,system:role:list,system:role:add,system:role:edit,system:role:remove,system:role:export', NULL, 'admin/管理员');
INSERT INTO `sys_privilege` VALUES (16, '2021-01-15 11:25:20.000000', 1, 0, 1, NULL, 0, '用户权限', 'system:user:query,system:user:list,system:user:add,system:user:edit,system:user:remove,system:user:export', NULL, NULL);
INSERT INTO `sys_privilege` VALUES (17, '2021-01-15 11:38:43.000000', 3, 0, 0, '2021-01-15 15:57:59.123000', 1, '系统监控', 'monitor:cache:list,monitor:druid:list,monitor:server:list,monitor:online:list', '12345/12345', 'admin/管理员');
INSERT INTO `sys_privilege` VALUES (18, '2021-01-15 11:52:50.000000', 1, 0, 0, '2021-01-20 16:08:22.268000', 1, '系统工具', 'tool:build:list,tool:gen:list,tool:swagger:list', 'admin/管理员', 'admin/管理员');
INSERT INTO `sys_privilege` VALUES (19, '2021-01-21 15:55:54.000000', 1, 0, 1, '2021-01-29 17:36:24.196000', 1, 'SAAS账号权限', 'system:account:query,system:account:list,system:account:add,system:account:edit,system:account:remove,system:account:export', NULL, 'admin/管理员');
INSERT INTO `sys_privilege` VALUES (20, '2021-02-04 15:08:00.119000', 1, 0, 0, '2021-02-04 15:08:00.119000', 1, '微信账号配置', 'wechat:authaccount:list,wechat:authaccount:edit,wechat:authaccount:query,wechat:authaccount:add,wechat:authaccount:remove,wechat:authaccount:export', 'admin/管理员', 'admin/管理员');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL COMMENT '软删除',
  `status_flag` int(11) DEFAULT NULL COMMENT '状态',
  `update_time` datetime(6) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `show_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '显示名称',
  `order_num` int(11) NOT NULL COMMENT '排序',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bqy406dtsr7j7d6fawi1ckyn1` (`name`),
  UNIQUE KEY `UK_pj7gudya1ckehxcisy2l0vg82` (`show_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '2020-12-30 18:30:50.000000', NULL, 0, 1, '2021-02-04 15:08:11.775000', 1, 'admin', 'admin', 1, NULL, 'admin/管理员');
INSERT INTO `sys_role` VALUES (2, '2020-12-30 19:00:23.000000', 1, 0, 1, '2021-01-15 11:26:46.557000', 1, 'dept', '部门管理', 2, NULL, 'admin/管理员');
INSERT INTO `sys_role` VALUES (3, '2020-12-31 11:39:49.173000', 1, 1, 1, '2020-12-31 11:40:10.991000', 1, 'finance', '财务人员', 3, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_privilege`;
CREATE TABLE `sys_role_privilege` (
  `role_id` bigint(20) NOT NULL,
  `privilege_id` bigint(20) NOT NULL,
  KEY `FKvf9lt66q9oyjludcg83nfinq` (`privilege_id`),
  KEY `FKr3sw06rqbrufgrkfq2c8dxhvb` (`role_id`),
  CONSTRAINT `FKr3sw06rqbrufgrkfq2c8dxhvb` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FKvf9lt66q9oyjludcg83nfinq` FOREIGN KEY (`privilege_id`) REFERENCES `sys_privilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_role_privilege
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_privilege` VALUES (2, 16);
INSERT INTO `sys_role_privilege` VALUES (2, 15);
INSERT INTO `sys_role_privilege` VALUES (2, 14);
INSERT INTO `sys_role_privilege` VALUES (2, 12);
INSERT INTO `sys_role_privilege` VALUES (2, 13);
INSERT INTO `sys_role_privilege` VALUES (2, 11);
INSERT INTO `sys_role_privilege` VALUES (1, 8);
INSERT INTO `sys_role_privilege` VALUES (1, 9);
INSERT INTO `sys_role_privilege` VALUES (1, 10);
INSERT INTO `sys_role_privilege` VALUES (1, 11);
INSERT INTO `sys_role_privilege` VALUES (1, 12);
INSERT INTO `sys_role_privilege` VALUES (1, 13);
INSERT INTO `sys_role_privilege` VALUES (1, 14);
INSERT INTO `sys_role_privilege` VALUES (1, 15);
INSERT INTO `sys_role_privilege` VALUES (1, 16);
INSERT INTO `sys_role_privilege` VALUES (1, 17);
INSERT INTO `sys_role_privilege` VALUES (1, 18);
INSERT INTO `sys_role_privilege` VALUES (1, 19);
INSERT INTO `sys_role_privilege` VALUES (1, 20);
COMMIT;

-- ----------------------------
-- Table structure for sys_saas_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_saas_account`;
CREATE TABLE `sys_saas_account` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `long_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公司全称',
  `mobile` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
  `contact_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人',
  `create_user_id` int(8) DEFAULT NULL,
  `update_user_id` int(8) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL,
  `status_flag` int(1) DEFAULT NULL,
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='SAAS账号';

-- ----------------------------
-- Records of sys_saas_account
-- ----------------------------
BEGIN;
INSERT INTO `sys_saas_account` VALUES (1, '智联招聘', '北京智联招聘有限公司', '18600982716', '张三', 1, 1, '2021-01-21 16:03:43', '2021-01-21 16:09:11', 0, 1, 'admin/管理员', 'admin/管理员');
INSERT INTO `sys_saas_account` VALUES (2, '开心网', '北京开心网科技有限公司', '18600098761', '里斯', 1, 1, '2021-01-21 16:10:20', '2021-01-21 16:37:02', 0, 1, 'admin/管理员', 'admin/管理员');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) DEFAULT NULL,
  `create_user_id` int(8) DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL COMMENT '软删除',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态',
  `update_time` datetime(6) DEFAULT NULL,
  `update_user_id` int(8) DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `login_date` datetime(6) DEFAULT NULL COMMENT '登陆时间',
  `login_ip` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登陆IP',
  `login_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登陆用户名',
  `mobile` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门',
  `remark` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `sex` int(1) NOT NULL COMMENT '性别',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `avatar` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `saas_id` int(8) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ahtq5ew3v0kt1n7hf1sgp7p8l` (`email`),
  UNIQUE KEY `UK_blyyljcvmmqokx6t10jvmcj98` (`login_name`),
  UNIQUE KEY `UK_cvv4commjv5h4bai0h4vuqvkd` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, '2020-12-28 16:11:00.000000', 1, 0, 1, '2020-12-28 16:11:10.000000', 1, 'admin@saas.com', '2020-12-28 16:11:24.000000', '1.1.1.1', 'admin', '18700092817', '管理员', '$2a$10$FLUle8pAygUwYyj5eprDjOze80kppIlDwIK4I1m0nvbf06.JgwjLu', NULL, NULL, NULL, 0, NULL, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (2, '2020-12-31 14:13:35.000000', 1, 1, 1, '2020-12-31 14:43:17.311000', 1, 'aa@sina.com', NULL, NULL, 'adkj', '18988820191', 'qowi', NULL, NULL, 4, '1234455', 0, NULL, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (3, '2021-01-07 11:25:54.000000', 1, 0, 1, '2021-01-20 15:29:46.291000', 3, '123@qq.com', NULL, NULL, '12345', '13545678909', '12345', '$2a$10$KMCbfHM5xZ8YUIi6nQ5xMuanoiHXKTpCnwKoN7Te2bpudQ/Avf29q', NULL, 2, NULL, 1, NULL, '12345/12345', '/profile/avatar/2021/01/15/6973168b-5a62-4d97-a6f9-047308c934de.jpeg', 0);
INSERT INTO `sys_user` VALUES (4, '2021-01-07 16:17:00.008000', 1, 1, 0, '2021-01-08 11:44:54.059000', 1, '111@11.com', NULL, NULL, '12122', '13600991823', '3222', '$2a$10$u2QhBO8JJexekJ2SFy2G.elctRZH8mDNK.exDuZxQCLZXyZy8s4.W', NULL, 1, NULL, 1, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  KEY `FKb40xxfch70f5qnyfw8yme1n1s` (`user_id`),
  CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (4, 2);
INSERT INTO `sys_user_role` VALUES (3, 2);
COMMIT;

-- ----------------------------
-- Table structure for wx_article
-- ----------------------------
DROP TABLE IF EXISTS `wx_article`;
CREATE TABLE `wx_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(8) DEFAULT NULL COMMENT '创建人ID',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标记',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态标记',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(8) DEFAULT NULL COMMENT '更人ID',
  `saas_id` bigint(8) DEFAULT NULL COMMENT 'Saas账号',
  `author` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作者',
  `channel_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '渠道',
  `cover_img` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '封面图',
  `cover_media_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '封面微信素材ID',
  `extension_profile` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '扩展信息',
  `main_body` text COLLATE utf8mb4_bin COMMENT '内容',
  `media_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信素材ID',
  `name` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标题',
  `only_fans_comment_flag` bit(1) NOT NULL COMMENT '粉丝评论',
  `open_comment_flag` bit(1) NOT NULL COMMENT '开放评论',
  `original_link` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '原创地址链接',
  `original_link_flag` bit(1) NOT NULL COMMENT '是否原创',
  `replace_url_cotent` text COLLATE utf8mb4_bin COMMENT '内容替换',
  `resource_codes` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源列表ID集合',
  `resource_type` int(1) DEFAULT NULL COMMENT '关联资源类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='图文素材';

-- ----------------------------
-- Table structure for wx_auth_account
-- ----------------------------
DROP TABLE IF EXISTS `wx_auth_account`;
CREATE TABLE `wx_auth_account` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `service_type` int(1) NOT NULL COMMENT '类型0小程序，2公众号',
  `authorizer_app_id` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'APPID',
  `saas_id` int(8) DEFAULT NULL COMMENT '归属公司ID',
  `authorizer_fresh_token` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'Token',
  `alias` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '别名',
  `head_img` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `nick_name` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `principal_name` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公司名称',
  `qrcode_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '二维码',
  `signature` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '签名',
  `user_name` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `other_info` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '其他信息',
  `create_user_id` int(8) DEFAULT NULL COMMENT '创建人',
  `update_user_id` int(8) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL,
  `status_flag` int(1) DEFAULT NULL,
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='微信授权账号信息';

-- ----------------------------
-- Table structure for wx_media
-- ----------------------------
DROP TABLE IF EXISTS `wx_media`;
CREATE TABLE `wx_media` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(8) DEFAULT NULL COMMENT '创建用户',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标记',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态标记',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(8) DEFAULT NULL COMMENT '更新ID',
  `saas_id` bigint(8) DEFAULT NULL COMMENT 'Saas账号',
  `introduction` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '介绍',
  `media_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '素材ID',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `type` int(2) NOT NULL COMMENT '类型',
  `url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='媒体素材';

-- ----------------------------
-- Table structure for wx_menu
-- ----------------------------
DROP TABLE IF EXISTS `wx_menu`;
CREATE TABLE `wx_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(8) DEFAULT NULL COMMENT '创建人ID',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标记',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态标记',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(8) DEFAULT NULL COMMENT '更新用户ID',
  `saas_id` bigint(8) DEFAULT NULL COMMENT 'saas账号',
  `action_type` int(2) DEFAULT NULL COMMENT '类型(1 普通菜单 2小程序菜单 3发送消息)',
  `channel_code` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '渠道',
  `link_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '链接',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `page_path` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '小程序路径',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单',
  `target_content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内容',
  `target_type` int(2) DEFAULT NULL COMMENT '内容类型(1.消息 2.微页面 3.商品 4.文章 5.普通链接)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='微信菜单';

-- ----------------------------
-- Table structure for wx_reply
-- ----------------------------
DROP TABLE IF EXISTS `wx_reply`;
CREATE TABLE `wx_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(8) DEFAULT NULL COMMENT '创建人ID',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标记',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态标记',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(8) DEFAULT NULL COMMENT '更新用户ID',
  `saas_id` bigint(8) DEFAULT NULL COMMENT 'Saas账号',
  `key_words` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关键字集合',
  `match_type` int(1) NOT NULL COMMENT '匹配类型',
  `order_num` int(5) NOT NULL COMMENT '优先级',
  `receive_type` int(1) NOT NULL COMMENT '接收消息类型',
  `reply_content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '回复内容',
  `reply_type` int(1) NOT NULL COMMENT '回复类型',
  `rule_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '规则名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='微信回复规则';

-- ----------------------------
-- Table structure for wx_template
-- ----------------------------
DROP TABLE IF EXISTS `wx_template`;
CREATE TABLE `wx_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(8) DEFAULT NULL COMMENT '创建用户ID',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标记',
  `status_flag` int(1) DEFAULT NULL COMMENT '状态标记',
  `update_by` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `update_user_id` bigint(8) DEFAULT NULL COMMENT '更新用户ID',
  `saas_id` bigint(8) DEFAULT NULL COMMENT 'Saas账号',
  `channel_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '渠道',
  `first` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '首替',
  `keyword1` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '二替',
  `keyword2` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '三替',
  `keyword3` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '四替',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `remark` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `scene_type` int(2) NOT NULL COMMENT '场景',
  `wx_template_id` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信模版ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='微信模版消息';

SET FOREIGN_KEY_CHECKS = 1;
