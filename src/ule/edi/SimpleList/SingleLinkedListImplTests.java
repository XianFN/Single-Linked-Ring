package ule.edi.SimpleList;



import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SingleLinkedListImplTests {

	

	private SingleLinkedListImpl<String> lS;
	private SingleLinkedListImpl<String> lSABC;
	

	@Before
	public void setUp() {
		this.lS = new SingleLinkedListImpl<String>();
		
		
		this.lSABC = new SingleLinkedListImpl<String>("A", "B", "C");
	}
	
   @Test
   public void constructorElemens(){
	   lS=new SingleLinkedListImpl<String>("A", "B", "C", "D");
	   Assert.assertEquals("[A, B, C, D]", lS.toString());
   }
   @Test
   public void sizeTest(){
	   Assert.assertEquals(0, lS.size());
	   lS=new SingleLinkedListImpl<String>("A", "B", "C", "D");
	   Assert.assertEquals(4, lS.size());
   }
   @Test
   public void addAtPos(){
	   
	   lS.addAtPos("A", 1);
	   Assert.assertEquals("[A]", lS.toString());
	   lS.addAtPos("C", 1);
	   Assert.assertEquals("[C, A]", lS.toString());
	   lS.addAtPos("B", 2);
	   Assert.assertEquals("[C, B, A]", lS.toString());
	   lS.addAtPos("D", 5);
	   Assert.assertEquals("[C, B, A, D]", lS.toString());
   }
   @Test
   public void addAtPostest2(){
	   
	 
	   lS=new SingleLinkedListImpl<String>("A", "B", "C", "D");
	   Assert.assertEquals(4, lS.size());
	   lS.addAtPos("E", 3);
	   Assert.assertEquals("[A, B, E, C, D]", lS.toString());
	   lS.addAtPos("Z", 6);
	   Assert.assertEquals(6, lS.size());
	   Assert.assertEquals("[A, B, E, C, D, Z]", lS.toString());
	   
   }
   
   @Test
   public void addNTimes(){
	   
	   lS.addNTimes("A", 3);
	   Assert.assertEquals("[A, A, A]", lS.toString());
	   lS.addNTimes("B", 2);
	   Assert.assertEquals("[A, A, A, B, B]", lS.toString());
	   
   }
   @Test
   public void indexofTest(){
	   
	 
	   lS=new SingleLinkedListImpl<String>("A", "B", "C", "D","E");
	   Assert.assertEquals(5, lS.size());
	   Assert.assertEquals(4, lS.indexOf("D"));
	   Assert.assertEquals(1, lS.indexOf("A"));
	   
   }
   @Test (expected = NoSuchElementException.class)
   public void indexofTestException(){
	   
	 
	   lS=new SingleLinkedListImpl<String>("A", "B", "C", "D","E");
	   Assert.assertEquals(5, lS.size());
	   Assert.assertEquals(4, lS.indexOf("H"));
	  
	   
   }
   @Test
   public void removeLastTest() throws EmptyCollectionException{
	   
	 
	   lS=new SingleLinkedListImpl<String>("A", "B", "C", "D","E");
	   Assert.assertEquals(5, lS.size());
	   Assert.assertEquals("E", lS.removeLast());
	   Assert.assertEquals(4, lS.size());
	   Assert.assertEquals("D", lS.removeLast());
	   Assert.assertEquals("C", lS.removeLast());
	   Assert.assertEquals("B", lS.removeLast());
	   Assert.assertEquals("A", lS.removeLast());
	   
   }
   @Test (expected = EmptyCollectionException.class)
   public void removeLastException() throws EmptyCollectionException{
	   
	   lS=new SingleLinkedListImpl<String>();
	   Assert.assertEquals("E", lS.removeLast());

 
   }
   @Test
   public void removeLastTestWithElement() throws EmptyCollectionException{
	   
	 
	   lS=new SingleLinkedListImpl<String>("A", "B", "E", "D","E");
	   Assert.assertEquals(5, lS.size());
	   Assert.assertEquals("E", lS.removeLast("E"));
	   Assert.assertEquals(4, lS.size());
	   
	   Assert.assertEquals("D", lS.removeLast("D"));
	   Assert.assertEquals("B", lS.removeLast("B"));
	   Assert.assertEquals("E", lS.removeLast("E"));
	   Assert.assertEquals(1, lS.size());
	   Assert.assertEquals("A", lS.removeLast("A"));
	   
   }
   @Test (expected = NoSuchElementException.class)
   public void removeLastNoSuchExceptionwithElement() throws NoSuchElementException, EmptyCollectionException{
	   
		 
	   lS=new SingleLinkedListImpl<String>("A","A");
	   Assert.assertEquals("E", lS.removeLast("E"));

	   
	  
	   
   }
   @Test (expected = IllegalArgumentException.class)
   public void addNtimesException() throws IllegalArgumentException{
	   
	   lS=new SingleLinkedListImpl<String>();
	   lS.addNTimes("A", -3);
 
   }
   @Test (expected = IllegalArgumentException.class)
   public void addAtPosException() throws IllegalArgumentException{
	   
	   lS=new SingleLinkedListImpl<String>();
	   lS.addAtPos("A", -3);
 
   }
   @Test (expected = NoSuchElementException.class)
   public void removeLastNoSuchExceptionwithElement2() throws NoSuchElementException, EmptyCollectionException{
	   
		 
	   lS=new SingleLinkedListImpl<String>("A");
	   Assert.assertEquals("E", lS.removeLast("E"));

	   
	  
	   
   }
   @Test (expected = EmptyCollectionException.class)
   public void removeLastExceptionwithElement() throws EmptyCollectionException{
	   
		 
	   lS=new SingleLinkedListImpl<String>();
	   Assert.assertEquals("E", lS.removeLast("E"));

	   
	  
	   
   }
   @Test
   public void reversetest() {
	   
	 
	   lS=new SingleLinkedListImpl<String>("A", "B", "E", "D","E");
	   Assert.assertEquals(lS.reverse().toString() , "[E, D, E, B, A]");
	   
	   
   }
   @Test(expected = UnsupportedOperationException.class)
	public void testIteratorExceptionremove() {
		Iterator<String> i = lS.iterator();
		i.remove();
		
	}
   @Test(expected = NoSuchElementException.class)
	public void testItException() {
		Iterator<String> i = lS.iterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}
// TEST DE SUBLIST
	@Test
	public void tesSubListEnListaVacia() {
	
		Assert.assertEquals(-1, lS.isSubList(lSABC));		
	}

		@Test
		public void tesSubListConSubListaVacia() {
			Assert.assertEquals(1, lSABC.isSubList(lS));		
		}
		
		
		@Test
		public void subListVarios() {
			lS = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E");
			Assert.assertEquals(1, lS.isSubList(lSABC));	
			lS = new SingleLinkedListImpl<String>("Z", "X", "A", "B", "C", "D", "E");
			Assert.assertEquals(3, lS.isSubList(lSABC));	
			lS = new SingleLinkedListImpl<String>("Z", "X", "A", "B", "C");
			Assert.assertEquals(3, lS.isSubList(lSABC));	
			lS = new SingleLinkedListImpl<String>("A", "B", "C");
			Assert.assertEquals(1, lS.isSubList(lSABC));	
		}
	 
   

}
