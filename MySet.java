/**
 * This Java application implements a Set data structure
 * that stores a set of Objects. MySet objects have a fixed 
 * capacity (i.e. they don't grow) and duplicate members
 * are not allowed.
 *
 * @creator John Cody
 * @created 02019.03.15
 */

interface CSC205_Set {
   boolean isMember(Object obj);
   boolean isEmpty();
   boolean isFull();
   int capacity();
   int size();
   void clear();
   String toString();
   int add(Object obj);
   void remove(Object obj);
   boolean equals(MySet that);
   MySet union(MySet that);
   MySet intersection(MySet that);
   MySet difference(MySet that);
   boolean subset(MySet that);
}

public class MySet implements CSC205_Set {

   public static final int ADD_DUP_ERRNO = -1;
   public static final int ADD_FULL_ERRNO = -2;

   private static final int CAPACITY = 7;

   private Object[] data; 
   private int size;

   /**
    * Constructs a MySet object having a default CAPACITY.
    */
   public MySet() { this(CAPACITY); }

   /**
    * Constructs a MySet object having a client supplied capacity.
    *
    * @param capacity  becomes CAPACITY if <= 0
    */
   public MySet(int capacity) {
      if (capacity <= 0) capacity = CAPACITY;
      data = new Object[capacity];
      size = 0;
   }

   /**
    * Constructs a MySet object containing Integer objects.
    *
    * @param e int[] of values that get added to this MySet
    */
   public MySet(int[] e) {
      data = new Object[CAPACITY];
      size = e.length > CAPACITY ? CAPACITY : e.length;
      for (int i = 0; i < size; i++)
         data[i] = new Integer(e[i]);
   }

   /**
    * Checks to see if this MySet has zero members (elements).
    *
    * @return true if this MySet has zero members
    * @return false if this MySet has more than zero members
    */
   public boolean isEmpty() { return size == 0; }

   /**
    * Checks to see if this MySet is at capacity.
    *
    * @return true if at capacity
    * @return false if not at capacity
    */
   public boolean isFull() { return size == data.length; }

   /**
    * Returns the number of members in this MySet.
    *
    * @return number of members in this MySet
    */
   public int size() { return size; }

   /**
    * An alias for size().
    *
    * @return number of members
    */
   public int cardinality() { return size; }

   /**
    * Getter method so client can check the capacity of this MySet.
    *
    * @return capacity of this MySet
    */
   public int capacity() { return data.length; }

   /**
    * Sets the size of this MySet to zero.
    */
   public void clear() { size = 0; }

   /**
    * Constructs a String representation of this MySet.
    *
    * @return string representation of this MySet
    */
   public String toString() {
      StringBuffer sb = new StringBuffer("{");
      int n = size - 1;
      for (int i = 0; i < n; i++)
         sb.append(data[i] + ", ");
      if (size > 0)
         sb.append(data[n]);
      sb.append('}');
      return new String(sb);
   }

   /** 
    * TBI (To Be Implemented) -- The remaining instance methods
    * need to be implemented as part of the #Set assignment.
    */

   /**
    * TBI (To Be Implemented)
    * Adds an object to this MySet.
    *
    * @param obj the class Object to add to this MySet
    * @return ADD_FULL_ERRNO if this MySet is at capacity; 
    *         else return ADD_DUP_ERRNO if object already 
    *         a member of this MySet; else return the new 
    *         size of this MySet 
    */
   public int add(Object obj) {
       if(size == data.length) 
           return ADD_FULL_ERRNO;
       if(isMember(obj)) 
           return ADD_DUP_ERRNO;
       data[size] = obj;
       return ++size;
   }
   
   /**
    * TBI (To Be Implemented)
    * Checks to see of an object is a member of this MySet.
    * 
    * @param obj the class Object object to search for
    * @return true if object is a member of this MySet
    * @return false if object is not a member of this MySet
    */
   public boolean isMember(Object obj) {
       for(int i = 0; i < size; i++) {
           if(data[i].equals(obj))
               return true;
       }
       return false;
   }

