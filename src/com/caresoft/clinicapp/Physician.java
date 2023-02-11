package com.caresoft.clinicapp;

public class Physician extends User implements HIPAACompliantUser {

	public Physician(Integer id) {
		super(id);
	}

	@Override
	public boolean assignPin(int pin) {
		String sPin = String.valueOf(pin);
		if (sPin.length() == 4) {
			setPin(pin);
			return true;
		}
		return false;
	}
	@Override
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if (getId() == confirmedAuthID) {
			return true;
		}
		return false;
	}
}
