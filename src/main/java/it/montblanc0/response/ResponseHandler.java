package it.montblanc0.response;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());
		map.put("auto", responseObj);

		return new ResponseEntity<Object>(map, status);
	}

	public static ResponseEntity<Object> generateResponseWithURI(String message, HttpStatus status, Object responseObj, Link link) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());
		map.put("auto", responseObj);
		map.put("link", link);

		return new ResponseEntity<Object>(map, status);
	}
}
