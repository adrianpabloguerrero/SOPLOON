
public class FilterDurationLessThan extends Filter {
	int duration;
	
	public FilterDurationLessThan(int duration) {
		this.duration = duration;
	}
	@Override
	public boolean matches(Song s) {
		return s.getDuration()<duration;
	}

}
