<template>
  <el-dialog title="添加往来记录" :visible.sync="visible" width="30%">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules">
      <el-form-item label="事件日期" :label-width="formLabelWidth" prop="eventDate">
        <el-date-picker v-model="ruleForm.eventDate" type="date" placeholder="请选择事件日期" style="width: 100%" />
      </el-form-item>
      <el-form-item label="事件名称" :label-width="formLabelWidth" prop="eventName">
        <el-input v-model.trim="ruleForm.eventName" autocomplete="off" style="width: 100%" placeholder="请输入事件名称" />
      </el-form-item>
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
      <el-form-item label="礼金" :label-width="formLabelWidth" prop="cashGift">
        <el-input v-model.trim="ruleForm.cashGift" type="number" placeholder="请输入礼金" />
      </el-form-item>
      <el-form-item label="回礼日期" :label-width="formLabelWidth" prop="returnGiftDate">
        <el-date-picker v-model="ruleForm.returnGiftDate" type="date" placeholder="请选择回礼日期" style="width: 100%" />
      </el-form-item>
      <el-form-item label="回礼金额" :label-width="formLabelWidth" prop="returnCashGift">
        <el-input v-model.trim="ruleForm.returnCashGift" type="number" placeholder="请输入回礼金额" />
      </el-form-item>
      <el-form-item label="备注" :label-width="formLabelWidth" prop="remark" placeholder="请输入备注">
        <el-input v-model.trim="ruleForm.remark" autocomplete="off" style="width: 100%" />
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
import favorDealingHistoryService from '@/api/vital/favorDealingHistory'
import { MessageUtil } from '@/utils/elementuiTool'

export default {
  data() {
    return {
      formLabelWidth: '100px',
      visible: false,
      metaOptions: {
        contact: []
      },
      ruleForm: {
        eventDate: null,
        contactId: null,
        eventName: '',
        cashGift: null,
        returnGiftDate: null,
        returnCashGift: null,
        remark: ''
      },
      rules: {
        eventDate: [
          { required: true, message: '请选择事件日期', trigger: 'change' }
        ],
        contactId: [
          { required: true, message: '请选择姓名', trigger: 'change' }
        ],
        eventName: [
          { required: true, message: '请输入事件名称', trigger: 'blur' }
        ],
        cashGift: [
          { required: true, message: '请输入礼金金额', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    show() {
      const _this = this
      contactInfoService.listMetaContactInfos({ current: 1, size: 1000, args: {}}).then(res => {
        _this.metaOptions.contact = res || []
      })
      this.ruleForm.eventDate = null
      this.ruleForm.contactId = null
      this.ruleForm.eventName = ''
      this.ruleForm.cashGift = null
      this.ruleForm.returnGiftDate = null
      this.ruleForm.returnCashGift = null
      this.ruleForm.remark = ''
      this.visible = true
    },
    onFormSubmitClick(formName) {
      const _this = this
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          favorDealingHistoryService.createFavorDealingHistory(_this.ruleForm).then(res => {
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
      this.$refs[formName].resetFields()
    }
  }
}
</script>
