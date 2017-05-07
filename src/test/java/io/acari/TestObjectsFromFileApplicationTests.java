package io.acari;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;

public class TestObjectsFromFileApplicationTests {

	@Test
	public void contextLoads() {
		TestDataProvider.getProgrammers().values().parallelStream().forEach(System.out::println);
		System.out.println("-------------------");
		TestDataProvider.getNonSerialProgrammers().values().parallelStream().forEach(System.out::println);
	}

}
