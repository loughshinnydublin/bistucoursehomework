/*
 Navicat Premium Data Transfer

 Source Server         : my
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : dianshangpingtai

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 29/05/2025 23:44:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart_item
-- ----------------------------
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` int NULL DEFAULT 1,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `product_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `cart_item_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cart_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart_item
-- ----------------------------
INSERT INTO `cart_item` VALUES (22, 3, 7, 1, '2025-05-25 09:30:42');
INSERT INTO `cart_item` VALUES (23, 3, 1, 2, '2025-05-25 09:31:36');
INSERT INTO `cart_item` VALUES (29, 3, 2, 1, '2025-05-25 14:13:03');
INSERT INTO `cart_item` VALUES (30, 1, 9, 1, '2025-05-25 14:15:37');

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `view_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES (17, 1, 1, '2025-05-22 21:32:32');
INSERT INTO `history` VALUES (18, 1, 1, '2025-05-22 21:32:33');
INSERT INTO `history` VALUES (19, 1, 1, '2025-05-22 21:32:33');
INSERT INTO `history` VALUES (20, 1, 1, '2025-05-22 21:32:33');
INSERT INTO `history` VALUES (21, 1, 1, '2025-05-23 18:40:50');
INSERT INTO `history` VALUES (22, 1, 1, '2025-05-23 18:43:03');
INSERT INTO `history` VALUES (23, 1, 1, '2025-05-23 18:43:04');
INSERT INTO `history` VALUES (24, 1, 1, '2025-05-23 18:43:05');
INSERT INTO `history` VALUES (25, 1, 1, '2025-05-23 18:43:54');
INSERT INTO `history` VALUES (26, 1, 1, '2025-05-23 18:56:26');
INSERT INTO `history` VALUES (27, 1, 1, '2025-05-23 18:57:05');
INSERT INTO `history` VALUES (28, 1, 3, '2025-05-23 22:32:02');
INSERT INTO `history` VALUES (29, 1, 6, '2025-05-25 09:18:42');
INSERT INTO `history` VALUES (30, 3, 1, '2025-05-25 09:31:24');
INSERT INTO `history` VALUES (31, 3, 1, '2025-05-25 09:31:28');
INSERT INTO `history` VALUES (32, 3, 1, '2025-05-25 09:31:28');
INSERT INTO `history` VALUES (33, 3, 2, '2025-05-25 09:31:33');
INSERT INTO `history` VALUES (34, 3, 2, '2025-05-25 09:31:52');
INSERT INTO `history` VALUES (35, 3, 2, '2025-05-25 09:31:53');
INSERT INTO `history` VALUES (36, 3, 2, '2025-05-25 09:31:53');
INSERT INTO `history` VALUES (37, 3, 2, '2025-05-25 09:31:53');
INSERT INTO `history` VALUES (38, 3, 2, '2025-05-25 09:31:53');
INSERT INTO `history` VALUES (39, 3, 2, '2025-05-25 09:31:54');
INSERT INTO `history` VALUES (40, 3, 2, '2025-05-25 09:31:54');
INSERT INTO `history` VALUES (41, 3, 2, '2025-05-25 09:31:54');
INSERT INTO `history` VALUES (42, 3, 2, '2025-05-25 09:31:55');
INSERT INTO `history` VALUES (43, 3, 2, '2025-05-25 09:31:55');
INSERT INTO `history` VALUES (44, 3, 2, '2025-05-25 09:31:55');
INSERT INTO `history` VALUES (45, 3, 2, '2025-05-25 09:31:56');
INSERT INTO `history` VALUES (46, 3, 2, '2025-05-25 09:31:56');
INSERT INTO `history` VALUES (47, 3, 2, '2025-05-25 09:31:56');
INSERT INTO `history` VALUES (48, 3, 2, '2025-05-25 09:31:56');
INSERT INTO `history` VALUES (49, 3, 2, '2025-05-25 09:31:56');
INSERT INTO `history` VALUES (50, 3, 2, '2025-05-25 09:31:57');
INSERT INTO `history` VALUES (51, 3, 2, '2025-05-25 09:31:57');
INSERT INTO `history` VALUES (52, 3, 2, '2025-05-25 09:31:57');
INSERT INTO `history` VALUES (53, 3, 2, '2025-05-25 09:31:58');
INSERT INTO `history` VALUES (54, 3, 2, '2025-05-25 09:31:58');
INSERT INTO `history` VALUES (55, 3, 2, '2025-05-25 09:31:58');
INSERT INTO `history` VALUES (56, 3, 2, '2025-05-25 09:31:58');
INSERT INTO `history` VALUES (57, 3, 2, '2025-05-25 09:31:58');
INSERT INTO `history` VALUES (58, 3, 2, '2025-05-25 09:32:02');
INSERT INTO `history` VALUES (59, 3, 2, '2025-05-25 09:32:05');
INSERT INTO `history` VALUES (60, 3, 6, '2025-05-25 09:33:39');
INSERT INTO `history` VALUES (61, 3, 6, '2025-05-25 09:33:39');
INSERT INTO `history` VALUES (62, 3, 6, '2025-05-25 09:33:40');
INSERT INTO `history` VALUES (63, 3, 6, '2025-05-25 09:33:40');
INSERT INTO `history` VALUES (64, 3, 6, '2025-05-25 09:33:40');
INSERT INTO `history` VALUES (65, 3, 6, '2025-05-25 09:33:41');
INSERT INTO `history` VALUES (66, 3, 6, '2025-05-25 09:33:41');
INSERT INTO `history` VALUES (67, 3, 6, '2025-05-25 09:33:42');
INSERT INTO `history` VALUES (68, 3, 1, '2025-05-25 09:34:27');
INSERT INTO `history` VALUES (69, 3, 1, '2025-05-25 09:34:27');
INSERT INTO `history` VALUES (70, 3, 1, '2025-05-25 09:34:27');
INSERT INTO `history` VALUES (71, 3, 1, '2025-05-25 09:34:27');
INSERT INTO `history` VALUES (72, 3, 1, '2025-05-25 09:34:28');
INSERT INTO `history` VALUES (73, 3, 1, '2025-05-25 09:34:28');
INSERT INTO `history` VALUES (74, 3, 1, '2025-05-25 09:34:28');
INSERT INTO `history` VALUES (75, 3, 1, '2025-05-25 09:34:28');
INSERT INTO `history` VALUES (76, 3, 1, '2025-05-25 09:34:29');
INSERT INTO `history` VALUES (77, 3, 1, '2025-05-25 09:34:29');
INSERT INTO `history` VALUES (78, 3, 1, '2025-05-25 09:34:30');
INSERT INTO `history` VALUES (79, 3, 1, '2025-05-25 09:34:30');
INSERT INTO `history` VALUES (80, 3, 1, '2025-05-25 09:34:30');
INSERT INTO `history` VALUES (81, 3, 1, '2025-05-25 09:34:30');
INSERT INTO `history` VALUES (82, 3, 1, '2025-05-25 09:34:31');
INSERT INTO `history` VALUES (83, 3, 1, '2025-05-25 09:34:31');
INSERT INTO `history` VALUES (84, 3, 1, '2025-05-25 09:34:32');
INSERT INTO `history` VALUES (85, 3, 1, '2025-05-25 09:34:32');
INSERT INTO `history` VALUES (86, 3, 1, '2025-05-25 09:34:32');
INSERT INTO `history` VALUES (87, 3, 1, '2025-05-25 09:36:07');
INSERT INTO `history` VALUES (88, 3, 7, '2025-05-25 09:36:21');
INSERT INTO `history` VALUES (89, 3, 7, '2025-05-25 09:36:26');
INSERT INTO `history` VALUES (90, 1, 1, '2025-05-25 09:43:06');
INSERT INTO `history` VALUES (91, 1, 2, '2025-05-25 09:43:18');
INSERT INTO `history` VALUES (92, 1, 3, '2025-05-25 14:00:04');
INSERT INTO `history` VALUES (93, 11, 1, '2025-05-25 14:18:07');

-- ----------------------------
-- Table structure for merchant_info
-- ----------------------------
DROP TABLE IF EXISTS `merchant_info`;
CREATE TABLE `merchant_info`  (
  `user_id` bigint NOT NULL,
  `license_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '营业执照图片路径',
  `id_card_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证图片路径',
  `merchant_level` tinyint NULL DEFAULT 1,
  `satisfaction_rate` decimal(5, 2) NULL DEFAULT 100.00,
  PRIMARY KEY (`user_id`) USING BTREE,
  CONSTRAINT `merchant_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of merchant_info
-- ----------------------------
INSERT INTO `merchant_info` VALUES (2, 'https://cdn.acwing.com/media/user/profile/photo/247763_lg_f72e146e58.jpg', 'https://cdn.acwing.com/media/user/profile/photo/247763_lg_f72e146e58.jpg', 1, 100.00);

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `status` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'WAIT_SHIP',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id` ASC) USING BTREE,
  INDEX `product_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (1, 1, 1, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (2, 1, 2, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (3, 1, 3, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (4, 2, 1, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (5, 2, 2, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (6, 2, 3, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (7, 3, 1, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (8, 3, 2, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (9, 3, 3, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (10, 4, 1, 3, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (11, 5, 1, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (12, 6, 1, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (13, 7, 1, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (14, 8, 1, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (15, 9, 1, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (16, 10, 1, 1, 0.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (17, 11, 1, 1, 20.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (18, 12, 1, 2, 20.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (19, 13, 1, 1, 20.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (20, 14, 6, 3, 30.00, 'SHIPPED');
INSERT INTO `order_item` VALUES (21, 15, 4, 1, 10.00, 'SHIPPED');
INSERT INTO `order_item` VALUES (22, 15, 6, 1, 30.00, 'SHIPPED');
INSERT INTO `order_item` VALUES (23, 16, 1, 2, 20.00, 'WAIT_SHIP');
INSERT INTO `order_item` VALUES (24, 17, 4, 2, 10.00, 'SHIPPED');
INSERT INTO `order_item` VALUES (25, 18, 4, 1, 10.00, 'SHIPPED');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `total_amount` double NOT NULL,
  `status` enum('CREATED','PAID','SHIPPED','RECEIVED','RETURN_REQUEST','RETURNED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'CREATED',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, 0, 'PAID', '2025-05-23 23:59:13', '2025-05-23 23:59:13');
INSERT INTO `orders` VALUES (2, 1, 0, 'CREATED', '2025-05-23 23:59:25', '2025-05-23 23:59:25');
INSERT INTO `orders` VALUES (3, 1, 0, 'CREATED', '2025-05-24 00:11:21', '2025-05-24 00:11:21');
INSERT INTO `orders` VALUES (4, 1, 0, 'CREATED', '2025-05-24 09:32:12', '2025-05-24 09:32:12');
INSERT INTO `orders` VALUES (5, 1, 0, 'CREATED', '2025-05-24 09:34:18', '2025-05-24 09:34:18');
INSERT INTO `orders` VALUES (6, 1, 0, 'CREATED', '2025-05-24 09:35:41', '2025-05-24 09:35:41');
INSERT INTO `orders` VALUES (7, 1, 0, 'CREATED', '2025-05-24 09:37:50', '2025-05-24 09:37:50');
INSERT INTO `orders` VALUES (8, 1, 0, 'CREATED', '2025-05-24 09:43:32', '2025-05-24 09:43:32');
INSERT INTO `orders` VALUES (9, 1, 0, 'CREATED', '2025-05-24 09:44:22', '2025-05-24 09:44:22');
INSERT INTO `orders` VALUES (10, 1, 0, 'PAID', '2025-05-24 09:45:48', '2025-05-24 09:45:48');
INSERT INTO `orders` VALUES (11, 1, 20, 'PAID', '2025-05-24 09:49:01', '2025-05-24 09:49:01');
INSERT INTO `orders` VALUES (12, 1, 40, 'PAID', '2025-05-25 08:42:28', '2025-05-25 08:42:28');
INSERT INTO `orders` VALUES (13, 1, 20, 'PAID', '2025-05-25 09:29:04', '2025-05-25 09:29:04');
INSERT INTO `orders` VALUES (14, 1, 90, 'RECEIVED', '2025-05-25 09:51:44', '2025-05-25 12:55:50');
INSERT INTO `orders` VALUES (15, 1, 40, 'RECEIVED', '2025-05-25 10:55:13', '2025-05-25 12:55:44');
INSERT INTO `orders` VALUES (16, 1, 40, 'PAID', '2025-05-25 13:49:07', '2025-05-25 13:49:07');
INSERT INTO `orders` VALUES (17, 1, 20, 'RECEIVED', '2025-05-25 13:49:49', '2025-05-25 13:50:13');
INSERT INTO `orders` VALUES (18, 11, 10, 'RECEIVED', '2025-05-25 14:17:19', '2025-05-25 14:17:43');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint NOT NULL,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `category` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NOT NULL,
  `discount_price` decimal(10, 2) NULL DEFAULT NULL,
  `size` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `bargain_allowed` tinyint(1) NULL DEFAULT 0,
  `quantity` int NULL DEFAULT 0,
  `product_condition` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '新旧程度：全新、九成新、八成新等',
  `status` enum('PENDING','APPROVED','OFFLINE','REJECTED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'PENDING',
  `sales` int NULL DEFAULT 0,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `merchant_id`(`merchant_id` ASC) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`merchant_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 2, '缪尔赛思', 'books', 20.00, NULL, NULL, '书', 0, 0, NULL, 'APPROVED', 0, '2025-04-03 18:56:30', '2025-05-25 13:31:31');
INSERT INTO `product` VALUES (2, 2, '丛雨', 'clothing', 10.00, NULL, NULL, '书', 0, 0, NULL, 'APPROVED', 0, '2025-04-03 18:56:30', '2025-05-25 13:31:31');
INSERT INTO `product` VALUES (3, 2, '芳乃', 'books', 5.00, 0.00, NULL, '动漫', 0, 0, NULL, 'APPROVED', 0, '2025-05-23 22:12:22', '2025-05-25 13:31:31');
INSERT INTO `product` VALUES (4, 3, '茉子', 'test', 10.00, 10.00, '', '动漫', 0, 1, '', 'APPROVED', 0, '2025-05-25 08:41:51', '2025-05-25 13:31:31');
INSERT INTO `product` VALUES (5, 3, '雷娜', 'test', 30.00, 30.00, '', 'test', 0, 1, '', 'REJECTED', 0, '2025-05-25 09:12:57', '2025-05-25 14:04:34');
INSERT INTO `product` VALUES (6, 3, '雷纳', 'test', 30.00, 30.00, '', 'test', 0, 1, '', 'APPROVED', 0, '2025-05-25 09:15:28', '2025-05-25 14:04:43');
INSERT INTO `product` VALUES (7, 3, '雷鸟', 'test', 40.00, 40.00, '', 'test', 0, 1, '', 'APPROVED', 0, '2025-05-25 09:30:13', '2025-05-25 14:04:54');
INSERT INTO `product` VALUES (8, 3, '1`2', 'test', 10.00, 10.00, '', '1234', 0, 1, '', 'PENDING', 0, '2025-05-25 14:13:31', '2025-05-25 14:13:31');
INSERT INTO `product` VALUES (9, 3, '1`2', 'test', 10.00, 10.00, '', '1234', 0, 1, '', 'APPROVED', 0, '2025-05-25 14:13:48', '2025-05-25 14:15:27');

-- ----------------------------
-- Table structure for product_image
-- ----------------------------
DROP TABLE IF EXISTS `product_image`;
CREATE TABLE `product_image`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `product_image_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_image
-- ----------------------------
INSERT INTO `product_image` VALUES (1, 1, 'https://p.sda1.dev/24/3c3576fc53f69db293686a3e8be90486/fd3dbc8b4a7ec6e76872b00f554969ffd424cbc9.jpg');
INSERT INTO `product_image` VALUES (2, 2, 'https://p.sda1.dev/24/28863c4e4bf4d35dfe30df8b32689bd8/6cad0534faa6c4a6a90d4772cb7b07cb96da965f.png');
INSERT INTO `product_image` VALUES (3, 3, 'https://p.sda1.dev/24/a05f7bebf5f70eb8729082e394aa5504/bd315c6034a85edf8db1e362151e1e23dd54564edf12.webp');
INSERT INTO `product_image` VALUES (4, 4, 'https://p.sda1.dev/24/d3d5b18f8d5f5190990ca7bbb3f597e8/375px-常陸茉子_角色專輯封面.webp');
INSERT INTO `product_image` VALUES (5, 6, 'https://p.sda1.dev/24/9574f0cfc352238f6e662d9f4bc5df54/375px-蕾娜·莉希特娜瓦_角色專輯封面.webp');
INSERT INTO `product_image` VALUES (6, 7, 'https://p.sda1.dev/24/9574f0cfc352238f6e662d9f4bc5df54/375px-蕾娜·莉希特娜瓦_角色專輯封面.webp');
INSERT INTO `product_image` VALUES (7, 8, '');
INSERT INTO `product_image` VALUES (8, 9, 'https://p.sda1.dev/24/9574f0cfc352238f6e662d9f4bc5df54/375px-蕾娜·莉希特娜瓦_角色專輯封面.webp');

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NULL DEFAULT NULL,
  `merchant_id` bigint NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `rating` tinyint NULL DEFAULT NULL COMMENT '1-5星',
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_id`(`product_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of review
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` enum('USER','MERCHANT','ADMIN') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` enum('男','女','其他') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_account` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` enum('PENDING','APPROVED','REJECTED','MERCHANT_REQUESTING') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'PENDING',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'test', '$2a$10$K.Evz59n45GD5y/yS5EfWO5BXGVGg/29owEOK6MU1vkxoKO/4mY4m', 'ADMIN', NULL, NULL, NULL, NULL, NULL, 'MERCHANT_REQUESTING', '2025-04-03 18:52:10', '2025-05-25 14:25:48', NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, 'test_merchant', '$2a$10$CRNrqwML1TtUqIQO1OoSLuvgS4O8Z06qBxUNpedYIy3F4RJcTi8qe', 'MERCHANT', '123', '123@123.com', 'beijing', NULL, NULL, 'APPROVED', '2025-04-03 18:53:25', '2025-04-20 18:30:16', NULL, NULL, NULL);
INSERT INTO `user` VALUES (3, 'test2', '$2a$10$95ykeXBKZElQmUxOwrFg3.TWYeglpi2PDKNlrgj9oD2btapb0iCJe', 'MERCHANT', NULL, NULL, NULL, '男', NULL, 'APPROVED', '2025-04-11 22:41:02', '2025-05-25 08:37:48', NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, 'test3', '$2a$10$XwpNjHgmrHId4R62uPEeDO4aEsY9P/6EyAe9gBMilsLv5to24MgG.', 'USER', NULL, NULL, NULL, '男', NULL, 'PENDING', '2025-04-12 15:32:30', '2025-04-12 15:32:30', NULL, NULL, NULL);
INSERT INTO `user` VALUES (5, 'test4', '$2a$10$5NI4eB1nTCPsm5s9ae1/K.fhcK1WJaEV4a.1RxeQovM3DFxQOQ2DO', 'USER', NULL, NULL, NULL, '男', NULL, 'APPROVED', '2025-04-12 16:35:31', '2025-04-12 16:35:30', NULL, NULL, NULL);
INSERT INTO `user` VALUES (6, 'test5', '$2a$10$XmLb.hJP1kTdbIwRwKVVrO4eTpSvfaK1jxclW418dwwQXYjGTOoLK', 'USER', NULL, NULL, NULL, '男', NULL, 'PENDING', '2025-04-27 18:59:42', '2025-04-27 18:59:41', NULL, NULL, NULL);
INSERT INTO `user` VALUES (7, 'test6', '$2a$10$cOg2FoSdX0ZIvdVkl/ebwexRb5vKuqhZ4AF.QNdY7cvo0sa3XzWB6', 'USER', NULL, NULL, NULL, '男', NULL, 'PENDING', '2025-04-27 19:02:17', '2025-04-27 19:02:16', NULL, NULL, NULL);
INSERT INTO `user` VALUES (8, '111111', '$2a$10$nDQRarjpnutjuD7WE1VRnO8LkEBCcpAGIqsXuh6U7j8EoFuBkG2A.', 'USER', NULL, NULL, NULL, '男', NULL, 'APPROVED', '2025-05-25 09:28:12', '2025-05-25 09:28:12', NULL, NULL, NULL);
INSERT INTO `user` VALUES (9, '11', '$2a$10$mLzPtziar8v05BFDzdgsses9Y03szSrghRAKVMhNoQyRjXoIizieW', 'USER', NULL, NULL, NULL, '男', NULL, 'PENDING', '2025-05-25 13:51:12', '2025-05-25 13:51:11', NULL, NULL, NULL);
INSERT INTO `user` VALUES (10, '22', '$2a$10$T0fbbTAJZtuArvM6tKZOMepalDktG20/CGfwJaT3NkvcT6bCEXmzK', 'USER', NULL, NULL, NULL, '男', NULL, 'PENDING', '2025-05-25 13:51:19', '2025-05-25 13:51:18', NULL, NULL, NULL);
INSERT INTO `user` VALUES (11, '12', '$2a$10$KnXog/Tvwbcq5tIco5BjL.BQUBk.vrkV1ipnPv4gTLj.dCJFuLCgi', 'USER', NULL, NULL, NULL, '男', NULL, 'APPROVED', '2025-05-25 14:11:54', '2025-05-25 14:11:54', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for wallet
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `balance` double NOT NULL,
  `points` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `chk_column_non_negative` CHECK (`balance` >= 0)
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wallet
-- ----------------------------
INSERT INTO `wallet` VALUES (1, 1, 99999730, 10000270);
INSERT INTO `wallet` VALUES (2, 3, 0, 0);
INSERT INTO `wallet` VALUES (4, 11, 990, 10);

SET FOREIGN_KEY_CHECKS = 1;
