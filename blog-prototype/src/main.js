import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'

Vue.config.productionTip = false

// 自定义 v-time 指令
Vue.directive('time', {
  bind: function (el, binding, vnode) {
    var localDateTime = binding.value
    console.log('localDateTime: ' + localDateTime)
    var time = el.innerHTML + ' ' + localDateTime.year + '-' + localDateTime.monthValue + '-' + localDateTime.dayOfMonth
    console.log(time)
    el.innerHTML = time
  }
})

Vue.filter('snippet', function(value) {
  return value.slice(0, 100) + '...'
})

new Vue({
  el: '#app',
  components: {App},
  template: '<App/>',
  router,
  store
})
