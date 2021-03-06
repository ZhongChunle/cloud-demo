package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope // 配置刷新
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 读取nacos中的配置
     */
//    @Value("${pattern.dateformat}")
//    private String dateformat;

    /**
     * 注入获取属性
     */
    @Autowired
    PatternProperties patternProperties;

    /**
     * 根据获取的格式返回数据
     * @return
     */
    @GetMapping("now")
    public String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id,@RequestHeader(value = "Truth",required = false) String truth) throws InterruptedException {
        // 设置休眠时间
        if(id == 1){
            Thread.sleep(60);
        }else if(id == 2){
            throw new RuntimeException("故意抛出异常，触发异常比例");
        }
        return userService.queryById(id);
    }
}
