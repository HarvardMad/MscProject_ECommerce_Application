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

import com.shop.entity.CustomerEntity;
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
                .addClasses(Greeter.class, PhraseBuilder.class, CustomerEntity.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @Inject
    Greeter greeter;

    /**
     * The @RunWith annotation tells JUnit to use Arquillian as the test controller.
     * Arquillian then looks for a public static method annotated
     * with the @Deployment annotation to retrieve the test archive (i.e., micro-deployment).
     * Then some magic happens and each @Test method is run inside the container environment.
     *
     * The @Deployment method is only mandatory for tests that run inside the container
     * and no extension is loaded that otherwise generates the test archive.
     * Client-side tests do not require a test archive, and therefore, do not require a @Deployment method.
     * TEST ARCHIVE
     * The purpose of the test archive is to isolate the classes and resources which are
     * needed by the test from the remainder of the classpath.
     * Unlike a normal unit test, Arquillian does not simply tap the entire classpath.
     * Instead, you include only what the test needs (which may be the entire classpath, if that’s what you decide).
     * The archive is defined using ShrinkWrap,
     * which is a Java API for creating archives (e.g., jar, war, ear) in Java. The micro-deployment strategy
     * lets you focus on precisely the classes you want to test. As a result, the test remains very lean and manageable.
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