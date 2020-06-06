package EXSW;

import java.io.File;
import java.io.IOException;
import java.lang.NullPointerException;

public class ERR08J {
	// Noncompliant code example (NullPointerException):
		public static boolean FileIsExists(File file){
			try {
				// Solution: Checking if file is null
				/*
				if (file == null) {
					System.out.println("file is null");
					return false;
				}
				*/
				
				if(!(file.exists())) {
					System.out.println("file not exists !");
					return false;
				}
				System.out.println("file exists !");
				return true;
			} catch (NullPointerException e) {
				// TODO: handle exception
				System.out.println("Catching NullPointerException " + e.getMessage());
				return false;
			}
		}
		
		
		public static void main(String[] args) {
			  
			// All test cases:
			/*
			File myObj1 = null;   // Null pointer dereference -> catch NullPointerException and return false value.
			File myObj2 = null;   // Null pointer dereference -> catch NullPointerException and return false value.
			File myObj3 = new File("D:\\newWorkspace\\EXSW\\Untitled1");
			File myObj4 = new File("");
			FileIsExists(myObj1); // print: "Catching NullPointerException null"
			FileIsExists(myObj2); // print: "Catching NullPointerException null"
			FileIsExists(myObj3); // print: "file exists !" (Optional)
			FileIsExists(myObj4); // print: "file not exists !" (Optional)	
			*/
			
			
			ERR08JTest tests = new ERR08JTest();
			tests.test1(); // Null pointer dereference
			tests.test2(); // "file exists !"
			tests.test3(); // "file not exists !"
			tests.test4(); // "file is null"
			
		}
		

}
