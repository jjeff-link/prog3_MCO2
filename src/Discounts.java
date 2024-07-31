/**
* Interface for the different discount methods for a reservation
* **/
public interface Discounts {
    /**
    * @param price the initial subtotal price of a reservation
    * @return discount which will be subtracted to the subtotal
    * */
    double iWorkHere(double price);
    /**
     * @param price the initial subtotal price of a reservation
     * @param checkInDate checks if the stay is within the discount terms
     * @param checkOutDate checks if the stay is within the discount terms
     * @return discount which will be subtracted to the subtotal
     * */
    double stay4Get1(double price, int checkInDate, int checkOutDate);
    /**
     * @param price the initial subtotal price of a reservation
     * @param checkInDate checks if the stay is within the discount terms
     * @param checkOutDate checks if the stay is within the discount terms
     * @return discount which will be subtracted to the subtotal
     * */
    double payday(double price, int checkInDate, int checkOutDate);
}
