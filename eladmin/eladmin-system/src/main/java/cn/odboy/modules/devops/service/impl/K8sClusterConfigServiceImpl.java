package cn.odboy.modules.devops.service.impl;

import cn.hutool.core.lang.Dict;
import cn.odboy.infra.mybatisplus.impl.EasyServiceImpl;
import cn.odboy.model.MetaOption;
import cn.odboy.modules.devops.domain.K8sClusterConfig;
import cn.odboy.modules.devops.mapper.K8sClusterConfigMapper;
import cn.odboy.modules.devops.service.K8sClusterConfigService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * k8s集群配置 服务实现类
 * </p>
 *
 * @author codegen
 * @since 2025-01-12
 */
@Service
public class K8sClusterConfigServiceImpl extends EasyServiceImpl<K8sClusterConfigMapper, K8sClusterConfig> implements K8sClusterConfigService {
    @Override
    public List<MetaOption> listClusterCodes() {
        return quickList(null).stream()
                .map(m -> MetaOption.builder()
                        .label(m.getClusterName())
                        .value(m.getClusterCode())
                        .ext(Dict.create()
                                .set("envCode", m.getEnvCode())
                                .set("status", m.getStatus())
                        )
                        .build())
                .collect(Collectors.toList());
    }
}