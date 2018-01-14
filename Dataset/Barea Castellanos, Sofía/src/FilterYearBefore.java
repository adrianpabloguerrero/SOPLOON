
public class FilterYearBefore extends Filter {
	int year;
	
	public FilterYearBefore (int year){
		this.year = year;
	}
	@Override
	public boolean matches(Song s) {
		return s.getYear() < year;
	}
}
