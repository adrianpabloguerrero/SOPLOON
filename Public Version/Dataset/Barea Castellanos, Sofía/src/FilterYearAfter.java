
public class FilterYearAfter extends Filter {
	int year;
	
	public FilterYearAfter (int year){
		this.year = year;
	}
	@Override
	public boolean matches(Song s) {
		return s.getYear() > year;
	}
}
