package com.vandenbreemen.kevincommon.cmd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineParametersTest {

    @Test
    public void shouldParseCommandLineParameters() {
        String[] parameters = new String[]{
                "-c",
                "help", "me"
        };
        CommandLineParameters params = new CommandLineParameters(parameters);
        assertEquals("help me", params.getArgument("c"));
    }

    @Test
    public void shouldHandleNoCommandLineParameters() {
        CommandLineParameters params = new CommandLineParameters(new String[]{});
        assertNull(params.getArgument("h"));
    }

    @Test
    public void shouldHandleDoubleDashParameter() {
        String[] parameters = new String[]{
                "--c",
                "help", "me"
        };
        CommandLineParameters params = new CommandLineParameters(parameters);
        assertEquals("help me", params.getArgument("c"));
    }

}