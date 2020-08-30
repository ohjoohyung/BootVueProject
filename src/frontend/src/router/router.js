import Vue from 'vue'
import Router from 'vue-router'

import Main from "@/components/main/Main";
import Join from "@/components/join/Join";
Vue.use(Router)

export const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'Main',
            component: Main
        },
        {
            path:'/join',
            name: 'Join',
            component: Join
        }
    ]

})