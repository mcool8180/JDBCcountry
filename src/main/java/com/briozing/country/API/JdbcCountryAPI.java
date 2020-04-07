package com.briozing.country.API;
import com.briozing.country.service.CountryServiceJdbc;
import com.briozing.country.service.countryService;
import models.CountryRequestVO;
import models.CountryResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country")

public class JdbcCountryAPI {
    CountryServiceJdbc countryServiceJdbc;

    public JdbcCountryAPI(@Autowired CountryServiceJdbc countryServiceJdbc){
        this.countryServiceJdbc=countryServiceJdbc;
    }
    @PostMapping(value="/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryResponseVO> addCountry(@RequestBody CountryRequestVO countryRequestVO){
        CountryResponseVO countryResponseVO= countryServiceJdbc.addCountry(countryRequestVO.getName());
        return new ResponseEntity<>(countryResponseVO, HttpStatus.OK);
    }


    @PutMapping(value="/updateById/{id}",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryResponseVO> updateById(@RequestBody CountryRequestVO countryRequestVO, @PathVariable int id){
        CountryResponseVO countryResponseVO=countryServiceJdbc.updateCountry(countryRequestVO,id);
        return new ResponseEntity<>(countryResponseVO,HttpStatus.OK);
    }


    @GetMapping(value="/findByName/{countryName}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryResponseVO> findByName(@PathVariable String countryName){
        System.out.println("Country Name : " + countryName);
        CountryResponseVO countryResponseVO= countryServiceJdbc.findCountryByName(countryName);
        return new ResponseEntity<>(countryResponseVO, HttpStatus.OK);
    }


    @GetMapping(value="/findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountryResponseVO> findById(@PathVariable String id){
        System.out.println("Country Id : " + id);
        HttpStatus status= HttpStatus.OK;
        CountryResponseVO countryResponseVO = null;
        try{
            countryResponseVO=countryServiceJdbc.findCountryById(id);
        }catch(Exception e){
            status = HttpStatus.NOT_FOUND;
            countryResponseVO = null;
        }
        return new ResponseEntity<>(countryResponseVO,status);
    }


@DeleteMapping(value = "/delete/{id}")
public ResponseEntity<String> delete(@PathVariable int id){
    HttpStatus status= HttpStatus.CREATED;
    String message = "ID is Deleted";
    try{
        int deletedId = CountryServiceJdbc.delete(id);
        if(deletedId==0){
            status = HttpStatus.NOT_FOUND;
            message ="Record Not Found";
        }
    }catch (Exception e){
        status=HttpStatus.NOT_FOUND;
        message="Record Not Found";
    }
    return new ResponseEntity<>(message,status);
}

}


