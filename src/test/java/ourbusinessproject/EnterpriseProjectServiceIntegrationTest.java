package ourbusinessproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EnterpriseProjectServiceIntegrationTest {

    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    private Project project;
    private Enterprise enterprise;

    @Test
    public void testSaveValidProject() {

        // given a a valid project
        project = new Project();
        project.setTitle("A project");
        project.setDescription("Project description");

        // when saving the project
        enterpriseProjectService.save(project);

        // expect the project is saved with a generated id
        assertThat(project.getId(), is(notNullValue()));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSaveNonValidProject() {

        // given a non valid project
        project = new Project();
        project.setTitle("");
        project.setDescription("Project description");

        // when saving the project
        enterpriseProjectService.save(project);

        // then an exception is thrown

    }

    @Test
    public void testSaveValidEnterprise() {

        // given a a valid Enterprise
        enterprise = new Enterprise();
        enterprise.setName("MyComp");
        enterprise.setDescription("My comp description");
        enterprise.setContactEmail("comp@com.com");
        enterprise.setContactName("comp contact name");

        // when saving the enterprise
        enterpriseProjectService.save(enterprise);

        // expect the enterprise is saved with a generated id
        assertThat(enterprise.getId(), is(notNullValue()));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSaveNonValidEnterprise() {

        // given a non valid enterprise
        enterprise = new Enterprise();
        enterprise.setName("MyComp");
        enterprise.setDescription("My comp description");
        enterprise.setContactEmail("comp@com.com");

        // when saving the enterprise
        enterpriseProjectService.save(enterprise);

        // then an exception is thrown

    }

    @Test
    public void testFindExistingProjectById() {

        // given a saved project
        project = new Project();
        project.setTitle("The project");
        project.setDescription("Project description");
        enterpriseProjectService.save(project);


        // when an existing  project is searched by id
        Project fetchedProject = enterpriseProjectService.findProjectById(project.getId());

        // then the fetched project is correctly instanced
        assertThat(fetchedProject.getTitle(), is(project.getTitle()));

    }

    @Test
    public void testFindNonExistingProjectById() {

        // when a non existing  project is searched by id
        Project fetchedProject = enterpriseProjectService.findProjectById(1L);

        // then the fetched project is null
        assertThat(fetchedProject, is(nullValue()));

    }

    @Test
    public void testFindExistingEnterprise() {
        // given a saved Enterprise
        enterprise = new Enterprise();
        enterprise.setName("MyComp");
        enterprise.setDescription("My comp description");
        enterprise.setContactEmail("comp@com.com");
        enterprise.setContactName("comp contact name");
        enterpriseProjectService.save(enterprise);

        // when an existing  enterprise is searched by id
        Enterprise fetchedEnterprise = enterpriseProjectService.findEnterpriseById(enterprise.getId());

        // the fetched enterprise is correctly instanced
        assertThat(fetchedEnterprise.getName(), is(enterprise.getName()));

    }

    @Test
    public void testFindNonExistingEnterpriseById() {

        // when a non existing  project is searched by id
        Enterprise fetchedEnterprise = enterpriseProjectService.findEnterpriseById(1L);

        // then the fetched project is null
        assertThat(fetchedEnterprise, is(nullValue()));

    }

}