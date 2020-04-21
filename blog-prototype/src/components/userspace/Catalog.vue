<template>
  <div>
    <div class="card ">
      <div class="card-header">
        <div class="d-flex justify-content-between">
          <div>
            <i class="fa fa-bars" aria-hidden="true"></i> <span>分类</span>
          </div>
          <div>
            <a href="javascript:void(0)" data-toggle="modal" data-target="#exampleModal" role="button" @click="addCatalog" v-if="isOwner">
              <i class="fa fa-plus" aria-hidden="true"></i>
            </a>
          </div>
        </div>
      </div>
      <ul class="list-group">
        <li class="list-group-item d-flex justify-content-between" v-for="(catalog) in catalogs" :key="catalog.id">
          <div>
            <a class="" href="javascript:void(0)" @click="queryBlogsList(catalog.id)" :title="catalog.name">{{catalog.name}}</a>
          </div>
          <div>
          <span>
            <a href="javascript:void(0)" data-toggle="modal" data-target="#exampleModal" role="button" @click="editCatalog(catalog.id)" v-if="isOwner">
              <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
            </a>
            <a href="javascript:void(0)" @click="deleteCatalog(catalog.id)" v-if="isOwner"><i class="fa fa-times" aria-hidden="true"></i></a>
          </span>
          </div>
        </li>
      </ul>
    </div>
    <!-- model -->
    <div class="modal" id="exampleModal" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">新建分类</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="container">
              <form>
                <div class="form-group">
                  <label for="catalogName" class="col-form-label">名称</label>
                  <input type="text" class="form-control" id="catalogName" maxlength="30" v-model="editingCatalog.name" placeholder="请输入分类名称，至少2个字符，至多30个">
                </div>
              </form>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" @click="saveCatalog">Save changes</button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {getCatalogsList, getCatalog, addCatalog, updateCatalog, deleteCatalog} from '@/api/catalog'
  import {mapState, mapGetters} from 'vuex'


  export default {
    name: "Catalog",
    data: function () {
      return {
        userId: this.$route.params.id,
        catalogs: [],
        editingCatalog: {}
      }
    },
    computed: {
      ...mapGetters(['user']),
      isOwner() {
        return (this.user.id != null) ? this.userId == this.user.id : false
      }
    },
    mounted() {
      this.flushCatalogList()
    },
    methods: {
      queryBlogsList(catalogId) {
        this.$emit('page', 1, 8, 'default', catalogId, null)
      },
      // 初始化分类列表
      flushCatalogList() {
        /*axios.get('http://localhost:8081/catalogs/' + this.userId)*/
        getCatalogsList(this.userId)
          .then(response => {
            if (response.status == 200) {
              var catalogs = response.data
              this.catalogs = catalogs
            } else {
              console.log('失败了' + response.data.message)
            }
          }).catch(error => {
          console.log('错误' + error)
        })
      },
      // 添加分类，修改 model 中 input 绑定的数据为一个空的 catalog
      addCatalog() {
        this.editingCatalog = {
          name: '',
          userId: this.user.id
        }
      },
      // 修改分类
      editCatalog(id) {
        // 修改model 中 input 绑定的数据为分类列表中选中的那个
        /*this.editingCatalog = this.catalogs.find(catalog => {
          return catalog.id == id
        })*/
        //Object.assign(this.editingCatalog, this.catalogs.find(catalog => {return catalog.id == id}))
        // 从后台查询出这个分类
        getCatalog(id)
          .then(response => {
            if (response.status == 200) {
              this.editingCatalog = response.data
            } else {
              console.log('获取分类失败: ' + response.data.message)
            }
          })
          .catch(error => {
            console.log('错误' + error)
          })
      },
      // 删除分类
      deleteCatalog(id) {
        /*axios.delete('http://localhost:8081/catalog/' + id)*/
        if (confirm('确认删除吗')) {
          deleteCatalog(id)
            .then(response => {
              if (response.status == 200) {
                this.flushCatalogList()
              } else {
                alert('删除失败')
                console.log(response.data.message)
              }
            }).catch(error => {
            console.log('错误')
            alert('删除失败，可能该分类下已存在内容')
          })
        }
      },
      // 保存分类
      saveCatalog() {
        var catalog = this.editingCatalog
        console.log('保存分类:' + catalog)
        if (catalog.id == null) {
          // 添加
          /*axios.post('http://localhost:8081/catalog', catalog)*/
          addCatalog(catalog)
            .then(response => {
              if (response.status == 200) {
                this.flushCatalogList()
              } else {
                console.log(response.data.message)
              }
            }).catch(error => {
            console.log('错误')
          })
        } else {
          // 更新
          /*axios.put('http://localhost:8081/catalog' + catalog.id, catalog)*/
          updateCatalog(catalog.id, catalog)
            .then(response => {
              if (response.status == 200) {
                this.flushCatalogList()
              } else {
                console.log(response.data.message)
              }
            }).catch(error => {
            console.log('错误')
          })
        }
      }
    }
  }
</script>

<style scoped>

</style>
