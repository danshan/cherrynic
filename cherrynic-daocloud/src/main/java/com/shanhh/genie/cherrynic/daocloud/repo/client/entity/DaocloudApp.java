package com.shanhh.genie.cherrynic.daocloud.repo.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shanhonghao
 * @since 2018-04-15 12:34
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DaocloudApp implements Serializable {
    @JsonProperty("enable_auto_redeploy")
    private boolean enableAutoReploy;
    @JsonProperty("name")
    private String name;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("last_operated_at")
    private Date lastOperatedAt;
    @JsonProperty("state")
    private String state;
    @JsonProperty("release_name")
    private String releaseName;
    private String id;

    @JsonProperty("package")
    private Package appPackage;
    @JsonProperty("app_runtime")
    private Package appRuntime;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    class Package implements Serializable {
        private String image;
        private String id;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    class Runtime implements Serializable {
        private String displayName;
        private String id;
        private String name;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
