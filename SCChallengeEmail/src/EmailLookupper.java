import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;
public class EmailLookupper {
    private java.io.BufferedReader emailIdReader;
    private java.net.URL theURL;
    private Scanner theScanner;
    private String emailId;
    private String urlAsString;
    private java.io.BufferedReader websiteReader;
    private String nameString;
    public EmailLookupper() {
        System.out.println("Enter an email id.");
        emailIdReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            emailId = emailIdReader.readLine();

        } catch (IOException e) {
            System.out.println("Error with input.");
        }
        urlAsString = "https://www.ecs.soton.ac.uk/people/" + emailId;
       try{
           theURL = new URL(urlAsString);

       } catch(MalformedURLException e) {
           System.out.println("malformed url exception");
       }
       try {
           websiteReader = new BufferedReader(new InputStreamReader(theURL.openStream()));

       } catch (IOException e){
           System.out.println("Error with website");
       }
       String TestString;
       for (int i = 0; i < 13; i++) {
           try {

                websiteReader.readLine();

           } catch (IOException e) {

           }

       }



        try {
            nameString = websiteReader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading website source code.");
        }
        String finalString = nameString.substring(35, nameString.lastIndexOf("\""));

        System.out.println(finalString);
    }


}
