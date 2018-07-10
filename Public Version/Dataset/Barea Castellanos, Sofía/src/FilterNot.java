
public class FilterNot extends Filter {
	Filter f;
	
	public FilterNot (Filter f){
		this.f = f;
	}
	@Override
	public boolean matches(Song s) {
		return !f.matches(s);
	}

}
