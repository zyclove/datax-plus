<template>
  <div class="createPost-container">
    <el-form
             ref="dataSourceForm"
             v-loading="formLoading"
             :model="dataSourceForm"
             :rules="rules"
             class="form-container">
      <div class="createPost-main-container">
        <el-row>
          <el-col :span="10">
<!--            <el-form-item style="margin-bottom: 40px;" prop="itemTitle">-->
<!--              <MDinput v-model="dataSourceForm.dataJobName" :maxlength="100" name="name" required>-->
<!--                工作名称-->
<!--              </MDinput>-->
<!--            </el-form-item>-->
            <el-form-item label="连接名称" prop="dataJobName">
              <el-input
                v-model="dataSourceForm.dataJobName"
                placeholder="连接名称"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="100">
          <el-col :span="10">
            <el-form-item style="margin-bottom: 40px;" label="数据库类别" prop="数据库类别">
              <el-select v-model="dataSourceForm.dataSourceType" placeholder="Type" class="filter-item" style="width: 130px">
                <el-option v-for="item in dataSourceTypeArray" :key="item.dataSourceTypeId" :label="item.dataSourceTypeName" :value="item.dataSourceTypeId" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <el-form-item label="连接地址" prop="dbHostUrl">
              <el-input
                v-model="dataSourceForm.dbHostUrl"
                placeholder="连接地址"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <el-form-item label="账号" prop="dbHostUrl">
              <el-input
                v-model="dataSourceForm.dbUsername"
                placeholder="username"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <el-form-item label="密码" prop="dbHostUrl">
              <el-input
                v-model="dataSourceForm.dbPassword"
                placeholder="password"
                type="password"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="5">
            <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="checkConnection">
              连通性测试
            </el-button>
          </el-col>
          <el-col :span="5">
            <el-tag
              v-if="this.dataSourceForm.connected === 1"
              type="success">
              连通性测试通过
            </el-tag>
            <el-tag
              v-if="this.dataSourceForm.connected === 0"
              type="danger">
              尚未通过连通性测试
            </el-tag>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="5">
            <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="cancelButton">
              取消
            </el-button>
          </el-col>
          <el-col :span="5">
            <el-button v-loading="loading" type="warning" @click="addOrUpdateData" :disabled="disableSubmit">
              提交
            </el-button>
          </el-col>
        </el-row>

      </div>
    </el-form>
  </div>
</template>

<script>
import { addOrUpdate, getDataSourceById, listDataSourceType, checkConnection } from '@/api/data-source'
// import { deleteFunction } from '@/api/role'

const dataSourceTypeArray = [
]

