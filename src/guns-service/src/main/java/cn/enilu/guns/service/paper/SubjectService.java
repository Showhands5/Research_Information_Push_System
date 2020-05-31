package cn.enilu.guns.service.paper;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.guns.bean.entity.paper.Subject;
import cn.enilu.guns.bean.entity.system.User;
import cn.enilu.guns.bean.vo.node.ZTreeNode;
import cn.enilu.guns.dao.paper.SubjectRepository;
import cn.enilu.guns.dao.system.UserRepository;
import cn.enilu.guns.service.BaseService;
import cn.enilu.guns.service.system.UserService;
import cn.enilu.guns.shiro.ShiroKit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubjectService extends BaseService<Subject, Long, SubjectRepository> {
    
    @Autowired
    private SubjectRepository subjectRepo;
    
    public List <Subject> findByNameLike(String name) {
        return subjectRepo.findByNameLike(name);
    }

    public List<ZTreeNode> queryAllWithIsSelectedByUser(Long userId) {
        List<Object[]> list = subjectRepo.queryAllWithIsSelectedByUser(userId);
        
        List<ZTreeNode> nodes = new ArrayList<>();
        for(Object[] obj:list){
            ZTreeNode node = transferWithIsSelectedByUser(obj);
            nodes.add(node);
        }
        return nodes;
    }
    
    private ZTreeNode transferWithIsSelectedByUser(Object[] obj){
        ZTreeNode node = new ZTreeNode();
        node.setId(Long.valueOf(obj[0].toString()));
        node.setpId(Long.valueOf(obj[1].toString()));
        node.setName(obj[2].toString());
        node.setIsOpen(Boolean.valueOf(obj[3].toString()));
        node.setChecked(Boolean.valueOf(obj[4].toString()));
        return node;
    }

    public List<ZTreeNode> tree() {
        List<Object[]> list = subjectRepo.tree();
        List<ZTreeNode> nodes = new ArrayList<>();
        for(Object[] obj:list){
            ZTreeNode node = transfer(obj);
            nodes.add(node);
        }
        return nodes;
    }

    private ZTreeNode transfer(Object[] obj){
        ZTreeNode node = new ZTreeNode();
        node.setId(Long.valueOf(obj[0].toString()));
        node.setpId(Long.valueOf(obj[1].toString()));
        node.setName(obj[2].toString());
        node.setIsOpen(Boolean.valueOf(obj[3].toString()));
        return node;
    }

    public List<Subject> query(String condition) {
        List<Subject> list = new ArrayList<>();
        if(Strings.isNullOrEmpty(condition)){
            list = (List<Subject>) subjectRepo.findAll();
        }else{
            condition = "%"+condition+"%";
            list = subjectRepo.findByNameLike(condition);
        }
        return list;
    }
}