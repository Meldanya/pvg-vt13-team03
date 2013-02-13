package racer;

public class RacerClass implements Comparable<RacerClass> {
	private String name;

	public RacerClass(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public boolean equals(Object o) {
		return ((RacerClass) o).name.equals(name);
	}

	@Override
	public int compareTo(RacerClass o) {
		return o.name.compareTo(name);
	}
}
