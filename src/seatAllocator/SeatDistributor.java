package seatAllocator;

import java.util.Scanner;

public class SeatDistributor {

	public static void main(String[] args) {
		System.out.println("Enter the number of seat matrices");
		Scanner sc = new Scanner(System.in);
		int matrixcount = sc.nextInt();
		int[][] seatMatrix = new int[matrixcount][matrixcount];
		int totalSeats = 0;

		for (int i = 1; i <= matrixcount; i++) {
			System.out.println("Enter the matrix no:" + i);
			seatMatrix[i - 1][0] = sc.nextInt();
			seatMatrix[i - 1][1] = sc.nextInt();
			totalSeats += seatMatrix[i - 1][0] * seatMatrix[i - 1][1];
		}
		System.out.println("Enter the number of passengers");
		int noOfWaitingPassengers = sc.nextInt();
		if(noOfWaitingPassengers<=0) {
			System.out.println("Minimum one passenger required");
		}
		
		
		sc.close();

		if (totalSeats < noOfWaitingPassengers) {
			System.out.println("Sorry,total number of seats available are only: " + totalSeats);
		} else {

			int numberOfAisleSeat = 0;
			int numberOfWindowSeat = 0;
			int numberOfLinkedList = 0;

			for (int i = 0; i < matrixcount; i++) {
				numberOfLinkedList = Math.max(numberOfLinkedList, seatMatrix[i][1]);
				if (i == 0 || i == matrixcount - 1) {
					numberOfAisleSeat += seatMatrix[i][1];
					if (seatMatrix[i][0] != 1) { // if the number of seats is just one then consider it for aisle seat
						numberOfWindowSeat += seatMatrix[i][1];
					}
				} else {
					numberOfAisleSeat += seatMatrix[i][1] * 2;
				}
			}

			int aisleStartPos = 1;
			int windowStartPos = numberOfAisleSeat + 1;
			int centerStartPos = windowStartPos + numberOfWindowSeat;

			FlightSeatPlanner flightSeatPlanner = new FlightSeatPlanner();
			flightSeatPlanner.initFlightSeats(numberOfLinkedList, seatMatrix);

			PassengerSeatAssigner passengerSeatAssigner = new PassengerSeatAssigner();
			passengerSeatAssigner.assignSeats(flightSeatPlanner.getSeats(), aisleStartPos, windowStartPos,
					centerStartPos, noOfWaitingPassengers);

			flightSeatPlanner.printSeats();

		}
	}
}