export default {
  name: 'CreateDataSource',
  components: {
  },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    // const validateRequire = (rule, value, callback) => {
    //   if (value === '') {
    //     this.$message({
    //       message: rule.field + '为必传项',
    //       type: 'error'
    //     })
    //     callback(new Error(rule.field + '为必传项'))
    //   } else {
    //     callback()
    //   }
    // }
    return {
      dataSourceForm: {
        dataSourceId: 0,
        dataSourceName: '',
        dataSourceType: 1,
        dbHostUrl: '',
        dbUsername: '',
        dbPassword: '',
        connected: 0
      },
      formLoading: false,
      disableSubmit: false,
      dataSourceTypeArray,
      loading: false,
      userListOptions: [],
      rules: {
        dataJobName: [
          { required: true, message: 'please input', trigger: 'blur' }
        ]
      },
      tempRoute: {},
      editorOptions: {
        enableBasicAutocompletion: true,
        enableSnippets: true,
        enableLiveAutocompletion: true,
        tabSize: 2,
        fontSize: 20,
        showPrintMargin: false
      }
    }
  },
  computed: {
    contentShortLength() {
      return this.dataSourceForm.content_short.length
    },
    displayTime: {
      get() {
        return (+new Date(this.dataSourceForm.display_time))
      },
      set(val) {
        this.dataSourceForm.display_time = new Date(val)
      }
    }
  },
  created() {
    // 获取数据库类型下拉列表
    this.fetchDataSourceType()
    const dataJobId = this.$route.params.dataJobId
    // 如果dataJobId == -1 代表是新增，反之则是更新
    if (dataJobId > -1) {
      this.dataSourceForm.dataJobId = dataJobId
      this.fetchData(dataJobId)
    }
    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
  },
  methods: {
    editorInit: function() {
      require('brace/theme/chrome')
      require('brace/ext/language_tools')
      // require('brace/mode/yaml')
      require('brace/mode/sql')
      // require('brace/mode/less')
      require('brace/snippets/sql')
    },
    fetchData(j) {
      getDataSourceById(this.dataSourceForm.dataJobId).then(response => {
        this.dataSourceForm = response.data
      }).catch(err => {
        console.log(err)
      })
    },
    fetchDataSourceType() {
      listDataSourceType(1, 1000).then(response => {
        console.log(response.data)
        this.dataSourceTypeArray = response.data.list
      }).catch(err => {
        console.log(err)
      })
    },
    // setTagsViewTitle() {
    //   const title = 'Edit Article'
    //   const route = Object.assign({}, this.tempRoute, { title: `${title}-${this.dataSourceForm.id}` })
    //   this.$store.dispatch('tagsView/updateVisitedView', route)
    // },
    // setPageTitle() {
    //   const title = 'Edit Article'
    //   document.title = `${title} - ${this.dataSourceForm.id}`
    // },
    addOrUpdateData() {
      this.$refs['dataSourceForm'].validate((valid) => {
        if (valid) {
          if (this.dataSourceForm.connected === 0) {
            this.$confirm('当前数据源尚未通过联通性测试，是否确认要提交？')
              .then(_ => {
                this.disableSubmit = true
                addOrUpdate(this.dataSourceForm).then(() => {
                  this.$notify({
                    title: 'Success',
                    message: '操作成功',
                    type: 'success',
                    duration: 2000
                  })
                  this.$router.push({ path: '/components/dataSource/list' })
                })
                // done();
              }).catch(_ => {})
          }
        }
      })
    },
    checkConnection() {
      this.$refs['dataSourceForm'].validate((valid) => {
        if (valid) {
          this.formLoading = true
          checkConnection(this.dataSourceForm).then(response => {
            setTimeout(() => {
              if (response.data.code === 1) {
                this.dataSourceForm.connected = 1
                this.$notify({
                  title: 'Success',
                  message: '测试连接数据源成功',
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.dataSourceForm.connected = 0
                this.$notify.error({
                  title: 'Failed',
                  message: '测试连接数据源失败',
                  duration: 2000
                })
              }
              this.formLoading = false
            }, 1000)
          })
        }
      })
    },
    cancelButton() {
      this.$router.push({ path: '/components/works-record-table' })
    }
    // submitForm() {
    //   this.$refs.dataSourceForm.validate(valid => {
    //     if (valid) {
    //       this.loading = true
    //       this.$notify({
    //         title: '成功',
    //         message: '发布文章成功',
    //         type: 'success',
    //         duration: 2000
    //       })
    //       this.dataSourceForm.status = 'published'
    //       this.loading = false
    //     } else {
    //       console.log('error submit!!')
    //       return false
    //     }
    //   })
    // }
    // draftForm() {
    //   if (this.dataSourceForm.content.length === 0 || this.dataSourceForm.title.length === 0) {
    //     this.$message({
    //       message: '请填写必要的标题和内容',
    //       type: 'warning'
    //     })
    //     return
    //   }
    //   this.$message({
    //     message: '保存成功',
    //     type: 'success',
    //     showClose: true,
    //     duration: 1000
    //   })
    //   this.dataSourceForm.status = 'draft'
    // },
    // getRemoteUserList(query) {
    //   searchUser(query).then(response => {
    //     if (!response.data.items) return
    //     this.userListOptions = response.data.items.map(v => v.name)
    //   })
    // }
  }
}
</script>

<style lang="scss" scoped>
  @import "~@/styles/mixin.scss";

  .createPost-container {
    position: relative;

  .createPost-main-container {
    padding: 40px 45px 20px 50px;

  .postInfo-container {
    position: relative;
  @include clearfix;
    margin-bottom: 10px;

  .postInfo-container-item {
    float: left;
  }
  }
  }

  .word-counter {
    width: 40px;
    position: absolute;
    right: 10px;
    top: 0px;
  }
  }

  .article-textarea ::v-deep {
  textarea {
    padding-right: 40px;
    resize: none;
    border: none;
    border-radius: 0px;
    border-bottom: 1px solid #bfcbd9;
  }
  }
</style>

