import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) throws Exception {
        File supp = new File("Suppliers.txt");
        Scanner supp_sc = new Scanner(supp);
        supp_sc.useDelimiter(", ");
        ArrayList<Supplier> supp_list = new ArrayList<>();

        File st = new File("Stores.txt");
        Scanner st_sc = new Scanner(st);
        st_sc.useDelimiter(", ");
        ArrayList<Store> st_list = new ArrayList<>();

        File crp = new File("Crops.txt");
        Scanner crp_sc = new Scanner(crp);
        crp_sc.useDelimiter(", ");
        ArrayList<Crop> crp_list = new ArrayList<>();


        while (supp_sc.hasNext()) {//Supplier listesine txt dosyasından ekleme yapılır
            String newLine = supp_sc.nextLine();
            Scanner newScanner = new Scanner(newLine);
            newScanner.useDelimiter(", ");
            Supplier sp = new Supplier(newScanner.next(), newScanner.next(), Double.parseDouble(newScanner.next()));
            supp_list.add(sp);
        }

        while (st_sc.hasNext()) {
            String newLine = st_sc.nextLine();
            Scanner newScanner = new Scanner(newLine);
            newScanner.useDelimiter(", ");
            Store sto = new Store(newScanner.next(), newScanner.next(),
                    Integer.parseInt(newScanner.next()), Integer.parseInt(newScanner.next()));
            st_list.add(sto);
        }

        while(crp_sc.hasNext()) {
            String name = crp_sc.next();
            String type = crp_sc.next();
            String newLine = crp_sc.nextLine();
            Scanner newScanner = new Scanner(newLine);
            newScanner.useDelimiter(", ");
            if(type.equals("fruit")) {
                Crop fr = new Fruit(name, type, Integer.parseInt(newScanner.next()), newScanner.next(),
                        newScanner.next(), Double.parseDouble(newScanner.next()), newScanner.next());
                crp_list.add((Crop) fr);
            }
            else if(type.equals("vegetable")) {
                Vegetable vg = new Vegetable(name, type,
                        Integer.parseInt(newScanner.next()), newScanner.next(), newScanner.next());
                crp_list.add((Crop) vg);
            }
        }

        // Crops.txt dosyasındaki her ID'ye bakıp ona uygun Supplier veya Store'a yerleştir!!!
        for(int i=0; i<crp_list.size(); i++) {
            for(int j=0; j<supp_list.size(); j++) {
                if(crp_list.get(i).getID().equals(supp_list.get(j).getID())) {
                    supp_list.get(j).getCropList().add(crp_list.get(i));
                    break;
                }
            }
            for(int k=0; k< st_list.size(); k++) {
                if(crp_list.get(i).getID().equals(st_list.get(k).getID())) {
                    st_list.get(k).getFruitList().add((Fruit) crp_list.get(i));
                    break;
                }
            }
        }
        //
        while(true) {
            System.out.print("(Press 1) Display all suppliers\n" +
                    "(Press 2) Display all stores\n" +
                    "(Press 3) Buy a fruit crop\n" +
                    "(Press 4) Sell a fruit crop\n" +
                    "(Press 5) Remove a fruit from a store.\n" +
                    "(Press 6) Remove a crop from a supplier.\n" +
                    "(Press 7) Add crop.\n" +
                    "(Press 8) Show remaining budget.\n" +
                    "(Press 9) Show remaining capacity.\n" +
                    "(Press 0) Quit : quits the app.\n");
            Scanner input = new Scanner(System.in);
            int entered = input.nextInt();
            if(entered==1) {
                for(int i=0; i<supp_list.size(); i++) {
                    System.out.println("Name: " + supp_list.get(i).getName());
                    System.out.println("ID: " + supp_list.get(i).getID());
                    System.out.println("Budget: " + supp_list.get(i).getBudget());
                    System.out.println("Crop List: " );
                    for(int j=0; j<supp_list.get(i).getCropList().size(); j++) {
                        Crop currentCrop = supp_list.get(i).getCropList().get(j);
                        System.out.println(currentCrop.getName() + "--> " + supp_list.get(i).howToStore(currentCrop) +
                                "--> " + currentCrop.consumeIt());
                    }
                    System.out.println();
                }
            }
            else if(entered==2) {//
                for(int i=0; i<st_list.size(); i++) {
                    System.out.println("Name: " + st_list.get(i).getName());
                    System.out.println("ID: " + st_list.get(i).getID());
                    System.out.println("maxCapacityArea: " + st_list.get(i).getMaxCapacityArea());
                    System.out.println("KGperSquareMeter: " + st_list.get(i).getKGperSquareMeter());
                    System.out.println("Fruit List: ");
                    for(int j=0; j<st_list.get(i).getFruitList().size(); j++) {
                        Fruit currentFruit = st_list.get(i).fruitList.get(j);
                        System.out.println(currentFruit.getName() + "--> " + st_list.get(i).howToStore(currentFruit) +
                                "--> " + currentFruit.consumeIt());
                    }
                    System.out.println();
                }
            }
            else if(entered==3) {
                System.out.println("For which supplier?");
                for(int i=0; i < supp_list.size(); i++) {
                    System.out.println(i+1 + ") " + supp_list.get(i).getName());
                }
                int supplierIndex = input.nextInt() - 1;
                Supplier currentSupplier = supp_list.get(supplierIndex);
                System.out.println("Which store would you like to buy from? ");
                for(int i=0; i < st_list.size(); i++) {
                    System.out.println(i+1 + ") " + st_list.get(i).getName());
                }
                int storeIndex = input.nextInt() - 1;
                Store currentStore = st_list.get(storeIndex);
                System.out.print("Which fruit would you like to buy? ");
                for(int i=0; i < currentStore.getFruitList().size(); i++) {
                    System.out.println(i+1 + ") " + currentStore.getFruitList().get(i).getName());
                }
                int fruitIndex = input.nextInt() - 1;
                Fruit currentFruit = currentStore.getFruitList().get(fruitIndex);
                currentSupplier.buyCrop(currentFruit, currentStore);
            }
            else if(entered==4) {
                System.out.print("For which supplier? ");
                for(int i=0; i < supp_list.size(); i++) {
                    System.out.println(i+1 + ") " + supp_list.get(i).getName());
                }
                int supplierIndex = input.nextInt() - 1;
                Supplier currentSupplier = supp_list.get(supplierIndex);
                System.out.print("Which store would you like to sell to? ");
                for(int i=0; i < st_list.size(); i++) {
                    System.out.println(i+1 + ") " + st_list.get(i).getName());
                }
                int storeIndex = input.nextInt() - 1;
                Store currentStore = st_list.get(storeIndex);
                System.out.print("Which fruit would you like to sell? ");
                for(int i=0; i < currentSupplier.getCropList().size(); i++) {
                    System.out.println(i+1 + ") " + currentSupplier.getCropList().get(i).getName());
                }
                int fruitIndex = input.nextInt() - 1;
                Crop currentFruit = currentSupplier.getCropList().get(fruitIndex);
                currentSupplier.sellCrop(currentFruit, currentStore);
            }
            else if(entered==5) {
                System.out.print("Which store would you like to remove from? ");
                for(int i=0; i < st_list.size(); i++) {
                    System.out.println(i+1 + ") " + st_list.get(i).getName());
                }
                int storeIndex = input.nextInt() - 1;
                Store currentStore = st_list.get(storeIndex);
                System.out.print("Which fruit would you like to remove? ");
                for(int i=0; i < currentStore.getFruitList().size(); i++) {
                    System.out.println(i+1 + ") " + currentStore.getFruitList().get(i).getName());
                }
                int fruitIndex = input.nextInt() - 1;
                Fruit currentFruit = currentStore.getFruitList().get(fruitIndex);
                currentStore.getFruitList().remove(currentFruit);
            }
            else if(entered==6) {
                System.out.print("For which supplier? ");
                for(int i=0; i < supp_list.size(); i++) {
                    System.out.println(i+1 + ") " + supp_list.get(i).getName());
                }
                int supplierIndex = input.nextInt() - 1;
                Supplier currentSupplier = supp_list.get(supplierIndex);
                System.out.print("Which crop would you like to remove? ");
                for(int i=0; i < currentSupplier.getCropList().size(); i++) {
                    System.out.println(i+1 + ") " + currentSupplier.getCropList().get(i).getName());
                }
                int cropIndex = input.nextInt() - 1;
                Crop currentCrop = currentSupplier.getCropList().get(cropIndex);
                currentSupplier.getCropList().remove(currentCrop);
            }
            else if(entered==7) {
                //
                System.out.print("Which crop would you like to add? ");
                for(int i=0; i < crp_list.size(); i++) {
                    System.out.println(i+1 + ") " + crp_list.get(i).getName());
                }
                int cropIndex = input.nextInt() - 1;
                Crop currentCrop = crp_list.get(cropIndex);
                System.out.print("Where would you like to add it? \n" +
                        "1) to a Store\n"
                        + "2) to a Supplier\n");
                int addWhere = input.nextInt();
                if(addWhere==1) {
                    System.out.print("Which store would you like to add to? ");
                    for(int i=0; i < st_list.size(); i++) {
                        System.out.println(i+1 + ") " + st_list.get(i).getName());
                    }
                    int storeIndex = input.nextInt() - 1;
                    Store currentStore = st_list.get(storeIndex);
                    currentStore.getFruitList().add((Fruit) currentCrop);
                }
                else if(addWhere==2) {
                    System.out.print("For which supplier? ");
                    for(int i=0; i < supp_list.size(); i++) {
                        System.out.println(i+1 + ") " + supp_list.get(i).getName());
                    }
                    int supplierIndex = input.nextInt() - 1;
                    Supplier currentSupplier = supp_list.get(supplierIndex);
                    currentSupplier.getCropList().add(currentCrop);
                }
            }
            else if(entered==8){
                System.out.print("For which supplier? ");
                for(int i=0; i < supp_list.size(); i++) {
                    System.out.println(i+1 + ") " + supp_list.get(i).getName());
                }
                int supplierIndex = input.nextInt() - 1;
                Supplier currentSupplier = supp_list.get(supplierIndex);

                System.out.println(currentSupplier.getName() + " 's remaining budget: "
                        + currentSupplier.getBudget());
            }
            else if(entered==9) {
                System.out.print("For which store? ");
                for(int i=0; i < st_list.size(); i++) {
                    System.out.println(i+1 + ") " + st_list.get(i).getName());
                }
                int storeIndex = input.nextInt() - 1;
                Store currentStore = st_list.get(storeIndex);

                System.out.println(currentStore.getName() + " 's remaining capacity: "
                        + (currentStore.getMaxCapacityArea() - currentStore.getUsedCapacityArea()));
            }
            else if(entered==0) {
                break;
            }

        }
    }
}
