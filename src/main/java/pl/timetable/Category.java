package pl.timetable;

import java.util.ArrayList;

/**
 * Created by lodwr on 29.10.2015.
 */
public class Category {


    private String categoryName;

    private ArrayList<Task> tasks;
    int lastIndex=0;
    public Category(String name)
    {
        this.categoryName=name;
        tasks = new ArrayList<Task>();
    }
    public void addTask(String name, Priority priority) throws TaskDuplicateException
    {
        for(Task t:tasks)
        if(t.taskName.equals(name))
            throw new TaskDuplicateException();
        tasks.add(new Task(lastIndex,name,priority));
    }
    public void addTask(String name) throws TaskDuplicateException
    {
        for(Task t:tasks)
            if(t.taskName.equals(name))
                throw new TaskDuplicateException();
        tasks.add(new Task(lastIndex,name));
    }
    public boolean checkTask(String name)
    {
        int i=0;
        for(Task t: tasks)
        {
            if(t.taskName.equals(name)) {
                tasks.get(i).check();
                return true;
            }
            i++;
        }
        return false;
    }
    public boolean deleteTask(String name)
    {
        int i=0;
        for(Task t: tasks)
        {
            if(t.taskName.equals(name)) {
                tasks.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }


}
