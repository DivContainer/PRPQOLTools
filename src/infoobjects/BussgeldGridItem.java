package infoobjects;

import javax.faces.event.ActionEvent;

import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;

import interfaces.GridButtonInterface;

public class BussgeldGridItem extends FIXGRIDItem {
	
	private static final long serialVersionUID = -8804425987780620854L;
	
	String description;
	double payAmount;
	int jailTime;
	int licensePoints;
	boolean actionEnabled;
	
	GridButtonInterface gridButtonInterface;
	
	public BussgeldGridItem(String description, double payAmount, int licensePoints, int jailTime, GridButtonInterface gridButtonInterface) {
		super();
		this.description = description;
		this.payAmount = payAmount;
		this.jailTime = jailTime;
		this.licensePoints = licensePoints;
		this.gridButtonInterface = gridButtonInterface;
		this.actionEnabled = true;
	}

	public void removeButtonPressed(ActionEvent event) {
		gridButtonInterface.removeButtonPressed();
	}
	
	public void addButtonPressed(ActionEvent event) {
		gridButtonInterface.addButtonPressed();
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}

	public int getJailTime() {
		return jailTime;
	}

	public void setJailTime(int jailTime) {
		this.jailTime = jailTime;
	}
	
	public int getLicensePoints() {
		return licensePoints;
	}

	public void setLicensePoints(int licensePoints) {
		this.licensePoints = licensePoints;
	}

	public GridButtonInterface getGridButtonInterface() {
		return gridButtonInterface;
	}

	public void setGridButtonInterface(GridButtonInterface gridButtonInterface) {
		this.gridButtonInterface = gridButtonInterface;
	}

	public boolean isActionEnabled() {
		return actionEnabled;
	}

	public void setActionEnabled(boolean actionEnabled) {
		this.actionEnabled = actionEnabled;
	}
}
