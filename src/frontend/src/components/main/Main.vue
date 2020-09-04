<template>
  <div>
    <p><router-link to="/join">회원가입</router-link></p>
    <p><router-link to="/login">로그인</router-link></p>
    <a href="" v-if="isAuthenticated != null" @click.prevent="logout">로그아웃</a>
    <p><router-link to="/me">Me</router-link></p>
  </div>
</template>

<script>
import store from "@/store"

export default {
  computed: {
    isAuthenticated() {
      return store.getters.isAuthenticated
    },
  },
  methods: {
    logout() {

      //로그아웃 mutations 실행 후 리다이렉트
      store.dispatch("LOGOUT").then(() => this.$router.push("/"))
    }
  }
}
</script>

<style scoped>

</style>