public abstract class Crop {
    protected String name;
    protected int weight;
    protected String cultivatedSeason;
    protected String type;
    protected String taste;
    protected double price;
    protected String cityName;
    protected String ID;
    protected int amount;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public Crop(String name, String type, int weight, String cultivatedSeason,
                String taste, double price, String ID) {//(for fruits)
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.cultivatedSeason = cultivatedSeason;
        this.taste = taste;
        this.price = price;
        this.ID = ID;
    }

    public Crop(String name, String type, int weight, String cityName, String ID) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.cityName = cityName;
        this.ID = ID;
    }

    public Crop() {
    }

    public abstract String toString();

    public abstract String consumeIt();

    public abstract boolean storeIt(Crop c);//HOCAYA SOR!!!

}
