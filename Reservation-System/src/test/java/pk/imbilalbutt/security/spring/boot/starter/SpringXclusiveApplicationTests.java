package pk.imbilalbutt.security.spring.boot.starter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pk.imbilalbutt.Train.TrainTest;
import pk.imbilalbutt.User.UserTest;

@SpringBootTest(classes= {TrainTest.class, UserTest.class})
class SpringXclusiveApplicationTests {

	@Test
	void contextLoads() {
	}

}
