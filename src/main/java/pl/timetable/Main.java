package pl.timetable;

import java.util.Scanner;

/**
 * Created by lodwr on 29.10.2015.
 */
public class Main {
    public static void main(String[] args) {
        Timetable tt = new Timetable();
        Scanner s = new Scanner(System.in);

        String arg1,arg2;
        int c;

        do {
            System.out.println("Co chcesz zrobic");
            System.out.println("0 - EXIT");
            System.out.println("1 - Zobacz kategorie");
            System.out.println("2 - Zobacz zadania z wybranej kategorii");
            System.out.println("3 - Usun zadanie z wybranej kategorii");
            System.out.println("4 - Oznacz zadanie jako zrobione");
            System.out.println("5 - Pokaz zadania do zorbienia");
            System.out.println("6 - Dodaj zadanie");
            System.out.println("7 - Dodaj kategorie");
            c = s.nextInt();
            s.nextLine();
            switch (c) {
                case 1:
                    tt.showCategories();
                    break;
                case 2:
                    System.out.println("Podaj kategoriê");
                    tt.getTasksFromCategory(s.nextLine());
                    break;
                case 3:
                    System.out.println("Podaj kategoriê");
                    arg1=s.nextLine();
                    System.out.println("Podaj zadanie");
                    arg2=s.nextLine();
                    tt.deleteTask(arg2,arg1);
                    break;
                case 4:
                    System.out.println("Podaj kategoriê");
                    arg1=s.nextLine();
                    System.out.println("Podaj zadanie");
                    arg2=s.nextLine();
                    tt.checkTask(arg2, arg1);
                    break;
                case 5:
                    System.out.println("Podaj kategoriê");
                    tt.showUncheckedTasks(s.nextLine());
                    break;
                case 6:
                    System.out.println("Podaj kategoriê");
                    arg1=s.nextLine();
                    System.out.println("Podaj zadanie");
                    arg2=s.nextLine();
                    do {
                        System.out.println("Wazne? y/n" );
                        c=s.next().charAt(0);
                    }while(!(c=='y'||c=='n'));
                    if(c=='n')
                        tt.addTask(arg2, arg1);
                    if(c=='y')
                        tt.addTask(arg2, arg1, Priority.HIGH);
                    break;
                case 7:
                    System.out.println("Podaj nazwe nowej kategorii");
                    arg1=s.nextLine();
                    try {
                        tt.addCategory(arg1);
                    } catch (NotAllowedValueException e) {
                        System.out.println("Kategoria ju¿ istnieje!");
                    }
                    break;
            }
        } while (c != 0);
    }
}