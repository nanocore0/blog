<template>
  <main class="container" role="main">
    <div class="row">
      <!-- Blog Entries Column -->
      <div class="col-md-9">
        <!-- Blog Post -->
        <div class="card mb-4">
          <div class="card-body">

            <!-- 标题 -->
            <h1 class="card-title">{{blog.title}}</h1>
            <!-- 发布信息 -->
            <div class="card-text d-flex justify-content-between">
              <div class="text-muted">
                <span><a class="card-link text-muted" :href="'#/user/'+blog.userId">{{blog.author}}</a></span>
                <span class="text-muted"> {{blog.createTime}}</span>
                <span class="ml-2"><i class="fa fa-eye" aria-hidden="true"></i> {{blog.readSize}}</span>
                <span class="ml-2"><i class="fa fa-heart-o" aria-hidden="true"></i> {{blog.voteSize}}</span>
                <span class="ml-2"><i class="fa fa-comment-o" aria-hidden="true"></i> {{blog.commentSize}}</span>
              </div>
              <div>
                <span class="ml-2">
                  <a href="javascript:void(0)" v-if="isOwner" @click="updateBlog"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                  <a href="javascript:void(0)" v-if="isOwner" @click="deleteBlog"><i class="fa fa-times" aria-hidden="true"></i></a>
                </span>
              </div>
            </div>
            <hr>

            <!-- 摘要 -->
            <div class="card-text">
              <p>{{blog.summary}}</p>
            </div>
            <!-- 内容 -->
            <article class="" v-html="blog.htmlContent">(current)</article>
            <hr>

            <!-- 交互-->
            <div class="mb-3">
              <h5>分类：
                <a :href="'#/user/' + blog.userId" class="text-success">{{catalog.name}}</a>
              </h5>
              <h5>标签：
                <a href="#" class="mr-1" v-for="tag in tags" :key="tag.id"> <span class="badge badge-secondary">{{tag.name}}</span></a>
              </h5>
              <div class="d-flex justify-content-center mt-4">
                <div class="px-2">
                  <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn" :class="{'btn-outline-secondary': !isVoted, 'btn-secondary': isVoted}" @click="addOrCancelVote">
                      <i class="fa fa-heart-o" aria-hidden="true"></i>
                      <span>点赞</span>
                    </button>
                    <button type="button" class="btn btn-outline-secondary">{{blog.voteSize}}</button>
                  </div>
                </div>
                <div class="px-2">
                  <div class="btn-group" role="group">
                    <button type="button" class="btn btn-outline-secondary">
                      <i class="fa fa-star-o" aria-hidden="true"></i>
                      <span>收藏</span>
                    </button>
                    <button type="button" class="btn btn-outline-secondary">0</button>
                  </div>
                </div>
              </div>
            </div>
            <hr class="my-0">

            <!-- 评论-->
            <Comment/>
          </div><!-- card body end -->
        </div><!-- card end -->
      </div><!-- left end -->

      <!-- 右侧栏目 -->
      <div class="col-md-3">
        <div>
          <!-- 关于作者 -->
          <Author :blog="blog"/>
        </div>

      </div><!-- 右侧栏目 end -->

    </div><!-- .row -->
  </main><!-- .container -->
</template>

