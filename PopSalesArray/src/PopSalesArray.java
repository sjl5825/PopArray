/*
 * Author: Spenser Lehman
 * Date Made: 01/30/2019
 * Java program that will bring a file in to be validated and printed out on two different files.
 * One file will have the errors "javapopper"
 * One file will have the correct inputs "javapopslb"
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Scanner;
import java.text.*;
import java.util.*;

public class PopSalesArray {
		static Scanner myScanner;
		static Scanner filescanner;
		static PrintWriter javapopslb;
		static PrintWriter javapopper;
		static double ctotal;
		static int ccases;
		static String lname;
		static String fname;
		static String iaddress;
		static String icity;
		static String istate;
		static String izip;
		static String ipoptype;
		static String icases;
		static String iteam;
		static double catotal;
		static double cbtotal;
		static double cctotal;
		static double cdtotal;
		static double cetotal;
		static int ccoke;
		static int cdietcoke;
		static int cmelloyello;
		static int ccherrycoke;
		static int cdietcherrycoke;
		static int csprite;
		static int index = 0;
		static String [] errorarray = {"Invalid Last Name", "Invalid First Name", "Invalid Address", "Invalid City", "Invalid State", "Invalid Zip", "Invalid Pop Type", "Invalid Cases", "Invalid Team"};
		static String [] statearray = {"IA", "IL" , "MI" , "MO" , "NE" , "WI"};
		static String [] teamname = {"A" , "B" , "C" , "D" , "E"};
		static double [] statedepositarray = {.05 , 0 , .10 , 0 , .05 , .05};
		static int [] popsalesqtyarray = new int [6];
		static double [] popsalesarray = new double [6];
		static double [] teamtotal = new double [5];
		static String [] poptypearray = {"COKE", "DIET COKE" , "MELLO YELLO" , "CHERRY COKE" , "DIET CHERRY COKE" , "SPRITE"};
		static double cdeposit;
		static Boolean eof = false;
		static int cpctr;
		static NumberFormat nf;	
		static String oteamtotal;
		static String oerror;
		static int errortotal;
	
	public static void main(String[] args) {
		
		init();
		
		while (eof == false) { 
			
		input();
		
		calcs();
		
		output();
		
		}
		
		totals();
		
		teamtotals();
		
		errorprinttotal();
		
		javapopslb.close();
		javapopper.close();
		
	}

	public static void init() {
		
		try {
			filescanner = new Scanner(new File("popsales.dat"));
			filescanner.useDelimiter(System.getProperty("line.separator"));
		} 
		//checking if its not being importing in 
		catch (FileNotFoundException e1) { 
			System.out.println("File error");
			System.exit(1);
		}
		//checking record
		try {
			javapopslb = new PrintWriter(new File ("JAVAPOPSLB.prt"));
			} 
		catch (FileNotFoundException e) {
				System.out.println("Output file error");
			}
		//checking record
		try {
			javapopper = new PrintWriter(new File ("JAVAPOPERB.prt"));
		}
		catch (FileNotFoundException e) {
			System.out.println("Output popper error.");
		}
		//initializing the arrays
		for (int i = 0; i < popsalesarray.length; i++) {
			popsalesarray[i] = 0;
		}
		//initializing the arrays
		for (int i = 0; i < popsalesqtyarray.length; i++) {
			popsalesqtyarray[i] = 0;
		}
		//initializing the arrays again
		for (int i = 0; i < teamtotal.length; i++) {
			teamtotal[i] = 0;
		}
		
		//numberformat
		nf = NumberFormat.getCurrencyInstance(java.util.Locale.US);
		//headings for all the printouts
		cpctr = cpctr + 1;
		javapopslb.format("%-6s%10s%30s%30s%50s%10s%5s\n" , "DATE:" , java.time.LocalDate.now() , " " , "POP SALES" , " " , "PAGE:" , cpctr);
		javapopslb.format("%61s%18s%20s\n\n" , " " , "POP SALE COUNT" , " " );
		javapopslb.format("%-20s%10s%-20s%10s%-10s%10s%10s%5s%10s%5s%15s%10s%15s%10s%15s%10s\n\n\n" , "LAST NAME", " " , "FIRST NAME" , " " , "CITY" , " " , "STATE" , " " , "ZIP CODE" , " " , "POP TYPE" , " " , "QUANTITY" , " " , "DEPOSIT AMT" , " " , "TOTAL SALES");
		javapopper.format("\n");
		javapopper.format("%-6s%10s%10s%30s\n" , "DATE:" , java.time.LocalDate.now() , " " , "POP SALES ERROR" );
		javapopper.format("%20s%20s%18s%20s\n\n" , " " , " " , "POP SALE ERROR RECORD" , " " );
		javapopper.format("%-20s%60s%61s\n\n" , "ERROR RECORD" , " " , "ERROR DESCRIPTION");
	}
		
	public static void input() { 
		String record;
		if (filescanner.hasNext()) {
			  record = filescanner.next();
			  		lname = record.substring(0,15);
			  		fname = record.substring(15,30);
			  		iaddress = record.substring(30,45);
			  		icity = record.substring(45,55);
			  		//try catch for the state
			  		try {
			  			istate = record.substring(55,57);
			  			for(int i = 0; i < statearray.length; i++) {
							if(istate.equals(statearray[i])) {
								index = i;
							}
						}
						if(index < 0) {
							oerror = errorarray[4];
							errorprint();
							return;
						}
			  		}
			  		catch (Exception e) {
			  			oerror = errorarray[4];
						errorprint();
			  		}
			  		try {
			  			izip = record.substring(57,66);
			  		}
			  		catch (Exception e) {
			  			oerror = errorarray[5];
						errorprint();
			  		}
			  		try {
			  			ipoptype = record.substring(66,67);
			  		}
			  		catch (Exception e) {
			  			oerror = errorarray[6];
			  			errorprint();
			  		}
			  		try {
			  			icases = record.substring(67,69);
			  		}
			  		catch (Exception e) { 
			  			oerror = errorarray[7];
			  			errorprint();
			  		}
			  		try {
			  			iteam = record.substring(69,70);
			  		}
			  		catch (Exception e) {
			  			oerror = errorarray[8];
			  			errorprint();
			  		}
				}
		else {
			eof = true;	
		     }	
		//parsing it for cases
		ccases = Integer.parseInt(icases);
		//validation to check if its empty
		if (lname.trim().isEmpty()) {
			oerror = errorarray[0];
			errorprint();
		}
		
		if (fname.trim().isEmpty()) {
			oerror = errorarray[1];
			errorprint();
		}
		
		if (iaddress.trim().isEmpty()) {
			oerror = errorarray[2];
			errorprint();
		}
		
		if (icity.trim().isEmpty()) {
			oerror = errorarray[3];
			errorprint();
		}
		
	  }
	
	public static void calcs() {
		
		//deposit calculation
		cdeposit = (ccases * 24) + statedepositarray[index];
		
		//case for the index to check for the subtotal and the qty
		switch (index) {
		
			case 0:
				ctotal = (18.75 * ccases) + cdeposit;
				popsalesqtyarray[0] = popsalesqtyarray[0] + ccases;
				break;
		
			case 1:
				ctotal = (18.75 * ccases) + cdeposit;
				popsalesqtyarray[1] = popsalesqtyarray[1] + ccases;
				break;
		
			case 2:
				ctotal = (18.75 * ccases) + cdeposit;
				popsalesqtyarray[2] = popsalesqtyarray[2] + ccases;
				break;
		
			case 3:
				ctotal = (18.75 * ccases) + cdeposit;
				popsalesqtyarray[3] = popsalesqtyarray[3] + ccases;
				break;
		
			case 4:
				ctotal = (18.75 * ccases) + cdeposit;
				popsalesqtyarray[4] = popsalesqtyarray[4] + ccases;
				break;
		
			case 5:
				ctotal = (18.75 * ccases) + cdeposit;
				popsalesqtyarray[5] = popsalesqtyarray[5] + ccases;
				break;
		
			default:
				System.out.println("Value is out of range between 1-6");
				break;
		}
		
		//case for the team totals
		switch (iteam) {
		
			case "A":
				teamtotal[0] = teamtotal[0] + ctotal;
				break;
		
			case "B":
				teamtotal[1] = teamtotal[1] + ctotal;
				break;
		
			case "C":
				teamtotal[2] = teamtotal[2] + ctotal;
				break;
			
			case "D":
				teamtotal[3] = teamtotal[3] + ctotal;
				break;
		
			case "E":
				teamtotal[4] = teamtotal[4] + ctotal;
				break;
		
			default:
				System.out.println("Invalid pop type");
				break;
		}
		
	}
		
	public static void output() {
		
		//detail line output
		javapopslb.format("%-20s%10s%-20s%10s%-10s%10s%10s%5s%10s%5s%15s%10s%15s%10s%15s%10s\n\n" , lname , " " , fname , " " , icity , " " , statearray[index] , " " , izip , " " , poptypearray[index] , " " , ccases , " " , cdeposit , " " , ctotal);
		
		
	}
		
	public static void totals() {
	
		//spacing 
		javapopslb.format("\n\n");
		
		//headings
		cpctr = cpctr + 1;
		javapopslb.format("%-6s%10s%30s%30s%50s%10s%5s\n" , "DATE:" , java.time.LocalDate.now() , " " , "POP SALES" , " " , "PAGE:" , cpctr);
		javapopslb.format("%61s%18s%20s\n\n" , " " , "POP SALE COUNT" , " " );
		javapopslb.format("%-20s%10s%-20s%10s%-10s%10s%10s%5s%10s%5s%15s%10s%15s%10s%15s%10s\n\n\n" , "LAST NAME", " " , "FIRST NAME" , " " , "CITY" , " " , "STATE" , " " , "ZIP CODE" , " " , "POP TYPE" , " " , "QUANTITY" , " " , "DEPOSIT AMT" , " " , "TOTAL SALES");
		javapopslb.format("%-20s\n\n" , "GRAND TOTALS:");
		
		int i;
		
		//listing the pop quantity
		for (i = 0; i < 3; i++) {
			javapopslb.format("%-10s%5s%10s%20s\t" , poptypearray[i] , " " , popsalesqtyarray[i] , " ");
			}
		//spacing them apart
		javapopslb.format("\n\n");
		
		for (i = 3; i < 6; i++) {
			javapopslb.format("%-10s%5s%10s%20s\t" , poptypearray[i] , " " , popsalesqtyarray[i] , " ");
		}
		
		javapopslb.format("\n\n\n\n");
	}
	
	
	public static void teamtotals() {
		
		javapopslb.format("%-20s\n\n" , "TEAM TOTALS:");
		//team totals print
		for (int i = 0; i < teamtotal.length; i++) {
			//formating my total
			oteamtotal = nf.format(teamtotal[i]);
			
			javapopslb.format("%-10s%5s%20s\n\n" , teamname[i] , " " , oteamtotal);
			
		}
	}
	
	public static void errorprint() {
		//my error report printout
		javapopper.format("%-15s%5s%-15s%5s%-10s%5s%3s%5s%5s%5s%15s%10s%5s%5s%5s%4s\t" , lname , " " , fname , " " , icity , " " , statearray[index] , " " , izip , " " , poptypearray[index] , " " , ccases , " " , cdeposit , " " , ctotal);
		javapopper.format("%-20s" , oerror);
		errortotal = errortotal + 1;
		//calling input to read the next record so it wont print out again
		input();
	}
	
	public static void errorprinttotal() {
		//error count
		javapopper.format("\n\n\n");
		javapopper.format("%-13s%10s" , "ERROR TOTALS:" , errortotal);
	}

}
