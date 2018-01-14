
public class FilterArtist extends Filter {
	String artist;
	
	public FilterArtist(String artist) {
		this.artist = artist;
	}
	@Override
	public boolean matches(Song s) {
		return s.getArtist().toLowerCase().contains(artist.toLowerCase());
	}
}