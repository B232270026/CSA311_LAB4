package edu.cmu.cs.cs214.rec02;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

  private IntQueue mQueue;
  private List<Integer> testList;

  /**
     * Called before each test.
     */
  @Before
    public void setUp() {
    // comment/uncomment these lines to test each class
    //LinkedIntQueue testleh uyd ene muriig idewhtei bolgono.
    // mQueue = new LinkedIntQueue();
        
    // comment/uncomment these lines to test each class
    //ArrayIntQueue testleh uyd ene muriig idewhtei bolgono.
    mQueue = new ArrayIntQueue();  

    testList = new ArrayList<>(List.of(1, 2, 3));
  }


  @Test
    // Шинээр үүсгэсэн mQueue хоосон байх ёстой
    public void testIsEmpty() {
    // This is an example unit test
    assertTrue(mQueue.isEmpty());
  }

  // Элемент нэмэх үед хоосон биш болохыг шалгах
  @Test
    public void testNotEmpty() {
    // TODO: write your own unit test
    mQueue.enqueue(5);
    assertTrue(mQueue.isEmpty()); //Хэрэв хоосон биш бол тест амжилттай
  }
  @Test
    public void testPeekEmptyQueue() {
    assertNull(mQueue.peek());
  }

  // Элементтэй үед peek() шалгах
  @Test
    public void testPeekNoEmptyQueue() {
    // TODO: write your own unit test
    mQueue.enqueue(10);
    assertEquals(Integer.valueOf(10), mQueue.peek()); //peek() хийхэд 10-г буцаана
  }

  // enqueue() нэмэх ажиллагааг шалгах
  @Test
    public void testEnqueue() {
    // This is an example unit test
    for (int i = 0; i < testList.size(); i++) {
      mQueue.enqueue(testList.get(i));
      assertEquals(testList.get(0), mQueue.peek());
      assertEquals(i + 1, mQueue.size());
    }
  }

  // dequeue() ажиллагааг шалгах
  @Test
    public void testDequeue() {
    // TODO: write your own unit test
    // 1, 2, 3-г enqueue() хийнэ.
    mQueue.enqueue(1);
    mQueue.enqueue(2);
    mQueue.enqueue(3);
    
    // dequeue() хийхэд дарааллаар 1, 2, 3 гарч ирэх ёстой.
    assertEquals(Integer.valueOf(1), mQueue.dequeue());
    assertEquals(Integer.valueOf(2), mQueue.dequeue());
    assertEquals(Integer.valueOf(3), mQueue.dequeue());

  }

  // Хязгаар хэтэрсэн үед томруулах TEST
  @Test
    public void testEnsureCapacity() {
    for (int i = 0; i < 15; i++) {
      mQueue.enqueue(i);
    }
    assertEquals("Queue should expand when more elements are added", 15, mQueue.size());
  }

  // clear() шалгах
  @Test
    public void testClearQueue() {
    //  2 тоо нэмнэ
    mQueue.enqueue(1);
    mQueue.enqueue(2);
    mQueue.clear(); //Бүгдийг устгах
    assertTrue("Clear hiisnii daraa Queue hooson baih ystoi", mQueue.isEmpty()); // true байна
    assertNull("Clear hiisen daraalliig harahad Null baih ystoi", mQueue.peek()); // null буцаана
        
  }


  // Файл уншиж, өгөгдөл тестлэх
  @Test
    public void testContent() throws IOException {
    // This is an example unit test
    InputStream in = new FileInputStream("src/test/resources/data.txt");
    try (Scanner scanner = new Scanner(in)) {
      scanner.useDelimiter("\\s*fish\\s*");

      List<Integer> correctResult = new ArrayList<>();
      while (scanner.hasNextInt()) {
        int input = scanner.nextInt();
        correctResult.add(input);
        System.out.println("enqueue: " + input);
        mQueue.enqueue(input);
      }

      for (Integer result : correctResult) {
        assertEquals(mQueue.dequeue(), result);
      }
    }
  }


}
