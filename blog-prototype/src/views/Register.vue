<template>
  <main class="container" role="main">
    <div class="row">
      <div class="col-md-8 mx-auto">
        <div class="card">
          <div class="card-header">
            <h5>用户注册</h5>
          </div>
          <div class="card-body">
            <form>
              <div class="form-group row">
                <label for="username" class="col-form-label col-md-2">账号</label>
                <input type="text" class="form-control col-md-10" id="username" maxlength="50" placeholder="请输入账号，至少3个字符，至多20个" v-model="user.username" >
              </div>
              <div class="form-group row">
                <label for="email" class="col-form-label col-md-2">邮箱</label>
                <input type="email" class="form-control col-md-10" id="email" maxlength="50" placeholder="请输入邮箱" v-model="user.email">
              </div>
              <div class="form-group row">
                <label for="nickname" class="col-form-label col-md-2">姓名</label>
                <input type="text" class="form-control col-md-10" id="nickname" maxlength="20" placeholder="请输入姓名，至少2个字符，至多20个" v-model="user.nickanme">
              </div>
              <div class="form-group row">
                <label for="password" class="col-form-label col-md-2">密码</label>
                <input type="password" class="form-control col-md-10" id="password" name="password" maxlength="30" placeholder="请输入密码，字母或特殊符号和数字结合" v-model="user.password">
              </div>
              <div class="form-group row">
                <div class="col-md-2"></div>
                <button type="button" class="btn btn-primary col-md-2" @click="register">提交</button>
              </div>
            </form>
          </div>
        </div><!-- .card -->
      </div>
    </div><!-- .row -->
  </main><!-- .container -->
</template>

<script>
  export default {
    name: "Register",
    data: function () {
      return {
        user: {
          username: '',
          email: '',
          nickanme: '',
          password: '',
        }
      }
    },
    methods: {
      register() {
        alert('register' + this.user)
      }
    }
  }
</script>

<style scoped>

</style>
