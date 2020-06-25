package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elastic.repository.BookElasticRepository;
import com.example.model.Book;
import com.example.model.BookElastic;
import com.example.repository.BookRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/books")
public class BookController {
	@Autowired
	private BookRepository repo3;
	@Autowired
	private BookElasticRepository repo4;
	@Autowired
	private ElasticsearchTemplate template;
	
	Runtime rt = Runtime.getRuntime();
	Boolean synchronised = true;
	
	@PostMapping("/saveBook")
	public String saveBook(@Valid @RequestBody Book book) throws IOException {
		repo3.save(book);
		if(synchronised==true) {
		synchronised = false;
		Update();
		System.out.println(synchronised);
		}
		
		return book.name+" A été bien ajouter";
	}	
	@PostMapping("/editBook")
	public String editBook(@Valid @RequestBody Book book) throws IOException {
		repo3.save(book);
		if(synchronised==true) {
			synchronised = false;
			Update();
			System.out.println(synchronised);
			}
		return book.name+"A été modifier avec succés";	
	}
	@GetMapping("/findAll")
	public <T> List<T> getBooks(){
		System.out.println(synchronised);
		if (synchronised == true) {
		Iterable <T> iterable =(Iterable<T>) repo4.findAll(); //elasticsearch
		List <T> result =  new ArrayList<T>();
		for (T bk : iterable) {
			result.add(bk);
		}
		return result;
	}
		else {
			System.out.println("Should be here");
			return (List<T>) repo3.findAll(); //secondary
		}
	}
	@GetMapping("del/{Id}")
	public void delBookById(@PathVariable int Id) throws IOException {
		repo3.deleteById(Id);
		if(synchronised==true) {
			synchronised = false;
			Update();
			System.out.println(synchronised);
			}
		
	}
	@GetMapping("/search/{name}")
	public List<BookElastic> findbyName(@PathVariable String name) {
		String search = ".*"+name+".*";
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(QueryBuilders.regexpQuery("name", search)).build();
		List<BookElastic> books = template.queryForList(searchQuery, BookElastic.class);
		return books;
	}
	

	public void Update() throws IOException {
		Process proc1 = rt.exec("cmd /c start curl -XDELETE http://localhost:9200/book");
		Process proc2 = rt.exec("cmd /c start /wait C:/Users/Public/logconf/logstash-7.8.0/bin/logstashh.bat -f ../../../Public/logconf/logstashb.conf");
		

	
	}

	
	@Scheduled(fixedDelayString="PT5M")
	void switchState() throws InterruptedException,IOException{
		if (synchronised ==false) {
		Update();
		}
		synchronised = true;
	}
@Configuration
@EnableScheduling
@ConditionalOnProperty(name="scheduling.enable",matchIfMissing=true)
class SchedulingConnfiguration {
	
}
}
