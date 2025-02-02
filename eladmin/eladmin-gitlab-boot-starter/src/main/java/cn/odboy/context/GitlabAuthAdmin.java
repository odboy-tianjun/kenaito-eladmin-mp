/*
 *  Copyright 2022-2025 Tian Jun
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cn.odboy.context;

import org.gitlab4j.api.GitLabApi;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * gitlab 客户端认证
 *
 * @author odboy
 * @date 2025-01-12
 */
public class GitlabAuthAdmin {
    @Autowired
    private GitlabProperties properties;

    public GitLabApi auth() {
        return new GitLabApi(properties.getUrl(), properties.getAccessToken());
    }
}
