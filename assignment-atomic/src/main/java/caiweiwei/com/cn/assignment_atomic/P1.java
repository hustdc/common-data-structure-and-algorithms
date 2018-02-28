package caiweiwei.com.cn.assignment_atomic;

public class P1 {
	// because b is long, 64 bits, so the assignment of b = 0 and b = -1 is not atomic, so it will print "Error".
	private long b = 0;
	public void set1() {
	    b = 0;
	}
	 
	public void set2() {
	    b = -1;
	}
	 
	public void check() {
		System.out.println(b);
	 
	    if (0 != b && -1 != b) {
	        System.err.println("Error");
	    }
	}
}
