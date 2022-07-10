package com.altera.capstone.bookingvaccine.domain.common;

import com.altera.capstone.bookingvaccine.constant.AppConstant;
import com.altera.capstone.bookingvaccine.domain.common.ApiResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
// @SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 3595678817094961783L;

    @Column(name = "created_at", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (createdBy == null)
            createdBy = AppConstant.DEFAULT_SYSTEM;
        this.isDeleted = Boolean.FALSE;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
