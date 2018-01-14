
public class FilterYear extends Filter {
	int year;
	
	public FilterYear(int year){
		this.year = year;
	}
	@Override
	public boolean matches(Song s) {
		return s.getYear() == year;
	}
}
