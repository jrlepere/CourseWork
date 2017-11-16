import java.util.*;

class UnderflowException extends Exception { };

public class MyTreeSet<AnyType extends Comparable<? super AnyType>>
{
  private static class BinaryNode<AnyType>
  {
	  BinaryNode(AnyType theElement) { 
		  this( theElement, null, null, null ); 
	  }
    
	  BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt, BinaryNode<AnyType> pt){ 
		  element  =  theElement; left  =  lt; right  =  rt; parent  =  pt; 
    
	  }
	  AnyType element;
	  BinaryNode<AnyType> left;
	  BinaryNode<AnyType> right;
	  BinaryNode<AnyType> parent;
  }

  	public java.util.Iterator<AnyType> iterator()
  	{
  		return new MyTreeSetIterator( ); 
  	}
  
  	private class MyTreeSetIterator implements java.util.Iterator<AnyType>
  	{
  		private BinaryNode<AnyType> current  =  findMin(root);
  		private BinaryNode<AnyType> previous;
  		private int expectedModCount  =  modCount;
  		private boolean okToRemove  =  false;
  		private boolean atEnd  =  false;
  		private Queue<AnyType> iterQueue;
  		
  		public MyTreeSetIterator() {
  			initQueue(root);
  			
  			if (iterQueue.isEmpty()) {
  				atEnd = true;
  			}
  		}
  		
  		public void initQueue(BinaryNode<AnyType> n) {
  			if (n == null) {
  				return;
  			}
  			initQueue(n.left);
  			iterQueue.add(n.element);
  			initQueue(n.right);
  			
  		}
  		
  		public boolean hasNext() {  
  			return !atEnd; 
  		}
    
  		public AnyType next() {
  			if (modCount != expectedModCount) {
  				throw new java.util.ConcurrentModificationException( );
  			}
  			if(!hasNext()) {
  				throw new java.util.NoSuchElementException( );
  			}
  			
  			AnyType element = iterQueue.remove();
  			
  			if (iterQueue.isEmpty()) {
  				atEnd = true;
  			}
  			
  			return element;
  		}
        
  		public void remove()
  		{
  			if( modCount != expectedModCount )
  				throw new java.util.ConcurrentModificationException( );
  			if( !okToRemove )
  				throw new IllegalStateException( );
  			
  			MyTreeSet.this.remove( previous.element );
  			okToRemove = false;
  		}
  	}
  	
  	public MyTreeSet() { 
  		root = null; 
  	}
  
  	public void makeEmpty() { 
  		modCount ++;
  		root  =  null; 
  	}
  
  	public boolean isEmpty() { 
  		return root  ==  null; 
  	}
  	
  	public boolean contains(AnyType x) { 
  		return contains( x, root ); 
  	}
  
  	public AnyType findMin() throws UnderflowException {
  		if ( isEmpty() )
  			throw new UnderflowException();
  		else
  			return findMin( root ).element;
  	}
  	
  	public AnyType findMax() throws UnderflowException {
  		if ( isEmpty() )
  			throw new UnderflowException();
  		else
  			return findMax( root ).element;
  	}
  	
  	public void insert(AnyType x) {
  		root  =  insert( x, root, null ); 
  	}
  	
  	public void remove(AnyType x) { 
  		root  =  remove( x, root ); 
  	}
  
  	public void printTree() { 
  		if ( isEmpty() )
  			System.out.println( "Empty tree" );
  		else
  			printTree( root );
  	}
    
    private void printTree( BinaryNode<AnyType> t ) {
      	if (t == null) {
      		return;
      	}
      	printTree(t.left);
      	System.out.print(t.element + " ");
      	printTree(t.right);
    }

    private boolean contains( AnyType x, BinaryNode<AnyType> t ) {
    	if (t == null) {
    		return false;
    	}
    	int comp = x.compareTo(t.element);
    	if (comp < 0) {
    		return contains(x, t.left);
    	} else if (comp > 0) {
    		return contains(x, t.right);
    	} else {
    		return true;
    	}
    }
  
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t ) {
    	if (t.left == null) {
    		return t;
    	}
    	return findMin(t.left);
    }
  
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t ) {
    	if (t.right == null) {
    		return t;
    	}
    	return findMin(t.right);
    }
  
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t, BinaryNode<AnyType> pt ) {
    	
    	if (t == null) {
    		return new BinaryNode<AnyType>(x, null, null, t);
    	}
    	
    	int comp = x.compareTo(t.element);
    	
    	if (comp < 0) {
    		t.left = insert(x, t.left, t);
    	} else if (comp > 0) {
    		t.right = insert(x, t.right, t);
    	} else {
    		// inserting an element that is already in the tree
    	}
    	return t;
    }
  
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
    	if ( t == null ) {
    		return t; // not found
    	}
       
    	int compareResult  =  x.compareTo( t.element );
    	
    	if ( compareResult < 0) {
    		t.left  =  remove( x, t.left );
    	} else if ( compareResult > 0) {
    		t.right  =  remove( x, t.right );
    	} else if ( t.left != null && t.right != null ) {
    		// two children 
    		t.element  =  findMin( t.right ).element;
    		t.right  =  remove( t.element, t.right );
    	} else{  
    		modCount++ ;
    		BinaryNode<AnyType> oneChild;
    		oneChild  =  ( t.left != null ) ? t.left : t.right;
    		oneChild.parent  =  t.parent;  // update parent link
    		t  =  oneChild;  
    	}
      
    	return t;
    }
  
    private BinaryNode<AnyType> root;
    int modCount  =  0;
	}
