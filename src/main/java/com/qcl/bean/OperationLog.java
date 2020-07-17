package com.qcl.bean;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class OperationLog {

    //操作日志id
    //    @GeneratedValue(strategy= GenerationType.IDENTITY)
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SeqGenerator")
//    @SequenceGenerator(name = "SeqGenerator",sequenceName = " LogSequence")
    @Id
    @GeneratedValue
    private Integer id;
    //操作者id
    private String operationId;
//    //操作类型
//    private String type;
//    //操作对象
//    private String object;
    //备注
    private String remark;
//    //操作者姓名
//    private String operationName;
//    //日志类型名称
//    private String typeName;
//    //登录者id
//    private String userId;
    private String log_level;
    private String detial;
    //创建时间
    @CreatedDate//自动添加创建时间的注解
    private Date createTime;
    //修改时间
    @LastModifiedDate//自动添加更新时间的注解
    private Date updateTime;

}
