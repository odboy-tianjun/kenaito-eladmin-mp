package me.zhengjie.repository;

import cn.hutool.core.lang.Assert;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiV2DepartmentListsubRequest;
import com.dingtalk.api.response.OapiV2DepartmentListsubResponse;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.context.DingtalkAuthAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 部门管理 2.0
 *
 * @author odboy
 * @date 2025-01-14
 */
@Slf4j
@Component
public class DingtalkDepartmentRepository {
    @Autowired
    private DingtalkAuthAdmin dingtalkAuthAdmin;

    /**
     * 获取部门列表
     *
     * @param deptId 部门Id
     * @return /
     */
    public List<OapiV2DepartmentListsubResponse.DeptBaseResponse> listDepartmentSubs(Long deptId) {
        Assert.notNull(deptId, "部门Id不能为空");
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/listsub");
            OapiV2DepartmentListsubRequest req = new OapiV2DepartmentListsubRequest();
            req.setDeptId(deptId);
            req.setLanguage("zh_CN");
            OapiV2DepartmentListsubResponse rsp = client.execute(req, dingtalkAuthAdmin.auth());
            log.info("获取部门列表成功, deptId={}", deptId);
            return rsp.getResult();
        } catch (Exception e) {
            log.info("获取部门列表失败, deptId={}", deptId, e);
            return new ArrayList<>();
        }
    }

    public void listAllSubDepartments(long deptId, Consumer<List<OapiV2DepartmentListsubResponse.DeptBaseResponse>> consumer) {
        List<OapiV2DepartmentListsubResponse.DeptBaseResponse> deptBaseResponses = listDepartmentSubs(deptId);
        if (deptBaseResponses.isEmpty()) {
            return;
        }
        consumer.accept(deptBaseResponses);
        for (OapiV2DepartmentListsubResponse.DeptBaseResponse deptBaseRespons : deptBaseResponses) {
            listAllSubDepartments(deptBaseRespons.getDeptId(), consumer);
        }
    }
}