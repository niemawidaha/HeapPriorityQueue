package com.company;
import java.io.*;

// -------------------------------------------------------------
class HeapApp {
    public static void main(String[] args) {

        // create a heap
        HeapTest theHeap = new HeapTest(31);

        // insert values into heap:
        theHeap.insert(70); // insert 10 items
        theHeap.insert(40);
        theHeap.insert(50);
        theHeap.insert(20);
        theHeap.insert(60);
        theHeap.insert(100);
        theHeap.insert(80);
        theHeap.insert(30);
        theHeap.insert(10);
        theHeap.insert(90);

        // display heap:
        String dots = "...............................";
        System.out.println("\n"+dots+dots); // dotted bottom line
        System.out.println("POPULATING TABLE BEFORE TEST BEGINS");
        theHeap.displayHeap();

        // Test insertion of index at root:
        System.out.println("TEST 1: Insertion of index at root - 200 ");
        theHeap.insert(200);
        theHeap.displayHeap();

        // Test insertion of index in middle:
        System.out.println("TEST 2: Insertion of index in the middle - 85");
        theHeap.insert(85);
        theHeap.displayHeap();

        // Test insertion of index at the end:
        System.out.println("TEST 3: Insertion of index at the end - 5");
        theHeap.insert(5);
        theHeap.displayHeap();

        // Test deletion of index at root:
        theHeap.removeMax();
        theHeap.displayHeap();

    }
}

////////////////////////////////////////////////////////////////
class NodeTest{

    // members:
    private int iData; // data item(key)
    // -------------------------------------------------------------
    // constructor:
    public NodeTest(int key){
        iData = key;
    }
    // -------------------------------------------------------------
    public int getKey(){
        return iData;
    }
    // -------------------------------------------------------------
    public void setKey(int key){
        iData = key;
    }
} // ends Node class
////////////////////////////////////////////////////////////////

class HeapTest{

    // members:
    private NodeTest[] heapArray;
    private int maxSize;       // size of array
    private int currentSize;   // # of nodes in array

    // -------------------------------------------------------------
    // constructor:
    public HeapTest(int max){
        maxSize = max;
        currentSize = 0;
        heapArray = new NodeTest[maxSize]; // create array
    }

    // -------------------------------------------------------------
    public boolean isEmpty(){
        return  currentSize == 0;
    }

    // -------------------------------------------------------------
    // insert(key):
    public boolean insert(int key){

        if(currentSize == maxSize){
            return false;
        }

        NodeTest newNode = new NodeTest(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    } // end insert()

    // -------------------------------------------------------------
    // trickleUp()
    public void trickleUp(int index){
        int parent = (index - 1)/2;

        NodeTest bottom = heapArray[index];

        while(index > 0 && heapArray[parent].getKey() < bottom.getKey()){
            // move it down:
            heapArray[index] = heapArray[parent];

            index = parent;
            parent = (parent - 1)/2;
        } // ends while
        heapArray[index] = bottom;
    } // end trickle up

    // -------------------------------------------------------------
    // removeMax()
    public NodeTest removeMax(){

        // delete item with max key
        // assuming this is a non empty list
        NodeTest root = heapArray[0];
        System.out.println("Now removing root from heap: " + root.getKey());

        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    } // end removeMax()

    // -------------------------------------------------------------
    // trickleDown()
    public void trickleDown(int index){
        int largerChild;

        // save root:
        NodeTest top = heapArray[index];
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
} // Heap