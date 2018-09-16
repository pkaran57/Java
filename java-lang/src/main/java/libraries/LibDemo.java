package libraries;

import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class LibDemo {
    public static void demo(){
        List<Integer> integerList = List.of(1, 2, 3, 4);
        log.debug(integerList);
    }
}
