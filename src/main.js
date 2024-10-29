import App from './App'
import { createSSRApp } from 'vue'
import { createStore } from 'vuex'

// Create a new store instance
const store = createStore({
  state() {
    return {
      user: null,
      learningProgress: {}
    }
  },
  mutations: {
    setUser(state, user) {
      state.user = user
    },
    updateLearningProgress(state, progress) {
      state.learningProgress = { ...state.learningProgress, ...progress }
    }
  }
})

export function createApp() {
  const app = createSSRApp(App)
  
  // Use Vuex store
  app.use(store)
  
  return {
    app
  }
}
