package lab.service;

import lab.dao.PersonDao;
import lab.model.MutablePerson;
import lab.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonDao personDao;

    @Transactional(readOnly = true, propagation = SUPPORTS, isolation = READ_COMMITTED)
    public List<Person> loadAllUsers() {
        return personDao.selectAll();
    }

    @Transactional(propagation = REQUIRES_NEW, isolation = SERIALIZABLE)
    public void save(MutablePerson person) {
        personDao.insert(person);
    }
}
