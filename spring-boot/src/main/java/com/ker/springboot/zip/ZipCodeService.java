package com.ker.springboot.zip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZipCodeService {

    @Autowired
    ZipDao zipDao;

    String getZipData(final int zipCode){
        final String locationData = zipDao.getLocationForZip(zipCode);
        return locationData == null ? "Sorry, unable to find data for zip : " + zipCode : locationData;
    }

    void throwException() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    int deleteZipData(final int zipCode) {
        return zipDao.deleteZips(zipCode);
    }

    int addZipData(final int zipCode, final String locationData) {
        return zipDao.addZipData(zipCode, locationData);
    }

    public int updateZipData(final int zipCode, final String newLocationData) {
        return zipDao.updateZipData(zipCode, newLocationData);
    }
}
