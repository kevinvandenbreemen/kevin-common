# kevin-common
Common Java Code for Hobby Etc. Projects

# Major Components
## Commandline Parsing

```CommandLineParameters```

To parse command line parameters pass in the arguments of your main method into a CommandLineParameters object like this:

```
public static void main(String[] args) {
        CommandLineParameters params = new CommandLineParameters(args);
}
```

### Documenting required parameters
You can add documentation to the CommandLineParameters by calling the ```addRequired``` method, like this:

```
...
        CommandLineParameters params = new CommandLineParameters(args);
        params.addRequired("f", "Filename to process");
...
```

Once you've supplied your required flags/parameters you can use them either to validate the user's input, or to print out instructions on how to use your program:

```
if(!params.validate()){ //  If the arguments weren't valid print out some usage info
    System.out.println(params.document());
}
```

### Documenting situations where at least one of a group of parameters is required
You can indicate that "at least one of..." parameters is required using the ```addAtLeast()``` method, as in:

```
        CommandLineParameters p = new CommandLineParameters(parms);
        p.addAtLeast("f", "File name to process");
        p.addAtLeast("d", "Dir to process");
```

## No-Bullshit Logging

Logging framework without the hassle of missing configuration files or build path bullshit.  It's written in Kotlin but usable from Java.

Currently the logger prints logs with a date/time, a level, and whatever message you wish to log.

### Initializing the System
```
Logger.configuration().level = Level.TRACE
```

### Getting a Logger
```
val logger = Logger.getLogger(MyClass::class.java)
```

You can also use a regular string as in:

```
val logger = Logger.getLogger("my-logger")
```

### Logging

```

//  Log an exception
logger.error (error){ "FATAL ERROR OCCURRED" }

//  Regular logging
logger.info { "This is an info" }
```