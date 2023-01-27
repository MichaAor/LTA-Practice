package com.lta.apilibrarybid.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Corresponde al Ejemplo ONE TO ONE & MANY TO ONE
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createDate","updateDate"},
        allowGetters = true
)
public abstract class Audit implements Serializable {
    private static final long serialVersionUID = 1L;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date",nullable = false,updatable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    //@Temporal(TemporalType.TIMESTAMP)
    //En el ejemplo se usa, solo va con Date, en esta configuracion guarda fecha y tiempo.
    //No respeta @DateTimeFormat.
    @Column(name = "update_date",nullable = false)
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate;
}
