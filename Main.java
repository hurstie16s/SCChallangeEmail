package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Email Address : ");
        String email = bufferedReader.readLine();
        String username = email.substring(0,email.indexOf('@'));

        URL url = new URL("https://www.ecs.soton.ac.uk/people/" + username);

        BufferedReader webReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line= webReader.readLine();
        String name=null;
        String school = null;
        do{
            if(line.contains("property=\"name\"")){
                name = line.substring((line.indexOf("property=\"name\">")+16),line.indexOf("<em property=\"honorificSuffix\""));
                school = line.substring((line.indexOf("class=\"uos-breadcrumb-child\"><a href='/'>")+41),line.indexOf("</a></div>&gt;</nav><dl class=\"uos-page-meta-capsule\">"));
                break;
            }
        }
        while((line= webReader.readLine())!=null);

        if(name==null){
            System.out.println("Error:Information Not Found");
        }
        else {
            System.out.println("Name : " + name);
            System.out.println("Working at School of "+school);
        }
    }
}