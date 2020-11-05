package com.tuling.shiro;

import com.tuling.dao.SysUsersMapper;
import com.tuling.entity.SysUsers;
import com.tuling.entity.SysUsersExample;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.session.HttpServletSession;

import javax.annotation.Resource;
import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Resource
    private SysUsersMapper sysUsersMapper;

    /**
     * 授权要做的事情
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权要做的事情");
        return null;
    }

    /**
     * 认证要做的事情
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        SysUsersExample example = new SysUsersExample();
        example.createCriteria().andLoginIdEqualTo(token.getUsername());
        List<SysUsers> sysUsers = sysUsersMapper.selectByExample(example);
        if(sysUsers.size()<1){
            return null;
        }else {
            //在查密码
            SysUsers users = sysUsers.get(0);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(users,users.getPassword(),getName());
            info.setCredentialsSalt(ByteSource.Util.bytes(users.getLoginId()));
            return info;
        }
    }
}
