package com.selfgrowth.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class DateAudit implements Serializable {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonProperty("createAt")
    private Instant createAt;

    @LastModifiedDate
    @Column(nullable = false)
    @JsonProperty("updateAt")
    private Instant updateAt;

    public DateAudit() {
    }

    public DateAudit(Instant createAt, Instant updateAt) {
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }
}
