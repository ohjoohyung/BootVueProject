<template>
  <div style="width: 20%; min-height: 500px" class="mx-auto mt-6">
    <h1>로그인</h1>
    <ValidationObserver ref="observer">
      <form @submit.prevent="onSubmit(email, password)">
        <ValidationProvider v-slot="{errors}" name="E-mail" rules="required">
          <v-text-field
              v-model="email"
              :error-messages="errors"
              label="E-mail"
          ></v-text-field>
        </ValidationProvider>
        <ValidationProvider v-slot="{errors}" name="Password" rules="required">
          <v-text-field
              v-model="password"
              :type="'password'"
              :error-messages="errors"
              label="Password"
          ></v-text-field>
        </ValidationProvider>
        <p style="color: red">{{msg}}</p>
        <v-btn type="submit" class="mr-2" dark>로그인</v-btn>
        <v-btn outlined @click="goBack">취소</v-btn>
      </form>
    </ValidationObserver>
<!--    <v-form @submit.prevent="onSubmit(email, password)">-->
<!--      <v-text-field-->
<!--        v-model="email"-->
<!--        :rules="emailRules"-->
<!--        label="E-mail"-->
<!--        required-->
<!--      ></v-text-field>-->
<!--      <v-text-field-->
<!--        v-model="password"-->
<!--        :type="'password'"-->
<!--        label="Password"-->
<!--      ></v-text-field>-->
<!--      <v-btn type="submit">Login</v-btn>-->
<!--    </v-form>-->


  </div>
</template>

<script>
import { required} from 'vee-validate/dist/rules'
import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'
setInteractionMode('eager')

extend('required', {
  ...required,
  message: '{_field_}는 필수항목입니다.'
})


export default {
  components: {
    ValidationProvider,
    ValidationObserver
  },
  data() {
    return {
      email : '',
      password : '',
      msg : '',
    }
  },
  methods : {
    onSubmit(email, password) {
        this.$refs.observer.validate()
          //LOGIN action 실행
          .then((res) => {
            if (res) {
              this.$store
                  .dispatch("LOGIN", {email, password})
                  .then((res) =>
                      res == undefined ? this.redirect() : this.msg = res)
                  .catch(({message}) => (this.msg = message))
            }
          })
          .catch((err) => {
           console.log(err)
         })
    },

    redirect() {

      this.$router.push("/")
    },
    goBack() {
      this.$router.replace('/')
    }




  }
}
</script>

<style scoped>

</style>