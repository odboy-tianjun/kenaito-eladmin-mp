/*
 * Kruise
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1.21.1
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package cn.odboy.model.openkruise;

import com.google.gson.annotations.SerializedName;
import io.kubernetes.client.openapi.models.V1LabelSelector;
import io.kubernetes.client.openapi.models.V1PersistentVolumeClaim;
import io.kubernetes.client.openapi.models.V1PodTemplateSpec;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * StatefulSetSpec defines the desired state of StatefulSet
 */
@ApiModel(description = "StatefulSetSpec defines the desired state of StatefulSet")
@Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-07-21T15:55:09.049+08:00")
public class KruiseAppsV1beta1StatefulSetSpec {
    @SerializedName("lifecycle")
    private KruiseAppsPubLifecycle lifecycle = null;
    @SerializedName("persistentVolumeClaimRetentionPolicy")
    private KruiseAppsV1beta1StatefulSetPersistentVolumeClaimRetentionPolicy persistentVolumeClaimRetentionPolicy = null;
    @SerializedName("podManagementPolicy")
    private String podManagementPolicy = null;
    @SerializedName("replicas")
    private Integer replicas = null;
    @SerializedName("reserveOrdinals")
    private List<Integer> reserveOrdinals = null;
    @SerializedName("revisionHistoryLimit")
    private Integer revisionHistoryLimit = null;
    @SerializedName("scaleStrategy")
    private KruiseAppsV1beta1StatefulSetScaleStrategy scaleStrategy = null;
    @SerializedName("selector")
    private V1LabelSelector selector = null;
    @SerializedName("serviceName")
    private String serviceName = null;
    @SerializedName("template")
    private V1PodTemplateSpec template = null;
    @SerializedName("updateStrategy")
    private KruiseAppsV1beta1StatefulSetUpdateStrategy updateStrategy = null;
    @SerializedName("volumeClaimTemplates")
    private List<V1PersistentVolumeClaim> volumeClaimTemplates = null;

    public KruiseAppsV1beta1StatefulSetSpec lifecycle(KruiseAppsPubLifecycle lifecycle) {
        this.lifecycle = lifecycle;
        return this;
    }

