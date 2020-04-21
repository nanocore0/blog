<template>
  <footer class="footer">
    <p class="text-center text-muted"><span>welcome to <a>new star blog</a></span></p>
  </footer>
</template>

<script>
  export default {
    name: "Footer",
    computed: {
      isShow() {
        if(this.$route.path.startsWith('/admin')) {
          return false
        }
        return true
      }
    }
  }
</script>

<style scoped>
  .footer {
    position: absolute;
    bottom: 0px;
    width: 100%;
    height: 75px; /* Set the fixed height of the footer here */
    line-height: 60px; /* Vertically center the text there */
    background-color: #f5f5f5;
  }
</style>
