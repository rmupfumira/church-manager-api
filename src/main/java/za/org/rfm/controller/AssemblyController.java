package za.org.rfm.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import za.org.rfm.entity.Assembly;
import za.org.rfm.service.AssemblyService;

@Controller
@RequestMapping("assembly")
public class AssemblyController {
	@Autowired
	private AssemblyService assemblyService;
	@GetMapping("{id}")
	public ResponseEntity<Assembly> getAssemblyById(@PathVariable("id") Integer id) {
		Assembly assembly = assemblyService.getAssemblyById(id);
		return new ResponseEntity<Assembly>(assembly, HttpStatus.OK);
	}
	@GetMapping("")
	public ResponseEntity<List<Assembly>> getAllAssemblys() {
		List<Assembly> list = assemblyService.getAllAssemblies();
		return new ResponseEntity<List<Assembly>>(list, HttpStatus.OK);
	}
	@PostMapping("")
	public ResponseEntity<Void> addAssembly(@RequestBody Assembly assembly, UriComponentsBuilder builder) {
        boolean flag = assemblyService.addAssembly(assembly);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("{id}").buildAndExpand(assembly.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("")
	public ResponseEntity<Assembly> updateAssembly(@RequestBody Assembly assembly) {
		assemblyService.updateAssembly(assembly);
		return new ResponseEntity<Assembly>(assembly, HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteAssembly(@PathVariable("id") Integer id) {
		assemblyService.deleteAssembly(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 