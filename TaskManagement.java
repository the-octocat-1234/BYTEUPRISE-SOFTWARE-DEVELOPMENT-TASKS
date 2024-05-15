import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class TaskManagement implements Serializable {
    private String title;
    private String description;
    private Date dueDate;

    public TaskManagement(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }
}

class TaskManager {
    private List<TaskManagement> tasks;
    private static final String FILENAME = "tasks.ser";

    public TaskManager() {
        tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTask(TaskManagement task) {
        tasks.add(task);
        saveTasks();
    }

    public void editTask(int index, TaskManagement task) {
        tasks.set(index, task);
        saveTasks();
    }

    public void deleteTask(int index) {
        tasks.remove(index);
        saveTasks();
    }

    public List<TaskManagement> getTasks() {
        return tasks;
    }

    private void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            tasks = (List<TaskManagement>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File doesn't exist or couldn't be read, ignore and start with an empty task list
        }
    }
}

 class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Edit Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    String dueDateString = scanner.nextLine();
                    Date dueDate = null;
                    try {
                        dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format!");
                        continue;
                    }
                    taskManager.addTask(new TaskManagement(title, description, dueDate));
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    List<TaskManagement> tasks = taskManager.getTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks found!");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            TaskManagement task = tasks.get(i);
                            System.out.println("Task " + (i + 1) + ":");
                            System.out.println("Title: " + task.getTitle());
                            System.out.println("Description: " + task.getDescription());
                            System.out.println("Due Date: " + new SimpleDateFormat("yyyy-MM-dd").format(task.getDueDate()));
                            System.out.println();
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter the index of the task to edit: ");
                    int editIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    if (editIndex >= 0 && editIndex < taskManager.getTasks().size()) {
                        System.out.print("Enter new task title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter new task description: ");
                        String newDescription = scanner.nextLine();
                        System.out.print("Enter new due date (YYYY-MM-DD): ");
                        String newDueDateString = scanner.nextLine();
                        Date newDueDate = null;
                        try {
                            newDueDate = new SimpleDateFormat("yyyy-MM-dd").parse(newDueDateString);
                        } catch (ParseException e) {
                            System.out.println("Invalid date format!");
                            continue;
                        }
                        taskManager.editTask(editIndex, new TaskManagement(newTitle, newDescription, newDueDate));
                        System.out.println("Task edited successfully!");
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;
                case 4:
                    System.out.print("Enter the index of the task to delete: ");
                    int deleteIndex = scanner.nextInt() - 1;
                    if (deleteIndex >= 0 && deleteIndex < taskManager.getTasks().size()) {
                        taskManager.deleteTask(deleteIndex);
                        System.out.println("Task deleted successfully!");
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
