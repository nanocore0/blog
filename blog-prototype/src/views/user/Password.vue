<template>
  <!-- 修改密码 -->
  <div class="card ">
    <h5 class="card-header">修改密码</h5>
    <div class="card-body">
      <form>
        <div class="form-group">
          <label for="oldPassword" class="col-form-label ">旧密码</label>
          <input type="password" class="form-control" id="oldPassword" v-model="oldPassword" maxlength="50" placeholder="请输入旧密码">
        </div>
        <div class="form-group">
          <label for="newPassword" class="col-form-label ">新密码</label>
          <input type="password" class="form-control" id="newPassword" v-model="newPassword" maxlength="50" placeholder="请输入新密码">
        </div>
        <div class="form-group">
          <label for="repeatPassword" class="col-form-label">确认新密码</label>
          <input type="password" class="form-control" id="repeatPassword" v-model="repeatPassword" maxlength="20" placeholder="请再次输入新密码">
        </div>
        <div class="form-group">
          <button type="button" class="btn btn-primary" @click="resetPassword">提交</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
  import {mapState, mapGetters, mapActions} from 'vuex'
  import {resetPassword} from '@/api/user'

  export default {
    name: "Password",
    data: function () {
      return {
        oldPassword: '',
        newPassword: '',
        repeatPassword: ''
      }
    },
    computed: {
      ...mapGetters(['user'])
    },
    methods: {
      resetPassword() {
        if(this.newPassword == this.oldPassword) {
          alert('新密码不能与旧密码相同')
        }
        if(this.newPassword != this.repeatPassword) {
          alert('两次密码不一致')
        }
        var form = new FormData();
        form.append('oldPassword', this.oldPassword)
        form.append('newPassword', this.newPassword)
        form.append('repeatPassword', this.repeatPassword)

        /*axios.post('http://localhost:8081/user/reset/' + this.user.id, form)*/
        resetPassword(this.user.id, form)
          .then(response => {
            if (response.status == 200) {
              var user = response.data
              this.$store.dispatch('setUser', user)
              alert('修改成功')
              this.oldPassword = ''
              this.newPassword = ''
              this.repeatPassword = ''
            } else {
              alert('修改失败')
              console.log('修改密码失败' + response.data.message)
            }
          }).catch(error => {
          console.log('错误' + error)
        })
      }
    }
  }
</script>

<style scoped>

</style>
