<template>
  <div class="container py-4">
    <h2>卖家等级调整</h2>
    <div v-if="sellers.length === 0">暂无卖家</div>
    <table class="table table-bordered" v-else>
      <thead>
        <tr>
          <th>用户名</th>
          <th>当前等级</th>
          <th>当前手续费(%)</th>
          <th>调整等级</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="seller in sellers" :key="seller.id">
          <td>{{ seller.username }}</td>
          <td>{{ seller.level }}</td>
          <td>{{ seller.feeRate }}</td>
          <td>
            <select v-model="seller.level" @change="updateLevel(seller)">
              <option v-for="n in 5" :key="n" :value="n">{{ n }}级</option>
            </select>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
const sellers = ref([])
const levelFeeMap = { 1: 1, 2: 2, 3: 3, 4: 4, 5: 5 }
const load = () => {
  // 卖家：role为SELLER或有商品发布的用户
  const users = JSON.parse(localStorage.getItem('users') || '[]')
  const products = JSON.parse(localStorage.getItem('products') || '[]')
  const sellerIds = new Set(products.map(p => p.merchantId))
  sellers.value = users.filter(u => u.role === 'SELLER' || sellerIds.has(u.id?.toString() || u.id)).map(u => {
    const level = Number(localStorage.getItem(`level_${u.id}`) || 5)
    const feeRate = Number(localStorage.getItem(`fee_rate_${u.id}`) || levelFeeMap[level])
    return { ...u, level, feeRate }
  })
}
const updateLevel = (seller) => {
  localStorage.setItem(`level_${seller.id}`, seller.level)
  localStorage.setItem(`fee_rate_${seller.id}`, levelFeeMap[seller.level])
  seller.feeRate = levelFeeMap[seller.level]
}
onMounted(load)
</script>
<style scoped>
</style> 