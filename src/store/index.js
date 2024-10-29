import { createStore } from 'vuex'
import api from '@/services/api'

const store = createStore({
  state: {
    user: null,
    learningProgress: 0,
    currentPracticeSession: null
  },
  mutations: {
    setUser(state, user) {
      state.user = user
    },
    setLearningProgress(state, progress) {
      state.learningProgress = progress
    },
    setCurrentPracticeSession(state, session) {
      state.currentPracticeSession = session
    }
  },
  actions: {
    async loginUser({ commit }, credentials) {
      const user = await api.login(credentials)
      commit('setUser', user)
      uni.setStorageSync('token', user.token)
    },
    async logoutUser({ commit }) {
      await api.logout()
      commit('setUser', null)
      uni.removeStorageSync('token')
    },
    async fetchUserProfile({ commit }) {
      const user = await api.getUserProfile()
      commit('setUser', user)
    },
    async fetchPracticeSession({ commit }, type) {
      const session = await api.getPracticeSession(type)
      commit('setCurrentPracticeSession', session)
      return session
    },
    async submitPracticeResult({ commit }, { type, result }) {
      const updatedProgress = await api.submitPracticeResult(type, result)
      commit('setLearningProgress', updatedProgress)
    }
  }
})

export default store
