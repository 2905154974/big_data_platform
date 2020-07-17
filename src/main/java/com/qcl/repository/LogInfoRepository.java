package com.qcl.repository;

import com.qcl.bean.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.OpenOption;
import java.util.List;

@Transactional
public interface LogInfoRepository extends JpaRepository<OperationLog , Integer>{

    @Modifying
    @Query(value = "insert into operation_log(operation_id,remark,create_time,update_time) VALUES (?1,?2,now(),now())",nativeQuery = true)
    int insertOperationLog(String a,String remark);

    /*查询总数*/
    @Query(value = "SELECT COUNT(id) FROM operation_log",nativeQuery = true)
    Integer countNum();

//   分页查询所有得数据
    @Query(value = "select * from operation_log order by create_time desc limit ?1,?2" ,nativeQuery = true)
    List<OperationLog> findAll(Integer page,Integer size);

//    @Query(value = "insert into operation_log(operation_id,remark) VALUES (?1,?2)",nativeQuery = true)
//    List<OperationLog> findAll();
}
