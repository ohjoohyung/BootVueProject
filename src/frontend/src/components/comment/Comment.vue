<template>
  <div>
    <span>작성자 : {{commentObj.userName}}</span>&nbsp;
    <span v-if="!modifyCheck">내용 : {{commentObj.content}}</span>&nbsp;
    <span v-else><input type="text" v-model="commentObj.content"></span>
    <span>작성일 : {{commentObj.modifyDate}}</span>
    <button v-if="getUserName() === commentObj.userName" @click="comment_modify(modifyCheck)">수정</button>
    <button v-if="getUserName() === commentObj.userName" @click="comment_delete(commentObj.id)">삭제</button>
  </div>

</template>

<script>
import axios from "axios";

export default {
  props: {
    commentObj: Object,
    fetchCommentData: Function
  },
  data() {
    return{
      modifyCheck: false
    }
  },
  methods: {
    getUserName() {
      return this.$store.getters.getUserName
    },
    comment_modify(modifyCheck) {
      if(modifyCheck) {
        if(this.$store.getters.getUserName != null && this.$store.getters.getUserName != '') {
          if(this.commentObj.content.length > 0) {
            axios.post('/api/comment/modify/' + this.$route.params.id,
              this.commentObj
            ).then((res) => {
              if(res.data.result != 1) {
                window.alert(res.data.msg)
              }
              this.fetchCommentData()
            }).catch((err) => {
              window.alert(err)
            })
          }else {
            window.alert("내용을 입력해주세요.")
          }
        }else {
          window.alert("작성자만 댓글을 수정할 수 있습니다.")
        }
      }
      this.modifyCheck = !modifyCheck


    },
    comment_delete(commentNo) {
      if(window.confirm('댓글을 삭제하시겠습니까?')) {
        axios.get('/api/comment/delete/'+this.$route.params.id+'/'+commentNo)
            .then((res) => {
              window.alert(res.data.msg)
              this.fetchCommentData();
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