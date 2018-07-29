package com.ker.springboot.sort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SortController {

    private static final Logger LOGGER = LogManager.getLogger(SortController.class);
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

        LOGGER.info("sortingService = " + sortingService);
        LOGGER.info("sortingService1 = " + sortingService1);
    }
}
