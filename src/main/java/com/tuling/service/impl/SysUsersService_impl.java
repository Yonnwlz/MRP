package com.tuling.service.impl;


import com.tuling.dao.SysUsersMapper;
import com.tuling.entity.SysUsers;
import com.tuling.entity.SysUsersExample;
import com.tuling.service.SysUsersService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户接口实现类
 */

@Service
public class SysUsersService_impl implements SysUsersService {
    @Resource
    private SysUsersMapper sysUsersMapper;

    @Override
    public Integer insertSysUsers(SysUsers sysUsers) {
        //对密码进行加密
        String newPwd = new Md5Hash(sysUsers.getPassword(),sysUsers.getLoginId(),3).toString();
        sysUsers.setPassword(newPwd);
        return sysUsersMapper.insertSelective(sysUsers);
    }

    @Override
    public SysUsers findeByloginId(SysUsers sysUsers) {
        SysUsersExample example = new SysUsersExample();
        example.createCriteria().andLoginIdEqualTo(sysUsers.getLoginId());
        return sysUsersMapper.selectByExample(example).get(0);
    }
}
