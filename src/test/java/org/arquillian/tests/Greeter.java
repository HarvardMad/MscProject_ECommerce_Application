package org.arquillian.tests;

/**
 * Created by LalinPethiyagoda on 05/09/2015.
 */

import java.io.PrintStream;


    /**
     * A component for creating personal greetings.
     */
    public class Greeter {
        public void greet(PrintStream to, String name) {
            to.println(createGreeting(name));
        }

        public String createGreeting(String name) {
            return "Hello, " + name + "!";
        }
    }
