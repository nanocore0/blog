<template>
  <header>
    <!-- 顶部导航栏 -->
    <nav class="navbar navbar-expand-md" :class="navbarColor">
      <a class="navbar-brand" href="#/">NewStarBlog</a>
      <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarContainer">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- 收集导航链接、表单和其他内容进行切换 -->
      <div class="collapse navbar-collapse" id="navbarContainer">
        <ul class="nav navbar-nav mr-2">
          <li class="nav-item">
            <a class="nav-link" href="#/index">首页<span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#" title="正在制作中">阅读<span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#" title="正在制作中">时光轴<span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#" title="正在制作中">留言板<span class="sr-only">(current)</span></a>
          </li>
        </ul>

        <form class="form-inline mr-auto">
          <input class="form-control" type="text" placeholder="Search">
        </form>

        <!-- 登录判断 -->
        <ul class="navbar-nav">
          <li class="nav-item dropdown" v-if="user.id">
            <a class="nav-link dropdown-toggle mr-2" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">{{user.username}}</a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <a class="dropdown-item" :href="'#/user/' + user.id">个人主页</a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#/userspace/profile">个人设置</a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#/blog/add">发表博客</a>
            </div>
          </li>
          <li class="nav-item" v-if="user.id">
            <button class="btn btn-outline-success" type="button" @click="logout">退出</button>
          </li>
          <li class="nav-item" v-else>
            <a class="btn btn-outline-success" href="#/login" role="button">登录</a>
          </li>
        </ul>
      </div><!-- .navbar-collspse -->
    </nav>
  </header>
</template>

<script>
  import {mapState, mapGetters} from 'vuex'
  import {getInfo, logout} from '@/api/login'
  import {removeToken} from '@/util/auth'

  export default {
    name: "Header",
    computed: {
      ...mapGetters(['token', 'user', 'roles']),
      navbarColor() {
        if(this.$route.path.startsWith('/admin')) {
          return 'navbar-dark bg-dark'
        }
        return 'navbar-light bg-light'
      }
    },
    mounted() {
      // 页面重新加载时获取用户信息
      if (this.token != undefined && this.token != '') {
        this.initUser()
      }
    },
    watch: {
      // token 改变(登录操作)时获取用户信息
      // watch 里面不要用箭头函数
      token: function (value) {
        if (value != undefined && value != '') {
          //this.a.methods.initUser()
          this.initUser()
        }
      }
    },
    methods: {
      initUser() {
        getInfo()
          .then(response => {
            if (response.status == 200) {
              // 保存 user 到 store
              this.$store.dispatch('addUser', response.data)
            } else {
              console.log('获取用户信息失败: ' + response.data.message)
            }
          }).catch(error => {

        })
      },
      logout() {
        // 调用 api 封装的登出请求
        logout()
          .then(response => {
            if (response.status == 200) {
              // 清除持久化的 token
              removeToken('token')
              // 清空 store 数据
              this.$store.dispatch('clearToken')
              this.$store.dispatch('removeUser')
              // 退出后的处理
              this.$router.push({path: '/login'})
            } else {
              console.log('登出失败')
            }
          })
          .catch(error => {

          })
        /*
        this.$store.dispatch('logout')
          .then(response => {
            if(response.status == 200) {
              this.$router.push({path: '/index'})
            } else {
              console.log('登出失败' + response.data.message)
            }
          }).catch(error => {
          console.log('错误' + error)
        })*/
      }
    }
  }
</script>

<style scoped>

</style>
