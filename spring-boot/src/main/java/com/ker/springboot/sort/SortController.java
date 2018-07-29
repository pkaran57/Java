package com.ker.springboot.sort;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class SortController {

    private final SortingService sortingService;
    private final ApplicationContext applicationContext;

    @Autowired
    public SortController(SortingService sortingService, ApplicationContext applicationContext) {
        this.sortingService = sortingService;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/sortInts")
    public int[] sortInts(int[] intsToSort){
        sortingService.sort(intsToSort);
        return intsToSort;
    }

    @GetMapping("/testSortService")
    public void testSortingService(){
        //SortingService sortingService = applicationContext.getBean(SortingService.class);
        sortingService.sort(new int[] {34, 99, 1, 0, -43, 3872732});
        sortingService.sort(new int[] {34, 99, 1, 0, -43, 3872732}); // Note that a new instance of bubble sort will be autowired even for the same instance of sortingService

        SortingService sortingService1 = applicationContext.getBean(SortingService.class);
        sortingService.sort(new int[] {34, 99, 1, 0, -43, 3872732});

        sortingService.listTestEnvUrl();

        log.info("sortingService = " + sortingService);
        log.info("sortingService1 = " + sortingService1);
    }
}
