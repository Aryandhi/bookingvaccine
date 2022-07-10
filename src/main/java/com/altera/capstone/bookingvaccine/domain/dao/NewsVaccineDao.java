package com.altera.capstone.bookingvaccine.domain.dao;

import java.sql.Time;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.altera.capstone.bookingvaccine.domain.common.BaseEntity;

/**
 * NewsVaccine
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "news_vaccine")
@SQLDelete(sql = "UPDATE news_vaccine SET is_deleted = true WHERE id_news_vaccine = ?")
@Where(clause = "is_deleted = false")
public class NewsVaccineDao extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_news_vaccine;

    @Column(name = "title", nullable = false)
    private String titleNewsVaccine;

    @Column(name = "author", nullable = false)
    private String authorNewsVaccine;

    @Column(name = "body_content", nullable = false, columnDefinition = "TEXT")
    private String contentNewsVaccine;

    @Column(name = "image_url")
    private String image;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "size")
    private long size;
}