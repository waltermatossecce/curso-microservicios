package com.moto.service.controladores;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moto.service.entidades.Moto;
import com.moto.service.servicio.MotoService;

@Controller
@RequestMapping("/moto")
public class MotoController {

	@Autowired
	private MotoService motoService;

	@GetMapping
	public ResponseEntity<List<Moto>> ListaMotos() {
		List<Moto> motos = motoService.getAll();
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Moto>obtenerMoto(@PathVariable ("id")int id){
		Moto moto=motoService.getMotoById(id);
		if (moto==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(moto);
	}

	@PostMapping
	public ResponseEntity<Moto>guardaMoto(@RequestBody Moto moto){
		Moto nuevaMoto=motoService.save(moto);
		return ResponseEntity.ok(nuevaMoto);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>>ListaMotosPorUsuariosId( @PathVariable ("usuarioId")int id){
	  List<Moto>moto=motoService.byUsuarioId(id);
	 if (moto.isEmpty()) {
		return ResponseEntity.noContent().build();
	 }
	 return ResponseEntity.ok(moto);
}}
