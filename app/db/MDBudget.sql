-- ----------------------------
-- Table structure for tb_Menu
-- ----------------------------
DROP TABLE IF EXISTS "main"."tb_Menu";
CREATE TABLE tb_Menu (
    menuId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    menuNama TEXT,
    menuHarga INTEGER,
    menuGambar TEXT
);

-- ----------------------------
-- Table structure for tb_Order
-- ----------------------------
DROP TABLE IF EXISTS "main"."tb_Order";
CREATE TABLE tb_Order (
    orderId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    orderDetailId INTEGER,
    orderTotal INTEGER,
    orderUserId INTEGER,
    orderDate TEXT,
    FOREIGN KEY (orderDetailId) REFERENCES OrderDetail(orderDetailId),
    FOREIGN KEY (orderUserId) REFERENCES User(userId)
);

-- ----------------------------
-- Table structure for tb_OrderDetail
-- ----------------------------
DROP TABLE IF EXISTS "main"."tb_OrderDetail";
CREATE TABLE tb_OrderDetail (
    orderDetailId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    orderDetailMenuId INTEGER,
    orderDetailMenuAmount INTEGER,
    FOREIGN KEY (orderDetailMenuId) REFERENCES Menu(menuId)
);

-- ----------------------------
-- Table structure for tb_User
-- ----------------------------
DROP TABLE IF EXISTS "main"."tb_User";
CREATE TABLE tb_User (
    userId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    userNama TEXT,
    userPassword TEXT,
		userRole TEXT
);

-- ----------------------------
-- Records of tb_User
-- ----------------------------
INSERT INTO "main"."tb_User" VALUES (1, 'admin', 'admin', 'admin');
INSERT INTO "main"."tb_User" VALUES (2, 'nito', 'nito', null);