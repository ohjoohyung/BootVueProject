<template>
  <div style="width: 20%" class="mx-auto">
    <h1>로그인</h1>
    <ValidationObserver ref="observer">
      <form @submit.prevent="onSubmit(email, password)">
        <ValidationProvider v-slot="{errors}" name="email" rules="required|email">
          <v-text-field
              v-model="email"
              :error-messages="errors"
              label="E-mail"
              required
          ></v-text-field>
        </ValidationProvider>
        <ValidationProvider>
          <v-text-field
              v-model="password"
              :type="'password'"
              label="Password"
              required
          ></v-text-field>
        </ValidationProvider>
        <v-btn @click="submit">Login</v-btn>
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

    <p><i>{{msg}}</i></p>
  </div>
</template>

<script>
import { required, email} from 'vee-validate/dist/rules'
import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'
setInteractionMode('eager')

extend('required', {
  ...required,
  message: '{_field_}는 필수항목입니다.'
})

extend('email', {
  ...email,
  message: '올바른 E-mail을 입력해주세요.'
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
      emailRules: [
        v => !!v || 'E-mail을 적어주세요.',
        v => /.+@.+\..+/.test(v) || '올바른 E-mail을 적어주세요.',
      ],
    }
  },
  methods : {
    onSubmit(email, password) {
      //LOGIN action 실행
      if(this.email && this.password) {
        this.$store
            .dispatch("LOGIN", {email, password})
            .then(() => this.redirect())
            .catch(({message}) => (this.msg = message))
      }else {
        window.alert("모든 항목을 입력해주세요.")
      }

    },
    submit () {
      this.$refs.observer.validate()
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




  }
}
</script>

<style scoped>

</style>