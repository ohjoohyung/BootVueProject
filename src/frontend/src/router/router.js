import Vue from 'vue'
import Router from 'vue-router'
import store from "@/store";

const Content = () => import("@/components/board/Content")
const Body = () => import("@/components/main/body/Body")
const Join = () => import("@/components/join/Join")
const Login = () => import("@/components/login/Login")
const Write = () => import("@/components/board/Write")
const Modify = () => import("@/components/board/Modify")
const Agreement = () => import("@/components/login/Agreement")
Vue.use(Router)


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
            path:'/agreement',
            name: 'Agreement',
            component: Agreement
        },
        {
            path: '/write',
            name: "Write",
            component: Write,
            //라우트 가드
            beforeEnter: (to, from, next) => {
                if(store.getters.isAuthenticated === undefined) {
                    window.alert('로그인 후 이용해주시기 바랍니다.')
                    next('/login')
                }else {
                    next()
                }

            }
        },
        {
            path: '/content/:id',
            component: Content
        },
        {
            path: '/modify/:id',
            component: Modify,
            //라우트 가드
            beforeEnter: (to, from, next) => {
                if(store.getters.isAuthenticated === undefined) {
                    window.alert('로그인 후 이용해주시기 바랍니다.')
                    next('/login')
                }else {
                    next()
                }

            }
        }
    ]

})