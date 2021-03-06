package com.creditease.ns.log.util;

import java.io.*;
import java.text.MessageFormat;

/**
 * Created by liuyang on 2017/3/22.
 *
 * @author liuyang
 */
public class ConverterCodeGenerator {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
            new FileInputStream(new File("/Users/liuyou/work/宜信/杂七杂八/fuqianla字段.txt")), "utf-8"));

        String line = null;
        StringBuilder converterCodes = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {

            String[] columnNameAndType = parseLine(line);
            String logContantsKey = SimpleLoggerCodeGenerator.normalizeLogConstantsKeyName(columnNameAndType[0]);
            converterCodes.append(generateConverterCodes(logContantsKey));
        }

        System.out.printf(converterCodes.toString());
        bufferedReader.close();
    }


    private static String generateConverterCodes(String logContantsKey) {

        String template = "logContentAppend(stringBuilder, FuqianlaLogConstants.MDC_KEY_{0}, prefix, tail);\n";

        return MessageFormat.format(template,logContantsKey.toUpperCase());
    }


    private static String[] parseLine(String line) {
        String splitPattern = "\\s+";
        return line.split(splitPattern);
    }

}
