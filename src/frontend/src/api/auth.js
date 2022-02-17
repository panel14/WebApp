import store from '@/store'

export default function (instance) {
    return {
        signIn(payload) {
            instance.defaults.headers.common['Authorization'] = `Bearer ${store.getters.getToken}`
            return instance.post('/log/auth', payload)
        },
        signUp(payload) {
            return instance.post('/log/register', payload)
        },
        sendPoint(payload) {
            instance.defaults.headers.common['Authorization'] = `Bearer ${store.getters.getToken}`
            return instance.post('/point/reg', payload)
        },
        getPoints() {
            instance.defaults.headers.common['Authorization'] = `Bearer ${store.getters.getToken}`
            return instance.get('/point/get')
        }
    }
}