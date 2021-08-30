
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import routes from './routes'

const router = new VueRouter({
    mode: 'history',
    base: '/',
    routes
})

export default router