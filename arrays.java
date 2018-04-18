import java.lang.*;
import java.util.Scanner;


public class arrays {

	public static class IndividualContribution {
	
		
public static String GroupNumber = "Fourteen";
		private String FullName;
		private String NetID;
		
		public String getFullName(){
			return FullName;
		}
		
		public String NetID(){
			return NetID;
		}
		
		public void Q1(){
			
		}
		public void Q2(){
			
		}
		public void Q3(){
			
		}
		public void Q4(){
			
		}
		
	public static class Richard extends IndividualContribution{
		
		public Richard(String FullName, String NetID) {
			super.FullName = FullName;
			super.NetID = NetID;
		}
		
		public void Q1(){
			
			// Initiate the string that will be flipped
			String reverseAndflip;
			
			//Initiate the object that manipulates strings
			StringBuilder input = new StringBuilder();
			
			//Initiate the object that takes in Strings
			Scanner scan = new Scanner(System.in);
			
			//Prints for user input, takes in user input
			System.out.println("Please enter your string: ");
			reverseAndflip = scan.nextLine();
			
			//Uses a Stringbuilder method and standard method to manipulate the String
			input.append(reverseAndflip);
			input = input.reverse();
			
			//Prints out the manipulated String
			System.out.println(input);
			
		}
			
		}

	public void  Q4() {
	}

	public static void main(String[] args) {
		//Required variable declarations
		double basePay;
		int hoursWorked;
		double totalPay = 0;
		double realHourlyPayment = 0;
		
		//Variable declarations and initializations for average calculations
		double totaltotalPay = 0;
		double totalBasePay = 0;
		double totalHoursWorked = 0;
		double realHourlyPaymentTotal = 0;
		
		//Divisor that kept track of the amount of entries 
		int i = 0;
		
		//Scanner object creation to take user inputs
		Scanner scan = new Scanner(System.in);
		
		//Take inputs and perform calculations per requirements
		do {	
			System.out.print("How many hours did you work? ");
			hoursWorked = scan.nextInt();
			if (hoursWorked > 0 ) {
				totalHoursWorked = totalHoursWorked + hoursWorked;
			} 

			System.out.print("What is your hourly rate or base pay? ");
			basePay = scan.nextDouble();
			if (basePay > 0) {
				totalBasePay = totalBasePay + basePay;
			}
			
			if ((hoursWorked > 0) && (basePay > 0)) {
				i++;
			}

			if (basePay < 8) {
				if (basePay > 0) {
					totalPay = (basePay * 1.5) *((double)hoursWorked);
					System.out.println("Recheck Base Payment!");
				}
			}

			if (hoursWorked > 40) {
				if (hoursWorked > 70) {
					totalPay = (basePay * 1.5) *((double)hoursWorked);
					System.out.print("Supervisor's Apprival Required!");
				} else {
					totalPay = (basePay * 1.5) *((double)hoursWorked);
				}

			} else {
				totalPay =  basePay * ((double)hoursWorked);
			}

			if (hoursWorked >= 5) {
				if (totalPay < 100) {
					totalPay = 100;
				} else if (totalPay > 1000) {
					totalPay = 1000;

				}
			}
			if ((hoursWorked > 0) || totalPay > 0) {
				realHourlyPayment = totalPay/((double)hoursWorked);
				realHourlyPaymentTotal = realHourlyPaymentTotal + realHourlyPayment;
			} else {
				break;
			}
			totaltotalPay = totaltotalPay + totalPay;
		} while ((hoursWorked != 0) && (totalPay != 0));
		
		//Print out desired results 
		System.out.println("The average amount of hours worked is: " +(totalHoursWorked/i));
		System.out.println("The average of the total pay is: $" +(totaltotalPay/i));
		System.out.println("The average pay rate is: $" +(totalBasePay/i));
		System.out.println("The real hourly payment is: $" +(realHourlyPaymentTotal));
	}

}



public static void main(String[]args){
		
		Richard richard = new Richard("Richard Bronshtein", "INPUT!");
		richard.Q1();
}
	}
		
		
	public static class Esha extends IndividualContribution{
			
			public Esha(String FullName, String NetID){
				super.FullName = FullName;
				super.NetID = NetID;
			}
			
			public void Q2(){
				
				//initiates string that will have removed immediate repetitions	
				String repetitions;
				
				//initiates the object that takes in strings
				Scanner scann = new Scanner(System.in);
				
				//lets user input string 
				System.out.println("Please enter your string: ");
				
				//takes in string input from user
				repetitions = scann.nextLine();

				//uses replaceAll method to remove immediate repetitions
				repetitions = repetitions.replaceAll("([a-zA-Z]+)\\1+", "$1");
					
				//prints the output of the manipulated string
				System.out.println(repetitions);
				
			}
			
		}
		
	public static void main(String[]args){
		
		Esha esha = new Esha("Esha Bhalla", "ebhalla2");
		esha.Q2();
	}
		
	}
	
	public static class Ravi extends IndividualContribution{

	
			public Ravi(String FullName, String NetID){
				super.FullName = FullName;
				super.NetID = NetID;
				}
				
				public void Q3(){
				
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter slope1=");
		double m1 = scanner.nextDouble();
		System.out.println("Please enter Y-intercept1=");
		double c1 = scanner.nextDouble();
		System.out.println("Please enter slope2=");
		double m2 = scanner.nextDouble();
		System.out.println("Please enter Y-intercept2=");
		double c2 = scanner.nextDouble();
		
		if(m1==m2)
		{
			double diff;
			diff= c1-c2;
		
			if(diff<0.01)
			{
				System.out.println("Overlap");
			}
				else
				{
					System.out.println("No intersect");
				}
			}
			
			else
			{
				double x, y;
				x= (c2-c1)/(m1-m2);
				y= m1*x + c1;
				
				System.out.println("The line intersect at X="+x+ "Y="+y);
			}
		scanner.close();
	}
	public static void main(String[]args){
		
		Ravi ravi = new Ravi("Ravi Gupta", "rgupta47");
		ravi.Q3();
	}
}


