package lab.service;

import lab.dao.CountryDao;
import lab.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.*;

@Service("countryService")
@Transactional
@Data
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    @Autowired
	private CountryDao countryDao;

	@Override
	public List<Country> getAllCountriesInsideTransaction(
			Propagation propagation) {
		if (REQUIRED.equals(propagation)) {
			return getAllCountriesRequired();
		} else if (REQUIRES_NEW.equals(propagation)) {
			return getAllCountriesRequiresNew();
		} else if (SUPPORTS.equals(propagation)) {
			return getAllCountriesSupports();
		} else if (NEVER.equals(propagation)) {
			return getAllCountriesNever();
		} else if (MANDATORY.equals(propagation)) {
			return getAllCountriesMandatory();
		} else if (NOT_SUPPORTED.equals(propagation)) {
			return getAllCountriesNotSupported();
		} else {
			return getAllCountries();
		}
	}

    @Override
	public List<Country> getAllCountriesRequired() {
		return countryDao.getAllCountries();
	}

    @Override
	@Transactional(propagation = REQUIRES_NEW)
	public List<Country> getAllCountriesRequiresNew() {
		return countryDao.getAllCountries();
	}

    @Override
	@Transactional(propagation = SUPPORTS)
	public List<Country> getAllCountriesSupports() {
		return countryDao.getAllCountries();
	}

    @Override
	@Transactional(propagation = NEVER)
	public List<Country> getAllCountriesNever() {
		return countryDao.getAllCountries();
	}

    @Override
	@Transactional(propagation = MANDATORY)
	public List<Country> getAllCountriesMandatory() {
		return countryDao.getAllCountries();
	}

    @Override
	@Transactional(propagation = NOT_SUPPORTED)
	public List<Country> getAllCountriesNotSupported() {
		return countryDao.getAllCountries();
	}

    @Override
	public List<Country> getAllCountries() {
		return countryDao.getAllCountries();
	}

}