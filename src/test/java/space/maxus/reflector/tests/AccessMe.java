package space.maxus.reflector.tests;

public class AccessMe {
    public static String imStatic = "STATIC DATA";

    public String imNormal = "Default Data";

    public static void staticMethod() {
        System.out.println("Hello, Static!");
    }

    public void instanceMethod(int i) {
        System.out.println(i + " - " + i);
    }}
