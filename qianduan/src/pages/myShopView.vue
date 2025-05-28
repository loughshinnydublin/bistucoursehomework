<template>
  <div class="container py-4">
    <h2>我的商店</h2>
    <div class="mb-3">
      <span>卖家等级：<strong>{{ sellerLevel }}</strong> 级</span>
      <span class="ms-4">累计交易额：<strong>￥{{ totalAmount }}</strong></span>
      <span class="ms-4">累计交易数：<strong>{{ totalCount }}</strong></span>
      <span class="ms-4">当前手续费：<strong>{{ feeRate }}%</strong></span>
      <span class="ms-4">钱包余额：<strong>￥{{ wallet }}</strong></span>
    </div>
    <div v-if="myProducts.length === 0">你还没有发布商品。</div>
    <div class="row" v-else>
      <div class="col-md-4 mb-4" v-for="product in myProducts" :key="product.id">
        <div class="card h-100">
          <img :src="product.imgUrl" class="card-img-top" :alt="product.name">
          <div class="card-body">
            <h5 class="card-title">{{ product.name }}</h5>
            <p class="card-text">{{ product.description }}</p>
            <div class="d-flex justify-content-between align-items-center">
              <span class="price">¥{{ product.price }}</span>
              <div>
                <button class="btn btn-primary btn-sm me-2" @click="editProduct(product)">编辑</button>
                <button class="btn btn-danger btn-sm" @click="removeProduct(product.id)">下架</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Edit Product Modal -->
    <div class="modal fade" id="editProductModal" tabindex="-1" aria-labelledby="editProductModalLabel"
      aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="editProductModalLabel">编辑商品</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form>
              <div class="mb-3">
                <label for="edit-product-name" class="form-label">商品名称</label>
                <input type="text" class="form-control" id="edit-product-name" v-model="editingProduct.name">
              </div>
              <div class="mb-3">
                <label for="edit-product-price" class="form-label">价格</label>
                <input type="number" step="0.01" class="form-control" id="edit-product-price"
                  v-model.number="editingProduct.price">
              </div>
              <div class="mb-3">
                <label for="edit-product-category" class="form-label">分类</label>
                <input type="text" class="form-control" id="edit-product-category" v-model="editingProduct.category">
              </div>
              <div class="mb-3">
                <label for="edit-product-size" class="form-label">尺寸/规格</label>
                <input type="text" class="form-control" id="edit-product-size" v-model="editingProduct.size">
              </div>
              <div class="mb-3">
                <label for="edit-product-condition" class="form-label">商品状况</label>
                <input type="text" class="form-control" id="edit-product-condition"
                  v-model="editingProduct.productCondition">
              </div>
              <div class="mb-3">
                <label for="edit-product-quantity" class="form-label">库存数量</label>
                <input type="number" class="form-control" id="edit-product-quantity"
                  v-model.number="editingProduct.quantity">
              </div>
              <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="edit-bargain-allowed"
                  v-model="editingProduct.bargainAllowed">
                <label class="form-check-label" for="edit-bargain-allowed">允许议价</label>
              </div>
              <div class="mb-3">
                <label for="edit-product-imageurl" class="form-label">图片链接</label>
                <input type="text" class="form-control" id="edit-product-imageurl" v-model="editingProduct.imageUrl">
              </div>
              <div class="mb-3">
                <label for="edit-product-description" class="form-label">描述</label>
                <textarea class="form-control" id="edit-product-description" rows="3"
                  v-model="editingProduct.description"></textarea>
              </div>
              <!-- Add other fields as needed -->
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
            <button type="button" class="btn btn-primary" @click="saveProductChanges">保存修改</button>
          </div>
        </div>
      </div>
    </div>

    <h3 class="mt-5">商品发货管理</h3>
    <div v-if="mySellProducts.length === 0">无需发货</div>
    <ul class="list-group" v-else>
      <li class="list-group-item mb-3" v-for="product in mySellProducts" :key="product.id">
        <div class="mb-2">
          <strong>订单号：</strong>{{ product.orderId }}<br>
          <strong>货物号：</strong>{{ product.product.id }}<br>
          <strong>状态：</strong>{{ product.status }}
        </div>
        <button class="btn btn-primary btn-sm me-2" @click="ship(product)">发货</button>
      </li>
    </ul>

    <!-- 订单管理部分 -->
    <h3 class="mt-5">订单管理</h3>
    <div v-if="myOrders.length === 0">暂无订单</div>
    <ul class="list-group" v-else>
      <li class="list-group-item mb-3" v-for="order in myOrders" :key="order.id">
        <div class="mb-2">
          <strong>订单号：</strong>{{ order.id }}<br>
          <strong>下单时间：</strong>{{ order.time }}<br>
          <strong>状态：</strong>
          <span v-if="order.status === '待发货'" class="text-warning">待发货</span>
          <span v-else-if="order.status === '已发货'" class="text-primary">已发货</span>
          <span v-else-if="order.status === '已收货'" class="text-success">已收货</span>
          <span v-else-if="order.status === '退款中'" class="text-danger">退款中</span>
          <span v-else-if="order.status === '退款成功'" class="text-success">退款成功</span>
          <span v-else-if="order.status === '退款被驳回'" class="text-danger">退款被驳回</span>
        </div>
        <ul>
          <li v-for="item in order.items" :key="item.id">
            {{ item.name }} ×{{ item.quantity }} - ￥{{ item.price }}
          </li>
        </ul>
        <div class="mt-2">
          <strong>总价：</strong>￥{{ order.total }}
        </div>
        <button v-if="order.status === '待发货'" class="btn btn-primary btn-sm mt-2"
          @click="shipOrder(order.id)">发货</button>
        <!-- 退款审核 -->
        <div v-if="order.status === '退款中'">
          <button class="btn btn-success btn-sm mt-2 me-2" @click="approveRefund(order.id, order.total)">同意退款</button>
          <button class="btn btn-danger btn-sm mt-2" @click="rejectRefund(order.id)">驳回退款</button>
        </div>
        <div v-if="order.status === '退款被驳回' && order.refundReason">
          <span class="text-danger">驳回原因：{{ order.refundReason }}</span>
        </div>
      </li>
    </ul>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import $ from 'jquery' // Import jquery
