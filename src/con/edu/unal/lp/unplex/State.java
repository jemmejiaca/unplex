package con.edu.unal.lp.unplex;

public class State {
	private String label;
	private int index;
	
	public State(int index) {
		this.index = index;
		this.label = "q" + index;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return label;
	}
	
	

}
