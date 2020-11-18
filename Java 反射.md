## Java 反射

### 反射的三种实现方式

```java
public class TestReflection {
  public static void main(String[] args) throws ClassNotFoundException {
    // 获取类对象的三种方式
    // 1.第一种方式(.class)
    Class student1 = Student.class;
    System.out.println(student1);
    // 2.第二种方式(调用forName，传全限定类名)
    Class<?> student2 = Class.forName("com.taking.reflect.Student");
    System.out.println(student2);
    // 3.第三种方式(调用实例对象的getClass()方法)
    Student student = new Student();
    Class<? extends Student> student3 = student.getClass();
    System.out.println(student3);
  }
}
```

### 属性操作

```java
public class TestFields {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<Student> student = Student.class;
        // 调用getFields()方法只能获取到public修饰的属性
        Field[] fields = student.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("--------------------");
        // 调用getDeclaredFields()方法可以获取所有的属性
        Field[] declaredFields = student.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }
        Student instance = student.newInstance();
        Field id = student.getDeclaredField("id");
        Field name = student.getDeclaredField("name");
        Field age = student.getDeclaredField("age");
        System.out.println(id);
        System.out.println(name);
        System.out.println(age);
        // 给私有属性赋值，需要调用setAccessible()方法，传入参数true，即将该属性设置为可注入
        id.setAccessible(true);
        id.set(instance, 1L);
        name.set(instance, "Taking");
        age.set(instance, 22);
        System.out.println(instance);
    }
}
```

### 方法操作

```java
public class TestMethods {
  public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
    Class<Student> student = Student.class;
    // 调用getMethods()方法只能获取public修饰的方法
    Method[] methods = student.getMethods();
    for (Method method : methods) {
      System.out.println(method);
    }
    System.out.println("--------------------");
    // 调用getDeclaredMethods()方法可以获取所有的方法
    Method[] declaredMethods = student.getDeclaredMethods();
    for (Method declaredMethod : declaredMethods) {
      System.out.println(declaredMethod);
    }
    Student instance = student.newInstance();
    Method swim = student.getDeclaredMethod("swim");
    Method read = student.getDeclaredMethod("read");
    Method study1 = student.getDeclaredMethod("study");
    Method study2 = student.getDeclaredMethod("study", String.class);
    Method study3 = student.getDeclaredMethod("study", String.class, int.class);
    // 调用private修饰的方法需要先调用setAccessible()方法，传入参数true，即设为可注入
    swim.setAccessible(true);
    swim.invoke(instance);
    read.invoke(instance);
    study1.invoke(instance);
    study2.invoke(instance, "Java");
    study3.invoke(instance, "Java", 1);
  }
}
```

### 构造器操作

```java
public class TestConstructors {
  public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class<Student> student = Student.class;
    Constructor<?>[] constructors = student.getConstructors();
    for (Constructor<?> constructor : constructors) {
      System.out.println(constructor);
    }
    Constructor<Student> constructor = student.getConstructor(Long.class, String.class, Integer.class);
    Student instance = constructor.newInstance(1L, "Taking", 22);
    System.out.println(instance);
  }
}
```

