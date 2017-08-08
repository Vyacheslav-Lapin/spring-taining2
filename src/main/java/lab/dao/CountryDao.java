package lab.dao;

import lab.model.Country;
import lab.model.simple.SimpleCountry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Component
public class CountryDao extends NamedParameterJdbcDaoSupport {
    private static final String LOAD_COUNTRIES_SQL = "insert into country (name, code_name) values ";

    private static final String GET_ALL_COUNTRIES_SQL = "SELECT * FROM country";
    private static final String GET_COUNTRIES_BY_NAME_SQL = "SELECT * FROM country WHERE name LIKE :name";
    private static final String GET_COUNTRY_BY_NAME_SQL = "select * from country where name = '";
    private static final String GET_COUNTRY_BY_CODE_NAME_SQL = "select * from country where code_name = '";

    private static final String UPDATE_COUNTRY_NAME_SQL = "UPDATE country SET name='%s' WHERE code_name='%s'";

    public static final String[][] COUNTRY_INIT_DATA = {
            {"Russian Federation", "RU"},
            {"Australia", "AU"},
            {"Canada", "CA"},
            {"France", "FR"},
            {"Hong Kong", "HK"},
            {"Iceland", "IC"},
            {"Japan", "JP"},
            {"Nepal", "NP"},
            {"Sweden", "SE"},
            {"Switzerland", "CH"},
            {"United Kingdom", "GB"},
            {"United States", "US"}};

    private static final RowMapper<Country> COUNTRY_ROW_MAPPER =
            (rs, rowNum) -> new SimpleCountry(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("code_name"));

    public List<Country> getCountryList() {
        return getJdbcTemplate().query(
                GET_ALL_COUNTRIES_SQL,
                COUNTRY_ROW_MAPPER);
    }

    public List<Country> getCountryListStartWith(String name) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(
                "name", name + "%");
        return getNamedParameterJdbcTemplate().query(
                GET_COUNTRIES_BY_NAME_SQL,
                sqlParameterSource,
                COUNTRY_ROW_MAPPER);
    }

    public void updateCountryName(String codeName, String newCountryName) {
        getJdbcTemplate().execute(format(
                UPDATE_COUNTRY_NAME_SQL,
                newCountryName,
                codeName));
    }

    public void loadCountries() {
        for (String[] countryData : COUNTRY_INIT_DATA) {
            String sql = LOAD_COUNTRIES_SQL + "('" + countryData[0] + "', '"
                    + countryData[1] + "');";
//			System.out.println(sql);
            getJdbcTemplate().execute(sql);
        }
    }

    public Country getCountryByCodeName(String codeName) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();

        String sql = GET_COUNTRY_BY_CODE_NAME_SQL + codeName + "'";
//		System.out.println(sql);

        return jdbcTemplate.query(sql, COUNTRY_ROW_MAPPER).get(0);
    }

    public Country getCountryByName(String name)
            throws CountryNotFoundException {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        List<Country> countryList = jdbcTemplate.query(GET_COUNTRY_BY_NAME_SQL
                + name + "'", COUNTRY_ROW_MAPPER);
        if (countryList.isEmpty()) {
            throw new CountryNotFoundException();
        }
        return countryList.get(0);
    }
}