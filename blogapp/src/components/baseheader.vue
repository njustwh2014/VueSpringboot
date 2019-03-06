<template>
 <el-header class="me-area">
    <el-row class="me-header">
      <el-col :span="4" class="me-header-left" align="center" >
        <router-link to="/" class="me-title">
          <img  src="../assets/logo.png"/>
        </router-link>
      </el-col>

      <el-col v-if="!simple" :span="17">
        <el-menu :router=true menu-trigger="click" active-text-color="#5FB878" :default-active="activeIndex"
                 mode="horizontal">
          <el-menu-item index="/">首页</el-menu-item>
          <el-menu-item index="/category/all">文章分类</el-menu-item>
          <el-menu-item index="/tag/all">标签</el-menu-item>
          <el-menu-item index="/archives">文章归档</el-menu-item>
          <el-menu-item index="/log">日志</el-menu-item>
          <el-menu-item index="/messageBoard">留言板</el-menu-item>
          <el-col :span="2" :offset="4">
            <el-menu-item index="/write"><i class="el-icon-edit"></i>写文章</el-menu-item>
          </el-col>
        </el-menu>
      </el-col>
      <template v-else :span="17">
        <slot></slot>
      </template>
      <el-col :span="3">
        <el-menu :router=true menu-trigger="click" mode="horizontal" active-text-color="#5FB878">
          <!-- <template v-if="!user.login">  -->
          <template v-if= "!user.login">
            <el-menu-item index="/login">
              <el-button type="text">登录</el-button>
            </el-menu-item>
            <el-menu-item index="/regist">
              <el-button type="text">注册</el-button>
            </el-menu-item>
          </template>
          <template v-else>
            <el-submenu index>
              <template slot="title">
                <img class="me-header-picture" :src="user.avatar"/>
              </template>
              <el-menu-item index @click="logout"><i class="el-icon-back"></i>退出</el-menu-item>
            </el-submenu>
          </template>
        </el-menu>
      </el-col>
    </el-row>
  </el-header> 
</template>

<script>
  export default {
    name: 'baseheader',
    props: {
      activeIndex: String,
      simple: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        activeIndex1: '1',
        activeIndex2: '1'
      }
    },
    computed: {
      user() {
        let login = this.$store.state.email.length != 0
        let avatar = this.$store.state.avatar
        return {
          login, avatar
        }
      }
    },
    methods: {
       handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      logout() {
        let that = this
        this.$store.dispatch('logout').then(() => {
          this.$router.push({path: '/'})
          }).catch((error) => {
          if (error !== 'error') {
            that.$message({message: error, type: 'error', showClose: true});
            }
          })
      }
    }
  }
</script>

<style>
   .el-header {
    position: fixed;
    z-index: 1024;
    min-width: 100%;
    box-shadow: 0 2px 3px hsla(0, 0%, 7%, .1), 0 0 0 1px hsla(0, 0%, 7%, .1);
  }

  .me-title {
    margin-top: 10px;
    font-size: 24px;
  }

  .me-header-left {
    margin-top: 5px;
  }

  .me-title img {
    max-height: 5rem;
    max-width: 100%;
  }

  .me-header-picture {
    width: 36px;
    height: 36px;
    border: 1px solid #ddd;
    border-radius: 50%;
    vertical-align: middle;
    background-color: #5fb878;
  }



</style>
