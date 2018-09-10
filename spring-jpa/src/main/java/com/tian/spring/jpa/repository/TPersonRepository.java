package com.tian.spring.jpa.repository;

import com.tian.spring.jpa.jpa.TPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tianbeiping
 * @Title: TPersonRepository
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/10下午4:05
 */
//@Repository
public interface TPersonRepository extends JpaRepository<TPersonEntity, Integer> {

    @Query(value = "select * from t_person where id>:id", nativeQuery = true)
    List<TPersonEntity> getByMyId(@Param("id") Integer id);


    @Modifying
    @Query(value = "delete * from t_person where id=:id", nativeQuery = true)
    int deleteByMyId(@Param("id") Integer id);
}
