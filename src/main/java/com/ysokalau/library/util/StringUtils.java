package com.ysokalau.library.util;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * class for processing with strings
 */
public class StringUtils {

    /**
     * method that releases string[] to string
     *
     * @param array - String[]
     * @return - String
     */
    public static String arrayToString(String[] array){
        StringBuilder resultString = new StringBuilder();
        Arrays.stream(array).filter(s -> s != null && !"".equals(s)).forEach(s -> resultString.append(s).append(","));
        resultString.deleteCharAt(resultString.length()-1);
        return resultString.toString();
    }

    /**
     * method that releases string to string[]
     *
     * @param str - String
     * @return - String[]
     */
    public static String[] stringToArray (String str){
        return str.split(",").clone();
    }

    /**
     * Pattern for date
     */
    public static Pattern DATE_PATTERN = Pattern.compile(
            "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");

    /**
     * method for check string for null and empty
     *
     * @param str - String
     * @return - boolean
     */
    public static boolean checkString(String str){
        return str != null && !"".equals(str);
    }

    public static boolean checkArray(String[] array){
        return array != null && array.length > 0;
    }
}
