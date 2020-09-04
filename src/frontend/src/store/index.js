import Vue from "vue"
import Vuex from "vuex"
import axios from "axios"

Vue.use(Vuex)

/*const resourceHost = "http://localhost:8081/api"*/

const enhanceAccessToken = () => {
    const {accessToken} = localStorage
    if (!accessToken) return
    axios.defaults.headers.common["Authorization"] = `Bearer ${accessToken}`
}
enhanceAccessToken()

export default new Vuex.Store({
    state: {
        accessToken: localStorage.accessToken === null ? null : localStorage.accessToken
    },
    getters: {
        isAuthenticated: function (state) {
            return state.accessToken
        }
    },
    mutations: {
        LOGIN(state, accessToken) {
            
            //store에 토큰 저장
            state.accessToken = accessToken

            //토큰을 로컬 스토리지에 저장
            localStorage.accessToken = accessToken


        },
        LOGOUT(state) {
            //토큰 정보 삭제
            state.accessToken = null
            delete localStorage.accessToken
        }
    },
    actions: {
        LOGIN({ commit }, {email, password}) {
            return axios
                .post('/api/login', {email, password})
                .then(({data}) => {
                    //LOGIN mutations 실행
                    commit("LOGIN", data)

                    //모든 HTTP 요청 헤더에 Authorization 추가
                    axios.defaults.headers.common['Authorization'] = `Bearer ${data}`;
                })
        },
        LOGOUT({ commit }) {
            //HTTP request header값 제거
            axios.defaults.headers.common["Authorization"] = undefined
            commit("LOGOUT")
        }
    },


})

