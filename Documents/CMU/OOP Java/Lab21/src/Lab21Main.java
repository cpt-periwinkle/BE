import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Lab21Main {

    public void classFun(Class<?> c) {
        try {
            System.out.println("1. Canonical Class Name: " + c.getCanonicalName());

            System.out.println("\n2. Member Fields:\n");
            Field[] fields = c.getDeclaredFields();
            if (fields.length == 0) {
                System.out.println("No member data found!");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < fields.length; i++) {
                    sb.append("\t").append((i + 1)).append(". ").append(fields[i]).append("\n");
                }
                System.out.println(sb.toString());
            }

            System.out.println("\n3. Local Constructors: ");
            Constructor<?>[] localConstructors = c.getDeclaredConstructors();
            if (localConstructors.length == 0) {
                System.out.println("No local constructors found!");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < localConstructors.length; i++) {
                    sb.append("\t").append((i + 1)).append(". ").append(localConstructors[i]).append("\n");
                }
                System.out.println(sb.toString());
            }

            System.out.println("\n4. Public Constructors: ");
            Constructor<?>[] publicConstructors = c.getConstructors();
            if (publicConstructors.length == 0) {
                System.out.println("No public constructors found!");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < publicConstructors.length; i++) {
                    sb.append("\t").append((i + 1)).append(". ").append(publicConstructors[i]).append("\n");
                }
                System.out.println(sb.toString());
            }

            System.out.println("\n5. Local Methods: ");
            Method[] localMethods = c.getDeclaredMethods();
            if (localMethods.length == 0) {
                System.out.println("No local methods found!");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < localMethods.length; i++) {
                    sb.append("\t").append((i + 1)).append(". ").append(localMethods[i]).append("\n");
                }
                System.out.println(sb.toString());
            }

            System.out.println("\n6. Public Methods: ");
            Method[] publicMethods = c.getMethods();
            if (localMethods.length == 0) {
                System.out.println("No public methods found!");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < publicMethods.length; i++) {
                    sb.append("\t").append((i + 1)).append(". ").append(publicMethods[i]).append("\n");
                }
                System.out.println(sb.toString());
            }

            Constructor<?> defaultConstructor = c.getDeclaredConstructor();
            defaultConstructor.setAccessible(true);
            Object employeeInstance = defaultConstructor.newInstance();
            System.out.println("Is employee an enum? " + c.isEnum());
            System.out.println("Is employee an interface? " + c.isInterface());
            System.out.println("Employee Instance: " + employeeInstance.toString());

            Method setSalaryMethod = find(publicMethods, "setSalary");
            if (setSalaryMethod != null) {
                setSalaryMethod.invoke(employeeInstance, 1000.0);
                System.out.println("setSalary() invoked successfully!");
            } else {
                System.out.println("setSalary method not found!");
            }
            Method getSalaryMethod = find(publicMethods, "getSalary");
            if (getSalaryMethod != null) {
                Object salary = getSalaryMethod.invoke(employeeInstance);
                System.out.println("getSalary() returned: " + salary);
            } else {
                System.out.println("getSalary method not found!");
            }
        } catch (Exception e)   {
            System.out.println(e.getMessage());
        }
    }

    private static Method find(Method[] methods, String what) {
        for (Method m: methods) {
            if (m.toString().contains(what)) {
                return m;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            Lab21Main lab21 = new Lab21Main();
            Class<?> c = Class.forName("Employee");
            lab21.classFun(c);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
