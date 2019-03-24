<template>
 <el-header class="me-area">
    <el-row class="me-header">
      <el-col :span="3" class="me-header-left" align="center" >
        <router-link to="/" class="me-title">
          <img  src="../assets/logo.png"/>
        </router-link>
      </el-col>

      <el-col v-if="!simple" :span="18">
        <el-menu :router=true menu-trigger="hover" active-text-color="#5FB878" :default-active="activeIndex"
                 mode="horizontal">
          <el-menu-item index="/">首页</el-menu-item>
          <el-submenu index="/category">
          <template slot="title">分类</template>
          <el-menu-item v-for="a in allcategory" :key=a.id :index="generateIndex(a.id)">{{a.categorydescription}}</el-menu-item>
        </el-submenu>
        <el-menu-item index="3" disabled>程序员步行街</el-menu-item>
        <el-menu-item index="4" disabled>待开发</el-menu-item>
        <el-menu-item index="/userinfo" >个人中心</el-menu-item>
        <el-menu-item index="/write"><i class="el-icon-edit"></i>写文章</el-menu-item>
          <!-- <el-menu-item index="/tag/all">标签</el-menu-item>
          <el-menu-item index="/archives">文章归档</el-menu-item>
          <el-menu-item index="/log">日志</el-menu-item>
          <el-menu-item index="/messageBoard">留言板</el-menu-item> -->
          <el-col :span="7" :offset="4">
            <div style="margin-top: 10px;">
              <el-input placeholder="请输入搜索内容" v-model="searchData" class="input-with-select" autofocus="true">
                <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
              </el-input>
            </div>
          </el-col>
        </el-menu>
      </el-col>
      <template v-else :span="18">
        <slot></slot>
      </template>
      <el-col :span="3">
        <el-menu :router=true menu-trigger="hover" mode="horizontal" active-text-color="#5FB878">
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
import {getAllCategorys} from '@/api/category'
  export default {
    name: 'baseheader',
    props: {
      activeIndex: String,
      simple: {
        type: Boolean,
        default: false
      }
    },
    created(){
      this.getCategorys();
    },
    data() {
      return {
        allcategory:[{id:1,categorydescription:"时政"}],
        searchData:"",
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
      },
      search(){
        let searchData=this.searchData;
        if(searchData!=""){
          this.searchData="";
          this.$router.push({path: `/search/${searchData}`});
        }else{
          this.$message("不要调戏人家啦！")
        }       
      },
      generateIndex(id){
        return "/articles/category/"+id;
      },
      getCategorys() {
        getAllCategorys().then(data => {
          this.allcategory = data.data.data;
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '文章分类加载失败', showClose: true})
          }
        })
      },
    },
    directives:{
      focus:{
        bind(el){
            // 在元素绑定时执行，只执行一次
            //还没有插入到dom树中，只在内存中
        },
        inserted(el){
            // 表示元素插入到dom中会执行，也执行一次
            el.focus();
        },
        updated(el){
            // 当组建更新的时候执行，科能会触发多次
            
        }
      }
    }
  }
</script>

<style scoped>
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
    background-color: #ffffff;
  }



</style>
