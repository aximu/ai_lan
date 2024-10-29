<template>
    <view class="profile-container">
      <text class="profile-title">用户资料</text>
      <view v-if="user" class="user-info">
        <text class="welcome-text">欢迎, {{ user.name }}</text>
        <text class="progress-text">学习进度: {{ learningProgress }}%</text>
        <button @click="logout" class="logout-btn">登出</button>
      </view>
      <view v-else class="login-section">
        <input v-model="username" placeholder="用户名" class="input-field" />
        <input v-model="password" type="password" placeholder="密码" class="input-field" />
        <button @click="login" class="login-btn">登录</button>
      </view>
    </view>
  </template>
  
  <script>
  import { mapState, mapActions } from 'vuex'
  
  export default {
    data() {
      return {
        username: '',
        password: ''
      }
    },
    computed: {
      ...mapState(['user', 'learningProgress'])
    },
    methods: {
      ...mapActions(['loginUser', 'logoutUser']),
      async login() {
        try {
          await this.loginUser({ username: this.username, password: this.password })
          uni.showToast({ title: '登录成功', icon: 'success' })
        } catch (error) {
          uni.showToast({ title: '登录失败', icon: 'none' })
        }
      },
      async logout() {
        try {
          await this.logoutUser()
          uni.showToast({ title: '已登出', icon: 'success' })
        } catch (error) {
          uni.showToast({ title: '登出失败', icon: 'none' })
        }
      }
    }
  }
  </script>
  
  <style>
  .profile-container {
    padding: 20px;
  }
  
  .profile-title {
    font-size: 36rpx;
    margin-bottom: 40rpx;
    color: #333;
  }
  
  .user-info, .login-section {
    display: flex;
    flex-direction: column;
    gap: 20rpx;
  }
  
  .welcome-text, .progress-text {
    font-size: 32rpx;
    color: #333;
  }
  
  .input-field {
    border: 1px solid #ddd;
    padding: 10rpx;
    border-radius: 5rpx;
  }
  
  .login-btn, .logout-btn {
    background-color: #2196F3;
    color: white;
    padding: 20rpx;
    border: none;
    border-radius: 10rpx;
    font-size: 32rpx;
  }
  
  .logout-btn {
    background-color: #f44336;
  }
  </style>
  