<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <Header/>
    <router-view/>
  </div>
</template>

<script>

import request from "request"
import Header from "@/components/main/header/Header";

export default {
  name: 'App',
  components: {'Header' : Header
  },
  mounted() {
    request('http://localhost:8081/api/hello', function (error, response, body) {
      window.console.log('error: ', error);
      window.console.log('statusCode: ', response && response.statusCode);
      window.console.log('body: ', body);
    });
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
