import Vue from 'vue'
import Router from 'vue-router'

import Body from "@/components/main/body/Body";
import Join from "@/components/join/Join";
import Login from "@/components/login/Login";
import Me from "@/components/user/Me";

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
        }
    ]

})