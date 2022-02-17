import { createRouter, createWebHistory } from 'vue-router'
import Login from "@/views/Auth/Login";
import Register from "@/views/Auth/Register";
import MainPage from "@/views/mainPage/MainPage"
import store from "@/store/index"

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Login
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/mainPage',
    name: 'MainPage',
    component: MainPage
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) =>{
  if (to.name !== 'Login' && to.name !== 'Register' && ! store.getters.isLoggedIn)
    next({ name: 'Login' })
  else next()
})

export default router
