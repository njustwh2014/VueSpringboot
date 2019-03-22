<template>
  <div class="app-container">

    <div class="filter-container">
      <!-- <el-input v-model="listQuery.title" :placeholder="title" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
       -->
      <el-input v-model="listQuery.title" placeholder="title" style="width: 200px;" class="filter-item" />
      
      <!-- <el-select v-model="listQuery.sort" style="width: 140px" class="filter-item" @change="handleFilter">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key" />
      </el-select> -->
      <!-- <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">{{ "搜索" }}</el-button> -->
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search">{{ "搜索" }}</el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column align="center" label="编号" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="昵称" min-width="150" align="center">
        <template slot-scope="scope">
          {{ scope.row.nickname }}
        </template>
      </el-table-column>
      
      <el-table-column label="账号" min-width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.account }}</span>
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="性别" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.gender | genderColorFilter">{{ scope.row.gender|genderFilter }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="毕业学校" width="90" align="center">
        <template slot-scope="scope">
          {{ scope.row.school }}
        </template>
      </el-table-column>

      <el-table-column label="贡献度" width="90" align="center">
        <template slot-scope="scope">
          {{ scope.row.contributiondegree }}
        </template>
      </el-table-column>

      <!-- <el-table-column class-name="status-col" label="Status" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column> -->
      <el-table-column align="center" prop="created_at" label="出生日期" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time"/>
          <span>{{ scope.row.birthdate|parseTime('{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="320" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" :disabled="true" @click="handleUpdate(scope.row)">{{ "edit" }}</el-button>
          <el-button v-if="scope.row.status!='published'" :disabled="true" size="mini" type="success" @click="handleModifyStatus(scope.row,'禁言')">
            {{ "publish" }}
          </el-button>
          <el-button v-if="scope.row.status!='draft'" :disabled="true" size="mini" @click="handleModifyStatus(scope.row,'draft')">
            {{ "draft" }}
          </el-button>
          <!-- <el-button v-if="scope.row.status!='deleted'" size="mini" type="danger" @click="handleModifyStatus(scope.row,'deleted')">
            {{ "delete" }}
          </el-button> -->
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">
            {{ "delete" }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNumber" :limit.sync="listQuery.pageSize" @pagination="fetchData" />
  </div>
</template>

<script>
import { getAllUser,deleteUser } from '@/api/user'
import { parseTime } from '@/utils'
import waves from '@/directive/waves' // Waves directive
import Pagination from '@/components/Pagination'
export default {
  directives: { waves },
  components:{Pagination},
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    genderFilter(gender){
      const genderMap={
        true:'男',
        false:'女'
      }
      return genderMap[gender]
    },
    genderColorFilter(gender){
      const genderMap={
        true:'blue',
        false:'danger'
      }
      return genderMap[gender]
    }
  },
  data() {
    return {
      list: null,
      total:0,
      listLoading: true,
      listQuery: {
        pageNumber: 1,
        pageSize: 20,
        title: undefined,
        type: undefined,
        sort: '+id',
        importance: undefined
        
      }

    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getAllUser(this.listQuery).then(response => {
        this.list = response.data.data.data;
        this.listLoading = false
        this.total=response.data.data.total;
      })
    },
    handleFilter() {
      
    },
    handleModifyStatus(row, status) {
      
    },
    sortChange(data) {
      
    },
    sortByID(order) {
      
    },
    resetTemp() {
      
    },
    handleCreate() {
      
    },
    createData() {
      
    },
    handleUpdate(row) {
      
    },
    updateData() {
      
    },
    handleDelete(row) {
      //删除文章
      console.log(row)
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteUser(row.id).then(response=>{
            if(response.data.status==200){
              //删除成功
              //重新加载文章
              this.listLoading=true;
              this.listQuery={
                pageNumber: 1,
                pageSize: 20,
                title: undefined,
                type: undefined,
                sort: '+id',
                importance: undefined
              };
              this.list=[];
              this.fetchData();
            }else{
              //删除失败
              this.$message(response.data.msg);
            }
            
          })
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    handleFetchPv(pv) {
      
    },
    handleDownload() {
      
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    }
  }
}
</script>
