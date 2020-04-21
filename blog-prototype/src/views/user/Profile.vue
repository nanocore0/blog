<template>
  <!-- 个人设置 -->
  <div class="card ">
    <h5 class="card-header">个人设置</h5>
    <div class="card-body">
      <form>
        <div class="form-group">
          <label for="username" class="col-form-label">账号</label>
          <input type="text" class="form-control" id="username" readonly="readonly" v-model="me.username">
        </div>
        <div class="form-group">
          <label for="email" class="col-form-label ">邮箱</label>
          <input type="email" class="form-control" id="email" maxlength="50" v-model="me.email" placeholder="请输入邮箱">
        </div>
        <div class="form-group">
          <label for="name" class="col-form-label">昵称</label>
          <input type="text" class="form-control" id="name" maxlength="20" v-model="me.nickname" placeholder="请输入昵称，至少2个字符，至多20个">
        </div>
        <div class="form-group">
          <label for="password" class="col-form-label">密码</label>
          <input type="password" class="form-control" id="password" readonly="readonly" maxlength="30" v-model="me.password">
        </div>
        <div class="form-group">
          <button type="button" class="btn btn-primary" @click="updateUser">保存</button>
        </div>
      </form>
    </div>
  </div><!-- .card end -->
</template>

<script>
  import {getUser, updateUser} from "@/api/user"
  import {mapGetters} from 'vuex'

  export default {
    name: "Profile",
    data: function () {
      return {
        me: {}
      }
    },
    mounted() {
      this.initUser()
    },
    computed: {
      ...mapGetters(['user', 'avatar'])
    },
    methods: {
      updateUser() {
        /*var form = new FormData()
        form.append('id', this.me.id)
        form.append('username', this.me.username)
        form.append('password', this.me.password)
        form.append('nickname', this.me.nickname)
        form.append('email', this.me.email)
        form.append('enabled', true)
        form.append('accountNonExpired', true)
        form.append('accountNonLocked', true)
        form.append('credentialsNonExpired', true)*/
        /*axios.put('http://localhost:8081/user/' + this.user.id, this.me)*/
        updateUser(this.me.id, this.me)
          .then(response => {
            if (response.status == 200) {
              var user = response.data
              this.me = user
              this.$store.dispatch('setUser', user)
              alert('修改成功')
            } else {
              console.log('修改用户失败' + response.data.message)
            }
          })
          .catch(error => {
            console.log('错误' + error)
          })
      },
      initUser() {
        /*axios.get('http://localhost:8081/user/' + this.user.id)*/
        getUser(this.user.id)
          .then(response => {
            if (response.status == 200) {
              this.me = response.data
            } else {
              console.log('获取用户失败' + response.data.message)
            }
          })
          .catch(error => {
            console.log('错误' + error)
          })
      }
    }
  }
</script>

<style scoped>

</style>
