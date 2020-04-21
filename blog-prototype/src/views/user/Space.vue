<template>
  <main class="container" role="main">
    <div class="row">
      <!-- 左侧栏目 -->
      <div class="col-md-3">
        <div class="card mb-3">
          <div class="card-body text-center">
            <a href="javascript:void(0)" class="blog-edit-avatar" data-toggle="modal" data-target="#exampleModalCenter" role="button">
              <img class="blog-avatar-160" :src="avatar" :alt="user.nickname"/>
            </a>
            <h5>{{user.nickname}}</h5>
          </div>
        </div><!-- .card end -->

        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-cog" aria-hidden="true"><span> 个人设置</span></i>
          </div>
          <div class="card-body ">
            <ul class="list-group list-group-flush ">
              <li class="list-group-item pl-0">
                <a href="#/userspace/profile" class="card-link">修改个人资料</a>
              </li>
              <li class="list-group-item pl-0">
                <a href="#/userspace/password" class="card-link">修改个人密码</a>
              </li>
            </ul>
          </div>
        </div><!-- .card end -->
      </div>

      <!-- 右侧栏目 -->
      <div class="col-md-9">
        <router-view />
      </div><!-- 右侧栏目 end -->
    </div><!-- .row end-->

    <Cropper/>
  </main>
</template>

<script>
  import {mapState, mapGetters, mapActions} from 'vuex'
  import Cropper from '@/components/userspace/Cropper'

  export default {
    name: "Space",
    props: {},
    data: function () {
      return {
        me: {}
      }
    },
    beforeRouteEnter(to, from, next) {
      console.log('##进入个人主页')
      next()
    },
    beforeRouteLeave(to, from, next) {
      //alert('还未保存，确定要离开吗')
      next()
    },
    computed: {
      ...mapGetters(['user', 'avatar']),
    },
    components: {
      Cropper
    },
    mounted() {
    },
    methods: {
    }
  }
</script>

<style scoped>

</style>
