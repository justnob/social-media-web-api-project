package com.amarnath.learning.learningrestfulwebservicesproject.versionurlcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@GetMapping("/v1/person")
	private Personv1 GetPersonV1() {
		return new Personv1("Amarnath sah");
	}

	@GetMapping("/v2/person")
	private Personv2 GetPersonV2() {
		return new Personv2(new Name("Amarnath", "sah"));
	}

	@GetMapping(path = "/person", params = "version=1")
	private Personv1 GetPersonParamVersion1() {
		return new Personv1("Amarnath sah");
	}

	@GetMapping(path = "/person", params = "version=2")
	private Personv2 GetPersonParamVersion2() {
		return new Personv2(new Name("Amarnath", "sah"));
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	private Personv1 GetPersonRequestHederVersion1() {
		return new Personv1("Amarnath sah");
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	private Personv2 GetPersonRequestHederVersion2() {
		return new Personv2(new Name("Amarnath", "sah"));
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	private Personv1 GetPersonAcceptHederVersion1() {
		return new Personv1("Amarnath sah");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	private Personv2 GetPersonAcceptHederVersion2() {
		return new Personv2(new Name("Amarnath", "sah"));
	}

}
