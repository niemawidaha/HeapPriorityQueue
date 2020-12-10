package com.company;

public class Main {

    public static PriorityQueue priorityQueue;
    public static void main(String[] args) {

        // Initialize priority heap:
        priorityQueue = new PriorityQueue(31);
        initializeHeap(priorityQueue);

        // Test insertion of index at root:
        testInsertionAtRoot();

        // Test insertion of index in middle:
        testInsertionInMiddle();

        // Test insertion of index at the end:
        testInsertionAtEnd();

        // Test deletion of index at root:
        testRemovalMaxValue();

    }   // ends main

    // -------------------------------------------------------------
    public static void initializeHeap(PriorityQueue priorityQueue){

        priorityQueue.insert(10);
        priorityQueue.insert(20);
        priorityQueue.insert(30);
        priorityQueue.insert(40);
        priorityQueue.insert(50);
        priorityQueue.insert(60);
        priorityQueue.insert(70);
        priorityQueue.insert(80);
        priorityQueue.insert(90);
        priorityQueue.insert(100);

        // display heap:
        String dots = "...............................";
        System.out.println("\n"+dots+dots); // dotted bottom line
        System.out.println("POPULATING TABLE BEFORE TEST BEGINS");
        priorityQueue.getPqHeap().displayHeap();
    } // end initializeHeap

    // -------------------------------------------------------------
    // Test insertion of index at root:
    public static void testInsertionAtRoot() {

        System.out.println("TEST 1: Insertion of index at root - 200 ");
        priorityQueue.insert(200);
        priorityQueue.getPqHeap().displayHeap();
    }

    // -------------------------------------------------------------
    // Test insertion of index in middle:
    public static void testInsertionInMiddle(){

        System.out.println("TEST 2: Insertion of index in the middle - 85");
        priorityQueue.insert(85);
        priorityQueue.getPqHeap().displayHeap();
    }

    // -------------------------------------------------------------
    // Test insertion of index at the end:
    public static void testInsertionAtEnd(){

        System.out.println("TEST 3: Insertion of index at the end - 5");
        priorityQueue.insert(5);
        priorityQueue.getPqHeap().displayHeap();
    }

    // -------------------------------------------------------------
    // Test deletion of index at root:
    public static void testRemovalMaxValue(){

        // Test deletion of index at root:
        priorityQueue.delete();
        priorityQueue.getPqHeap().displayHeap();
    }

} // ends main

////////////////////////////////////////////////////////////////
class PriorityQueue {
    //this class is all written! Your work is in writing some methods for the Heap class below.

    private Heap pqHeap;
    private int maxSize;

    public PriorityQueue(int max){
        maxSize = max;
        pqHeap = new Heap(maxSize);
    }

    public boolean insert(int key) {
        pqHeap.insert(key);
        return true;
    }

    public Node delete() {
        return pqHeap.delete();
    }

    // ive added this method so that I can utilize the display method in the Heap class
    public Heap getPqHeap(){
        return pqHeap;
    }
}
////////////////////////////////////////////////////////////////
class Heap {
    private Node[] heapArray;
    private int maxSize; //maximum size of the underlying heapArray
    private int currentSize; //number of nodes in the heapArray

    // -------------------------------------------------------------
    public Heap(int max) {
        maxSize = max;
        currentSize = 0;
        heapArray = new Node[maxSize]; //set up array for heap
    }

    // -------------------------------------------------------------
    public boolean insert(int key) {
        //FOR YOU TO WRITE
        //insert a new Node with the data key into the correct spot of the heap
        //return false if heap is full and you can't insert
        //return true otherwise
        if(currentSize == maxSize){
            return false;
        }

        Node newNode = new Node(key);
        heapArray[currentSize] = newNode;

        trickleUp(currentSize++);
        return true;
    }

    // -------------------------------------------------------------
    public void trickleUp(int index){

        int parent = (index - 1)/2;

        Node bottom = heapArray[index];

        while(index > 0 && heapArray[parent].getKey() < bottom.getKey()){
            // move it down:
            heapArray[index] = heapArray[parent];

            index = parent;
            parent = (parent - 1)/2;
        } // ends while

        heapArray[index] = bottom;
    }

    // -------------------------------------------------------------
    public Node delete() {
        //delete item with the maximum key
        //return that item (return null if the heap is empty)

        Node root = heapArray[0]; // item with max @ root
        System.out.println("Now removing root from heap: " + root.getKey());

        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    // -------------------------------------------------------------
    // -------------------------------------------------------------
    // trickleDown()
    public void trickleDown(int index){
        int largerChild;

        // save root:
        Node top = heapArray[index];
        while(index < currentSize/2){
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            // find the large child:
            if(rightChild < currentSize && heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild;
            if( top.getKey() >= heapArray[largerChild].getKey() )
                break;

            heapArray[index] = heapArray[largerChild];
            index = largerChild; // go down
        } // end while
        heapArray[index] = top; // root to index
    }

    // -------------------------------------------------------------
    // displayHeap()
    public void displayHeap(){
        System.out.print("heapArray: "); // array format
        for(int m=0; m<currentSize; m++)
            if(heapArray[m] != null)
                System.out.print( heapArray[m].getKey() + " ");
            else
                System.out.print( "-- ");
        System.out.println();

        // heap format
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0; // current item
        String dots = "...............................";

        System.out.println(dots+dots); // dotted top line
        while(currentSize > 0) // for each heap item
        {
            if(column == 0) // first item in row?
                for(int k=0; k<nBlanks; k++) // preceding blanks
                    System.out.print(" ");
            // display item
            System.out.print(heapArray[j].getKey());
            if(++j == currentSize) // done?
                break;
            if(++column==itemsPerRow) // end of row?
            {
                nBlanks /= 2; // half the blanks
                itemsPerRow *= 2; // twice the items
                column = 0; // start over on
                System.out.println(); // new row
            }
            else // next item on row
                for(int k=0; k<nBlanks*2-2; k++)
                    System.out.print(" "); // interim blanks
        } // end for
        System.out.println("\n"+dots+dots); // dotted bottom line

    }
}

////////////////////////////////////////////////////////////////
class Node {
    private int nodeData;

    public Node (int key) {
        nodeData = key;
    }

    public int getKey() {
        return nodeData;
    }

    public void setKey(int newKey) {
        nodeData = newKey;
    }
}