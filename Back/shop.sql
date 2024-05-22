/*
 Navicat Premium Data Transfer

 Source Server         : SHOP
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 20/05/2024 20:55:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '电子产品');
INSERT INTO `category` VALUES (2, '服装');
INSERT INTO `category` VALUES (3, '食品');

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites`  (
  `favoriteid` int NOT NULL,
  `userid` int NULL DEFAULT NULL,
  `productid` int NULL DEFAULT NULL,
  `favoritetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`favoriteid`) USING BTREE,
  INDEX `userid`(`userid` ASC) USING BTREE,
  INDEX `productid`(`productid` ASC) USING BTREE,
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of favorites
-- ----------------------------

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail`  (
  `orderdetailid` int NOT NULL AUTO_INCREMENT,
  `orderid` int NULL DEFAULT NULL,
  `productid` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `unitprice` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`orderdetailid`) USING BTREE,
  INDEX `orderid`(`orderid` ASC) USING BTREE,
  INDEX `productid`(`productid` ASC) USING BTREE,
  CONSTRAINT `orderdetail_ibfk_1` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orderdetail_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orderid` int NOT NULL AUTO_INCREMENT,
  `userid` int NULL DEFAULT NULL,
  `orderstatus` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `totalamount` decimal(10, 2) NULL DEFAULT NULL,
  `ordertime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderid`) USING BTREE,
  INDEX `userid`(`userid` ASC) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `productid` int NOT NULL AUTO_INCREMENT,
  `productname` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `price` decimal(10, 2) NOT NULL,
  `categoryid` int NULL DEFAULT NULL,
  `image` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`productid`) USING BTREE,
  INDEX `categoryid`(`categoryid` ASC) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (15, '老弟', '老弟！！！！！！！！！！！！', 1.00, 1, 'laodi.jpg');
INSERT INTO `product` VALUES (17, '耶比', '耶比耶比', 0.02, 3, 'shu3.jpg');
INSERT INTO `product` VALUES (19, '降龙十八掌', 'v我50，授你传世秘籍', 50.00, 1, 'long.jpg');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart`  (
  `cartid` int NOT NULL AUTO_INCREMENT,
  `userid` int NULL DEFAULT NULL,
  `productid` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  PRIMARY KEY (`cartid`) USING BTREE,
  INDEX `userid`(`userid` ASC) USING BTREE,
  INDEX `productid`(`productid` ASC) USING BTREE,
  CONSTRAINT `shoppingcart_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `shoppingcart_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES (1, NULL, NULL, 1);
INSERT INTO `shoppingcart` VALUES (2, NULL, NULL, 1);
INSERT INTO `shoppingcart` VALUES (3, NULL, NULL, 1);
INSERT INTO `shoppingcart` VALUES (4, NULL, NULL, 1);
INSERT INTO `shoppingcart` VALUES (5, NULL, NULL, 1);
INSERT INTO `shoppingcart` VALUES (6, NULL, NULL, 1);
INSERT INTO `shoppingcart` VALUES (7, 1, 17, 1);
INSERT INTO `shoppingcart` VALUES (8, 1, 17, 1);
INSERT INTO `shoppingcart` VALUES (9, 1, 17, 1);
INSERT INTO `shoppingcart` VALUES (10, 1, 15, 1);
INSERT INTO `shoppingcart` VALUES (11, 1, 19, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `phonenumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `permissions` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'alpha', '1', NULL, NULL, 0);
INSERT INTO `user` VALUES (2, '1234', '1234', '1', NULL, NULL, 0);
INSERT INTO `user` VALUES (3, '123456', '123456', '1', NULL, NULL, 0);
INSERT INTO `user` VALUES (4, NULL, '1', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (5, '545', '1', NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (7, '1', '1', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (8, '1', '1', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (9, '1', '1', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (10, '1', '1', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (11, '1', '1', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (15, '1', '1', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (16, 'adsa', '123', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (17, 'as', '123', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (18, 'as', '123', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (19, '1', '1', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (20, '1', '1', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (21, '1', '1', NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (22, '1234', '1234', NULL, NULL, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
