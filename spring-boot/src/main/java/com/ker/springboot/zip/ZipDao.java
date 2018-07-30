package com.ker.springboot.zip;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class ZipDao {
    private List<Zip> zipCache = new ArrayList<>();

    // spring JDBC will auto close connection in case an exception is thrown while retrieving data
    // How does Spring boot know about info on H2? It sees H2 on classpath and it auto configures JDBC template to be able to connect to the H2 in mem DB
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void loadData(){
        zipCache = jdbcTemplate.query("select * from ZIP_DATA", new BeanPropertyRowMapper<>(Zip.class));
        log.info("Loaded following zips form DB : " + zipCache);
    }

    String getLocationForZip(final int zipCode){
        log.debug("Looking up info for {} zipcode", zipCode);
        for(Zip zip : zipCache){
            if(zip.getZip() == zipCode)
                return zip.getLocationInfo();
        }
        return null;
    }
}
