package com.vandenbreemen.kevincommon.cmd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineParameters {

    private HashMap<String, String> rawArguments;

    public CommandLineParameters(String[] parameters) {
        rawArguments = new HashMap<>();

        String currentArgument = null;
        String currentValue = null;
        for(String element : parameters) {
            if(element.startsWith("-")) {

                Pattern dashPattern = Pattern.compile("([-]*)");
                Matcher matcher = dashPattern.matcher(element);
                if(matcher.find()) {
                    String group = matcher.group(1);
                    currentArgument = element.substring(group.length());
                } else {
                    currentArgument = element.substring(1);
                }
                currentValue = "";
            } else {
                currentValue += " ";
                currentValue += element;
            }
        }

        if(currentArgument != null) {
            rawArguments.put(currentArgument, currentValue.trim());
        }
    }

    public String getArgument(String argName) {
        return rawArguments.get(argName);
    }
}