import { useStore } from 'vuex' // Import useStore

const myProducts = ref([])
const myOrders = ref([])
const userId = localStorage.getItem('user_id') || '1'
const totalAmount = ref(0)
const totalCount = ref(0)
const sellerLevel = ref(5)
const feeRate = ref(5)
const wallet = ref(0)
const mySellProducts = ref([]);

const store = useStore(); // Get store instance

// State for editing modal
const editingProduct = ref({}); // Object to hold product data for editing

const calcLevel = (amount, count) => {
  if (amount >= 100000 && count >= 500) return { level: 1, fee: 1 }
  if (amount >= 20000 && count >= 100) return { level: 2, fee: 2 }
  if (amount >= 5000 && count >= 30) return { level: 3, fee: 3 }
  if (amount >= 1000 && count >= 10) return { level: 4, fee: 4 }
  return { level: 5, fee: 5 }
}


const load = () => {
  //根据userid = merchantid 获取 product
  $.ajax({
    url: "http://localhost:8088/api/merchant/getlist",
    type: "get",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    contentType: "application/json",
    success(resp) {
      console.log(resp)
      myProducts.value = resp;
    }
  })

  // 统计交易额和交易数 - Keep this logic if orders are loaded from backend too
  const orders = JSON.parse(localStorage.getItem('orders') || '[]') // Assuming orders still from localStorage for now
  let amount = 0, count = 0
  for (const order of orders) {
    for (const item of order.items) {
      if (item.merchantId == userId) {
        amount += item.price * item.quantity
        count += item.quantity
      }
    }
  }
  totalAmount.value = amount
  totalCount.value = count
  const { level, fee } = calcLevel(amount, count)
  sellerLevel.value = level
  feeRate.value = fee
}

const removeProduct = (id) => {
  if (confirm("确定要下架该商品吗？")) {
    $.ajax({
      url: "http://localhost:8088/api/product/remove/", // Backend API endpoint for delist
      type: "post",
      contentType: "application/json",
      data: JSON.stringify({ productId: id }), // Send product ID as JSON
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        if (resp.error_message === "success") {
          alert("商品下架成功");
          load(); // Reload product list after successful delist
        } else {
          alert("商品下架失败: " + resp.error_message);
        }
      },
      error(xhr, status, error) {
        console.error("Error delisting product:", error);
        alert("下架商品时发生错误。");
      }
    });
  }
}

const ship = (product) => {
  $.ajax({
    url: "http://localhost:8088/api/merchant/ship",
    type: "post",
    // contentType: "application/json",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    data: {
      productId: product.product.id
    },
    success(resp) {
      //商品可能来自多个卖家，所以暂时不用myorder管理，直接显示orderitem，商家只用送对应的单个货物
      // myOrders.value = resp;
      console.log(resp);
      loadMerchantOrders();
    }
  })
}

