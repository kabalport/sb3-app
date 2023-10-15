package com.kabalport.sb3app;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchitectureTest {
    JavaClasses javaClasses;

    @BeforeEach
    public void beforeEach(){
        javaClasses = new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .importPackages(
                        "com.kabalport.sb3app.score"
//                        "com.kabalport.sb3app"
//                        "com.kabalport.sb3app.product"
                );
    }

    @Test
    @DisplayName("controller 패키지안에 있는 클래스들은 Controller로 끝나야 합니다")
    public void controllerTest(){
        ArchRule rule = classes()
                .that().resideInAnyPackage("..controller")
                .should().haveSimpleNameEndingWith("Controller");

        ArchRule annotationRole = classes()
                .that().resideInAnyPackage("..controller")
                        .should().beAnnotatedWith(RestController.class)
                        .orShould().beAnnotatedWith(Controller.class);
        rule.check(javaClasses);
        annotationRole.check(javaClasses);


    }

    @Test
    @DisplayName("request 패키지 안에 있는 클래스는 Request로 끝내야 합니다.")
    public void requestTest(){
        ArchRule rule = classes()
                .that().resideInAnyPackage("..request..")
                .should().haveSimpleNameEndingWith("Request");

        rule.check(javaClasses);
    }

    @Test
    @DisplayName("response 패키지 안에 있는 클래스는 Response로 끝나야 합니다.")
    public void responseTest(){
        ArchRule rule = classes()
                .that().resideInAnyPackage("..response..")
                .should().haveSimpleNameEndingWith("Response");

        rule.check(javaClasses);
    }


    @Test
    @DisplayName("repository 패키지 안에 있는 클래스는 Repository로 끝나야 하고, 인터페이스여야 합니다.")
    public void repositoryTest(){
        ArchRule rule = classes()
                .that().resideInAnyPackage("..repository..")
                .should().haveSimpleNameEndingWith("Repository")
                .andShould().beInterfaces();

        rule.check(javaClasses);
    }

    @Test
    @DisplayName("service 패키지 안에 있는 클래스는 Service로 끝나야 하고 @Service 애너테이션이 붙어있어야 합니다.")
    public void serviceTest(){
        ArchRule rule = classes()
                .that().resideInAnyPackage("..service..")
                .should().haveSimpleNameEndingWith("Service")
                .andShould().beAnnotatedWith(Service.class);

        rule.check(javaClasses);
    }

    @Test
    @DisplayName("config 패키지 안에 있는 클래스는 Config로 끝나야하고 @Configuartion 애너테이션이 붙어있어야 합니다.")
    public void configTest(){
        ArchRule rule = classes()
                .that().resideInAnyPackage("..config..")
                .should().haveSimpleNameEndingWith("Config")
                .andShould().beAnnotatedWith(Configuration.class);

        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Controller는 Service와 Request/Response를 사용할 수 있습니다.")
    public void controllerDependencyTest(){
        ArchRule rule = classes()
                .that().resideInAnyPackage("..controller")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..request..","..response..","..service..");
        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Controller는 의존되지않음")
    public void controllerDependencyTest2(){
        ArchRule rule = classes()
                .that().resideInAnyPackage("..controller")
                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..controller");
        rule.check(javaClasses);

    }

    @Test
    @DisplayName("Controller는 모델을 사용할 수 없음")
    public void controllerDependencyTest3(){
        ArchRule rule = noClasses()
                .that().resideInAnyPackage("..controller")
                .should().dependOnClassesThat().resideInAnyPackage("..model..");

        rule.check(javaClasses);
    }


    @Test
    @DisplayName("Service는 Controller를 의존하면 안됨")
    public void serviceDependencyTest(){
        ArchRule rule = noClasses()
                .that().resideInAnyPackage("..service..")
                .should().dependOnClassesThat().resideInAnyPackage("..controller");
        rule.check(javaClasses);
    }

    @Test
    @DisplayName("Model은 오직 Service와 Repository에 의해 의존됨")
    public void modelDependencyTest(){
        ArchRule rule = classes()
                .that().resideInAnyPackage("..model..")
                .should().onlyHaveDependentClassesThat().resideInAnyPackage("..repository..","..service..","..model..");

        rule.check(javaClasses);
    }


    @Test
    @DisplayName("Model은 아무것도 의존하지 않는다.")
    public void modelDependencyTest2(){
        ArchRule rule = classes()
                .that().resideInAnyPackage("..model..")
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage("..model..","java..","jakarta..");

        rule.check(javaClasses);
    }





}