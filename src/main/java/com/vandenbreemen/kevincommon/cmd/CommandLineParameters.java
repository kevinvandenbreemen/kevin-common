package com.vandenbreemen.kevincommon.cmd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineParameters {

    private HashMap<String, String> rawArguments;
    private HashMap<String, String> requiredArguments;

    public CommandLineParameters(String[] parameters) {
        this.requiredArguments = new HashMap<>();
        rawArguments = new HashMap<>();

        String currentArgument = null;
        String currentValue = null;
        for(String element : parameters) {
            if(element.startsWith("-")) {

                if(currentArgument != null) {
                    rawArguments.put(currentArgument, currentValue.trim());
                }

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

    public boolean flag(String flagName) {
        return rawArguments.containsKey(flagName);
    }

    public void addRequired(String parameterFlag, String documentation) {
        requiredArguments.put(parameterFlag, documentation);
    }

    public String document() {
        StringBuilder bld = new StringBuilder("Usage:\n");
        requiredArguments.entrySet().forEach(flagToDocumentation ->
                bld.append("-").append(flagToDocumentation.getKey()).append("\t").append(flagToDocumentation.getValue()).append("\n")
                );

        return bld.toString();
    }

    public boolean validate() {
        if(requiredArguments.keySet().stream().filter(k->!this.flag(k)).findAny().isPresent()){
            return false;
        }
        return true;
    }
}
