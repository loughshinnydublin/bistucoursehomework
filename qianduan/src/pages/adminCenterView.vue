<template>
  <div class="container py-4">
    <h2>管理员中心</h2>
    <ul class="list-group">
      <li class="list-group-item"><router-link to="/admin/product-audit">商品审核</router-link></li>
      <li class="list-group-item"><router-link to="/admin/merchant-audit">商家审核</router-link></li>
      <li class="list-group-item"><router-link to="/admin/audit/">用户注册管理</router-link></li>
      <li class="list-group-item"><router-link to="/admin/level">卖家等级调整</router-link></li>
      <li class="list-group-item"><router-link to="/admin/fee">平台费率设置</router-link></li>
    </ul>
  </div>
</template>
<script setup>
</script>
<style scoped>
</style> 