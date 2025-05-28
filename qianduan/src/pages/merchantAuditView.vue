<template>
  <div class="container py-4">
    <h2>商家审核</h2>
    <div v-if="pendingUsers.length === 0">暂无待审核用户</div>
    <ul class="list-group" v-else>
      <li class="list-group-item mb-3" v-for="user in pendingUsers" :key="user.id">
        <div class="mb-2">
          <strong>用户名：</strong>{{ user.username }}<br>
          <strong>角色：</strong>{{ user.role }}
        </div>
        <button class="btn btn-success btn-sm me-2" @click="approve(user.id)">通过</button>
        <button class="btn btn-danger btn-sm" @click="reject(user.id)">驳回</button>
      </li>
    </ul>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
const pendingUsers = ref([])
const load = () => {
  const all = JSON.parse(localStorage.getItem('users') || '[]')
  pendingUsers.value = all.filter(u => u.status === '待审核')
}
const approve = (id) => {
  let all = JSON.parse(localStorage.getItem('users') || '[]')
  all = all.map(u => u.id === id ? { ...u, status: '正常' } : u)
  localStorage.setItem('users', JSON.stringify(all))
  load()
}
const reject = (id) => {
  let all = JSON.parse(localStorage.getItem('users') || '[]')
  all = all.filter(u => u.id !== id)
  localStorage.setItem('users', JSON.stringify(all))
  load()
}
onMounted(load)
</script>
<style scoped>
</style> 