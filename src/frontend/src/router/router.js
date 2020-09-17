import Vue from 'vue'
import Router from 'vue-router'


const Content = () => import("@/components/board/Content")
const Body = () => import("@/components/main/body/Body")
const Join = () => import("@/components/join/Join")
const Login = () => import("@/components/login/Login")
const Me = () => import("@/components/user/Me")
const Write = () => import("@/components/board/Write")
const Modify = () => import("@/components/board/Modify")

Vue.use(Router)

const requireAuth = () => (from, to, next) => {
    let isAuthenticated = false
    if(localStorage.accessToken != null) {
        isAuthenticated = true
    }
    if(isAuthenticated) return next()
    next('/login?returnPath=me')
}

export const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'Body',
            component: Body
        },
        {
            path:'/join',
            name: 'Join',
            component: Join
        },
        {
            path:'/login',
            name: 'Login',
            component: Login
        },
        {
            path:'/me',
            name: 'Me',
            component: Me,
            beforeEnter: requireAuth()
        },
        {
            path: '/write',
            name: "Write",
            component: Write
        },
        {
            path: '/content/:id',
            component: Content
        },
        {
            path: '/modify/:id',
            component: Modify
        }
    ]

})