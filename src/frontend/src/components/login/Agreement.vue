<template>
 <div>
 </div>
</template>

<script>
export default {
  name: "Agreement.vue",
  data() {
    return {
      access_token: ''
    }
  },
  created() {
    this.access_token=this.$route.query.token;
    this.$store.dispatch("NAVERLOGIN", this.access_token)
    .then(() => this.$router.push('/'))
  }
}
</script>

<style scoped>

</style>