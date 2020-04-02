import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the ConcordanceDataManager class
 * Two of the tests are based on the provided tests. I needed to edit them to isolate what the issues were.
 * 
 * @author Montague Nagel
 *
 */
public class ConcordanceDataManager_STUDENT_Test {

	private ConcordanceDataManagerInterface concordanceManager = new ConcordanceDataManager();
	private File inputFile, outputFile;
	private String text, text2, text3;
	
	@Before
	public void setUp() throws Exception {
		concordanceManager = new ConcordanceDataManager();
		
		text = "Daisy, Daisy\ngive me your answer do.\n"+
				"I'm half crazy\nall for the love of you";
		
		text2 = "Are you ready, kids?! \n"
				+ "Aye, aye, captain! \n"
				+ "Ooooooooooh!";
		
		text3 = "ConCORDance Fits thiS, mAn. \n"
				+ " I hope you understand? \n"
				+ "Aight?";
	}
	
	@After
	public void tearDown() throws Exception {
		concordanceManager = null;
	}
	
	//---------------------------------------------------------------------
	
	@Test
	public void testFormat() {
		U.p("Test Format");
		ArrayList<String> format = concordanceManager.createConcordanceArray(text3);
		
		assertEquals("aight: 3\n",format.get(0));
		assertEquals("concordance: 1\n",format.get(1));
		assertEquals("fits: 1\n",format.get(2));
		assertEquals("hope: 2\n",format.get(3));
		assertEquals("man: 1\n",format.get(4));
		assertEquals("this: 1\n",format.get(5));
		assertEquals("understand: 2\n",format.get(6));
		assertEquals("you: 2\n",format.get(7));
	}
	
	//---------------------------------------------------------------------
	
	@Test
	public void testFile() {
		U.p("Test File");
		ArrayList<String> fileString = new ArrayList<String>();
		try {
			inputFile = new File("FileTest.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.print(text2);
			inFile.close();
			outputFile = new File("FileTest_Concordance.txt");
			PrintWriter outFile = new PrintWriter(outputFile);
			outFile.print(" ");
			concordanceManager.createConcordanceFile(inputFile, outputFile);
			Scanner scan = new Scanner(outputFile);
			int index = 0;
			while (scan.hasNext())
			{
				fileString.add(scan.nextLine());
				fileString.get(index);
				index++;
			}

			scan.close();
			outFile.close();
		 
			for(String s : fileString) {
				U.p(s + " ");
			}
			assertEquals(fileString.get(0), "are: 1");
			assertEquals(fileString.get(1), "aye: 2");
			assertEquals(fileString.get(2), "captain: 2");
			assertEquals(fileString.get(3), "kids: 1");
			assertEquals(fileString.get(4), "ooooooooooh: 3");
			assertEquals(fileString.get(5), "ready: 1");
			assertEquals(fileString.get(6), "you: 1");
		} catch (FileNotFoundException e) {
			fail("This should not have caused an FileNotFoundException");
		} catch (Exception e) {
			U.p("There is error: " + e.getMessage());
			fail("This should not have caused an Exception");
		}
	}

	//---------------------------------------------------------------------
	
	@Test
	public void testCreateConcordanceFileA2() {
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
			int index = 0;
			while (scan.hasNext())
			{
				words.add(scan.nextLine());
				words.get(index);
				index++;
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
			U.p("There is error: " + e.getMessage());
			fail("This should not have caused an Exception");
		}
	}
	
	//---------------------------------------------------------------------
	
	@Test
	public void testCreateConcordanceFileB2() {
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
	
}
