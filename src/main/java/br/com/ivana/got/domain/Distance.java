package br.com.ivana.got.domain;


public class Distance {

	
	private String vAdj;

	public Distance(String vAdj, int distance) {
		super();
		this.vAdj = vAdj;
		this.distance = distance;
	}


	public String getvAdj() {
		return vAdj;
	}


	public void setvAdj(String vAdj) {
		this.vAdj = vAdj;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	private int distance;
	
}
