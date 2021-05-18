package random;

import org.gradle.api.Project;
import org.gradle.api.Plugin;
import org.gradle.api.artifacts.repositories.IvyArtifactRepository;
import org.gradle.api.publish.PublishingExtension;

import java.net.URI;

public class MyPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {

        project.getPlugins().withId("ivy-publish", {
            PublishingExtension publishingExtension = project.getExtensions().getByType(PublishingExtension.class)
            System.out.println(project.extensions.publishing)
            System.out.println(publishingExtension)
            RepositoryHandler rh1 = publishingExtension.getRepositories()
            rh1.whenObjectAdded( { repository ->
                println(repository)
                URI uri = repository.getUrl()
                println(uri)
            })
        })
/*
        project.getPlugins().withId("ivy-publish", {
            def exts = project.extensions
            def publishingExt = exts.publishing
            RepositoryHandler rh = publishingExt.repositories
            rh.whenObjectAdded( {
                println(it.class)
                println(it.url.class)
            })
        })
*/
    }
}
