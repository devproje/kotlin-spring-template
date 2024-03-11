package net.projecttl.template

import net.projecttl.template.model.Sample
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class TemplateApplication {
	@GetMapping("/")
	fun index(): Sample {
		return Sample()
	}

	@GetMapping("/hello")
	fun hello(): String {
		return "World!"
	}

	@GetMapping("/test/{msg}")
	fun message(@PathVariable msg: String): String {
		return "Your message is: $msg"
	}
}

fun main(args: Array<String>) {
	runApplication<TemplateApplication>(*args)
}
