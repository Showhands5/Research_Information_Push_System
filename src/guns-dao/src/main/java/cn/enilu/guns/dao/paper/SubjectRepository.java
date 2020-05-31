package cn.enilu.guns.dao.paper;

import java.util.List;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.repository.Query;

import cn.enilu.guns.bean.entity.paper.Subject;
import cn.enilu.guns.dao.BaseRepository;

public interface SubjectRepository extends BaseRepository<Subject,Long> {
    Subject findByName(String name); // List ?

    @Query(nativeQuery = true,value = "SELECT id, pid AS pId, name AS NAME, ( CASE WHEN (pId = 0 OR pId IS NULL) THEN 'true' ELSE 'false' END ) AS isOpen FROM subject")
    List<Object[]> tree();
    
    List<Subject> findByNameLike(String name);

    @Query(nativeQuery = true,value = 
                "SELECT id, pid AS pId, name AS NAME," 
                + "( CASE WHEN (pId = 0 OR pId IS NULL) THEN 'true' ELSE 'false' END ) AS isOpen,"
                + "( CASE WHEN (user_id = 0 OR user_id IS NULL) THEN 'false' ELSE 'true' END ) AS isSelected "
                + "FROM subject LEFT JOIN (SELECT subject_id, user_id FROM user_subject WHERE user_subject.user_id = ?1) AS tmp "
                + "ON subject.id = tmp.subject_id")
    List<Object[]> queryAllWithIsSelectedByUser(Long userId);
}