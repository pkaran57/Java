package com.ker.springboot.zip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZipCodeService {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ZipDaoJDBC zipDaoJDBC;

    String getZipData(final int zipCode){
        final String locationData = zipDaoJDBC.getLocationInfoForZip(zipCode);
        return locationData == null ? "Sorry, unable to find data for zip : " + zipCode : locationData;
    }

    void throwException() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    int deleteZipData(final int zipCode) {
        return zipDaoJDBC.deleteZip(zipCode);
    }

    int addZipData(final int zipCode, final String locationData) {
        return zipDaoJDBC.addZip(zipCode, locationData);
    }

    int updateZipData(final int zipCode, final String newLocationData) {
        return zipDaoJDBC.updateZipData(zipCode, newLocationData);
    }

    List<Zip> getAllZips(){
        return zipDaoJDBC.finalAllZips();
    }
}
