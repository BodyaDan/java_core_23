import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.*;

public class Fraction {
    String nameOfFraction;
    ArrayList<Deputy> deputies = new ArrayList<Deputy>();
    Stream<Deputy> deputyStream = deputies.stream();

    public Fraction(String nameOfFraction) {
        this.nameOfFraction = nameOfFraction;
    }

    public void addDeputy() {
        boolean fencer;
        Scanner scn = new Scanner(System.in);
        System.out.print("Вага депутата: ");
        double weight = scn.nextDouble();
        System.out.print("Ріст депутата: ");
        double height = scn.nextDouble();
        System.out.print("Ім'я депутата: ");
        String name = scn.next();
        System.out.print("Прізвище депутата: ");
        String lastname = scn.next();
        System.out.print("Вік депутата: ");
        int age = scn.nextInt();
        System.out.print("Чи є депутат хабарником? (0 - ні, 1 - так): ");
        while (true) {
            int n = scn.nextInt();
            if (n == 0) {
                fencer = false;
                break;
            }
            else if (n == 1) {
                fencer = true;
                break;
            }
            else
                System.out.println("Щось не так, спробуйте ще раз");
        }
        deputies.add(new Deputy(weight, height, name, lastname, age, fencer, nameOfFraction));
        System.out.println("Депутат успішно доданий!\n");
    }

    public void removeDeputy() {
        Scanner scn = new Scanner(System.in);
        int index;
        while (true) {
            System.out.print("Введіть порядковий номер депутата");
            index = scn.nextInt();
            if ((index >= 0) && (index < deputies.size())) {
                System.out.println("Депутат " + deputies.get(index).toString() + " видалений");
                deputies.remove(index);
                break;
            }
            else
                System.out.println("Щось пішло не так, спробуйте ще раз");
        }
    }

    public void showFencers() {
        System.out.println("Хабарники фракції " + nameOfFraction);
        List<Deputy> fencersList = deputyStream.filter(x -> x.fencer).collect(Collectors.toList());
        System.out.println(fencersList);
    }

    public void showTheBiggestFencer() {
        System.out.println("Найбільший хабарник фрації " + nameOfFraction);
        Deputy theDeputy = deputyStream.max(Comparator.comparing(Deputy::getFenceCount)).get();
        System.out.println(theDeputy.lastname + " " + theDeputy.name + " хабар:" + theDeputy.fenceCount);
    }

    public void showAllDeputies() {
        System.out.println("Усі депутати фракції " + nameOfFraction);
        for (Deputy dpt : deputies)
            System.out.println(dpt.lastname + " " + dpt.name + " " + dpt.age);
    }

    public void clearAllFraction() {
        System.out.println("Фракція " + nameOfFraction + " повністю очищена від депутатів");
        deputies.clear();
    }

    public String getNameOfFraction() {
        return nameOfFraction;
    }

    public void setNameOfFraction(String nameOfFraction) {
        this.nameOfFraction = nameOfFraction;
    }
}
