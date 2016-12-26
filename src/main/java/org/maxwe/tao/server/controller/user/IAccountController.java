package org.maxwe.tao.server.controller.user;

import org.maxwe.tao.server.interceptor.ManagerInterceptor;
import org.maxwe.tao.server.interceptor.MenuInterceptor;
import org.maxwe.tao.server.interceptor.SessionInterceptor;
import com.jfinal.aop.Before;

/**
 * Created by Pengwei Ding on 2016-08-16 08:58.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public interface IAccountController {

    /**
     * 用户接口
     * 检测账号的手机号码是否已经注册
     */
    void exist();

    /**
     * 用户接口
     * 获取短信验证码
     */
    void smsCode();

    /**
     * 用户接口
     * 注册接口
     * 用户创建账户接口
     */
    void create();

    /**
     * 用户接口
     * 用户登录接口
     */
    void login();

    /**
     * 用户接口
     * 用户退出登录接口
     */
    @Before(SessionInterceptor.class)
    void logout();

    /**
     * 用户接口
     * 忘记密码接口
     */
    void password();
}
