/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
	protected DList[] table;
	protected int size;

	
  /**
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
	  table = new DList[getNextPrime(sizeEstimate)];
	  for(int i = 0; i < table.length; i++){
		  table[i] = new DList();
	  }
  }
  
  private int getNextPrime(int num){
	  if(isPrime(num)){
		  return num;
	  }else{
		  int curNum = num + 1;
		  while(!isPrime(curNum)){
			  curNum++;
		  }
		  return curNum;
	  }
  }
  
  private boolean isPrime(int num){
	  if(num < 2){
		  return false;
	  }else if(num == 2){
		  return true;
	  }else{
		  int cnt = 3;
		  while(cnt < num){
			  if(num % cnt == 0){
				  return false;
			  }else{
				  cnt = cnt + 2;
			  }
		  }
		  return true;
	  }
  }
  
  public int getTableLength(){
	  return table.length;
  }
  
  public int countCollisions() {
    int ret = 0;
    for (int i = 0; i < table.length; i++) {
      if (table[i].length() > 1) {
        ret += table[i].length() - 1;
      }
    }
    return ret;
  }

  
  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/
 
  public HashTableChained() {
    // Your solution here.
	  this(100);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
	return (Math.abs((7 * code + 13) * getNextPrime(table.length * 75))) %
	    	table.length;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
	for(int i = 0; i < size; i++){
		if(!table[i].isEmpty())
			return false;
	}
    return true;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
	  if(key != null){
		  int n = compFunction(key.hashCode());
		  Entry e = new Entry();
		  e.key = key;
		  e.value = value;
		  
		  table[n].insertBack(e);
		  size++;
		  return e;
	  }
      return null;
  }

  
  public void printHashTable(){
	  try{
		  for(int i = 0; i < table.length; i++){
			  if(table[i].length() != 0){
				  System.out.println("Table[" + i + "] :");
				  for(int j = 0; j < table[i].length(); j++){
					  ListNode curNode = table[i].front();
					  Entry e = (Entry)(curNode.item());
					  System.out.println("key:" + e.key() + "; value:" + e.value() + "->");
				  }
			  }
		  }
	  }catch(InvalidNodeException ine){
		  System.err.println(ine);
	  }
  }
  
  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
	  if(key != null){
		  int n = compFunction(key.hashCode());
		  ListNode node = table[n].front();
		  try{
			  for(int i = 0; i < table[n].length(); i++){
				  if(key.equals(((Entry)node.item()).key())){
					  return (Entry)node.item();
				  }
			  }
		  }catch(InvalidNodeException ine){
			  System.err.println(ine);
		  }
	  }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
	  if(key != null){
		  int n = compFunction(key.hashCode());
		  ListNode node = table[n].front();
		  try{
			  for(int i = 0; i < table[n].length(); i++){
				  if(key.equals(((Entry)node.item()).key())){
					  Entry e = (Entry)node.item();
					  node.remove();
					  size--;
					  return e;
				  }
				  node = node.next();
			  }
		  }catch(InvalidNodeException ine){
			  System.err.println(ine);
		  }
	  }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
	for (int i = 0; i < table.length; i++) {
		table[i] = new DList();
    }
    size = 0;
  }
}
