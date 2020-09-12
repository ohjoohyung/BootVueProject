<template>
  <div>
    댓글영역
    <div v-for="(item) in commentList" :key="item.id">
      <span>작성자 : {{item.userName}}</span>&nbsp;
      <span>내용 : {{item.content}}</span>&nbsp;
      <span>작성일 : {{item.modifyDate}}</span>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  props: ['boardNo'],
  data() {
    return {
      commentList : '',
      propBoardNo: this.boardNo
    }
  },
  created() {
    this.fetchData()
  },
  methods : {
    fetchData() {
      window.alert(this.propBoardNo)
      axios.get('/api/comment/list/' + this.propBoardNo)
      .then((res) => {
        this.commentList = res.data.list
      }).catch((err) => {
        window.alert(err)
      })
    }
  }

}
</script>

<style scoped>

</style>