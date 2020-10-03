<template>
  <v-app>
  <div id="app">



      <Header/>
      <router-view/>
      <Footer/>






  </div>
  </v-app>
</template>

<script>

import Header from "@/components/main/header/Header";
import Footer from "@/components/main/footer/Footer";

export default {
  name: 'App',
  components: {
    'Footer' : Footer,
    'Header' : Header
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
}
</style>