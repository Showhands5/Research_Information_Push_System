package cn.enilu.guns.dao.social;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.enilu.guns.bean.entity.social.SocialAccount;
import cn.enilu.guns.dao.BaseRepository;

public interface SocialAccountRepository extends BaseRepository<SocialAccount, Long>{
    @Query(nativeQuery = true,value = 
                //"SELECT id, username, website," 
                //+ "( CASE WHEN (pId = 0 OR pId IS NULL) THEN 'true' ELSE 'false' END ) AS isOpen,"
                //+ "( CASE WHEN (user_id = 0 OR user_id IS NULL) THEN 'false' ELSE 'true' END ) AS isSelected "
                "SELECT * "
                + "FROM social_account LEFT JOIN (SELECT social_account_id FROM user_social_account WHERE user_social_account.user_id = ?1) AS tmp "
                + "ON social_account.id = tmp.social_account_id")
    List<SocialAccount> queryAllByUserId(Long userId);

    @Query(nativeQuery = true,value = 
    //"SELECT id, username, website," 
    //+ "( CASE WHEN (pId = 0 OR pId IS NULL) THEN 'true' ELSE 'false' END ) AS isOpen,"
    //+ "( CASE WHEN (user_id = 0 OR user_id IS NULL) THEN 'false' ELSE 'true' END ) AS isSelected "
    "SELECT * "
    + "FROM social_account INNER JOIN (SELECT social_account_id FROM user_social_account WHERE user_social_account.user_id = ?1) AS tmp "
    + "ON social_account.id = tmp.social_account_id AND social_account.username LIKE %?2%")
    List<SocialAccount> queryAllByUserIdAndLikeUsername(Long userId, String username);
}