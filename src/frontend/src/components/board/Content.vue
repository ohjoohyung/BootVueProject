<template>
  <div>

    <v-card style="width: 60%" class="mx-auto mt-2" min-height="600">
      <v-card-title>{{board.title}}</v-card-title>
      <v-divider class="mx-4"></v-divider>
      <v-card-subtitle class="text-right">
        <span>작성자: {{board.userName}}</span>
        <span class="ml-5">작성일: {{board.modifyDate}}</span>
      </v-card-subtitle>
      <v-card-text style="min-height: 400px">
        <div class="text-left text--primary">
          {{board.content}}
        </div>
      </v-card-text>
      <v-card-actions>
        <v-row>
          <v-col cols="8" class="text-left">
            <v-btn outlined class="ma-2" v-if="userNameCheck === true" @click="board_modify">수정</v-btn>
            <v-btn outlined class="ma-2" v-if="userNameCheck === true" @click="board_delete">삭제</v-btn>

          </v-col>

          <v-col cols="4" class="text-right">
            <v-btn outlined class="ma-2" @click="goBack">뒤로가기</v-btn>
          </v-col>
        </v-row>

      </v-card-actions>





    <CommentList />
    </v-card>
  </div>
</template>

<script>
import axios from 'axios'
import CommentList from "@/components/comment/CommentList";

export default {
  components : {
    CommentList
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
    this.fetchContentData()
  },
  /*watch: {
    //라우트 객체를 감시하고 있다가 fetchData()를 호출한다
    '$route' : 'fetchData'
  },*/
  computed: {
    userNameCheck() {
        return this.$store.getters.getUserName === this.board.userName
    },

  },
  methods: {
    fetchContentData() {

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