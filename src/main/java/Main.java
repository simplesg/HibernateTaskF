import entity.Discipline;
import entity.Role;
import entity.Task;
import entity.User;
import entity.enums.Status;
import entity.enums.DisciplineType;
import service.DisciplineService;
import service.RoleService;
import service.TaskService;
import service.UserService;
import utils.HibernateUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final UserService userService = new UserService();
    private static final RoleService roleService = new RoleService();
    private static final TaskService taskService = new TaskService();
    private static final DisciplineService disciplineService = new DisciplineService();

    public static void main(String[] args) throws SQLException {
        Set<Role> roles = roleFactory();
        List<Task> tasks = taskFactory();
        List<Discipline> disciplineList = disciplines();
        List<User> userList = AmUserFactory(roles.stream().filter(it -> it.getName().equals("Intern")).collect(Collectors.toSet()), tasks, disciplineList.get(0));

        //ADDING INITIAL VALUES TO DATABASE
        for(Role role : roles){
            roleService.addToDatabase(role);
        }
        for (Task task : tasks) {
            taskService.addToDatabase(task);
        }
        for (Discipline discipline : disciplineList) {
            disciplineService.addToDatabase(discipline);
        }
        for (User user : userList) {
            userService.addToDatabase(user);
        }

        //Set THE AM HEAD
        disciplineList.get(0).setHeadOfDiscipline(userList.get(0));
        disciplineService.update(disciplineList.get(0));

        //Updating disciplines with users
        disciplineList.get(1).setUsers(Arrays.asList(TestUserFactory(
                roles.stream().filter(it -> it.getName().equals("Employee") || it.getName().equals("Intern")).collect(Collectors.toSet())
                , tasks, disciplineList.get(1)).get(1)));
        disciplineService.update(disciplineList.get(1));

        disciplineList.get(2).setUsers(DevUserFactory(
                roles.stream().filter(it -> it.getName().equals("Employee") || it.getName().equals("Intern")).collect(Collectors.toSet())
                , tasks, disciplineList.get(2)));
        disciplineService.update(disciplineList.get(2));

        //adding users for TEST HEAD and DEV Head
        User TestHead = new User("Alex", "ciumbac", "alex@endava.com", "alexc", Date.valueOf(LocalDate.now()),true, disciplineList.get(1), Collections.EMPTY_LIST
                , roles.stream().filter(it -> it.getName().equals("Head")).collect(Collectors.toSet()));
        userService.addToDatabase(TestHead);

        User DevHead = new User("Nick", "stropsa", "nick@endava.com", "nick",Date.valueOf(LocalDate.now()),true, disciplineList.get(2), Collections.EMPTY_LIST
                , roles.stream().filter(it -> it.getName().equals("Head")).collect(Collectors.toSet()));
        userService.addToDatabase(DevHead);


        //SET the Disciplines HEAD
        disciplineList.get(1).setHeadOfDiscipline(TestHead);
        disciplineList.get(2).setHeadOfDiscipline(DevHead);
        disciplineService.update(disciplineList.get(1));
        disciplineService.update(disciplineList.get(2));
        printSeparator();

        //List All Users with specific name
        List<User> list = userService.getUserByRole("Head");
        for (User user : list) {
            System.out.println(user);
            System.out.println("");
        }
        userService.closeTransactionSession();
        printSeparator();
        printSeparator();
//
        //List All Users from AM discipline
        List<User> AmUsers = userService.getUsersByDiscipline(DisciplineType.AM);
        for (User user : AmUsers) {
            System.out.println(user);
            printSeparator();
        }
        userService.closeTransactionSession();
        Discipline discipline = disciplineService.getDiscipline(DisciplineType.AM);
        System.out.println(discipline);
        disciplineService.closeTransactionSession();
        printSeparator();

        //List All Users with Task StatusTODO
        List<User> UsersWithTODO = userService.getUsersByTaskStatus(Status.TODO);
        for (User user : UsersWithTODO) {
            System.out.println(user);
            printSeparator();
        }
        userService.closeTransactionSession();

//        Changing the HEAD of DEV
        User user = new User("eugen", "chirilov", "eugen@endava.com", "eugen",Date.valueOf(LocalDate.now()),true, disciplineList.get(2), Collections.EMPTY_LIST
                , roles.stream().filter(it -> it.getName().equals("Head")).collect(Collectors.toSet()));
        userService.addToDatabase(user);
        Discipline DevDiscipline = disciplineService.getDiscipline(DisciplineType.DEV);
        System.out.println(DevDiscipline);
        disciplineService.closeTransactionSession();
        printSeparator();

        DevDiscipline.setHeadOfDiscipline(user);
        disciplineService.update(DevDiscipline);
        System.out.println(disciplineService.getDiscipline(DisciplineType.DEV));
        disciplineService.closeTransactionSession();


        //Soft delete a User
        List<User> userById = userService.getUserById(3);
        userService.closeTransactionSession();

        if(userById.size() == 1)
        userService.delete(userById.get(0));

        HibernateUtils.closeSessionFactory();
    }


    private static Set<Role> roleFactory() {
        Role role1 = new Role("Intern");
        Role role2 = new Role("Head");
        Role role3 = new Role("Employee");

        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        return roles;
    }

    public static List<Discipline> disciplines() {
        List<Discipline> disciplines = new ArrayList<>();
        disciplines.add(new Discipline(DisciplineType.AM));
        disciplines.add(new Discipline(DisciplineType.TEST));
        disciplines.add(new Discipline(DisciplineType.DEV));

        return disciplines;
    }

    private static List<User> AmUserFactory(Set<Role> roleSet, List<Task> tasks, Discipline discipline) {
        List<User> users = new ArrayList<>();
        users.add(new User("sorin", "gorea", "sgorea@endava.com", "sgorea", Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(0)), roleSet));
        users.add(new User("andrei", "burns", "vadim@endava.com", "andrei",Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(1)), roleSet));
        users.add(new User("vlad", "timur", "vlad@endava.com", "vlad",Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(2)), roleSet));
        users.add(new User("vadim", "besn", "vaadim@endava.com", "vaadim",Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(3)), roleSet));
        users.add(new User("victoria", "burns", "vburns@endava.com", "victoria",Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(4)), roleSet));

        return users;
    }

    private static List<User> TestUserFactory(Set<Role> roleSet, List<Task> tasks, Discipline discipline) {
        List<User> users = new ArrayList<>();
        users.add(new User("timur", "delimh", "tim@endava.com", "tim", Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(0)), roleSet));
        users.add(new User("catea", "burns", "catea@endava.com", "catea",Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(1)), roleSet));
        users.add(new User("vladislav", "timur", "vladislav@endava.com", "vladislav",Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(2)), roleSet));
        users.add(new User("serghei", "besn", "serghei@endava.com", "serghei",Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(3)), roleSet));
        users.add(new User("victorita", "burns", "vicburns@endava.com", "victorita",Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(4)), roleSet));

        return users;
    }

    private static List<User> DevUserFactory(Set<Role> roleSet, List<Task> tasks, Discipline discipline) {
        List<User> users = new ArrayList<>();
        users.add(new User("valentina", "gorea", "valentina@endava.com", "valentinag", Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(0)), roleSet));
        users.add(new User("nicolae", "burns", "nicolae@endava.com", "nicolaes",Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(1)), roleSet));
        users.add(new User("vlad", "timur", "vladik@endava.com", "vladik",Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(2)), roleSet));
        users.add(new User("petru", "besn", "petru@endava.com", "petru",Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(3)), roleSet));
        users.add(new User("linda", "burns", "linda@endava.com", "linda",Date.valueOf(LocalDate.now()),true,
                discipline, Arrays.asList(tasks.get(4)), roleSet));

        return users;
    }

    public static List<Task> taskFactory() {
        return Arrays.asList(new Task("Java core", "java core exercises", Date.valueOf(LocalDate.of(2020, 10, 25)), Date.valueOf(LocalDate.now()), Status.DONE),
                new Task("Java Stream", "java stream exercises", Date.valueOf(LocalDate.of(2020, 10, 25)), Date.valueOf(LocalDate.now()), Status.TODO),
                new Task("Java core", "java core exercises", Date.valueOf(LocalDate.of(2020, 10, 25)), Date.valueOf(LocalDate.now()), Status.TODO),
                new Task("Hibernate", "hibernate core exercises", Date.valueOf(LocalDate.of(2020, 10, 25)), Date.valueOf(LocalDate.now()), Status.PROGRESS),
                new Task("Spring Core", "spring core exercises",Date.valueOf(LocalDate.of(2020, 10, 25)), Date.valueOf(LocalDate.now()), Status.PROGRESS));

    }

    public static void printSeparator() {
        System.out.println("----------------------------------------------------------------");
    }


}
