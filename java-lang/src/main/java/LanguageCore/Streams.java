package LanguageCore;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

@Log4j2
public class Streams {

    public static void demo(){
        int[] ints = new int[]{100,-43534,34,0};
        String[] strings = new String[]{"b", "B", null, "car", "bla"};

        int returned = Arrays.stream(ints)
                .map(num -> num * 2)
                .filter(num -> num > 0)
                            .findFirst()
                            .orElse(-1);

        log.debug("Got following int: {}", returned);
    }
}
