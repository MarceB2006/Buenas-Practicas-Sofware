import java.util.ArrayList;

public class UserManagerArreglado {
    private static final int MAX_USERS = 10;
    private static ArrayList<String> users = new ArrayList<>();

// Método para agregar un usuario
    public static boolean addUser(String username) {
        // Validación para nombre de usuario vacío o nulo
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Invalid username.");
            return false;
    }

    // Validación para evitar nombres duplicados
        if (users.contains(username)) {
            System.out.println("Username already exists.");
            return false;
        }

    // Se verifica si se puede agregar más usuarios
        if (users.size() < MAX_USERS) {
            users.add(username);
            System.out.println("User added: " + username);
            return true;
        } else {
            System.out.println("User limit reached.");
            return false;
        }
    }

// Método para imprimir la lista de usuarios
    public static void printUsers() {
        for (String user : users) {
        System.out.println(user);
        }
    }
}