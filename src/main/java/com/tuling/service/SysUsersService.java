package com.tuling.service;

import com.tuling.entity.SysUsers;

/**
 * 用户接口
 */
public interface SysUsersService {

    /**
     * 添加用户
     * @param sysUsers
     * @return
     */
    public Integer insertSysUsers(SysUsers sysUsers);

    /**
     * 查询用户
     * @param sysUsers
     * @return
     */
    public SysUsers findeByloginId(SysUsers sysUsers);
}
