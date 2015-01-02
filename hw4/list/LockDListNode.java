package list;

public class LockDListNode extends DListNode {
	protected boolean locked = false;
	
	LockDListNode(Object i, LockDListNode p, LockDListNode n){
		super(i, p, n);
		locked = true;
	}
	
//	LockDListNode(Object i, LockDListNode p, LockDListNode n, boolean lock){
//		super(i, p, n);
//		locked = lock;
//	}
}
