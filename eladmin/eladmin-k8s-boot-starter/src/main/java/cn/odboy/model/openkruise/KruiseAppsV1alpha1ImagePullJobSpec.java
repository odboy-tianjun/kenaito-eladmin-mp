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
import io.kubernetes.client.custom.IntOrString;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ImagePullJobSpec defines the desired state of ImagePullJob
 */
@ApiModel(description = "ImagePullJobSpec defines the desired state of ImagePullJob")
@Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-07-21T15:55:09.049+08:00")
public class KruiseAppsV1alpha1ImagePullJobSpec {
    @SerializedName("completionPolicy")
    private KruiseAppsV1alpha1CompletionPolicy completionPolicy = null;
    @SerializedName("image")
    private String image = null;
    @SerializedName("parallelism")
    private IntOrString parallelism = null;
    @SerializedName("podSelector")
    private KruiseAppsV1alpha1ImagePullJobPodSelector podSelector = null;
    @SerializedName("pullPolicy")
    private KruiseAppsV1alpha1PullPolicy pullPolicy = null;
    @SerializedName("pullSecrets")
    private List<String> pullSecrets = null;
    @SerializedName("sandboxConfig")
    private KruiseAppsV1alpha1SandboxConfig sandboxConfig = null;
    @SerializedName("selector")
    private KruiseAppsV1alpha1ImagePullJobNodeSelector selector = null;

    public KruiseAppsV1alpha1ImagePullJobSpec completionPolicy(KruiseAppsV1alpha1CompletionPolicy completionPolicy) {
        this.completionPolicy = completionPolicy;
        return this;
    }

    /**
     * Get completionPolicy
     *
     * @return completionPolicy
     **/
    @ApiModelProperty(required = true, value = "")
    public KruiseAppsV1alpha1CompletionPolicy getCompletionPolicy() {
        return completionPolicy;
    }

    public void setCompletionPolicy(KruiseAppsV1alpha1CompletionPolicy completionPolicy) {
        this.completionPolicy = completionPolicy;
    }

    public KruiseAppsV1alpha1ImagePullJobSpec image(String image) {
        this.image = image;
        return this;
    }

    /**
     * Image is the image to be pulled by the job
     *
     * @return image
     **/
    @ApiModelProperty(required = true, value = "Image is the image to be pulled by the job")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public KruiseAppsV1alpha1ImagePullJobSpec parallelism(IntOrString parallelism) {
        this.parallelism = parallelism;
        return this;
    }

    /**
     * Parallelism is the requested parallelism, it can be set to any non-negative value. If it is unspecified, it defaults to 1. If it is specified as 0, then the Job is effectively paused until it is increased.
     *
     * @return parallelism
     **/
    @ApiModelProperty(value = "Parallelism is the requested parallelism, it can be set to any non-negative value. If it is unspecified, it defaults to 1. If it is specified as 0, then the Job is effectively paused until it is increased.")
    public IntOrString getParallelism() {
        return parallelism;
    }

    public void setParallelism(IntOrString parallelism) {
        this.parallelism = parallelism;
    }

    public KruiseAppsV1alpha1ImagePullJobSpec podSelector(KruiseAppsV1alpha1ImagePullJobPodSelector podSelector) {
        this.podSelector = podSelector;
        return this;
    }

    /**
     * Get podSelector
     *
     * @return podSelector
     **/
    @ApiModelProperty(value = "")
    public KruiseAppsV1alpha1ImagePullJobPodSelector getPodSelector() {
        return podSelector;
    }

    public void setPodSelector(KruiseAppsV1alpha1ImagePullJobPodSelector podSelector) {
        this.podSelector = podSelector;
    }

    public KruiseAppsV1alpha1ImagePullJobSpec pullPolicy(KruiseAppsV1alpha1PullPolicy pullPolicy) {
        this.pullPolicy = pullPolicy;
        return this;
    }

    /**
     * Get pullPolicy
     *
     * @return pullPolicy
     **/
    @ApiModelProperty(value = "")
    public KruiseAppsV1alpha1PullPolicy getPullPolicy() {
        return pullPolicy;
    }

    public void setPullPolicy(KruiseAppsV1alpha1PullPolicy pullPolicy) {
        this.pullPolicy = pullPolicy;
    }

    public KruiseAppsV1alpha1ImagePullJobSpec pullSecrets(List<String> pullSecrets) {
        this.pullSecrets = pullSecrets;
        return this;
    }

