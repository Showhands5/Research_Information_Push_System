package cn.enilu.guns.service.paper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.enilu.guns.bean.constant.factory.PageFactory;
import cn.enilu.guns.bean.entity.paper.Paper;
import cn.enilu.guns.bean.vo.query.DynamicSpecifications;
import cn.enilu.guns.bean.vo.query.Page;
import cn.enilu.guns.dao.paper.PaperRepository;
import cn.enilu.guns.service.BaseService;
import cn.enilu.guns.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import cn.enilu.guns.bean.entity.paper.Subject;
import cn.enilu.guns.bean.entity.system.User;

@Slf4j
@Service
public class PaperService extends BaseService<Paper, Long, PaperRepository>{
    @Autowired 
    private PaperRepository paperRepo;

    public List<Paper> mytest(Long userId){
        return paperRepo.myFindByUserId(userId);
    }

    @PersistenceContext
    private EntityManager em;
    
    //public Page<Paper> queryPageWithSubjectFilter(Page<Paper> page, Pageable pageable, String title, Set<Subject> subjects) {


    
    public List<Paper> queryPageWithSubjectFilter(String title, Long userId) {//Set<Subject> subjects) {
        return null;//paperRepo.findByUser_Id(userId);
        /*
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Subject> cq = cb.createQuery(Subject.class);
        Root<Subject> rootSubject = cq.from(Subject.class);
        Predicate condition = cb.equal(rootSubject.get(Employee_.age), 24);
        criteriaQuery.where(condition);
        TypedQuery<Employee> typedQuery = em.createQuery(criteriaQuery);
        List<Employee> result = typedQuery.getResultList();
        */
        /*
        Specification<Paper> specification = new Specification<Paper>(){

            @Override
            public Predicate toPredicate(Root<Paper> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicateList = new ArrayList<>();
                //--------------------------------------------
               
                //关联表查询示例
                //if (!StringUtils.isEmpty(courseName)) {
                //Join<Paper, Subject> joinSubject = root.getModeljoin("subjects", JoinType.LEFT);
                //Join<Paper, Subject> joinSubject = root.join(root.getModel().getSet("subjects", Subject.class), JoinType.LEFT);
                //Predicate predicate = cb.equal(joinSubject.get("subject"), subjects);
                //predicatesList.add(predicate);
                //}

                //Join<Paper, Subject> joinSubject = root.join(root.getModel().getSet("subjects", Subject.class), JoinType.INNER);
                
                //Join<Paper, Subject> joinSubject = root.join(root.get("subjects"), JoinType.INNER);
                //log.info(joinSubject.getAttribute().toString());
                //Join<User, 
                
                Join<Paper, User> joinUser = root.join("subjects", JoinType.INNER);
                //Root<User> userRoot = cb.get
                log.info(joinUser.toString());
                predicateList.add(cb.equal(joinUser.get("name"), "a")); //user.getId()
                
                //Join<Paper, Subject> joinPaperSubject = root.join("subjects", JoinType.LEFT);
                
                //predicateList.add(cb.in(joinSubject.get("Subject"), subjects));
                Predicate[] predicates = new Predicate[predicateList.size()];
                return cb.and(predicateList.toArray(predicates));
                //排序示例(先根据学号排序，后根据姓名排序)
                //query.orderBy(cb.asc(root.get("studentNumber")),cb.asc(root.get("name")));
                //--------------------------------------------
                //最终将查询条件拼好然后return
                //redicate[] predicates = new Predicate[predicatesList.size()];
                //return cb.and(predicatesList.toArray(predicates));
            }
        };
        
        return paperRepo.findAll(specification);
        */
        //return null;
    }
    


}