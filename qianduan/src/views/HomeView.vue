<template>
  <div class="container py-5">
    <div class="d-flex justify-content-end mb-3">
      <!-- <button class="btn btn-danger" @click="logout">退出登录</button> -->
    </div>
    <div class="text-center mb-5">
      <h1 class="display-4">欢迎来到商城系统</h1>
      <p class="lead">一个简单的电商管理与购物平台</p>
    </div>
    <div class="row justify-content-center">
      <div class="col-md-3 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-body text-center">
            <i class="bi bi-shop display-4 text-primary mb-3"></i>
            <h5 class="card-title">商品首页</h5>
            <p class="card-text">浏览和搜索所有在售商品。</p>
            <router-link class="btn btn-outline-primary" :to="{ name: 'product_index' }">进入</router-link>
          </div>
        </div>
      </div>
      <div class="col-md-3 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-body text-center">
            <i class="bi bi-cart4 display-4 text-success mb-3"></i>
            <h5 class="card-title">购物车</h5>
            <p class="card-text">查看已选商品，进行结算。</p>
            <router-link class="btn btn-outline-success" :to="{ name: 'cart' }">进入</router-link>
          </div>
        </div>
      </div>
      <div class="col-md-3 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-body text-center">
            <i class="bi bi-clock-history display-4 text-secondary mb-3"></i>
            <h5 class="card-title">浏览历史</h5>
            <p class="card-text">查看你最近浏览过的商品。</p>
            <router-link class="btn btn-outline-secondary" :to="{ name: 'history' }">进入</router-link>
          </div>
        </div>
      </div>
      <div class="col-md-3 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-body text-center">
            <i class="bi bi-list-check display-4 text-dark mb-3"></i>
            <h5 class="card-title">我的订单</h5>
            <p class="card-text">查看和管理你的订单。</p>
            <router-link class="btn btn-outline-dark" :to="{ name: 'orders' }">进入</router-link>
          </div>
        </div>
      </div>
      <div class="col-md-3 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-body text-center">
            <i class="bi bi-wallet2 display-4 text-success mb-3"></i>
            <h5 class="card-title">我的钱包</h5>
            <p class="card-text">查看你的余额。</p>
            <router-link class="btn btn-outline-success" :to="{ name: 'wallet' }">进入</router-link>
          </div>
        </div>
      </div>
      <div class="col-md-3 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-body text-center">
            <i class="bi bi-person-circle display-4 text-info mb-3"></i>
            <h5 class="card-title">个人中心</h5>
            <p class="card-text">管理个人信息和订单。</p>
            <router-link class="btn btn-outline-info" :to="{ name: 'user_account_profile' }">进入</router-link>
          </div>
        </div>
      </div>
      <div class="col-md-3 mb-4" v-if="$store.state.user.role === 'ADMIN'">
        <div class="card h-100 shadow-sm">
          <div class="card-body text-center">
            <i class="bi bi-shield-lock display-4 text-warning mb-3"></i>
            <h5 class="card-title">审核管理</h5>
            <p class="card-text">管理员审核新用户注册。</p>
            <router-link class="btn btn-outline-warning" :to="{ name: 'admin_audit' }">进入</router-link>
          </div>
        </div>
      </div>
      <div class="col-md-3 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-body text-center">
            <i class="bi bi-plus-square display-4 text-primary mb-3"></i>
            <h5 class="card-title">发布商品</h5>
            <p class="card-text">自己上架新商品。</p>
            <router-link class="btn btn-outline-primary" :to="{ name: 'publish_product' }">进入</router-link>
          </div>
        </div>
      </div>
      <div class="col-md-3 mb-4">
        <div class="card h-100 shadow-sm">
          <div class="card-body text-center">
            <i class="bi bi-shop-window display-4 text-warning mb-3"></i>
            <h5 class="card-title">我的商店</h5>
            <p class="card-text">管理自己发布的商品。</p>
            <router-link class="btn btn-outline-warning" :to="{ name: 'my_shop' }">进入</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import $ from 'jquery'
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  name: 'HomeView',

  setup() {
    const store = useStore();
    const router = useRouter();
    const logout = () => {
      store.dispatch('logout');
      router.push({ name: 'user_account_login' });
    };

    $.ajax({
      url: "http://localhost:8088/api/user/account/login/",
      type: "post",
      data: {
        username: "test2",
        password: "test2",
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }
    })

    


    // $.ajax({
    //   url:"http://localhost:8088/api/user/account/info/",
    //   type:"get",
    //   headers: {
    //     Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJlMWI1ZTkzZjZjZTk0NGI1OTQ2OWJmZGNhMDI2ZmU1MCIsInN1YiI6IjMiLCJpc3MiOiJzZyIsImlhdCI6MTc0NDQ0NzU4OSwiZXhwIjoxNzQ1NjU3MTg5fQ.SxDDvzrRqpOmgMYQwJH9ervqKW0Ctvs_zW-gza08Ric",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // })

    return { logout };
  }
}
</script>

<style scoped>
.container {
  min-height: 80vh;
}

.card-title {
  font-size: 1.3rem;
}
</style>