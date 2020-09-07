<template>
  <div>
    <h1>메인 페이지</h1>
    <div>
      <router-link v-if="isAuthenticated" to="/write">글쓰기</router-link>
    </div>
    <table>
      <th>번호</th><th>제목</th><th>작성자</th><th>작성일</th>
      <tbody v-for="(item) in boardList" :key="item.id">
        <tr>
          <td>{{item.id}}</td>
          <td><a @click="enterContent(item.id)">{{item.title}}</a></td>
          <td>{{item.userName}}</td>
          <td>{{item.modifyDate}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import store from "@/store";
import axios from 'axios';

export default {
  computed: {
    isAuthenticated() {
      return store.getters.isAuthenticated
    },
  },
  data() {
    return {
      list: '',
      boardList: ''
    }
  },
  created() {
    this.fetchData(0)
   /* this.$bus.$on*/
  },
  /*watch: {
    '$route' : 'fetchData'
  },*/
  methods: {
    fetchData(pageNo) {
      axios.get('/api/list/'+pageNo)
        .then((res) => {
          this.boardList = res.data.list.content

          //Vuex Store에 페이징 정보 설정
          //axios로 가져온 res.data(hashmap)의 key값 list에 해당되는 object의 data를
          //페이징을 위해 넣어준다
         /* this.$store.commit('pageFlush', {
            pageNumber: res.data.list.number,
            pageSize: res.data.list.size,*/

        })
      .catch((err) => {
        window.alert(err)
      })
      },
    enterContent(boardId) {
      this.$router.push('/content/'+boardId)
    }
  }
}

</script>

<style scoped>

</style>