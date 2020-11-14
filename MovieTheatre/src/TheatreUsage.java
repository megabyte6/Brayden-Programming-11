public class TheatreUsage {
    public static void main(String[] args) {
        int[] firstRowAisleSeats = {2,3,10,11};
        Theatre test = new Theatre(60, firstRowAisleSeats, 5 );
        test.purchaseSeat(10);
        test.getSeatsRemaining();
        test.isAisle(4);
        test.isPurchased(2);
        test.getSize();
    }
}
