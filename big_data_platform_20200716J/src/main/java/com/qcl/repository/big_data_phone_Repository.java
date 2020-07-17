package com.qcl.repository;

import com.qcl.bean.big_data_phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * niuyabo 20200319
 *
 */
@Repository
@Transactional //开启事务管理
public interface big_data_phone_Repository extends JpaRepository<big_data_phone, Integer> {

//    JpaSpecificationExecutor<big_data_phone>

//    分页查询所有得数据
    @Query(value = "select * from diancan.big_data_phone order by create_time desc limit ?1,?2" ,nativeQuery = true)
    List<big_data_phone> findAll(Integer page,Integer size);

    /*模糊查询手机号并分页*/
    @Query(value = "select * from diancan.big_data_phone where phone like CONCAT('%',?1,'%') order by create_time desc limit ?2,?3" ,nativeQuery = true)
    List<big_data_phone> findAllBigDataPhone(String Phone,Integer page,Integer size);

    /*模糊查询手机号*/
    @Query(value = "select * from diancan.big_data_phone where phone like CONCAT('%',?1,'%') order by create_time desc" ,nativeQuery = true)
    List<big_data_phone> findAllBigDataPhoneExport(String Phone);

    /*模糊查询总数*/
    @Query(value = "SELECT COUNT(id) FROM diancan.big_data_phone where phone like CONCAT('%',?1,'%')",nativeQuery = true)
    Integer count(String Phone);

    /*查询总数*/
    @Query(value = "SELECT COUNT(id) FROM diancan.big_data_phone",nativeQuery = true)
    Integer countNum();

}
