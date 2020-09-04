<template>
  <div>
    <h1>로그인</h1>
    <form @submit.prevent="onSubmit(email, password)">
    <p>ID : <input type="text" v-model="email"></p>
    <p>PWD : <input type="password" v-model="password"></p>
    <input type="submit" value="Login">
    </form>
    <p><i>{{msg}}</i></p>
  </div>
</template>

<script>
/*import axios from 'axios';*/
export default {
  data() {
    return {
      email : '',
      password : '',
      msg : ''
    }
  },
  methods : {
    onSubmit(email, password) {
      //LOGIN action 실행
      this.$store
        .dispatch("LOGIN", {email, password})
        .then(() => this.redirect())
        .catch(({message}) => (this.msg = message))
    },
    redirect() {
     /* const {search} = window.location
      const tokens = search.replace(/^\?/, "").split("&")
      const {returnPath} = tokens.reduce((qs, tkn) => {
        const pair = tkn.split("=")
        qs[pair[0]] = decodeURIComponent(pair[1])
        return qs
      }, {})
      window.console.log(window.location.replace(/^\?/, "").split("&"))
      window.console.log(tokens)
      window.console.log(returnPath)*/

  /*    const that = this*/
      //리다이렉트 처리
      this.$router.push("/")
    }



    /*login() {
      if(this.email && this.password) {
        const that = this
        axios.post('/api/login', {
          email : this.email,
          password : this.password
        }).then(function (res) {
          window.alert(res)
          that.$router.push({path: '/'})
        }).catch(function (err) {
          window.alert(err)
        })
      }else {
        window.alert("모든 항목을 입력해주세요.")
      }

    }*/
  }
}
</script>

<style scoped>

</style>