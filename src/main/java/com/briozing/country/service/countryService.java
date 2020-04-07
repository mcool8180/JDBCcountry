package com.briozing.country.service;

import models.CountryResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Map;
@Service

public class countryService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public countryService( DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }




    public CountryResponseVO findCountryByName(String name){
        return jdbcTemplate.queryForObject("select * from country where name = " + name, CountryResponseVO.class);
    }
}
