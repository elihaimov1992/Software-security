package EXSW;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;
import java.lang.NullPointerException;



public class EXP01JTest {
	
	// Compliant code example (NullPointerException):
	//Solution (Wrapped Method)
	/*
	 * isReadablePrivate() method takes a File argument, called file, and returns:
	 * If the file is unreadable (if the file name is not specified) -> returns false value.
	 * Otherwise, (the file is readable if the file name is specified) returns true value.
	 * **Note: The isReadablePrivate() method is defined as private separate from method isReadable() defined as public.
	 *         The isReadablePrivate() method called by testFile() wrapped method, that fixing the problem.
	 */
	private static boolean isReadablePrivate(File file) {
		if (!(file.canRead())) {
			System.out.println("The file isn't readable !");
			return false;
		}
		System.out.println("The file is readable !");
		return true;
		
	}

	public static boolean testFile(File file) {
		if (file == null) {
	 	System.out.println("The file is a null argument- Null pointer dereferences");
	 	return false;
	 }
	 else return isReadablePrivate(file);
	}

	
			// Noncompliant code example cases:
	@Test(expected=NullPointerException.class)
	public void testA() {
		EXP01J Obj = new EXP01J();
		File file = null; // Null pointer dereference
		System.out.println("Exception in thread \"main\" java.lang.NullPointerException\r\n" + 
				"	at EXSW.EXP01J.isReadable(EXP01J.java:55)\r\n" + 
				"	at EXSW.EXP01JTest.testA(EXP01JTest.java:46)\r\n" + 
				"	at EXSW.EXP01J.main(EXP01J.java:88)\r\n" + 
				"");
		if(Obj.isReadable(file) == (false || true)) {
			fail("Error: the right output must be: ..Exception in thread \"main\" java.lang.NullPointerException..");		
		}
	}
	
	
	@Test
	public void testB() {
		EXP01J Obj = new EXP01J();
		File file= new File("D:\\newWorkspace\\EXSW\\Untitled1"); // The path of the file and file name is specified
		if(Obj.isReadable(file) != true) {
			fail("Error: the file must be readable and returns a true value");
		}
	}
	
	
	@Test
	public void testC() {
		EXP01J Obj = new EXP01J();
		File file= new File(""); // The path of the file and file name is not specified
		if(Obj.isReadable(file) != false) {
			fail("Error: the file must be not readable and returns a false value");
		}
	}
	
	
			// Compliant code example case (Fixing the problem):
	@Test
	public void testD() {
		EXP01JTest Obj = new EXP01JTest();
		File file = null; // Null pointer dereference
		if(Obj.testFile(file) != false) {
			fail("Error: the file must be a null argument and returns a false value");
		}
	}
	
	
	@Test
	public void testE() {
		EXP01JTest Obj = new EXP01JTest();
		File file= new File("D:\\newWorkspace\\EXSW\\Untitled1"); // The path of the file and file name is specified
		if(Obj.testFile(file) != true) {
			fail("Error: the file must be a readable and returns a true value");
		}
	}
	
	
	@Test
	public void testF() {
		EXP01JTest Obj = new EXP01JTest();
		File file= new File(""); // The path of the file and file name is not specified
		if(Obj.testFile(file) != false) {
			fail("Error: the file must be not readable and returns a false value");
		}
	}


}
