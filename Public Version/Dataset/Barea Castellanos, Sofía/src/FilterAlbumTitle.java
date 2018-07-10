
public class FilterAlbumTitle extends Filter {
	String title;
	
	public FilterAlbumTitle(String title) {
		this.title = title;
	}
	@Override
	public boolean matches(Song s) {
		return s.getAlbumTitle().toLowerCase().contains(title.toLowerCase());
	}
}