    public KruiseAppsV1alpha1ImagePullJobSpec addPullSecretsItem(String pullSecretsItem) {
        if (this.pullSecrets == null) {
            this.pullSecrets = new ArrayList<String>();
        }
        this.pullSecrets.add(pullSecretsItem);
        return this;
    }

    /**
     * ImagePullSecrets is an optional list of references to secrets in the same namespace to use for pulling the image. If specified, these secrets will be passed to individual puller implementations for them to use.  For example, in the case of docker, only DockerConfig type secrets are honored.
     *
     * @return pullSecrets
     **/
    @ApiModelProperty(value = "ImagePullSecrets is an optional list of references to secrets in the same namespace to use for pulling the image. If specified, these secrets will be passed to individual puller implementations for them to use.  For example, in the case of docker, only DockerConfig type secrets are honored.")
    public List<String> getPullSecrets() {
        return pullSecrets;
    }

    public void setPullSecrets(List<String> pullSecrets) {
        this.pullSecrets = pullSecrets;
    }

    public KruiseAppsV1alpha1ImagePullJobSpec sandboxConfig(KruiseAppsV1alpha1SandboxConfig sandboxConfig) {
        this.sandboxConfig = sandboxConfig;
        return this;
    }

    /**
     * Get sandboxConfig
     *
     * @return sandboxConfig
     **/
    @ApiModelProperty(value = "")
    public KruiseAppsV1alpha1SandboxConfig getSandboxConfig() {
        return sandboxConfig;
    }

    public void setSandboxConfig(KruiseAppsV1alpha1SandboxConfig sandboxConfig) {
        this.sandboxConfig = sandboxConfig;
    }

    public KruiseAppsV1alpha1ImagePullJobSpec selector(KruiseAppsV1alpha1ImagePullJobNodeSelector selector) {
        this.selector = selector;
        return this;
    }

    /**
     * Get selector
     *
     * @return selector
     **/
    @ApiModelProperty(value = "")
    public KruiseAppsV1alpha1ImagePullJobNodeSelector getSelector() {
        return selector;
    }

    public void setSelector(KruiseAppsV1alpha1ImagePullJobNodeSelector selector) {
        this.selector = selector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KruiseAppsV1alpha1ImagePullJobSpec)) {
            return false;
        }
        KruiseAppsV1alpha1ImagePullJobSpec ioKruiseAppsV1alpha1ImagePullJobSpec = (KruiseAppsV1alpha1ImagePullJobSpec) o;
        return Objects.equals(this.completionPolicy, ioKruiseAppsV1alpha1ImagePullJobSpec.completionPolicy) &&
                Objects.equals(this.image, ioKruiseAppsV1alpha1ImagePullJobSpec.image) &&
                Objects.equals(this.parallelism, ioKruiseAppsV1alpha1ImagePullJobSpec.parallelism) &&
                Objects.equals(this.podSelector, ioKruiseAppsV1alpha1ImagePullJobSpec.podSelector) &&
                Objects.equals(this.pullPolicy, ioKruiseAppsV1alpha1ImagePullJobSpec.pullPolicy) &&
                Objects.equals(this.pullSecrets, ioKruiseAppsV1alpha1ImagePullJobSpec.pullSecrets) &&
                Objects.equals(this.sandboxConfig, ioKruiseAppsV1alpha1ImagePullJobSpec.sandboxConfig) &&
                Objects.equals(this.selector, ioKruiseAppsV1alpha1ImagePullJobSpec.selector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(completionPolicy, image, parallelism, podSelector, pullPolicy, pullSecrets, sandboxConfig, selector);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KruiseAppsV1alpha1ImagePullJobSpec {\n");
        sb.append("    completionPolicy: ").append(toIndentedString(completionPolicy)).append("\n");
        sb.append("    image: ").append(toIndentedString(image)).append("\n");
        sb.append("    parallelism: ").append(toIndentedString(parallelism)).append("\n");
        sb.append("    podSelector: ").append(toIndentedString(podSelector)).append("\n");
        sb.append("    pullPolicy: ").append(toIndentedString(pullPolicy)).append("\n");
        sb.append("    pullSecrets: ").append(toIndentedString(pullSecrets)).append("\n");
        sb.append("    sandboxConfig: ").append(toIndentedString(sandboxConfig)).append("\n");
        sb.append("    selector: ").append(toIndentedString(selector)).append("\n");
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