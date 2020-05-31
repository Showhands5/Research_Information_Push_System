package cn.enilu.guns.bean.dto;

import cn.enilu.guns.bean.entity.paper.Subject;
import lombok.Data;

@Data
public class SubjectDto extends Subject{
    private Boolean isSelected;
    public SubjectDto (Object[] obj) {
        //super((Subject)obj[0]);
        isSelected = Boolean.valueOf(obj[1].toString());

    }
}