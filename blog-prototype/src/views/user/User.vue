<template>
  <main class="container" role="main">
    <div class="row">
      <!-- 左侧栏目 -->
      <div class="col-md-3">
        <div class="card mb-3">
          <div class="card-body text-center">
            <a class="" role="button">
              <img class="blog-avatar-160" :src="author.avatar?author.avatar:defaultAvatar" :alt="author.nickname"/>
            </a>
            <h5>{{author.nickname}}</h5>
          </div>
        </div><!-- .card end -->

        <!-- 分类 -->
        <Catalog @page="initBlogsList"/>
      </div>

      <!-- 右侧栏目 -->
      <div class="col-md-9">

        <!-- 工具栏 -->
        <nav class="navbar navbar-light bg-light rounded mb-2">
          <ul class="nav nav-pills  mr-auto">
            <li class="nav-item">
              <a class="nav-link" href="javascript:void(0)" @click="queryNewestBlogsList" :class="{active: order!='hot'}">最新</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="javascript:void(0)" @click="queryHotestBlogsList" :class="{active: order=='hot'}">最热</a>
            </li>
          </ul>
          <form class="form-inline mt-2 mt-md-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" v-model="keyword">
            <a href="javascript:void(0)" class="btn btn-outline-success my-2 my-sm-0" @click="searchBlogs">
              <i class="fa fa-search" aria-hidden="true"></i>
            </a>
          </form>
        </nav>
        <!-- Blog Post -->
        <div class="card  mb-3 blog-list">
          <div class="card-body">
            <List :blogs="blogs"/>
          </div>
        </div>
        <!--card end -->

        <!-- 分页 -->
        <Page :page="page" @page="initBlogsList" :pageSize="pageSize" :order="order" :catalogId="catalogId" :keyword="keyword"/>
      </div><!-- reight end -->
    </div><!-- .row -->


  </main><!-- .container -->
</template>

<script>
  import List from '@/components/blog/List'
  import Page from '@/components/fragments/Page'
  import Catalog from '@/components/userspace/Catalog'
  import {getBlogsListByUser} from "@/api/blog"
  import {getUser} from "@/api/user"

  export default {
    name: "user",
    data: function () {
      return {
        userId: this.$route.params.id,
        defaultAvatar: '/static/images/avatar-default-1.jpg',
        author: {},
        blogs: [],
        page: {},
        // 以下 4 项传到分页组件
        pageSize: 0,
        order: '',
        catalogId: null,
        keyword: ''
      }
    },
    computed: {
      //...mapState(['user']),
    },
    components: {
      List,
      Page,
      Catalog
    },
    mounted() {
      this.initUser()
      this.initBlogsList(1, 8, 'default', null, "")
    },
    watch: {
      userId: function () {
        this.initUser()
        this.initBlogsList(1, 8, 'default', null, "")
      }
    },
    methods: {
      // 搜索博客
      searchBlogs() {
        this.initBlogsList(1, 8, 'default', null, this.keyword)
      },
      // 最新
      queryNewestBlogsList() {
        this.initBlogsList(1, 8, 'new', this.catalogId, null)
      },
      // 最热
      queryHotestBlogsList() {
        this.initBlogsList(1, 8, 'hot', this.catalogId, null)
      },
      // 初始化用户
      initUser() {
        /*axios.get('http://localhost:8081/user/' + this.userId)*/
        getUser(this.userId)
          .then(response => {
            if (response.status == 200) {
              this.author = response.data
            } else {
              console.log('失败了' + response.data.message)
            }
          })
          .catch(error => {
            console.log('初始化用户错误' + error)
          })
      },
      // 初始化博客列表
      initBlogsList(pageNum, pageSize, order, catalogId, keyword) {
        // 存起来传到分页组件
        this.pageSize = pageSize
        this.order = order
        this.catalogId = catalogId
        this.keyword = keyword
        console.log('查询博客列表order:' + order + ", catalogId:" + catalogId + ' ,keyword: ' + keyword)
        /*axios.get('http://localhost:8081/blogs/user/' + this.userId, {
          params: {pageNum: pageNum, pageSize: pageSize, order: order, catalogId: catalogId, keyword: keyword}
        })*/
        getBlogsListByUser(this.userId, {pageNum: pageNum, pageSize: pageSize, order: order, catalogId: catalogId, keyword: keyword})
          .then(response => {
            if (response.status == 200) {
              this.blogs = response.data.list
              this.page = response.data
            } else {
              console.log('请求失败:' + response.data.message)
            }
          })
          .catch(error => {
            console.log('错误:' + error)
          })
      }
    }
  }
</script>

<style scoped>

</style>
