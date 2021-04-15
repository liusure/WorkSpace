<template>
  <el-form ref="basicInfoForm" :model="info" :rules="rules" label-width="150px">
    <el-row>
      <el-col :span="12">
        <el-form-item label="表名称" prop="tableName">
          <el-input placeholder="请输入仓库名称" v-model="info.tableName" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item label="表描述" prop="tableComment">
          <el-input placeholder="请输入" v-model="info.tableComment" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="functionName">
          <span slot="label">
            显示名称
            <el-tooltip content="用作类描述，例如 用户" placement="top">
              <i class="el-icon-question"></i>
            </el-tooltip>
          </span>
          <el-input v-model="info.functionName" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item label="实体类名称" prop="className">
          <el-input placeholder="请输入" v-model="info.className" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item label="类型" prop="tplCategory">
          <el-radio-group v-model="info.tplCategory">
            <el-radio
              v-for="dict in tableTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="info.statusFlag">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-col>




      <el-col :span="12">
        <el-form-item label="作者" prop="functionAuthor">
          <el-input placeholder="请输入" v-model="info.functionAuthor" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="packageName">
          <span slot="label">
            生成包路径
            <el-tooltip content="生成在哪个java包下，例如 com.saas.system" placement="top">
              <i class="el-icon-question"></i>
            </el-tooltip>
          </span>
          <el-input v-model="info.packageName" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="moduleName">
          <span slot="label">
            生成模块名
            <el-tooltip content="可理解为子系统名，例如 system" placement="top">
              <i class="el-icon-question"></i>
            </el-tooltip>
          </span>
          <el-input v-model="info.moduleName" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item prop="businessName">
          <span slot="label">
            生成业务名
            <el-tooltip content="可理解为功能英文名，例如 user" placement="top">
              <i class="el-icon-question"></i>
            </el-tooltip>
          </span>
          <el-input v-model="info.businessName" />
        </el-form-item>
      </el-col>


    </el-row>
  </el-form>
</template>
<script>
  export default {
    name: "BasicInfoForm",
    props: {
      info: {
        type: Object,
        default: null
      },
      statusOptions: {
        type: Array,
        default: []
      },
      tableTypeOptions: {
        type: Array,
        default: []
      }
    },
    data() {
      return {
        rules: {
          tableName: [
            { required: true, message: "请输入表名称", trigger: "blur" }
          ],
          tableComment: [
            { required: true, message: "请输入表描述", trigger: "blur" }
          ],
          className: [
            { required: true, message: "请输入实体类名称", trigger: "blur" }
          ],
          functionAuthor: [
            { required: true, message: "请输入作者", trigger: "blur" }
          ],
          tplCategory: [
            { required: true, message: "请选择生成模板", trigger: "blur" }
          ],
          packageName: [
            { required: true, message: "请输入生成包路径", trigger: "blur" }
          ],
          moduleName: [
            { required: true, message: "请输入生成模块名", trigger: "blur" }
          ],
          businessName: [
            { required: true, message: "请输入生成业务名", trigger: "blur" }
          ],
          functionName: [
            { required: true, message: "请输入显示功能名", trigger: "blur" }
          ]
        }
      };
    }
  };
</script>
