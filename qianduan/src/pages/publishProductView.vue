<template>
  <div class="container py-4">
    <h1>成为商家</h1>
    <button class="btn btn-primary" @click="merchant_register">申请</button>

    <h2>发布商品</h2>
    <form @submit.prevent="publish">
      <div class="mb-3">
        <label class="form-label">商品名称</label>
        <input v-model="name" class="form-control" required />
      </div>
      <div class="mb-3">
        <label class="form-label">价格</label>
        <input v-model="price" class="form-control" />
      </div>
      <div class="mb-3">
        <label class="form-label">分类</label>
        <input v-model="category" class="form-control" required />
      </div>
      <div class="mb-3">
        <label class="form-label">描述</label>
        <textarea v-model="description" class="form-control" rows="3"></textarea>
      </div>
      <div class="mb-3">
        <label class="form-label">图片链接</label>
        <input v-model="image" class="form-control" />
      </div>
      <button class="btn btn-primary" type="submit">发布</button>
    </form>
    <div v-if="msg" class="alert alert-info mt-3">{{ msg }}</div>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import $ from 'jquery'; // Import jquery
import { useStore } from 'vuex'; // Import useStore

const name = ref('')
const price = ref('')
const category = ref('')
const description = ref('')
const image = ref('')
const msg = ref('')

const store = useStore(); // Get store instance

const merchant_register = () => {
  $.ajax({
    url: "http://localhost:8088/api/merchant/register/",
    type: "get",
    contentType: "application/json",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    success(resp) {
      if (resp.error_message === "success") {
        msg.value = '申请成功！等待管理员审核。';
      }
    }
  })
}

const publish = () => {
  msg.value = ''; // Clear previous message

  $.ajax({
    url: "http://localhost:8088/api/product/add/",
    type: "post",
    contentType: "application/json",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    data: JSON.stringify({
      name: name.value,
      price: price.value,
      category: category.value,
      description: description.value || '',
      size: "",
      bargain_allowed: false,
      product_condition: "",
      quantity: 1,
      imgUrl: image.value
    }),
    success(resp) {
      if (resp.error_message === "success") {
        msg.value = '发布成功！等待管理员审核。';
      } else {
        msg.value = '发布失败：' + resp.error_message;
      }
    },
    error(xhr, status, error) {
      msg.value = '发布失败：' + error;
      console.error("API Error:", xhr.responseText);
      console.log(price.value);
    }
  });
}
</script>

<style scoped></style>