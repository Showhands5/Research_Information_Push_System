package cn.enilu.guns.admin.modular.paper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.enilu.guns.admin.core.base.controller.BaseController;
import cn.enilu.guns.bean.annotion.core.Permission;
import cn.enilu.guns.bean.constant.factory.PageFactory;
import cn.enilu.guns.bean.dto.SubjectDto;
import cn.enilu.guns.bean.entity.paper.Subject;
import cn.enilu.guns.bean.entity.system.User;
import cn.enilu.guns.bean.vo.node.ZTreeNode;
import cn.enilu.guns.bean.vo.query.Page;
import cn.enilu.guns.bean.vo.query.SearchFilter;
import cn.enilu.guns.service.paper.SubjectService;
import cn.enilu.guns.service.system.UserService;
import cn.enilu.guns.shiro.ShiroKit;
import cn.enilu.guns.utils.BeanUtil;
import cn.enilu.guns.utils.StringUtils;
import cn.enilu.guns.warpper.BaseControllerWarpper;
import cn.enilu.guns.warpper.SubjectWarpper;
import cn.enilu.guns.warpper.UserWarpper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;
    
    private static String PREFIX = "/paper/subject/";

    @RequestMapping("")
    public String index() {
        return PREFIX + "subject.html";
    }

    // 展示列表
    // 控制器向视图传值
    
    @RequestMapping(value = "/list")
    @ResponseBody
    //@Permission
    public Object list(@RequestParam(required = false) String name) {
        // 为了加上一个该用户是否已选择的片段，必须实现多表联查

        /*
        List<Subject> list = null;
        if (StringUtils.isNullOrEmpty(name)) {
            list = (List<Subject>) this.subjectService.queryAll();
        } else {
            list = (List<Subject>) this.subjectService.findByNameLike(name);
        }*/

        //log.info(list.map( (e)->e.toString()));
        //List<Map<String, Object>> subjectListToBeWrapped = BeanUtil.objectsToMaps(list);
        //SubjectWarpper subjectWarpper = new SubjectWarpper(BeanUtil.objectsToMaps(list));
        
        //SubjectWarpper subjectWarpper = new SubjectWarpper(BeanUtil.objectsToMaps(list));
        Long userId = ShiroKit.getUser().getId();
        List<ZTreeNode> list = null;
        //List<Subject> userSubjectList = new ArrayList<>(user.getSubjects());
       
        list = this.subjectService.queryAllWithIsSelectedByUser(userId);

        // List<Subject> list = null;
        // if (StringUtils.isNullOrEmpty(name)) {
        //     list = (List<Subject>) this.subjectService.queryAll();
        // } else {
        //     list = (List<Subject>) this.subjectService.findByNameLike(name);
        // }

        //log.info("test" + list[0][1]);
        //list.add(ZTreeNode.createParent());
        return super.warpObject(new SubjectWarpper((BeanUtil.objectsToMaps(list))));
        /*
        Page <Subject> page = new PageFactory<Subject>().defaultPage();
        if(StringUtils.isNotEmpty(name)){
            page.addFilter(SearchFilter.build("name", SearchFilter.Operator.LIKE, name));
        }
        
        page = subjectService.queryPage(page);
        return packForBT(page);
        */
    }
    
    
    @RequestMapping(value = "/list_no")
    @ResponseBody
    public List<ZTreeNode> tree(@RequestParam(required = false) String subjectName) {
        List<ZTreeNode> tree = this.subjectService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    @RequestMapping(value = "/update")
    @ResponseBody //@RequestMapping后，返回值通常解析为跳转路径，但是加上 @ResponseBody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中
    //@RequestParam(required = true) 
    //@BussinessLog(value = "保存领域", key = "name",dict = CfgDict.class)
    public Object update(@RequestParam(value = "subjectIds[]") List<Long> subjectIds) throws Exception {
        
        //subjectService.addSubjectToUser();
        Long id = ShiroKit.getUser().getId();
        log.info("User: " + id);
        User user = userService.get(id);
        //user.setSubjects(subjectIds);
        
        Set<Subject> userSubjects = user.getSubjects();
        userSubjects.clear();
        log.info(""+userSubjects.size());
        
        for(Long e: subjectIds) {
            userSubjects.add(subjectService.get(e));
            //userSubjects.add(e);
        }
        
        
        //user.setSubjects(userSubjects);
        //log.info("what happened???");
        userService.update(user); // 忘掉了 查了好久
        log.info("" + userSubjects.size());
        
        return SUCCESS_TIP;
    }
}