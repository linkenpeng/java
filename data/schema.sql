
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `balance` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'linken', '379');

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `book_id` bigint(20) NOT NULL COMMENT '图书ID',
  `student_id` bigint(20) NOT NULL COMMENT '学号',
  `appoint_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '预约时间',
  PRIMARY KEY (`book_id`,`student_id`),
  KEY `idx_appoint_time` (`appoint_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约图书表';

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('1000', '12345678910', '2016-10-23 11:47:58');
INSERT INTO `appointment` VALUES ('1001', '12345678910', '2016-10-23 13:03:49');

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `author_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `biography` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author` VALUES ('1', 'linken', 'linken', 'linken@qq.com', 'linken_desc');

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `author_id` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `featured` tinyint(2) DEFAULT '0',
  PRIMARY KEY (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', '1', 'Linken\'s bloger', '1');
INSERT INTO `blog` VALUES ('2', '1', 'Linken\'s Second Blog update.', '1');
INSERT INTO `blog` VALUES ('5', '1', 'Linken\'s Second Blog', '0');
INSERT INTO `blog` VALUES ('6', '1', 'Linken\'s Second Blog', '0');
INSERT INTO `blog` VALUES ('7', '0', 'Linken Second Blog', '0');
INSERT INTO `blog` VALUES ('8', '1', 'Linken\'s Second Blog', '0');
INSERT INTO `blog` VALUES ('9', '1', 'Linken\'s Second Blog', '0');
INSERT INTO `blog` VALUES ('10', '1', 'Linken\'s Second Blog', '0');
INSERT INTO `blog` VALUES ('11', '1', 'Linken\'s Second Blog', '0');
INSERT INTO `blog` VALUES ('12', '1', 'Linken\'s Second Blog', '0');
INSERT INTO `blog` VALUES ('13', '1', 'Linken\'s Second Blog', '0');
INSERT INTO `blog` VALUES ('14', '1', 'Linken\'s Second Blog', '0');
INSERT INTO `blog` VALUES ('15', '1', 'Linken\'s Second Blog', '0');
INSERT INTO `blog` VALUES ('16', '1', 'Linken\'s Second Blog', '0');
INSERT INTO `blog` VALUES ('17', '1', 'Linken\'s Second Blog', '0');
INSERT INTO `blog` VALUES ('18', '1', 'bbaa', '1');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图书ID',
  `name` varchar(100) NOT NULL COMMENT '图书名称',
  `number` int(11) NOT NULL COMMENT '馆藏数量',
  `price` int(10) DEFAULT NULL,
  `stock` int(10) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `ID` int(11) NOT NULL,
  `BOOKNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 COMMENT='图书表';

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1000', 'Java程序设计', '10', '500', '2', '1000', '0', null);
INSERT INTO `book` VALUES ('1001', '数据结构', '0', '60', '0', '1001', '0', null);
INSERT INTO `book` VALUES ('1002', '设计模式', '10', '70', '10', '1002', '0', null);
INSERT INTO `book` VALUES ('1003', '编译原理', '10', '100', '10', '1003', '0', null);

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '1', 'Goods');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'it');
INSERT INTO `department` VALUES ('2', 'hr');

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO `employees` VALUES ('1', 'wwu', 'zhangsan@qq.com', '1');
INSERT INTO `employees` VALUES ('2', 'lisi', 'lisi@qq.com', '2');
INSERT INTO `employees` VALUES ('3', 'AA', 'aa@qq.com', '1');
INSERT INTO `employees` VALUES ('4', 'BB', 'bb@qq.com', '2');
INSERT INTO `employees` VALUES ('5', 'CC', 'cc@qq.com', '2');
INSERT INTO `employees` VALUES ('6', 'DD', 'dd@qq.com', '1');
INSERT INTO `employees` VALUES ('7', 'EE', 'ee@qq.com', '1');
INSERT INTO `employees` VALUES ('8', 'GG', 'gg@qq.com', '3');
INSERT INTO `employees` VALUES ('9', 'WL', 'wl@qq.com', '2');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `body` text,
  `draft` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '1', 'Blog1', 'Blog1', '1', '2016-10-22 16:01:23');

-- ----------------------------
-- Table structure for post_tag
-- ----------------------------
DROP TABLE IF EXISTS `post_tag`;
CREATE TABLE `post_tag` (
  `post_id` int(11) NOT NULL,
  `tag_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post_tag
-- ----------------------------
INSERT INTO `post_tag` VALUES ('1', '1');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'tag1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `balance` decimal(10,6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `age` mediumint(8) DEFAULT NULL,
  `sex` tinyint(2) DEFAULT '0',
  `memo` text,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'linken', 'linken', 'abcd12345', '820.000000', '2016-12-11 23:06:00', '35', '127', '我是歌手');