    /**
     * Get lifecycle
     *
     * @return lifecycle
     **/
    @ApiModelProperty(value = "")
    public KruiseAppsPubLifecycle getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(KruiseAppsPubLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public KruiseAppsV1beta1StatefulSetSpec persistentVolumeClaimRetentionPolicy(KruiseAppsV1beta1StatefulSetPersistentVolumeClaimRetentionPolicy persistentVolumeClaimRetentionPolicy) {
        this.persistentVolumeClaimRetentionPolicy = persistentVolumeClaimRetentionPolicy;
        return this;
    }

    /**
     * Get persistentVolumeClaimRetentionPolicy
     *
     * @return persistentVolumeClaimRetentionPolicy
     **/
    @ApiModelProperty(value = "")
    public KruiseAppsV1beta1StatefulSetPersistentVolumeClaimRetentionPolicy getPersistentVolumeClaimRetentionPolicy() {
        return persistentVolumeClaimRetentionPolicy;
    }

    public void setPersistentVolumeClaimRetentionPolicy(KruiseAppsV1beta1StatefulSetPersistentVolumeClaimRetentionPolicy persistentVolumeClaimRetentionPolicy) {
        this.persistentVolumeClaimRetentionPolicy = persistentVolumeClaimRetentionPolicy;
    }

    public KruiseAppsV1beta1StatefulSetSpec podManagementPolicy(String podManagementPolicy) {
        this.podManagementPolicy = podManagementPolicy;
        return this;
    }

    /**
     * podManagementPolicy controls how pods are created during initial scale up, when replacing pods on nodes, or when scaling down. The default policy is &#x60;OrderedReady&#x60;, where pods are created in increasing order (pod-0, then pod-1, etc) and the controller will wait until each pod is ready before continuing. When scaling down, the pods are removed in the opposite order. The alternative policy is &#x60;Parallel&#x60; which will create pods in parallel to match the desired scale without waiting, and on scale down will delete all pods at once.
     *
     * @return podManagementPolicy
     **/
    @ApiModelProperty(value = "podManagementPolicy controls how pods are created during initial scale up, when replacing pods on nodes, or when scaling down. The default policy is `OrderedReady`, where pods are created in increasing order (pod-0, then pod-1, etc) and the controller will wait until each pod is ready before continuing. When scaling down, the pods are removed in the opposite order. The alternative policy is `Parallel` which will create pods in parallel to match the desired scale without waiting, and on scale down will delete all pods at once.")
    public String getPodManagementPolicy() {
        return podManagementPolicy;
    }

    public void setPodManagementPolicy(String podManagementPolicy) {
        this.podManagementPolicy = podManagementPolicy;
    }

    public KruiseAppsV1beta1StatefulSetSpec replicas(Integer replicas) {
        this.replicas = replicas;
        return this;
    }

    /**
     * replicas is the desired number of replicas of the given Template. These are replicas in the sense that they are instantiations of the same Template, but individual replicas also have a consistent identity. If unspecified, defaults to 1. TODO: Consider a rename of this field.
     *
     * @return replicas
     **/
    @ApiModelProperty(value = "replicas is the desired number of replicas of the given Template. These are replicas in the sense that they are instantiations of the same Template, but individual replicas also have a consistent identity. If unspecified, defaults to 1. TODO: Consider a rename of this field.")
    public Integer getReplicas() {
        return replicas;
    }

    public void setReplicas(Integer replicas) {
        this.replicas = replicas;
    }

    public KruiseAppsV1beta1StatefulSetSpec reserveOrdinals(List<Integer> reserveOrdinals) {
        this.reserveOrdinals = reserveOrdinals;
        return this;
    }

    public KruiseAppsV1beta1StatefulSetSpec addReserveOrdinalsItem(Integer reserveOrdinalsItem) {
        if (this.reserveOrdinals == null) {
            this.reserveOrdinals = new ArrayList<Integer>();
        }
        this.reserveOrdinals.add(reserveOrdinalsItem);
        return this;
    }

    /**
     * reserveOrdinals controls the ordinal numbers that should be reserved, and the replicas will always be the expectation number of running Pods. For a sts with replicas&#x3D;3 and its Pods in [0, 1, 2]: - If you want to migrate Pod-1 and reserve this ordinal, just set spec.reserveOrdinal to [1].   Then controller will delete Pod-1 and create Pod-3 (existing Pods will be [0, 2, 3]) - If you just want to delete Pod-1, you should set spec.reserveOrdinal to [1] and spec.replicas to 2.   Then controller will delete Pod-1 (existing Pods will be [0, 2])
     *
     * @return reserveOrdinals
     **/
    @ApiModelProperty(value = "reserveOrdinals controls the ordinal numbers that should be reserved, and the replicas will always be the expectation number of running Pods. For a sts with replicas=3 and its Pods in [0, 1, 2]: - If you want to migrate Pod-1 and reserve this ordinal, just set spec.reserveOrdinal to [1].   Then controller will delete Pod-1 and create Pod-3 (existing Pods will be [0, 2, 3]) - If you just want to delete Pod-1, you should set spec.reserveOrdinal to [1] and spec.replicas to 2.   Then controller will delete Pod-1 (existing Pods will be [0, 2])")
    public List<Integer> getReserveOrdinals() {
        return reserveOrdinals;
    }

    public void setReserveOrdinals(List<Integer> reserveOrdinals) {
        this.reserveOrdinals = reserveOrdinals;
    }

    public KruiseAppsV1beta1StatefulSetSpec revisionHistoryLimit(Integer revisionHistoryLimit) {
        this.revisionHistoryLimit = revisionHistoryLimit;
        return this;
    }

    /**
     * revisionHistoryLimit is the maximum number of revisions that will be maintained in the StatefulSet&#39;s revision history. The revision history consists of all revisions not represented by a currently applied StatefulSetSpec version. The default value is 10.
     *
     * @return revisionHistoryLimit
     **/
    @ApiModelProperty(value = "revisionHistoryLimit is the maximum number of revisions that will be maintained in the StatefulSet's revision history. The revision history consists of all revisions not represented by a currently applied StatefulSetSpec version. The default value is 10.")
    public Integer getRevisionHistoryLimit() {
        return revisionHistoryLimit;
    }

    public void setRevisionHistoryLimit(Integer revisionHistoryLimit) {
        this.revisionHistoryLimit = revisionHistoryLimit;
    }

    public KruiseAppsV1beta1StatefulSetSpec scaleStrategy(KruiseAppsV1beta1StatefulSetScaleStrategy scaleStrategy) {
        this.scaleStrategy = scaleStrategy;
        return this;
    }

    /**
     * Get scaleStrategy
     *
     * @return scaleStrategy
     **/
    @ApiModelProperty(value = "")
    public KruiseAppsV1beta1StatefulSetScaleStrategy getScaleStrategy() {
        return scaleStrategy;
    }

    public void setScaleStrategy(KruiseAppsV1beta1StatefulSetScaleStrategy scaleStrategy) {
        this.scaleStrategy = scaleStrategy;
    }

    public KruiseAppsV1beta1StatefulSetSpec selector(V1LabelSelector selector) {
        this.selector = selector;
        return this;
    }

    /**
     * Get selector
     *
     * @return selector
     **/
    @ApiModelProperty(required = true, value = "")
    public V1LabelSelector getSelector() {
        return selector;
    }

    public void setSelector(V1LabelSelector selector) {
        this.selector = selector;
    }

    public KruiseAppsV1beta1StatefulSetSpec serviceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    /**
     * serviceName is the name of the service that governs this StatefulSet. This service must exist before the StatefulSet, and is responsible for the network identity of the set. Pods get DNS/hostnames that follow the pattern: pod-specific-string.serviceName.default.svc.cluster.local where \&quot;pod-specific-string\&quot; is managed by the StatefulSet controller.
     *
     * @return serviceName
     **/
    @ApiModelProperty(value = "serviceName is the name of the service that governs this StatefulSet. This service must exist before the StatefulSet, and is responsible for the network identity of the set. Pods get DNS/hostnames that follow the pattern: pod-specific-string.serviceName.default.svc.cluster.local where \"pod-specific-string\" is managed by the StatefulSet controller.")
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public KruiseAppsV1beta1StatefulSetSpec template(V1PodTemplateSpec template) {
        this.template = template;
        return this;
    }

    /**
     * template is the object that describes the pod that will be created if insufficient replicas are detected. Each pod stamped out by the StatefulSet will fulfill this Template, but have a unique identity from the rest of the StatefulSet.
     *
     * @return template
     **/
    @ApiModelProperty(required = true, value = "template is the object that describes the pod that will be created if insufficient replicas are detected. Each pod stamped out by the StatefulSet will fulfill this Template, but have a unique identity from the rest of the StatefulSet.")
    public V1PodTemplateSpec getTemplate() {
        return template;
    }

    public void setTemplate(V1PodTemplateSpec template) {
        this.template = template;
    }

    public KruiseAppsV1beta1StatefulSetSpec updateStrategy(KruiseAppsV1beta1StatefulSetUpdateStrategy updateStrategy) {
        this.updateStrategy = updateStrategy;
        return this;
    }

    /**
     * Get updateStrategy
     *
     * @return updateStrategy
     **/
    @ApiModelProperty(value = "")
    public KruiseAppsV1beta1StatefulSetUpdateStrategy getUpdateStrategy() {
        return updateStrategy;
    }

    public void setUpdateStrategy(KruiseAppsV1beta1StatefulSetUpdateStrategy updateStrategy) {
        this.updateStrategy = updateStrategy;
    }

    public KruiseAppsV1beta1StatefulSetSpec volumeClaimTemplates(List<V1PersistentVolumeClaim> volumeClaimTemplates) {
        this.volumeClaimTemplates = volumeClaimTemplates;
        return this;
    }

    /**
     * volumeClaimTemplates is a list of claims that pods are allowed to reference. The StatefulSet controller is responsible for mapping network identities to claims in a way that maintains the identity of a pod. Every claim in this list must have at least one matching (by name) volumeMount in one container in the template. A claim in this list takes precedence over any volumes in the template, with the same name. TODO: Define the behavior if a claim already exists with the same name.
     *
     * @return volumeClaimTemplates
     **/
    @ApiModelProperty(value = "volumeClaimTemplates is a list of claims that pods are allowed to reference. The StatefulSet controller is responsible for mapping network identities to claims in a way that maintains the identity of a pod. Every claim in this list must have at least one matching (by name) volumeMount in one container in the template. A claim in this list takes precedence over any volumes in the template, with the same name. TODO: Define the behavior if a claim already exists with the same name.")
    public List<V1PersistentVolumeClaim> getVolumeClaimTemplates() {
        return volumeClaimTemplates;
    }

    public void setVolumeClaimTemplates(List<V1PersistentVolumeClaim> volumeClaimTemplates) {
        this.volumeClaimTemplates = volumeClaimTemplates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KruiseAppsV1beta1StatefulSetSpec)) {
            return false;
        }
        KruiseAppsV1beta1StatefulSetSpec ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec = (KruiseAppsV1beta1StatefulSetSpec) o;
        return Objects.equals(this.lifecycle, ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec.lifecycle) &&
                Objects.equals(this.persistentVolumeClaimRetentionPolicy, ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec.persistentVolumeClaimRetentionPolicy) &&
                Objects.equals(this.podManagementPolicy, ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec.podManagementPolicy) &&
                Objects.equals(this.replicas, ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec.replicas) &&
                Objects.equals(this.reserveOrdinals, ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec.reserveOrdinals) &&
                Objects.equals(this.revisionHistoryLimit, ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec.revisionHistoryLimit) &&
                Objects.equals(this.scaleStrategy, ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec.scaleStrategy) &&
                Objects.equals(this.selector, ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec.selector) &&
                Objects.equals(this.serviceName, ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec.serviceName) &&
                Objects.equals(this.template, ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec.template) &&
                Objects.equals(this.updateStrategy, ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec.updateStrategy) &&
                Objects.equals(this.volumeClaimTemplates, ioKruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec.volumeClaimTemplates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lifecycle, persistentVolumeClaimRetentionPolicy, podManagementPolicy, replicas, reserveOrdinals, revisionHistoryLimit, scaleStrategy, selector, serviceName, template, updateStrategy, volumeClaimTemplates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KruiseAppsV1alpha1UnitedDeploymentSpecTemplateAdvancedStatefulSetTemplateSpec {\n");
        sb.append("    lifecycle: ").append(toIndentedString(lifecycle)).append("\n");
        sb.append("    persistentVolumeClaimRetentionPolicy: ").append(toIndentedString(persistentVolumeClaimRetentionPolicy)).append("\n");
        sb.append("    podManagementPolicy: ").append(toIndentedString(podManagementPolicy)).append("\n");
        sb.append("    replicas: ").append(toIndentedString(replicas)).append("\n");
        sb.append("    reserveOrdinals: ").append(toIndentedString(reserveOrdinals)).append("\n");
        sb.append("    revisionHistoryLimit: ").append(toIndentedString(revisionHistoryLimit)).append("\n");
        sb.append("    scaleStrategy: ").append(toIndentedString(scaleStrategy)).append("\n");
        sb.append("    selector: ").append(toIndentedString(selector)).append("\n");
        sb.append("    serviceName: ").append(toIndentedString(serviceName)).append("\n");
        sb.append("    template: ").append(toIndentedString(template)).append("\n");
        sb.append("    updateStrategy: ").append(toIndentedString(updateStrategy)).append("\n");
        sb.append("    volumeClaimTemplates: ").append(toIndentedString(volumeClaimTemplates)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}