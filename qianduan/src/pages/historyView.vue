<template>
  <div id="app" class="container mt-4">
    <h3 class="mb-3">浏览历史</h3>

    <div v-if="records.length === 0" class="alert alert-info">暂无记录</div>
    <div class="row row-cols-1 row-cols-md-3 g-4" v-else>
      <div class="col" v-for="item in records" :key="item.productId">
        <div class="card h-100">
          <img :src="item.imageUrl" class="card-img-top" alt="图片">
          <div class="card-body">
            <h5 class="card-title">{{ item.productName }}</h5>
            <p class="card-text">{{ item.description }}</p>
          </div>
          <div class="card-footer">
            <small class="text-muted">浏览时间：{{ formatTime(item.viewTime) }}</small>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap 分页 -->
    <nav v-if="total > 0" class="mt-4 d-flex justify-content-center">
      <ul class="pagination">
        <li class="page-item" :class="{ disabled: page === 1 }">
          <a class="page-link" href="#" @click.prevent="changePage(page - 1)">上一页</a>
        </li>

        <li v-for="p in totalPages" :key="p" class="page-item" :class="{ active: page === p }">
          <a class="page-link" href="#" @click.prevent="changePage(p)">{{ p }}</a>
        </li>

        <li class="page-item" :class="{ disabled: page === totalPages }">
          <a class="page-link" href="#" @click.prevent="changePage(page + 1)">下一页</a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import { ref, onMounted, computed, watch } from 'vue';
import { useStore } from 'vuex';
import $ from 'jquery';

export default {
  setup() {
    const userId = ref(1)
    const store = useStore()
    const page = ref(1)
    const size = ref(10)
    const total = ref(1)
    const records = ref([])

    const totalPages = computed(() => Math.ceil(total.value / size.value))

    // const fetchData = () => {
    // const url = `/gethistorylist?userId=${userId}&page=${page.value}&size=${size.value}`
    //   fetch(url)
    //     .then(res => res.json())
    //     .then(data => {
    //       records.value = data.records
    //       total.value = data.total
    //     })
    // }
    const fetchData = () => {
      $.ajax({
        url: `http://localhost:8088/api/gethistorylist?userId=${userId.value}&page=${page.value}&size=${size.value}`,
        type: "get",
        contentType: "application/json",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);
          records.value = JSON.parse(resp.result);
          total.value = JSON.parse(resp.total);
        },
      })
    }

    const changePage = (p) => {
      if (p < 1 || p > totalPages.value) return
      page.value = p
      fetchData()
    }

    const formatTime = (str) => new Date(str).toLocaleString()

    fetchData()

    onMounted(() => {
      if (store.state.user.id) {
        fetchData()
      } else {
        // 监听 user.id 的变化
        const unwatch = watch(
          () => store.state.user.id,
          (newId) => {
            if (newId) {
              fetchData()
              unwatch() // 加载一次后就取消监听，避免不必要的重复
            }
          }
        )
      }
    })

    return {
      page, size, total, records,userId,
      fetchData, changePage, formatTime, totalPages
    }
  }
}

</script>
<style scoped></style>