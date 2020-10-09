package com.kaltons.order.handler;

import com.kaltons.order.config.ProjectUrlConfig;
import com.kaltons.order.exception.SellException;
import com.kaltons.order.exception.SellerAuthorizeException;
import com.kaltons.order.utils.ResultVOUtil;
import com.kaltons.order.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录异常拦截
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/29 - 16:25
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    //http://sell.natapp4.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    //@ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }

    /**
     * 商品异常错误拦截
     * @param e
     * @return
     */
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
