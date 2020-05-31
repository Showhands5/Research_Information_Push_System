package cn.enilu.guns.warpper;

import java.util.List;
import java.util.Map;

public class SubjectWarpper extends BaseControllerWarpper {
    public SubjectWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        
        // TODO Auto-generated method stub
        // e.g.
        // map.put("statusName", ConstantFactory.me().getMenuStatusName((Integer) map.get("status")));
        // map.put("isMenuName", IsMenu.valueOf((Integer) map.get("ismenu")));
    }
}