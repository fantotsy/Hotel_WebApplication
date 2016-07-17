package ua.fantotsy.entities;

public class Reservation {
    private Integer reservationId;
    private Guest guest;
    private Apartment apartment;
    private String arrival;
    private String departure;
    private Integer totalPrice;
    private Integer status;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Reservation(Guest guest, Apartment apartment, String arrival, String departure) {
        this.guest = guest;
        this.apartment = apartment;
        this.arrival = arrival;
        this.departure = departure;
    }

    public Reservation(Guest guest, Apartment apartment, String arrival, String departure, Integer totalPrice) {
        this.guest = guest;
        this.apartment = apartment;
        this.arrival = arrival;
        this.departure = departure;
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;

        Reservation that = (Reservation) o;

        if (!reservationId.equals(that.reservationId)) return false;
        if (!guest.equals(that.guest)) return false;
        if (!apartment.equals(that.apartment)) return false;
        if (!arrival.equals(that.arrival)) return false;
        if (!departure.equals(that.departure)) return false;
        if (!totalPrice.equals(that.totalPrice)) return false;
        return status.equals(that.status);

    }

    @Override
    public int hashCode() {
        int result = reservationId.hashCode();
        result = 31 * result + guest.hashCode();
        result = 31 * result + apartment.hashCode();
        result = 31 * result + arrival.hashCode();
        result = 31 * result + departure.hashCode();
        result = 31 * result + totalPrice.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", guest=" + guest +
                ", apartment=" + apartment +
                ", arrival=" + arrival +
                ", departure=" + departure +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                '}';
    }
}