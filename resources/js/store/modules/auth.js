const auth = {
    namespaced: true,

    state: {
        token: '',
        isLoged: false
    },

    getters: {
        getToken: state => {
            return state.token
        },

        getIsLoged: state => {
            return state.isLoged
        }
    },

    actions: {
        auth({ commit }, payload) {
            commit('setAuth', payload)
        },

        isLoged({ commit }, payload) {
            commit('setIsLoged', payload)
        }
    },

    mutations: {
        setAuth(state, payload) {
            state.token = payload
        },

        setIsLoged(state, payload) {
            state.isLoged = payload
        }
    }
}

export default auth