package com.ker.java.lang.scratch;

import com.google.common.io.Resources;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

@Slf4j
public class ScratchMain {

    public static void main(String[] args) throws IOException {

        generateMissingHTLabels();
    }

    private static void printKeys() {
        String keys =
            "1835086-1206889, 1835000-1206751, 1830232-1198736, 1828293-1195804, 1766692-1064481, 990824-638952, 1583077-298336, 1775122-1083786, 1775141-1083822, 1261738-638952, 465928-522211, 1804203-1143469, 1804137-1143155, 1775129-1083807, 1775125-1083796, 1775293-1084050, 1825660-1191948, 4181894-5731095, 1804357-1144007";

        keys = keys.replaceAll(" ", "");
        final String[] split = keys.split(",");

        for (String s : split) {
            System.out.println("keys:" + s);
        }
    }

    private static void generateMissingHTLabels() throws IOException {
        final String json = new String(Resources.toByteArray(Resources.getResource("translations.json")));
        final Configuration config = Configuration.builder()
                                  .mappingProvider(new JacksonMappingProvider())
                                  .jsonProvider(new JacksonJsonProvider())
                                  .build();

        final Filter translationsWithNoHTLabelFilterFilter = filter(where("labels.ht_HT").exists(false)).or(where("labels.ht_HT").eq(null));
        final Filter translationsWithENLabelFilter = filter(where("labels.en_US").exists(true)).and(where("labels.en_US").ne(null));


        final List<Translation> translations = JsonPath
                .using(config)
                .parse(json)
                .read(JsonPath.compile("$.body[?,?]", translationsWithENLabelFilter, translationsWithNoHTLabelFilterFilter), new TypeRef<List<Translation>>(){});

        FileWriter out = new FileWriter("C:\\K.E.R Projects\\java\\java-lang\\src\\main\\resources\\Missing Haitian Creole Labels.csv");

        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader("Label Key", "Label in English"))) {
      translations.forEach(
          translation -> {
            try {
              if (StringUtils.isNotBlank(translation.getLabels().getEn_US())){
                  printer.printRecord(translation.getKey(), translation.getLabels().getEn_US());
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          });
        }


        translations.forEach(System.out::println);
    }

}
