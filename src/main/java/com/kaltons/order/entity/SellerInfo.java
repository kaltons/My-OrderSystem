package com.kaltons.order.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 商家后台管理员信息
 *
 * @author 衍方
 * @link https://github.com/kaltons
 * @date 2020/9/29 - 1:18
 */
@Data
@Entity
public class SellerInfo {

    /**
     * 唯一编号
     */
    @Id
    private String sellerId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 微信用户openid
     */
    private String openid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
