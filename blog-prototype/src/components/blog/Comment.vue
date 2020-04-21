<template>
  <section>
    <h2 class="border-bottom border-grey pb-2 mb-0">评论：</h2>
    <!-- 评论列表 -->
    <div class="media pt-3" v-for="comment in comments" :key="comment.key">
      <a :href="'#/user/' + comment.userId">
        <img :src="comment.avatar?comment.avatar:defaultAvatar" :alt="'图片未加载'" class="blog-avatar-50 mr-2 rounded">
      </a>
      <div class="media-body pb-3 border-bottom border-gray">
        <div class="d-flex justify-content-between pb-2">
          <a :href="'#/user/' + comment.userId" class="d-block text-dark font-weight-bold">{{comment.author}}</a>
          <span class="ml-2">
            <a href="javascript:void(0)" v-if="isOwner(comment.userId)" @click="updateComment(comment.id)">
              <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
            </a>
            <a href="javascript:void(0)" v-if="isOwner(comment.userId)" @click="deleteComment(comment.id)">
              <i class="fa fa-times" aria-hidden="true"></i>
            </a>
          <time class="text-muted">发表于 {{comment.createTime}}</time>
          </span>
        </div>
        <span>{{comment.content}}</span>
      </div>
    </div>
    <!-- 发表评论 -->
    <a id="tips"></a>
    <div class="media pt-3">
      <a href="javascript:void(0)">
        <img :src="avatar" class="blog-avatar-50 mr-2 rounded">
      </a>
      <div class="media-body border-bottom border-gray">
        <div class="d-flex justify-content-between text-muted pb-2">
          <a href="javascript:void(0)" class="d-block text-dark font-weight-bold" v-if="isLogin">{{editingComment.author}}</a>
          <a href="javascript:void(0)" class="d-block text-dark font-weight-bold" v-else>匿名用户</a>
          <time>现在是 2019-02-30 12:00:00</time>
        </div>
        <textarea class="blog-textarea mb-0" placeholder="看帖需留言~" v-model="editingComment.content"></textarea>
        <button class="btn btn-info btn-sm mb-2" @click="saveComment">发表评论</button>
      </div>
    </div>
  </section>
</template>

<script>

  import {mapState, mapGetters} from 'vuex'
  import {getCommentsList, saveComment, updateComment, getComment, deleteComment} from '@/api/comment'

  export default {
    name: "Comment",
    data: function () {
      return {
        blogId: this.$route.params.id,
        defaultAvatar: '/static/images/avatar-default-1.jpg',
        comments: [],
        editingComment: {}
      }
    },
    props: {},
    computed: {
      ...mapGetters(['user', 'avatar']),
      isLogin() {
        return (this.user.id != null)
      },
    },
    mounted() {
      this.initComments()
    },
    methods: {
      isOwner(userId) {
        return this.user.id != null ? userId == this.user.id : false
      },
      initComments() {
        /*axios.get('http://localhost:8081/comments/blog/' + this.blogId)*/
        getCommentsList(this.blogId)
          .then(response => {
            if (response.status == 200) {
              this.comments = response.data
              this.editingComment = {
                content: '',
                userId: this.user.id,
                blogId: this.blogId,
                author: this.user.nickname
              }
            } else {
              console.log('获取评论失败 ' + response.data.message)
            }
          })
          .catch(error => {
            console.log('错误' + error)
          })
      },
      saveComment() {
        if (this.isLogin == false) {
          alert('请先登录')
          return
        }
        var comment = this.editingComment
        if (comment.id == null) {
          /*axios.post('http://localhost:8081/comment', comment)*/
          saveComment(comment)
            .then(response => {
              if (response.status == 200) {
                console.log('发表评论成功')
                this.initComments()
                this.editingComment.content = ''
              } else {
                console.log('请求失败' + response.data.message)
              }
            })
            .catch(error => {
              console.log('错误' + error)
            })
        } else {
          /*axios.put('http://localhost:8081/comment/' + comment.id, comment)*/
          updateComment(comment.id, comment)
            .then(response => {
              if (response.status == 200) {
                console.log('评论修改成功')
                this.initComments()
              } else {
                console.log('请求失败' + response.data.message)
              }
            })
            .catch(error => {
              console.log('错误' + error)
            })
        }
      },
      updateComment(id) {
        /*axios.get('http://localhost:8081/comment/' + id)*/
        getComment(id)
          .then(response => {
            if (response.status == 200) {
              this.editingComment = response.data
              // 跳到发布文本框
              window.location = '#tips'
            } else {
              console.log('请求失败' + response.data.message)
            }
          })
          .catch(error => {
            console.log('错误' + error)
          })
        /*this.editingComment = this.comments.find(comment => {
          return id == comment.id
        })*/
      },
      deleteComment(id) {
        if (confirm('确认删除嘛')) {
          /*axios.delete('http://localhost:8081/comment/' + id)*/
          deleteComment(id)
            .then(response => {
              if (response.status == 200) {
                console.log('删除评论成功')
                this.initComments()
              } else {
                console.log('请求失败' + response.data.message)
              }
            })
            .catch(error => {
              console.log('错误' + error)
            })
        }
      },
    }
  }
</script>

<style scoped>
  /* 博客评论 */
  .blog-textarea {
    border-radius: 0;
    margin-bottom: 10px;
    width: 100%;
    height: 80px;
    padding: 5px 10px;
    border: 1px solid #ddd;
    border-radius: 3px;
    resize: none;
  }

</style>