   /**
    * TBI (To Be Implemented)
    * Removes an object from this MySet. The method does nothing
    * if the object is not a member. 
    *
    * @param obj the object to remove from this MySet
    */
   public void remove(Object obj) {
       if(!isMember(obj)) 
           return;
       int i;
       for(i = 0; i < size; i++) {
           if(data[i].equals(obj)) {
               data[i] = null; // add size edit and shift all other objects
               break;
           }
       }
       for(; i < size; i++) {
           if(i+1 < size) {
               data[i] = data[i+1];
           }
       }
       size--;
   }

   /**
    * TBI (To Be Implemented)
    * Checks to see if two MySet objects are equal.
    * hint: a.difference(b) and b.difference(a) 
    *       are both empty, then...
    *
    * @param that MySet to compare again this MySet
    * @return true if the two MySets are equal; else
    *         return false 
    */
   public boolean equals(MySet that) {
       if(this.difference(that).size() == 0 && that.difference(this).size() == 0) 
           return true;
       return false;
   }

   /**
    * TBI (To Be Implemented)
    * Instantiates a new MySet object that contains all of the elements 
    * of this MySet and all of the elements of that MySet (duplicates 
    * are not allowed).
    *
    * @param that MySet to do union with this MySet
    * @return the union of this and that MySets
    */
   public MySet union(MySet that) {
       MySet union = new MySet(this.size + that.size);
       
       for(int i = 0; i < this.size; i++) {
           union.add(this.data[i]);
       }
       for(int j = 0; j < that.size; j++) {
           union.add(that.data[j]);
       }
       return union;
   }

   /**
    * TBI (To Be Implemented)
    * Instantiates a new MySet object that contains all of the 
    * members that are found in both this MySet and that MySet.
    *
    * @param that MySet to do intersection with this MySet
    * @return the intersection of this and that MySets
    */
   public MySet intersection(MySet that) {
       MySet intersection;
       
       if(this.size() > that.size()) 
           intersection = new MySet(this.size());
       else
           intersection = new MySet(that.size());
       for(int i = 0; i < this.size(); i++) {
           if(that.isMember(this.data[i])){
               intersection.add(this.data[i]);
           }
       }
       return intersection;
   }

   /**
    * TBI (To Be Implemented)
    * Instantiates a new MySet object that contains all of the 
    * members of this MySet that are not found in that MySet.
    *
    * @param that MySet to do difference with this MySet
    * @return the difference of this and that MySets
    */
   public MySet difference(MySet that) {
       MySet difference = new MySet(this.size());
       
       for(int i = 0; i < this.size(); i++) {
           if(!that.isMember(this.data[i])) {
               difference.add(this.data[i]);
           }
       }
       
       return difference;
   }

   /**
    * TBI (To Be Implemented)
    * Checks if every member of this MySet is a 
    * member of that Myset.
    *
    * @param that MySet to do see if it's a subset of this MySet
    * @return true if this is a subset of that; false otherwise
    */
   public boolean subset(MySet that) {
       for(int i = 0; i < this.size(); i++) {
           if(!that.isMember(this.data[i]))
               return false;
       }
       return true;
   }

