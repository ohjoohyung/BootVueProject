<template>
  <div>
    <h1>회원가입</h1>
    <p>ID : <input type="text" v-model="email"></p>
    <p>PWD : <input type="password" v-model="password"></p>
    <p>이름 : <input type="text" v-model="name"></p>
    <button @click="join">가입</button>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      email : '',
      password : '',
      name : ''
    }
  },
  methods : {
    join() {
      if(this.email && this.password && this.name) {
        const that = this
        axios.post('/api/user/join', {
          email : this.email,
          password : this.password,
          name : this.name
        }).then(function (res) {
          window.alert(res)
          that.$router.push({path: '/login'})
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