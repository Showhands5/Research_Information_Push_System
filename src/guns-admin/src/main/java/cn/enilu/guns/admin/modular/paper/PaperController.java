package cn.enilu.guns.admin.modular.paper;

import cn.enilu.guns.service.system.UserService;
import cn.enilu.guns.service.system.impl.ConstantFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.enilu.guns.admin.core.base.controller.BaseController;
import cn.enilu.guns.bean.annotion.core.BussinessLog;
import cn.enilu.guns.bean.constant.factory.PageFactory;
import cn.enilu.guns.bean.entity.paper.Paper;
import cn.enilu.guns.bean.entity.paper.Subject;
import cn.enilu.guns.bean.entity.system.User;
import cn.enilu.guns.bean.vo.query.Page;
import cn.enilu.guns.bean.vo.query.SearchFilter;
import cn.enilu.guns.dao.paper.PaperRepository;
import cn.enilu.guns.service.paper.PaperService;
import cn.enilu.guns.shiro.ShiroKit;
import cn.enilu.guns.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
@RequestMapping("/paper/paper")
public class PaperController extends BaseController{
    @Autowired
    private PaperService paperService;
    @Autowired
    private UserService userService;
    private static String PREFIX = "/paper/paper/";

    /* 
    跳转到参数首页
    */
    @RequestMapping("")
    public String index() {
        //log.info("heresdfas");
        return PREFIX + "paper.html";
    }

    /*
    // add paper
    @RequestMapping("/paper_add")
    public String add() {
        return PREFIX + "paper_add.html";
    }

    // update paper
    @RequestMapping("/paper_update/{paperId}")
    public String update(@PathVariable Long paperId, Model model) {
        Paper paper = paperService.get(paperId);
        model.addAttribute("item", paper);
        return PREFIX + "paper_update.html";
    }

*/    
    @RequestMapping(value = "/list")
    @ResponseBody    
    public Object list(@RequestParam(required = false) String title) {
        
        Page <Paper> page = new PageFactory<Paper>().defaultPage();
        /*
        if(StringUtils.isNotEmpty(title)){
            page.addFilter(SearchFilter.build("title", SearchFilter.Operator.LIKE, title));
        }
        Pageable pageable = PageRequest.of(page.getCurrent()-1,page.getSize(), Sort.Direction.DESC,"id");
        
        */
        /*
        if(page.isOpenSort()) {
            pageable = new PageRequest(page.getCurrent()-1, page.getSize(), page.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC, page.getOrderByField());
        }else{
            pageable = new PageRequest(page.getCurrent()-1,page.getSize(), Sort.Direction.DESC,"id");
        }*/
        
        Long userId = ShiroKit.getUser().getId();
        //User user = userService.get(userId);
        
        //List<Subject> userSubjects = new ArrayList<>(user.getSubjects());
        //List<Paper> rsl = paperService.queryPageWithSubjectFilter(title, userId);
        List<Paper> rsl = paperService.mytest(userId); // ok
        //for(Paper p:rsl){
        //    log.info(p.toString());
        //}
        //List<Paper> paperList = queryAll();
        //for (Subject us: userSubjects) {
        
        //page.addFilter(SearchFilter.build("subjects", SearchFilter.Operator.IN, userSubjects));
        //}
        
        page.setRecords(rsl);
        //page = paperService.queryPage(page);
        //page = paperService.queryPageWithSubjectFilter(page, pageable, title, userSubjects);
        page.setTotal(Integer.valueOf(rsl.size()+""));
        return packForBT(page);
    }
    
/*
    @RequestMapping("/paper_delete")
    @ResponseBody
    //@BussinessLog(value = "删除参数", key = "paperId",dict = CfgDict.class)
    public Object delete(@RequestParam Long paperId) {
        paperService.delete(paperId);
        return SUCCESS_TIP;
    } */

}