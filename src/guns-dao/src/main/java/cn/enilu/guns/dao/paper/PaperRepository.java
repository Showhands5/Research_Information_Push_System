package cn.enilu.guns.dao.paper;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.enilu.guns.bean.entity.paper.Paper;
import cn.enilu.guns.bean.entity.paper.Subject;
import cn.enilu.guns.bean.vo.query.Page;
import cn.enilu.guns.dao.BaseRepository;

public interface PaperRepository extends BaseRepository<Paper, Long> {
    Paper findByTitle(String title);
/*
    @Query(nativeQuery = true, value = 
        "SELECT * FROM paper, subject, paper_subject "
        + "WHERE paper.id=paper_subject.paper_id "
        + "AND paper_subject.subject_id=subject.id "
        + "AND subject IN ?1")
    Page <Paper> findByIdNotIn(Set<Long> subjects, Pageable pageable);
*/
    // 前面必须把paper的所有属性写出来，否则返回List<Paper>的时候会拿错误的字段去生成Paper
    // 造成出现重复论文的错误
    @Query(nativeQuery = true, value = 
    "SELECT paper.title, paper.author, paper.id, paper.create_by, paper.create_time, paper.modify_time, paper.modify_by, paper.title, paper.url, paper.org, paper.comment"
    +" FROM ( `subject` INNER JOIN user_subject ON (user_subject.user_id=?1 AND subject.id=user_subject.subject_id) ) INNER JOIN paper_subject ON paper_subject.subject_id=user_subject.subject_id INNER JOIN paper ON paper.id=paper_subject.paper_id"
    )  
    List<Paper> myFindByUserId(Long userId);
    
    //List<Paper> findByUser_Id(Long userId);
}