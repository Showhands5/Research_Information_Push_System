package cn.enilu.guns.bean.entity.paper;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.enilu.guns.bean.entity.BaseEntity;
import lombok.Data;

@Entity(name="paper")
@Data
@Table(appliesTo = "paper", comment="论文")
@EntityListeners(AuditingEntityListener.class)
public class Paper extends BaseEntity{
    private String title;
    private String org;
    private String author;
    private String comment;
    private String url;
    //private String area;
    @ManyToMany(targetEntity = Subject.class)
    @JoinTable(name = "paper_subject", 
        joinColumns = @JoinColumn(name = "paper_id"), 
        inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Long> subjects = new HashSet<>();
}