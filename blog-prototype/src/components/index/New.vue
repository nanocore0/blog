<template>
  <div class="card">
    <div class="card-header">
      <i class="fa fa-line-chart" aria-hidden="true"></i> <span>最新发布</span>
    </div>
    <ul class="list-group">
      <li class="list-group-item" v-for="blog in blogs" :key="blog.id">
        <a class="small" :href="'/#/blog/detail/'+blog.id" :title="blog.author">{{blog.title}}</a>
      </li>
    </ul>
  </div>
</template>

<script>
  import {listNewestBlogs} from '@/api/blog'

  export default {
    name: "New",
    data: function () {
      return {
        blogs: []
      }
    },
    mounted() {
      this.initNewestBlogs()
    },
    methods: {
      initNewestBlogs() {
        listNewestBlogs()
          .then(response => {
            if (response.status == 200) {
              this.blogs = response.data
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
