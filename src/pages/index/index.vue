<template>
    <view class="content">
      <image class="logo" src="/static/logo.png"></image>
      <view class="text-area">
        <text class="title">{{ title }}</text>
      </view>
      <view class="button-area">
        <button @click="startLearning" class="primary-btn">开始学习</button>
        <button @click="goToProfile" class="secondary-btn">个人资料</button>
      </view>
    </view>
  </template>
  
  <script>
  import { mapState } from 'vuex'
  
  export default {
    data() {
      return {
        title: '欢迎来到您的语言学习之旅'
      }
    },
    computed: {
      ...mapState(['user'])
    },
    onLoad() {
      console.log('首页加载')
      if (!this.user) {
        this.checkLoginStatus()
      }
    },
    methods: {
      startLearning() {
        uni.navigateTo({
          url: '/pages/practice/practice'
        })
      },
      goToProfile() {
        uni.navigateTo({
          url: '/pages/profile/profile'
        })
      },
      async checkLoginStatus() {
        try {
          const token = uni.getStorageSync('token')
          if (token) {
            await this.$store.dispatch('fetchUserProfile')
          }
        } catch (error) {
          console.error('检查登录状态失败:', error)
        }
      }
    }
  }
  </script>
  
  <style>
  .content {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 20px;
  }
  
  .logo {
    height: 200rpx;
    width: 200rpx;
    margin: 50rpx auto;
  }
  
  .text-area {
    display: flex;
    justify-content: center;
    margin-bottom: 50rpx;
  }
  
  .title {
    font-size: 36rpx;
    color: #333;
    text-align: center;
  }
  
  .button-area {
    display: flex;
    flex-direction: column;
    gap: 20rpx;
    width: 80%;
    margin-top: 30rpx;
  }
  
  .primary-btn, .secondary-btn {
    width: 100%;
    padding: 20rpx;
    border-radius: 10rpx;
    font-size: 32rpx;
  }
  
  .primary-btn {
    background-color: #007AFF;
    color: #FFFFFF;
  }
  
  .secondary-btn {
    background-color: #F0F0F0;
    color: #333;
  }
  </style>
  