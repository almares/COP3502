import java.util.*;
public class TattooCustomer {
	
	private String name;
	private String tattoo;
	private int minutes;
	
	public TattooCustomer(String n, String t, int m) {
		name = n;
		tattoo = t;
		minutes = m;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getMinutes() {
		return this.minutes;
	}
	
	public String getTattoo() {
		return this.tattoo;
	}

}
