public class Product {
    private String name;
    private String description;
    private double price;
    private boolean availableForSale;

    public Product(String name, String description, double price, boolean availableForSale) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.availableForSale = availableForSale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailableForSale() {
        return availableForSale;
    }

    public void setAvailableForSale(boolean availableForSale) {
        this.availableForSale = availableForSale;
    }

    @Override
    public String toString() {
        return "Nome: " + name + "\n" +
                "Descrição: " + description + "\n" +
                "Valor: R$" + String.format("%.2f", price) + "\n" +
                "Disponível para venda: " + (availableForSale ? "Sim" : "Não");
    }
}
