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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * An update strategy to replace existing DaemonSet pods with new pods.
 */
@ApiModel(description = "An update strategy to replace existing DaemonSet pods with new pods.")
@Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-07-21T15:55:09.049+08:00")
public class KruiseAppsV1alpha1DaemonSetUpdateStrategy {
    @SerializedName("rollingUpdate")
    private KruiseAppsV1alpha1RollingUpdateDaemonSet rollingUpdate = null;
    @SerializedName("type")
    private String type = null;

    public KruiseAppsV1alpha1DaemonSetUpdateStrategy rollingUpdate(KruiseAppsV1alpha1RollingUpdateDaemonSet rollingUpdate) {
        this.rollingUpdate = rollingUpdate;
        return this;
    }

    /**
     * Get rollingUpdate
     *
     * @return rollingUpdate
     **/
    @ApiModelProperty(value = "")
    public KruiseAppsV1alpha1RollingUpdateDaemonSet getRollingUpdate() {
        return rollingUpdate;
    }

    public void setRollingUpdate(KruiseAppsV1alpha1RollingUpdateDaemonSet rollingUpdate) {
        this.rollingUpdate = rollingUpdate;
    }

    public KruiseAppsV1alpha1DaemonSetUpdateStrategy type(String type) {
        this.type = type;
        return this;
    }

    /**
     * Type of daemon set update. Can be \&quot;RollingUpdate\&quot; or \&quot;OnDelete\&quot;. Default is RollingUpdate.
     *
     * @return type
     **/
    @ApiModelProperty(value = "Type of daemon set update. Can be \"RollingUpdate\" or \"OnDelete\". Default is RollingUpdate.")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KruiseAppsV1alpha1DaemonSetUpdateStrategy)) {
            return false;
        }
        KruiseAppsV1alpha1DaemonSetUpdateStrategy ioKruiseAppsV1alpha1DaemonSetSpecUpdateStrategy = (KruiseAppsV1alpha1DaemonSetUpdateStrategy) o;
        return Objects.equals(this.rollingUpdate, ioKruiseAppsV1alpha1DaemonSetSpecUpdateStrategy.rollingUpdate) &&
                Objects.equals(this.type, ioKruiseAppsV1alpha1DaemonSetSpecUpdateStrategy.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollingUpdate, type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KruiseAppsV1alpha1DaemonSetSpecUpdateStrategy {\n");
        sb.append("    rollingUpdate: ").append(toIndentedString(rollingUpdate)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
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