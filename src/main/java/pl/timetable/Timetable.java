package pl.timetable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by lodwr on 29.10.2015.
 */
public class Timetable implements TimeTableInterface{

    private ArrayList<Category> categories = new ArrayList<Category>();
    public Timetable(){
        loadDefaultCategories();
    }

    public void addCategory(String categoryName) throws NotAllowedValueException{
        for(Category c: categories)
        {
            if(c.equals(categoryName))
                throw new NotAllowedValueException();
        }
        categories.add(new Category(categoryName));
    }

    public void addTask(String tName, String cName)
    {
        try {
            getCattegory(cName).addTask(tName);
        } catch (NoExistingCategoryException e) {

            Scanner s = new Scanner(System.in);
            char c;
            do {
                System.out.println("Kategoria: "+ cName  +" nie istnieje, czy chcesz ja dodac?(y/n)");
                c=s.next().charAt(0);
                if(c=='y')
                    try {
                        addCategory(cName);
                    } catch (NotAllowedValueException e1) {
                    }
            }while(!(c=='y'||c=='n'));
        } catch (TaskDuplicateException e) {
            System.out.println("Zadanie o tej nazwie ju¿ istnieje w zadanej kategorii");
        }
    }
    public void addTask(String tName, String cName, Priority priority)
    {
        try {
            getCattegory(cName).addTask(tName,priority);
        } catch (NoExistingCategoryException e) {

            Scanner s = new Scanner(System.in);
            char c;
            do {
                System.out.println("Kategoria nie istnieje, czy chcesz ja dodac?(y/n)");
                c=s.next().charAt(0);
                if(c=='y')
                    try {
                        addCategory(cName);
                    } catch (NotAllowedValueException e1) {
                    }
            }while(!(c=='y'||c=='n'));
        } catch (TaskDuplicateException e) {
            System.out.println("Zadanie o tej nazwie ju¿ istnieje w zadanej kategorii");
        }
    }
    public void showCategories() {
        for (Category c: categories)
            System.out.println(c.getCategoryName());
    }
    public void getTasksFromCategory(String categoryName) {
        try{
            ArrayList<Task> tasks=getCattegory(categoryName).getTasks();
            ArrayList<Task> lowPriorityTasks = new ArrayList<Task>();
            for(Task t:tasks){
                if(t.priority==Priority.LOW)
                    lowPriorityTasks.add(t);
                else System.out.println(t.taskName);
            }
            for(Task t:lowPriorityTasks){
                System.out.println(t.taskName);
            }
        } catch (NoExistingCategoryException e) {
            System.out.println("Taka kategoria nie istnieje");
        }
    }

    public boolean deleteTask(String name, String cName) {
        try {
            if(getCattegory(cName).deleteTask(name))
            {
                System.out.println("Zadanie usuniête z listy");
            }else System.out.println("W podanej kategorii nie by³o takiego zadania");
        }
        catch (NoExistingCategoryException e) {
            System.out.println("Nieprawidlowa nazwa kategorii");
        }
        return false;
    }

    public boolean checkTask(String name, String cName) {
        try {
           if(getCattegory(cName).checkTask(name))
               System.out.println("Zadanie zrobione");
            else System.out.println("Nie odnaleziono takiego zadania w podanej kategorii");
        } catch (NoExistingCategoryException e) {
            System.out.println("Nieprawidlowa nazwa kategorii");
        }

        return false;
    }

    public void showUncheckedTasks(String cName) {
        try {
            for (Task t : getCattegory(cName).getTasks()) {
                if (!t.checked)
                    System.out.println(t.taskName);
            }
        }
        catch (NoExistingCategoryException e) {
            System.out.println("Nieprawidlowa nazwa kategorii");
        }
    }
    private Category getCattegory(String name) throws NoExistingCategoryException
    {
        for(Category c: categories)
        {
            if(c.getCategoryName().equals(name))
                return c;
        }
        throw new NoExistingCategoryException();
    }
    private void loadDefaultCategories()
    {
        try {
            addCategory("Dzis");
            addCategory("Jutro");
            addCategory("Pojutrze");
        } catch (NotAllowedValueException e) {
            System.out.println("Taka kategoria ju¿ istnieje");
        }
    }
}
