package danny.jiang.plugin

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager
import danny.jiang.asm.LifecycleClassVisitor
import groovy.io.FileType
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.apache.commons.io.FileUtils

class LifeCycleTransform extends Transform {
    @Override
    String getName() {
        //设置我们自定义的 Transform 对应的 Task 名称。Gradle 在编译的时候，会将这个名称显示在控制台上。
        //比如：Task :app:transformClassesWithXXXForDebug。
        //生成文件夹路径是app\build\intermediates
        return "LifecycleTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        //只提供本地或远程依赖项
        return TransformManager.PROJECT_ONLY
    }

    @Override
    boolean isIncremental() {
        //不增量更新
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        //拿到所有class文件
        Collection<TransformInput> transformInputs = transformInvocation.inputs
        TransformOutputProvider outputProvider = transformInvocation.outputProvider

        transformInputs.each { TransformInput transformInput ->
            //directoryInputs代表了以源码方式参与项目编译的所有目录结果及其目录下的源码文件
            //包括R.class,MainActivity.class,BuildConfig.class等文件
            transformInput.directoryInputs.each { DirectoryInput directoryInput ->
                File dir = directoryInput.file
                if (dir) {
                    //设置过滤文件,去除文件夹类型
                    dir.traverse(type: FileType.FILES, nameFilter: ~/.*\.class/) { File file ->
                        //遍历输出所有文件名称
                        System.out.println("find class:" + file.name)
                        //对class文件进行读取和解析
                        ClassReader classReader = new ClassReader(file.bytes)
                        //对class文件的写入
                        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                        //访问class文件相应的内容,解析到某一个结构就会通知到ClassVisitor的的相应方法
                        ClassVisitor classVisitor = new LifecycleClassVisitor(classWriter)
                        //依次调用ClassVisitor的相应方法
                        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES)
                        //toByteArray方法会将最终修改的字节码以byte的形式返回
                        byte[] bytes = classWriter.toByteArray()
                        //通过文件流写入覆盖原来的内容,实现class文件的改写
                        FileOutputStream outputStream = new FileOutputStream(file.path)
                        outputStream.write(bytes)
                        outputStream.close()
                    }
                }
                //处理完输入文件后把输出传给下一个文件
                def dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes,
                        directoryInput.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(directoryInput.file, dest)
            }
        }
    }
}