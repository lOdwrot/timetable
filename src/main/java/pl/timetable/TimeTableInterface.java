package pl.timetable;

/**
 * Created by lodwr on 29.10.2015.
 */
public interface TimeTableInterface {
    public void addCategory(String categoryName) throws NotAllowedValueException;
    public void showCategories();
    public void getTasksFromCategory(String categoryName);
    public boolean deleteTask(String name, String cName);
    public boolean checkTask(String name, String cName);
    public void showUncheckedTasks(String cName);
}
