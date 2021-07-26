package test.design_mode.builder;

/**
 * Created by ncx on 2021/6/11
 * 这个建造者模式是进阶之光里的例子,但我感觉写的不太完善
 */
public class BuilderTest {
    public static void main(String[] args) {
        Builder builder  = new MoonComputerBuilder();
        Package p = new Package(builder);
        //组装计算机
        Computer computer = p.createComputer("i7-6700", "华擎玩家至尊", "三星 DDR4");
        System.out.println(computer);
    }
}
