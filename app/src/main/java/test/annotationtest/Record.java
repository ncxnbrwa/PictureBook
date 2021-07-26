package test.annotationtest;

/**
 * Created by ncx on 2021/3/19
 */
public class Record {
    @FieldMethodParameterAnnotation(describe = "编号", type = int.class)
    int id;
    @FieldMethodParameterAnnotation(describe = "姓名", type = String.class)
    String name;

    @ConstructorAnnotation
    public Record() {
    }
    @ConstructorAnnotation("立即初始化构造方法")
    public Record(@FieldMethodParameterAnnotation(describe = "编号", type = int.class) int id,
                  @FieldMethodParameterAnnotation(describe = "姓名", type = String.class) String name) {
        this.id = id;
        this.name = name;
    }

    @FieldMethodParameterAnnotation(describe = "获得编号", type = int.class)
    public int getId() {
        return id;
    }

    @FieldMethodParameterAnnotation(describe = "设置编号")
    public void setId(int id) {
        this.id = id;
    }

    @FieldMethodParameterAnnotation(describe = "获得姓名", type = String.class)
    public String getName() {
        return name;
    }

    @FieldMethodParameterAnnotation(describe = "设置姓名")
    public void setName(String name) {
        this.name = name;
    }
}
