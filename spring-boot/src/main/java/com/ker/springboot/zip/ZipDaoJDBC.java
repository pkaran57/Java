package com.ker.springboot.zip;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@Repository
public class ZipDaoJDBC {

    // spring JDBC will auto close connection in case an exception is thrown while retrieving data
    // How does Spring boot know about info on H2? It sees H2 on classpath and it auto configures JDBC template to be able to connect to the H2 in mem DB
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    String getLocationInfoForZip(final int zipCode){
        log.debug("Looking up info for {} zipcode", zipCode);

        log.debug("Fetching zip data from DB");
        final Zip zip = jdbcTemplate.queryForObject("select * from ZIP_DATA where zip=?", new Object[]{zipCode}, new BeanPropertyRowMapper<>(Zip.class));
        return zip != null ? zip.getLocationInfo() : null;
    }

    int deleteZip(final int zipToDelete){
        return jdbcTemplate.update("delete from zip_data where zip = ?", new Object[]{zipToDelete});
    }

    public int addZip(final int zip, final String locationData){
        return jdbcTemplate.update("insert into zip_data (zip, location_info) values (?,?)", new Object[]{zip, locationData});
    }

    int updateZipData(final int zip, final String newLocationData){
        return jdbcTemplate.update("update zip_data set location_info = ? where zip = ?", newLocationData, zip);
    }

    int updateZipData2(final int zip, final String newLocationData){
        Zip zipToUpdate = Zip.builder().zip(zip).locationInfo(newLocationData).build();
        String query = "update zip_data set location_info = :locationInfo where zip = :zip";
        return namedParameterJdbcTemplate.update(query, new BeanPropertySqlParameterSource(zipToUpdate));
    }

    List<Zip> finalAllZips(){
        return jdbcTemplate.query("select * from zip_data", new BeanPropertyRowMapper<>(Zip.class));
    }

    List<Zip> finalAllZips2(){
        return jdbcTemplate.query("select * from zip_data", this::zipRowMapper);
    }

    private Zip zipRowMapper(final ResultSet resultSet, int rowNumber) throws SQLException {
        return Zip.builder().zip(resultSet.getInt("ZIP")).locationInfo(resultSet.getString("LOCATION_INFO")).build();
    }
}
