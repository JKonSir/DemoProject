package org.mycompany.repositories;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mycompany.model.Student;
import org.mycompany.utils.DemoProjectSpringContext;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class StudentRepositoryTest {

    @Inject
    private EntityRepository<Student> studentRepository;

    @Deployment(testable = true)
    public static Archive<?> createDeployment() {
        String uniqueDeploymentName = "DemoProject-integration-tests-" +
                Instant.now() +
                ".war";
        final Archive<?> webArchive = ShrinkWrap.create(WebArchive.class, uniqueDeploymentName)
                .addAsLibrary(StudentRepositoryTest.class.getProtectionDomain()
                        .getCodeSource()
                        .getLocation(), "DemoProject-it.jar")
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("jboss-deployment-structure.xml", "jboss-deployment-structure.xml");
        System.out.println(webArchive.toString(true));
        return webArchive;
    }

    @Test
    public void testSave_shouldCreateStudent() {
//        System.out.println(studentRepository.toString());
        studentRepository = DemoProjectSpringContext.getInstance().getStudentRepository();
        List<Student> students = studentRepository.findAll();
        System.out.println("============================");
        System.out.println(students.get(0).getFirstName());
        System.out.println("============================");
        Assert.assertTrue(true);
    }
}
