package kr.bora.api.common.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
public abstract class BaseEntity{

    @CreatedDate
    @Column(name = "reg_date", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "mod_date")
    private LocalDateTime modDate;


}
