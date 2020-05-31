package cn.enilu.guns.bean.entity.system;

import cn.enilu.guns.bean.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.Table;
import org.nutz.json.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.enilu.guns.bean.entity.paper.Subject;
import cn.enilu.guns.bean.entity.social.SocialAccount;

/**
 * Created  on 2018/4/2 0002.
 *
 * @author enilu
 */
@Entity(name = "t_sys_user")
@Table(appliesTo = "t_sys_user",comment = "系统管理员")
@Data
//@EqualsAndHashCode
//@ToString(exclude = "subjectList")
//@ToString(exclude = "subjectList") // 否则爆栈
@EntityListeners(AuditingEntityListener.class)
public class User  extends BaseEntity {
    @Column
    private String avatar;
    @Column
    private String account;
    @Column
    private String password;
    @Column
    private String salt;
    @Column
    private String name;
    @Column
    private Date birthday;
    @Column
    private Integer sex;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String roleid;
    @Column
    private Long deptid;
    @Column
    private Integer status;
    @Column
    private Integer version;
    // test for subjects
    // 注意这里JoinColumn的name不是referencedColumnName
    // 实现多对多
    @ManyToMany(targetEntity = Subject.class)
    @JoinTable(name = "user_subject", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set <Subject> subjects = new HashSet<>();

    @ManyToMany(targetEntity = SocialAccount.class)
    @JoinTable(name = "user_social_account", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "social_account_id"))
    private Set <SocialAccount> socialAccounts = new HashSet<>();

}
