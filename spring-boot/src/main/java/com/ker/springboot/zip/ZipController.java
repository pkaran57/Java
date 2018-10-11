package com.ker.springboot.zip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZipController {

    @Autowired
    ZipCodeService zipCodeService;

    @GetMapping("/getZipData")
    public String getZipData(@RequestParam final int zipCode){
        return zipCodeService.getZipData(zipCode);
    }

    @GetMapping("/deleteZipData")
    public int deleteZipData(@RequestParam final int zipCode){
        return zipCodeService.deleteZipData(zipCode);
    }

    @GetMapping("/addZipData")
    public int addZipData(@RequestParam final int zipCode, @RequestParam final String locationData){
        return zipCodeService.addZipData(zipCode, locationData);
    }

    @GetMapping("/updateZipData")
    public int delete(@RequestParam final int zipCode, @RequestParam final String newLocationData){
        return zipCodeService.updateZipData(zipCode, newLocationData);
    }

    @GetMapping("/throwException")
    public void throwError() throws Exception {
        zipCodeService.throwException();
    }

    @GetMapping("/getAllZips")
    public List<Zip> getAllZips(){
        return zipCodeService.getAllZips();
    }
}
