<template>
  <div class="container py-4">
    <h2 class="mb-4">购物车</h2>
    <div class="alert alert-info" v-if="cartItems.length === 0">
      你的购物车为空。
    </div>
    <ul class="list-group mb-3" v-else>
      <li class="list-group-item d-flex justify-content-between align-items-center" v-for="item in cartItems"
        :key="item.id">
        <div>
          <strong>{{ item.name }}</strong>
          <span class="text-muted ms-2">x{{ item.quantity }}</span>
        </div>
        <div>
          <span class="text-primary me-3">￥{{ item.price }}</span>
          <button class="btn btn-sm btn-outline-danger" @click="removeItem(item.id)">删除</button>
        </div>
      </li>
    </ul>
    <div v-if="cartItems.length > 0" class="d-flex flex-column align-items-end">
      <div class="mb-2 w-100 d-flex align-items-center">
        <label class="me-2">选择优惠券：</label>
        <select v-model="selectedCouponId" class="form-select w-auto">
          <option :value="''">不使用优惠券</option>
          <option v-for="coupon in coupons" :key="coupon.id" :value="coupon.id"
            :disabled="Number(totalPrice) < coupon.minAmount">
            {{ coupon.desc }}（截止{{ coupon.expire }}）
          </option>
        </select>
      </div>
      <div class="mb-2 w-100 d-flex align-items-center">
        <label class="me-2">积分抵扣：</label>
        <input type="checkbox" v-model="usePoints" class="form-check-input me-2" id="usePoints">
        <label for="usePoints" class="form-check-label">使用积分抵扣（当前积分：{{ points }}，可抵扣￥{{ maxPointDiscount }}）</label>
      </div>
      <span>总价：<strong class="text-danger">￥{{ finalPriceWithPoints }}</strong></span>
      <button class="btn btn-success mt-2" @click="checkout">去结算</button>
    </div>
  </div>
</template>

<script>

import $ from 'jquery'

