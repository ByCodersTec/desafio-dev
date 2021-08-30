import MovimentacaoFinanceira from '../pages/auth/MovimentacaoFinanceira.vue';
import Login from '../pages/Login.vue';
import store from '../store/index'

const routes = [
    {
        path: '/',
        component: Login,
        name: 'login'
        
    },
    {
        beforeEnter: (to, from, next) => {
            let loged = store.getters['auth/getIsLoged']
            if (loged) {
                next()
            }else {
                alert('Permissão negada')
                next('/')
            }
            
        },
        path: '/auth/movimentacao-financeira',
        component: MovimentacaoFinanceira,
        name: 'movimentacao_financeira'
    }
]

export default routes