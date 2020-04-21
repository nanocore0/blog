<template>
  <main class="container" role="main">
    <div class="row">
      <div class="col-md-9">

        <div class="input-group mb-3">
          <input type="text" class="form-control" v-model="editingKeyword" placeholder="在这里输入搜索的内容">
          <div class="input-group-append">
            <button class="btn btn-info" type="button" @click="executeSearch">
              <i class="fa fa-search" aria-hidden="true"></i> 搜一下
            </button>
          </div>
        </div>

        <div class="alert alert-primary" role="alert">
          <span>搜到 {{page.total}} 条跟 '{{keyword}}' 有关的记录</span>
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>

        <div class="card mb-2" v-if="blogs.length > 0">
          <div class="card-body">
            <List :blogs="blogs"/>
          </div>
        </div><!-- blogList end -->

        <!-- 分页 -->
        <Page :page="page" @page="search" :pageSize="pageSize" :order="order" :keyword="keyword" />
      </div><!-- left end -->

      <!-- 右侧栏目 -->
      <div class="col-md-3 aside pl-1">
        <About/>
      </div><!-- .col-md-3 end -->

    </div><!-- .row end -->
  </main>
</template>

<script>
  import {search} from '@/api/blog'

  import List from '../components/blog/List'
  import Page from '../components/fragments/Page'
  import About from '../components/index/About'

  export default {
    name: "Search",
    data: function () {
      return {
        keyword: this.$route.query.keyword,
        editingKeyword: this.$route.query.keyword,
        blogs: [],
        page: {}, // 传到分页组件
        pageSize: 0,
        order: '',
      }
    },
    components: {
      List,
      Page,
      About
    },
    mounted() {
      this.search(this.editingKeyword)
    },
    methods: {
      search(keyword, pageNum, pageSize, order) {
        if(keyword && keyword != '') {
          search({keyword: keyword, pageNum: pageNum, pageSize: pageSize, order: order})
            .then(response => {
              if(response.status == 200) {
                this.blogs = response.data.list
                this.page = response.data
              } else {
                console.log('搜索失败: ' + response.data.message)
              }
            })
            .catch(error => {
              console.log('搜索错误: ' + error)
            })
        }
      },
      executeSearch() {
        console.log('execute 1')
        this.search(this.editingKeyword)
        console.log('execute 2')

        console.log('execute 3')

      }
    }
  }
</script>

<style scoped>

</style>
