package test.classloadtest;


import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ncx on 2021/3/26
 */
public class DiskClassLoader extends ClassLoader {
    private String filePath;

    public DiskClassLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String newPath = filePath + name + ".class";
        byte[] classBytes = null;
        Path path = null;
        try {
            path = Paths.get(new URI(newPath));
            classBytes = Files.readAllBytes(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建class并返回
        return defineClass(name, classBytes, 0, classBytes.length);
    }
}
