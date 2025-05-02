# 🧪 Análisis de Calidad de Software: `UserManager`

## 📌 Problemas Identificados

### 1. **Nombres de métodos no descriptivos (`a`, `p`)**
- **Problema**: Los métodos se nombran con letras individuales (`a`, `p`), lo cual no da ninguna pista sobre su propósito o comportamiento.
- **Impacto**: Dificulta la comprensión del código por parte de otros desarrolladores (o incluso del mismo autor con el tiempo), y complica su mantenimiento y documentación.
- **Solución**: Utilizar nombres significativos que describan claramente la acción que realiza el método, por ejemplo `addUser(String username)` para agregar un usuario y `printUsers()` para mostrarlos.

```java
    public static boolean a(String u) { ... } // debería llamarse addUser
    public static void p() { ... }           // debería llamarse printUsers
```

---

### 2. **Uso de variables y campos públicos (`users`, `userCount`)**
- **Problema**: Las variables `users` y `userCount` son accesibles directamente desde fuera de la clase.
- **Impacto**: Esto rompe el principio de encapsulamiento de la programación orientada a objetos, exponiendo los datos internos a modificaciones externas no controladas, lo cual puede generar errores difíciles de rastrear.
- **Solución**: Declarar estas variables como `private` y proveer métodos públicos para acceder o modificar su contenido de forma controlada (getters y setters).

```java
    public static String[] users = new String[10];
    public static int userCount = 0;
```

---

### 3. **Uso de un array estático con tamaño fijo**
- **Problema**: El uso de un array `String[10]` restringe artificialmente la cantidad de usuarios que se pueden agregar.
- **Impacto**: Esto limita la escalabilidad del sistema y puede provocar errores (por ejemplo, si se intenta agregar más de 10 usuarios).
- **Solución**: Reemplazar el array por una estructura de datos dinámica como `ArrayList<String>`, que se ajusta automáticamente al número de elementos.

```java
    public static String[] users = new String[10];
        if(userCount < 10) { ... } // Restricción artificial
```

---

### 4. **Mensajes de error poco informativos**
- **Problema**: Cuando ocurre un error, el mensaje simplemente dice `"Error"`, sin dar detalles sobre la causa.
- **Impacto**: Esto dificulta el diagnóstico de fallos tanto para el usuario final como para el desarrollador que depura el sistema.
- **Solución**: Usar mensajes específicos como `"User limit reached"` o `"Username cannot be empty"`, lo que permite entender y resolver el problema con mayor facilidad.

```java
    {...} else {
    System.out.println("Error"); // Mensaje genérico
    return false;
    }
```

---

### 5. **Falta de validación de nombres duplicados**
- **Problema**: El sistema no comprueba si un nombre de usuario ya ha sido agregado anteriormente.
- **Impacto**: Puede llevar a inconsistencias, ya que se podrían tener múltiples entradas con el mismo nombre, lo que no siempre es deseable (por ejemplo, si se espera que los nombres sean únicos).
- **Solución**: Agregar una verificación previa al agregar un usuario para asegurarse de que el nombre no esté repetido.

```java
    if(u != null && u.length() > 0) { ... } // No valida duplicados
```

---

## ✅ Conclusión

El código actual de `UserManager` presenta múltiples problemas de diseño y calidad que afectan negativamente su legibilidad, mantenibilidad y escalabilidad. Estos problemas incluyen la mala elección de nombres, violaciones del principio de encapsulamiento, uso de estructuras de datos inflexibles, manejo deficiente de errores y ausencia de validaciones clave. Para mejorar significativamente la calidad del software, se recomienda aplicar principios de buenas prácticas en programación orientada a objetos, como el uso de nombres claros, encapsulamiento adecuado, estructuras dinámicas y validación de datos. Implementar estas mejoras no solo facilitará el trabajo de los desarrolladores, sino que también hará el sistema más robusto y preparado para crecer.


## ✅ Código Mejorado

```java
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
```