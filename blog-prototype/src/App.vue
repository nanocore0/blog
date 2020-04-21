<template>
  <div id="app">
    <Header :user="user" />
    <router-view></router-view>
    <Footer />
  </div>
</template>

<script>
  import Header from './components/fragments/Header'
  import Footer from './components/fragments/Footer'

  export default {
    name: 'App',
    data: function() {
      return {
        user: null
      }
    },
    components: {
      Header,
      Footer,
    }
  }
</script>

<style>
  /* 处理页面导航栏，页脚，内容布局 */
  html {
    position: relative;
    min-height: 100%;
  }

  body {
    margin-bottom: 75px; /* 距页脚高度 */
    /*background-image: url('/static/images/background.png');*/
    font-size: 14px;
  }

  main[role="main"] {
    width: auto;
    padding-top: 15px;
    padding-bottom: 15px;
  }

  /* 用户头像 */
  .blog-avatar-50 {
   border-radius: 50%;
    height: 50px;
    width: 50px;
  }

  .blog-avatar-85 {
    /*border-radius: 50%;*/
    height: 85px;
    width: 85px;
  }

  .blog-avatar-160 {
    /*border-radius: 50%;*/
    height: 160px;
    width: 160px;
  }

</style>
