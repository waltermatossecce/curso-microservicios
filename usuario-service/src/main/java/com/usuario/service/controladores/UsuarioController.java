package com.usuario.service.controladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicios.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@GetMapping
	public ResponseEntity<List<Usuario>>ListaUsuarios(){
		List<Usuario>usuario=usuarioService.getAll();
		if(usuario.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuario);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario>ObtenerUsurios(@PathVariable ("id")int id){
	 Usuario usuario=usuarioService.getUsuarioById(id);
	 if(usuario==null) {
		 return ResponseEntity.notFound().build();
	 }
	 return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario>guardaUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario =usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);

	}
	///Rest template
	
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>>ListarCarros(@PathVariable ("usuarioId") int id){
		Usuario usuario=usuarioService.getUsuarioById(id);
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		List<Carro>carros=usuarioService.getCarros(id);
		return ResponseEntity.ok(carros);
	}
	
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>>ListarMotos(@PathVariable ("usuarioId") int id){
		Usuario usuario=usuarioService.getUsuarioById(id);
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		List<Moto>moto=usuarioService.getMotos(id);
		return ResponseEntity.ok(moto);
	}
	//Feign CLIENTS
	//Carro-service
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro>guardarCarro(@PathVariable("usuarioId")int usuarioId,@RequestBody Carro carro){
     Carro nuevoCarro=usuarioService.saveCarro(usuarioId, carro);
     return ResponseEntity.ok(nuevoCarro);
	}
	//Feign CLIENTS
	//Moto-service
	 @PostMapping("/moto/{usuarioId}")
	 public ResponseEntity<Moto>guardarMotos(@PathVariable ("usuarioId")int usuarioId,@RequestBody Moto moto){
		 Moto nuevaMoto=usuarioService.saveMoto(usuarioId, moto);
		 return ResponseEntity.ok(nuevaMoto);
		 
	 }
	 @GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>>todosLosVehiculos(@PathVariable ("usuarioId") int usuarioId){
		 Map<String, Object>resultado=usuarioService.getUsuariosAndVehiculos(usuarioId);
		 return ResponseEntity.ok(resultado);
		 
	 }
}
