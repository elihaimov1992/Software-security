package EXSW;

import java.io.File;
import java.io.IOException;
import java.lang.NullPointerException;

//ERR01_J: Do not use a null in a case where an object is required:
// Source: Noncompliant Code Example (Wrapped Method):
// https://wiki.sei.cmu.edu/confluence/display/java/EXP01-J.+Do+not+use+a+null+in+a+case+where+an+object+is+required
/*
 * This noncompliant code example defines an isReadable() method that takes a File argument, called file, and returns:
 * If the file is unreadable (if the file name is not specified) -> returns false value.
 * Otherwise, (the file is readable if the file name is specified) returns true value.
 * The main problem:
 * Method isReadable() is noncompliant because it may be called with a null argument, resulting in a null pointer dereference. 
 * The solution:
 * Wrapped Method: includes the isReadablePrivate() method implementation as the isReadable() method previous noncompliant example, 
 * but it is now a private method with only one caller in its containing class and called by method, testFile(), guarantees that 
 * isReadablePrivate() is always called with a valid file (file name is specified) reference. As a result, the class conforms 
 * with this rule even though a public isReadable() method would not. Guarantees of this sort can be used to eliminate null 
 * pointer dereferences.
 * 
 * Explanation: The main problems of our example:
 * Because we define an object used / required as NULL, when the object is required - the result will be an abnormal shot and an
 * interruption of activity / program execution.
 */


public class EXP01J {

// Solution (Wrapped Method)
/*
private static boolean isReadablePrivate(File file) {
	if (!(file.canRead())) {
		System.out.println("The file isn't readable !");
		return false;
	}
	System.out.println("The file is readable !");
	return false;
	
}

public static boolean testFile(File file) {
	if (file == null) {
    	System.out.println("The file is a null argument- Null pointer dereferences");
    	return false;
    }
    else return isReadablePrivate(file);
 }
 */

// Noncompliant code example:
public static boolean isReadable(File file) {
	
	if (!(file.canRead())) {
		System.out.println("The file isn't readable !");
		return false;
	}
	System.out.println("The file is readable !");
	return true;
}



public static void main(String[] args) {
	
	// All test cases:
	/*
	File myObj1 = null;  // Null pointer dereferences
	File myObj2 = new File("D:\\newWorkspace\\EXSW\\Untitled1");
	File myObj3 = new File("");
	isReadable(myObj1);    // Without solution Wrapped Method: throw Exception "..NullPointerException.."
	isReadable(myObj2);    // Without solution Wrapped Method: Does not print anything because of the exception throw before
	isReadable(myObj3);    // Without solution Wrapped Method: Does not print anything because of the exception throw before
	testFile(myObj1);    // With solution Wrapped Method: print: "The file is a null argument- Null pointer dereferences"
	testFile(myObj2);      // With solution Wrapped Method: print: "The file is readable !"
	testFile(myObj3);      // With solution Wrapped Method: print: "The file isn't readable !" 
	 */
	
	EXP01JTest tests = new EXP01JTest();
	// Using to try-catch block to print NullPointerException and avoiding stop the program
	try {
		tests.testA();
	} catch (NullPointerException e) {
		// TODO: handle exception
		System.out.println(e);
	}
	tests.testB();
	tests.testC();
	tests.testD();
	tests.testE();
	tests.testF();	
}


}
