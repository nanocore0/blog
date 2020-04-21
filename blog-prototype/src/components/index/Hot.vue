<template>
  <div class="card mb-4">
    <div class="card-header">
      <i class="fa fa-fire" aria-hidden="true"></i> <span>热门文章</span>
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item" v-for="blog in blogs" :key="blog.id">
        <a class="small" :href="'/#/blog/detail/'+blog.id" :title="blog.author">{{blog.title}} <span class="badge badge-danger badge-pill">{{hot(blog)}}</span></a>
      </li>
    </ul>
  </div>
</template>

<script>
  import {listHotestBlogs} from '@/api/blog'

  export default {
    name: "Hot",
    data: function () {
      return {
        blogs: []
      }
    },
    computed: {
    },
    mounted() {
      this.initHotestBlogs()
    },
    methods: {
      initHotestBlogs() {
        listHotestBlogs()
          .then(response => {
            if(response.status == 200) {
              this.blogs = response.data
            }
          })
          .catch(error => {})
      },
      hot(blog) {
        return blog.commentSize + blog.voteSize + blog.readSize
      }
    }
  }
</script>

<style scoped>

</style>
