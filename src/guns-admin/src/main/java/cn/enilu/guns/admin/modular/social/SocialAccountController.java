package cn.enilu.guns.admin.modular.social;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.enilu.guns.admin.core.base.controller.BaseController;
import cn.enilu.guns.bean.annotion.core.BussinessLog;
import cn.enilu.guns.bean.constant.factory.PageFactory;
import cn.enilu.guns.bean.vo.query.Page;
import cn.enilu.guns.bean.vo.query.SearchFilter;
import cn.enilu.guns.service.social.SocialAccountService;
import cn.enilu.guns.service.system.UserService;
import cn.enilu.guns.shiro.ShiroKit;
import cn.enilu.guns.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import cn.enilu.guns.bean.entity.social.SocialAccount;
import cn.enilu.guns.bean.entity.system.User;

@Controller
@Slf4j
@RequestMapping("/social/account")
public class SocialAccountController extends BaseController {
    @Autowired
    private SocialAccountService saService;
    @Autowired
    private UserService userService;

    private static String PREFIX = "/social/account/";
    
   
    @RequestMapping("")
    public String index() {
        //log.info("??????????");
        return PREFIX + "account.html";
    }

    // 展示社交账号
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String username) {
        //log.info("list!!!!!!!");
        log.info("has accountname: " + username);
        Page <SocialAccount> page = new PageFactory<SocialAccount>().defaultPage();
        Long userId = ShiroKit.getUser().getId();
        User user = userService.get(userId);

        //List <SocialAccount> rsl = saService.queryAllByUserId(userId);
        List <SocialAccount> rsl;
        if(StringUtils.isNotEmpty(username)){
            rsl = saService.queryAllByUserIdAndLikeUsername(userId, username);
            //page.addFilter(SearchFilter.build("username", SearchFilter.Operator.LIKE, username));
        }
        else {
            rsl = new ArrayList<>(user.getSocialAccounts()); //saService.queryAllByUserId(userId);
        }
        
        page.setRecords(rsl);
        page.setTotal(Integer.valueOf(rsl.size()+""));
        
        //page = saService.queryPage(page);
        return packForBT(page);
    }

    //跳转到添加参数
    @RequestMapping("/account_add")
    public String add() {
        return PREFIX + "account_add.html";
    }

    // 新增参数
    @RequestMapping(value = "/add")
    @ResponseBody
    //@BussinessLog(value = "添加参数", key = "cfgName",dict = CfgDict.class)
    public Object add(SocialAccount sa) {
        saService.update(sa);

        Long id = ShiroKit.getUser().getId();
        log.info("User: " + id);
        User user = userService.get(id);
        Set<SocialAccount> userSa = user.getSocialAccounts();
        userSa.add(sa);
        userService.update(user);
        
        return SUCCESS_TIP;
    }


    // 删除参数
    @RequestMapping(value = "/delete")
    @ResponseBody
    //@BussinessLog(value = "删除参数", key = "cfgId",dict = CfgDict.class)
    public Object delete(@RequestParam Long socialAccountId) {
        saService.delete(socialAccountId);

        Long userId = ShiroKit.getUser().getId();
        log.info("User: " + userId);
        User user = userService.get(userId);
        Set<SocialAccount> userSa = user.getSocialAccounts();
        for (SocialAccount e: userSa) {
            if (e.getId() == socialAccountId) {
                userSa.remove(e);
                break;
            }
        }
        user.setSocialAccounts(userSa);

        return SUCCESS_TIP;
    }

   // 跳转到修改参数
    @RequestMapping("/account_update/{saId}")
    public String update(@PathVariable Long saId, Model model) {
        SocialAccount sa = saService.get(saId);
        model.addAttribute("item", sa);
        return PREFIX + "account_edit.html";
    }

    // 修改参数
    @RequestMapping(value = "/update")
    @ResponseBody
   // @BussinessLog(value = "编辑参数", key = "cfgName",dict = CfgDict.class)
    public Object update(SocialAccount sa) {
        saService.update(sa);
        return SUCCESS_TIP;
    }

    // 参数详情
    @RequestMapping(value = "/detail/{saId}")
    @ResponseBody
    public Object detail(@PathVariable("saId") Long saId) {
        return saService.get(saId);
    }

}