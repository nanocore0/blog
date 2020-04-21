<template>
  <main class="container " role="main">
    <div class="row">
      <div class="col-md-9  article">
        <!-- 博客列表 -->
        <div class="card mb-2">
          <div class="card-header">
            <ul class="nav nav-tabs card-header-tabs">
              <li class="nav-item">
                <a class="nav-link" href="javascript:void(0)" @click="initBlogsList(1, 8, 'new')" :class="{active: order!='hot'}">最新</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="javascript:void(0)" @click="initBlogsList(1, 8, 'hot')" :class="{active: order=='hot'}">最热</a>
              </li>
            </ul>
          </div>
          <div class="card-body">
            <List :blogs="blogs"/>
          </div>
        </div>
        <!-- 分页 -->
        <Page :page="page" @page="initBlogsList" :pageSize="pageSize" :order="order" :keyword="null" :catalogId="null"/>
      </div><!-- left end -->
      <!-- 右侧栏目 -->
      <div class="col-md-3 aside pl-1">
        <!-- 关于 -->
        <About/>
        <!-- 热门标签 -->
        <Tag/>
        <!-- 热门文章 -->
        <Hot/>
        <!-- 最新发布 -->
        <New/>
      </div><!-- .col-md-3 end -->

    </div><!-- .row end -->
  </main><!-- /.container -->
</template>

<script>
  import {getBlogsList} from '@/api/blog'

  import List from '../components/blog/List'
  import Page from '../components/fragments/Page'
  import About from '../components/index/About'
  import Tag from '../components/index/Tag'
  import Hot from '../components/index/Hot'
  import New from '../components/index/New'

  export default {
    name: "Home",
    data: function () {
      return {
        blogs: [],
        page: {}, // 传到分页组件
        pageSize: 0,
        order: '',
      }
    },
    components: {
      List,
      Page,
      About,
      Tag,
      Hot,
      New
    },
    mounted() {
      this.initBlogsList(1, 8, 'default')
    },
    methods: {
      // 初始化博客列表
      initBlogsList(pageNum, pageSize, order) {
        // 存起来传到分页组件
        this.pageSize = pageSize
        this.order = order
        console.log('查询博客列表order:' + order)
        /*axios.get('http://localhost:8081/blogs', {
          params: {pageNum: pageNum, pageSize: pageSize, order: order}
        })*/
        getBlogsList({pageNum: pageNum, pageSize: pageSize, order: order})
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
