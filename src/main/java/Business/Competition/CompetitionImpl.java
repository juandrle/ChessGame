package Business.Competition;

public class CompetitionImpl implements Competition{
	private int time;
	

	public CompetitionImpl(int time) {
		super();
		this.time = time;
	}

	@Override
	public int countTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean whoWin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void stopTime() {
		// TODO Auto-generated method stub
		
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
}
