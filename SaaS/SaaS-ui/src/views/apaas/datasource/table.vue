<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="数据源" prop="search_EQ_dsId">
        <el-select v-model="queryParams.search_EQ_dsId" size="small">
          <el-option
            v-for="item in dsOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="表名称" prop="search_LIKE_tableName">
        <el-input
          v-model="queryParams.search_LIKE_tableName"
          placeholder="请输入表名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="search_EQ_statusFlag">
        <el-select v-model="queryParams.search_EQ_statusFlag" placeholder="数据状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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
      <el-table-column label="编码" align="center" prop="id" />
      <el-table-column label="表名" align="center" prop="tableName" :show-overflow-tooltip="true" />
      <el-table-column label="显示名称" align="center" prop="functionName" />
      <el-table-column label="模版" align="center" prop="tplCategory" :formatter="tplCategoryFormat"/>
      <el-table-column label="SAAS" align="center" prop="saasFlag" :formatter="saasFlagFormat"/>



      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="DB同步时间" align="center" prop="syncDbTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.syncDbTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="tableComment" :show-overflow-tooltip="true"  />
      <el-table-column label="修改人" align="center" prop="updateBy" :show-overflow-tooltip="true" />
      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
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
            type="text"
            size="small"
            icon="el-icon-refresh"
            @click="handleSynchDb(scope.row)"
            v-hasPermi="['apaas:ds:edit']"
          >同步</el-button>
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

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="数据源">
          <el-input v-model="form.dsId" :disabled="true" />
        </el-form-item>
        <el-form-item label="表名称" prop="dictLabel">
          <el-input v-model="form.tableName" placeholder="请输入表名称" />
        </el-form-item>
        <el-form-item label="显示名称" prop="dictLabel">
          <el-input v-model="form.functionName" placeholder="请输入显示名称" />
        </el-form-item>
        <el-form-item label="类型" prop="tplCategory">
          <el-radio-group v-model="form.tplCategory">
            <el-radio
              v-for="dict in tableTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.statusFlag">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="tableComment">
          <el-input v-model="form.tableComment" placeholder="请输入表备注" />
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
  import { listTable, getTable, delTable, addTable, updateTable, exportTable, synchDb } from "@/api/apaas/table";
  import { listDataSource, getDataSource } from "@/api/apaas/ds";
  import consts from "@/consts";
  export default {
    name: "Data",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        defaultDsId: null,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 表格数据
        dataList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 状态数据数据表
        statusOptions: [],
        tableTypeOptions: [],
        // 数据源
        dsOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          search_LIKE_tableName: undefined,
          search_EQ_dsId: undefined,
          search_EQ_statusFlag: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          tableName: [
            { required: true, message: "名称不能为空", trigger: "blur" }
          ],
          tplCategory: [
            { required: true, message: "生成模板不能为空", trigger: "blur" }
          ],
          functionName: [
            { required: true, message: "显示名称不能为空", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      const dsId = this.$route.params && this.$route.params.dsId;
      this.queryParams.search_EQ_dsId = parseInt(dsId);
      this.defaultDsId = dsId;
      this.getDSList();
      this.getList();
      this.yesOrNoOptions = consts.yesOrNoOptions;
      this.statusOptions = consts.statusOptions;
      this.getDicts("apaas_table_type").then(response => {
        this.tableTypeOptions = response.data;
      });
    },
    methods: {

      /** 查询数据表类型列表 */
      getDSList() {
        listDataSource().then(response => {
          this.dsOptions = response.data.content;
        });
      },
      /** 查询数据表数据列表 */
      getList() {
        this.loading = true;
        listTable(this.queryParams).then(response => {
          this.dataList = response.data.content;
          this.total = response.data.total;
          this.loading = false;
        });
      },
      /** 同步数据库操作 */
      handleSynchDb(row) {
        const id = row.id;
        this.$confirm('确认要强制同步编码为"' + id + '"数据库表结构吗？', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return synchDb(id);
        }).then(() => {
          this.msgSuccess("同步成功");
        })
      },
      saasFlagFormat(row, column) {
        return this.selectDictLabel(this.yesOrNoOptions, row.saasFlag);
      },
      tplCategoryFormat(row, column) {
        return this.selectDictLabel(this.tableTypeOptions, row.tplCategory);
      },
      // 数据状态数据表翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.statusFlag);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: undefined,
          tableName: undefined,
          status: "0"
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
        this.resetForm("queryForm");
        this.queryParams.search_EQ_dsId = this.defaultDsId;
        this.handleQuery();
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加数据表数据";
        this.form.dsId = this.queryParams.search_EQ_dsId;
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        const tableId = row.id || this.ids[0];
        this.$router.push("/apaas/datasource/table/edit/" + tableId);
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateTable(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addTable(this.form).then(response => {
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
        const tableIds = row.id || this.ids;
        this.$confirm('是否确认删除数据表编码为"' + tableIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTable(tableIds);
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
          return exportTable(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    }
  };
</script>
