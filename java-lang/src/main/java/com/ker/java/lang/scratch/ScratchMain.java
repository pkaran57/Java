package com.ker.java.lang.scratch;

import java.io.IOException;

public class ScratchMain {

    public static void main(String[] args) throws IOException {

        printKeys();
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

}
