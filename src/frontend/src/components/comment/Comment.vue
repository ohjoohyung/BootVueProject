<template>
  <div>
    <v-card class="ma-3" style="border: none" outlined min-height="20">
      <v-card-title class="text-left">

        <span class="text-body-1 mr-2">{{commentObj.userName}}</span>
        <span class="text-body-2 text--secondary mr-2">|</span>
        <span class="text-body-2 text--secondary mr-2">{{commentObj.modifyDate}}</span>
        <v-btn fab text x-small v-if="getUserName() === commentObj.userName" @click="comment_modify(modifyCheck)">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
        <v-btn fab text x-small v-if="getUserName() === commentObj.userName" @click="comment_delete(commentObj.id)">
          <v-icon>mdi-delete</v-icon>
        </v-btn>
      </v-card-title>
      <v-card-text class="text-left">
        <span v-if="!modifyCheck">{{commentObj.content}}</span>&nbsp;
        <span v-else>
          <v-text-field
              v-model="commentObj.content"
              dense
              outlined
          ></v-text-field>
        </span>
      </v-card-text>




    </v-card>

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