<template>
  <div class="container py-4">
    <h2>平台费率设置</h2>
    <div class="mb-3">
      <label>全局默认费率(%)：</label>
      <input type="number" v-model.number="globalFee" min="0" max="100" class="form-control w-auto d-inline-block" style="width:100px;">
      <button class="btn btn-primary ms-2" @click="saveGlobalFee">保存</button>
    </div>
    <h5>分等级费率：</h5>
    <table class="table table-bordered w-auto">
      <thead><tr><th>等级</th><th>费率(%)</th><th>操作</th></tr></thead>
      <tbody>
        <tr v-for="n in 5" :key="n">
          <td>{{ n }}级</td>
          <td><input type="number" v-model.number="levelFees[n]" min="0" max="100" class="form-control w-auto" style="width:80px;"></td>
          <td><button class="btn btn-success btn-sm" @click="saveLevelFee(n)">保存</button></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
const globalFee = ref(5)
const levelFees = ref({ 1: 1, 2: 2, 3: 3, 4: 4, 5: 5 })
const load = () => {
  globalFee.value = Number(localStorage.getItem('global_fee_rate') || 5)
  for (let n = 1; n <= 5; n++) {
    levelFees.value[n] = Number(localStorage.getItem(`level_fee_rate_${n}`) || n)
  }
}
const saveGlobalFee = () => {
  localStorage.setItem('global_fee_rate', globalFee.value)
  alert('全局费率已保存！')
}
const saveLevelFee = (n) => {
  localStorage.setItem(`level_fee_rate_${n}`, levelFees.value[n])
  alert(`等级${n}费率已保存！`)
}
onMounted(load)
</script>
<style scoped>
</style> 