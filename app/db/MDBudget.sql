
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
-- Records of tb_Menu
-- ----------------------------
INSERT INTO "main"."tb_Menu" VALUES (1, 'Spicy Burger', 12000, 'mcspicy');
INSERT INTO "main"."tb_Menu" VALUES (2, 'Bacon Cheese Burger', 17000, 'baconcheeseburger');
INSERT INTO "main"."tb_Menu" VALUES (3, 'Beef Burger', 15000, 'beefburger');
INSERT INTO "main"."tb_Menu" VALUES (4, 'Cheese Burger', 13000, 'cheeseburger');
INSERT INTO "main"."tb_Menu" VALUES (5, 'Fish Burger', 10000, 'fishburger');
INSERT INTO "main"."tb_Menu" VALUES (6, 'Katsu Burger', 11000, 'katsuburger');
INSERT INTO "main"."tb_Menu" VALUES (7, 'Garden Burger', 11000, 'gardenburger');
INSERT INTO "main"."tb_Menu" VALUES (8, 'Mushroom Burger', 16000, 'mushroomburger');

-- ----------------------------
-- Table structure for tb_Order
-- ----------------------------
DROP TABLE IF EXISTS "main"."tb_Order";
CREATE TABLE "tb_Order" (
    "orderId"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "orderTotal"  INTEGER,
    "orderUserId"  INTEGER,
    "orderDate"  TEXT,
    FOREIGN KEY ("orderUserId") REFERENCES "tb_User" ("userId")
);

-- ----------------------------
-- Records of tb_Order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_OrderDetail
-- ----------------------------
DROP TABLE IF EXISTS "main"."tb_OrderDetail";
CREATE TABLE "tb_OrderDetail" (
    "orderDetailId"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    "orderDetailOrderId"  INTEGER,
    "orderDetailMenuId"  INTEGER,
    "orderDetailMenuAmount"  INTEGER,
    CONSTRAINT "fk0" FOREIGN KEY ("orderDetailOrderId") REFERENCES "tb_Order" ("orderId"),
    FOREIGN KEY ("orderDetailMenuId") REFERENCES "tb_Menu" ("menuId")
);

-- ----------------------------
-- Records of tb_OrderDetail
-- ----------------------------

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
