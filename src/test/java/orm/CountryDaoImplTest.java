package orm;

import lab.dao.CountryDao;
import lab.model.Country;
import lab.model.simple.SimpleCountry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:orm.xml")
class CountryDaoImplTest {

//	private static Log log = LogFactory.getLog(orm.CountryDaoImplTest.class);

	private Country exampleCountry = new SimpleCountry("Australia", "AU");

	@Autowired
	private CountryDao countryDao;

	@Test
	void testSaveCountry() {

        List<Country> countryList = countryDao.getAllCountries();
        int sizeBeforeSave = countryList.size();

        countryDao.save(exampleCountry);

        countryList = countryDao.getAllCountries();
		assertEquals(sizeBeforeSave + 1, countryList.size());
		assertEquals(exampleCountry, countryList.get(sizeBeforeSave));

//		countryDao.delete(exampleCountry);
//        assertEquals(0, countryDao.getAllCountries().size());
	}

	@Test
	void testGetAllCountries() {
        List<Country> countryList = countryDao.getAllCountries();
        int sizeBeforeSave = countryList.size();

        countryDao.save(new SimpleCountry("Canada", "CA"));

		countryList = countryDao.getAllCountries();
		assertEquals(sizeBeforeSave + 1, countryList.size());
	}

	@Test
	void testGetCountryByName() {

        countryDao.save(exampleCountry);
		Country country = countryDao.getCountryByName("Australia");
		assertEquals(exampleCountry, country);
	}

}
