<template>
  <div class="container py-4">
    <h2>我的订单</h2>
    <div v-if="orders.length === 0">暂无订单</div>
    <ul class="list-group" v-else>
      <li class="list-group-item mb-3" v-for="order in orders" :key="order.id">
        <div class="mb-2">
          <strong>订单号：</strong>{{ order.id }}<br>
          <strong>下单时间：</strong>{{ order.createTime }}<br>
          <strong>状态：</strong>{{ order.status }}
          <div class="mb-2">
            <span v-if="order.status === 'PAID'" class="text-warning">待发货</span>
            <span v-else-if="order.status === 'SHIPPED'" class="text-primary">已发货</span>
            <span v-else-if="order.status === 'RECEIVED'" class="text-success">已收货</span>
            <span v-else-if="order.status === '退款中'" class="text-danger">退款中</span>
            <span v-else-if="order.status === '退款成功'" class="text-success">退款成功</span>
          </div>
        </div>
        <ul>
          <li v-for="item in order.items" :key="item.id">
            {{ item.name }} ×{{ item.quantity }} - ￥{{ item.price }}
            <template v-if="order.status === '已收货'">
              <button class="btn btn-link btn-sm p-0 ms-2" @click="openReview(order.id, item.id)">评价</button>
              <span v-if="getReview(order.id, item.id)">
                | 评价：{{ getReview(order.id, item.id).text }}
                | 星级：{{ getReview(order.id, item.id).star }}
              </span>
            </template>
          </li>
        </ul>
        <div class="mt-2">
          <strong>总价：</strong>￥{{ order.totalAmount }}
        </div>
        <button
          v-if="order.status !== 'RECEIVED' && order.status !== 'SHIPPED' && order.status !== '退款中' && order.status !== '退款成功'"
          class="btn btn-success btn-sm mt-2" @click="confirm(order.id)">刷新订单</button>

        <button
          v-if="order.status === 'SHIPPED'"
          class="btn btn-danger btn-sm mt-2 ms-2" @click="receive(order.id)">确认收货</button>

        <!-- <button
          v-if="(order.status === 'RECEIVED' || order.status === 'SHIPPED') && order.status !== '退款中' && order.status !== '退款成功'"
          class="btn btn-danger btn-sm mt-2 ms-2" @click="refund(order.id)">申请退款</button> -->
        <button v-if="order.status === '退款中'" class="btn btn-info btn-sm mt-2 ms-2"
          @click="refundSuccess(order.id, order.total)">退款成功</button>
        <!-- 服务态度评分 -->
        <div v-if="order.status === '已收货'">
          <span>商家服务态度评分：</span>
          <template v-if="getServiceScore(order.id)">
            <span class="text-warning">{{ getServiceScore(order.id) }}星</span>
          </template>
          <template v-else>
            <select v-model="serviceScoreInput" class="form-select d-inline-block w-auto" style="width:80px">
              <option disabled value="">请选择</option>
              <option v-for="n in 5" :key="n" :value="n">{{ n }}星</option>
            </select>
            <button class="btn btn-sm btn-outline-primary ms-2" @click="submitServiceScore(order.id)">提交评分</button>
          </template>
        </div>
        <!-- 卖家评分 -->
        <div v-if="order.status === '已收货'">
          <span>给卖家评分：</span>
          <template v-if="getSellerScore(order.id)">
            <span class="text-success">{{ getSellerScore(order.id) }}星</span>
          </template>
          <template v-else>
            <select v-model="sellerScoreInput" class="form-select d-inline-block w-auto" style="width:80px">
              <option disabled value="">请选择</option>
              <option v-for="n in 5" :key="n" :value="n">{{ n }}星</option>
            </select>
            <button class="btn btn-sm btn-outline-success ms-2"
              @click="submitSellerScore(order.id, order.sellerId)">提交评分</button>
          </template>
        </div>
      </li>
    </ul>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import $ from 'jquery'

