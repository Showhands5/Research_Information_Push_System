package cn.enilu.guns.bean.entity.paper;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.enilu.guns.bean.entity.BaseEntity;
import cn.enilu.guns.bean.entity.system.User;
import lombok.Data;
import lombok.ToString;

@Entity(name = "subject")
@Data
@Table(appliesTo = "subject", comment = "学科领域")
//@ToString(exclude = "userList")
@EntityListeners(AuditingEntityListener.class)
public class Subject extends BaseEntity {
    @Column(name = "name",columnDefinition = "VARCHAR(32) COMMENT '学科名'")
    private String name;

    //@ManyToOne(targetEntity = Subject.class)
    @Column(name = "pid",columnDefinition = "BIGINT COMMENT '父学科id'")
    private Long pid;

    // 加上就是双向多对多，双向多对多如果不自己写get和set方法会爆栈
    //@ManyToMany(mappedBy = "subjectList")
    //private List<User> userList;

    /*
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Long getPid(){
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }*/

}