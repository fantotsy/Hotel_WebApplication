package ua.fantotsy.entities;

/**
 * Class {@code Apartment} stores information about apartments.
 *
 * @author fantotsy
 * @version 1.0
 */
public class Apartment {
    private Integer apartmentId;
    private Category category;

    public Apartment(Integer apartmentId, Category category) {
        this.apartmentId = apartmentId;
        this.category = category;
    }

    public Apartment(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apartment)) return false;

        Apartment apartment = (Apartment) o;

        if (!apartmentId.equals(apartment.apartmentId)) return false;
        return category.equals(apartment.category);
    }

    @Override
    public int hashCode() {
        int result = apartmentId.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId=" + apartmentId +
                ", category=" + category +
                '}';
    }
}