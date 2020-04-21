<template>
  <nav aria-label="Page navigation example" v-if="page.pages > 1">
    <ul class="pagination justify-content-center mb-4">
      <!-- 上一页 -->
      <li class="page-item" :class="{disabled: !page.hasPreviousPage}">
        <a class="page-link" href="javascript:(0)" aria-label="Previous" @click="queryByPage(page.pageNum-1)">
          <span aria-hidden="true">&laquo;</span>
          <span class="sr-only">Previous</span>
        </a>
      </li>
      <!-- 前面有页数被省略 -->
      <li class="page-item">
        <a class="page-link" href="javascript:(0)" v-show="page.navigateFirstPage > 1">...</a>
      </li>
      <li class="page-item" v-for="(navigatepageNum) in page.navigatepageNums" :class="{active: navigatepageNum == page.pageNum}">
        <a class="page-link" href="javascript:(0)" @click="queryByPage(navigatepageNum)">{{navigatepageNum}}</a>
      </li>
      <!-- 后面有页数被省略 -->
      <li class="page-item">
        <a class="page-link" href="javascript:(0)" v-show="page.navigateLastPage < page.pages">...</a>
      </li>
      <!-- 下一页 -->
      <li class="page-item" :class="{disabled: !page.hasNextPage}" @click="queryByPage(page.pageNum+1)">
        <a class="page-link" href="javascript:(0)" aria-label="Next" >
          <span aria-hidden="true">&raquo;</span>
          <span class="sr-only">Next</span>
        </a>
      </li>
    </ul>
  </nav>

</template>

<script>
  export default {
    name: "Page",
    data: function () {
      return {
      }
    },
    props: {
      page: Object,
      pageSize: Number,
      order: String,
      keyword: String,
      catalogId: Number
    },
    computed: {

    },
    methods: {
      queryByPage(navigatepageNum) {
        //console.log(navigatepageNum, this.pageSize, this.order)
        this.$emit('page', navigatepageNum, this.pageSize, this.order, this.catalogId, this.keyword)
      }
    }
  }
</script>

<style scoped>

</style>
