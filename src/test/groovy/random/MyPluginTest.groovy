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

    def "test1"() {
        setup:
        buildFile = testProjectDir.newFile("build.gradle")
        buildFile << """
            plugins {
                id 'ivy-publish'
                id 'random.MyPlugin'
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
}
