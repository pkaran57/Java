package com.ker.springboot.zip;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Repository
public class ZipDao {
    private final Map<String, String> zipData = new HashMap<>();

    @PostConstruct
    private void loadData(){
        zipData.put("97220", "Portland, Oregon, US");
        zipData.put("400076", "Mumbai, Maharashtra, India");
    }

    String getLocationForZip(final String zipCode){
        log.debug("Looking up info for {} zipcode", zipCode);
        return zipData.get(zipCode);
    }
}
