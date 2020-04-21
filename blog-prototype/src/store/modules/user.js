import {login, getInfo, logout} from '@/api/login'
import {getToken, setToken, removeToken} from '@/util/auth'

const user = {
  state: {
    token: getToken('token'),
    user: {},
    avatar: '/static/images/avatar-default-1.jpg',
    roles: [],
  },

  mutations: {
    SET_TOKEN(state, token) {
      state.token = token
    },
    SET_USER(state, user) {
      state.user = user
    },
    SET_AVATAR(state, avatar) {
      state.avatar = avatar
    },
    SET_ROLES(state, roles) {
      state.roles = roles
    }
  },

  actions: {
    /*
    login({commit}, form) {
      return new Promise((resolve, reject) => {
        // 调用 api 里面封装的 login 请求
        login(form)
          .then(response => {
            if (response.status == 200) {
              // 调用 util 里面的 setToken，持久化 token
              setToken('token', response.data.access_token)
              resolve(response)
            } else {
              resolve(response)
            }
          })
          .catch(error => {
            reject(error)
          })
      })
    },
    getInfo({commit}) {
      return new Promise((resolve, reject) => {
        // 调用 api 封装的 getInfo 请求
        getInfo()
          .then(response => {
            if (response.status == 200) {
              // 保存数据到 store
              commit('SET_USER', response.data)
              commit('SET_ROLE', response.data.authorities)
              commit('SET_AVATASAR', response.data.avatar)
              resolve(response)
            } else {
              resolve(response)
            }
          })
          .catch(error => {
            reject(error)
          })
      })
    },
    logout({commit}) {
      return new Promise((resolve, reject) => {
        logout()
          .then(response => {
            if (response.status == 200) {
              // 清除持久化的 token
              removeToken('token')
              // 清空 store 数据
              commit('SET_USER', {})
              commit('SET_ROLE', [])
              commit('SET_AVATASAR', null)
              resolve(response)
            } else {
                resolve(response)
            }
          })
          .catch(error => {
            reject(error)
          })
      })
    }
    */
    // 修改，只操作 vuex
    addToken({commit}, token) {
      commit('SET_TOKEN', token.access_token)
    },
    clearToken({commit}) {
      commit('SET_TOKEN', '')
    },
    addUser({commit}, user) {
      commit('SET_USER', user)
      if (user.avatar != null && user.avatar != '') {
        commit('SET_AVATAR', user.avatar)
      }
      commit('SET_ROLES', user.roles)
    },
    setUser({commit}, user) {
      commit('SET_USER', user)
      if (user.avatar != null && user.avatar != '') {
        commit('SET_AVATAR', user.avatar)
      }
    },
    removeUser({commit}) {
      commit('SET_USER', {})
      commit('SET_AVATAR', '/static/images/avatar-default-1.jpg')
      commit('SET_ROLES', [])
    }
  }
}

export default user
