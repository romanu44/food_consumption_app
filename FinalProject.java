/*
   Rodrigo Márquez, A01022943
   26/11/2018
   Programming Final Project
 */

import java.io.*;
import java.util.*;

public class FinalProject {
//instance variables
public static Scanner scan = new Scanner (System.in);
public static String username="", sure="", food="";
public static int action=0;
public static Double calories=0.0, read=0.0;
public static Double[][] consumption=new Double[8][2];
public static Boolean exit=false;
public static String[][] fruits=new String[13][5], dairy=new String[7][5], legumes=new String[7][5], sugars=new String[4][5], animals=new String[8][5], vegetables=new String[18][5], oils=new String[14][5], cereals=new String[18][5];

//process
public static void main(String[] args) throws IOException {
        scanFiles();
        System.out.print("Enter your name:\t");
        username= scan.next();
        /*
           The username will be needed to refer directly to the user and to save a personalized summary file.
         */

        System.out.println("\nWelcome to your eating disorder analysis app "+username+"!\n\nEnjoy!");
        while (true) {
                System.out.println("\nWould you like to:\n\n1.\tRegister food intake\n2.\tSee your summary\n3.\tReset your current data\n4.\tSet your limits\n5.\tRead the User Manual\n6.\tSee the Menu\n7.\tExit the app\n");
                try{
                        action=scan.nextInt();
                }//try end
                catch (InputMismatchException e) {
                        System.out.println("\nYou must enter the number, not words.");
                        action=-1;
                        scan.next();
                }//catch end
                switch (action) {
                case -1: break;
                case 1: register(); break;
                case 2: summary(); break;
                case 3: reset(); break;
                case 4: setLim(); break;
                case 5: seemanual(); break;
                case 6: printMenu(); break;
                case 7: exitapp(); exit=true; break;
                default: System.out.println("\nThat's not a valid option."); break;
                }//switch end
                if (exit) {break;}
        }//while end
         /*
            This is the main menu, in which the user can pick between the different methods the app can realize.
          */

        System.out.println("\nThank you for using my app, now yo can go back to eating without shame.");
}//main method end

//other methods
public static void exitapp() throws IOException {
        String summaryfile = "summary.txt";
        PrintWriter theSummary = new PrintWriter(summaryfile);
        theSummary.println(username+"\n");
        theSummary.println(consumption[0][1]+" of "+consumption[0][0]+" portions of Fruit.");
        theSummary.println(consumption[1][1]+" of "+consumption[1][0]+" portions of Dairy Products.");
        theSummary.println(consumption[2][1]+" of "+consumption[2][0]+" portions of Legumes.");
        theSummary.println(consumption[3][1]+" of "+consumption[3][0]+" portions of Sugars.");
        theSummary.println(consumption[4][1]+" of "+consumption[4][0]+" portions of Products of Animal Origin.");
        theSummary.println(consumption[5][1]+" of "+consumption[5][0]+" portions of Vegetables.");
        theSummary.println(consumption[6][1]+" of "+consumption[6][0]+" portions of Oils.");
        theSummary.println(consumption[7][1]+" of "+consumption[7][0]+" portions of Cereals.");
        theSummary.println("\nTotal calories:\t"+calories);
        theSummary.close();
}//exit app end
/*
   This method prints the summary in a file and then exits the app.
 */

public static void seemanual() throws IOException {
        Scanner manualfile = null;
        try{
                manualfile = new Scanner (new File("manual.txt"));
        }//try end
        catch(FileNotFoundException e) {
                System.out.println("File not found, figure it out yourself.");
                System.exit(0);
        }//catch end
        System.out.print("\n");
        while(manualfile.hasNextLine()) {
                System.out.println(manualfile.nextLine());
        }//while end
}//see manual end
/*
   This method prints the user manual, which is read from a file.
 */

public static void limits(){
        System.out.println("\nWrite the value for the limit of each category, entering a negative value will leave the current limit value as it is.\n");
        System.out.print("Fruits:\t\t");
        read=scan.nextDouble();
        if (read<0) {
                consumption[0][0]=consumption[0][0];
        }//default
        else{
                consumption[0][0]=read;
        }//set limit
        System.out.print("Dairy:\t\t");
        read=scan.nextDouble();
        if (read<0) {
                consumption[1][0]=consumption[1][0];
        }//default
        else{
                consumption[1][0]=read;
        }//set limit
        System.out.print("Legumes:\t");
        read=scan.nextDouble();
        if (read<0) {
                consumption[2][0]=consumption[2][0];
        }//default
        else{
                consumption[2][0]=read;
        }//set limit
        System.out.print("Sugars:\t\t");
        read=scan.nextDouble();
        if (read<0) {
                consumption[3][0]=consumption[3][0];
        }//default
        else{
                consumption[3][0]=read;
        }//set limit
        System.out.print("Animals:\t");
        read=scan.nextDouble();
        if (read<0) {
                consumption[4][0]=consumption[4][0];
        }//default
        else{
                consumption[4][0]=read;
        }//set limit
        System.out.print("Vegetables:\t");
        read=scan.nextDouble();
        if (read<0) {
                consumption[5][0]=consumption[5][0];
        }//default
        else{
                consumption[5][0]=read;
        }//set limit
        System.out.print("Oils:\t\t");
        read=scan.nextDouble();
        if (read<0) {
                consumption[6][0]=consumption[6][0];
        }//default
        else{
                consumption[6][0]=read;
        }//set limit
        System.out.print("Cereals:\t");
        read=scan.nextDouble();
        if (read<0) {
                consumption[7][0]=consumption[7][0];
        }//default
        else{
                consumption[7][0]=read;
        }//set limit
}//limits end
/*
   This method lets the user input new values for the limit in each category.
   If the user enters a negative number, the limit will keep its current value.
 */

public static void register(){
        String food="", units="";
        int countb=0, category=0, product=0;
        Double port=0.0, quantity=0.0, portions=0.0, cals=0.0, caloriesum=0.0;
        Boolean exitswitch=true, exitinvalid=true;
        System.out.println("\nWhat did you eat? (write 'nothing' if you didn't eat anything)\n");
        scan.nextLine(); //without this line '.nextLine' doesn't work
        food=scan.nextLine();
        if (food.equalsIgnoreCase("nothing")) {
                System.out.println("\nOk.");
        }//ate nothing
        else{
                for (countb=0; countb<fruits.length; countb++) {
                        if (food.equalsIgnoreCase(fruits[countb][0])) {
                                category=1;
                                product=countb;
                        }//if end
                        else{
                                category=category;
                                product=product;
                        }//else end
                }//for end
                for (countb=0; countb<dairy.length; countb++) {
                        if (food.equalsIgnoreCase(dairy[countb][0])) {
                                category=2;
                                product=countb;
                        }//if end
                        else{
                                category=category;
                                product=product;
                        }
                }
                for (countb=0; countb<legumes.length; countb++) {
                        if (food.equalsIgnoreCase(legumes[countb][0])) {
                                category=3;
                                product=countb;
                        }//if end
                        else{
                                category=category;
                                product=product;
                        }
                }
                for (countb=0; countb<sugars.length; countb++) {
                        if (food.equalsIgnoreCase(sugars[countb][0])) {
                                category=4;
                                product=countb;
                        }//if end
                        else{
                                category=category;
                                product=product;
                        }
                }
                for (countb=0; countb<animals.length; countb++) {
                        if (food.equalsIgnoreCase(animals[countb][0])) {
                                category=5;
                                product=countb;
                        }//if end
                        else{
                                category=category;
                                product=product;
                        }
                }
                for (countb=0; countb<vegetables.length; countb++) {
                        if (food.equalsIgnoreCase(vegetables[countb][0])) {
                                category=6;
                                product=countb;
                        }//if end
                        else{
                                category=category;
                                product=product;
                        }
                }
                for (countb=0; countb<oils.length; countb++) {
                        if (food.equalsIgnoreCase(oils[countb][0])) {
                                category=7;
                                product=countb;
                        }//if end
                        else{
                                category=category;
                                product=product;
                        }
                }
                for (countb=0; countb<cereals.length; countb++) {
                        if (food.equalsIgnoreCase(cereals[countb][0])) {
                                category=8;
                                product=countb;
                        }//if end
                        else{
                                category=category;
                                product=product;
                        }
                }//for end
                if (category==0) {
                        System.out.println("\nThat's not on the menu.");
                }//if end
                else{
                        while (exitinvalid) {
                                System.out.println("\nWould you like to enter quantity or portions?\n");
                                units=scan.next();
                                switch (units) {
                                case "portions":
                                        switch (category) {
                                        case 1:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+fruits[product][0]+" is equal to "+fruits[product][1]+".\nHow many portions did you eat?\n");
                                                        try{
                                                                portions=scan.nextDouble();
                                                                consumption[0][1]=consumption[0][1]+portions;
                                                                cals=Double.parseDouble(fruits[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        } //try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 2:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+dairy[product][0]+" is equal to "+dairy[product][1]+".\nHow many portions did you eat?\n");
                                                        try{
                                                                portions=scan.nextDouble();
                                                                consumption[1][1]=consumption[1][1]+portions;
                                                                cals=Double.parseDouble(dairy[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 3:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+legumes[product][0]+" is equal to "+legumes[product][1]+".\nHow many portions did you eat?\n");
                                                        try{
                                                                portions=scan.nextDouble();
                                                                consumption[2][1]=consumption[2][1]+portions;
                                                                cals=Double.parseDouble(legumes[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//whlie end
                                                break;
                                        case 4:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+sugars[product][0]+" is equal to "+sugars[product][1]+".\nHow many portions did you eat?\n");
                                                        try{
                                                                portions=scan.nextDouble();
                                                                consumption[3][1]=consumption[3][1]+portions;
                                                                cals=Double.parseDouble(sugars[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 5:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+animals[product][0]+" is equal to "+animals[product][1]+".\nHow many portions did you eat?\n");
                                                        try{
                                                                portions=scan.nextDouble();
                                                                consumption[4][1]=consumption[4][1]+portions;
                                                                cals=Double.parseDouble(animals[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 6:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+vegetables[product][0]+" is equal to "+vegetables[product][1]+".\nHow many portions did you eat?\n");
                                                        try{
                                                                portions=scan.nextDouble();
                                                                consumption[5][1]=consumption[5][1]+portions;
                                                                cals=Double.parseDouble(vegetables[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 7:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+oils[product][0]+" is equal to "+oils[product][1]+".\nHow many portions did you eat?\n");
                                                        try{
                                                                portions=scan.nextDouble();
                                                                consumption[6][1]=consumption[6][1]+portions;
                                                                cals=Double.parseDouble(oils[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 8:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+cereals[product][0]+" is equal to "+cereals[product][1]+".\nHow many portions did you eat?\n");
                                                        try{
                                                                portions=scan.nextDouble();
                                                                consumption[7][1]=consumption[7][1]+portions;
                                                                cals=Double.parseDouble(cereals[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        default:
                                                System.out.println("That's not on the menu."); break;
                                        }//portions switch end
                                        System.out.println("\nYou just consumed "+caloriesum+" calories.");
                                        exitinvalid=false;
                                        break;
                                case "quantity":
                                        switch (category) {
                                        case 1:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+fruits[product][0]+" is equal to "+fruits[product][1]+".\nHow many "+fruits[product][3]+" did you eat?\n");
                                                        try{
                                                                quantity=scan.nextDouble();
                                                                port=Double.parseDouble(fruits[product][4]);
                                                                portions=quantity/port;
                                                                consumption[0][1]=consumption[0][1]+portions;
                                                                cals=Double.parseDouble(fruits[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 2:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+dairy[product][0]+" is equal to "+dairy[product][1]+".\nHow many "+dairy[product][3]+" did you eat?\n");
                                                        try{
                                                                quantity=scan.nextDouble();
                                                                port=Double.parseDouble(dairy[product][4]);
                                                                portions=quantity/port;
                                                                consumption[1][1]=consumption[1][1]+portions;
                                                                cals=Double.parseDouble(dairy[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 3:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+legumes[product][0]+" is equal to "+legumes[product][1]+".\nHow many "+legumes[product][3]+" did you eat?\n");
                                                        try{
                                                                quantity=scan.nextDouble();
                                                                port=Double.parseDouble(legumes[product][4]);
                                                                portions=quantity/port;
                                                                consumption[2][1]=consumption[2][1]+portions;
                                                                cals=Double.parseDouble(legumes[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 4:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+sugars[product][0]+" is equal to "+sugars[product][1]+".\nHow many "+sugars[product][3]+" did you eat?\n");
                                                        try{
                                                                quantity=scan.nextDouble();
                                                                port=Double.parseDouble(sugars[product][4]);
                                                                portions=quantity/port;
                                                                consumption[3][1]=consumption[3][1]+portions;
                                                                cals=Double.parseDouble(sugars[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 5:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+animals[product][0]+" is equal to "+animals[product][1]+".\nHow many "+animals[product][3]+" did you eat?\n");
                                                        try{
                                                                quantity=scan.nextDouble();
                                                                port=Double.parseDouble(animals[product][4]);
                                                                portions=quantity/port;
                                                                consumption[4][1]=consumption[4][1]+portions;
                                                                cals=Double.parseDouble(animals[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 6:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+vegetables[product][0]+" is equal to "+vegetables[product][1]+".\nHow many "+vegetables[product][3]+" did you eat?\n");
                                                        try{
                                                                quantity=scan.nextDouble();
                                                                port=Double.parseDouble(vegetables[product][4]);
                                                                portions=quantity/port;
                                                                consumption[5][1]=consumption[5][1]+portions;
                                                                cals=Double.parseDouble(vegetables[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 7:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+oils[product][0]+" is equal to "+oils[product][1]+".\nHow many "+oils[product][3]+" did you eat?\n");
                                                        try{
                                                                quantity=scan.nextDouble();
                                                                port=Double.parseDouble(oils[product][4]);
                                                                portions=quantity/port;
                                                                consumption[6][1]=consumption[6][1]+portions;
                                                                cals=Double.parseDouble(oils[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        case 8:
                                                while (exitswitch) {
                                                        System.out.println("\nA portion of "+cereals[product][0]+" is equal to "+cereals[product][1]+".\nHow many "+cereals[product][3]+" did you eat?\n");
                                                        try{
                                                                quantity=scan.nextDouble();
                                                                port=Double.parseDouble(cereals[product][4]);
                                                                portions=quantity/port;
                                                                consumption[7][1]=consumption[7][1]+portions;
                                                                cals=Double.parseDouble(cereals[product][2]);
                                                                caloriesum=cals*portions;
                                                                calories=calories+caloriesum;
                                                                exitswitch=false;
                                                        }//try end
                                                        catch (InputMismatchException e) {
                                                                System.out.println("\nYou must only enter numbers.");
                                                                scan.next();
                                                        }//catch end
                                                }//while end
                                                break;
                                        default:
                                                System.out.println("That's not on the menu."); break;
                                        }//quantity switch end
                                        System.out.println("\nYou just consumed "+caloriesum+" calories.");
                                        exitinvalid=false;
                                        break;
                                default: System.out.println("\nThat's not a valid option."); break;
                                }//units switch end
                        }//while end
                }//else end
        }//else end
}//register end
/*
   This method lets the user input the product, searches the product and its category,
   if the product is not found, the app will tell the user the product is not in the menu,
   else, it will ask the user to choose between entering quantity of portions,
   the app will add the portions to the category and calculate the calories to add them to the total,
   the method prints the amount of calories consumed in each register.
 */

public static void summary(){
        System.out.println("\nYou have currently eaten:\n");
        System.out.println(consumption[0][1]+" of "+consumption[0][0]+" portions of Fruit.");
        System.out.println(consumption[1][1]+" of "+consumption[1][0]+" portions of Dairy Products.");
        System.out.println(consumption[2][1]+" of "+consumption[2][0]+" portions of Legumes.");
        System.out.println(consumption[3][1]+" of "+consumption[3][0]+" portions of Sugars.");
        System.out.println(consumption[4][1]+" of "+consumption[4][0]+" portions of Products of Animal Origin.");
        System.out.println(consumption[5][1]+" of "+consumption[5][0]+" portions of Vegetables.");
        System.out.println(consumption[6][1]+" of "+consumption[6][0]+" portions of Oils.");
        System.out.println(consumption[7][1]+" of "+consumption[7][0]+" portions of Cereals.");
        System.out.println("\nThat gives you a total of:\t"+calories+" calories.");
}//summary end
/*
   This method will print the current stats.
 */

public static void reset(){
        int countb=0;
        Boolean exitReset=true;
        while (exitReset) {
                System.out.print("\nAre you sure? (y/n):\t");
                sure=scan.next();
                switch (sure) {
                case "y":
                        for (countb=0; countb<8; countb++) {
                                consumption[countb][1]=0.0;
                        }//reset current consumption
                        calories=0.0;
                        System.out.println("\nYour data has been reset.");
                        exitReset=false;
                        break;
                case "n":
                        System.out.println("\nOk.");
                        exitReset=false;
                        break;
                default:
                        System.out.println("\nThat's not a valid option.");
                        break;
                }//switch end
        }//while end
}//reset end
/*
   This method resets the current consumed portions in each category, as well as the total amount of calories.
 */

public static void setLim() throws IOException {
        Scanner limitscan=null;
        int countb=0;
        Boolean exit2=true, exitsure=true;
        while(exit2) {
                System.out.println("\nDo you want to:\n\n1.\tReset your limits to default settings\n2.\tSet new limits for each category\n3.\tLeave them as they are\n");
                try{action=scan.nextInt();}
                catch(InputMismatchException e) {
                        System.out.println("\nYou must enter the number, not words.");
                        action=-1;
                        scan.next();
                }
                switch (action) {
                case -1: break;
                case 1:
                        while (exitsure) {
                                System.out.print("\nAre you sure? (y/n):\t");
                                sure=scan.next();
                                switch (sure) {
                                case "y":
                                        try{
                                                limitscan = new Scanner (new File("limits.txt"));
                                        }//try end
                                        catch(FileNotFoundException e) {
                                                System.out.println("File not found.");
                                                System.exit(0);
                                        }//catch end
                                        for (countb=0; countb<8; countb++) {
                                                consumption[countb][0]=limitscan.nextDouble();
                                        }//reset
                                        System.out.println("\nYour limits have been reset to default settings.");
                                        exitsure=false;
                                        break;
                                case "n":
                                        System.out.println("\nOk.");
                                        exitsure=false;
                                        break;
                                default: System.out.println("\nThat's not a valid option."); break;
                                }//switch end
                        }//while end
                        exit2=false;
                        break;
                case 2: limits();
                        System.out.println("\nYour new limits have been set.");
                        exit2=false;
                        break;
                case 3: System.out.println("\nYour limits have been kept the same.");
                        exit2=false;
                        break;
                default: System.out.println("\nThat's not a valid option."); break;
                }//switch end
        }//while end
}//set limits end
/*
   This method lets the user change the limits for each category, reset them or leave them as they are
 */

public static void scanFiles() throws IOException {
        Scanner fruitscan=null, dairyscan=null, legumescan=null, sugarscan=null, animalscan=null, vegetablescan=null, oilscan=null, cerealscan=null, limitscan=null;
        int counta=0, countb=0, countc=0;
        try{
                fruitscan = new Scanner (new File("fruits.txt"));
                dairyscan = new Scanner (new File("dairy.txt"));
                legumescan = new Scanner (new File("legumes.txt"));
                sugarscan = new Scanner (new File("sugars.txt"));
                animalscan = new Scanner (new File("animals.txt"));
                vegetablescan = new Scanner (new File("vegetables.txt"));
                oilscan = new Scanner (new File("oils.txt"));
                cerealscan = new Scanner (new File("cereals.txt"));
                limitscan = new Scanner (new File("limits.txt"));
        }//try end
        catch(FileNotFoundException e) {
                System.out.println("1 or more files not found.");
                System.exit(0);
        }//catch end
        for (countb=0; countb<8; countb++) {
                consumption[countb][0]=limitscan.nextDouble();
                consumption[countb][1]=0.0;
        }        //limits
        for (counta=0; counta<5; counta++) {
                for (countb=0; countb<fruits.length; countb++) {
                        fruits[countb][counta]=fruitscan.nextLine();
                }//analyse fruits file
                for (countb=0; countb<dairy.length; countb++) {
                        dairy[countb][counta]=dairyscan.nextLine();
                }
                for (countb=0; countb<legumes.length; countb++) {
                        legumes[countb][counta]=legumescan.nextLine();
                }
                for (countb=0; countb<sugars.length; countb++) {
                        sugars[countb][counta]=sugarscan.nextLine();
                }
                for (countb=0; countb<animals.length; countb++) {
                        animals[countb][counta]=animalscan.nextLine();
                }
                for (countb=0; countb<vegetables.length; countb++) {
                        vegetables[countb][counta]=vegetablescan.nextLine();
                }
                for (countb=0; countb<oils.length; countb++) {
                        oils[countb][counta]=oilscan.nextLine();
                }
                for (countb=0; countb<cereals.length; countb++) {
                        cereals[countb][counta]=cerealscan.nextLine();
                }
        }//for end
}//scan files end
/*
   This method scans the files containing the limits for each category and the menu,
   which contains the name, ammount in a portion, calories, units and the value of a portion for each product in each category.
 */

public static void printMenu(){
        int countc=0;
        System.out.println("\nMenu (product/portion/calories per portion):");
        System.out.println("\nFruits:\n");
        for (countc=0; countc<fruits.length; countc++) {
                System.out.print(fruits[countc][0]+"\t\t");
                System.out.print(fruits[countc][1]+"\t\t");
                System.out.println(fruits[countc][2]);
        }
        System.out.println("\nDairy:\n");
        for (countc=0; countc<dairy.length; countc++) {
                System.out.print(dairy[countc][0]+"\t\t");
                System.out.print(dairy[countc][1]+"\t\t");
                System.out.println(dairy[countc][2]);
        }
        System.out.println("\nLegumes:\n");
        for (countc=0; countc<legumes.length; countc++) {
                System.out.print(legumes[countc][0]+"\t\t");
                System.out.print(legumes[countc][1]+"\t\t");
                System.out.println(legumes[countc][2]);
        }
        System.out.println("\nSugars:\n");
        for (countc=0; countc<sugars.length; countc++) {
                System.out.print(sugars[countc][0]+"\t\t");
                System.out.print(sugars[countc][1]+"\t\t");
                System.out.println(sugars[countc][2]);
        }
        System.out.println("\nAnimals:\n");
        for (countc=0; countc<animals.length; countc++) {
                System.out.print(animals[countc][0]+"\t\t");
                System.out.print(animals[countc][1]+"\t\t");
                System.out.println(animals[countc][2]);
        }
        System.out.println("\nVegetables:\n");
        for (countc=0; countc<vegetables.length; countc++) {
                System.out.print(vegetables[countc][0]+"\t\t");
                System.out.print(vegetables[countc][1]+"\t\t");
                System.out.println(vegetables[countc][2]);
        }
        System.out.println("\nOils:\n");
        for (countc=0; countc<oils.length; countc++) {
                System.out.print(oils[countc][0]+"\t\t");
                System.out.print(oils[countc][1]+"\t\t");
                System.out.println(oils[countc][2]);
        }
        System.out.println("\nCereals:\n");
        for (countc=0; countc<cereals.length; countc++) {
                System.out.print(cereals[countc][0]+"\t\t");
                System.out.print(cereals[countc][1]+"\t\t");
                System.out.println(cereals[countc][2]);
        }
}//print menu end
/*
   This method prints the full menu scanned from the category files.
 */

}//class end
