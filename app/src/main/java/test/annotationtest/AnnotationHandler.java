package test.annotationtest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by ncx on 2021/3/19
 */
class AnnotationHandler {
    public static void main(String[] args) {
        Record record = new Record();
        Class recordC = record.getClass();
        //获取所有构造方法
        Constructor<?>[] constructors = recordC.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            //判断构造方法是否添加了ConstructorAnnotation注解
            if (constructor.isAnnotationPresent(ConstructorAnnotation.class)) {
                //获取构造方法的注解
                ConstructorAnnotation ca = (ConstructorAnnotation) constructor.getAnnotation(ConstructorAnnotation.class);
                System.out.println(ca.value());
            }
            //获取构造方法参数的注解,一个参数可能有多个注解,所以返回一个二维数组
            Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                int aLength = parameterAnnotations[i].length;
                if (aLength == 0) {
                    System.out.println("该构造方法没有添加参数注解");
                } else {
                    for (int j = 0; j < aLength; j++) {
                        FieldMethodParameterAnnotation paraAnnotation = (FieldMethodParameterAnnotation) parameterAnnotations[i][j];
                        System.out.println(paraAnnotation.describe() + "," + paraAnnotation.type());
                    }
                }
            }
            System.out.println("=======================");
        }

        //获取所有成员变量的注解
        Field[] fields = recordC.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FieldMethodParameterAnnotation.class)) {
                FieldMethodParameterAnnotation anno = field.getAnnotation(FieldMethodParameterAnnotation.class);
                System.out.println(anno.describe() + "," + anno.type());
            }
        }
        System.out.println("***************************");

        //获取所有方法的注解
        Method[] methods = recordC.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(FieldMethodParameterAnnotation.class)) {
                FieldMethodParameterAnnotation anno = method.getAnnotation(FieldMethodParameterAnnotation.class);
                System.out.println(anno.describe() + "," + anno.type());
            }
            //获取方法参数的注解
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                int aLength = parameterAnnotations[i].length;
                if (aLength == 0) {
                    System.out.println("该方法没有添加参数注解");
                } else {
                    for (int j = 0; j < aLength; j++) {
                        FieldMethodParameterAnnotation annotation = (FieldMethodParameterAnnotation) parameterAnnotations[i][j];
                        System.out.println(annotation.describe() + "," + annotation.type());
                    }
                }
            }
            System.out.println("=======================");
        }
    }
}
