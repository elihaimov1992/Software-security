

# Java Assignment

Development of examples, two test cases, that reproducing and demonstrating the issues in program when working with files
and using in java-8.

## Introduction

#### ERR08-J. Do not catch NullPointerException or any of its ancestors

Programs must not catch `java.lang.NullPointerException`. A `NullPointerException` exception thrown at runtime indicates the existence of an underlying null pointer dereference that must be fixed in the application code. Handling the underlying null pointer dereference by catching the `NullPointerException`. rather than fixing the underlying problem is inappropriate for several reasons:
* Catching `NullPointerException` adds significantly more performance overhead than simply adding the necessary null checks [Bloch 2008]. 
* When multiple expressions in a `try` block are capable of throwing a `NullPointerException`, it is difficult or impossible to determine which expression is responsible for the exception because the `NullPointerException` `catch` block handles any `NullPointerException` thrown from any location in the try block. 
* Programs rarely remain in an expected and usable state after a `NullPointerException` has been thrown. Attempts to continue execution after first catching and logging (or worse, suppressing) the exception rarely succeed.

Likewise, programs must not catch `RuntimeException`, `Exception`, or `Throwable`. Few, if any, methods are capable of handling all possible runtime exceptions. When a method catches `RuntimeException`, it may receive exceptions unanticipated by the designer, including `NullPointerException` and `ArrayIndexOutOfBoundsException`. Many `catch` clauses simply log or ignore the enclosed exceptional condition and attempt to resume normal execution. Runtime exceptions often indicate bugs in the program that should be fixed by the developer and often cause control flow vulnerabilities.

#### EXP01-J. Do not use a null in a case where an object is required

Do not use the `null` value in any instance where an object is required, including the following cases:
* Calling the instance method of a null object
* Accessing or modifying the field of a `null` object
* Taking the length of `null` as if it were an array
* Accessing or modifying the elements of `null` as if it were an array
* Throwing `null` as if it were a `Throwable` value

