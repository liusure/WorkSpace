<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="2" style="padding-left: 20px">
        <el-image style="width: 100px; height: 100px"
                  :src="url"
                  :fit="fit"></el-image>
      </el-col>
      <el-col :sm="24" :lg="22" style="padding-left: 50px">
        <el-row>
          <el-col :span="6">
            <h4>名称：江湖一品</h4>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>商家认证：<i class="el-icon-check"></i>已认证</h4>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>有效期：2024年10月11日</h4>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="2">
            <el-button
              type="primary"
              icon="el-icon-menu"
              size="mini"
              @click="handleAdd"
            >微信菜单</el-button>
          </el-col>
          <el-col :span="2">
            <el-button
              type="success"
              icon="el-icon-chat-square"
              size="mini"
              @click="handleAdd"
            >模版消息</el-button>
          </el-col>
          <el-col :span="2">
            <el-button
              type="success"
              icon="el-icon-postcard"
              size="mini"
              @click="handleAdd"
            >支付配置</el-button>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="30">
      <el-col :sm="24" :lg="16" style="padding-left: 20px">
        <el-row>
          <el-col :span="20">
            <h2>开启小程序商城</h2>
            <ul>
              <li>条件:绑定微信小程序，将店铺拓展到小程序。如果你已经有“认证小程序”，更可以进入控制台管理发布。</li>
              <li>进度:<el-tag type="success">已认证</el-tag></li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
      <el-col :sm="24" :lg="4" style="padding-left: 50px">
        <el-button
          type="success"
          icon="el-icon-s-platform"
          size="large"
          @click="handleAdd"
        >进入控制台</el-button>
      </el-col>
    </el-row>
    <el-divider />

    <el-row :gutter="30">
      <el-col :sm="24" :lg="16" style="padding-left: 20px">
        <el-row>
          <el-col :span="18">
            <h2>开启小程序商城</h2>
            <ul>
              <li>条件:绑定微信小程序，将店铺拓展到小程序。如果你已经有“认证小程序”，更可以进入控制台管理发布。</li>
              <li>进度:<el-tag >未认证</el-tag></li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
      <el-col :sm="24" :lg="6" style="padding-left: 50px">
        <el-button
          type="success"
          icon="el-icon-s-platform"
          size="large"
          @click="handleAdd"
        >一键授权</el-button>

      </el-col>
    </el-row>
    <el-divider />
  </div>
</template>

<script>
  import CountTo from 'vue-count-to'

  export default {
    name: "微信账号",
    components: {
      CountTo
    },
    data() {
      return {
        url:"http://wstore-1255653546.image.myqcloud.com/wstore_saas/2018-07-28/c587107e-099d-4528-a7ab-44bbf58934d9.png",
        version: "3.3.0",
      };
    },
    methods: {
      goTarget(href) {
        window.open(href, "_blank");
      },
      goConfig(){
        alert("do config");
      },
    },
  };
</script>

<style scoped lang="scss">


  .home {
    blockquote {
      padding: 10px 20px;
      margin: 0 0 20px;
      font-size: 17.5px;
      border-left: 5px solid #eee;
    }
    hr {
      margin-top: 20px;
      margin-bottom: 20px;
      border: 0;
      border-top: 1px solid #eee;
    }
    .col-item {
      margin-bottom: 20px;
    }

    ul {
      padding: 0;
      margin: 0;
    }

    font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 13px;
    color: #676a6c;
    overflow-x: hidden;

    ul {
      list-style-type: none;
    }

    h4 {
      margin-top: 0px;
    }

    h2 {
      margin-top: 10px;
      font-size: 26px;
      font-weight: 100;
    }

    p {
      margin-top: 10px;

      b {
        font-weight: 700;
      }
    }

    .update-log {
      ol {
        display: block;
        list-style-type: decimal;
        margin-block-start: 1em;
        margin-block-end: 1em;
        margin-inline-start: 0;
        margin-inline-end: 0;
        padding-inline-start: 40px;
      }
    }
  }
</style>

