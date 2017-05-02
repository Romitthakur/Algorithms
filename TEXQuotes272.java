import java.io.FileInputStream;
import java.util.Scanner;

public class TEXQuotes272 {
//class Main{

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(new FileInputStream("TEXQuotes272"));
//		Scanner sc = new Scanner(System.in);
	
		boolean leftQuote = true;
		
		while(sc.hasNext()){
			String str = sc.nextLine();
			int length = str.length();
			for(int i = 0; i < length; i++){
				char ch = str.charAt(i);
				if (ch == '\u001a'){
					break;
				}
				if (ch == '"' && leftQuote){
					System.out.print("``");
					leftQuote = false;
				}else if (ch == '"' && !leftQuote){
					System.out.print("''");
					leftQuote = true;
				}else{
					System.out.print(ch);
				}
			}
			System.out.println();
		}
		sc.close();
		System.exit(0);
	}
}
