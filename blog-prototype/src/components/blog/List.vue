<template>

  <ul class="list-group list-group-flush">
    <li class="list-group-item" v-for="blog in blogs" :key="blog.id">
      <h5>
        <a :href="'#/blog/detail/'+blog.id">{{blog.title}}</a>
      </h5>
      <div class="media">
        <div class="media-body pr-2">
          <p class="">{{blog.summary}}</p>
          <div class="text-muted d-flex justify-content-between small">
            <div>
              <a class="" :href="'#/user/'+blog.userId">{{blog.author}}</a>
              <span >发表于 {{blog.createTime}}</span>
            </div>
            <div class="text-muted">
              <span class="ml-2"><i class="fa fa-eye" aria-hidden="true"></i> {{blog.readSize}}</span>
              <span class="ml-2"><i class="fa fa-heart-o" aria-hidden="true"></i> {{blog.voteSize}}</span>
              <span class="ml-2"><i class="fa fa-comment-o" aria-hidden="true"></i> {{blog.commentSize}}</span>
            </div>
          </div>
        </div>
        <img class="media-right" style="width: 150px" :src="defaultBlogImage"/>
      </div>
    </li>
  </ul>

</template>

<script>
  export default {
    name: "List",
    data: function () {
      return {
        defaultBlogImage: '/static/images/Lighthouse.jpg'
      }
    },
    props: {
      blogs: Array
    },
  }
</script>

<style scoped>

</style>
