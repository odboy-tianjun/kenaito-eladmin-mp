package me.zhengjie.repository;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.openapi.models.V1ServiceBuilder;
import io.kubernetes.client.openapi.models.V1ServicePort;
import io.kubernetes.client.util.Yaml;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.constant.K8sActionReasonCodeEnum;
import me.zhengjie.constant.K8sAppLabelEnum;
import me.zhengjie.context.K8sClientAdmin;
import me.zhengjie.infra.exception.BadRequestException;
import me.zhengjie.model.response.K8sResource;
import me.zhengjie.model.request.K8sService;
import me.zhengjie.util.K8sDryRunUtil;
import me.zhengjie.util.K8sResourceNameUtil;
import me.zhengjie.util.ValidationUtil;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Kubernetes Service Repository
 *
 * @author odboy
 * @date 2024-09-26
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class K8sServiceRepository {
    private final K8sClientAdmin k8SClientAdmin;

    /**
     * 创建Service
     *
     * @param args /
     */
    public V1Service createService(K8sService.CreateArgs args) {
        ValidationUtil.validate(args);
        try {
            String serviceName = K8sResourceNameUtil.getServiceName(args.getAppName(), k8SClientAdmin.getEnvCode(args.getClusterCode()));
            args.getLabelSelector().put(K8sAppLabelEnum.AppName.getCode(), args.getAppName());
            // 构建service的yaml对象
            V1Service service = new V1ServiceBuilder()
                    .withNewMetadata()
                    .withName(serviceName)
                    .withNamespace(args.getAppName())
                    .withAnnotations(args.getAnnotations())
                    .endMetadata()
                    .withNewSpec()
                    .withPorts(new V1ServicePort().protocol("TCP").port(args.getPort()).targetPort(new IntOrString(args.getTargetPort())))
                    .withSelector(args.getLabelSelector())
                    .endSpec()
                    .build();
            ApiClient apiClient = k8SClientAdmin.getClient(args.getClusterCode());
            // Deployment and StatefulSet is defined in apps/v1, so you should use AppsV1Api instead of CoreV1API
            CoreV1Api api = new CoreV1Api(apiClient);
            return api.createNamespacedService(args.getAppName(), service, null, K8sDryRunUtil.transferState(args.getDryRun()), null);
        } catch (ApiException e) {
            String responseBody = e.getResponseBody();
            log.error("创建Service失败: {}", responseBody, e);
            K8sResource.ActionExceptionBody actionExceptionBody = JSON.parseObject(responseBody, K8sResource.ActionExceptionBody.class);
            if (actionExceptionBody != null) {
                throw new BadRequestException("创建Service失败, 原因：" + actionExceptionBody.getReason());
            }
            throw new BadRequestException("创建Service失败");
        } catch (Exception e) {
            log.error("创建Service失败:", e);
            throw new BadRequestException("创建Service失败");
        }
    }

    /**
     * 删除Service
     *
     * @param args /
     */
    public void deleteService(K8sService.DeleteArgs args) {
        ValidationUtil.validate(args);
        try {
            ApiClient apiClient = k8SClientAdmin.getClient(args.getClusterCode());
            CoreV1Api api = new CoreV1Api(apiClient);
            api.deleteNamespacedService(K8sResourceNameUtil.getServiceName(args.getAppName(), k8SClientAdmin.getEnvCode(args.getClusterCode())), args.getAppName(), null, K8sDryRunUtil.transferState(args.getDryRun()), null, null, null, null);
        } catch (ApiException e) {
            String responseBody = e.getResponseBody();
            log.error("删除Service失败: {}", responseBody, e);
            K8sResource.ActionExceptionBody actionExceptionBody = JSON.parseObject(responseBody, K8sResource.ActionExceptionBody.class);
            if (actionExceptionBody != null) {
                throw new BadRequestException("删除Service失败, 原因：" + actionExceptionBody.getReason());
            }
            throw new BadRequestException("删除Service失败");
        } catch (Exception e) {
            log.error("删除Service失败:", e);
            throw new BadRequestException("删除Service失败");
        }
    }

    /**
     * 根据appName获取Service
     *
     * @return /
     */
    public V1Service describeServiceByAppName(String clusterCode, String appName) {
        Assert.notEmpty(clusterCode, "集群编码不能为空");
        Assert.notEmpty(appName, "应用名称不能为空");
        try {
            ApiClient apiClient = k8SClientAdmin.getClient(clusterCode);
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            String serviceName = K8sResourceNameUtil.getServiceName(appName, clusterCode);
            return coreV1Api.readNamespacedService(serviceName, appName, "false", null, null);
        } catch (ApiException e) {
            String responseBody = e.getResponseBody();
            K8sResource.ActionExceptionBody actionExceptionBody = JSON.parseObject(responseBody, K8sResource.ActionExceptionBody.class);
            if (actionExceptionBody != null) {
                if (actionExceptionBody.getReason().contains(K8sActionReasonCodeEnum.NotFound.getCode())) {
                    return null;
                }
                log.error("根据appName获取Service失败: {}", responseBody, e);
                throw new BadRequestException("根据appName获取Service失败, 原因：" + actionExceptionBody.getReason());
            }
            throw new BadRequestException("根据appName获取Service失败");
        } catch (Exception e) {
            log.error("根据appName获取Service失败:", e);
            throw new BadRequestException("根据appName获取Service失败");
        }
    }

    /**
     * 根据name获取Service
     *
     * @return /
     */
    public V1Service describeServiceByName(String clusterCode, String name, String namespace) {
        Assert.notEmpty(clusterCode, "集群编码不能为空");
        Assert.notEmpty(name, "名称不能为空");
        Assert.notEmpty(namespace, "命名空间不能为空");
        try {
            ApiClient apiClient = k8SClientAdmin.getClient(clusterCode);
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            return coreV1Api.readNamespacedService(name, namespace, "false", null, null);
        } catch (ApiException e) {
            String responseBody = e.getResponseBody();
            K8sResource.ActionExceptionBody actionExceptionBody = JSON.parseObject(responseBody, K8sResource.ActionExceptionBody.class);
            if (actionExceptionBody != null) {
                if (actionExceptionBody.getReason().contains(K8sActionReasonCodeEnum.NotFound.getCode())) {
                    return null;
                }
                log.error("根据name获取Service失败: {}", responseBody, e);
                throw new BadRequestException("根据name获取Service失败, 原因：" + actionExceptionBody.getReason());
            }
            throw new BadRequestException("根据name获取Service失败");
        } catch (Exception e) {
            log.error("根据name获取Service失败:", e);
            throw new BadRequestException("根据name获取Service失败");
        }
    }

    public V1Service loadServiceFromYaml(K8sService.LoadFromYamlArgs args) {
        ValidationUtil.validate(args);
        try {
            V1Service v1Service = Yaml.loadAs(args.getYamlContent(), V1Service.class);
            ApiClient apiClient = k8SClientAdmin.getClient(args.getClusterCode());
            CoreV1Api coreV1Api = new CoreV1Api(apiClient);
            String serviceName = Objects.requireNonNull(v1Service.getMetadata()).getName();
            String namespace = Objects.requireNonNull(v1Service.getMetadata()).getNamespace();
            V1Service localService = describeServiceByName(args.getClusterCode(), serviceName, namespace);
            if (localService == null) {
                coreV1Api.createNamespacedService(namespace, v1Service, "false", K8sDryRunUtil.transferState(args.getDryRun()), null);
            } else {
                coreV1Api.replaceNamespacedService(serviceName, namespace, v1Service, "false", K8sDryRunUtil.transferState(args.getDryRun()), null);
            }
            return v1Service;
        } catch (ApiException e) {
            String responseBody = e.getResponseBody();
            log.error("从yml加载Service失败: {}", responseBody, e);
            K8sResource.ActionExceptionBody actionExceptionBody = JSON.parseObject(responseBody, K8sResource.ActionExceptionBody.class);
            if (actionExceptionBody != null) {
                throw new BadRequestException("从yml加载Service失败, 原因：" + actionExceptionBody.getReason());
            }
            throw new BadRequestException("从yml加载Service失败");
        } catch (Exception e) {
            log.error("从yml加载Service失败:", e);
            throw new BadRequestException("从yml加载Service失败");
        }
    }
}