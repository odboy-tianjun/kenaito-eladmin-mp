///*
// *  Copyright 2022-2025 Tian Jun
// *
// *  Licensed under the Apache License, Version 2.0 (the "License");
// *  you may not use this file except in compliance with the License.
// *  You may obtain a copy of the License at
// *
// *  http://www.apache.org/licenses/LICENSE-2.0
// *
// *  Unless required by applicable law or agreed to in writing, software
// *  distributed under the License is distributed on an "AS IS" BASIS,
// *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  See the License for the specific language governing permissions and
// *  limitations under the License.
// */
//package cn.odboy.infra.gitlab;
//
//import cn.odboy.constant.GitlabSystemHookEventTypeEnum;
//import cn.odboy.infra.exception.BadRequestException;
//import cn.odboy.infra.security.annotation.AnonymousAccess;
//import cn.odboy.model.GitlabSystemHook;
//import com.alibaba.fastjson.JSONObject;
//import org.gitlab4j.api.webhook.MergeRequestEvent;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Gitlab事件回调
// *
// * @author odboy
// * @date 2025-01-17
// */
//@RestController
//@RequestMapping("/openApi/gitlab/webhook")
//public class GitlabWebHookCallback {
//    @AnonymousAccess
//    @RequestMapping("/callback")
//    public void doCallback(@RequestBody JSONObject body) {
//        if (body == null) {
//            throw new BadRequestException("回调内容不能为空");
//        }
////        System.err.println(body);
//        String eventName = body.getString("event_name");
//        if (eventName == null) {
//            eventName = body.getString("event_type");
//        }
//        if (GitlabSystemHookEventTypeEnum.RepositoryUpdate.getCode().equals(eventName)) {
//            GitlabSystemHook.ProjectUpdateEvent projectUpdateEvent = body.toJavaObject(GitlabSystemHook.ProjectUpdateEvent.class);
//            System.err.println("=====================GitlabProjectUpdateEvent====================");
//            System.err.println(projectUpdateEvent);
//            return;
//        }
//        if (GitlabSystemHookEventTypeEnum.Push.getCode().equals(eventName)) {
//            GitlabSystemHook.PushCommitsToProjectEvent pushCommitsToProjectEvent = body.toJavaObject(GitlabSystemHook.PushCommitsToProjectEvent.class);
//            System.err.println("=====================GitlabPushCommitsToProjectEvent====================");
//            System.err.println(pushCommitsToProjectEvent);
//            return;
//        }
//        if (GitlabSystemHookEventTypeEnum.MergeRequest.getCode().equals(eventName)) {
//            GitlabSystemHook.MergeRequestEvent mergeRequestEvent = body.toJavaObject(GitlabSystemHook.MergeRequestEvent.class);
//            MergeRequestEvent.ObjectAttributes objectAttributes = mergeRequestEvent.getObjectAttributes();
//            Long iid = objectAttributes.getIid();
//            String detailedMergeStatus = objectAttributes.getDetailedMergeStatus();
//            String state = objectAttributes.getState();
//            String title = objectAttributes.getTitle();
//            Long projectId = objectAttributes.getSourceProjectId();
//            String sourceBranch = objectAttributes.getSourceBranch();
//            String targetBranch = objectAttributes.getTargetBranch();
//            System.err.println("=====================GitlabMergeRequestEvent====================");
//            System.err.println("iid=" + iid);
//            System.err.println("detailedMergeStatus=" + detailedMergeStatus);
//            System.err.println("title=" + title);
//            System.err.println("state=" + state);
//            System.err.println("projectId=" + projectId);
//            System.err.println("sourceBranch=" + sourceBranch);
//            System.err.println("targetBranch=" + targetBranch);
//        }
//    }
//}
