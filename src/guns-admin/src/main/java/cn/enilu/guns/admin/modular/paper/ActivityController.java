package cn.enilu.guns.admin.modular.paper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.enilu.guns.admin.core.base.controller.BaseController;
import cn.enilu.guns.bean.annotion.core.BussinessLog;
import cn.enilu.guns.bean.constant.factory.PageFactory;
import cn.enilu.guns.bean.entity.paper.Activity;
import cn.enilu.guns.bean.vo.query.Page;
import cn.enilu.guns.bean.vo.query.SearchFilter;
import cn.enilu.guns.service.paper.ActivityService;
import cn.enilu.guns.utils.StringUtils;

@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {
    @Autowired
    private ActivityService activityService;
    private static String PREFIX = "/paper/activity/";

    /**
     * 跳转到参数首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "activity.html";
    }

    /*
    // 跳转到添加参数
    
    @RequestMapping("/activity_add")
    public String add() {
        return PREFIX + "activity_add.html";
    }

    // 跳转到修改参数
   
    @RequestMapping("/activity_update/{activityId}")
    public String update(@PathVariable Long activityId, Model model) {
        Activity activity = activityService.get(activityId);
        model.addAttribute("item",activity);
        return PREFIX + "activity_edit.html";
    }
    */
    // 获取参数列表
    
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String activityname) {
        Page<Activity> page = new PageFactory<Activity>().defaultPage();
        if(StringUtils.isNotEmpty(activityname)){
            page.addFilter(SearchFilter.build("name", SearchFilter.Operator.LIKE, activityname));
        }
        
        page = activityService.queryPage(page);
        return packForBT(page);
    }
    /*
    // 新增参数
    
    @RequestMapping(value = "/add")
    @ResponseBody
    @BussinessLog(value = "添加领域", key = "activityName",dict = ActivityDict.class)
    public Object add(Activity activity) {
        activityService.saveOrUpdate(activity);
        return SUCCESS_TIP;
    }

    // 删除参数
    
    @RequestMapping(value = "/delete")
    @ResponseBody
    @BussinessLog(value = "删除领域", key = "activityId",dict = ActivityDict.class)
    public Object delete(@RequestParam Long activityId) {
        activityService.delete(activityId);
        return SUCCESS_TIP;
    }

   
    // 修改参数
    
    @RequestMapping(value = "/update")
    @ResponseBody
    @BussinessLog(value = "编辑领域", key = "activityName",dict = ActivityDict.class)
    public Object update(Activity activity) {
       activityService.update(activity);
        return SUCCESS_TIP;
    }
    */
}