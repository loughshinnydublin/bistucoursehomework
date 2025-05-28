# 微信小程序——订菜系统

### **微信小程序 + Spring Boot + MyBatis-Plus + MySQL 详细设计**

#### **技术选型**
- **后端**：Spring Boot + MyBatis-Plus + MySQL
- **前端**：uni-app + 微信小程序（基于 WXML + WXSS + JavaScript）
- **数据库**：MySQL
- **API 接口**：RESTful API，基于 Spring Boot
- **认证方式**：微信小程序登录（基于微信 openid）
- **部署**：Spring Boot 运行在服务器（如阿里云、腾讯云等）

---

## **1. 需求分析**
### **角色**
1. **要货人**：
    - 提交商品需求（商品种类、数量、交货日期）
    - 查看需求状态
    - 确认收到客户转发的货物
2. **客户**：
    - 汇总多个要货人的需求
    - 向供货商发送订单需求
    - 收到供货商报价后确认订单
    - 负责发货给要货人
3. **供货商**：
    - 接收客户的需求
    - 计算总报价并拆分各要货人的订单
    - 发货并上传物流信息
    - 订单完成后客户确认收货

---

## **2. 数据库设计（MySQL）**
数据库设计包含以下核心表：

### **2.1 商品表（product）**
记录商品信息，每个供货商只能供应一种商品。

| 字段名         | 类型           | 说明       |
|---------------|--------------|------------|
| id            | BIGINT       | 主键，自增 |
| name          | VARCHAR(255) | 商品名称   |
| category      | VARCHAR(100) | 商品种类   |
| description   | TEXT         | 商品描述   |

---

### **2.2 供货商表（supplier）**
记录供货商信息，每个供货商只能提供一种商品。

| 字段名         | 类型           | 说明       |
|---------------|--------------|------------|
| id            | BIGINT       | 主键，自增 |
| name          | VARCHAR(255) | 供货商名称 |
| product_id    | BIGINT       | 供应商品ID |
| price         | DECIMAL(10,2)| 商品单价   |
| contact       | VARCHAR(255) | 联系方式   |
| address       | TEXT         | 供货商地址 |

---

### **2.3 要货人表（requester）**
记录要货人的信息。

| 字段名         | 类型           | 说明       |
|---------------|--------------|------------|
| id            | BIGINT       | 主键，自增 |
| name          | VARCHAR(255) | 要货人姓名 |
| contact       | VARCHAR(255) | 联系方式   |
| address       | TEXT         | 地址       |

---

### **2.4 需求表（demand）**
记录要货人的需求。

| 字段名         | 类型           | 说明         |
|---------------|--------------|--------------|
| id            | BIGINT       | 主键，自增   |
| requester_id  | BIGINT       | 要货人ID     |
| product_id    | BIGINT       | 商品ID       |
| quantity      | INT          | 需求数量     |
| delivery_date | DATE         | 交货日期     |
| status        | VARCHAR(50)  | 状态（待处理/已汇总/已发货/完成） |

---

### **2.5 客户订单表（customer_order）**
客户向供货商下的订单。

| 字段名         | 类型           | 说明         |
|---------------|--------------|--------------|
| id            | BIGINT       | 主键，自增   |
| customer_id   | BIGINT       | 客户ID       |
| supplier_id   | BIGINT       | 供货商ID     |
| product_id    | BIGINT       | 商品ID       |
| total_quantity| INT          | 订单总数量   |
| total_price   | DECIMAL(10,2)| 订单总金额   |
| status        | VARCHAR(50)  | 订单状态（待确认/已确认/已发货/已完成） |

---

### **2.6 订单明细表（order_detail）**
客户订单的拆分详情，记录要货人的具体需求。

| 字段名         | 类型           | 说明         |
|---------------|--------------|--------------|
| id            | BIGINT       | 主键，自增   |
| order_id      | BIGINT       | 订单ID       |
| requester_id  | BIGINT       | 要货人ID     |
| product_id    | BIGINT       | 商品ID       |
| quantity      | INT          | 订单数量     |
| price         | DECIMAL(10,2)| 订单金额     |

---

### **2.7 发货表（shipment）**
供货商向客户发货的记录。
用于记录客户将货物发送给各个要货人的信息。

| **字段名**       | **类型**         | **说明**              |
|----------------|--------------|-------------------|
| id            | BIGINT       | 主键，自增       |
| order_detail_id | BIGINT       | 订单明细 ID，关联要货人的订单 |
| requester_id  | BIGINT       | 要货人 ID        |
| product_id    | BIGINT       | 商品 ID          |
| quantity      | INT          | 发货数量         |
| shipment_date | DATE         | 发货日期         |
| status        | VARCHAR(50)  | 发货状态（待发货/已发货/已签收） |

---

## **3. 后端 Spring Boot 设计**
- **框架**：
    - Spring Boot 3.x
    - MyBatis-Plus
    - Spring Security + JWT（认证）
    - Swagger（API 文档）
    - Lombok（简化代码）

---


### **3.3 主要 API 设计**
#### **需求提交 API**


#### **客户汇总需求 API**


#### **供货商发货 API**


## **4. 微信小程序前端**
- **小程序用户登录（微信 openid 认证）**
- **要货人提交需求**
- **客户汇总需求**
- **供货商管理订单**
- **物流追踪**

---

## **5. 部署**
- **后端**：部署在阿里云或腾讯云
- **前端**：微信小程序上线

---


## **2. 更新的数据库关系**
### **更新后的关系**
1. **供货商（supplier）向客户（customer）发货**，记录在 **shipment（发货表）**。
2. **客户（customer）收到货物后，将货物按照订单明细（order_detail）拆分**，然后通过 **customer_shipment（客户发货表）** 记录向**要货人（requester）** 的发货信息。
3. **要货人（requester）确认收货后，更新客户发货状态（status = '已签收'）**。

```text
shipment (供货商发货) ——> customer_order (客户订单) ——> order_detail (订单明细) ——> customer_shipment (客户发货) ——> requester (要货人)
```

---

## **3. 客户收到供货商货物并发货给要货人的 SQL 操作**
### **3.1 客户收到货物（更新供货商发货状态）**
```sql
UPDATE shipment 
SET status = '已签收' 
WHERE id = 1; -- 假设发货记录 ID 为 1
```

### **3.2 客户将货物发给要货人**
```sql
INSERT INTO customer_shipment (order_detail_id, requester_id, product_id, quantity, shipment_date, status) 
VALUES (1, 1, 101, 50, '2025-02-14', '已发货');

INSERT INTO customer_shipment (order_detail_id, requester_id, product_id, quantity, shipment_date, status) 
VALUES (2, 2, 101, 30, '2025-02-14', '已发货');
```

### **3.3 要货人确认收货**
```sql
UPDATE customer_shipment 
SET status = '已签收' 
WHERE id = 1;
```

---

## **4. 总结**
### **新增表：customer_shipment（客户发货表）**
- 记录 **客户向要货人** 发货的过程。
- 关联 `order_detail`，确保每个要货人收到自己的订单商品。

### **数据流**
1. **供货商 → 客户**（shipment）
2. **客户 → 要货人**（customer_shipment）
3. **要货人确认收货**（更新 `customer_shipment.status`）

这个设计确保了：
- 供货商 → 客户 → 要货人 物流流转清晰。
- 每个要货人的订单都能准确追踪。
- 订单处理状态可查询，便于管理。