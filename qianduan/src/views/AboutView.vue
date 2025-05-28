<template>
  <div class="row justify-content-md-center">
    <div class="col-3">
      <form @submit.prevent="login">
        <div class="mb-3">
          <label for="username" class="form-label">用户名</label>
          <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">密码</label>
          <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
        </div>

        <div class="mb-3">
          <label for="captcha" class="form-label">验证码</label>
          <img src="http://localhost:8088/api/captcha/">
          <input v-model="captcha" type="text" class="form-control" id="captcha" placeholder="请输入验证码：">
        </div>

        <div class="error-message">{{ error_message }}</div>
        <button type="submit" class="btn btn-primary">提交</button>
      </form>
    </div>
  </div>

  <div>
    <img src="https://cdn.acwing.com/media/user/profile/photo/247763_lg_f72e146e58.jpg">
  </div>
</template>

<script>
import {
  ref
} from 'vue'
import $ from 'jquery'


export default {
  setup() {

    let username = ref('');
    let password = ref('');
    let error_message = ref('');
    let captcha = ref('');


    const login = () => {
      error_message.value = "";
      $.ajax({
        url: "http://localhost:8088/api/captcha/verify",
        type: "post",
        contentType: "application/json",  // 明确指定 Content-Type 为 JSON
        data: JSON.stringify({
          captcha: captcha.value,
        }),
        success(resp) {
          console.log(resp);
        },
        error(resp) {
          // data.error(resp);
          console.log(resp);
        }
      })
    }



    return {
      username,
      password,
      captcha,
      error_message,
      login,
    }
  }
}
</script>

<style scoped>
button {
  width: 100%;
}

div.error-message {
  color: red;
}
</style>
