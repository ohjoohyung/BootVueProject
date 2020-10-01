<template>
<!--  <div>-->
<!--    <h1>수정하기</h1>-->
<!--    <div>-->
<!--      <input type="text" placeholder="제목" v-model="board.title">-->
<!--      <textarea placeholder="내용" v-model="board.content"></textarea>-->
<!--      <button @click="modify">수정</button>-->
<!--      <button @click="cancel">취소</button>-->
<!--    </div>-->
<!--  </div>-->
  <div class="mb-7 mt-5">
    <h1>수정하기</h1>
    <div>
      <v-text-field
          placeholder="제목"
          v-model="board.title"
          dense
          class="mx-auto"
          style="width: 59%"
      ></v-text-field>
      <EditorTiptap v-model="board.content"/>
      <v-btn @click="modify" dark class="mr-2">수정</v-btn>
      <v-btn @click="cancel" outlined>취소</v-btn>
    </div>

  </div>
</template>

<script>
import axios from 'axios'

import EditorTiptap from "@/components/board/EditorTiptap";
export default {
  components: {
    EditorTiptap
  },
  data() {
    return {
      board: {
        title: '',
        content: ''
      }
    }
  },
  created() {
    this.fetchData()
  },
  watch: {
    '$route' : 'fetchData'
  },
  methods: {
    fetchData() {
      axios.get('/api/board/content/'+this.$route.params.id)
      .then((res) => {
        this.board = null
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
    modify() {
      if(this.board.title.length > 0 && this.board.content.length > 0) {
        if(window.confirm('게시글을 수정하시겠습니까?')) {
          axios.post('/api/board/modify', this.board
          ).then((res) => {
            window.alert(res.data.msg)
            if(res.data.result === 1) {
              this.$router.push('/content/'+this.$route.params.id)
            }
            else {
              this.$router.replace('/')
            }
          }).catch((err) => {
            window.alert(err)
          })
        }
      } else {
        window.alert('모든 내용을 작성하고 버튼을 눌러주세요.')
      }
    },
    cancel() {
      this.$router.replace('/')
    }
  }

}
</script>

<style scoped>

</style>