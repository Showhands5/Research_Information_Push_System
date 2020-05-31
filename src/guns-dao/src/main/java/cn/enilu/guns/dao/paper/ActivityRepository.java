package cn.enilu.guns.dao.paper;
import cn.enilu.guns.bean.entity.paper.Activity;
import cn.enilu.guns.dao.BaseRepository;

public interface ActivityRepository extends BaseRepository<Activity, Long>{
    Activity findByName(String name);
}