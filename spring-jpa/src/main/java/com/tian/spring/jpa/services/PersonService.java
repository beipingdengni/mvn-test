package com.tian.spring.jpa.services;

import com.tian.spring.jpa.jpa.TPersonEntity;
import com.tian.spring.jpa.repository.TPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        return all;
    }

}
