<template>
  <div>
    <h1>게시글 상세페이지</h1>
    <div>
      <p>작성자: {{board.userName}}</p>
      <p>작성일: {{board.modifyDate}}</p>
      <p>글번호: {{board.id}}</p>
      <p>제 목: {{board.title}}</p>
      <p>내 용: {{board.content}}</p>
    </div>
    <div>
      <button v-if="userNameCheck === true" @click="board_modify">수정</button>
      <button v-if="userNameCheck === true" @click="board_delete">삭제</button>
      <button @click="goBack">뒤로가기</button>
    </div>
    <Comment v-bind:boardNo="board.id"/>

  </div>
</template>

<script>
import axios from 'axios'
import Comment from "@/components/comment/Comment";

export default {
  components : {
    'Comment' : Comment
  },
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
      axios.get('/api/board/content/'+this.$route.params.id)
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
    },
    board_modify() {
      this.$router.push('/modify/' + this.$route.params.id)
    },
    board_delete() {
      if(window.confirm('게시글을 삭제하시겠습니까?')) {
        axios.get('/api/board/delete/'+this.$route.params.id)
            .then((res) => {
          window.alert(res.data.msg)
            if(res.data.result === 1) {
              this.$router.push('/')
            }else {
              this.$router.replace('/')
            }

        }).catch((err) => {
          window.alert(err)
          window.console.log(err)
        })
      }
    },
    goBack() {
      this.$router.replace('/')
    }
  }
}
</script>

<style scoped>

</style>