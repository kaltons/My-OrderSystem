package com.kaltons.order.logtest;

/**
 * TODO
 * LogBack日志框架初步测试
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 13:52
 */
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest2 {

    @Test
    public void test1(){
        String name = "kalton";
        String password = "123456";

        //系统默认日志级别是info，在info级别以下的不会打印输出
        log.debug("debug....");
        log.info("info....");
        log.error("error...");
        log.info("name: {},password: {}",name,password);
    }
}