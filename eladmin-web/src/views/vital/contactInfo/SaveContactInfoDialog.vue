<template>
  <el-dialog title="添加联系人" :visible.sync="visible" width="20%">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules">
      <el-form-item label="姓名" :label-width="formLabelWidth" prop="realName">
        <el-input v-model.trim="ruleForm.realName" autocomplete="off" style="width: 100%" />
      </el-form-item>
      <el-form-item label="称谓" :label-width="formLabelWidth" prop="nickName">
        <el-input v-model.trim="ruleForm.nickName" autocomplete="off" style="width: 100%" />
      </el-form-item>
      <el-form-item label="所属分组" :label-width="formLabelWidth" prop="groupName">
        <el-input v-model.trim="ruleForm.groupName" autocomplete="off" style="width: 100%" />
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
import { MessageUtil } from '@/utils/elementuiTool'

export default {
  data() {
    return {
      formLabelWidth: '100px',
      visible: false,
      ruleForm: {
        realName: '',
        nickName: '',
        groupName: ''
      },
      rules: {
        realName: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
  },
  methods: {
    show() {
      this.ruleForm.realName = ''
      this.ruleForm.nickName = ''
      this.ruleForm.groupName = ''
      this.visible = true
    },
    onFormSubmitClick(formName) {
      const _this = this
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          contactInfoService.createContactInfo(_this.ruleForm).then(res => {
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
