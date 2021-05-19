package random;

import org.gradle.api.Project;
import org.gradle.api.Plugin;
import org.gradle.api.artifacts.repositories.IvyArtifactRepository;
import org.gradle.api.publish.PublishingExtension;
import org.gradle.api.Action;
import org.gradle.api.artifacts.repositories.IvyArtifactRepository;
import org.gradle.api.artifacts.repositories.ArtifactRepository;

import java.net.URI;

public class MyPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getPlugins().withId("ivy-publish", plugin -> {
            PublishingExtension publishingExtension = project.getExtensions().getByType(PublishingExtension.class);
            publishingExtension.getRepositories().whenObjectAdded( repository -> {//new Action<? super IvyArtifactRepository>() {
                    //URI uri = action.getUrl();
                    // We have the repository object here, how to cast it?
                    if (repository instanceof IvyArtifactRepository) {
                        IvyArtifactRepository ivy = (IvyArtifactRepository)repository;
                        System.out.println("URL: " + ivy.getUrl());
                    }
                    System.out.println("Action: " + repository.toString());
                    System.out.println("Action: " + repository.toString());
                    System.out.println(repository instanceof IvyArtifactRepository);
                    System.out.println(repository instanceof ArtifactRepository);
                });
            });
/*
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
