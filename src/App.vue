<script>
export default {
  onLaunch: function() {
    console.log('App Launch')
    // Initialize app-wide services or fetch initial data here
  },
  onShow: function() {
    console.log('App Show')
    // Handle app coming to foreground
  },
  onHide: function() {
    console.log('App Hide')
    // Handle app going to background
  }
}
</script>

<style>
/* Global styles */
page {
  font-family: -apple-system, BlinkMacSystemFont, 'Helvetica Neue', Helvetica,
    Segoe UI, Arial, Roboto, 'PingFang SC', 'miui', 'Hiragino Sans GB', 'Microsoft Yahei',
    sans-serif;
}
</style>
