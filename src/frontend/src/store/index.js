import Vue from "vue"
import Vuex from "vuex"
import axios from "axios"
/*import jwt_decode from 'jwt_decode'*/
Vue.use(Vuex)

/*const resourceHost = "http://localhost:8081/api"*/

//새로고침해도 토큰 헤더에 저장
const enhanceAccessToken = () => {
    const {accessToken} = localStorage
    if (!accessToken) return
    axios.defaults.headers.common["Authorization"] = `Bearer ${accessToken}`
}
enhanceAccessToken()

//jwt 토큰 decode
const parseJwt = (token) => {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}

export default new Vuex.Store({
    state: {
        accessToken: localStorage.accessToken === null ? null : localStorage.accessToken,

        //jpa PageRequest
        //페이징
        paging: {
            pageNumber: 1, //현재 페이지 번호
            pageSize: 10, //페이지 사이즈
            totalPages: 0, //전체 페이지 개수
            totalElements: 0, //전체 게시물 개수
            firstPage: 1, //첫 페이지
            prevPage: 0, //이전 페이지(이전 페이지 블록)
            nextPage: 0, //다음 페이지(다음 페이지 블록)
            endPage: 0, //끝 페이지
            pageNumberList: [1] //페이지 번호 리스트
        }
    },
    getters: {
        isAuthenticated: function (state) {
            return state.accessToken;
        },
       getUserName: function (state) {
            if(state.accessToken != null) {
                return parseJwt(state.accessToken).name;
            }
        },
        getPaging: function (state) {
            return state.paging;
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
        },
        PAGING(state, payload) {
            state.paging.pageNumber = payload.pageNumber + 1; //PageRequest는 pageNumber가 0부터 시작
            state.paging.pageSize = payload.pageSize;
            state.paging.totalPages = payload.totalPages;
            state.paging.totalElements = payload.totalElements;

            //이전 페이지 구하기(Math.floor는 내림을 한다)
            if(Math.floor(payload.pageNumber / payload.pageSize) === 0) {
                state.paging.prevPage = 1;
            }else {
                //그 전 페이지 블록에 마지막 페이지로 이동
                //10, 20, 30, 40....
                state.paging.prevPage = Math.floor(payload.pageNumber / payload.pageSize) * payload.pageSize;
            }

            //다음 페이지 구하기
            if((state.paging.prevPage + payload.pageSize) > payload.totalPages) {
                state.paging.nextPage = payload.totalPages;
            }else {
                //1페이지 블록에선 11, 2페이지 블록에선 21....
                state.paging.nextPage = state.paging.prevPage === 1 ? (state.paging.prevPage + payload.pageSize) : (state.paging.prevPage + payload.pageSize) + 1;
            }

            //끝 페이지 구하기
            state.paging.endPage = payload.totalPages;

            //현재 페이지 블록의 페이지 번호 리스트 구하기
            let tempPageNumberList = [];
            //페이지 블록당 페이지 번호 리스트를 만들기 위해 for문을 사용한다
            //처음 페이지 블록의 경우 1~10까지 그 다음 11~20...
            //마지막 블록의 경우 끝 페이지에 +1 값 보다 작은 범위까지 돌려서 만든다
            let curBlockEndPage = state.paging.nextPage === payload.totalPages ? (state.paging.totalPages + 1) : state.paging.nextPage;
            let i = state.paging.prevPage === 1 ? 1: (state.paging.prevPage + 1);

            for(i; i < curBlockEndPage; i++) {
                tempPageNumberList.push(i);
            }
            state.paging.pageNumberList = tempPageNumberList;
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

