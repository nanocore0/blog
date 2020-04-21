<template>
  <!-- 编辑博客页面 -->
  <main class="container" role="main">
    <div class="row">
      <div class="col-md-9">
        <!-- blog post -->
        <div class="card mb-4">
          <div class="card-body">
            <form>
              <div class="form-group">
                <label for="title">博客标题</label>
                <input type="text" class="form-control" id="title" placeholder="请填写博客标题" v-model="editingBlog.title">
              </div>
              <div class="form-group">
                <label for="summary">博客摘要</label>
                <textarea class="form-control" id="summary" rows="3" placeholder="请填写博客摘要" v-model="editingBlog.summary"></textarea>
              </div>
              <div class="form-group">
                <label for="md">博客正文</label>
                <textarea class="form-control" id="md" data-provide="markdown" data-hidden-buttons="cmdImage" v-model="editingBlog.content"></textarea>
              </div>
            </form>
          </div>
        </div><!-- blog post end -->
      </div>

      <!-- 右侧栏目 -->
      <div class="col-md-3">
        <div>
          <!-- 文章目录 -->
          <div class="card">
            <div class="card-header">
              <i class="fa fa-file-image-o" aria-hidden="true"></i> <span>图片上传</span>
            </div>
            <div class="card-body">
              <form enctype="multipart/form-data">
                <input type="file" name="file" accept="image/png,image/gif,image/jpeg">
                <button class="btn btn-primary btn-sm float-right" type="button">插入</button>
              </form>
            </div>
          </div>
          <!-- 博客设置 -->
          <div class="card mt-4">
            <div class="card-header">
              <i class="fa fa-cog" aria-hidden="true"></i> <span>博客设置</span>
            </div>
            <div class="card-body">
              <div class="form-group">
                <label for="tags">标签：</label>
                <input type="text" class="form-control form-control-sm" data-role="tagsinput" id="tags">
              </div>
              <div class="form-group">
                <label for="catalog">分类：</label>
                <select class="form-control form-control-sm" id="catalog" v-model="selectedCatalogName">
                  <option v-for="catalog in catalogs" :key="catalog.id">{{catalog.name}}</option>
                </select>
              </div>
              <button class="btn btn-primary btn-sm float-right" @click="saveBlog">发布</button>
            </div>
          </div><!-- .card end -->
        </div>
      </div><!-- 右侧栏目 end -->

    </div><!-- .row -->
  </main><!-- .container -->
</template>

