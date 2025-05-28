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
					<label for="role" class="form-label">身份</label>
					<select v-model="role" class="form-select" id="role">
						<option value="user">商家/个人用户</option>
						<option value="admin">管理员</option>
					</select>
				</div>

				<div class="mb-3">
					<label for="captcha" class="form-label">验证码</label>
					<img src="http://localhost:8088/api/captcha/">
					<input v-model="captcha" type="text" class="form-control" id="captcha" placeholder="请输入验证码：">
				</div>

				<div class="error-message">{{ error_message }}</div>
				<button type="submit" class="btn btn-primary">提交</button>
				<button type="button" class="btn btn-link mt-2 w-100" @click="goRegister">没有账号？去注册</button>
			</form>
		</div>
	</div>
</template>

<script>
import {
	useStore
} from 'vuex'
import {
	ref
} from 'vue'
import router from '@/router/index'

export default {
	setup() {
		const store = useStore();
		let username = ref('');
		let password = ref('');
		let error_message = ref('');
		let role = ref('user');
		let captcha = ref('');

		const jwt_token = localStorage.getItem("jwt_token");
		if (jwt_token) {
			console.log("执行getinfo");
			store.commit("updateToken", jwt_token);
			store.dispatch("getinfo", {
				success() {
					router.push({
						name: "home",
					});
				},
				error() {
					console.log("获取信息失败");
				}
			})

		}

		const login = () => {
			error_message.value = "";
			store.dispatch("login", {
				username: username.value,
				password: password.value,
				captcha: captcha.value,
				role: role.value,
				success() {
					store.dispatch("getinfo", {
						success(resp) {
							console.log("dengluchenggong")
							console.log(resp);
							if (role.value === 'admin') {
								router.push('/admin/audit/');
							} else {
								router.push({ name: 'home' });
							}
						}
					})
				},
				error(resp) {
					error_message.value = resp.error_message;
				}
			})
		}

		const goRegister = () => {
			router.push({ name: 'user_account_register' });
		}

		return {
			username,
			password,
			captcha,
			error_message,
			login,
			role,
			goRegister,
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