const loadMerchantOrders = () => {

  $.ajax({
    url: "http://localhost:8088/api/merchant/getproducts",
    type: "get",
    contentType: "application/json",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    success(resp) {
      //商品可能来自多个卖家，所以暂时不用myorder管理，直接显示orderitem，商家只用送对应的单个货物
      // myOrders.value = resp;
      mySellProducts.value = resp;
      console.log(mySellProducts.value)
    }
  })

  // This needs to be updated to fetch orders from backend
  const orders = JSON.parse(localStorage.getItem('orders') || '[]')
  myOrders.value = orders.filter(order => order.sellerId == userId)
}
const shipOrder = (orderId) => {
  // This needs to be updated to call a backend API
  console.log("Attempting to ship order with ID:", orderId); // Placeholder
  let orders = JSON.parse(localStorage.getItem('orders') || '[]')
  orders = orders.map(o => o.id === orderId ? { ...o, status: '已发货' } : o)
  localStorage.setItem('orders', JSON.stringify(orders))
  loadMerchantOrders()
}
const loadWallet = () => {
  // This needs to be updated to fetch wallet info from backend
  wallet.value = Number(localStorage.getItem(`wallet_${userId}`) || 0)
}
const approveRefund = (orderId, total) => {
  // This needs to be updated to call a backend API
  console.log("Attempting to approve refund for order with ID:", orderId); // Placeholder
  let orders = JSON.parse(localStorage.getItem('orders') || '[]')
  orders = orders.map(o => o.id === orderId ? { ...o, status: '退款成功' } : o)
  localStorage.setItem('orders', JSON.stringify(orders))
  // 退钱给买家 - This also needs backend interaction
  let wallet = Number(localStorage.getItem('wallet') || 1000)
  wallet += Number(total)
  localStorage.setItem('wallet', wallet)
  loadMerchantOrders()
}
const rejectRefund = (orderId) => {
  // This needs to be updated to call a backend API
  console.log("Attempting to reject refund for order with ID:", orderId); // Placeholder
  const reason = prompt('请输入驳回退款原因：')
  if (!reason) return
  let orders = JSON.parse(localStorage.getItem('orders') || '[]')
  orders = orders.map(o => o.id === orderId ? { ...o, status: '退款被驳回', refundReason: reason } : o)
  localStorage.setItem('orders', JSON.stringify(orders))
  loadMerchantOrders()
}

// New method to handle product editing
const editProduct = (product) => {
  editingProduct.value = { ...product }; // Copy product data to editingProduct
  // Use Bootstrap's modal API to show the modal
  if (window.bootstrap && window.bootstrap.Modal) { // Directly check and use window.bootstrap.Modal
    const editModal = new window.bootstrap.Modal(document.getElementById('editProductModal'));
    editModal.show();
    console.log("Edit modal shown using window.bootstrap.Modal."); // Added log
  } else {
    console.error("Bootstrap object or Modal constructor not found on window when trying to show modal."); // Updated error log
    // Fallback or error handling if Bootstrap Modal is not loaded
  }
}

// New method to save product changes
const saveProductChanges = () => {
  // TODO: Implement AJAX call to backend update endpoint
  console.log("Saving changes for:", editingProduct.value);

  $.ajax({
    url: "http://localhost:8088/user/product/update/", // Backend update API endpoint
    type: "post",
    contentType: "application/json",
    data: JSON.stringify(editingProduct.value), // Send edited product data as JSON
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    success(resp) {
      if (resp.error_message === "success") {
        // Reload product list after successful update
        load();
        // Close the modal
        const editModal = window.bootstrap.Modal.getInstance(document.getElementById('editProductModal'));
        if (editModal) {
          editModal.hide();
        }
      } else {
        // Display error message from backend
        alert("更新失败: " + resp.error_message);
      }
    },
    error(xhr, status, error) {
      console.error("Error updating product:", error);
      // Display a generic error message
      alert("更新商品时发生错误。");
      // Close the modal on error
      const editModal = window.bootstrap.Modal.getInstance(document.getElementById('editProductModal'));
      if (editModal) {
        editModal.hide();
      }
    }
  });
}

onMounted(() => {
  load()
  loadMerchantOrders() // Note: loadOrders still uses localStorage
  loadWallet() // Note: loadWallet still uses localStorage

  // Attempt to get Bootstrap Modal constructor after component is mounted
  if (window.bootstrap && window.bootstrap.Modal) {
    console.log("Bootstrap object and Modal constructor found on window after mounted."); // Added log
  } else {
    console.error("Bootstrap object or Modal constructor not found on window after mounted."); // Added log
  }
})
</script>
<style scoped>
.price {
  font-size: 1.1em;
  color: #e74c3c;
  font-weight: bold;
}
</style>