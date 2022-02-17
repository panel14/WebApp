import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import instance from "@/api/instance";

export default createStore({
  plugins: [ createPersistedState() ],
  state: {
    status: '',
    token: '',
    user: {}
  },
  mutations: {
    SET_USER(state, payload) {
      state.status = 'success'
      state.token = payload.token
      state.user = payload.user
    },
    DELETE_USER(state) {
      state.status = ''
      state.token = ''
    },
    ERROR_AUTH(state) {
      state.status = 'failed'
    }
  },
  actions: {
    setUser({ commit }, payload) {
      try {
        const token = payload.token
        localStorage.setItem('token', token)
        commit('SET_USER', payload)
      } catch (error) {
        localStorage.removeItem('token')
        commit('ERROR_AUTH')
      }
    },
    deleteUser({ commit }) {
      commit('DELETE_USER')
      localStorage.removeItem('token')
      delete instance.defaults.headers.common['Authorization']
    }
  },
  getters: {
    isLoggedIn: state => {
      return state.token !== ''
    },
    authStatus: state => {
      return state.status
    },
    getUserName: state => {
      return state.user.login
    },
    getToken: state => {
      return state.token
    }
  }
})
