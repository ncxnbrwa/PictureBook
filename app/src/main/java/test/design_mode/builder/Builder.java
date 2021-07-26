package test.design_mode.builder;

/**
 * Created by ncx on 2021/6/11
 * 规范产品的组建的类,包含了各种零件的组装功能
 */
public abstract class Builder {
    public abstract void buildCpu(String cpu);
    public abstract void buildMainBoard(String mainBoard);
    public abstract void buildRam(String ram);
    public abstract Computer create();
}
