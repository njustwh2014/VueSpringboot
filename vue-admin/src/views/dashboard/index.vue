<template>
  <div class="dashboard-container">
    <github-corner style="position: absolute; top: 0px; border: 0; right: 0;" />
    <div class="dashboard-text">name:{{ name }}</div>
    <div class="dashboard-text">roles:<span v-for="role in roles" :key="role">{{ role }}</span></div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import GithubCorner from '@/components/GithubCorner'

export default {
  name: 'index',
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  },
  components:{
    GithubCorner
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
