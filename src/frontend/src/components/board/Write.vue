<template>
  <div class="mb-7 mt-5">
      <h1>글쓰기</h1>
      <div>
        <v-text-field
            placeholder="제목"
            v-model="title"
            dense
            class="mx-auto"
            style="width: 59%"
        ></v-text-field>
        <EditorTiptap v-model="content"/>
        <v-btn @click="write" dark class="mr-2">작성</v-btn>
        <v-btn @click="cancel" outlined>취소</v-btn>
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