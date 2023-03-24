public class Vegetable extends Crop implements Comparable<Vegetable> {
    protected CropKeeper ck;

    public Vegetable(String name, String type, int weight, String cityName, String ID) {
        //String name, String type, int weight, String cityName
        super(name, type, weight, cityName, ID);
    }

    public String toString() {
        return "city: " + cityName;
    }

    public String consumeIt() {
        return "vegetables are cooked";
    }

    public boolean storeIt(Crop c) {
        //HOCAYA SOR!!!
        return false;
    }

    public int compareTo(Vegetable anotherVegetable) {
        if (this.name.equals(anotherVegetable.name)) {
            return 0;
        } else {
            return (this.weight - anotherVegetable.weight);
        }
    }
}

class CanNotBeStoredException extends Exception {
    public CanNotBeStoredException() {}
}
