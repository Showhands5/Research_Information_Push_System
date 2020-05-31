package cn.enilu.guns.service.social;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.guns.bean.entity.social.SocialAccount;
import cn.enilu.guns.dao.social.SocialAccountRepository;
import cn.enilu.guns.service.BaseService;

@Service
public class SocialAccountService extends BaseService<SocialAccount, Long, SocialAccountRepository>{
    @Autowired
    private SocialAccountRepository saRepo;

    public List<SocialAccount> queryAllByUserId(Long userId) {
        return saRepo.queryAllByUserId(userId);
    }

    public List<SocialAccount> queryAllByUserIdAndLikeUsername(Long userId, String username) {
        return saRepo.queryAllByUserIdAndLikeUsername(userId, username);
    }
}