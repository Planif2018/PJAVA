/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.strategic.planning.util.code;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author luis
 */
public class StandardCode {

    public static String generatedCode(String entity, Object obj) {
        String code = entity + RandomStringUtils.randomAlphabetic(4).toUpperCase() + obj.hashCode() + new Date().getTime();
        return code;
    }

    public static String RandomStringColor() {
        String color = "#";
        List<String> hex = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            hex.add("" + i);
        }
        hex.add("A");
        hex.add("B");
        hex.add("D");
        hex.add("E");
        hex.add("F");
        Random x = new Random();
        for (int i = 0; i < 6; i++) {
            color += hex.get((x.nextInt(hex.size() - 1)));
        }
        return color;
    }
}
