package random;

import org.gradle.api.initialization.Settings;
import org.gradle.api.Plugin;
import org.gradle.api.artifacts.repositories.IvyArtifactRepository;
import org.gradle.api.publish.PublishingExtension;
import org.gradle.api.Action;
import org.gradle.api.artifacts.repositories.IvyArtifactRepository;
import org.gradle.api.artifacts.repositories.ArtifactRepository;
import org.gradle.api.artifacts.repositories.UrlArtifactRepository;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;

import java.net.URI;

public class MySettingsPlugin implements Plugin<Settings> {

    @Override
    public void apply(Settings settings) {
        System.out.println("Applying MySettingsPlugin");
        settings.getPluginManagement().getRepositories().whenObjectAdded( repository -> {
            System.out.println("Action: " + repository.toString());
            System.out.println(repository instanceof ArtifactRepository);
            System.out.println(repository instanceof UrlArtifactRepository);
            System.out.println(repository instanceof IvyArtifactRepository);
            System.out.println(repository instanceof MavenArtifactRepository);
            if (repository instanceof IvyArtifactRepository) {
                IvyArtifactRepository ivy = (IvyArtifactRepository)repository;
                System.out.println("Ivy URL: " + ivy.getUrl());
            }
            if (repository instanceof MavenArtifactRepository) {
                MavenArtifactRepository maven = (MavenArtifactRepository)repository;
                System.out.println("Maven URL: " + maven.getUrl());
            }
            if (repository instanceof UrlArtifactRepository) {
                UrlArtifactRepository url = (UrlArtifactRepository)repository;
                System.out.println("Url URL: " + url.getUrl());
            }
        });
    };
}
