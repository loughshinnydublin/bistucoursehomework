import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import registerView from '@/pages/registerView.vue'
import NotFound from '@/pages/NotFound.vue'
import loginView from '@/pages/loginView.vue'
import { useStore } from 'vuex'
import AuditView from '@/pages/auditView.vue'
import profileView from '@/pages/profileView.vue'
import productIndexView from '@/pages/productIndexView.vue'
import cartView from '@/pages/cartView.vue'
import historyView from '@/pages/historyView.vue'
import orderView from '@/pages/orderView.vue'
import walletView from '@/pages/walletView.vue'
import publishProductView from '@/pages/publishProductView.vue'
import myShopView from '@/pages/myShopView.vue'
import adminCenterView from '../pages/adminCenterView.vue'
import productAuditView from '../pages/productAuditView.vue'
import merchantAuditView from '@/pages/merchantAuditView.vue'
import sellerLevelView from '../pages/sellerLevelView.vue'
import feeSettingView from '../pages/feeSettingView.vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap'
import AboutView from '@/views/AboutView.vue'

const routes = [
  {
    path: '/',
    redirect: { name: 'user_account_login' }
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/user/account/register/',
    name: 'user_account_register',
    component:registerView,
    meta: {
			requestAuth: false, //存储页面是否需要授权
		}
  },
  {
    path:'/user/account/login/',
    name:'user_account_login',
    component:loginView,
    meta: {
			requestAuth: false, //存储页面是否需要授权
		}
  },
  {
    path: "/404/",
    name: "404",
    component: NotFound,
    meta: {
			requestAuth: false, //存储页面是否需要授权
		}
  },
  {
    path:"/about/",
    name:"about",
    component:AboutView,
    meta:{
      requestAuth:false,
    }
  },
  {
    path:"/admin/audit/",
    name:"admin_audit",
    component:AuditView,
    meta:{
      requestAuth: true,
    }
  },
  {
    path:"/user/account/profile/",
    name:"user_account_profile",
    component:profileView,
    meta:{
      requestAuth: true,
    }
  },
  {
    path: '/product/',
    name: 'product_index',
    component: productIndexView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: '/cart/',
    name: 'cart',
    component: cartView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/history/',
    name: 'history',
    component: historyView,
    meta: { requestAuth: true }
  },
  {
    path: '/orders/',
    name: 'orders',
    component: orderView,
    meta: { requestAuth: true }
  },
  {
    path: '/wallet/',
    name: 'wallet',
    component: walletView,
    meta: { requestAuth: true }
  },
  {
    path: '/publish/',
    name: 'publish_product',
    component: publishProductView,
    meta: { requestAuth: true }
  },
  {
    path: '/myshop/',
    name: 'my_shop',
    component: myShopView,
    meta: { requestAuth: true }
  },
  {
    path: '/admin',
    name: 'admin_center',
    component: adminCenterView,
    meta: { requestAuth: true, adminOnly: true }
  },
  {
    path: '/admin/product-audit',
    name: 'admin_product_audit',
    component: productAuditView,
    meta: { requestAuth: true, adminOnly: true }
  },
  {
    path: '/admin/merchant-audit',
    name: 'admin_merchant_audit',
    component: merchantAuditView,
    meta: { requestAuth: true, adminOnly: true }
  },
  {
    path: '/admin/user-manage',
    name: 'admin_user_manage',
    component: AuditView,
    meta: { requestAuth: true, adminOnly: true }
  },
  {
    path: '/admin/level',
    name: 'admin_seller_level',
    component: sellerLevelView,
    meta: { requestAuth: true, adminOnly: true }
  },
  {
    path: '/admin/fee',
    name: 'admin_fee_setting',
    component: feeSettingView,
    meta: { requestAuth: true, adminOnly: true }
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/"
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach(async (to, from, next) => {
	const store = useStore();
	const token = localStorage.getItem("jwt_token");

	if (to.meta.requestAuth && !store.state.user.isLogin && !token) {
		// 如果目标页面需要登录，但没有 token，跳转登录
		next({ name: "user_account_login" });
	} else {
		if (token) {
			store.commit("updateToken", token);
			if (!store.state.user.isLogin) {
				// 如果 token 存在，且用户未登录，再尝试获取用户信息
				await store.dispatch("getinfo", {
					success() {},
					error() {
						alert("token无效，请重新登录！");
						store.commit("logout"); // 你也可以加一个 logout mutation 清除状态
						next({ name: "user_account_login" });
					}
				});
			}
		}
		next();
	}
});


export default router
