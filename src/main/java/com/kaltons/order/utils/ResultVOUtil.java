package com.kaltons.order.utils;

import com.kaltons.order.vo.ResultVO;

/**
 * TODO
 * 封装返回前端视图对象的工具类
 * @author 衍方
 * @desc
 * @date 2020/9/22 - 0:42
 */
public class ResultVOUtil {

    /**
     * 成功返回
     * @param list
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> success(T list) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setData(list);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    /**
     * 无值返回
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> success() {
        return success(null);
    }

    /**
     * 失败返回
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> error(Integer code, String msg) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
