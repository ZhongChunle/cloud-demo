package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import com.zcl.feign.clients.UserClient;
import com.zcl.feign.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 注入feign客户端
     */
    @Autowired
    UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2、利用RestTemplate发起http请求，查询用户
        User user = userClient.findById(order.getUserId());
        // 3、发送请求
        order.setUser(user);
        // 4.返回
        return order;
    }

    /**
     * 注入发起请求
     */
    /*@Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2、利用RestTemplate发起http请求，查询用户
        String url = "http://userserver/user/"+order.getUserId();
        // 3、发送请求
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);
        // 4.返回
        return order;
    }*/
}
