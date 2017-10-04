package kafkatool.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import kafkatool.service.KafkaProducerService;

@RestController
public class KafkaController {
	
	@Autowired
	KafkaProducerService producerService;
	
	@GetMapping("/hello")
	@HystrixCommand
	public String helloWorld() {
		return "Hello, World";
	}
	
	@GetMapping("/send")
	@HystrixCommand
	public StreamingResponseBody testMessage(int size, int count) throws InterruptedException, ExecutionException {
		
		return new StreamingResponseBody() {

			@Override
			public void writeTo(OutputStream out) throws IOException {
				out.write("Sending \n".getBytes());
				out.flush();
				
				String content = System.currentTimeMillis() + ":" + StringUtils.repeat("*", size);
				int i;
				for(i=0;i<count;i++) {
					long startTime = System.nanoTime();
					long offset = producerService.send(content);
					long endTime = System.nanoTime();

					long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
					out.write(("offset " + offset + "[" + duration + "] \n").getBytes());
					out.flush();
				}			
			}
        };
		
	}
}
