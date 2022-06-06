package cn.itcast.order.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目名称：cloud-demo
 * 描述：拦截
 *
 * @author zhong
 * @date 2022-06-06 19:44
 */
@Component
public class HeaderOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        // 1、获取请求头
        String orgin = httpServletRequest.getHeader("orgin");
        // 2、判断请求头是否为空
        if(StringUtils.isEmpty(orgin)){
            // 手动设置请求头
            orgin = "blank";
        }
        return orgin;
    }
}