const store = useStore()
const orders = ref([])
const reviews = ref({})
const serviceScores = ref({})
const sellerScores = ref({})
const reviewInput = ref('')
const reviewStar = ref(5)
const reviewOrderId = ref(null)
const reviewItemId = ref(null)
const serviceScoreInput = ref('')
const sellerScoreInput = ref('')

const loadOrders = () => {
  $.ajax({
    url: "http://localhost:8088/api/order/list",
    type: "get",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    success: (resp) => {
      orders.value = resp
      console.log(resp);
    }
  })
}

loadOrders()

const confirm = (id) => {
  // shipOrder
  $.ajax({
    url: `http://localhost:8088/api/order/confirm/${id}`,
    type: "post",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    success: (resp) => {
      if (resp.error_message === "success") {
        $.ajax({
          url: `http://localhost:8088/api/order/ship/${id}`,
          type: "post",
          headers: {
            Authorization: "Bearer " + store.state.user.token,
          },
          success(resp) {
            console.log(resp);
            if (resp.error_message === "success") {
              loadOrders()
            }
          }
        })
      }
    }
  })
}

const receive = (id) => {
$.ajax({
    url: `http://localhost:8088/api/order/receive/${id}`,
    type: "post",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    success: () => {
      loadOrders();
    }
  })
}

// const refund = (id) => {
//   $.ajax({
//     url: `http://localhost:8088/api/order/refund/${id}`,
//     type: "post",
//     headers: {
//       Authorization: "Bearer " + store.state.user.token,
//     },
//     success: (resp) => {
//       if (resp.error_message === "success") {
//         loadOrders()
//       }
//     }
//   })
// }

const refundSuccess = (id, total) => {
  $.ajax({
    url: `http://localhost:8088/api/order/refund/approve/${id}`,
    type: "post",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    success: (resp) => {
      if (resp.error_message === "success") {
        // 退钱给买家
        $.ajax({
          url: "http://localhost:8088/api/wallet/recharge",
          type: "post",
          data: {
            amount: total
          },
          headers: {
            Authorization: "Bearer " + store.state.user.token,
          },
          success: (resp) => {
            if (resp.error_message === "success") {
              loadOrders()
            }
          }
        })
      }
    }
  })
}

function openReview(orderId, itemId) {
  reviewOrderId.value = orderId
  reviewItemId.value = itemId
  reviewInput.value = ''
  reviewStar.value = 5
  setTimeout(() => {
    const text = prompt('请输入评价内容：', '')
    if (text !== null) {
      const star = Number(prompt('请输入星级（1-5）：', '5'))
      if (star >= 1 && star <= 5) {
        const key = `${orderId}_${itemId}`
        reviews.value[key] = { text, star }
        // TODO: 添加评价API
      }
    }
  }, 100)
}

function getReview(orderId, itemId) {
  return reviews.value[`${orderId}_${itemId}`]
}

function submitServiceScore(orderId) {
  if (!serviceScoreInput.value) return
  serviceScores.value[orderId] = serviceScoreInput.value
  // TODO: 添加服务评分API
}

function getServiceScore(orderId) {
  return serviceScores.value[orderId]
}

function submitSellerScore(orderId, sellerId) {
  if (!sellerScoreInput.value) return
  sellerScores.value[sellerId] = sellerScores.value[sellerId] || []
  sellerScores.value[sellerId].push(Number(sellerScoreInput.value))
  // TODO: 添加卖家评分API
}

function getSellerScore(orderId) {
  const order = orders.value.find(o => o.id === orderId)
  if (!order) return null
  const sellerId = order.sellerId
  const scores = sellerScores.value[sellerId] || []
  if (scores.length === 0) return null
  // 显示平均分（保留1位小数）
  const avg = (scores.reduce((a, b) => a + b, 0) / scores.length).toFixed(1)
  return avg
}

onMounted(() => {
  if (store.state.user.id) {
    loadOrders()
  }
})
</script>