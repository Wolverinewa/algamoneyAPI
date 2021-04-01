package com.example.algamoneyapi.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoneyapi.event.RecursoCriadoEvent;
import com.example.algamoneyapi.execeptionhandler.AlgamoneyExceptionHandler.Erro;
import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.LancamentoRepository;
import com.example.algamoneyapi.repository.filter.LancamentoFilter;
import com.example.algamoneyapi.service.LancamentoService;
import com.example.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
		return lancamentoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscaPeloCodigo(@PathVariable Long codigo) {
		Optional<Lancamento> lancamento = this.lancamentoRepository.findById(codigo);
		return lancamento.isPresent() ?
				ResponseEntity.ok(lancamento.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalva = LancamentoService.salvar(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
		String mensagemUsuario = 
				messageSource.getMessage("pessoa.inixistente-ou-inativa", null, 
											LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
}
