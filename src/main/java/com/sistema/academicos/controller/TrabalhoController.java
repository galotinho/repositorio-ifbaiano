package com.sistema.academicos.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.academicos.model.DBFile;
import com.sistema.academicos.model.Trabalho;
import com.sistema.academicos.service.DBFileService;
import com.sistema.academicos.service.TrabalhoService;

@Controller
@RequestMapping("trabalho")
public class TrabalhoController {

	@Autowired
	private TrabalhoService service;
	
	@Autowired
    private DBFileService DBFileStorageService;
	
	@GetMapping({"", "/"})
	public String raiz(Trabalho trabalho) {
		return "trabalhos/cadastrar";
	}
	
	@PostMapping("/salvar")
	public String salvar(@RequestParam("arquivo") MultipartFile file, Trabalho trabalho, RedirectAttributes attr) {
		
		//verifica se novo arquivo não for anexado
		if(file.getContentType().equals("application/octet-stream")){
			trabalho.setFile(service.buscarPorId(trabalho.getId()).getFile());
		}else {
			DBFile dbFile = DBFileStorageService.storeFile(file);
			trabalho.setFile(dbFile);
		}
								
		service.salvar(trabalho);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");		
		return "redirect:/trabalho";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getTrabalhos(HttpServletRequest request){
		return ResponseEntity.ok(service.buscarTrabalhos(request));
	}
	
	@GetMapping("/home/datatables/server")
	public ResponseEntity<?> historicoTrabalhosHome(HttpServletRequest request) {
		
		return ResponseEntity.ok(service.buscarTrabalhos(request));
		
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("trabalho", service.buscarPorId(id));
		return "trabalhos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
		service.remover(id);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
		return "redirect:/trabalho";
	}
	
	@GetMapping("/curso/listar")
	public ResponseEntity<?> getCursos() {
		
		return ResponseEntity.ok(service.buscarCursos());
	}
	
	@GetMapping("/detalhes/{id}")
	public String detalhar(@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("trabalho", service.buscarPorId(id));
		return "trabalhos/detalhes";
	}
	
	@GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        
        DBFile dbFile = DBFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
	
	/*
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
  
    	DBFile dbFile = DBFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
*/
}
