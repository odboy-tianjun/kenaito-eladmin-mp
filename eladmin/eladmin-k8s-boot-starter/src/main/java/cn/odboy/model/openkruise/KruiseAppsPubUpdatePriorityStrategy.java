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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Priorities are the rules for calculating the priority of updating pods. Each pod to be updated, will pass through these terms and get a sum of weights.
 */
@ApiModel(description = "Priorities are the rules for calculating the priority of updating pods. Each pod to be updated, will pass through these terms and get a sum of weights.")
@Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-07-21T15:55:09.049+08:00")
public class KruiseAppsPubUpdatePriorityStrategy {
    @SerializedName("orderPriority")
    private List<KruiseAppsPubUpdatePriorityOrderTerm> orderPriority = null;
    @SerializedName("weightPriority")
    private List<KruiseAppsPubUpdatePriorityWeightTerm> weightPriority = null;

    public KruiseAppsPubUpdatePriorityStrategy orderPriority(List<KruiseAppsPubUpdatePriorityOrderTerm> orderPriority) {
        this.orderPriority = orderPriority;
        return this;
    }

    public KruiseAppsPubUpdatePriorityStrategy addOrderPriorityItem(KruiseAppsPubUpdatePriorityOrderTerm orderPriorityItem) {
        if (this.orderPriority == null) {
            this.orderPriority = new ArrayList<KruiseAppsPubUpdatePriorityOrderTerm>();
        }
        this.orderPriority.add(orderPriorityItem);
        return this;
    }

    /**
     * Order priority terms, pods will be sorted by the value of orderedKey. For example: &#x60;&#x60;&#x60; orderPriority: - orderedKey: key1 - orderedKey: key2 &#x60;&#x60;&#x60; First, all pods which have key1 in labels will be sorted by the value of key1. Then, the left pods which have no key1 but have key2 in labels will be sorted by the value of key2 and put behind those pods have key1.
     *
     * @return orderPriority
     **/
    @ApiModelProperty(value = "Order priority terms, pods will be sorted by the value of orderedKey. For example: ``` orderPriority: - orderedKey: key1 - orderedKey: key2 ``` First, all pods which have key1 in labels will be sorted by the value of key1. Then, the left pods which have no key1 but have key2 in labels will be sorted by the value of key2 and put behind those pods have key1.")
    public List<KruiseAppsPubUpdatePriorityOrderTerm> getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(List<KruiseAppsPubUpdatePriorityOrderTerm> orderPriority) {
        this.orderPriority = orderPriority;
    }

    public KruiseAppsPubUpdatePriorityStrategy weightPriority(List<KruiseAppsPubUpdatePriorityWeightTerm> weightPriority) {
        this.weightPriority = weightPriority;
        return this;
    }

    public KruiseAppsPubUpdatePriorityStrategy addWeightPriorityItem(KruiseAppsPubUpdatePriorityWeightTerm weightPriorityItem) {
        if (this.weightPriority == null) {
            this.weightPriority = new ArrayList<KruiseAppsPubUpdatePriorityWeightTerm>();
        }
        this.weightPriority.add(weightPriorityItem);
        return this;
    }

    /**
     * Weight priority terms, pods will be sorted by the sum of all terms weight.
     *
     * @return weightPriority
     **/
    @ApiModelProperty(value = "Weight priority terms, pods will be sorted by the sum of all terms weight.")
    public List<KruiseAppsPubUpdatePriorityWeightTerm> getWeightPriority() {
        return weightPriority;
    }

    public void setWeightPriority(List<KruiseAppsPubUpdatePriorityWeightTerm> weightPriority) {
        this.weightPriority = weightPriority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KruiseAppsPubUpdatePriorityStrategy)) {
            return false;
        }
        KruiseAppsPubUpdatePriorityStrategy ioKruiseAppsV1alpha1CloneSetSpecUpdateStrategyPriorityStrategy = (KruiseAppsPubUpdatePriorityStrategy) o;
        return Objects.equals(this.orderPriority, ioKruiseAppsV1alpha1CloneSetSpecUpdateStrategyPriorityStrategy.orderPriority) &&
                Objects.equals(this.weightPriority, ioKruiseAppsV1alpha1CloneSetSpecUpdateStrategyPriorityStrategy.weightPriority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderPriority, weightPriority);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class KruiseAppsV1alpha1CloneSetSpecUpdateStrategyPriorityStrategy {\n");
        sb.append("    orderPriority: ").append(toIndentedString(orderPriority)).append("\n");
        sb.append("    weightPriority: ").append(toIndentedString(weightPriority)).append("\n");
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