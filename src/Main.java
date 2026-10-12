import java.lang.reflect.Field;
public class Main {
    public static void main(String[] args) throws Exception{
          Class<User> clazz =User.class;

        System.out.println(clazz.isAnnotation());
        Field[] declaredField = clazz.getDeclaredField();
        System.out.println(declaredField);
    }
    }
