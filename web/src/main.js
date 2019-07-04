import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import Vuetify from 'vuetify'

import 'vuetify/dist/vuetify.min.css'

Vue.config.productionTip = false
Vue.use(Vuetify)

Vue.use(VueRouter)
const router = new VueRouter({
    mode: 'history',
    base: __dirname
})

new Vue({
    router,
    render: h => h(App),
}).$mount('#healthcheck')
