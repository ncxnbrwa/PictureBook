package danny.jiang.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class LifeCyclePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        System.out.println("======LifeCyclePlugin gradle plugin======")
//        project.android.registerTransform(new ModifyTransform(project))
        def android = project.extensions.getByType(AppExtension)
        println '-----注册AutoTrackTransform------'
        LifeCycleTransform transform = new LifeCycleTransform()
        android.registerTransform(transform)
    }
}
