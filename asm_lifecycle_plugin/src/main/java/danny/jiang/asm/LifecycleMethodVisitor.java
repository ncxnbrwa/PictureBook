package danny.jiang.asm;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by ncx on 2021/3/25
 */
public class LifecycleMethodVisitor extends MethodVisitor {
    private String className, methodName;

    public LifecycleMethodVisitor(MethodVisitor methodVisitor, String className, String methodName) {
        super(Opcodes.ASM5, methodVisitor);
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        System.out.println("LifecycleMethodVisitor visitCode");
        //这一段就相当于写了一个Log打印日志的方法
        mv.visitLdcInsn("TAG");//设置第一个参数
        mv.visitLdcInsn(className + "=======" + methodName);//设置第二个参数
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i",
                "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(Opcodes.POP);//这里估计是插入一段代码的意思
    }
}
