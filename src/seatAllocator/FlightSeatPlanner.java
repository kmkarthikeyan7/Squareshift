package seatAllocator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FlightSeatPlanner {
    private List<LinkedList<SeatDetail>> seats;

    public List<LinkedList<SeatDetail>> getSeats() {
        return seats;
    }

    public void initFlightSeats(int n, int[][] seatMatrix) {
        seats = new ArrayList<>(n);

        for(int i = 0; i < seatMatrix[0][1]; i++) {
            LinkedList<SeatDetail> list = new LinkedList<>();
            for(int j = 0; j < seatMatrix[0][0]; j++) {
                SeatDetail seatDetail = new SeatDetail();
                seatDetail.setSeatInFlightPos(i, j);
                if(j == 0) {
                    seatDetail.setSeatType(SeatType.WINDOW);
                } else if(j == seatMatrix[0][0] - 1) {
                    seatDetail.setSeatType(SeatType.AISLE);
                } else {
                    seatDetail.setSeatType(SeatType.CENTER);
                }

                list.add(seatDetail);
            }
            seats.add(list);
        }

        int seatColOffset = seatMatrix[0][0];
        for(int inp = 1; inp < seatMatrix.length - 1; inp++) {
            for(int i = 0; i < seatMatrix[inp][1]; i++) {
                for(int j = 0; j < seatMatrix[inp][0]; j++) {
                    SeatDetail seatDetail = new SeatDetail();
                    seatDetail.setSeatInFlightPos(i, seatColOffset + j);
                    if(j > 0 && j < seatMatrix[inp][0] - 1) {
                        seatDetail.setSeatType(SeatType.CENTER);
                    } else {
                        seatDetail.setSeatType(SeatType.AISLE);
                    }

                    LinkedList<SeatDetail> list;
                    try {
                        list = seats.get(i);
                    } catch (IndexOutOfBoundsException e) {
                        list = new LinkedList<>();
                        seats.add(list);
                    }
                    list.add(seatDetail);
                }
            }
            seatColOffset += seatMatrix[inp][0];
        }

        for(int i = 0; i < seatMatrix[seatMatrix.length - 1][1]; i++) {
            for(int j = 0; j < seatMatrix[seatMatrix.length - 1][0]; j++) {
                SeatDetail seatDetail = new SeatDetail();
                seatDetail.setSeatInFlightPos(i, seatColOffset + j);
                if(j == 0) {
                    seatDetail.setSeatType(SeatType.AISLE);
                } else if(j == seatMatrix[seatMatrix.length - 1][0] - 1) {
                    seatDetail.setSeatType(SeatType.WINDOW);
                } else {
                    seatDetail.setSeatType(SeatType.CENTER);
                }

                LinkedList<SeatDetail> list;
                try {
                    list = seats.get(i);
                } catch (IndexOutOfBoundsException e) {
                    list = new LinkedList<>();
                    seats.add(list);
                }
                list.add(seatDetail);
            }
        }
    }

    public void printSeats() {
        for (LinkedList<SeatDetail> seatDetails: seats) {
            System.out.println();
            int startSeatPositionInFlight = 0;
            for (SeatDetail seatDetail : seatDetails) {
                if(seatDetail.j != startSeatPositionInFlight) {
                    for (int k = startSeatPositionInFlight; k < seatDetail.j; k++) {
                        System.out.printf("%-18s", "");
                    }
                }
				String passnum=Integer.toString(seatDetail.getPassNumber());
				if(passnum.equals("-1")) {
					passnum ="Empty";
				}
                startSeatPositionInFlight = seatDetail.j + 1;
                System.out.printf("%-18s", (seatDetail.getSeatType() + ":" + passnum));
            }
        }
    }


}