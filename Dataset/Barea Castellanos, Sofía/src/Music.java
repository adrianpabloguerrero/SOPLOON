import java.util.Set;

public abstract class Music {
	
	public abstract int getDuration();
	public abstract Set<Song> search(Filter f);
	public abstract int count();
	public abstract void print();
	
}