<script>
  import {getBlogDetail, saveBlog, updateBlog} from '@/api/blog'
  import {getCatalogsList, addCatalog} from '@/api/catalog'
  import {getTagsList} from '@/api/tag'

  import {mapState, mapGetters} from 'vuex'

  export default {
    name: "Add",
    data: function () {
      return {
        blogId: this.$route.params.id, // 编辑博客时有值
        editingBlog: {},
        catalogs: [],
        tags: [],
        selectedCatalogName: '',
      }
    },
    beforeRouteEnter(to, from, next) {
      console.log('##进入编辑博客页面')
      next()
    },
    beforeRouteLeave(to, from, next) {
      //alert('还未保存，确定要离开吗')
      next()
    },
    mounted() {
      this.initMarkdown()
      this.initTagsInput()
      this.initAllCatalogs()
      this.initBlog()
    },
    computed: {
      ...mapGetters(['user']),
      tagStr() {
        return this.generateTagStrFromTagsList(this.tags)
        //return 'foo,bar,baz'
      }
    },
    methods: {
      // 保存博客
      saveBlog() {
        var blog = this.editingBlog
        if (blog.id == null) {
          // 新增博客
          blog.author = this.user.nickname
          blog.userId = this.user.id
          blog.catalogId = this.findSelectedCatalogByName(this.selectedCatalogName, this.catalogs).id
          blog.htmlContent = this.getMdHtmlContent()
          blog.tags = this.generateTagListFromTagsStr(this.getTagsValue())
          console.log(blog)

          /*axios.post('http://localhost:8081/blog', blog)*/
          saveBlog(blog)
            .then(response => {
              if (response.status == 200) {
                console.log('保存博客成功')
                this.editingBlog = response.data
                this.$router.push({path: '/blog/detail/' + this.editingBlog.id})
              } else {
                console.log(response.data.message)
              }
            }).catch(error => {
            console.log('错误' + error)
          })
        } else {
          // 修改博客
          blog.catalogId = this.findSelectedCatalogByName(this.selectedCatalogName, this.catalogs).id
          blog.htmlContent = this.getMdHtmlContent()
          blog.tags = this.generateTagListFromTagsStr(this.getTagsValue())

          /*axios.put('http://localhost:8081/blog/' + blog.id, blog)*/
          updateBlog(blog.id, blog)
            .then(response => {
              if (response.status == 200) {
                console.log('修改博客成功')
                this.editingBlog = response.data
                this.$router.push({path: '/blog/detail/' + this.editingBlog.id})
              } else {
                console.log(response.data.message)
              }
            }).catch(error => {
            console.log('错误' + error)
          })
        }
      },
      // -------------------------------------
      // 根据分类名称从分类列表找出 分类对象
      findSelectedCatalogByName(catalogName, catalogs) {
        return catalogs.find(catalog => {
          return catalog.name == catalogName
        })
      },
      // 根据分类id从分类列表找出 分类对象
      findSelectedCatalogById(catalogId, catalogs) {
        return catalogs.find(catalog => {
          return catalog.id == catalogId
        })
      },
      // 添加一个默认分类
      addDefaultCatalog() {
        /*axios.post('http://localhost:8081/catalog', {
          name: '默认分类',
          userId: this.user.id
        })*/
        addCatalog({name: '默认分类', userId: this.user.id})
          .then(response => {
            if (response.status == 200) {
              var catalog = response.data
              this.catalogs = [catalog]
              this.selectedCatalogName = catalog.name
            } else {
              console.log(response.data.message)
            }
          })
          .catch(error => {
            console.log('错误' + error)
          })
      },
      // 从标签插件的字符串生成标签对象列表
      generateTagListFromTagsStr(tags) {
        var arr = tags.split(',')
        var tagsList = []
        for (var i = 0; i < arr.length; i++) {
          tagsList[i] = {name: arr[i]}
        }
        return tagsList
      },
      // 从标签对象列表生成标签插件识别的字符串
      generateTagStrFromTagsList(tags) {
        var tagStr = ''
        for (var i = 0; i < tags.length; i++) {
          if(i < tags.length -1) {
            tagStr = tagStr.concat(tags[i].name, ',')
          } else {
            tagStr = tagStr.concat(tags[i].name)
          }
        }
        return tagStr
      },
      // 获得标签插件的值
      getTagsValue() {
        console.log($('#tags').val())
        return $('#tags').val()
      },
      addTagsValue(tags) {
        for(var i = 0; i < tags.length; i++) {
          $('#tags').tagsinput('add', tags[i].name);
        }
      },
      // 获得编辑器解析后的内容
      getMdHtmlContent() {
        return $('#md').data('markdown').parseContent()
      },
      // --------------------------------------
      // 编辑博客时初始化数据
      // 初始化博客
      initBlog() {
        if (this.blogId) {
          /*axios.get('http://localhost:8081/blog/' + this.$route.params.id)*/
          getBlogDetail(this.blogId)
            .then(response => {
              if (response.status == 200) {
                this.editingBlog = response.data
                // 获取博客成功，再初始化标签列表，分类列表
                this.initAllCatalogs()
                this.initTags()
              } else {
                console.log(response.data.message)
              }
            }).catch(error => {
            console.log('错误' + error)
          })
        }
      },
      // 打开页面时获取分类列表
      initAllCatalogs() {
        // 如果有参数，则是更新博客，使用查询出的博客的作者的 userId 获取分类列表
        if (this.blogId) {
          // 防止重复调用
          if (this.editingBlog.userId == null) {
            return
          }
          getCatalogsList(this.editingBlog.userId)
            .then(response => {
              if (response.status == 200) {
                var catalogs = response.data
                this.catalogs = catalogs
                // 选中博客的分类
                this.selectedCatalogName = this.findSelectedCatalogById(this.editingBlog.catalogId, catalogs).name
              } else {
                console.log(response.data.message)
              }
            }).catch(error => {
            console.log('错误' + error)
          })
        } else {
          // 否则说明是新增博客，使用存放在 store 的 user 的 id 获取分类列表
          /*axios.get('http://localhost:8081/catalogs/' + this.user.id)*/
          getCatalogsList(this.user.id)
            .then(response => {
              if (response.status == 200) {
                var catalogs = response.data
                // 如果还没有分类，则创建一个默认分类
                if (catalogs == null || catalogs.length == 0) {
                  this.addDefaultCatalog()
                } else {
                  this.catalogs = catalogs
                  // 选中博客的分类，默认选择第一个
                  this.selectedCatalogName = catalogs[0].name
                }
              } else {
                console.log(response.data.message)
              }
            }).catch(error => {
            console.log('错误' + error)
          })
        }
      },
      // 编辑博客时初始化标签
      initTags() {
        if (this.blogId) {
          /*axios.get('http://localhost:8081/tags/blog/' + this.editingBlog.id)*/
          getTagsList(this.editingBlog.id)
            .then(response => {
              if (response.status == 200) {
                var tags = response.data
                this.tags = tags
                this.addTagsValue(tags)
              } else {
                console.log(response.data.message)
              }
            }).catch(error => {
            console.log('错误' + error)
          })
        }
      },
      // 初始化markdown
      initMarkdown() {
        $("#md").markdown({
          language: 'zh',
          fullscreen: {
            enable: true
          },
          resize: 'vertical',
          localStorage: 'md',
          imgurl: 'http://localhost:8081/upload/img',
          base64url: 'http://localhost:8081/upload/base64'
        })
      },
      // 初始化标签插件
      initTagsInput() {
        /*$('#tags').tagsInput({
          'height': 'auto',
          'width': 'auto',
          'defaultText': ''
        })*/
        $('#tags').tagsinput()
      }
    }
  }
</script>

<style scoped>

</style>
