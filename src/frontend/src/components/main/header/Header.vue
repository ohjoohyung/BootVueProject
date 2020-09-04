<template>
  <div>
        <router-link v-if="isAuthenticated == null" to="/join">회원가입</router-link>
        &nbsp;
        <router-link v-if="isAuthenticated == null" to="/login">로그인</router-link>
       &nbsp;
        <a href="" v-if="isAuthenticated != null" @click.prevent="logout">로그아웃</a>
        &nbsp;
        <router-link v-if="isAuthenticated != null" to="/me">Me</router-link>

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

<style>

</style>