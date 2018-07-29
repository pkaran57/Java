package com.ker.springboot.zip;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ZipDao {
    private final Map<String, String> zipData = new HashMap<>();

    @PostConstruct
    private void loadData(){
        zipData.put("97220", "Portland, Oregon, US");
        zipData.put("400076", "Mumbai, Maharashtra, India");
    }

    String getLocationForZip(final String zipCode){
        return zipData.get(zipCode);
    }
}
