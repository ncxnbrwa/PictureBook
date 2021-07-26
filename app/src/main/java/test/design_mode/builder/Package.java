package test.design_mode.builder;

/**
 * Created by ncx on 2021/6/11
 */
public class Package {
    Builder builder = null;

    public Package(Builder builder) {
        this.builder = builder;
    }

    public Computer createComputer(String cpu,String mainBoard,String ram) {
        this.builder.buildCpu(cpu);
        this.builder.buildMainBoard(mainBoard);
        this.builder.buildRam(ram);
        return this.builder.create();
    }
}
