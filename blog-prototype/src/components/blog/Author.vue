<template>
  <div class="card mb-4">
    <div class="card-body">
      <div class="text-center">
        <img class="blog-avatar-85" :src="blog.avatar?blog.avatar:defaultAvatar"/>
        <h5><a :href="'#/user/'+blog.userId" class="text-dark">{{blog.author}}</a></h5>
      </div>
    </div>
    <div class="card-footer p-2">
      <table class="w-100 small">
        <tbody>
        <tr align="center">
          <td>
            <span class="text-muted">主题数</span><br>
            <b>{{info.blogSize}}</b>
          </td>
          <td>
            <span class="text-muted">评论数</span><br>
            <b>{{info.commentSize}}</b>
          </td>
          <td>
            <span class="text-muted">收到的赞</span><br>
            <b>{{info.receivedVoteSize}}</b>
          </td>
          <td>
            <span class="text-muted">注册排名</span><br>
            <b>{{info.registerRank}}</b>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  import {getAccountInfo} from '@/api/user'

  export default {
    name: "Author",
    data: function () {
      return {
        defaultAvatar: '/static/images/avatar-default-1.jpg',
        info: {}
      }
    },
    props: {
      blog: Object
    },
    watch: {
      blog: function () {
        this.initUserInfo(this.blog.userId)
      }
    },
    methods: {
      initUserInfo(id) {
        getAccountInfo(id)
          .then(response => {
            if (response.status == 200) {
              this.info = response.data
            }
          })
          .catch(error => {

          })
      }
    }
  }
</script>

<style scoped>

</style>
