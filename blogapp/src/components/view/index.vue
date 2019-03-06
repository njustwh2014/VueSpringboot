<template>
  <el-container>
    <el-main>
      <article-scroll-page></article-scroll-page>
    </el-main>
    <el-aside >
      <cardme class="me-area"></cardme>
    </el-aside>
  </el-container>
</template>
<script>
import cardme from '@/components/view/card/cardme.vue'
import ArticleScrollPage from '@/components/view/article/ArticleScrollPage.vue'
export default {
  name:'index',
  components:{
    'cardme':cardme,
    ArticleScrollPage,
  }
}
</script>
<style scoped>
 .el-container {
    width: 100px;
  }

  .el-aside {
    margin-left: 20px;
    width: 30%;
  }

  .el-main {
    padding: 0px;
    line-height: 16px;
  }

  .el-card {
    border-radius: 0;
  }

  .el-card:not(:first-child) {
    margin-top: 20px;
  }
</style>


