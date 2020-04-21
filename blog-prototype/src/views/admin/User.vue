<template>
  <main class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
    <div class="pb-2 mb-3 border-bottom">
      <h1 class="h2">用户管理</h1>
    </div>
    <!-- 用户管理交互 -->
    <nav class="navbar justify-content-end">
      <form class="form-inline">
        <div class="input-group">
          <input class="form-control" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-outline-success mr-2" type="submit">
              <i class="fa fa-search" aria-hidden="true"></i>
            </button>
          </div>
        </div>
        <button class="btn btn-outline-success" data-toggle="modal" data-target="#exampleModal">
          <i class="fa fa-plus" aria-hidden="true"></i>
        </button>
      </form>
    </nav>
    <!-- 用户表格 -->
    <div class="">
      <table class="table table-striped">
        <thead>
        <tr>
          <th>#</th>
          <th>Username</th>
          <th>Nickname</th>
          <th>Email</th>
          <th>Role</th>
          <th>Operation</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>1</td>
          <td>admin</td>
          <td>香草冰淇淋</td>
          <td>pianxian@game.com</td>
          <td>ADMIN</td>
          <td>
            <div>
              <a class="btn btn-link btn-sm py-0" data-toggle="modal" data-target="#exampleModal" role="button">
                <i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
              <a class="btn btn-link btn-sm py-0" data-toggle="modal" role="button">
                <i class="fa fa-times" aria-hidden="true"></i>
              </a>
            </div>
          </td>
        </tr>
        <tr>
          <td>2</td>
          <td>nanocore</td>
          <td>一舞翩跹</td>
          <td>pianxian@game.com</td>
          <td>USER</td>
          <td>
            <div>
              <a class="btn btn-link btn-sm py-0" data-toggle="modal" data-target="#exampleModal" role="button">
                <i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
              <a class="btn btn-link btn-sm py-0" data-toggle="modal" role="button">
                <i class="fa fa-times" aria-hidden="true"></i>
              </a>
            </div>
          </td>
        </tr>
        <tr>
          <td>3</td>
          <td>小鳄鱼</td>
          <td>邪恶的小鳄鱼</td>
          <td>aa@bb.cc</td>
          <td>USER</td>
          <td>
            <div>
              <a class="btn btn-link btn-sm py-0" data-toggle="modal" data-target="#exampleModalCenter" role="button">
                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
              </a>
              <a class="btn btn-link btn-sm py-0" data-toggle="modal" role="button">
                <i class="fa fa-times" aria-hidden="true"></i>
              </a>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </main>
</template>

<script>


  export default {
    name: "User",
    data: function () {
      return {
        users: []
      }
    },
    mounted() {
      this.initUsersList()
    },
    methods: {
      initUsersList() {

      }
    }
  }
</script>

<style scoped>

</style>
