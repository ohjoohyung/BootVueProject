import Vue from 'vue'
import App from './App'
import { router } from './router/router'
import store from "./store"
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false
Vue.prototype.$EventBus = new Vue();

new Vue({
  render: h => h(App),
  router,
  store,
  vuetify,
  components: { App }
}).$mount('#app')
