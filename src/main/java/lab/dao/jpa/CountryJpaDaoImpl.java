package lab.dao.jpa;

import lab.dao.CountryDao;
import lab.model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

    @Override
    public void save(Country country) {
        withEntityManagerInTransaction(entityManager ->
                entityManager.persist(country));
    }

//    @Override
//    public void delete(Country country) {
//        withEntityManagerInTransaction(entityManager -> {
//            entityManager.remove(country);
//            entityManager.detach(country);
//        });
//    }

    @Override
    public List<Country> getAllCountries() {
        return mapEntityManager(entityManager ->
                entityManager.createQuery(
                        "SELECT c FROM SimpleCountry c",
                        Country.class)
                        .getResultList());
    }

    @Override
    public Country getCountryByName(String name) {
        return mapEntityManager(entityManager ->
                entityManager.createQuery(
                        "SELECT c FROM SimpleCountry c WHERE c.name LIKE :name",
                        Country.class)
                        .setParameter("name", name)
                        .getSingleResult());
    }

}
