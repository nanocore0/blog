<template>
  <main class="container" role="main">
    <div class="row">
      <div class="col-md-8 mx-auto">
        <div class="card">
          <div class="card-header">
            <h5>用户登录</h5>
          </div>
          <div class="card-body">
            <form>
              <div class="form-group">
                <label for="username" class="col-form-label">账号</label>
                <input type="text" class="form-control" id="username" placeholder="Enter username" v-model="username"/>
              </div>
              <div class="form-group">
                <label for="password" class="col-form-label">密码</label>
                <input type="password" class="form-control" id="password" placeholder="Enter password" v-model="password"/>
              </div>
              <div class="form-check  mb-2">
                <input type="checkbox" class="form-check-input" id="remember-me" v-model="rememberMe"/>
                <label for="remember-me" class="form-check-label">记住我</label>
              </div>
              <div class="form-group">
                <button type="button" class="btn btn-primary" @click="login">登录</button>
              </div>
              <!-- error -->
              <div>
                <p class="blog-label-error" style="color: red" v-show="errorMsg">{{errorMsg}}</p>
              </div>
            </form>
          </div>
        </div><!-- .card -->
      </div>
    </div><!-- .row -->
  </main><!-- .container -->
</template>

<script>
  import {login} from '@/api/login'
  import {setToken} from '@/util/auth'

  export default {
    name: "Login",
    data: function () {
      return {
        // 默认填写数据
        username: 'nanocore',
        password: '123456',
        rememberMe: false,
        errorMsg: '',
      }
    },
    methods: {
      // 登录
      login() {
        // 清除错误提示
        this.errorMsg = ''
        // 清除 store 的 token 以免冲突
        this.$store.dispatch('clearToken')
        // 拼接表单数据
        var form = new FormData();
        form.append('username', this.username)
        form.append('password', this.password)
        // 调用 action
        /*
        this.$store.dispatch('login', form)
          .then(response => {
            if (response.status == 200) {
              initUser()
              this.$router.push({path: '/index'})
            } else {
              this.errorMsg = response.data.message
            }
          })
          .catch(error => {
            console.log('错误' + error)
            this.errorMsg = '系统错误'
          })
        */
        // 调用 api 发登录请求
        login(form)
          .then(response => {
            if (response.status == 200) {
              // 持久化 token
              setToken('token', response.data.access_token)
              // 保存 token 到 store
              this.$store.dispatch('addToken', response.data)
              // 跳转到首页
              this.$router.push({path: '/index'})
            } else {
              console.log('登录失败: ' + response.data.message)
              this.errorMsg = response.data.message
            }
          })
          .catch(error => {
            //console.log('登录错误: ' + error)
            this.errorMsg = '系统错误'
          })
      }
    }
  }
</script>

<style scoped>

</style>
