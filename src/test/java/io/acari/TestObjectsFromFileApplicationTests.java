package io.acari;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestObjectsFromFileApplicationTests {

	@Test
	public void contextLoads() {
		TestDataProvider.getProgrammers().parallelStream().forEach(System.out::println);
	}

}
