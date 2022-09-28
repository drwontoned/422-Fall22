import java.util.Scanner;

public class Calendar {

	private static Date date = null;
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Day: ");
		int dd = Integer.parseInt(scanner.nextLine());
		System.out.println("Month: ");
		int mm = Integer.parseInt(scanner.nextLine());
		System.out.println("Year: ");
		int yyyy = Integer.parseInt(scanner.nextLine());
		scanner.close();
		
		if(Date.isValidDate(dd, mm, yyyy))
			date = new Date(mm, dd, yyyy);
		System.out.println(date.toString());

	}
}
