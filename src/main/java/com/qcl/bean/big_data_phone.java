package com.qcl.bean;

import com.qcl.meiju.FoodStatusEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 手机号
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class big_data_phone {
    @Id
    @GeneratedValue
    private Integer Id;//id
    private String Name;//名字
    private String Sex;//性别
    private String Phone;//手机号
    private Integer foodStatus = FoodStatusEnum.UP.getCode();//状态, 0正常1下架.
    private Integer leimuType;//菜品类目编号
    private Integer adminId;//菜品属于那个商家

    @CreatedDate//自动添加创建时间的注解
    private Date createTime;
    @LastModifiedDate//自动添加更新时间的注解
    private Date updateTime;


}
