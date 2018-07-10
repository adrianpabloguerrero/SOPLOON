
public class FilterGender extends Filter {
	String gender;
	
	public FilterGender(String gender) {
		this.gender = gender;
	}
	@Override
	public boolean matches(Song s) {
		return s.getGender().toLowerCase().contains(gender.toLowerCase());
	}
}
