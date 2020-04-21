import Vue from 'vue'
import Vuex from 'vuex'

import user from './modules/user'
import getters from './getters'

Vue.use(Vuex)

/*const state = {
  user: {}
}

const mutations = {
  add(state, {user}) {
    state.user = user
  },
  delete(state) {
    state.user = {}
  },
  update(state, {user}) {
    state.user = user
  }
}

const actions = {
  initUser({commit}, user) {
    console.log('action 登录')
    commit('add', {user})
  },
  clearUser({commit}) {
    console.log('action 退出')
    commit('delete')
  },
  updateUser({commit}, user) {
    console.log('action 更新')
    commit('update', {user})
  }
}*/

const store = new Vuex.Store({
  modules: {
    user
  },
  getters
})

export default store
