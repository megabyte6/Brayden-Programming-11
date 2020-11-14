public class Theatre {
    private IntegerSet seatsRemaining;
    private IntegerSet occudpiedSeats;
    private IntegerSet aisleSeats;
    private int totalRows;

    Theatre(int totalSeats, int[] firstRowAisleSeats, int rows){
        seatsRemaining = new IntegerSet();
        occudpiedSeats = new IntegerSet();
        aisleSeats = new IntegerSet();
        for(int i = 0; i< totalSeats;i++){
            seatsRemaining.insert(i+1);
        }
        for(Integer n : firstRowAisleSeats){
           aisleSeats.insert(n);
        }
        totalRows = rows;
    }

    //Default
    Theatre(){
        seatsRemaining = new IntegerSet();
        occudpiedSeats = new IntegerSet();
        aisleSeats = new IntegerSet();
        for(int i = 0; i< 120;i++){
            seatsRemaining.insert(i+1);
        }
        totalRows = 10;
    }

    //Requires: integer, seat number
    //Modifies: this
    //Effects: if the seat is remaining remove seat from IntegerSet and add it to occupied
    //integerset.  Else do nothing
    public void purchaseSeat(Integer seatNum){
        if(seatsRemaining.contains(seatNum)){
            occudpiedSeats.insert(seatNum);
            seatsRemaining.remove(seatNum);
        }
    }

    //Effects: returns the remaining seats in the theatre
    public IntegerSet getSeatsRemaining(){
        return seatsRemaining;
    }

    //Requires: integer, seatNum
    //Effects: returns true if seat is on an aisle, else return false
    public boolean isAisle(Integer seatNum){
        int seatsPerRow = getSize() / totalRows;
        return aisleSeats.contains(seatNum % seatsPerRow);
    }

    //Requires: integer, seatnumber
    //Effects: returns true if seat is purchased, else returns false
    public boolean isPurchased(Integer num){
        return occudpiedSeats.contains(num);
    }

    //Effects: returns number of remaining seats
    public int getSize(){
        return seatsRemaining.size();
    }
}
