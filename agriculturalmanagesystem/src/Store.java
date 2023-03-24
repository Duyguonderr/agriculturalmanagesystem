import java.util.ArrayList;

public class Store implements CropKeeper {
    private String ID;
    private String name;
    private  int maxCapacityArea;
    private  int usedCapacityArea;
    private int KGperSquareMeter = 10;
    public  ArrayList<Fruit> fruitList;

    public Store(String name, String ID, int maxCapacityArea, int KGperSquareMeter) {
        this.ID = ID;
        this.name = name;
        this.maxCapacityArea = maxCapacityArea;
        this.usedCapacityArea = 0;
        this.KGperSquareMeter = KGperSquareMeter;
        this.fruitList = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public  int getMaxCapacityArea() {
        return maxCapacityArea;
    }

    public  int getUsedCapacityArea() {
        return usedCapacityArea;
    }

    public int getKGperSquareMeter() {
        return KGperSquareMeter;
    }

    public  ArrayList<Fruit> getFruitList() {
        return fruitList;
    }

    public int availableCapacity() {
        return (maxCapacityArea - usedCapacityArea);
    }

    public  boolean canBeStored(Fruit f) throws CapacityNotEnoughException {
        if (usedCapacityArea + (f.weight / 10) <= maxCapacityArea) {
            return true;
        } else {
            throw new CapacityNotEnoughException();
        }
    }

    public  void importCrop(Fruit f) {
        boolean doesSameNameExist = false;

        try {
            boolean canBeStored = canBeStored(f);
        }
        catch(CapacityNotEnoughException ex) {
            System.out.println("Max capacity is reached!");
        }

        for(int i=0; i< fruitList.size(); i++) {
            if(f.getName().equals(fruitList.get(i).getName())) {
                doesSameNameExist = true;
                fruitList.get(i).setAmount(fruitList.get(i).getAmount() + 1);
            }
        }
        if(!doesSameNameExist) {
            fruitList.add(f);
        }
        usedCapacityArea += (f.weight / 10);
    }

    public  void exportCrop(Fruit f) {
        try {
            if (fruitList.contains(f)) {
                fruitList.remove(f);
                usedCapacityArea -= (f.weight / 10);
            } else {
                throw new FruitNotFoundException();
            }
        }
        catch(FruitNotFoundException ex) {
            System.out.println("Fruit is not found!");
        }



    }

    public String howToStore(Crop c) {
        if(c instanceof Fruit) {
            return "fruits in large refrigerated cooler rooms";
        }
        else if(c instanceof Vegetable) {
            return "vegetables in sheds, not listed";
        }
        return "";
    }
}

class CapacityNotEnoughException extends Exception {
    public CapacityNotEnoughException() {}
}

class FruitNotFoundException extends Exception {
    public FruitNotFoundException() {}
}
