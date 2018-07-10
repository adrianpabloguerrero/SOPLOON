import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
public class Playlist extends Music {
	String name;
	Vector<Music> list;
	
	public Playlist(String name) {
		this.name = name;
		list = new Vector<Music>();
	}

	public void add(Music music){
		list.addElement(music);
	}

	public void remove(Music music){
		list.removeElement(music);
	}

	public void sort(int i, Music music){
		remove(music);
		list.setElementAt(music, i);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration(){
		int total = 0;
		for(int i=0; i<list.size();i++){
			total+=list.elementAt(i).getDuration();
		}
		return total;
	}
	
	@Override
	public Set<Song> search(Filter f) {
		Set<Song> result = new HashSet<Song>();
		for (int m = 0; m < list.size(); m++) {
			Music music = list.elementAt(m);
			Set<Song> aux = music.search(f);
			result.addAll(aux);
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Playlist playlist = (Playlist) obj;
		for(int i=0; i<list.size();i++){
			if (!list.elementAt(i).equals(playlist.list.elementAt(i))){
				return false;
			}
		}
		return true;
	}

	@Override
	public int count() {
		int total = 0;
		for (int i = 0; i <list.size();i++){
			Music m = list.elementAt(i);
			total += m.count();
		}
		return total;
	}

	@Override
	public void print() {
		System.out.println("-----------------------------");
		System.out.println("#Playlist#");
		System.out.println("Name: " + name);
		for (int i = 0; i < list.size(); i++){
			Music music = list.elementAt(i);
			music.print();
		}
		System.out.println("-----------------------------");
	}


}
