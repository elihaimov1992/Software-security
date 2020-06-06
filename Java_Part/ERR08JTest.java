package EXSW;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ERR08JTest {
	
	// Compliant code example (NullPointerException):
	/*
	 * FileIsExists() method takes a File argument, called file, and returns:
	 * If file is a null argument -> returns a false value.
	 * If file not exists -> returns false value.
	 * Otherwise, return a true value or catching NullPointerException (if file is a null argument, 
	 * resulting in a null pointer dereference) and returns false value.
	 * **Note: FileIsExistsFixed() method is defined as FileIsExists() method with the addition of a 
	 *         condition that fixes the problem.
	 */
			public static boolean FileIsExistsFixed(File file){
				try {
					// Solution: Checking if file is null
					if (file == null) {
						System.out.println("file is null");
						return false;
					}
					
					
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

	
			// Noncompliant code example cases:
	@Test
	public void test1() {
		ERR08J Obj = new ERR08J();
		File file= null;  // Null pointer dereference
		if(Obj.FileIsExists(file) != false) {
			fail("Error: the file must catch NullPointerException and return a false value");
		}
	}
	
	
	@Test
	public void test2() {
		ERR08J Obj = new ERR08J();
		File file= new File("D:\\newWorkspace\\EXSW\\Untitled1"); // The file name is specified
		if(Obj.FileIsExists(file) != true) {
			fail("Error: the file must be exists and returns a true value");
		}
	}
	
	
	@Test
	public void test3() {
		ERR08J Obj = new ERR08J();
		File file= new File(""); // The file name is not specified
		if(Obj.FileIsExists(file) != false) {
			fail("Error: the file must be not exists and returns a false value");
		}
	}
	
	
			// Compliant code example case (Fixing the problem):
	@Test
	public void test4() {
		ERR08JTest Obj = new ERR08JTest();
		File file= null; // Null pointer dereference
		if(Obj.FileIsExistsFixed(file) != false) {
			fail("Error: the file must be a null argument and returns a false value");
		}
	}
	

}
