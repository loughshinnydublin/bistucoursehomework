import $ from 'jquery';
import router from '@/router/index';

export default {
	state: {
		id: "",
		username: "",
		photo: "",
		token: "",
		role:"",
		// token: localStorage.getItem("jwt_token"),
		isLogin: false,

	},
	getters: {},
	mutations: {
		updateUser(state, user) {
			state.id = user.id;
			state.username = user.username;
			// state.photo = user.photo;
			state.isLogin = user.isLogin;
			state.role = user.role;
			console.log(user);
		},
		updateToken(state, token) {
			state.token = token;
		},
		logout(state) {
			state.id = "";
			state.username = "";
			// state.photo = "";
			state.token = "";
			state.role = "";
			state.isLogin = false;
		}
	},
	//修改state的函数
	actions: {
		login(context, data) {
			$.ajax({
				url: "http://localhost:8088/api/captcha/verify",
				type: "post",
				contentType: "application/json",  // 明确指定 Content-Type 为 JSON
				data: JSON.stringify({
					captcha: data.captcha,
				}),
				success(resp) {
					if (resp.error_message === "success") {
						$.ajax({
							url: "http://localhost:8088/api/user/account/login/",
							type: "post",
							data: {
								username: data.username,
								password: data.password,
							},
							success(resp) {
								console.log("actions>login>resp:")
								//actions调用mutation函数需要commit
								if (resp.error_message === "success") //在后端定义
								{
									console.log("登录返回数据：", resp);
									localStorage.setItem("jwt_token", resp.token); //持久化登录
									context.commit("updateToken", resp.token);
									console.log(resp);
									data.success(resp);
									router.push({ name: "home" });	//在view跳转没反应（因为没调用success回调函数
								} else {
									data.error(resp);
									console.log(resp);
								}
							},
							error(resp) {
								resp.error_message = "用户名或密码错误";
								data.error(resp);
								console.log(resp);
							}
						});
					} else {
						data.error(resp);
					}
				}
			})

		},

		getinfo(context, data) {
			$.ajax({
				url: "http://localhost:8088/api/user/account/info/",
				type: "get",
				headers: {
					Authorization: "Bearer " + context.state.token, //context改成store?
					// 在后端SecurityConfig中定义
				},
				// async: false,
				success(resp) {
					if (resp.error_message === "success") {
						context.commit("updateUser", {
							...resp, //解构resp中的信息，放在当前的对象中
							isLogin: true,
						});
						data.success(resp);
						// console.log(resp);
					} else {
						data.error(resp);
					}
				},
				error(resp) {
					data.error(resp);
				}
			})
		},

		logout(context) {
			localStorage.removeItem("jwt_token");

			//fake cart, history
			localStorage.removeItem("cart");
			localStorage.removeItem("history");

			context.commit("logout");
		},

	},
	modules: {}
}