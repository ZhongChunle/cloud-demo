package com.zcl.feign.clients.fallback;

import com.zcl.feign.clients.UserClient;
import com.zcl.feign.pojo.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目名称：cloud-demo
 * 描述：失败降级处理提示
 *
 * @author zhong
 * @date 2022-06-06 16:02
 */
@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {

    @Override
    public UserClient create(Throwable throwable) {
        return id -> {
            log.error("查询用户异常："+throwable);
            System.err.println("查询用户异常");
            return new User();
        };
    }
}
