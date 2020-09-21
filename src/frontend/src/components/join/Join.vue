<template>
  <div style="width: 20%" class="mx-auto">
    <h1>회원가입</h1>
    <ValidationObserver ref="observer">
        <ValidationProvider v-slot="{errors}" name="E-mail" rules="required|email">
          <v-text-field
              v-model="email"
              :error-messages="errors"
              label="E-mail"
              required
          ></v-text-field>
        </ValidationProvider>
        <ValidationProvider v-slot="{errors}" name="Password" rules="required|password">
          <v-text-field
              v-model="password"
              :type="'password'"
              :error-messages="errors"
              label="Password"
              required
          ></v-text-field>
        </ValidationProvider>
      <ValidationProvider v-slot="{errors}" name="Password Confirm" rules="required|confirm:@Password">
        <v-text-field
            v-model="confirmation"
            :type="'password'"
            :error-messages="errors"
            label="Password Confirmation"
            required
        ></v-text-field>
      </ValidationProvider>
        <ValidationProvider v-slot="{errors}" name="Name" rules="required|max:10|min:2">
          <v-text-field
              v-model="name"
              :error-messages="errors"
              :counter="10"
              label="Name"
              required
          ></v-text-field>
        </ValidationProvider>
        <v-btn @click="join">가입</v-btn>
    </ValidationObserver>
  </div>
</template>

<script>
import axios from 'axios'
import { required, email, max, min } from 'vee-validate/dist/rules'
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

extend('max', {
  ...max,
  message: '{_field_}은 {length}자 이하만 가능합니다.',
})

extend('min', {
  ...min,
  message: '{_field_}은 {length}자 이상만 가능합니다.',
})

extend('confirm', {
  params: ['target'],
  validate(value, {target}) {
    return value === target
  },
  message: 'Password가 일치하지 않습니다.',
})

extend('password', {
  validate: value => {
    return /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(value)
  },
  message: '{_field_}는 영대소문자, 숫자, 특수기호가 포함된 8자 이상만 가능합니다.'
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
      name : '',
      confirmation: ''
    }
  },
  methods : {
    join() {
      this.$refs.observer.validate()
        .then((res) => {
          if(res) {
            const that = this
              axios.post('/api/join', {
                email : this.email,
                password : this.password,
                name : this.name
              }).then(function(res) {
                console.log(res)
                window.alert("회원가입이 완료되었습니다.")
                that.$router.push({path: '/login'})
              }).catch(function (err) {
                window.alert(err)
              })
          }
        })
        .catch((err) => {
          console.log(err)
        })


    }
  }
}
</script>

<style scoped>

</style>