package com.kaltons.order.logtest;

/**
 * TODO
 * LogBack日志框架初步测试
 * @author 衍方
 * @desc
 * @date 2020/9/21 - 13:52
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {

    public final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void test1(){
        //系统默认日志级别是info，在info级别以下的不会打印输出
        logger.debug("debug....");
        logger.info("info....");
        logger.error("error...");
    }
}