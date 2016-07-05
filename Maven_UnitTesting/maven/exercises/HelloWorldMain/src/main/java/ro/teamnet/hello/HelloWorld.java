package ro.teamnet.hello;

import org.apache.log4j.Logger;

/**
 * Created by user on 7/5/2016.
 */
public class HelloWorld {

    static final Logger logger = Logger.getLogger(HelloWorld.class.getName());

    /**
     * method for saying hello
     */
    public void sayHello(){

        logger.debug("DEBUG -> Enters in sayHello() method from HelloWorld");
        logger.info("INFO -> Enters in returnHelloKey from HelloWorld");

        System.out.println("Hello World!");
    }

    /**
     * method for returning a key
     * @return - The HelloWorld key
     */
    public String returnHelloKey(){
        return "HelloKey";
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }

}
