package com.briozing.country.API;


import models.CountryRequestVO;
import models.CountryResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
// Data is stored in list

@RestController
@RequestMapping("/countryList")

public class CountryAPI {
    private List<String >countries= new ArrayList<>();

    @PostMapping(value="/add",consumes={MediaType.APPLICATION_JSON_VALUE}, produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CountryResponseVO> addCountry(@RequestBody CountryRequestVO countryRequestVO){

        countries.add(countryRequestVO.getName());
        CountryResponseVO countryResponseVO= new CountryResponseVO();
        countryResponseVO.setName((countryRequestVO.getName()));
        countryResponseVO.setId(countries.size());
        return  new ResponseEntity<>(countryResponseVO, HttpStatus.OK);
    }






    @GetMapping(value="/list/{id}", produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CountryResponseVO> getCountry(@PathVariable int id){
        CountryResponseVO countryResponseVO=new CountryResponseVO();
        HttpStatus status=HttpStatus.OK;
        try {
            String countryName = countries.get(id - 1);

            countryResponseVO.setName(countryName);
            countryResponseVO.setId(id);
        }catch(Exception ex) {
            countryResponseVO.setId(id);
            status= HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(countryResponseVO, status);

    }
    @PutMapping(value="/update", produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CountryResponseVO> putCountry(@RequestBody CountryRequestVO countryRequestVO, @PathVariable int id){
        countries.add(countryRequestVO.getName());
        CountryResponseVO countryResponseVO= new CountryResponseVO();
        countryResponseVO.setName((countryRequestVO.getName()));
        countryResponseVO.setId(countries.size());
        return  new ResponseEntity<>(countryResponseVO, HttpStatus.OK);
    }

    @DeleteMapping(value="/delete", produces ={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CountryResponseVO> deleteCountry(@RequestBody CountryRequestVO countryRequestVO, @PathVariable int id){
        countries.add(countryRequestVO.getName());
        CountryResponseVO countryResponseVO= new CountryResponseVO();
        countryResponseVO.setName((countryRequestVO.getName()));
        countryResponseVO.setId(countries.size());
        return  new ResponseEntity<>(countryResponseVO, HttpStatus.OK);
    }



}
