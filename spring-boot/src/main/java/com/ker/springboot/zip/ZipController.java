package com.ker.springboot.zip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZipController {

    @Autowired
    ZipCodeService zipCodeService;

    @GetMapping("/getZipData")
    public String getZipData(@RequestParam final String zipCode){
        return zipCodeService.getZipData(zipCode);
    }
}
