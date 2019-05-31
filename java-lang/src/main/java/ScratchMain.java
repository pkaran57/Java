import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ScratchMain {

    public static void main(String[] args) throws IOException {
        final String pathname = "C:\\Users\\r631915\\Downloads\\IconsByRecognitionCategoryCodes";

        Arrays.stream(new File(pathname).listFiles())
                .forEach(
                        file -> {
                            String[] iconNames = file.listFiles() == null ? null : Arrays.stream(file.listFiles()).map(File::getName).toArray(String[]::new);
                            System.out.println("category code - " + file.getName() + ", icon name - " + Arrays.toString(iconNames));
                        });
    }

    private static void generateFolders() throws IOException {
        final String pathname = "C:\\Users\\r631915\\Downloads\\IconsByRecognitionCategoryCodes\\";
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\r631915\\Downloads\\codestemp.txt"), Charset.defaultCharset());
        lines.forEach(line -> new File(pathname + line).mkdir());
    }

    private static void printFivePartKeys() {
        String keyString = "530|AZL1|1609229764||26, 530|AZL1|1609229764||29, 530|AZL1|1164460077||11, 530|AZL1|1124143011||04, 530|AZL1|1609229764||27, 530|AZL1|1154355188||01, 530|AZL1|1164460077||03, 530|AZL1|1164460077||24, 530|AZL1|1164460077||29, 530|AZL1|1750664231||02, 530|AZL1|1750664231||04, 530|AZL1|1609229764||43, 530|AZL1|1770690695||01, 530|AZL1|1982733655||01, 530|AZL1|1750664231||03, 530|AZL1|1609229764||42, 530|AZL1|1891925582||02, 530|AZL1|1164460077||25, 530|AZL1|1124143011||03, 530|AZL1|1891888517||01, 530|AZL1|1164460077||18, 530|AZL1|1609229764||21, 530|AZL1|1124143011||06, 530|AZL1|1750664231||01, 530|AZL1|1952560914||01, 530|AZL1|1841224870||01, 530|AZL1|1164460077||07, 530|AZL1|1164460077||26, 530|AZL1|1043371826||01, 530|AZL1|1760480503||01, 530|AZL1|1164460077||09, 530|AZL1|1164460077||13, 530|AZL1|1922347723||01, 530|AZL1|1043371826||07, 530|AZL1|1972847663||01, 530|AZL1|1609229764||40, 530|AZL1|1528169125||01, 530|AZL1|1073576740||01, 530|AZL1|1164460077||19, 530|AZL1|1457802019||01, 530|AZL1|1073938817||01, 530|AZL1|1609229764||32, 530|AZL1|1164460077||30, 530|AZL1|1609229764||20, 530|AZL1|1093791170||01, 530|AZL1|1194119412||01, 530|AZL1|1750664231||05, 530|AZL1|1609229764||18, 530|AZL1|1508029422||01, 530|AZL1|1164460077||12, 530|AZL1|1124143011||05, 530|AZL1|1164460077||02, 530|AZL1|1609229764||36, 530|AZL1|1164460077||08, 530|AZL1|1588823553||01, 530|AZL1|1609229764||30, 530|AZL1|1902896236||11, 530|AZL1|1902896236||15, 530|AZL1|1609229764||28, 530|AZL1|1811951429||01, 530|AZL1|1609229764||24, 530|AZL1|1447644521||01, 530|AZL1|1609229764||34, 530|AZL1|1750664231||06, 530|AZL1|1326108515||04, 530|AZL1|1124143011||08, 530|AZL1|1386608859||01, 530|AZL1|1164460077||04, 530|AZL1|1902896236||19, 530|AZL1|1497852479||01, 530|AZL1|1902896236||13, 530|AZL1|1609229764||15, 530|AZL1|1982098406||01, 530|AZL1|1609229764||22, 530|AZL1|1043371826||06, 530|AZL1|1609229764||03, 530|AZL1|1720011810||01, 530|AZL1|1609229764||11, 530|AZL1|1164460077||05, 530|AZL1|1124143011||12, 530|AZL1|1164460077||20, 530|AZL1|1609229764||39, 530|AZL1|1609229764||14, 530|AZL1|1164460077||23, 530|AZL1|1952403834||01, 530|AZL1|1700910189||01, 530|AZL1|1609229764||04, 530|AZL1|1609229764||02, 530|AZL1|1164460077||01";

        keyString.replace(" ", "");
        final List<String> keys = Arrays.asList(keyString.split(","));

        for (String key : keys) {
            System.out.println("keys:" + StringUtils.trim(key));
        }
    }
}
