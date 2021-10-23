//app.js
const config = require('./utils/config')
const util = require('./utils/util.js')
const api = require('./utils/api')
var aldstat = require("./utils/ald-stat.js")

App({
  config,
  util,
  api,
  onLaunch: function() {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        console.log('wx.login', res)
        wx.setStorageSync('code', res.code)
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })

    let userInfo = wx.getStorageSync('userInfo') || null
    let categoryType = wx.getStorageSync('categoryType') || null
    if (userInfo && categoryType) {
      wx.redirectTo({
        url: `/pages/main/main?cityID=${categoryType.cityID}&sujectID=${categoryType.sujectID}`
      })
    }

    // 获取用户信息 此接口微信已废弃 获取 wx.getUserInfo 接口后续将不再出现授权弹窗，请注意升级
    // 获取用户信息
    // wx.getSetting({
    //   success: res => {
    //     console.log('wx.getSetting', res)
    //     if (res.authSetting['scope.userInfo']) {
    //       // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
    //       wx.getUserInfo({
    //         success: res => {
    //           console.log('wx.getUserInfo', res)

    //           // 可以将 res 发送给后台解码出 unionId
    //           this.globalData.userInfo = res.userInfo

    //           // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
    //           // 所以此处加入 callback 以防止这种情况
    //           if (this.userInfoReadyCallback) {
    //             this.userInfoReadyCallback(res)
    //           }
    //         }
    //       })
    //     }
    //   }
    // })
  },
  // 新版 按钮方式登录
  getUserInfo(detail, cb) {
    if (this.globalData.userInfo) {
      typeof cb == 'function' && cb(this.globalData.userInfo)
    } else {
      // 此次项目中，我们不适用 加密字串，而是使用 userInfo 作为参数
      // 然后在服务器端，将 wxMember.id 作为 token 存储
      // 由于 4 月份，微信官方对 getUserInfo 做了调整，为了节省 BUG 调整时间，所以就将 userInfo 的数据暂时写死

      let data = {
        // code: wx.getStorageSync('code') || null,
        // encryptedData: detail.encryptedData,
        // iv: detail.iv
        nickName: "Wriprin", // 昵称
        gender: 1, // 1 男， 0 女
        language: "zh_CN", // 语言
        city: "HangZhou", // 城市
        province: "ZheJiang", // 省份
        country: "China", // 国家
        avatarUrl: "http://cdn.jsdelivr.net/gh/Wriprin/Wriprin-Gallery/Gallery/wriprin.jpg" // 头像
      }
      console.log('data => ', data)
      this.api.userLogin(data).then(res => {
        console.log('res => ', res)
        wx.setStorageSync('token', res.data.result.token)
        wx.setStorageSync('userInfo', res.data.result.userInfo)
        typeof cb == 'function' && cb()
      })
    }
  },
  // getUserInfo (cb) {
  //   if (this.globalData.userInfo) {
  //     typeof cb == "function" && cb(this.globalData.userInfo)
  //   } else {
  //     //调用登陆接口
  //     wx.login({
  //       success: res => {
  //         const code = res.code // code
  //         wx.getUserInfo({
  //           success:(res) => {
  //             let data = {
  //               code,
  //               encryptedData: res.encryptedData,
  //               iv: res.iv
  //             }
  //             this.api.userLogin(data).then(res => {
  //               this.globalData.userInfo = res
  //               typeof cb == "function" && cb(this.globalData.userInfo)
  //             })
  //             // this.globalData.userInfo = res.userInfo
  //             // typeof cb == "function" && cb(this.globalData.userInfo)
  //           }
  //         })
  //       }
  //     })
  //   }
  // },
  globalData: {
  }
})