Using a `null` in cases where an object is required results in a `NullPointerException` being thrown, which interrupts execution of the program or thread. Code conforming to this coding standard will consequently terminate because [ERR08-J. Do not catch NullPointerException or any of its ancestors](https://wiki.sei.cmu.edu/confluence/display/java/ERR08-J.+Do+not+catch+NullPointerException+or+any+of+its+ancestors) requires that `NullPointerException` is not caught. 


## Table of contents

> * [Java Assignment](#java-assignment)
> * [Introduction](#introduction)
>   * [ERR08-J. Do not catch NullPointerException or any of its ancestors](#err08-j-do-not-catch-nullpointerexception-or-any-of-its-ancestors)
>   * [EXP01-J. Do not use a null in a case where an object is required](#exp01-j-do-not-use-a-null-in-a-case-where-an-object-is-required)
> * [Table of contents](#table-of-contents)
> * [Installation](#installation)
> * [ERR08-J.: Noncompliant code example (NullPointerException):](#err08-j-noncompliant-code-example-nullpointerexception)
>   * [ERR08-J.: The main problem:](#err08-j-the-main-problem)
>   * [ERR08-J.: The main problems of our example:](#err08-j-the-main-problems-of-our-example)
> * [ERR08-J.: Noncompliant code example (NullPointerException) J-Unit Tests:](#err08-j-noncompliant-code-example-nullpointerexception-j-unit-tests)
> * [EXP01-J.: Noncompliant code example:](#exp01-j-noncompliant-code-example)
>   * [EXP01-J.: The main problem:](#exp01-j-the-main-problem)
>   * [EXP01-J.: The main problems of our example:](#exp01-j-the-main-problems-of-our-example)
> * [EXP01-J.: Noncompliant code example J-Unit Tests:](#exp01-j-noncompliant-code-example-j-unit-tests)
> * [Java version 8](#java-version-8)
> * [Author](#author)
> * [License](#license)
> * [Acknowledgments](#acknowledgments)


## Installation

To get started developing in Java, you need to:
 * Install the Windows Eclipse Java [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/packages/release/helios/sr1/eclipse-ide-java-developers).
 * Install the Java-8 [Java Version 8](https://www.java.com/en/download/).
 
 
To run the jar files for Java tests of programs ERR08-J and EXP01-J, you need to: the following jar files must be installed:
 * Install the `JUnit 4` jar file [junit4](https://search.maven.org/search?q=g:junit%20AND%20a:junit)
 * Install the `test jar` jar file [test jar](http://www.java2s.com/Code/Jar/t/test-jar.htm)
 * Install the `hamcrest-core` jar file [hamcrest-core-1.3.jar](http://www.java2s.com/Code/Jar/h/Downloadhamcrestcore13jar.htm)
 * Install all files in the Windows Eclipse Java:
 ```Project file Properties -> Java Build Path -> Adding and applying all downloaded jar files```


## ERR08-J.: Noncompliant code example (NullPointerException):
This noncompliant code example defines an `FileIsExists()` method that takes a File argument, called file, and returns:
* If file not exists -> returns false value.
* Otherwise, return a true value or catching `NullPointerException` (if file dereference null pointer) and returns false value.


#### ERR08-J.: The main problem:
Rather than checking to see whether the given file is a null argument, the method catches `NullPointerException` and returns false.
* The solution: Add condition (if) that checks the if a file is a null argument,  resulting in a null pointer dereference rather than catching `NullPointerException` because its adds significantly more performance overhead than simply adding the necessary null checks [Bloch 2008].


#### ERR08-J.: The main problems of our example:
1. Delay and damage to performance overhead.
2. When there are more than one object is a null arguments, the program returns a null pointer but it is not known which of the objects should the program continue to run after the first of the two - which should throw an `NullPointerException`.
3. We can see that the third and fourth objects may will not run, if one of the two previous objects that are a null arguments has thrown an `NullPointerException`, because when the exception is thrown, in most cases, the program throws an exception and stops running.

**Note: Adding the condition (if) specified as a solution -> all problems will be resolved and all objects will run as required and output, respectively.

```
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
```

Now, compile and run the program (output the program without adding the solution condition):
```
Catching NullPointerException null
Catching NullPointerException null
```

Output the program with adding the solution condition (and sometimes even without - in case the program continues to run after the NullPointerException is thrown):
```
Catching NullPointerException null
Catching NullPointerException null
file exists !
file not exists !
```

**Note: The reference in this output to cases that are wrapped in comments and not to cases where tests are run from the ERR08_JTest Object (JUnit test for ERR08_J).


## ERR08-J.: Noncompliant code example (NullPointerException) J-Unit Tests:
```
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
```
Now, to running the ERR08_JTest.jar (jar file) for Java tests of ERR08_J (our JUnit tests), you need to write in cmd (Command Prompt):
`cd "<Your EER08J_EXP01J_Tests.jar path>" -> java -jar EER08J_EXP01J_Tests.jar ->  `

```
--- ERR08_J Test cases: ---

Catching NullPointerException null
file exists !
file not exists !
file is null

--- EXP01_J Test cases: ---

Exception in thread "main" java.lang.NullPointerException
        at EXSW.EXP01J.isReadable(EXP01J.java:55)
        at EXSW.EXP01JTest.testA(EXP01JTest.java:46)
        at EXSW.EXP01J.main(EXP01J.java:88)

java.lang.NullPointerException
The file is readable !
The file isn't readable !
The file is a null argument- Null pointer dereferences
The file is readable !
The file isn't readable !
```

## EXP01-J.: Noncompliant code example:
This noncompliant code example defines an `isReadable()` method that takes a File argument, called file, and returns:
* If the file is unreadable (if the file name is not specified) -> returns false value.
* Otherwise, (the file is readable if the file name is specified) returns true value.


#### EXP01-J.: The main problem:
Method `isReadable()` is noncompliant because it may be called with a null argument, resulting in a null pointer dereference.
* The solution: Wrapped Method: includes the `isReadablePrivate()` method implementation as the `isReadable()` method previous noncompliant example, but it is now a private method with only one caller in its containing class and calling method, `testFile()`, guarantees that `isReadablePrivate()` is always called with a valid file (file name is specified) reference. As a result, the class conforms with this rule even though a public `isReadable()` method would not. Guarantees of this sort can be used to eliminate null pointer dereferences.


#### EXP01-J.: The main problems of our example:
Because we define an object used / required as `NULL`, when the object is required - the result will be an abnormal shot and an interruption of activity / program execution.

```
package EXSW;

import java.io.File;
import java.io.IOException;
import java.lang.NullPointerException;

//ERR01_J: Do not use a null in a case where an object is required:
// Source: Noncompliant Code Example (Wrapped Method):

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
```

Now, compile and run the program (output the program without adding the solution (Wrapped Method)):
```
Exception in thread "main" java.lang.NullPointerException
	at EXP01_J.isReadable(EXP01_J.java:48)
	at EXP01_J.main(EXP01_J.java:62)
```

Output the program with adding the solution (Wrapped Method):
```
The file is a null argument- Null pointer dereferences
The file is readable !
The file isn't readable !
```


## EXP01-J.: Noncompliant code example J-Unit Tests:
```
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

```
Now, to running the EER08J_EXP01J_Tests.jar (jar file) for Java tests of EER08J and EXP01J(our JUnit tests), you need to write in cmd (Command Prompt):
`cd "<Your EER08J_EXP01J_Tests.jar path>" -> java -jar EER08J_EXP01J_Tests.jar ->  `

```
--- ERR08_J Test cases: ---

Catching NullPointerException null
file exists !
file not exists !
file is null

--- EXP01_J Test cases: ---

Exception in thread "main" java.lang.NullPointerException
        at EXSW.EXP01J.isReadable(EXP01J.java:55)
        at EXSW.EXP01JTest.testA(EXP01JTest.java:46)
        at EXSW.EXP01J.main(EXP01J.java:88)

java.lang.NullPointerException
The file is readable !
The file isn't readable !
The file is a null argument- Null pointer dereferences
The file is readable !
The file isn't readable !
```
 **Note: To run the 2 programs EER08_J and EXP01_J test cases using the jar file, we use and run the main method of the MainClass class - which runs their 2 programs and test cases. MainClass:
```
package EXSW;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ERR08J err08j = new ERR08J();
		System.out.println("--- ERR08_J Test cases: ---\n");
		err08j.main(args);
		System.out.println();
		
		EXP01J exp01j = new EXP01J();
		System.out.println("--- EXP01_J Test cases: ---\n");
		exp01j.main(args);
		System.out.println();

	}

}
```

## Java version 8
The version of Java that we used:
```
// Open the Computer's Windows PowerShell:
// java -version
java version "1.8.0_251"
Java(TM) SE Runtime Environment (build 1.8.0_251-b08)
Java HotSpot(TM) 64-Bit Server VM (build 25.251-b08, mixed mode)
```


## Author

* **Eli Haimov (ID. 308019306)** - *Java Assignment* 


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details


## Acknowledgments

* Explanation about Noncompliant NullPointerException ERR08-J. example [ERR08-J. Do not catch NullPointerException or any of its ancestors](https://wiki.sei.cmu.edu/confluence/display/java/ERR08-J.+Do+not+catch+NullPointerException+or+any+of+its+ancestors)
* Explanation about Noncompliant EXP01-J. example [EXP01-J. Do not use a null in a case where an object is required](https://wiki.sei.cmu.edu/confluence/display/java/EXP01-J.+Do+not+use+a+null+in+a+case+where+an+object+is+required)
* Explanation about Files in Java [Java Files](https://www.w3schools.com/java/java_files.asp)
