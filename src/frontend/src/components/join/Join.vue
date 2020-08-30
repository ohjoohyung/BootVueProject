<template>
  <div>
    <h1>회원가입</h1>
    <p>ID : <input type="text" v-model="userId"></p>
    <p>PWD : <input type="password" v-model="userPwd"></p>
    <p>이름 : <input type="text" v-model="userName"></p>
    <button @click="join">가입</button>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      userId : '',
      userPwd : '',
      userName : ''
    }
  },
  methods : {
    join() {
      if(this.userId && this.userPwd && this.userName) {
        const that = this
        axios.post('/api/join', {
          userId : this.userId,
          userPwd : this.userPwd,
          userName : this.userName
        }).then(function (res) {
          window.alert(res)
          that.$router.push({path: '/'})
        }).catch(function (err) {
          window.alert(err)
        })
      }else {
        window.alert("모든 항목을 입력해주세요.")
      }

    }
  }
}
</script>

<style scoped>

</style>