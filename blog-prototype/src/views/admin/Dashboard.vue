<template>
  <div class="container-fluid">
    <div class="row">
      <!-- 侧边导航栏 -->
      <nav class="col-md-2 d-none d-md-block bg-light sidebar">
        <div class="sidebar-sticky">
          <ul class="nav flex-column">
            <li class="nav-item">
              <a class="nav-link active" href="#/admin/user">
                <span data-feather="home"></span>
                用户管理 <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/admin/">
                <span data-feather="file"></span>
                权限管理
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/admin/">
                <span data-feather="shopping-cart"></span>
                博客管理
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#/admin/">
                <span data-feather="users"></span>
                评论管理
              </a>
            </li>
          </ul>
        </div>
      </nav>

      <router-view/>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Dashboard",
    beforeRouteEnter(to, from, next) {
      /*if(this.$store.getters.roles) {
        console.log('未登录')
        next({name: 'login'})
      }
      var isAdmin = false
      var roles = this.$store.getters.roles
      for(var i = 0; i < roles.length; i++) {
        if(roles[i].id == 1 && roles[i].roleName == 'admin') {
          isAdmin = true
        }
      }
      if(!isAdmin) {
        console.log('不是管理员')
        next({name: 'home'})
      }*/
      next()
    },
    data: function () {
      return {
      }
    },
    mounted() {
      this.initFeather()
    },
    methods: {
      initFeather() {
        feather.replace()
      }
    }
  }
</script>

<style scoped>
  .feather {
    width: 16px;
    height: 16px;
    vertical-align: text-bottom;
  }

  /*
   * Sidebar
   */
  .sidebar {
    /*position: fixed;*/
    top: 0;
    bottom: 0;
    left: 0;
    z-index: 100; /* Behind the navbar */
    padding: 0;
    box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
  }

  .sidebar-sticky {
    position: -webkit-sticky;
    position: sticky;
    /*top: 48px;*/ /* Height of navbar */
    height: calc(100vh - 48px);
    padding-top: .5rem;
    overflow-x: hidden;
    overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
  }

  .sidebar .nav-link {
    font-weight: 500;
    color: #333;
  }

  .sidebar .nav-link .feather {
    margin-right: 4px;
    color: #999;
  }

  .sidebar .nav-link.active {
    color: #007bff;
  }

  .sidebar .nav-link:hover .feather,
  .sidebar .nav-link.active .feather {
    color: inherit;
  }

  .sidebar-heading {
    font-size: .75rem;
    text-transform: uppercase;
  }

  .border-top {
    border-top: 1px solid #e5e5e5;
  }

  .border-bottom {
    border-bottom: 1px solid #e5e5e5;
  }
</style>
