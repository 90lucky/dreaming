/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2017-12-15 09:59:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_sys_user_base`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_base`;
CREATE TABLE `t_sys_user_base` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(64) NOT NULL COMMENT '用户唯一标识',
  `USER_NAME` varchar(255) DEFAULT NULL COMMENT '用户名',
  `USER_PASSWORD` varchar(255) DEFAULT NULL COMMENT '用户密码，MD5加密',
  `USER_PHONE` varchar(15) DEFAULT NULL COMMENT '用户注册号码（可变更）',
  `USER_MAIL` varchar(100) DEFAULT NULL COMMENT '用户邮箱',
  `CREATE_TIME` varchar(255) DEFAULT NULL COMMENT '注册时间',
  `UPDATE_TIME` varchar(255) DEFAULT NULL COMMENT '更新时间',
  `LOGIN_TIME` varchar(255) DEFAULT NULL COMMENT '最新登录时间',
  PRIMARY KEY (`ID`),
  KEY `pk_user_id` (`USER_ID`) USING BTREE,
  KEY `pk_user_phone` (`USER_PHONE`) USING BTREE,
  KEY `pk_user_name` (`USER_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user_base
-- ----------------------------