<script>
  import Comment from '@/components/blog/Comment.vue'
  import Author from '@/components/blog/Author.vue'
  import {mapState, mapGetters} from 'vuex'
  import {getBlogDetail, deleteBlog } from "@/api/blog"
  import {getTagsList} from "@/api/tag"
  import {existVote, addVote, cancelVote} from "@/api/vote"
  import {getCatalog} from "@/api/catalog"
  import {getUser} from '@/api/user'

  export default {
    name: "Blog",
    data: function () {
      return {
        blogId: this.$route.params.id,
        blog: {},
        catalog: {},
        tags: [],
        vote: {}
      }
    },
    computed: {
      ...mapGetters(['user']),
      isOwner() {
        return this.user.id != null ? this.blog.userId == this.user.id : false
      },
      isLogin() {
        return this.user.id != null
      },
      isVoted() {
        return this.vote != null ? this.vote.status == 1 : false
      }
    },
    components: {
      Comment,
      Author
    },
    mounted() {
      this.initBlog()
    },
    watch: {
      blogId: function () {

      }
    },
    methods: {
      updateBlog() {
        if (this.user.id != null && this.user.id == this.blog.userId) {
          this.$router.push({path: '/blog/edit/' + this.blog.id})
        }
      },
      deleteBlog() {
        if (this.user.id != null && this.user.id == this.blog.userId) {
          if (confirm('确定要删除吗')) {
            /*axios.delete('http://localhost:8081/blog/', this.blog.id)*/
            deleteBlog(this.blog.id)
              .then(response => {
                if (response.status == 200) {
                  alert('删除成功')
                  this.$router.push({path: '/index'})
                } else {
                  console.log('删除失败 ' + response.data.message)
                }
              }).catch(error => {
              console.log('错误' + error)
            })
          }
        }
      },
      addOrCancelVote() {
        if (this.user.id == null) {
          alert('请先登录')
          return
        }
        var vote = this.vote
        if (vote.status == null) {
          vote = {blogId: this.blogId, userId: this.user.id, status: 0}
        }
        if (vote.status == 0) { // 点赞
          vote.status = 1
          /*axios.post('http://localhost:8081/vote/add', this.vote)*/
          addVote(vote)
            .then(response => {
              if (response.status == 200) {
                this.vote = response.data
                this.blog.voteSize++
              } else {
                console.log('点赞失败 ' + response.data.message)
              }
            }).catch(error => {
            console.log('错误' + error)
          })
        } else if (vote.status == 1) { // 取消点赞
          vote.status = 0
          /*axios.put('http://localhost:8081/vote/cancel', this.vote)*/
          cancelVote(vote)
            .then(response => {
              if (response.status == 200) {
                this.vote = response.data
                this.blog.voteSize--
              } else {
                console.log('点赞失败 ' + response.data.message)
              }
            }).catch(error => {
            console.log('错误' + error)
          })
        }
      },
      //-----------------------------------
      initBlog() {
        /*axios.get('http://localhost:8081/blog/' + this.blogId)*/
        getBlogDetail(this.blogId)
          .then(response => {
            if (response.status == 200) {
              this.blog = response.data
              // 初始化博客之后再根据博客的数据去获取其他内容
              this.initCatalog()
              this.initTags()
              this.initUser()
              this.initVote()
            } else {
              console.log('获取博客内容失败 ' + response.data.message)
            }
          })
          .catch(error => {
            console.log('错误' + error)
          })
      },
      initCatalog() {
        /*axios.get('http://localhost:8081/catalog/' + this.blog.catalogId)*/
        getCatalog(this.blog.catalogId)
          .then(response => {
            if (response.status == 200) {
              this.catalog = response.data
            } else {
              console.log('获取博客分类失败 ' + response.data.message)
            }
          })
          .catch(error => {
            console.log('错误' + error)
          })
      },
      initTags() {
        /*axios.get('http://localhost:8081/tags/blog/' + this.blog.id)*/
        getTagsList(this.blog.id)
          .then(response => {
            if (response.status == 200) {
              this.tags = response.data
            } else {
              console.log('获取博客标签失败 ' + response.data.message)
            }
          })
          .catch(error => {
            console.log('错误' + error)
          })
      },
      initUser() {
        /*axios.get('http://localhost:8081/user/' + this.blog.userId)*/
        getUser(this.blog.userId)
          .then(response => {
            if (response.status == 200) {
              this.author = response.data
            } else {
              console.log('初始化用户失败' + response.data.message)
            }
          })
          .catch(error => {
            console.log('初始化用户错误' + error)
          })
      },
      // 获取点过的赞
      initVote() {
        if (this.isLogin) {
          /*axios.get('http://localhost:8081/vote/exists', {
            params: {
              blogId: this.blogId,
              userId: this.user.id
            }
          })*/
          existVote({blogId: this.blogId, userId: this.user.id})
          .then(response => {
            if (response.status == 200) {
              if (response.data.exists == true) {
                this.vote = response.data.vote
              }
            } else {
              console.log('获取点赞失败 ' + response.data.message)
            }
          }).catch(error => {
            console.log('错误' + error)
          })
        }
      }
    }
  }
</script>

<style scoped>

</style>
