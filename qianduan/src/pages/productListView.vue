<template>
  <div class="container py-4">
    <h2 class="mb-4">商品列表</h2>
    <div class="row">
      <div class="col-md-4 mb-4" v-for="product in products" :key="product.id">
        <div class="card h-100 shadow-sm">
          <img :src="product.image" class="card-img-top" alt="商品图片" style="height: 200px; object-fit: cover;">
          <div class="card-body">
            <h5 class="card-title">{{ product.name }}</h5>
            <p class="card-text text-danger">￥{{ product.price }}</p>
            <p class="card-text text-muted" style="font-size: 0.95rem;">{{ product.description }}</p>
            <button class="btn btn-primary w-100" @click="addToCart(product)">加入购物车</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  name: 'productListView',
  setup() {
    // 示例商品数据，实际可用API获取
    const products = ref([
      {
        id: 1,
        name: '苹果手机',
        price: 5999,
        description: '最新款苹果智能手机，性能强劲，拍照清晰。',
        image: 'https://img14.360buyimg.com/n7/jfs/t1/123456/12345/12345/12345/5e8c1234N12345678.jpg',
      },
      {
        id: 2,
        name: '笔记本电脑',
        price: 4999,
        description: '轻薄便携，适合办公和学习。',
        image: 'https://img14.360buyimg.com/n7/jfs/t1/234567/23456/23456/23456/5e8c2345N23456789.jpg',
      },
      {
        id: 3,
        name: '蓝牙耳机',
        price: 299,
        description: '无线蓝牙耳机，音质出色，续航持久。',
        image: 'https://img14.360buyimg.com/n7/jfs/t1/345678/34567/34567/34567/5e8c3456N34567890.jpg',
      },
    ]);

    const addToCart = (product) => {
      alert(`已将【${product.name}】加入购物车！`);
      // 实际项目可调用API或Vuex action
    };

    return {
      products,
      addToCart,
    };
  },
};
</script>

<style scoped>
.card-title {
  font-size: 1.2rem;
}
.card-img-top {
  border-bottom: 1px solid #eee;
}
</style> 