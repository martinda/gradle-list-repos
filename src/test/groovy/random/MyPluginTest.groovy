package random

import spock.lang.Specification
import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import static org.gradle.testkit.runner.TaskOutcome.*

public class MyPluginTest extends Specification {

    @Rule
    TemporaryFolder testProjectDir = new TemporaryFolder()

    File buildFile

    def "Test project plugin"() {
        setup:
        testProjectDir.create()
        buildFile = testProjectDir.newFile("build.gradle")
        buildFile << """
            plugins {
                id 'ivy-publish'
                id 'random.MyPlugin'
            }
            repositories {
                ivy {
                    url 'resolution-repository'
                }
            }
            publishing {
                repositories {
                    ivy {
                        url = "http://a.b.c/"
                    }
                    maven {
                        url = "http://d.e.f/"
                    }
                }
            }"""
       when:
       def result = GradleRunner.create()
           .withProjectDir(testProjectDir.root)
           .withArguments(["--stacktrace", "tasks"])
           .withPluginClasspath()
           .build()

       then:
       println(result.output)
       result.task(":tasks").outcome == SUCCESS
    }

    def "Test settings plugin"() {
        setup:
        testProjectDir.create()
        File settingsFile = testProjectDir.newFile("settings.gradle")
        settingsFile << """
            pluginManagement {
                repositories {
                    maven {
                       url 'https://ciena.com/my-maven'
                    }
                    ivy {
                       url 'https://ciena.com/my-ivy'
                    }
                    gradlePluginPortal()
                }
                plugins {
                    id 'random.MySettingsPlugin'
                }
            }
            println("Inside settings")
            """
       when:
       def result = GradleRunner.create()
           .withProjectDir(testProjectDir.root)
           .withArguments(["--stacktrace", "tasks"])
           .withPluginClasspath()
           .build()

       then:
       println(result.output)
       result.output.contains("Applying MySettingsPlugin")
       result.task(":tasks").outcome == SUCCESS
    }
}
