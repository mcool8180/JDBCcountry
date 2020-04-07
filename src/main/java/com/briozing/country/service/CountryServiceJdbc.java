package com.briozing.country.service;

import models.CountryRequestVO;
import models.CountryResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Map;

@Service

public class CountryServiceJdbc {
    private static JdbcTemplate jdbcTemplate = null;
    private CountryResponseVO countryResponseVo;

    @Autowired
    public CountryServiceJdbc(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }





    ///ADD
    public CountryResponseVO addCountry(String name){
        int id = jdbcTemplate.update("insert into country (name) values('"+name+"')");
        CountryResponseVO countryResponseVO=new CountryResponseVO();
        countryResponseVO.setName(name);
        return countryResponseVO;
    }

    public CountryResponseVO updateCountry(CountryRequestVO countryRequestVO, int id){
        int id1 = jdbcTemplate.update("UPDATE country SET name = '"+countryRequestVO.getName()+"' WHERE id = '"+id+"'");
        CountryResponseVO countryResponseVO=new CountryResponseVO();
        countryResponseVO.setName(countryRequestVO.getName());
        countryResponseVO.setId(id);
        return countryResponseVO;
    }

    public CountryResponseVO findCountryByName(String name) {
        String query = "select id, name from country where name = '" + name + "'";
        System.out.println("Query :"+query);
        Map<String ,Object>map=jdbcTemplate.queryForMap(query);
        CountryResponseVO countryResponseVO=new CountryResponseVO();
        countryResponseVO.setId(Integer.parseInt(String.valueOf(map.get("id"))));
        countryResponseVO.setName(String.valueOf(map.get("name")));
        System.out.println(countryResponseVO);
        return countryResponseVO;
    }

    public CountryResponseVO findCountryById(String id) {
        String query = "select id, name from country where id = '" + id + "'";
        System.out.println("Query : " + query);
        Map<String ,Object> map=jdbcTemplate.queryForMap(query);
        CountryResponseVO countryResponseVO=new CountryResponseVO();
        countryResponseVO.setId(Integer.parseInt(String.valueOf(map.get("id"))));
        countryResponseVO.setName(String.valueOf(map.get("name")));
        System.out.println(countryResponseVO);
        return countryResponseVO;
    }
    public static int delete(int id){
        String query="DELETE from country WHERE id=?";
        System.out.println("DELETE Query :- " + query);
        int deletedId = jdbcTemplate.update(query,id);

        return deletedId;
    }




}
