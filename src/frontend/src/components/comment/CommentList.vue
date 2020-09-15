<template>
  <div>
    <CommentWrite :fetchCommentData="fetchCommentData"/>
    <p>댓글영역</p>

    <div v-for="(item) in commentList" :key="item.id">
      <Comment :commentObj="item" :fetchCommentData="fetchCommentData"/>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import CommentWrite from "@/components/comment/CommentWrite";
import Comment from "@/components/comment/Comment";
export default {
  data() {
    return {
      commentList : ''
      }
    },
  components: {
    CommentWrite,
    Comment
  },
  created() {
    this.fetchCommentData()
  },
  methods : {

    fetchCommentData() {
      
      axios.get('/api/comment/list/' + this.$route.params.id)
      .then((res) => {
        this.commentList = res.data.list

        console.log(this.commentList)

      }).catch((err) => {
        window.alert(err)
      })
    }



  }

}
</script>

<style scoped>

</style>