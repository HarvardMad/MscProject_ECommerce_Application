package org.arquillian.tests;

/**
 * Created by LalinPethiyagoda on 05/09/2015.
 * You won’t need the typical JUnit setup
 * and tearDown methods since
 * Arquillian will handle the heavy lifting.
 *
 * An Arquillian test case must have three things:
 * A @RunWith(Arquillian.class) annotation on the class
 * A public static method annotated with @Deployment that returns a ShrinkWrap archive
 * At least one method annotated with @Test
 */

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(Arquillian.class) // requirement # 1
public class GreeterTest {

    @Deployment //requirement #2 A static method that returns a ShrinkWrap archive
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(Greeter.class, PhraseBuilder.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @Inject
    Greeter greeter;

    /**
     * The @RunWith annotation tells JUnit to use Arquillian as the test controller.
     * Arquillian then looks for a public static method annotated
     * with the @Deployment annotation to retrieve the test archive (i.e., micro-deployment).
     * Then some magic happens and each @Test method is run inside the container environment.
     */
    @Test // at least one method annotated with @Test
    public void should_create_greeting() {
        Assert.assertEquals("Hello, Ding Dong!",
                greeter.createGreeting("Ding Dong"));
        greeter.greet(System.out, "Ding Dong");

        Assert.assertEquals("Hello, {0}!", greeter.phraseResult());
        greeter.phraseGreet(System.out, "Hello, {0}!");
    }
}