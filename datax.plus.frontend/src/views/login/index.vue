<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" autocomplete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">机场运行系统</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="Username"
          name="username"
          type="text"
          tabindex="1"
          autocomplete="on"
        />
      </el-form-item>

      <el-tooltip v-model="capsTooltip" content="Caps lock is On" placement="right" manual>
        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            placeholder="Password"
            name="password"
            tabindex="2"
            autocomplete="on"
            @keyup.native="checkCapslock"
            @blur="capsTooltip = false"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>
      </el-tooltip>

      <el-tooltip v-if="forLogin == 0" v-model="capsTooltip" content="Caps lock is On" placement="right" manual>
        <el-form-item prop="activePassword">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            :key="passwordType"
            ref="activePassword"
            v-model="loginForm.activePassword"
            :type="passwordType"
            placeholder="新密码"
            name="activePassword"
            tabindex="2"
            autocomplete="on"
            @keyup.native="checkCapslock"
            @blur="capsTooltip = false"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>
      </el-tooltip>

      <el-tooltip v-if="forLogin == 0" v-model="capsTooltip" content="Caps lock is On" placement="right" manual>
        <el-form-item prop="activePassword2">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            v-if="forLogin == 0"
            :key="passwordType"
            ref="activePassword2"
            v-model="loginForm.activePassword2"
            :type="passwordType"
            placeholder="再次输入新密码"
            name="activePassword2"
            tabindex="2"
            autocomplete="on"
            @keyup.native="checkCapslock"
            @blur="capsTooltip = false"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>
      </el-tooltip>

      <el-button v-if="forLogin == 1" :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin">登录</el-button>
      <el-button v-if="forLogin == 0" :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleActiveUser">激活用户</el-button>
      <el-button v-if="forLogin == 0" :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleActiveUser">取消返回</el-button>
    </el-form>

    <el-dialog title="Or connect with" :visible.sync="showDialog">
      Can not be simulated on local, so please combine you own business simulation! ! !
      <br>
      <br>
      <br>
      <social-sign />
    </el-dialog>
  </div>
</template>

<script>
// import { validUsername } from '@/utils/validate'
import SocialSign from './components/SocialSignin'
import { checkLogin } from '@/api/user'
import { Message } from 'element-ui'
// import { Base64 } from 'js-base64'
var jwt = require('jsonwebtoken')

export default {
  name: 'Login',
  components: { SocialSign },
  data() {
    // const validateUsername = (rule, value, callback) => {
    //   if (!validUsername(value)) {
    //     callback(new Error('Please enter the correct user name'))
    //   } else {
    //     callback()
    //   }
    // }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('The password can not be less than 6 digits'))
      } else {
        callback()
      }
    }
    return {
      forLogin: 1,
      loginForm: {
        username: 'admin',
        password: '123456',
        activePassword: '',
        activePassword2: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur' }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      passwordType: 'password',
      capsTooltip: false,
      loading: false,
      showDialog: false,
      redirect: undefined,
      otherQuery: {}
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        const query = route.query
        if (query) {
          this.redirect = query.redirect
          this.otherQuery = this.getOtherQuery(query)
        }
      },
      immediate: true
    }
  },
  created() {
    // window.addEventListener('storage', this.afterQRScan)
  },
  mounted() {
    if (this.loginForm.username === '') {
      this.$refs.username.focus()
    } else if (this.loginForm.password === '') {
      this.$refs.password.focus()
    }
  },
  destroyed() {
    // window.removeEventListener('storage', this.afterQRScan)
  },
  methods: {
    checkCapslock(e) {
      const { key } = e
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          // 登录判断逻辑
          new Promise((resolve, reject) => {
            // 这里的checkLogin没写对，是应该用token去看用户的token是否过期，后面再重构。
            checkLogin(this.loginForm)
              .then(res => {
                if (res.data.code === 100) {
                  const parsedToken = jwt.decode(res.token)
                  console.log('parsedToken:', parsedToken)
                  const parsedJson = JSON.parse(parsedToken.sub)
                  // 进行判断，如果用户状态为0(未激活状态)，则要求用户重新登录。
                  if (parsedJson.userStatus === 0) {
                    this.loading = false
                    this.$confirm('你的账户需要激活才能使用！')
                      .then(_ => {
                        // this.$router.push({ path: '/active-user' })
                        this.forLogin = 0
                      })
                      .catch(_ => {})
                  } else {
                    this.$store.dispatch('user/loginInfo', res.token)
                      .then(() => {
                        console.log('otherQuery', this.otherQuery, '|', 'redirect', this.redirect)
                        this.$router.push({ path: this.redirect || '/', query: this.otherQuery })
                        this.loading = false
                      })
                      .catch(() => {
                        this.loading = false
                      })
                  }
                } else {
                  console.log('login failed')
                  Message({
                    message: res.data.msg || 'Error',
                    type: 'error',
                    duration: 1 * 1000
                  })
                  this.loading = false
                  return false
                }

                resolve()
              })
              .catch(error => {
                console.log(error)
                reject(error)
              })
          }).catch(error => {
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    handleActiveUser() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          // 登录判断逻辑
          new Promise((resolve, reject) => {
            // 这里的checkLogin没写对，是应该用token去看用户的token是否过期，后面再重构。
            checkLogin(this.loginForm)
              .then(res => {
                if (res.data.code === 100) {
                  const parsedToken = jwt.decode(res.token)
                  const parsedJson = JSON.parse(parsedToken.sub)
                  // 进行判断，如果用户状态为0(未激活状态)，则要求用户重新登录。
                  if (parsedJson.userStatus === 0) {
                    this.loading = false
                    this.$confirm('你的账户需要激活才能使用！')
                      .then(_ => {
                        this.$router.push({ path: '/active-user' })
                      })
                      .catch(_ => {})
                  } else {
                    this.$store.dispatch('user/loginInfo', res.token)
                      .then(() => {
                        console.log('otherQuery', this.otherQuery, '|', 'redirect', this.redirect)
                        this.$router.push({ path: this.redirect || '/', query: this.otherQuery })
                        this.loading = false
                      })
                      .catch(() => {
                        this.loading = false
                      })
                  }
                } else {
                  console.log('login failed')
                  Message({
                    message: res.data.msg || 'Error',
                    type: 'error',
                    duration: 1 * 1000
                  })
                  this.loading = false
                  return false
                }

                resolve()
              })
              .catch(error => {
                console.log(error)
                reject(error)
              })
          }).catch(error => {
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur]
        }
        return acc
      }, {})
    }

  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  width: 100%;
  /*background-color: $bg;*/
  overflow: hidden;
  //background: url('../../assets/u1.png');

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }

  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }
}
</style>
