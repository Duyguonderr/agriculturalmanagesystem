public class Fruit extends Crop implements Comparable<Fruit> {
    protected String color;
    protected CropKeeper ck;

    public Fruit(String name, String type, int weight, String cultivatedSeason, String taste, double price, String ID) {
        //String name, String type, int weight, String cultivatedSeason, String taste, double price
        super(name, type, weight, cultivatedSeason, taste, price, ID);
    }

    public String toString() {
        return "taste: " + taste + " price is: " + price;
    }

    public String consumeIt() {
        return "fruits are consumed raw";
    }

    public boolean storeIt(Crop c) {
        return true;
    }

    public int compareTo(Fruit anotherFruit) {
        if (this.name.equals(anotherFruit.name) && this.color.equals(anotherFruit.color)) {
            return 0;
        } else {
            return (this.weight - anotherFruit.weight);
        }
    }
}

