package seatAllocator;

public class SeatDetail {
	int i = -1;
	int j = -1;
	int passNumber = -1;
	
	SeatType seatType;
	

	public int getPassNumber() {
		return passNumber;
	}

	public void setPassNumber(int passNumber) {
		this.passNumber = passNumber;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public void setSeatInFlightPos(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public String getSeatInFlightPos() {
		return i + "" + j;
	}
}
