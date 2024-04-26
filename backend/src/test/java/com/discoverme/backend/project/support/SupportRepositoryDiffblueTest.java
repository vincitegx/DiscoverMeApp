package com.discoverme.backend.project.support;

import com.discoverme.backend.project.Project;
import com.discoverme.backend.project.calender.Calender;
import com.discoverme.backend.social.Socials;
import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SupportRepository.class})
@ExtendWith(SpringExtension.class)
class SupportRepositoryDiffblueTest {
    @Autowired
    private SupportRepository supportRepository;

    /**
     * Method under test:
     * {@link SupportRepository#findByProjectAndUser(Project, Users)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindByProjectAndUser() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: interface com.discoverme.backend.project.support.SupportRepository
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Failed to load ApplicationContext for [MergedContextConfiguration@73740478 testClass = com.discoverme.backend.project.support.DiffblueFakeClass4052, locations = [], classes = [com.discoverme.backend.project.support.SupportRepository], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@14a9e66a, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@4a367425, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@59197837], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:143)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'supportRepository': Failed to instantiate [com.discoverme.backend.project.support.SupportRepository]: Specified class is an interface
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1314)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1199)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:560)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326)
        //       at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
        //       at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:973)
        //       at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:942)
        //       at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:608)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:221)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:110)
        //       at org.springframework.test.context.support.AbstractDelegatingSmartContextLoader.loadContext(AbstractDelegatingSmartContextLoader.java:212)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:187)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:119)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.discoverme.backend.project.support.SupportRepository]: Specified class is an interface
        //       at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:76)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1308)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1199)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:560)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326)
        //       at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
        //       at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:973)
        //       at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:942)
        //       at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:608)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:221)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:110)
        //       at org.springframework.test.context.support.AbstractDelegatingSmartContextLoader.loadContext(AbstractDelegatingSmartContextLoader.java:212)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:187)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:119)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserName("User Name");

        Support support = new Support();
        support.setProject(project);
        support.setUser(user2);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(2L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social2 = new Socials();
        social2.setId(2L);
        social2.setName("com.discoverme.backend.social.Socials");

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("john.smith@example.org");
        user3.setEnabled(false);
        user3.setId(2L);
        user3.setNonLocked(false);
        user3.setPassword("Password");
        user3.setRole("com.discoverme.backend.user.Users");
        user3.setUserName("com.discoverme.backend.user.Users");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Content Uri");
        project2.setId(2L);
        project2.setSocial(social2);
        project2.setSongTitle("Mr");
        project2.setSongUri("com.discoverme.backend.project.Project");
        project2.setSupportCount(1);
        project2.setUrl("Url");
        project2.setUser(user3);

        Users user4 = new Users();
        user4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user4.setEmail("john.smith@example.org");
        user4.setEnabled(false);
        user4.setId(2L);
        user4.setNonLocked(false);
        user4.setPassword("Password");
        user4.setRole("com.discoverme.backend.user.Users");
        user4.setUserName("com.discoverme.backend.user.Users");

        Support support2 = new Support();
        support2.setProject(project2);
        support2.setUser(user4);
        supportRepository.save(support);
        supportRepository.save(support2);

        Calender calender3 = new Calender();
        calender3.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender3.setId(1L);
        calender3.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social3 = new Socials();
        social3.setId(1L);
        social3.setName("Name");

        Users user5 = new Users();
        user5.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user5.setEmail("jane.doe@example.org");
        user5.setEnabled(true);
        user5.setId(1L);
        user5.setNonLocked(true);
        user5.setPassword("iloveyou");
        user5.setRole("Role");
        user5.setUserName("User Name");

        Project project3 = new Project();
        project3.setCalender(calender3);
        project3.setContentUri("Not all who wander are lost");
        project3.setSocial(social3);
        project3.setSongTitle("Dr");
        project3.setSongUri("Song Uri");
        project3.setSupportCount(3);
        project3.setUrl("https://example.org/example");
        project3.setUser(user5);

        Users user6 = new Users();
        user6.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user6.setEmail("jane.doe@example.org");
        user6.setEnabled(true);
        user6.setNonLocked(true);
        user6.setPassword("iloveyou");
        user6.setRole("Role");
        user6.setUserName("User Name");

        // Act
        supportRepository.findByProjectAndUser(project3, user6);
    }

    /**
     * Method under test: {@link SupportRepository#findByProject(Project)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindByProject() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: interface com.discoverme.backend.project.support.SupportRepository
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Failed to load ApplicationContext for [MergedContextConfiguration@4b3fb3d2 testClass = com.discoverme.backend.project.support.DiffblueFakeClass3832, locations = [], classes = [com.discoverme.backend.project.support.SupportRepository], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@14a9e66a, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@4a367425, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@59197837], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:143)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'supportRepository': Failed to instantiate [com.discoverme.backend.project.support.SupportRepository]: Specified class is an interface
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1314)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1199)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:560)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326)
        //       at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
        //       at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:973)
        //       at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:942)
        //       at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:608)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:221)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:110)
        //       at org.springframework.test.context.support.AbstractDelegatingSmartContextLoader.loadContext(AbstractDelegatingSmartContextLoader.java:212)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:187)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:119)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.discoverme.backend.project.support.SupportRepository]: Specified class is an interface
        //       at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:76)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1308)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1199)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:560)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326)
        //       at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
        //       at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:973)
        //       at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:942)
        //       at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:608)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:221)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:110)
        //       at org.springframework.test.context.support.AbstractDelegatingSmartContextLoader.loadContext(AbstractDelegatingSmartContextLoader.java:212)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:187)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:119)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserName("User Name");

        Support support = new Support();
        support.setProject(project);
        support.setUser(user2);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(2L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social2 = new Socials();
        social2.setId(2L);
        social2.setName("com.discoverme.backend.social.Socials");

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("john.smith@example.org");
        user3.setEnabled(false);
        user3.setId(2L);
        user3.setNonLocked(false);
        user3.setPassword("Password");
        user3.setRole("com.discoverme.backend.user.Users");
        user3.setUserName("com.discoverme.backend.user.Users");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Content Uri");
        project2.setId(2L);
        project2.setSocial(social2);
        project2.setSongTitle("Mr");
        project2.setSongUri("com.discoverme.backend.project.Project");
        project2.setSupportCount(1);
        project2.setUrl("Url");
        project2.setUser(user3);

        Users user4 = new Users();
        user4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user4.setEmail("john.smith@example.org");
        user4.setEnabled(false);
        user4.setId(2L);
        user4.setNonLocked(false);
        user4.setPassword("Password");
        user4.setRole("com.discoverme.backend.user.Users");
        user4.setUserName("com.discoverme.backend.user.Users");

        Support support2 = new Support();
        support2.setProject(project2);
        support2.setUser(user4);
        supportRepository.save(support);
        supportRepository.save(support2);

        Calender calender3 = new Calender();
        calender3.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender3.setId(1L);
        calender3.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social3 = new Socials();
        social3.setId(1L);
        social3.setName("Name");

        Users user5 = new Users();
        user5.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user5.setEmail("jane.doe@example.org");
        user5.setEnabled(true);
        user5.setId(1L);
        user5.setNonLocked(true);
        user5.setPassword("iloveyou");
        user5.setRole("Role");
        user5.setUserName("User Name");

        Project project3 = new Project();
        project3.setCalender(calender3);
        project3.setContentUri("Not all who wander are lost");
        project3.setSocial(social3);
        project3.setSongTitle("Dr");
        project3.setSongUri("Song Uri");
        project3.setSupportCount(3);
        project3.setUrl("https://example.org/example");
        project3.setUser(user5);

        // Act
        supportRepository.findByProject(project3);
    }

    /**
     * Method under test: {@link SupportRepository#findByUser(Users)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindByUser() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: interface com.discoverme.backend.project.support.SupportRepository
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Failed to load ApplicationContext for [MergedContextConfiguration@69fad3c6 testClass = com.discoverme.backend.project.support.DiffblueFakeClass4272, locations = [], classes = [com.discoverme.backend.project.support.SupportRepository], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@14a9e66a, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@4a367425, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@59197837], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:143)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'supportRepository': Failed to instantiate [com.discoverme.backend.project.support.SupportRepository]: Specified class is an interface
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1314)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1199)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:560)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326)
        //       at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
        //       at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:973)
        //       at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:942)
        //       at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:608)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:221)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:110)
        //       at org.springframework.test.context.support.AbstractDelegatingSmartContextLoader.loadContext(AbstractDelegatingSmartContextLoader.java:212)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:187)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:119)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.discoverme.backend.project.support.SupportRepository]: Specified class is an interface
        //       at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:76)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1308)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1199)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:560)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326)
        //       at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
        //       at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:973)
        //       at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:942)
        //       at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:608)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:221)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:110)
        //       at org.springframework.test.context.support.AbstractDelegatingSmartContextLoader.loadContext(AbstractDelegatingSmartContextLoader.java:212)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:187)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:119)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserName("User Name");

        Support support = new Support();
        support.setProject(project);
        support.setUser(user2);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(2L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social2 = new Socials();
        social2.setId(2L);
        social2.setName("com.discoverme.backend.social.Socials");

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("john.smith@example.org");
        user3.setEnabled(false);
        user3.setId(2L);
        user3.setNonLocked(false);
        user3.setPassword("Password");
        user3.setRole("com.discoverme.backend.user.Users");
        user3.setUserName("com.discoverme.backend.user.Users");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Content Uri");
        project2.setId(2L);
        project2.setSocial(social2);
        project2.setSongTitle("Mr");
        project2.setSongUri("com.discoverme.backend.project.Project");
        project2.setSupportCount(1);
        project2.setUrl("Url");
        project2.setUser(user3);

        Users user4 = new Users();
        user4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user4.setEmail("john.smith@example.org");
        user4.setEnabled(false);
        user4.setId(2L);
        user4.setNonLocked(false);
        user4.setPassword("Password");
        user4.setRole("com.discoverme.backend.user.Users");
        user4.setUserName("com.discoverme.backend.user.Users");

        Support support2 = new Support();
        support2.setProject(project2);
        support2.setUser(user4);
        supportRepository.save(support);
        supportRepository.save(support2);

        Users user5 = new Users();
        user5.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user5.setEmail("jane.doe@example.org");
        user5.setEnabled(true);
        user5.setNonLocked(true);
        user5.setPassword("iloveyou");
        user5.setRole("Role");
        user5.setUserName("User Name");

        // Act
        supportRepository.findByUser(user5);
    }

    /**
     * Method under test:
     * {@link SupportRepository#findTopByProjectAndUserOrderByIdDesc(Project, Users)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindTopByProjectAndUserOrderByIdDesc() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: interface com.discoverme.backend.project.support.SupportRepository
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Failed to load ApplicationContext for [MergedContextConfiguration@1bd5b344 testClass = com.discoverme.backend.project.support.DiffblueFakeClass4492, locations = [], classes = [com.discoverme.backend.project.support.SupportRepository], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@14a9e66a, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@4a367425, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@59197837], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:143)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'supportRepository': Failed to instantiate [com.discoverme.backend.project.support.SupportRepository]: Specified class is an interface
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1314)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1199)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:560)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326)
        //       at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
        //       at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:973)
        //       at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:942)
        //       at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:608)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:221)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:110)
        //       at org.springframework.test.context.support.AbstractDelegatingSmartContextLoader.loadContext(AbstractDelegatingSmartContextLoader.java:212)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:187)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:119)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.discoverme.backend.project.support.SupportRepository]: Specified class is an interface
        //       at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:76)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1308)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1199)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:560)
        //       at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:520)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326)
        //       at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324)
        //       at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200)
        //       at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:973)
        //       at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:942)
        //       at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:608)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:221)
        //       at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:110)
        //       at org.springframework.test.context.support.AbstractDelegatingSmartContextLoader.loadContext(AbstractDelegatingSmartContextLoader.java:212)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:187)
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:119)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        Calender calender = new Calender();
        calender.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender.setId(1L);
        calender.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social = new Socials();
        social.setId(1L);
        social.setName("Name");

        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        Project project = new Project();
        project.setCalender(calender);
        project.setContentUri("Not all who wander are lost");
        project.setId(1L);
        project.setSocial(social);
        project.setSongTitle("Dr");
        project.setSongUri("Song Uri");
        project.setSupportCount(3);
        project.setUrl("https://example.org/example");
        project.setUser(user);

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("jane.doe@example.org");
        user2.setEnabled(true);
        user2.setId(1L);
        user2.setNonLocked(true);
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUserName("User Name");

        Support support = new Support();
        support.setProject(project);
        support.setUser(user2);

        Calender calender2 = new Calender();
        calender2.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender2.setId(2L);
        calender2.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social2 = new Socials();
        social2.setId(2L);
        social2.setName("com.discoverme.backend.social.Socials");

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("john.smith@example.org");
        user3.setEnabled(false);
        user3.setId(2L);
        user3.setNonLocked(false);
        user3.setPassword("Password");
        user3.setRole("com.discoverme.backend.user.Users");
        user3.setUserName("com.discoverme.backend.user.Users");

        Project project2 = new Project();
        project2.setCalender(calender2);
        project2.setContentUri("Content Uri");
        project2.setId(2L);
        project2.setSocial(social2);
        project2.setSongTitle("Mr");
        project2.setSongUri("com.discoverme.backend.project.Project");
        project2.setSupportCount(1);
        project2.setUrl("Url");
        project2.setUser(user3);

        Users user4 = new Users();
        user4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user4.setEmail("john.smith@example.org");
        user4.setEnabled(false);
        user4.setId(2L);
        user4.setNonLocked(false);
        user4.setPassword("Password");
        user4.setRole("com.discoverme.backend.user.Users");
        user4.setUserName("com.discoverme.backend.user.Users");

        Support support2 = new Support();
        support2.setProject(project2);
        support2.setUser(user4);
        supportRepository.save(support);
        supportRepository.save(support2);

        Calender calender3 = new Calender();
        calender3.setEndDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        calender3.setId(1L);
        calender3.setStartDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        Socials social3 = new Socials();
        social3.setId(1L);
        social3.setName("Name");

        Users user5 = new Users();
        user5.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user5.setEmail("jane.doe@example.org");
        user5.setEnabled(true);
        user5.setId(1L);
        user5.setNonLocked(true);
        user5.setPassword("iloveyou");
        user5.setRole("Role");
        user5.setUserName("User Name");

        Project project3 = new Project();
        project3.setCalender(calender3);
        project3.setContentUri("Not all who wander are lost");
        project3.setSocial(social3);
        project3.setSongTitle("Dr");
        project3.setSongUri("Song Uri");
        project3.setSupportCount(3);
        project3.setUrl("https://example.org/example");
        project3.setUser(user5);

        Users currentUser = new Users();
        currentUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        currentUser.setEmail("jane.doe@example.org");
        currentUser.setEnabled(true);
        currentUser.setNonLocked(true);
        currentUser.setPassword("iloveyou");
        currentUser.setRole("Role");
        currentUser.setUserName("User Name");

        // Act
        supportRepository.findTopByProjectAndUserOrderByIdDesc(project3, currentUser);
    }
}
