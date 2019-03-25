<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="newtag" placeholder="请输入要添加的标签" style="width: 200px;" class="filter-item" />
      <el-select v-model="categoryid" placeholder="分类" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in allcategory" :key="item.id" :label="item.categorydescription" :value="item.id" />
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-upload2" @click="handleAdd">{{ "添加" }}</el-button>
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
      <el-table-column label="描述" min-width="150" align="center">
        <template slot-scope="scope">
          {{ scope.row.tagdescription }}
        </template>
      </el-table-column>
      <el-table-column label="分类" min-width="150" align="center">
        <template slot-scope="scope">
          {{ scope.row.categorydescription }}
        </template>
      </el-table-column>
      

      <el-table-column class-name="status-col" label="状态" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.tagstatus | statusFilter">{{ scope.row.tagstatus }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="320" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" :disabled="true" @click="handleUpdate(scope.row)">{{ "edit" }}</el-button>
          <el-button v-if="scope.row.tagstatus!='hide'" size="mini" type="gray" @click="handleModifyStatus(scope.row,'hide')">
            {{ "hide" }}
          </el-button>
          <el-button v-if="scope.row.tagstatus!='show'" size="mini" type="success" @click="handleModifyStatus(scope.row,'show')">
            {{ "show" }}
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
import { getAllTag,deleteTag,changeTagStatus,addTag } from '@/api/tag'
import { getAllCategory} from '@/api/category'
import { parseTime } from '@/utils'
import waves from '@/directive/waves' // Waves directive
import Pagination from '@/components/Pagination'
export default {
  directives: { waves },
  components:{Pagination},
  filters: {
    statusFilter(status) {
      const statusMap = {
        show: 'success',
        hide: 'info'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      total:0,
      newtag:null,
      categoryid:null,
      allcategory:[],
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
    this.fetchData();
    
  },
  methods: {
    handleAdd(){
      
      if(this.newtag==""||this.newtag==null){
        this.$message({
            type: 'info',
            message: '标签为空'
          }); 
        return ;
      }
      if(this.categoryid==null||this.categoryid==""){
        this.$message({
            type: 'info',
            message: '未选分类'
          }); 
        return ;
      }
      this.$confirm('此操作将添加该标签, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          addTag(this.newtag,this.categoryid).then(response=>{
            this.newtag="";
            this.categoryid="";
            if(response.data.status==200){
              //添加成功
              //重新加载
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
              this.$message({
              type: 'success',
              message: '添加成功!'
              });
            }else{
              //添加失败
              this.$message({
                type: 'info',
                message: response.data.msg
              });
            }  
          })
          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消添加'
          });          
        });
    },
    getAllCategory(){
      let params={
        pageNumber:1,
        pageSize:100
      }
      getAllCategory(params).then(response=>{
        this.allcategory=response.data.data.data;
      })
    },
    fetchData() {
      this.listLoading = true
      getAllTag(this.listQuery).then(response => {
        this.list = response.data.data.data;
        this.listLoading = false
        this.total=response.data.data.total;
        this.getAllCategory();
      })
    },
    handleFilter() {
      
    },
    handleModifyStatus(row, status) {
      changeTagStatus(row.id,status).then(data=>{
        if(data.status==200){
          this.$message({
          message: '操作成功',
          type: 'success'
          })
          row.tagstatus=status;
        }else{
          this.$message({
          message: '操作失败',
          type: 'Danger'
          })
        }
      })
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
      //删除标签
      this.$confirm('此操作将永久删除该分类, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteTag(row.id).then(response=>{
            if(response.data.status==200){
              //删除成功
              //重新加载
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
