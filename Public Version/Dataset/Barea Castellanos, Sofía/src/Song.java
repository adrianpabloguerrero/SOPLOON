import java.util.HashSet;
import java.util.Set;


public class Song extends Music{
	int id;
	String title;
	int duration; 
	String artist;
	String albumTitle;
	int year;
	String gender;
	String comments;
	
	public Song(int id, String title, int duration, String artist, String albumTitle, int year, String gender) {
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.artist = artist;
		this.albumTitle = albumTitle;
		this.year = year;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbumTitle() {
		return albumTitle;
	}
	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Override
	public Set<Song> search(Filter f) {
		Set<Song> result = new HashSet<Song>();
		if (f.matches(this)){
			result.add(this);
		}
		return result;
	}
	
	@Override
	public void print(){
		System.out.println("");
		System.out.println("ID: " + id);
		System.out.println("Title: " + title);
		System.out.println("Artist: " + artist);
		System.out.println("Album: " + albumTitle + " (" + gender + ", " + year + ")");
		System.out.println("Duration: " + duration);
	}

	@Override
	public boolean equals(Object obj) {
		Song song = (Song)obj;
		return this.id == song.getId();
	}
	
	@Override
	public int count() {
		return 1;
	}
}
