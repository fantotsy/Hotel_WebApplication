package ua.fantotsy.entities;

/**
 * Class {@code Category} stores information about categories.
 *
 * @author fantotsy
 * @version 1.0
 */
public class Category {
    private Integer categoryId;
    private String type;
    private Integer numberOfBeds;
    private Integer price;
    private Integer apartments;

    public Category(Integer categoryId, String type, Integer numberOfBeds, Integer price, Integer apartments) {
        this.categoryId = categoryId;
        this.type = type;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.apartments = apartments;
    }

    public Category(String type, Integer numberOfBeds) {
        this.type = type;
        this.numberOfBeds = numberOfBeds;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getApartments() {
        return apartments;
    }

    public void setApartments(Integer apartments) {
        this.apartments = apartments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (!categoryId.equals(category.categoryId)) return false;
        if (!type.equals(category.type)) return false;
        if (!numberOfBeds.equals(category.numberOfBeds)) return false;
        return price.equals(category.price);
    }

    @Override
    public int hashCode() {
        int result = categoryId.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + numberOfBeds.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", type='" + type + '\'' +
                ", numberOfBeds=" + numberOfBeds +
                ", price=" + price +
                '}';
    }
}