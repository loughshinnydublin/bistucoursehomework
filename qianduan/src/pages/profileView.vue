<template>
    <div class="container mt-4">
      <h3>用户个人中心</h3>
  
      <!-- 头像 -->
      <div class="mb-3">
        <label class="form-label">头像</label><br>
        <img :src="user.photo || defaultAvatar" alt="头像" width="100" height="100" class="rounded-circle mb-2">
        <input type="file" @change="uploadAvatar" class="form-control">
      </div>
  
      <!-- 基本信息 -->
      <div class="mb-3">
        <label class="form-label">用户名</label>
        <input type="text" class="form-control" :value="user.username" disabled>
      </div>
  
      <div class="mb-3">
        <label class="form-label">邮箱</label>
        <input type="email" class="form-control" v-model="user.email">
      </div>
  
      <div class="mb-3">
        <label class="form-label">手机号</label>
        <input type="text" class="form-control" v-model="user.phone">
      </div>
  
      <div class="mb-3">
        <label class="form-label">收货地址</label>
        <input type="text" class="form-control" v-model="user.address">
      </div>
  
      <div class="mb-3">
        <label class="form-label">个人简介</label>
        <textarea class="form-control" rows="3" v-model="user.bio"></textarea>
      </div>
  
      <!-- 修改密码 -->
      <hr>
      <h5>修改密码</h5>
      <div class="mb-3">
        <input type="password" class="form-control" v-model="password.old" placeholder="原密码">
      </div>
      <div class="mb-3">
        <input type="password" class="form-control" v-model="password.new1" placeholder="新密码">
      </div>
      <div class="mb-3">
        <input type="password" class="form-control" v-model="password.new2" placeholder="确认新密码">
      </div>
  
      <!-- 提交按钮 -->
      <button class="btn btn-primary" @click="saveChanges">保存修改</button>
    </div>
  </template>
  
  <script setup>
  import { computed, ref } from "vue";
  import { useStore } from "vuex";
  
  const store = useStore();
  const defaultAvatar = "https://cdn.acwing.com/media/user/profile/photo/default.png";
  // 直接从store获取当前登录用户信息
  const user = computed(() => store.state.user);
  
  const password = ref({
    old: "",
    new1: "",
    new2: ""
  });
  
  const uploadAvatar = (e) => {
    const file = e.target.files[0];
    if (!file) return;
    const formData = new FormData();
    formData.append("avatar", file);
    fetch("http://localhost:8088/user/avatar", {
      method: "POST",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      body: formData
    })
      .then(res => res.json())
      .then(data => {
        if (data.error_message === "success") {
          alert("上传成功");
        } else {
          alert("上传失败：" + data.error_message);
        }
      });
  };
  
  const saveChanges = () => {
    if (password.value.new1 !== password.value.new2) {
      alert("两次密码不一致");
      return;
    }
    // 提交用户基本信息到后端
    fetch("http://localhost:8088/api/user/account/update/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + store.state.user.token,
      },
      body: JSON.stringify({
        email: user.value.email,
        phone: user.value.phone,
        address: user.value.address,
        bio: user.value.bio,
      })
    })
      .then(res => res.json())
      .then(data => {
        if (data.error_message === "success") {
          alert("保存成功");
        } else {
          alert("保存失败：" + data.error_message);
        }
      });
    // 密码修改可根据后端接口另行实现
  };
  </script>
  