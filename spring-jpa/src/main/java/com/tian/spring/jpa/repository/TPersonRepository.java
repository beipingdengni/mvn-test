package com.tian.spring.jpa.repository;

import com.tian.spring.jpa.jpa.TPersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query(value = "SELECT * FROM t_person WHERE age = ?1",
            countQuery = "SELECT count(*) FROM t_person WHERE age = ?1",
            nativeQuery = true)
    Page<TPersonEntity> findByLastname(String lastname, Pageable pageable);


    @Query(value = "SELECT * FROM t_person WHERE id > ?1", nativeQuery = true)
    Page<TPersonEntity> findTPersonById(int id, Pageable pageable);
}
