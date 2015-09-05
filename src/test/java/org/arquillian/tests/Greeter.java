package org.arquillian.tests;

/**
 * Created by LalinPethiyagoda on 05/09/2015.
 */

import javax.inject.Inject;
import java.io.PrintStream;


    /**
     * A component for creating personal greetings.
     */
    public class Greeter {


        private PhraseBuilder phraseBuilder;

        @Inject //constructor injection
        public Greeter(PhraseBuilder phraseBuilder) {
            this.phraseBuilder = phraseBuilder;
        }

        public void greet(PrintStream to, String name) {
            to.println(createGreeting(name));
        }

        public void phraseGreet(PrintStream to, String res) {
            to.println(phraseGreeting(res));
        }

        public String phraseGreeting(String result){
            return result;
        }

        public String createGreeting(String name) {

            return "Hello, " + name + "!";
        }

        public String phraseResult(){
            return phraseBuilder.result();
        }
    }
