package cn.enilu.guns.admin.modular.social;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.enilu.guns.admin.core.base.controller.BaseController;

@Controller
@RequestMapping("/social/account")
public class SocialAccountDisplayController extends BaseController {
    private static String PREFIX = "/social/";
    @RequestMapping("/display")
    public String index() {
        return PREFIX + "social.html";
    }
    
}