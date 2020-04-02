
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the ConcordanceDataManager
 * which is implemented from the ConcordanceDataManagerInterface
 * 
 * @author Professor Kartchner
 *
 */
public class ConcordanceDataManagerTest {
	private ConcordanceDataManagerInterface concordanceManager = new ConcordanceDataManager();
	private File inputFile, outputFile;
	private String text;

	/**
	 * Create an instance of ConcordanceDataManager
	 * Create a string for testing
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		concordanceManager = new ConcordanceDataManager();
		text = "Daisy, Daisy\ngive me your answer do.\n"+
				"I'm half crazy\nall for the love of you";
	}

	/**
	 * Set concordanceManager reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		concordanceManager = null;
	}

	/**
	 * Test for the createConcordanceArray method
	 * Use the String text created in setUp()
	 */
	@Test
	public void testCreateConcordanceArray() {
		ArrayList<String> words = concordanceManager.createConcordanceArray(text);
		assertEquals("all: 4\n",words.get(0));
		assertEquals("answer: 2\n",words.get(1));
		assertEquals("crazy: 3\n",words.get(2));
		assertEquals("daisy: 1\n",words.get(3));
		assertEquals("for: 4\n",words.get(4));
		assertEquals("give: 2\n",words.get(5));
		assertEquals("half: 3\n",words.get(6));
		assertEquals("i'm: 3\n",words.get(7));
		assertEquals("love: 4\n",words.get(8));
		assertEquals("you: 4\n",words.get(9));
		assertEquals("your: 2\n",words.get(10));
	}
	
	
	/**
	 * Test for createConcordanceFile method
	 * This is intended to be used with the test file:
	 * Now_is_the_time.txt
	 */
	@Test
	public void testCreateConcordanceFileA() {
		U.p("Test A");
		ArrayList<String> words = new ArrayList<String>();
		try {
			inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.print("Daisy, Daisy\ngive me your answer do.\n"+
					"I'm half crazy\nall for the love of you");
			
			inFile.close();
			outputFile = new File("Test1Out.txt");
			PrintWriter outFile = new PrintWriter(outputFile);
			outFile.print(" ");
			
			concordanceManager.createConcordanceFile(inputFile, outputFile);
			Scanner scan = new Scanner(outputFile);
			while (scan.hasNext())
			{
				words.add(scan.nextLine());
			}

			scan.close();
			outFile.close();
		 
			for(String s : words) {
				U.p(s + " ");
			}
			
			assertEquals(words.get(0), "all: 4");
			assertEquals(words.get(1), "answer: 2");
			assertEquals(words.get(2), "crazy: 3");
			assertEquals(words.get(3), "daisy: 1");
			assertEquals(words.get(4), "for: 4");
			assertEquals(words.get(5), "give: 2");
			assertEquals(words.get(6), "half: 3");
			assertEquals(words.get(7), "i'm: 3");
			assertEquals(words.get(8), "love: 4");
			assertEquals(words.get(9), "you: 4");
			assertEquals(words.get(10),"your: 2");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fail("This should not have caused an FileNotFoundException");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("This should not have caused an Exception");
		}
	}

	/**
	 * Test for createConcordanceFile method
	 * Create an inputFile "Test.txt" 
	 * and an outputFile "TestOut.txt"
	 */
	
	@Test
	public void testCreateConcordanceFileB() {
		U.p("Test B");
		ArrayList<String> words = new ArrayList<String>();
		try {
			inputFile = new File("Test2.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.print("Into the woods,\n" + 
					"It's time to go,\n" + 
					"I hate to leave,\n" + 
					"I have to go.\n" + 
					"Into the woods\n" + 
					"It's time, and so\n" + 
					"I must begin my journey.\n" + 
					"Into the woods\n" + 
					"And through the trees\n" + 
					"To where I am\n" + 
					"Expected ma'am\n" + 
					"Into the woods\n" + 
					"To Grandmother's house\n" + 
					"And home before dark.\n");
			inFile.close();
			outputFile = new File("TestOut1.txt");
			PrintWriter outFile = new PrintWriter(outputFile);
		 
			concordanceManager.createConcordanceFile(inputFile, outputFile);
			Scanner scan = new Scanner(outputFile);
			while (scan.hasNext())
			{
				words.add(scan.nextLine());
				
			}

			scan.close();
			outFile.close();
			U.p("Size of concordance: "+words.size());
		for(int i=0; i<words.size(); i++)
			System.out.println("Test B: \n" + words.get(i));
		
			 
			assertEquals("before: 14", words.get(0));
			assertEquals("begin: 7", words.get(1));
			assertEquals("dark: 14", words.get(2));
			assertEquals("expected: 11", words.get(3));
			assertEquals("grandmother's: 13", words.get(4));
			assertEquals("into: 1, 5, 8, 12", words.get(9));
			assertEquals("journey: 7", words.get(11));
			assertEquals("woods: 1, 5, 8, 12", words.get(19));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateConcordanceFileC() {
		U.p("Test C");
		try {
			inputFile = new File("Test3.txt");
			inputFile.setReadable(false);
			outputFile = new File("Test3Out.txt");
			PrintWriter outFile = new PrintWriter(outputFile);
			outFile.print(" ");
			
			concordanceManager.createConcordanceFile(inputFile, outputFile);
			assertTrue("This should have raised an exception", false);
			outFile.close();
		
		} catch (FileNotFoundException e) {
			assertTrue("This should have raised a FileNotFoundexception", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateConcordanceFileD() {
		U.p("Test D");
		try {
			inputFile = new File("Test3.txt");
			outputFile = new File("Test3Out.txt");
			outputFile.setWritable(false);
			
			concordanceManager.createConcordanceFile(inputFile, outputFile);
			assertTrue("This should have raised an exception", false);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			assertTrue("This should have raised a FileNotFoundException", true);
		}
	}
	
}
