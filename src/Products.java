public class Products {
    private String Name;
    private double Price;
    private String Manufacturer;
    private String ReleaseDate;

    public Products(String name, double price, String manufacturer, String releaseDate) {
        Name = name;
        Price = price;
        Manufacturer = manufacturer;
        ReleaseDate = releaseDate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }
}
