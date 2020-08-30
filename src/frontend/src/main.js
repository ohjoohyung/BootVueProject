import Vue from 'vue'
import App from './App'
import { router } from './router/router'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router,
  components: { App }
}).$mount('#app')
