<template>
	<div class="container">
		<div class="row">
			<div class="col-3">
				<div class="card" style="margin-top: 20px;">
					<div class="card-body">
						管理员：{{ $store.state.user.username }}
					</div>
				</div>
			</div>
			<div class="col-9">
				<div class="card" style="margin-top: 20px;">
					<div class="card-header">
						<span style="font-size: 130%">pending_users</span>
					</div>
					<div class="card-body">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>名称</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="user in users" :key="user.id">
									<td>{{ user.username }}</td>
									<td>{{ user.createTime }}</td>
									<td>
										<button type="button" class="btn btn-secondary" style="margin-right: 10px;"
											data-bs-toggle="modal"
											:data-bs-target="'#update-bot-modal-' + user.id">修改</button>

										<div class="modal fade" :id="'update-bot-modal-' + user.id" tabindex="-1">
											<div class="modal-dialog modal-xl">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title">status</h5>
														<button type="button" class="btn-close" data-bs-dismiss="modal"
															aria-label="Close"></button>
													</div>
													<div class="modal-body">

														<div class="mb-3">
															<label for="update-bot-status"
																class="form-label">status</label>
															<select v-model="user.status" class="form-control"
																id="update-bot-status" rows="3">
																<option value="PENDING">PENDING</option>
																<option value="APPROVED">APPROVED</option>
																<option value="REJECTED">REJECTED</option>
															</select>
														</div>
													</div>
													<div class="modal-footer">
														<div class="error-message">{{ useradd.error_message }}</div>
														<button type="button" class="btn btn-primary"
															@click="update_bot(user)">保存修改</button>
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">取消</button>
													</div>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import {
	ref,
	reactive
} from 'vue'
import $ from 'jquery'
import {
	useStore
} from 'vuex'
// import { Modal } from 'bootstrap';

export default {

	setup() {

		const store = useStore();
		let users = ref([]);

		const useradd = reactive({
			status: "",
			error_message: "",
		});

		const refresh_bots = () => {
			$.ajax({
				url: "http://localhost:8088/api/admin/getpendinglist/",
				type: "get",
				headers: {
					Authorization: "Bearer " + store.state.user.token,
				},
				success(resp) {
					console.log(resp);
					users.value = resp;
				}
			})
		}
		console.log("refresh_bots");
		refresh_bots();


		const update_bot = (user) => {
			useradd.error_message = "";
			$.ajax({
				url: "http://localhost:8088/audit-user/",
				type: "post",
				data: JSON.stringify({
					userId: user.id,
					status: user.status,
				}),
				headers: {
					Authorization: "Bearer " + store.state.user.token,
				},
				contentType: "application/json",
				success(resp) {
					if (resp.error_message === "success") {
						refresh_bots();
					} else {
						useradd.error_message = resp.error_message;
					}
				}
			})
			console.log(user.status);
		}

		return {
			users,
			update_bot,
			useradd,
		}
	}
}
</script>

<style scoped>
div.error-message {
	color: red;
}
</style>