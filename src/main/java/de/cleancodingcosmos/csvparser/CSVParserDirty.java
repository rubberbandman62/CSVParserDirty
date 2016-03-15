package de.cleancodingcosmos.csvparser;

import java.util.ArrayList;

public class CSVParserDirty {

    public ArrayList<String> parseLine(String str) {
        ArrayList<String> result = new ArrayList<>();
        str = "°" + str + "°";
        String[] parts = str.split(";");
        if (parts.length > 0) {
            parts[0] = parts[0].substring(1);
            parts[parts.length-1] = parts[parts.length-1].substring(0, parts[parts.length-1].length()-1);
        }


        for (int i = 0; i < parts.length; i++) {
            int c = count(parts[i]);
            if (c == 0) {
                result.add(parts[i]);
            }
            else if (c % 2 == 1) {
                String temp = parts[i];
                int j = i+1;
                while (j < parts.length && count(parts[j]) % 2 == 0) {
                    temp += ';' + parts[j++];
                }
                if (j < parts.length) temp += ';' + parts[j];
                result.add(clean(temp));
                i = j;
            }
            else if (c % 2 == 0) {
                result.add(clean(parts[i]));
            }
        }
        return result;
    }


    private int count(String str) {
        int pos = -1, counter = 0;
        while ((pos = str.indexOf('"', pos+1)) >= 0) {
            counter++;
        }
        return counter;
    }


    private String clean(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '"') {
                if (i > 0 && i < str.length()-1) sb.append('"');
            }
            else sb.append(str.charAt(i));
        }
        return sb.toString().replaceAll("\"\"", "\"");
    }

}


