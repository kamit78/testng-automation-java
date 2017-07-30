package com.globallogic.velocity.automation.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtil {

    public static List<Object[]> getCSVDataAsMap(String strFile) {
        ArrayList<Object[]> rows = new ArrayList<>();
        BufferedReader br = null;
        try {

            File csvFile = new File(strFile);
            br = new BufferedReader(new FileReader(csvFile));
            String strLine = "";
            char separatorChar = ',';
            // read comma separated file line by line
            // exclude blank lines and comments
            String[] colsNames = null;
            while ((strLine = br.readLine()) != null) {
                if (!("".equalsIgnoreCase(strLine.trim()) || "#!".contains("" + strLine.trim().charAt(0)))) {
                    if (colsNames == null) {
                        colsNames = parseCSV(strLine, separatorChar);
                    } else {
                        String[] cols = parseCSV(strLine, separatorChar);
                        HashMap<String, String> map = new HashMap<>();
                        for (int i = 0; i < cols.length; i++) {
                            try {
                                map.put(colsNames[i].trim(), cols[i]);
                            } catch (ArrayIndexOutOfBoundsException e) {

                                map.put(String.valueOf(i), cols[i]);

                            }
                        }
                        rows.add(new Object[]{map});

                    }
                } else if (strLine.contains("col.separator")) {
                    separatorChar = strLine.split("=")[1].trim().charAt(0);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return rows;
    }

    public static Object[][] getCSVDataAsArray(String strFile) {

        BufferedReader br = null;
        //Object[][] objArray = null;
        List<Object[]> objArray = new ArrayList<>();
        File file = new File(strFile);
        String strRead = "";
        String[] colsNames = null;
        char separatorChar = ',';
        int row_counter = 0;
        Object[][] tarray = null;

        try {

            br = new BufferedReader(new FileReader(file));
            while ((strRead = br.readLine()) != null) {

                if (colsNames == null) {

                    colsNames = parseCSV(strRead, separatorChar);

                } else {
                    String[] cols = parseCSV(strRead, separatorChar);

                    objArray.add(cols);

                }

            }

            tarray = new Object[objArray.size()][];
            tarray = objArray.toArray(tarray);

        } catch (IOException e) {

            System.out.println(e.getMessage());
        } finally {

            if (br != null) {

                try {
                    br.close();
                } catch (IOException e) {

                    System.out.println(e.getMessage());
                }
            }

        }

        return tarray;

    }

    public static String[] parseCSV(String data, char... ch) {
        List<String> values = new ArrayList<>();
        char seperator = ((null == ch) || (ch.length < 1) ? ',' : ch[0]);
        char escapeChar = ((null == ch) || (ch.length < 2) ? '\\' : ch[1]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length(); ++i) {
            char c = data.charAt(i);
            if (c == seperator) {
                values.add(sb.toString());
                sb = new StringBuilder();
                continue;
            } else if (c == escapeChar) {
                ++i;
                c = data.charAt(i);
            }
            sb.append(c);
        }
        values.add(sb.toString());

        return (values.toArray(new String[values.size()]));
    }


}