export default {

  name: 'cartView',
  data() {
    return {
      cartItems: [],
      coupons: [
        { id: 1, code: 'SAVE10', desc: '满100减10元', discount: 10, minAmount: 100, expire: '2024-12-31' },
        { id: 2, code: 'SAVE20', desc: '满200减20元', discount: 20, minAmount: 200, expire: '2024-06-30' },
        { id: 3, code: 'VIP50', desc: '满500减50元', discount: 50, minAmount: 500, expire: '2025-01-01' }
      ],
      selectedCouponId: '',
      usePoints: false,
      points: 0,
      wallet: 0
    }
  },
  computed: {
    totalPrice() {
      return this.cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0).toFixed(2);
    },
    validCoupons() {
      const now = new Date();
      return this.coupons.filter(c =>
        new Date(c.expire) >= now
      );
    },
    finalPrice() {
      const coupon = this.coupons.find(c => c.id === Number(this.selectedCouponId));
      if (coupon && Number(this.totalPrice) >= coupon.minAmount) {
        return (this.totalPrice - coupon.discount).toFixed(2);
      }
      return this.totalPrice;
    },
    maxPointDiscount() {
      return Math.floor(this.points / 100);
    },
    finalPriceWithPoints() {
      let price = Number(this.finalPrice);
      if (this.usePoints) {
        price -= this.maxPointDiscount;
        if (price < 0) price = 0;
      }
      return price.toFixed(2);
    }
  },
  mounted() {
    this.loadCartItems();
    this.loadWalletInfo();
  },
  methods: {
    loadCartItems() {
      $.ajax({
        url: "http://localhost:8088/api/cart/list",
        type: "get",
        headers: {
          Authorization: "Bearer " + this.$store.state.user.token,
        },
        success: (resp) => {
          this.cartItems = resp;
          console.log(resp);
        }
      });
    },
    loadWalletInfo() {
      $.ajax({
        url: "http://localhost:8088/api/wallet/info",
        type: "get",
        headers: {
          Authorization: "Bearer " + this.$store.state.user.token,
        },
        success: (resp) => {
          this.wallet = resp.balance;
          this.points = resp.points;
        }
      });
    },
    removeItem(id) {
      $.ajax({
        url: `http://localhost:8088/api/cart/delete/${id}`,
        type: "delete",
        headers: {
          Authorization: "Bearer " + this.$store.state.user.token,
        },
        success: (resp) => {
          if (resp.error_message === "success") {
            this.loadCartItems();
          }
        }
      });
    },
    checkout() {
      const pay = Number(this.finalPriceWithPoints);
      if (this.wallet < pay) {
        alert('余额不足，无法结算！');
        return;
      }

      // 创建订单
      const order = {
        // userId: this.$store.state.user.id,
        // sellerId: this.cartItems[0].merchantId,
        totalAmount: pay,
        status: 'CREATED',
        //order_item, order_id先生成，item后传
        items: this.cartItems.map(item => ({
          productId: item.productId,
          quantity: item.quantity,
          price: item.price,
        }))
      };

      console.log(order);

      $.ajax({
        url: "http://localhost:8088/api/order/create",
        type: "post",
        contentType: "application/json",
        data: JSON.stringify(order),
        headers: {
          Authorization: "Bearer " + this.$store.state.user.token,
        },
        // 付款
        success: (resp) => {
          console.log(resp);
          order.id = resp.order_id;
          if (resp.error_message === "success") {
            // 扣除余额
            $.ajax({
              url: "http://localhost:8088/api/wallet/deduct",
              type: "post",
              data: {
                amount: pay
              },
              headers: {
                Authorization: "Bearer " + this.$store.state.user.token,
              },
              success: (resp) => {
                if (resp.error_message === "success") {
                  // 清空购物车
                  $.ajax({
                    url: `http://localhost:8088/api/cart/clear/`,
                    type: "delete",
                    headers: {
                      Authorization: "Bearer " + this.$store.state.user.token,
                    },
                    success: (resp) => {
                      if (resp.error_message === "success") {
                        // 扣除积分
                        if (this.usePoints) {
                          $.ajax({
                            url: "http://localhost:8088/api/wallet/use-points",
                            type: "post",
                            data: {
                              points: this.maxPointDiscount * 100
                            },
                            headers: {
                              Authorization: "Bearer " + this.$store.state.user.token,
                            },
                            success: (resp) => {
                              if (resp.error_message === "success") {
                                // 添加积分
                                $.ajax({
                                  url: "http://localhost:8088/api/wallet/add-points",
                                  type: "post",
                                  data: {
                                    points: Math.floor(pay)
                                  },
                                  headers: {
                                    Authorization: "Bearer " + this.$store.state.user.token,
                                  },
                                  success: (resp) => {
                                    if (resp.error_message === "success") {
                                      alert('结算成功，订单已生成！获得' + Math.floor(pay) + '积分！');
                                      this.loadWalletInfo();
                                      this.loadCartItems();
                                    }
                                  }
                                });
                              }
                            }
                          });
                        } else {
                          // 添加积分
                          $.ajax({
                            url: "http://localhost:8088/api/wallet/add-points",
                            type: "post",
                            data: {
                              points: Math.floor(pay)
                            },
                            headers: {
                              Authorization: "Bearer " + this.$store.state.user.token,
                            },
                            success: (resp) => {
                              if (resp.error_message === "success") {
                                order.status = 'PAID';
                                console.log(order);
                                $.ajax({
                                  url: "http://localhost:8088/api/order/update",
                                  type: "post",
                                  contentType: "application/json",
                                  data: JSON.stringify(order),
                                  headers: {
                                    Authorization: "Bearer " + this.$store.state.user.token,
                                  },
                                })
                                alert('结算成功，订单已生成！获得' + Math.floor(pay) + '积分！');
                                this.loadWalletInfo();
                                this.loadCartItems();
                              }
                            }
                          });
                        }
                      }
                    }
                  });
                }
              }
            });
          }
        }
      });
    }
  }
}
</script>

<style scoped>
.container {
  max-width: 700px;
}
</style>