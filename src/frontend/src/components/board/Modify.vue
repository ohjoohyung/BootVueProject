<template>
  <div>
    <h1>수정하기</h1>
    <div>
      <input type="text" placeholder="제목" v-model="board.title">
      <textarea placeholder="내용" v-model="board.content"></textarea>
      <button @click="modify">수정</button>
      <button @click="cancel">취소</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
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