package com.vandenbreemen.kevincommon.cmd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandLineParameters {

    private HashMap<String, String> rawArguments;

    public CommandLineParameters(String[] parameters) {
        rawArguments = new HashMap<>();

        String currentArgument = null;
        String currentValue = null;
        for(String element : parameters) {
            if(element.startsWith("-")) {
                currentArgument = element.substring(1);
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
