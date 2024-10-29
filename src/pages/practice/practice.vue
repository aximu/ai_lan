<template>
    <view class="practice-container">
      <text class="practice-title">练习您的技能</text>
      <view class="practice-options">
        <button @click="startPractice('pronunciation')" class="practice-btn">发音练习</button>
        <button @click="startPractice('conversation')" class="practice-btn">对话练习</button>
        <button @click="startPractice('roleplay')" class="practice-btn">角色扮演</button>
      </view>
    </view>
  </template>
  
  <script>
  import { mapActions } from 'vuex'
  
  export default {
    methods: {
      ...mapActions(['fetchPracticeSession']),
      async startPractice(type) {
        console.log(`开始 ${type} 练习`)
        try {
          const session = await this.fetchPracticeSession(type)
          uni.navigateTo({
            url: `/pages/practice/${type}?sessionId=${session.id}`
          })
        } catch (error) {
          console.error('获取练习会话失败:', error)
          uni.showToast({
            title: '无法开始练习，请稍后再试',
            icon: 'none'
          })
        }
      }
    }
  }
  </script>
  
  <style>
  .practice-container {
    padding: 20px;
  }
  
  .practice-title {
    font-size: 36rpx;
    margin-bottom: 40rpx;
    text-align: center;
    color: #333;
  }
  
  .practice-options {
    display: flex;
    flex-direction: column;
    gap: 20rpx;
  }
  
  .practice-btn {
    background-color: #4CAF50;
    color: white;
    padding: 20rpx;
    border: none;
    border-radius: 10rpx;
    font-size: 32rpx;
  }
  </style>
  