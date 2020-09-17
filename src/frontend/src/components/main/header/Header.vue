<template>
  <div>

    <v-toolbar>
      <v-spacer></v-spacer>
      <router-link v-if="isAuthenticated == null" to="/join" class="ma-2">회원가입</router-link>
      <router-link v-if="isAuthenticated == null" to="/login">로그인</router-link>
      <a href="" v-if="isAuthenticated != null" @click.prevent="logout" class="ma-2">로그아웃</a>
      <router-link v-if="isAuthenticated != null" to="/me">Me</router-link>
    </v-toolbar>
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
      store.dispatch("LOGOUT")
          .then(() => this.$router.push("/")
          .catch(err => {
            if(err.name != "NavigationDuplicated") {
              throw err;
            }
          })
      )
    }
  }
}
</script>

<style>

</style>