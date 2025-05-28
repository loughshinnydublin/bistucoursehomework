<template>
  <div class="container py-4">
    <h2>我的钱包</h2>
    <div class="fs-4 mt-4">当前余额：<span class="text-success">￥{{ wallet }}</span></div>
    <div class="fs-5 mt-3">当前积分：<span class="text-primary">{{ points }}</span>，可抵扣：￥{{ maxPointDiscount }}</div>
  </div>
</template>
<script setup>
import { ref, onMounted, computed } from 'vue'
import { useStore } from 'vuex'
import $ from 'jquery'

const store = useStore()
const wallet = ref(0)
const points = ref(0)
const maxPointDiscount = computed(() => Math.floor(points.value / 100))

const loadWalletInfo = () => {
  $.ajax({
    url: "http://localhost:8088/api/wallet/info",
    type: "get",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    success: (resp) => {
      wallet.value = resp.balance
      points.value = resp.points
    }
  })
}

onMounted(() => {
  loadWalletInfo()
})
</script> 