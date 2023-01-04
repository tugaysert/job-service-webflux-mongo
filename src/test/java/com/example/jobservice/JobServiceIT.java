package com.example.jobservice;

import com.example.jobservice.dto.JobDto;
import com.example.jobservice.generic.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

@SpringBootTest
@AutoConfigureWebTestClient
class JobServiceIT extends BaseTest {


	@Autowired
	private WebTestClient client;

	@Test
	public void allJobsTest() {
		this.client.get()
				.uri("/job/all")
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody()
				.consumeWith(e -> System.out.println(new String(e.getResponseBody())))
				.jsonPath("$").isNotEmpty();
	}

	@Test
	public void searchBySkillTest() {
		this.client.get()
				.uri("/job/search?skills=java")
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody()
				.consumeWith(e -> System.out.println(new String(e.getResponseBody())))
				.jsonPath("$.size()").isEqualTo(3);
	}

	@Test
	public void postJobTest() {

		JobDto dto = JobDto.create(null, "k8s engr", "google", Set.of("k8s"), 200000, true);
		this.client.post()
				.uri("/job")
				.bodyValue(dto)
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody()
				.consumeWith(e -> System.out.println(new String(e.getResponseBody())))
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.description").isEqualTo("k8s engr");
	}

}
