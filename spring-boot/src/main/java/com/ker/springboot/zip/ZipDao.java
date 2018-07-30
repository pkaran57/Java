package com.ker.springboot.zip;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class ZipDao {
    private List<Zip> zipCache = new ArrayList<>();

    @Value("${load.zip.data.on.startup:false}")
    private boolean loadZipDataCacheOnStartup;

    // spring JDBC will auto close connection in case an exception is thrown while retrieving data
    // How does Spring boot know about info on H2? It sees H2 on classpath and it auto configures JDBC template to be able to connect to the H2 in mem DB
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostConstruct
    private void loadData(){
        if(loadZipDataCacheOnStartup){
            zipCache = jdbcTemplate.query("select * from ZIP_DATA", new BeanPropertyRowMapper<>(Zip.class));
            log.info("Loaded following zips form DB : " + zipCache);
        }
    }

    String getLocationForZip(final int zipCode){
        log.debug("Looking up info for {} zipcode", zipCode);

        if(loadZipDataCacheOnStartup){
            log.debug("Using zip data from cache");
            for(Zip zip : zipCache){
                if(zip.getZip() == zipCode)
                    return zip.getLocationInfo();
            }
        } else {
            log.debug("Fetching zip data from DB");
            final Zip zip = jdbcTemplate.queryForObject("select * from ZIP_DATA where zip=?", new Object[]{zipCode}, new BeanPropertyRowMapper<>(Zip.class));
            return zip != null ? zip.getLocationInfo() : null;
        }

        return null;
    }

    int deleteZips(final int zipToDelete){
        return jdbcTemplate.update("delete from zip_data where zip = ?", new Object[]{zipToDelete});
    }

    int addZipData(final int zip, final String locationData){
        return jdbcTemplate.update("insert into zip_data (zip, location_info) values (?,?)", new Object[]{zip, locationData});
    }

    int updateZipData(final int zip, final String newLocationData){
        return jdbcTemplate.update("update zip_data set location_info = ? where zip = ?", newLocationData, zip);
    }
}
