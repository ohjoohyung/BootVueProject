<template>
  <div>
    <h1>게시글 상세페이지</h1>
    <div>
      <p>{{board.userName}}</p>
      <p>{{board.modifyDate}}</p>
      <p>{{board.id}}</p>
      <p>{{board.title}}</p>
      <p>{{board.content}}</p>
    </div>

  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      board: {
        id: '',
        title: '',
        content: '',
        userName: '',
        modifyDate: '',
      }
    }
  },
  created() {
    this.fetchData()
  },
  /*watch: {
    //라우트 객체를 감시하고 있다가 fetchData()를 호출한다
    '$route' : 'fetchData'
  },*/
  computed: {
    userNameCheck() {
      return this.$store.getters.getUserName === this.board.userName
    }
  },
  methods: {
    fetchData() {
      axios.get('/api/content/'+this.$route.params.id)
      .then((res) => {
        if(res.data.result === 1) {
          this.board = res.data.board
        }else {
          window.alert(res.data.msg)
          this.$router.replace('/')
        }
      }).catch((err) => {
        window.alert(err)
      })
    }
  }
}
</script>

<style scoped>

</style>