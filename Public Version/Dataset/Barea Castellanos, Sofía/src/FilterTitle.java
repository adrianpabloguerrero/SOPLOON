
public class FilterTitle extends Filter {
	String title;
	
	public FilterTitle(String title) {
		this.title = title;
	}
	@Override
	public boolean matches(Song s) {
		return s.getTitle().toLowerCase().contains(title.toLowerCase());
	}
}
