<template>
  <div id="app">
    <Header/>
    <router-view/>
    <Footer/>
  </div>
</template>

<script>

import request from "request"
import Header from "@/components/main/header/Header";
import Footer from "@/components/main/footer/Footer";

export default {
  name: 'App',
  components: {
    Footer, 'Header' : Header
  },

  //포트9090이랑 연결 test (나중에 지울것)
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
