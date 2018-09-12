package com.tian.spring.jpa.services;
import java.util.ArrayList;

import com.tian.spring.jpa.jpa.TPersonEntity;
import com.tian.spring.jpa.repository.TPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * @author tianbeiping
 * @Title: PersonService
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/10下午4:08
 */
@Component
public class PersonService {

    @Autowired
    private TPersonRepository tPersonRepository;

    @Transactional(noRollbackFor = Exception.class)
    public List<TPersonEntity> getPerson() {

        List<TPersonEntity> all = tPersonRepository.findAll();
        List<TPersonEntity> byId = tPersonRepository.getByMyId(2);

        Pageable pageable=new QPageRequest(1,3);

        Page<TPersonEntity> tPersonById = tPersonRepository.findTPersonById(0, pageable);

        Iterator<TPersonEntity> iterator = tPersonById.iterator();
        while (iterator.hasNext()) {
            TPersonEntity next = iterator.next();

            System.out.println(next);
        }


        System.out.println("===========================");

        return all;
    }

}
