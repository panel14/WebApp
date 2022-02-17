import { createApp } from 'vue'
import App from './App.vue'
import AppPlugin from './plugins/api'
import router from './router'
import store from './store'
import VueGoodTable from 'vue-good-table'

const app = createApp(App).use(store).use(router).use(AppPlugin).use(VueGoodTable)

app.mount('#app')