   /**
    * The main() method is used exclusively for testing.
    */
   public static void main(String[] argv) {
      Object[] objects = {
         new Integer(205),
         new String("Java supports OOP"),
         new Boolean(true),
         new Byte((byte)42),
         new Integer(240),
         new Byte((byte)42),
         new String("foo"),
         new Boolean(true),
         new String("foo"),
         new String("Java creator: James Gosling"),
         new Integer(240),
         new Double(3.14159265),
         new Object(),
      };

      MySet test = new MySet();

      for (int i = 0; i < objects.length; i++) {
         System.out.print(objects[i]);
         int rv = test.add(objects[i]);
         switch (rv) {
            case ADD_FULL_ERRNO: 
               System.out.println(" not added b/c test is full");
               break;
            case ADD_DUP_ERRNO:
               System.out.println(" not added b/c it's a duplicate");
               break;
            default:
               System.out.println(" added (" + rv + ")");
               break;
         }
      }
      System.out.println(test);
      dump(test);
      test.clear();
      System.out.println("test cleared: " + test);
      dump(test);

      final String SEPARATOR = "\n=========================";
      System.out.println(SEPARATOR);

      final int[] A = { 5, 7, 3, 2 };
      final int[] B = { 2, 6 };
      final int[] C = { 1, 2, 5 };         
      final int[] D = { 3, 7, 2, 5 };       
      final int[] E = { 5, 2, 1 };
      final int[] F = { 7, 5 };
      final int[] G = { 0, 6, 1, 4 };

      MySet a = new MySet(A); System.out.println("A: " + a);
      MySet b = new MySet(B); System.out.println("B: " + b);
      MySet c = new MySet(C); System.out.println("C: " + c);
      MySet d = new MySet(D); System.out.println("D: " + d);
      MySet e = new MySet(E); System.out.println("E: " + e);
      MySet f = new MySet(F); System.out.println("F: " + f);
      MySet g = new MySet(G); System.out.println("G: " + g);

      System.out.println(a + ".equals(" + b + ") is " + a.equals(b));
      System.out.println(c + ".equals(" + d + ") is " + c.equals(d));
      System.out.println(c + ".equals(" + e + ") is " + c.equals(e));

      System.out.println(a + ".union(" + b + "): " + a.union(b));
      System.out.println(a + ".union(" + a + "): " + a.union(a));
      System.out.println(a + ".union(" + g + "): " + a.union(g));
      System.out.println(a + ".intersection(" + b + "): " + a.intersection(b));
      System.out.println(a + ".difference(" + b + "): " + a.difference(b));
      System.out.println(b + ".difference(" + a + "): " + b.difference(a)); 

      System.out.println(c + ".union(" + d + "): " + c.union(d));
      System.out.println(c + ".intersection(" + d + "): " + c.intersection(d)); 
      System.out.println(c + ".difference(" + d + "): " + c.difference(d));
      System.out.println(d + ".difference(" + c + "): " + d.difference(c));
      System.out.println(c + ".difference(" + e + "): " + c.difference(e));

      System.out.println(a + ".union(" + a + "): " + a.union(a));
      System.out.println(a + ".intersection(" + a + "): " + a.intersection(a));
      System.out.println(a + ".difference(" + a  +"): " + a.difference(a));

      System.out.println(f + ".subset(" + a  +"): " + f.subset(a));
      System.out.println(f + ".subset(" + c  +"): " + f.subset(c));
      System.out.println(f + ".subset(" + d  +"): " + f.subset(d));
      System.out.println(f + ".subset(" + f  +"): " + f.subset(f));
      System.out.println(d + ".subset(" + f  +"): " + d.subset(f));

      Integer x = new Integer(A[1]);
      System.out.print(a);
      a.remove(x);
      System.out.println(".remove(" + x + "): " + a);
      
      // miscelleanous testing...
      MySet n0 = new MySet();
      MySet n1 = new MySet();
      System.out.println(n0 + ".union(" + n1 + "): " + n0.union(n1));
      System.out.println(n0 + ".intersection(" + n1 + "): " + 
                         n0.intersection(n1)); 
      System.out.println(n0 + ".difference(" + n1 + "): " + n0.difference(n1));
      System.out.println(n0 + ".union(" + a + "): " + n0.union(a));
      System.out.println(n0 + ".intersection(" + a + "): " + 
                         n0.intersection(a)); 
      System.out.println(n0 + ".difference(" + a + "): " + n0.difference(a));
      System.out.println(a + ".union(" + n0 + "): " + a.union(n0));
      System.out.println(a + ".intersection(" + n0 + "): " + 
                         a.intersection(n0));
      System.out.println(a + ".difference(" + n0 + "): " + a.difference(n0));
   }

   private static void dump(MySet set) {
      System.out.println("...isEmpty(): " + set.isEmpty() + "; isFull(): " +
                         set.isFull() + "; size(): " + set.size() +
                         "; capacity(): " + set.capacity());
   }
   
}