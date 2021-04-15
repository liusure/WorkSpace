<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="ID" prop="search_EQ_id">
        <el-input
          v-model="queryParams.search_EQ_id"
          placeholder="请输入ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="search_LIKE_name">
        <el-input
          v-model="queryParams.search_LIKE_name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="search_EQ_statusFlag">
        <el-select v-model="queryParams.search_EQ_statusFlag" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>


      <el-form-item label="更新人" prop="search_LIKE_updateBy">
        <el-input
          v-model="queryParams.search_LIKE_updateBy"
          placeholder="请输入更新人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="更新时间">
        <el-date-picker
          v-model="daterangeupdateTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>


      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['apaas:ds:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['apaas:ds:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['apaas:ds:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['apaas:ds:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="名称" align="center" :show-overflow-tooltip="true" >
      <template slot-scope="scope">
        <router-link :to="'/apaas/datasource/table/' + scope.row.id" class="link-type">
          <span>{{ scope.row.name }}</span>
        </router-link>
      </template>
      </el-table-column>
      <el-table-column label="类型" align="center" prop="dsType"  :formatter="dsTypeFormat"/>
      <el-table-column label="作者" align="center" prop="author" />
      <el-table-column label="包名" align="center" prop="packageName" />
      <el-table-column label="带前缀" align="center" prop="autoRemovePre" :formatter="autoRemovePreFormat"/>
      <el-table-column label="表前缀" align="center" prop="tablePrefix" />
      <el-table-column label="更新人" align="center" prop="updateBy" />
      <el-table-column label="创建人" align="center" prop="createBy" />
      <el-table-column label="状态" align="center" prop="statusFlag" :formatter="statusFlagFormat" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['apaas:ds:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['apaas:ds:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="数据库类型" prop="dsType">
          <el-select v-model="form.dsType" placeholder="请选择数据库类型">
            <el-option
              v-for="dict in dbTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="数据库地址" prop="dsUrl">
          <el-input v-model="form.dsUrl" placeholder="请输入数据库地址" />
        </el-form-item>
        <el-form-item label="数据库用户名" prop="dsUser">
          <el-input v-model="form.dsUser" placeholder="请输入数据库用户名" />
        </el-form-item>
        <el-form-item label="数据库密码" prop="dsPwd">
          <el-input v-model="form.dsPwd" placeholder="请输入数据库密码" />
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入代码生成注释作者" />
        </el-form-item>
        <el-form-item label="包路径(可选)" prop="packageName">
          <el-input v-model="form.packageName" placeholder="请输入代码生成包路径" />
        </el-form-item>
        <el-form-item label="去除表前缀" prop="autoRemovePre">
          <el-radio-group v-model="form.autoRemovePre">
            <el-radio
              v-for="dict in yesOrNoOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="表前缀" prop="tablePrefix">
          <el-input v-model="form.tablePrefix" placeholder="请输入表前缀" />
        </el-form-item>

        <el-form-item label="状态" prop="statusFlag">
          <el-select v-model="form.statusFlag" placeholder="请选择状态">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listDataSource, getDataSource, delDataSource, addDataSource, updateDataSource, exportDataSource } from "@/api/apaas/ds";
  import consts from "@/consts";

  export default {
    name: "DataSource",
    components: {
    },
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 数据
        dataList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 状态字典
        statusOptions: [],
        dbTypeOptions:[],
        yesOrNoOptions:[],
        // 更新时间时间范围
        daterangeupdateTime: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          search_LIKE_updateBy: null,
          search_EQ_statusFlag: null,
          search_BETWEEN_updateTime_date: null,
          search_LIKE_name: null,
          search_EQ_id: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          dsType: [
            { required: true, message: "数据库类型必须", trigger: "blur" }
          ],
          name: [
            { required: true, message: "公司简称不能为空", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
      this.statusOptions =consts.statusOptions;
      this.yesOrNoOptions = consts.yesOrNoOptions;
      this.getDicts("apaas_db_type").then(response => {
        this.dbTypeOptions = response.data;
      });
    },
    methods: {
      /** 查询列表 */
      getList() {
        this.loading = true;
        this.queryParams.params = {};
        if (null != this.daterangeupdateTime && '' != this.daterangeupdateTime) {
          this.queryParams.params["GTE_updateTime_date"] = this.daterangeupdateTime[0];
          this.queryParams.params["LT_updateTime_date"] = this.daterangeupdateTime[1];
        }
        listDataSource(this.queryParams).then(response => {
          this.dataList = response.data.content;
          this.total = response.data.total;
          this.loading = false;
        });
      },
      // 状态字典翻译
      statusFlagFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.statusFlag);
      },
      autoRemovePreFormat(row, column) {
        return this.selectDictLabel(this.yesOrNoOptions, row.autoRemovePre);
      },
      dsTypeFormat(row, column) {
        return this.selectDictLabel(this.dbTypeOptions, row.dsType);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          updateBy: null,
          createBy: null,
          statusFlag: null,
          delFlag: null,
          updateTime: null,
          createTime: null,
          updateUserId: null,
          createUserId: null,
          dsType: null,
          dsUrl: null,
          dsUser: null,
          dsPwd: null,
          author: "apaas",
          packageName: "com.saas.aps",
          tablePrefix: "aps_",
          autoRemovePre: false,
          name: null,
          id: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.daterangeUpdateTime = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getDataSource(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateDataSource(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addDataSource(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$confirm('是否确认删除编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDataSource(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportDataSource(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    }
  };
</script>
