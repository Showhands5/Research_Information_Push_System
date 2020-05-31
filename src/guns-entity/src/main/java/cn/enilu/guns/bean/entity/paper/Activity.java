package cn.enilu.guns.bean.entity.paper;

import cn.enilu.guns.bean.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
@Data
@Entity(name = "activity")
@Table(appliesTo = "activity", comment = "学术活动")
public class Activity extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(32) COMMENT '活动名'")
    private String name;
    @Column(columnDefinition = "VARCHAR(64) COMMENT '链接'")
    private String url;
    @Column(name = "begin_time", columnDefinition = "DATETIME COMMENT '开始时间'")
    private Date beginTime;
    @Column(name = "end_time", columnDefinition = "DATETIME COMMENT '结束时间'")
    private Date endTime;
}
