import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '../views/Home'
import Login from '../views/Login'
import Register from '../views/Register'
import Search from '../views/Search'

import Add from '../views/blog/Add'
import Blog from '../views/blog/Blog'
import User from '../views/user/User'
import Space from '../views/user/Space'
import Profile from '../views/user/Profile'
import Password from '../views/user/Password'

import Dashboard from '../views/admin/Dashboard'

Vue.use(VueRouter)

const router = new VueRouter({
  routes:[
    {path: '/', component: Home, name: 'home'},
    {path: '/index', redirect: '/'},
    {path: '/login', component: Login, name: 'login'},
    {path: '/register', component: Register, name: 'register'},
    {path: '/search', component: Search},
    {path: '/user/:id', component: User},
    {
      path: '/userspace',
      component: Space,
      name: 'userspace',
      children: [
        {path: 'profile', component: Profile},
        {path: 'password', component: Password}
      ]
    },
    {path: '/blog/add', component: Add},
    {path: '/blog/edit/:id', component: Add},
    {path: '/blog/detail/:id', component: Blog},
    {
      path: '/admin',
      component: Dashboard,
      name: 'admin',
     /* redirect: '/admin/user',*/
      children: [
        {path: 'user', component: () => import('../views/admin/User')}

      ]
    }
  ],
  mode: "history"
})

// 路由守卫
/*const HAS_LOGIN = true
router.beforeEach((to, from, next) => {
  if(to.name !== 'login') {
    if(HAS_LOGIN) next()
    else next({name: 'login'})
  } else {
    if(HAS_LOGIN) next({name: 'home'})
    else next()
  }
})*/

export default router

