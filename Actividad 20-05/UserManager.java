public class UserManager {
    public static String[] users = new String[10];
    public static int userCount = 0;
    
    public static boolean a(String u) {
        if(u != null && u.length() > 0) {
            if(userCount < 10) {
                users[userCount] = u;
                userCount++;
                System.out.println("User added: " + u);
                return true;
            } else {
                System.out.println("Error");
                return false;
            }
        } else {
            return false;
        }
    }
    
    public static void p() {
        for(int i=0; i<userCount; i++)
            System.out.println(users[i]);
    }
}