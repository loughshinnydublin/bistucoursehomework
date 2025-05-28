<template>
	<div class="container">
		<!-- 搜索栏 -->
		<div class="search-bar">
			<div class="input-group mb-3">
				<input type="text" class="form-control" placeholder="搜索商品..." v-model="searchQuery">
				<button class="btn btn-primary" type="button" @click="searchProducts">搜索</button>
			</div>
		</div>

		<!-- 商品分类 -->
		<div class="category-filter mb-3">
			<div class="btn-group" role="group">
				<button type="button" class="btn btn-outline-primary" :class="{ active: currentCategory === 'all' }"
					@click="filterByCategory('all')">全部</button>
				<button type="button" class="btn btn-outline-primary"
					:class="{ active: currentCategory === 'electronics' }"
					@click="filterByCategory('electronics')">电子产品</button>
				<button type="button" class="btn btn-outline-primary"
					:class="{ active: currentCategory === 'clothing' }"
					@click="filterByCategory('clothing')">服装</button>
				<button type="button" class="btn btn-outline-primary" :class="{ active: currentCategory === 'books' }"
					@click="filterByCategory('books')">图书</button>
			</div>
		</div>

		<!-- 商品列表 -->
		<div class="row">
			<div class="col-md-4 mb-4" v-for="product in filteredProducts" :key="product.id">
				<div class="card h-100" @click="viewProduct(product)">
					<img :src="product.image" class="card-img-top" :alt="product.name">
					<div class="card-body">
						<h5 class="card-title">{{ product.name }}</h5>
						<p class="card-text">{{ product.description }}</p>
						<div class="d-flex justify-content-between align-items-center">
							<span class="price">¥{{ product.price }}</span>
							<button class="btn btn-primary" @click.stop="addToCart(product)">加入购物车</button>
							<div class="text-end">
								{{ error_message }}
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 分页 -->
		<nav aria-label="Page navigation" class="mt-4">
			<ul class="pagination justify-content-center">
				<li class="page-item" :class="{ disabled: currentPage === 1 }">
					<a class="page-link" href="#" @click.prevent="changePage(currentPage - 1)">上一页</a>
				</li>
				<li class="page-item" v-for="page in totalPages" :key="page" :class="{ active: currentPage === page }">
					<a class="page-link" href="#" @click.prevent="changePage(page)">{{ page }}</a>
				</li>
				<li class="page-item" :class="{ disabled: currentPage === totalPages }">
					<a class="page-link" href="#" @click.prevent="changePage(currentPage + 1)">下一页</a>
				</li>
			</ul>
		</nav>
	</div>
</template>

<script>
import { ref, computed } from 'vue'
import $ from 'jquery'
import { useStore } from 'vuex'


export default {
	setup() {
		const store = useStore();
		const products = ref([
			{
				id: 1,
				name: "iPhone 15 Pro",
				description: "苹果最新旗舰手机，性能强劲，影像系统升级。",
				price: 8999,
				image: "https://img2.baidu.com/it/u=1234567890,1234567890&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
				category: "electronics"
			},
			{
				id: 2,
				name: "Nike 跑鞋",
				description: "舒适透气，适合日常运动。",
				price: 599,
				image: "https://img2.baidu.com/it/u=2345678901,2345678901&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
				category: "clothing"
			},
			{
				id: 3,
				name: "《三体》",
				description: "刘慈欣著，科幻经典，值得一读。",
				price: 49,
				image: "https://img2.baidu.com/it/u=3456789012,3456789012&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
				category: "books"
			}
		])
		const searchQuery = ref('')
		const currentCategory = ref('all')
		const currentPage = ref(1)
		const pageSize = 5
		const error_message = ref('');

		// 获取商品列表
		const getProducts = () => {
			$.ajax({
				url: "http://localhost:8088/api/product/getlist/",
				type: "get",
				success(resp) {
					products.value = resp
					products.value.forEach(product => {
						product.image = []; // 初始化，防止出错
						getImage(product);
					});
				}
			})
		}

		//获取图片（后端未使用ProductDTO）
		const getImage = (product) => {
			$.ajax({
				url: "http://localhost:8088/api/product/image/",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(product.id),  // BigInteger需要JSON.stringify
				headers: {
					Authorization: "Bearer " + store.state.user.token,
				},
				success(resp) {
					console.log(resp);
					product.image = resp.imageUrl; // 提取 imageUrl
				}
			})
		}

		// 搜索商品
		const searchProducts = () => {
			// 实现搜索逻辑
		}

		// 按分类筛选
		const filterByCategory = (category) => {
			currentCategory.value = category
		}

		// 加入购物车
		const addToCart = (product) => {
			store.dispatch("getinfo", {
				success(resp) {
					console.log("dengluchenggong")
					console.log(resp);

					// ——————————————————————————————————————————————————————
					$.ajax({
						url: "http://localhost:8088/api/cart/add",
						type: "post",
						contentType: "application/json",
						data: JSON.stringify({
							productId: product.id,
						}),
						headers: {
							Authorization: "Bearer " + store.state.user.token,
						},
						success(resp) {
							console.log(resp);
							alert(`已将【${product.name}】加入购物车！`);
						}
					})
					// ——————————————————————————————————————————————————————

				},
				error() {
					error_message.value = "未登录";
				}
			})
		}

		// 切换页码
		const changePage = (page) => {
			if (page >= 1 && page <= totalPages.value) {
				currentPage.value = page
			}
		}

		// 计算属性：筛选后的商品列表
		const filteredProducts = computed(() => {
			let result = products.value

			// 搜索过滤
			if (searchQuery.value) {
				result = result.filter(product =>
					product.name.toLowerCase().includes(searchQuery.value.toLowerCase()) ||
					product.description.toLowerCase().includes(searchQuery.value.toLowerCase())
				)
			}

			// 分类过滤
			if (currentCategory.value !== 'all') {
				result = result.filter(product => product.category === currentCategory.value)
			}

			// 分页
			const start = (currentPage.value - 1) * pageSize
			const end = start + pageSize
			return result.slice(start, end)
		})

		// 计算总页数
		const totalPages = computed(() => {
			return Math.ceil(products.value.length / pageSize)
		})

		// 初始化加载商品
		getProducts()

		const viewProduct = (product) => {
			console.log(product.id);
			$.ajax({
				url: "http://localhost:8088/api/history",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify({
					product_id: product.id,

				}),
				headers: {
					Authorization: "Bearer " + store.state.user.token,
				},
				success(resp) {
					console.log("viewing");
					console.log(resp);
					//跳转到商品详情页
				},
				error() {
					error_message.value = "未登录"
				}
			})

		}

		return {
			products,
			getImage,
			searchQuery,
			currentCategory,
			currentPage,
			filteredProducts,
			totalPages,
			searchProducts,
			filterByCategory,
			addToCart,
			changePage,
			viewProduct,
			error_message
		}
	}
}
</script>

<style scoped>
.search-bar {
	margin: 20px 0;
}

.category-filter {
	margin-bottom: 20px;
}

.card {
	transition: transform 0.2s;
}

.card:hover {
	transform: translateY(-5px);
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-img-top {
	height: 200px;
	object-fit: cover;
}

.price {
	font-size: 1.2em;
	font-weight: bold;
	color: #e74c3c;
}

.btn-group .btn {
	margin-right: 5px;
}

.btn-group .btn.active {
	background-color: #007bff;
	color: white;
}
</style>