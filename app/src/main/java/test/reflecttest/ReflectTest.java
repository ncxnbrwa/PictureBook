package test.reflecttest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by ncx on 2021/3/15
 * 反射功能点测试
 */
public class ReflectTest {
    public static void constructorTest() {
        Example_01 example = new Example_01("10", "20", "30");
        Class<? extends Example_01> exampleC = example.getClass();
        Constructor<?>[] constructors = exampleC.getDeclaredConstructors();
        //构造方法会按照声明顺序倒序输出
        for (int i = 0; i < constructors.length; i++) {
            Constructor<?> constructor = constructors[i];
            System.out.println("查看是否带有可变数量的参数:" + constructor.isVarArgs());
            System.out.println("该构造方法的入口参数类型依次为:");
            //获取构造方法所有参数类型
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (Class<?> parameter : parameterTypes) {
                System.out.println(" " + parameter);
            }
            System.out.println(" ");
            System.out.println("该构造方法可能抛出的异常类型为:");
            //获取构造方法的所有可能抛出的异常信息类型
            Class<?>[] exceptionTypes = constructor.getExceptionTypes();
            for (Class<?> exception : exceptionTypes) {
                System.out.println(" " + exception);
            }
            System.out.println("============================");

            Example_01 example2 = null;
            while (example2 == null) {
                //newInstance用于调用构造方法,参数根据具体需要调用的方法填写
                try {
                    if (i == 2) {
                        example2 = (Example_01) constructor.newInstance();
                    } else if (i == 1) {
                        example2 = (Example_01) constructor.newInstance("100", 5);
                    } else {
                        Object[] params = new Object[]{new String[]{"100", "200", "300"}};
                        example2 = (Example_01) constructor.newInstance(params);
                    }
                } catch (Exception e) {
                    System.out.println("创建对象时抛出异常,下面执行setAccessible()方法");
                    //该方法用于让private权限的方法可以访问
                    constructor.setAccessible(true);
                }
            }
            if (example2 != null) {
                example2.print();
            }
        }
    }

    public static void fieldTest() {
        Example_02 example = new Example_02();
        Class<?> exampleC = example.getClass();
        //获取所有成员变量
        Field[] fields = exampleC.getDeclaredFields();
        for (Field field : fields) {
            //获取成员变量名称
            System.out.println("名称为:" + field.getName());
            //获取成员变量类型
            Class<?> fieldType = field.getType();
            System.out.println("类型为:" + fieldType);
            boolean isTurn = true;
            while (isTurn) {
                try {
                    isTurn = false;
                    //获取成员变量值
                    System.out.println("修改前值为:" + field.get(example));
                    if (fieldType.equals(int.class)) {
                        System.out.println("利用方法setInt()修改成员变量的值");
                        field.setInt(example, 168);
                    } else if (fieldType.equals(float.class)) {
                        System.out.println("利用方法setFloat()修改成员变量的值");
                        field.setFloat(example, 99.9f);
                    } else if (fieldType.equals(boolean.class)) {
                        System.out.println("利用方法setBoolean()修改成员变量的值");
                        field.setBoolean(example, true);
                    } else {
                        System.out.println("利用方法set()修改成员变量的值");
                        field.set(example, "NCX");
                    }
                    System.out.println("修改后值为:" + field.get(example));
                } catch (Exception e) {
                    System.out.println("在设置成员变量值时抛出异常,下面执行setAccessible()方法");
                    field.setAccessible(true);
                    isTurn = true;
                }
            }
            System.out.println("============================");
        }
    }

    public static void methodTest() {
        Example_03 example = new Example_03();
        Class<?> exampleC = example.getClass();
        Method[] methods = exampleC.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("名称为:" + method.getName());
            System.out.println("是否带有可变数量的参数:" + method.isVarArgs());
            System.out.println("入口参数信息依次为:");
            //获得所有参数类型
            Class<?>[] parmas = method.getParameterTypes();
            for (Class<?> param : parmas) {
                System.out.println(" " + param);
            }
            //获得方法返回值类型
            System.out.println("返回值类型为:" + method.getReturnType());
            System.out.println("可能抛出的异常类型有:");
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            for (Class<?> exception : exceptionTypes) {
                System.out.println(" " + exception);
            }
            boolean isTurn = true;
            while (isTurn) {
                try {
                    isTurn = false;
                    if ("staticMethod".equals(method.getName())) {
                        method.invoke(example);
                    } else if ("publicMethod".equals(method.getName())) {
                        System.out.println("返回值为:" + method.invoke(example, 168));
                    } else if ("protectedMethod".equals(method.getName())) {
                        System.out.println("返回值为:" + method.invoke(example, "7", 5));
                    } else {
                        Object[] params = new Object[]{new String[]{"N", "C", "X"}};
                        System.out.println("返回值为:" + method.invoke(example, params));
                    }
                } catch (Exception e) {
                    System.out.println("在执行方法时抛出异常,下面执行setAccessible()方法");
                    method.setAccessible(true);
                    isTurn = true;
                }
            }
            System.out.println("================================");
        }
    }

    public static void main(String[] args) {
//        constructorTest();
//        fieldTest();
        methodTest();
    }
}
