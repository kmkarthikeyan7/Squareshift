package seatAllocator;

import java.util.LinkedList;
import java.util.List;

public class PassengerSeatAssigner {
    public void assignSeats(List<LinkedList<SeatDetail>> seats,
                            int aisleStartPos,
                            int windowStartPos,
                            int centerStartPos,
                            int noOfPass) {

        int aislePassNumber = aisleStartPos;
        int windowPassNumber = windowStartPos;
        int centerPassNumber = centerStartPos;
        int totalNumberPassenger = noOfPass;

        for (LinkedList<SeatDetail> seatDetails: seats) {
            for (SeatDetail seatDetail : seatDetails) {
                switch (seatDetail.getSeatType()) {
                    case AISLE:
                        if(aislePassNumber < windowStartPos) {
                            seatDetail.setPassNumber(aislePassNumber++);
                        }
                        break;
                    case WINDOW:
                        if(windowPassNumber < centerStartPos) {
                            seatDetail.setPassNumber(windowPassNumber++);
                        }
                        break;
                    case CENTER:
                        if(centerPassNumber <= totalNumberPassenger) {
                            seatDetail.setPassNumber(centerPassNumber++);
                        }
                        break;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
            noOfPass--;
            if(noOfPass == 0) {
                System.out.println("All seats filled for waiting passenger");
                break;
            };
        }
    }
}
