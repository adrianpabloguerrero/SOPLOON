
public class FilterDuration extends Filter {
	int duration;
	
	public FilterDuration (int duration) {
		this.duration = duration;
	}
	@Override
	public boolean matches(Song s) {
		return s.getDuration() == duration;
	}

}