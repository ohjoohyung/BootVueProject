<template>
  <div v-if="isAuthenticated" class="ma-3" style="height: 300px">

    <v-textarea
        outlined
        clearable
        clear-icon="mdi-close"
        placeholder="댓글을 입력하세요."
        v-model="content"
        rows="4"
    />
    <div class="text-right">
      <v-btn outlined @click="comment_write">댓글 등록</v-btn>
    </div>



  </div>
</template>

<script>
import axios from "axios";
import store from "@/store";

export default {
  props: {
    fetchCommentData: Function
  },
  data(){
    return{
      content: ''
    }
  },
  computed: {
    isAuthenticated() {
      return store.getters.isAuthenticated
    },
  },
  methods: {
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
            this.content = ''
            this.fetchCommentData()
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
  }
}
</script>

<style scoped>

</style>