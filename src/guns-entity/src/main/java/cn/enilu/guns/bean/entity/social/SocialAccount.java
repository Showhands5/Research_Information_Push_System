package cn.enilu.guns.bean.entity.social;

import cn.enilu.guns.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(appliesTo = "social_account", comment = "社交帐号")
@Data
public class SocialAccount extends BaseEntity {
    @Column(name = "username", columnDefinition = "VARCHAR(32) COMMENT '社交帐号实体名称'")
    private String username;
    @Column(name = "website", columnDefinition = "VARCHAR(32) COMMENT '社交网络公司'")
    private String website;
}
