<template>
  <!-- The modal -->
  <div class="modal fade  " id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="modalLabel">编辑头像</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-12">
                <label class="btn btn-primary btn-upload" for="inputImage" title="选择本地图片">
                  <input type="file" class="sr-only" id="inputImage" name="file" accept=".jpg,.jpeg,.png,.gif,.bmp,.tiff">
                  <span title="选择本地图片">选择本地图片</span>
                </label>
              </div>
            </div>

            <div class="row mb-2">
              <div class="col-md-9">
                <div class="img-container">
                  <img id="image" :src="user.avatar?user.avatar:defaultAvatar" alt="头像"/>
                </div>
              </div>
              <div class="col-md-3">
                <div class="img-preview preview-rect mb-2" style="overflow: hidden; width: 162px; height: 162px"></div>
                <div class="img-preview preview-round" style="overflow: hidden; width: 162px; height: 162px"></div>
              </div>
            </div>

            <div class="row">
              <div class="col-md-9 ">
                <div class="btn-group">
                  <button class="btn btn-primary" id="button-rotate-left" title="向左旋转"><span class="fa fa-rotate-left"></span></button>
                  <button class="btn btn-primary" id="button-rotate-right" title="向右旋转"><span class="fa fa-rotate-right"></span></button>
                </div>
                <div class="btn-group">
                  <button class="btn btn-primary" id="button-scalex" title="左右反转"><span class="fa fa-arrows-h"></span></button>
                  <button class="btn btn-primary" id="button-scaley" title="上下反转"><span class="fa fa-arrows-v"></span></button>
                </div>
                <div class="btn-group">
                  <button class="btn btn-primary" id="button-crop" title="生成裁剪框"><span class="fa fa-check"></span></button>
                  <button class="btn btn-primary" id="button-clear" title="移除裁剪框"><span class="fa fa-remove"></span></button>
                </div>
                <div class="btn-group">
                  <button class="btn btn-primary" id="button-reset" title="重置"><span class="fa fa-refresh"></span></button>
                  <button class="btn btn-primary" id="button-destroy" title="如果cropper销毁了，那就刷新页面吧"><span class="fa fa-power-off"></span></button>
                </div>
              </div>
              <div class="col-md-3">
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" data-dismiss="modal" @click="saveAvatar">提交</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div><!-- .modal end -->
</template>

<script>
  import {mapGetters} from 'vuex'
  import {upload} from '@/api/upload'
  import {getUser, updateUser} from "@/api/user"

  export default {
    name: "Avatar",
    data: function () {
      return {
        me: {},
        defaultAvatar: '/static/images/avatar-cropper.jpg',
        uploadAvatar: ''
      }
    },
    computed: {
      ...mapGetters(['user', 'avatar'])
    },
    mounted() {
      this.initCropper()
      this.initUser()
    },
    methods: {
      saveAvatar: function () {
        var canvas = this.getCroppedAvatar()
        var form = new FormData()
        form.append('file', canvas)
        upload(form)
          .then(response => {
            if (response.status == 200) {
              this.uploadAvatar = response.data
              this.updateUserAvatar()
            }
          })
          .catch(error => {
            console.log('错误: ' + error)
          })
      },
      updateUserAvatar() {
        this.me.avatar = this.uploadAvatar
        updateUser(this.me.id, this.me)
          .then(response => {
            if (response.status == 200) {
              var user = response.data
              this.me = user
              this.$store.dispatch('setUser', user)
              alert('修改成功')
            } else {
              console.log('修改用户失败' + response.data.message)
            }
          })
          .catch(error => {
            console.log('错误' + error)
          })
      },
      getCroppedAvatar() {
        return $('#image').cropper('getCroppedCanvas').toDataURL('image/jpeg')
      },
      initUser() {
        /*axios.get('http://localhost:8081/user/' + this.user.id)*/
        getUser(this.user.id)
          .then(response => {
            if (response.status == 200) {
              this.me = response.data
            } else {
              console.log('获取用户失败' + response.data.message)
            }
          })
          .catch(error => {
            console.log('错误' + error)
          })
      },
      initCropper() {
        //const image = document.getElementById("image")
        var $image = $('#image')
        //var cropper = new Cropper(image, {
        $image.cropper({
          aspectRatio: 1 / 1, // 正方形
          viewMode: 1, // 裁剪框不能超过图片
          dragMode: 'move',
          //preview: '.img-preview',
          minContainerWidth: 544,
          minContainerHeight: 361,
        })
        var cropper = $image.data('cropper')

        $('#button-rotate-left').on('click', function () {
          cropper.rotate(-45)
        })
        $('#button-rotate-right').on('click', function () {
          cropper.rotate(45)
        })
        $('#button-scalex').on('click', function () {
          var x = cropper.getData().scaleX; //获取原本的 scaleX 取反
          cropper.scaleX(-x)
        })
        $('#button-scaley').on('click', function () {
          cropper.scaleY(-(cropper.getData().scaleY))
        })
        $('#button-crop').on('click', function () {
          cropper.crop()
        })
        $('#button-clear').on('click', function () {
          cropper.clear()
        })
        $('#button-reset').on('click', function () {
          cropper.reset()
        })
        $('#button-destroy').on('click', function () {
          cropper.destroy()
        })

        $('#inputImage').on('change', function () {
          var URL;
          var uploadedImageURL;
          var files = this.files;
          var file;
          if (files && files.length) {
            file = files[0];
            if (/^image\/\w+/.test(file.type)) {
              if (uploadedImageURL) {
                URL.revokeObjectURL(uploadedImageURL);
              }
              URL = window.URL;
              uploadedImageURL = URL.createObjectURL(file);
              cropper.replace(uploadedImageURL)
            }
            $('#inputImage').val('')
          } else {
            alert('Please choose an image file')
          }
        })
      }
    }
  }
</script>

<style scoped>
  .img-container {
    margin-bottom: 1rem;
    max-height: 361px;
    min-height: 200px;
  }

  .img-container, .img-preview {
    background-color: #f7f7f7;
    text-align: center;
    width: 100%;
    margin-bottom: 0px
  }

  @media (min-width: 768px) {
    .img-container {
      min-height: 361px;
    }
  }

  .img-container > img {
    max-width: 100%;
  }

  .preview-rect {
    /*width: 162px;*/
    /*height: 162px;*/
  }

  .preview-round {
    /*width: 162px;*/
    /*height: 162px;*/
    border-radius: 50%
  }
</style>
