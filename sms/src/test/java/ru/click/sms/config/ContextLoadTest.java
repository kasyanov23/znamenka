package ru.click.sms.config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.click.sms.SmsRuService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmsAutoConfiguration.class)
public class ContextLoadTest {

    @Autowired
	private SmsRuService service;

	@Test
	public void contextLoads() {
	    service.send("9850625571", "Привет");
	}

}
