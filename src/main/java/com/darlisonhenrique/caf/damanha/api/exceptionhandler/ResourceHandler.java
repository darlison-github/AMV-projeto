package com.darlisonhenrique.caf.damanha.api.exceptionhandler;

import java.lang.reflect.Field;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.darlisonhenrique.caf.damanha.domain.exceptions.ObjetoNãoEncontradoExceptions;
import com.darlisonhenrique.caf.damanha.domain.exceptions.ProjetomvExceptions;

@ControllerAdvice
public class ResourceHandler extends ResponseEntityExceptionHandler {
	
	private static final String MENSAGEMUSUARIO = "Error, caso o erro continue entre em contato com o desenvolvedor";
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(ProjetomvExceptions.class)
	public ResponseEntity<Object> handleProjetomv(ProjetomvExceptions ex, WebRequest request) {
		HttpStatus status= HttpStatus.BAD_REQUEST;
		Error error = Error.PROJETOMVEXCEPTIONS;
		String mensagem = ex.getMessage();
		MensagemErrorPersonalizada mensagemErrorPersonalizada = new MensagemErrorPersonalizada(error.getTipo(), error.getTitulo(), mensagem, status.value(), mensagem);

		return handleExceptionInternal(ex, mensagemErrorPersonalizada, new HttpHeaders(), status, request);
	}
			
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
			Error error = Error.ERROR_VALIDACAO;
			String mensagem = "Erro na validação";
			MensagemErrorPersonalizada mensagemErrorPersonalizada= new MensagemErrorPersonalizada(error.getTipo(), error.getTitulo(), mensagem, status.value(), 
					mensagem );
			
			for(ObjectError objectError: ex.getAllErrors()) {
				String field = objectError.getObjectName();
				
				if(objectError instanceof FieldError) {
					field = ((FieldError) objectError).getField();
				}
				
				String userMessage = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
				
				mensagemErrorPersonalizada.adicionarError(field, mensagem);
			}
			
			return handleExceptionInternal(ex, mensagemErrorPersonalizada, headers, status, request);
		}
	
	@ExceptionHandler(ObjetoNãoEncontradoExceptions.class)
	public ResponseEntity<Object> handlerObjetoNaoEncontradoExceptionEntity(ObjetoNãoEncontradoExceptions ex, WebRequest request) {
		HttpStatus status= HttpStatus.NOT_FOUND;
		Error error = Error.OBJETO_NÃO_ENCONTRADO;
		String mensagem = ex.getMessage();
		MensagemErrorPersonalizada mensagemErrorPersonalizada = new MensagemErrorPersonalizada(error.getTipo(), error.getTitulo(), mensagem, status.value(), mensagem);
	
		return handleExceptionInternal(ex, mensagemErrorPersonalizada, new HttpHeaders(), status, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if(body == null) {
			body = new MensagemErrorPersonalizada(null, null, status.getReasonPhrase() , status.value(), MENSAGEMUSUARIO);
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
}
