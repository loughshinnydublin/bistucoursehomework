<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
  <div class="container">
    <router-link class="navbar-brand fw-bold" :to="{name: 'home'}">商城系统</router-link>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link :class="route_name == 'product_index' ? 'nav-link active' : 'nav-link'" :to="{name: 'product_index'}">商品首页</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name == 'cart' ? 'nav-link active' : 'nav-link'" :to="{name: 'cart'}">购物车</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name == 'history' ? 'nav-link active' : 'nav-link'" :to="{name: 'history'}">浏览历史</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name == 'orders' ? 'nav-link active' : 'nav-link'" :to="{name: 'orders'}">我的订单</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name == 'wallet' ? 'nav-link active' : 'nav-link'" :to="{name: 'wallet'}">我的钱包</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name == 'publish_product' ? 'nav-link active' : 'nav-link'" :to="{name: 'publish_product'}">发布商品</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name == 'my_shop' ? 'nav-link active' : 'nav-link'" :to="{name: 'my_shop'}">我的商店</router-link>
        </li>
        <li class="nav-item" v-if="$store.state.user.role === 'ADMIN'">
          <router-link :class="route_name == 'admin_center' ? 'nav-link active' : 'nav-link'" :to="{name: 'admin_center'}">管理员中心</router-link>
        </li>
      </ul>
      <ul class="navbar-nav ms-auto">
        <li class="nav-item d-flex align-items-center" v-if="$store.state.user.isLogin">
          <a class="nav-link fw-bold" href="#" @click.prevent="goProfile">
            {{ $store.state.user.username }}
          </a>
          <button class="btn btn-outline-light btn-sm ms-2" @click="logout">退出</button>
        </li>
        <li class="nav-item" v-else>
          <router-link class="nav-link" :to="{name: 'user_account_login'}">登录</router-link>
          <router-link class="nav-link" :to="{name: 'user_account_register'}">注册</router-link>
        </li>
      </ul>
    </div>
  </div>
</nav>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { computed } from 'vue'
import { useStore } from 'vuex';

export default {
    setup() {
        const store = useStore();
        const route = useRoute();
        const router = useRouter();
        let route_name = computed(() => route.name)

        const logout = () => {
          store.dispatch("logout");
          router.push({ name: "user_account_login" });
        }
        const goProfile = () => {
          // 关闭Bootstrap下拉菜单
          const dropdown = document.getElementById('navbarDropdown');
          if (dropdown) {
            const dropdownInstance = window.bootstrap?.Dropdown.getOrCreateInstance(dropdown);
            dropdownInstance?.hide();
          }
          router.push({ name: 'user_account_profile' });
        }

        return {
            route_name,
            logout,
            goProfile
        }
    }
}
</script>

<style scoped>
.navbar {
  font-size: 1.1rem;
}
.navbar-brand {
  font-size: 1.5rem;
}
</style>