import Vue from 'vue'
import Vuex from 'vuex'
import createPersisteState from 'vuex-persistedstate'

import auth from './modules/auth'

Vue.use(Vuex)

const dataState = createPersisteState({
    paths: ['auth']
})

export default new Vuex.Store({

    modules: {
        auth
    },

    plugins: [dataState]
})