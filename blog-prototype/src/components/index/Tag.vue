<template>
  <div class="card mb-4">
    <div class="card-header">
      <i class="fa fa-tags" aria-hidden="true"></i> <span>热门标签</span>
    </div>
    <div class="card-body">
      <div>
        <a class="mr-1" :href="'#/search?keyword='+tag.name" v-for="tag in tags" :key="tag.id"> <span class="badge badge-success">{{tag.name}}</span></a>
      </div>
    </div>
  </div>
</template>

<script>
  import {listTopTags} from '@/api/tag'

  export default {
    name: "Tag",
    data: function () {
      return {
        tags: []
      }
    },
    mounted() {
      this.initHotestTags()
    },
    methods: {
      initHotestTags() {
        listTopTags()
          .then(response => {
            if (response.status == 200) {
              this.tags = response.data
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
