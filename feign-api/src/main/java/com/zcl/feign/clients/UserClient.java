package com.zcl.feign.clients;

import com.zcl.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 项目名称：cloud-demo
 * 描述：feign客户端接口
 *
 * @author zhong
 * @date 2022-05-29 10:32
 */
@FeignClient("userserver")
public interface UserClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);
}
