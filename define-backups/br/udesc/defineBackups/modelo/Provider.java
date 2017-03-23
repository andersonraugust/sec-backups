package br.udesc.defineBackups.modelo;

public class Provider {

    private String name;
    private int outages;
    private double downtime;
    
    public Provider(String name, int outages, double downtime) {
    	this.setName(name);
    	this.setOutages(outages);
    	this.setDowntime(downtime);
    }

    public int getOutages() {
		return outages;
	}

	public void setOutages(int outages) {
		this.outages = outages;
	}

	public double getFailureProbability() {
		return 1 / ((720 - this.getDowntime()) / this.getOutages());
	}

	public double getDowntime() {
		return downtime;
	}

	public void setDowntime(double uptime) {
		this.downtime = uptime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
