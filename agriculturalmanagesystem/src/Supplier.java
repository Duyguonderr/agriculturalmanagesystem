import java.util.ArrayList;

public class Supplier implements CropKeeper {
    private String name;
    private String ID;
    private double budget;
    private ArrayList<Crop> cropList;

    public Supplier(String name, String ID, double budget) {
        this.name = name;
        this.ID = ID;
        this.budget = budget;
        this.cropList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public double getBudget() {
        return budget;
    }

    public ArrayList<Crop> getCropList() {
        return cropList;
    }

    public void buyCrop(Crop c, Store s) {
        try {
            if(s.fruitList.contains(c)) {
                if(budget >= ((Fruit) c).price) {
                    s.exportCrop((Fruit) c);
                    budget -= ((Fruit) c).price;
                    cropList.add(c);
                }
                else {
                    throw new SupplierHasNotEnougMoneyException();
                }
            }
            else {
                throw new FruitNotAvailableException();
            }
        }
        catch(SupplierHasNotEnougMoneyException ex) {
            System.out.println("Supplier does not have enough money!");
        }
        catch(FruitNotAvailableException ex) {
            System.out.println("Fruit is not available!");
        }
    }

    public void sellCrop(Crop c, Store s) {
        try {
            if (cropList.contains(c)) {
                cropList.remove(c);
                s.importCrop((Fruit) c);
                budget += ((Fruit) c).price;
            } else {
                throw new FruitNotFoundException();
            }
        }
        catch(FruitNotFoundException ex) {
            System.out.println("Fruit is not available!");
        }
    }

    public String howToStore(Crop c) {
        if(c instanceof Fruit) {
            return "fruits in big refrigerators";
        }
        else if(c instanceof Vegetable) {
            return "vegetables in the field booths";
        }
        return "";
    }
}

class SupplierHasNotEnougMoneyException extends Exception {
    public SupplierHasNotEnougMoneyException() {}
}

class FruitNotAvailableException extends Exception {
    public FruitNotAvailableException() {}
}
