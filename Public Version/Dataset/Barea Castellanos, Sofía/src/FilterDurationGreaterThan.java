
public class FilterDurationGreaterThan extends Filter {
	int duration;
	
	public FilterDurationGreaterThan(int duration) {
		this.duration = duration;
	}
	@Override
	public boolean matches(Song s) {
		return s.getDuration()>duration;
	}

}
