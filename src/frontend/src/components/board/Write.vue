<template>
  <div>
      <h1>글쓰기</h1>
      <div>
        <v-text-field
            placeholder="제목"
            v-model="title"
            outlined
            dense
            class="mx-auto"
            style="width: 60%"
        ></v-text-field>
        <!--      <textarea placeholder="내용" v-model="content"></textarea>-->
        <EditorTiptap v-model="content"/>
        <v-btn @click="write">작성</v-btn>
        <v-btn @click="cancel">취소</v-btn>
      </div>

  </div>
</template>

<script>
import axios from 'axios';
import EditorTiptap from "@/components/board/EditorTiptap";
export default {
  components: {
    EditorTiptap
  },
  data() {
    return {
      title: '',
      content: ''
    }
  },
  methods: {
   write() {
     if(this.title.length > 0 && this.content.length > 0) {
       axios.post('/api/board/write', {
         userName: this.$store.getters.getUserName,
         title: this.title,
         content: this.content
       }).then((res) => {
         window.alert(res.data.msg)
         if(res.data.result === 1) {
           this.$router.push('/')
         }
         else {
           this.$router.replace('/write')
         }
       }).catch((err) => {
         window.alert(err)
       })

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