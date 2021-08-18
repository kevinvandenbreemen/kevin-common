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

    @Test
    public void shouldProvideForFlags() {
        String[] parameters = new String[]{
                "testFile", "-F", "fred"
        };
        CommandLineParameters params = new CommandLineParameters(parameters);
        assertTrue(params.flag("F"));
    }

    @Test
    public void realWorldTest() {
        String[] parameters = new String[]{
                "-f", "/home/john/testbed/init.dat"
        };
        CommandLineParameters params = new CommandLineParameters(parameters);
        assertEquals("/home/john/testbed/init.dat", params.getArgument("f"));
    }

    @Test
    public void shouldParseMultipleArguments() {
        String[] parms = new String[] {
                "-f", "test", "-b", "build"
        };

        CommandLineParameters params = new CommandLineParameters(parms);
        assertEquals("test", params.getArgument("f"));
        assertEquals("build", params.getArgument("b"));
    }

    @Test
    public void shouldProvideForSelfDocumentation() {
        String[] parms = new String[] {
                "-f", "test", "-b", "build"
        };

        CommandLineParameters params = new CommandLineParameters(parms);
        params.addRequired("f", "filename to use");

        String documentation = params.document();
        assertEquals("Usage:\n-f\tfilename to use", documentation);

    }

    @Test
    public void shouldProvideForSelfDocumentationMultiline() {
        String[] parms = new String[] {
                "-f", "test", "-b", "build"
        };

        CommandLineParameters params = new CommandLineParameters(parms);
        params.addRequired("f", "filename to use");
        params.addRequired("g", "Golf");

        String documentation = params.document();
        assertEquals("Usage:\n-f\tfilename to use\n-g\tGolf", documentation.trim());

    }

    @Test
    public void shouldProvideForValidation(){
        String[] parms = new String[] {
                "-g", "test", "-b", "build"
        };

        CommandLineParameters params = new CommandLineParameters(parms);
        params.addRequired("f", "filename to use");

        assertFalse(params.validate());
    }

    @Test
    public void shouldAllowValidParametersThrough() {
        String[] parms = new String[] {
                "-f", "test", "-b", "build"
        };

        CommandLineParameters params = new CommandLineParameters(parms);
        assertTrue(params.validate());
    }

}