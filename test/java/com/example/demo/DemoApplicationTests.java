package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//Эта аннотация используется для указания исполнителя тестов JUnit.
// В этом случае SpringRunner является средством запуска тестов Spring Boot.
// Это позволяет интегрировать среду тестирования Spring с JUnit.
@SpringBootTest
//This annotation is used to indicate that the test is a Spring Boot test and should
// load the entire application context for testing.
// It starts up the Spring application context and performs full integration testing.
@ActiveProfiles("test")
// эта аннотация используется для указания того, какие профили Spring должны быть активны во время
// выполнения теста. В этом случае он устанавливает «тестовый» профиль в качестве активного профиля.
// Как упоминалось ранее, в application.yml мы определили различные конфигурации для «тестового» профиля,
// включая использование для тестирования базы данных H2 в памяти.
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
