<template>
  <el-dialog title="添加生日" :visible.sync="visible" width="30%">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules">
      <el-form-item label="姓名" :label-width="formLabelWidth" prop="contactId">
        <el-select v-model="ruleForm.contactId" filterable placeholder="请选择姓名" style="width: 100%">
          <el-option
            v-for="item in metaOptions.contact"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="计算方式" :label-width="formLabelWidth" prop="computeMode">
        <el-select
          v-model="ruleForm.computeMode"
          placeholder="请选择计算方式"
          style="width: 100%"
          @change="onFormComputeModeChange"
        >
          <el-option
            v-for="item in metaOptions.computeMode"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="生日表盘" :label-width="formLabelWidth">
        <!-- 公历表盘 -->
        <el-date-picker
          v-if="formGregorianDateVisible"
          v-model="ruleForm.gregorianDate"
          type="date"
          placeholder="请选择生日"
          format="yyyy年MM月dd日"
          value-format="yyyy-MM-dd"
          @change="onFormGregorianDateChange"
        />
        <!-- 农历表盘 -->
        <el-row v-if="formLunarDateVisible">
          <el-col :span="8">
            <el-date-picker
              v-model="ruleForm.lunarYear"
              type="year"
              placeholder="年份"
              value-format="yyyy"
              @change="onFormLunarYearChange"
            />
          </el-col>
          <el-col :span="8">
            <el-select
              v-model="ruleForm.lunarMonth"
              placeholder="月份"
              @change="onFormLunarMonthChange"
            >
              <el-option
                v-for="item in metaOptions.lunarMonth"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-col>
          <el-col :span="8">
            <el-select
              v-model="ruleForm.lunarDay"
              placeholder="日"
              @change="onFormLunarDayChange"
            >
              <el-option
                v-for="item in metaOptions.lunarDay"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item label="生日表达式" :label-width="formLabelWidth" prop="regexStr">
        <el-input v-model="ruleForm.regexStr" readonly />
      </el-form-item>
      <el-form-item label="备注" :label-width="formLabelWidth" prop="remark">
        <el-input v-model="ruleForm.remark" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="onFormSubmitClick('ruleForm')">立即创建</el-button>
      <el-button @click="onFormResetClick('ruleForm')">重置</el-button>
    </div>
  </el-dialog>
</template>

<script>
import contactInfoService from '@/api/vital/contactInfo'
import birthDateService from '@/api/vital/birthDate'
import calendarDictService from '@/api/vital/calendarDict'
import { MessageUtil } from '@/utils/elementuiTool'

export default {
  data() {
    return {
      formLabelWidth: '100px',
      visible: false,
      metaOptions: {
        contact: [],
        computeMode: [
          { value: 'gregorian', label: '公历' },
          { value: 'lunar', label: '农历' }
        ],
        lunarMonth: [],
        lunarDay: []
      },
      formGregorianDateVisible: false,
      formLunarDateVisible: false,
      ruleForm: {
        contactId: null,
        computeMode: null,
        gregorianDate: null,
        lunarYear: null,
        lunarMonth: null,
        lunarDay: null,
        regexStr: null
      },
      rules: {
        contactId: [
          { required: true, message: '请选择姓名', trigger: 'change' }
        ]
      }
    }
  },
  created() {
  },
  methods: {
    show() {
      const _this = this
      contactInfoService.listMetaContactInfos({ current: 1, size: 1000, args: {}}).then(res => {
        _this.metaOptions.contact = res || []
      })
      this.ruleForm.contactId = null
      this.ruleForm.computeMode = null
      this.ruleForm.gregorianDate = null
      this.ruleForm.lunarYear = null
      this.ruleForm.lunarMonth = null
      this.ruleForm.lunarDay = null
      this.ruleForm.regexStr = null
      this.visible = true
    },
    onFormComputeModeChange(computeMode) {
      const _this = this
      if (computeMode === 'gregorian') { // 公历
        _this.formGregorianDateVisible = true
        _this.formLunarDateVisible = false
      } else { // 农历
        _this.formGregorianDateVisible = false
        _this.formLunarDateVisible = true
      }
    },
    onFormGregorianDateChange(dateStr) {
      this.ruleForm.regexStr = dateStr
    },
    onFormLunarYearChange(yearStr) {
      const _this = this
      _this.ruleForm.lunarYear = yearStr
      calendarDictService.listMetaMonthsByLunarYear({ lunarYear: yearStr }).then(data => {
        _this.metaOptions.lunarMonth = data
        _this.ruleForm.regexStr = yearStr + '#' + _this.ruleForm.lunarMonth + '#' + _this.ruleForm.lunarDay
      }).catch(err => {
        console.error(err)
      })
    },
    onFormLunarMonthChange(month) {
      const _this = this
      _this.ruleForm.lunarMonth = month
      calendarDictService.listMetaDaysByLunarYearMonth({
        lunarYear: _this.ruleForm.lunarYear,
        lunarMonth: month
      }).then(data => {
        _this.metaOptions.lunarDay = data
        _this.ruleForm.regexStr = _this.ruleForm.year + '#' + month + '#' + _this.ruleForm.lunarDay
      }).catch(err => {
        console.error(err)
      })
    },
    onFormLunarDayChange(day) {
      const _this = this
      _this.ruleForm.lunarDay = day
      _this.ruleForm.regexStr = this.ruleForm.lunarYear + '#' + this.ruleForm.lunarMonth + '#' + day
    },
    onFormSubmitClick(formName) {
      const _this = this
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          birthDateService.createBirthDate(_this.ruleForm).then(res => {
            MessageUtil.success(_this, '操作成功')
            _this.$emit('onSuccessCallback')
            _this.visible = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    onFormResetClick(formName) {
      const _this = this
      _this.formGregorianDateVisible = false
      _this.formLunarDateVisible = false
      _this.$refs[formName].resetFields()
    }
  }
}
</script>
