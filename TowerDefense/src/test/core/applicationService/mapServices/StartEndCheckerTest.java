package test.core.applicationService.mapServices;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.applicationService.mapServices.connectivity.imp.StartEndChecker;
import core.domain.waves.Position;

public class StartEndCheckerTest {
	private StartEndChecker checker;

	@BeforeClass
	public static void setUpBeforeClass(){
		try {
			System.out.println("start of StartEndChecker test");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@AfterClass
	public static void tearDownAfterClass(){
		try {
			System.out.println("start of StartEndChecker test");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Before
	public void setUp(){
		try {
			checker = new StartEndChecker();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testHasOverLap() {
		try {
			Position first = new Position(0, 0);
			Position second = new Position(0, 0);
			assertTrue(checker.hasOverlap(first, second));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testIsInEdge(){
		try {
			Position position = new Position(12, 13);
			int width = 12;
			int height = 13;
			assertTrue(checker.isInEdge(width, height, position));
		} catch (Exception e) {
			// TODO: handle exception
		}
	} 
	@Test 
	public void testHasEnd(){
		try {
			int width = 8;
			int height = 4;
			MatrixUtility utility = new MatrixUtility();
			int[][] matrix = utility.matrixReadre("matrix.txt", height, width);
			assertTrue(checker.hasEnd(matrix));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testHasStart(){
		try {
			int width = 4;
			int height = 8;
			MatrixUtility utility = new MatrixUtility();
			int[][] matrix = utility.matrixReadre("matrix.txt", height, width);
			assertTrue(checker.hasStart(matrix));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testIsCorrectSize(){
		int height = 31;
		int width = 30;
		assertFalse(checker.isCorrectSize(height, width));
	}
}