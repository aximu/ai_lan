import axios from 'axios'

const API_BASE_URL = 'https://api.yourdomain.com'

const api = axios.create({
  baseURL: API_BASE_URL,
  timeout: 5000,
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  }
})

api.interceptors.request.use(
  (config) => {
    const token = uni.getStorageSync('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  (error) => Promise.reject(error)
)

api.interceptors.response.use(
  (response) => response.data,
  (error) => {
    if (error.response && error.response.status === 401) {
      uni.clearStorageSync()
      uni.reLaunch({ url: '/pages/index/index' })
    }
    return Promise.reject(error)
  }
)

export default {
  login: (credentials) => api.post('/auth/login', credentials),
  logout: () => api.post('/auth/logout'),
  register: (userData) => api.post('/auth/register', userData),
  getUserProfile: () => api.get('/user/profile'),
  getPracticeSession: (type) => api.get(`/practice/${type}`),
  submitPracticeResult: (type, result) => api.post(`/practice/${type}`, result),
}
