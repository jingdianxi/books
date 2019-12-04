/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : novels

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2019-12-04 16:50:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `username` varchar(255) DEFAULT '' COMMENT '用户名',
  `password` char(32) DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(255) DEFAULT '' COMMENT '分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '玄幻');
INSERT INTO `category` VALUES ('2', '武侠');
INSERT INTO `category` VALUES ('3', '都市');
INSERT INTO `category` VALUES ('4', '军事');
INSERT INTO `category` VALUES ('5', '历史');
INSERT INTO `category` VALUES ('6', '游戏');
INSERT INTO `category` VALUES ('7', '科幻');
INSERT INTO `category` VALUES ('8', '悬疑');

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '章节ID',
  `novel` int(11) unsigned DEFAULT NULL COMMENT '小说ID',
  `title` varchar(255) DEFAULT '' COMMENT '章节标题',
  `content` longtext COMMENT '章节内容',
  PRIMARY KEY (`id`),
  KEY `chapter_ibfk_1` (`novel`),
  CONSTRAINT `chapter_ibfk_1` FOREIGN KEY (`novel`) REFERENCES `novel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='章节表';

-- ----------------------------
-- Records of chapter
-- ----------------------------

-- ----------------------------
-- Table structure for novel
-- ----------------------------
DROP TABLE IF EXISTS `novel`;
CREATE TABLE `novel` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '小说ID',
  `name` varchar(255) DEFAULT '' COMMENT '小说名称',
  `category` int(11) unsigned DEFAULT NULL COMMENT '分类ID',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `info` varchar(255) DEFAULT NULL COMMENT '作者简介',
  `abstracts` varchar(255) DEFAULT NULL COMMENT '小说摘要',
  `cover` varchar(255) DEFAULT NULL COMMENT '小说封面',
  `hits` int(11) DEFAULT '0' COMMENT '点击量',
  PRIMARY KEY (`id`),
  KEY `novel_ibfk_1` (`category`),
  CONSTRAINT `novel_ibfk_1` FOREIGN KEY (`category`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小说表';

-- ----------------------------
-- Records of novel
-- ----------------------------
