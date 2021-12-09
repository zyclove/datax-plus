<template>
  <div class="createPost-container">
    <el-form ref="itemForm" :model="itemForm" :rules="rules" class="form-container">
      <div class="createPost-main-container">
        <el-row>
          <el-col :span="24">
            <el-form-item style="margin-bottom: 40px;" prop="itemTitle">
              <MDinput v-model="itemForm.itemTitle" :maxlength="100" name="name" required>
                工作名称
              </MDinput>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="100">
          <el-col :span="10">
            <el-form-item style="margin-bottom: 40px;" label="工作类别" prop="工作类别">
              <el-select v-model="itemForm.itemType" placeholder="Type" class="filter-item" style="width: 130px">
                <el-option v-for="item in typeValuesArray" :key="item.typeValue" :label="item.typeName" :value="item.typeValue" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="submitForm">
            取消
          </el-button>
          <el-button v-loading="loading" type="warning" @click="addOrUpdateData" :disabled="disableSubmit">
            提交
          </el-button>
        </el-row>

      </div>
    </el-form>
  </div>
</template>

<script>
import MDinput from '@/components/MDinput'
// import { validURL } from '@/utils/validate'
// import { fetchArticle } from '@/api/article'
import { searchUser } from '@/api/remote-search'
import { addOrUpdate, getJob } from '@/api/work-record'

const typeValuesArray = [
  { typeValue: 0, typeName: '小说' },
  { typeValue: 1, typeName: '散文' },
  { typeValue: 2, typeName: '科技论文' },
  { typeValue: 3, typeName: '其他' }
]

// const defaultForm = {
//   status: 'draft',
//   title: '', // 文章题目
//   content: '', // 文章内容
//   content_short: '', // 文章摘要
//   source_uri: '', // 文章外链
//   image_uri: '', // 文章图片
//   display_time: undefined, // 前台展示时间
//   id: undefined,
//   platforms: ['a-platform'],
//   comment_disabled: false,
//   importance: 0
// }

export default {
  name: 'CreateJob',
  components: { MDinput },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === '') {
        this.$message({
          message: rule.field + '为必传项',
          type: 'error'
        })
        callback(new Error(rule.field + '为必传项'))
      } else {
        callback()
      }
    }
    return {
      dataJobForm: {
        dataJobId: -1,
        dataJobName: ''
      },
      disableSubmit: false,
      typeValuesArray,
      loading: false,
      userListOptions: [],
      rules: {
        dataJobName: [
          { required: true, message: 'please input', trigger: 'blur' }
        ],
        image_uri: [{ validator: validateRequire }]
      },
      tempRoute: {}
    }
  },
  computed: {
    contentShortLength() {
      return this.itemForm.content_short.length
    },
    displayTime: {
      get() {
        return (+new Date(this.itemForm.display_time))
      },
      set(val) {
        this.itemForm.display_time = new Date(val)
      }
    }
  },
  created() {
    let itemId = this.$route.params.itemId
    // 如果itemId == -1 代表是新增，反之则是更新
    if (itemId > -1) {
      this.itemForm.itemId = itemId
      this.fetchData(itemId)
    }
    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
  },
  methods: {
    fetchData(j) {
      getJob(itemId).then(response => {
        this.itemForm = response.data
      }).catch(err => {
        console.log(err)
      })
    },
    setTagsViewTitle() {
      const title = 'Edit Article'
      const route = Object.assign({}, this.tempRoute, { title: `${title}-${this.itemForm.id}` })
      this.$store.dispatch('tagsView/updateVisitedView', route)
    },
    setPageTitle() {
      const title = 'Edit Article'
      document.title = `${title} - ${this.itemForm.id}`
    },
    addOrUpdateData() {
      this.$refs['dataJobForm'].validate((valid) => {
        if (valid) {
          this.disableSubmit = true
          addOrUpdate(this.dataJobForm).then(() => {
            this.$notify({
              title: 'Success',
              message: '操作成功',
              type: 'success',
              duration: 2000
            })
            this.$router.push({ path: '/components/works-record-table' })
          })
        }
      })
    },
    submitForm() {
      console.log(this.itemForm)
      this.$refs.itemForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$notify({
            title: '成功',
            message: '发布文章成功',
            type: 'success',
            duration: 2000
          })
          this.itemForm.status = 'published'
          this.loading = false
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    draftForm() {
      if (this.itemForm.content.length === 0 || this.itemForm.title.length === 0) {
        this.$message({
          message: '请填写必要的标题和内容',
          type: 'warning'
        })
        return
      }
      this.$message({
        message: '保存成功',
        type: 'success',
        showClose: true,
        duration: 1000
      })
      this.itemForm.status = 'draft'
    },
    getRemoteUserList(query) {
      searchUser(query).then(response => {
        if (!response.data.items) return
        this.userListOptions = response.data.items.map(v => v.name)
      })
    }
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

