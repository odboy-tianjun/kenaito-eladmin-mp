<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <div class="head-container">
      <el-form :inline="true" :model="searchArgs">
        <el-form-item label="姓名">
          <el-input v-model="searchArgs.realName" placeholder="支持模糊查询" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSearchFormBtnClick">查询</el-button>
          <el-button type="primary" @click="onSaveBtnClick">添加生日</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 表格 -->
    <el-table :data="tableData" height="250" max-height="250" style="width: 100%">
      <el-table-column fixed="left" prop="contactRealName" label="姓名" width="220" />
      <el-table-column prop="regexStr" label="生日表达式" width="220" />
      <el-table-column prop="oneYearOld" label="周岁" />
      <el-table-column prop="virtualYearOld" label="虚岁" />
      <el-table-column prop="notifyStatus" label="是否通知">
        <template slot-scope="scope">
          <el-switch :value="scope.row.notifyStatus" active-color="#13ce66" inactive-color="#ff4949" @change="(status) => onTableRowNotifyStatusChange(scope.row, status)" />
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="备注" show-overflow-tooltip />
      <el-table-column prop="updateBy" label="更新人" />
      <el-table-column prop="updateTime" label="更新时间" />
      <el-table-column fixed="right" label="操作" width="120">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click.native.prevent="onTableRowDelete(scope.row)">移除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      layout="total, sizes, prev, pager, next"
      :current-page="pagination.current"
      :page-sizes="[20, 40, 60, 100]"
      :page-size="pagination.size"
      :total="pagination.total"
      @current-change="onPageCurrentChange"
      @size-change="onPageSizeChange"
    />
    <save-birth-date-dialog ref="saveBirthDateDialog" @onSuccessCallback="loadTableData(pagination, searchArgs)" />
  </div>
</template>

<script>
import birthDateService from '@/api/vital/birthDate'
import { MessageBoxUtil, MessageUtil } from '@/utils/elementuiTool'
import SaveBirthDateDialog from '@/views/vital/birthDate/SaveBirthDateDialog'

export default {
  name: 'VitalBirthDate',
  components: { SaveBirthDateDialog },
  data() {
    return {
      // 查询条件
      searchArgs: {
        realName: ''
      },
      // 表格
      tableData: [],
      // 分页
      pagination: {
        current: 1,
        size: 20,
        total: 0
      }
    }
  },
  mounted() {
    this.loadTableData(this.pagination, this.searchArgs)
  },
  methods: {
    loadTableData(pagination, args) {
      const _this = this
      const data = {
        current: pagination.current,
        size: pagination.size,
        args: args
      }
      birthDateService.searchBirthDates(data).then(res => {
        _this.tableData = res.content || []
        _this.pagination.total = res.totalElements || 0
      })
    },
    onSearchFormBtnClick() {
      const _this = this
      _this.pagination.current = 1
      _this.loadTableData(_this.pagination, _this.searchArgs)
    },
    onSaveBtnClick() {
      const _this = this
      _this.$refs.saveBirthDateDialog.show()
    },
    onTableRowNotifyStatusChange(row, status) {
      const _this = this
      row.notifyStatus = status
      birthDateService.modifyBirthDateNotifyStatus(row).then(res => {
        MessageUtil.success(_this, '操作成功')
        _this.loadTableData(_this.pagination, _this.searchArgs)
      })
    },
    onTableRowDelete(row) {
      const _this = this
      MessageBoxUtil.deleteConfirm(_this, () => {
        const data = {
          ids: [row.id]
        }
        birthDateService.deleteBirthDates(data).then(res => {
          MessageUtil.success(_this, '操作成功')
          _this.loadTableData(_this.pagination, _this.searchArgs)
        })
      }, null)
    },
    onPageCurrentChange(current) {
      const _this = this
      _this.pagination.current = current
      _this.loadTableData(_this.pagination, _this.searchArgs)
    },
    onPageSizeChange(size) {
      const _this = this
      _this.pagination.current = 1
      _this.pagination.size = size
      _this.loadTableData(_this.pagination, _this.searchArgs)
    }
  }
}
</script>
