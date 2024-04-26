package com.discoverme.backend.user.login.refresh;

import com.discoverme.backend.user.Users;

import java.time.LocalDate;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RefreshTokenRepository.class})
@ExtendWith(SpringExtension.class)
class RefreshTokenRepositoryDiffblueTest {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    /**
     * Method under test: {@link RefreshTokenRepository#findByToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindByToken() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: interface com.discoverme.backend.user.login.refresh.RefreshTokenRepository
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Failed to load ApplicationContext for [MergedContextConfiguration@147d6755 testClass = com.discoverme.backend.user.login.refresh.DiffblueFakeClass6633, locations = [], classes = [com.discoverme.backend.user.login.refresh.RefreshTokenRepository], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@2bcc5cdb, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7b8462b7, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@64baaba5], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:143)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'refreshTokenRepository': Failed to instantiate [com.discoverme.backend.user.login.refresh.RefreshTokenRepository]: Specified class is an interface
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
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.discoverme.backend.user.login.refresh.RefreshTokenRepository]: Specified class is an interface
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
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("john.smith@example.org");
        user2.setEnabled(false);
        user2.setId(2L);
        user2.setNonLocked(false);
        user2.setPassword("Password");
        user2.setRole("com.discoverme.backend.user.Users");
        user2.setUserName("com.discoverme.backend.user.Users");

        RefreshToken refreshToken2 = new RefreshToken();
        refreshToken2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken2.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken2.setToken("Token");
        refreshToken2.setUser(user2);
        refreshTokenRepository.save(refreshToken);
        refreshTokenRepository.save(refreshToken2);

        // Act
        refreshTokenRepository.findByToken("ABC123");
    }

    /**
     * Method under test:
     * {@link RefreshTokenRepository#findByTokenAndUser(String, Users)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindByTokenAndUser() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: interface com.discoverme.backend.user.login.refresh.RefreshTokenRepository
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Failed to load ApplicationContext for [MergedContextConfiguration@285215bc testClass = com.discoverme.backend.user.login.refresh.DiffblueFakeClass6887, locations = [], classes = [com.discoverme.backend.user.login.refresh.RefreshTokenRepository], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@2bcc5cdb, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7b8462b7, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@64baaba5], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:143)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'refreshTokenRepository': Failed to instantiate [com.discoverme.backend.user.login.refresh.RefreshTokenRepository]: Specified class is an interface
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
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.discoverme.backend.user.login.refresh.RefreshTokenRepository]: Specified class is an interface
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
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("john.smith@example.org");
        user2.setEnabled(false);
        user2.setId(2L);
        user2.setNonLocked(false);
        user2.setPassword("Password");
        user2.setRole("com.discoverme.backend.user.Users");
        user2.setUserName("com.discoverme.backend.user.Users");

        RefreshToken refreshToken2 = new RefreshToken();
        refreshToken2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken2.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken2.setToken("Token");
        refreshToken2.setUser(user2);
        refreshTokenRepository.save(refreshToken);
        refreshTokenRepository.save(refreshToken2);

        Users user3 = new Users();
        user3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user3.setEmail("jane.doe@example.org");
        user3.setEnabled(true);
        user3.setNonLocked(true);
        user3.setPassword("iloveyou");
        user3.setRole("Role");
        user3.setUserName("User Name");

        // Act
        refreshTokenRepository.findByTokenAndUser("ABC123", user3);
    }

    /**
     * Method under test: {@link RefreshTokenRepository#deleteByToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteByToken() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   com.diffblue.fuzztest.shared.proxy.BeanInstantiationException: Could not instantiate bean: interface com.discoverme.backend.user.login.refresh.RefreshTokenRepository
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   java.lang.IllegalStateException: Failed to load ApplicationContext for [MergedContextConfiguration@13459591 testClass = com.discoverme.backend.user.login.refresh.DiffblueFakeClass6379, locations = [], classes = [com.discoverme.backend.user.login.refresh.RefreshTokenRepository], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@2bcc5cdb, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7b8462b7, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@64baaba5], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:143)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'refreshTokenRepository': Failed to instantiate [com.discoverme.backend.user.login.refresh.RefreshTokenRepository]: Specified class is an interface
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
        //   org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.discoverme.backend.user.login.refresh.RefreshTokenRepository]: Specified class is an interface
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
        Users user = new Users();
        user.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user.setEmail("jane.doe@example.org");
        user.setEnabled(true);
        user.setId(1L);
        user.setNonLocked(true);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUserName("User Name");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken.setToken("ABC123");
        refreshToken.setUser(user);

        Users user2 = new Users();
        user2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC));
        user2.setEmail("john.smith@example.org");
        user2.setEnabled(false);
        user2.setId(2L);
        user2.setNonLocked(false);
        user2.setPassword("Password");
        user2.setRole("com.discoverme.backend.user.Users");
        user2.setUserName("com.discoverme.backend.user.Users");

        RefreshToken refreshToken2 = new RefreshToken();
        refreshToken2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken2.setExpiresAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        refreshToken2.setToken("Token");
        refreshToken2.setUser(user2);
        refreshTokenRepository.save(refreshToken);
        refreshTokenRepository.save(refreshToken2);

        // Act
        refreshTokenRepository.deleteByToken("ABC123");
    }
}
