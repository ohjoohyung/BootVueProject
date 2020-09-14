<template>
  <div>
    <input type="text" placeholder="댓글을 입력하세요." v-model="content">
    <button @click="comment_write">댓글 등록</button>
    <button type="reset">취소</button>
    <p>댓글영역</p>

    <div v-for="(item) in commentList" :key="item.id">
      <span>작성자 : {{item.userName}}</span>&nbsp;
      <span v-if="!modifyCheck">내용 : {{item.content}}</span>&nbsp;
      <span v-else><input v-model="item.content"></span>
      <span>작성일 : {{item.modifyDate}}</span>
      <button v-if="getUserName() === item.userName" @click="modifyCheck = !modifyCheck">수정</button>
      <button v-if="getUserName() === item.userName" @click="comment_delete(item.id)">삭제</button>

    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      content: '',
      commentList : '',
      modifyCheck : false
      }
    },

  created() {
    this.fetchCommentData()
  },
  methods : {
    getUserName() {
      return this.$store.getters.getUserName
    },
    fetchCommentData() {
      
      axios.get('/api/comment/list/' + this.$route.params.id)
      .then((res) => {
        this.commentList = res.data.list

        console.log(this.commentList)

      }).catch((err) => {
        window.alert(err)
      })
    },
    comment_write() {
      if(this.$store.getters.getUserName != null && this.$store.getters.getUserName != '') {
        if(this.content.length > 0) {
          axios.post('/api/comment/write/' + this.$route.params.id, {
            userName: this.$store.getters.getUserName,
            content: this.content
          }).then((res) => {
            if(res.data.result != 1) {
              window.alert(res.data.msg)
            }
            this.commentList = res.data.list


          }).catch((err) => {
            window.alert(err)
          })
        }else {
          window.alert("내용을 입력해주세요.")
        }
      }else {
        window.alert("회원만 댓글을 남길 수 있습니다.")
      }

    },

    comment_delete(commentNo) {
      if(window.confirm('댓글을 삭제하시겠습니까?')) {
        axios.get('/api/comment/delete/'+this.$route.params.id+'/'+commentNo)
            .then((res) => {
              window.alert(res.data.msg)
              this.commentList = res.data.list
            }).catch((err) => {
          window.alert(err)
          window.console.log(err)
        })
      }

    }
  }

}
</script>

<style scoped>

</style>