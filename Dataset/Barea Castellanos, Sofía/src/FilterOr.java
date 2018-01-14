
public class FilterOr extends Filter {
	Filter f1;
	Filter f2;
	
	public FilterOr (Filter f1, Filter f2){
		this.f1 = f1;
		this.f2 = f2;
	}
	@Override
	public boolean matches(Song s) {
		return f1.matches(s)||f2.matches(s);
	}

}
