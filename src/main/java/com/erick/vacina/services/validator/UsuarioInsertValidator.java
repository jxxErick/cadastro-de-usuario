package com.erick.vacina.services.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.erick.vacina.domain.Usuario;
import com.erick.vacina.repositories.UsuarioRepository;
import com.erick.vacina.resources.exception.FieldMessage;
import com.erick.vacina.services.validator.utils.BR;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, Usuario> {
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(Usuario obj, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		if(!BR.isValidCPF(obj.getCpf())) {
			list.add(new FieldMessage("cpf", "CPF inválido"));
		}
		
		Usuario aux = repo.findByEmail(obj.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		Usuario aux2 = repo.findByCpf(obj.getCpf());
		if (aux2 != null) {
			list.add(new FieldMessage("cpf", "Cpf já existente"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}


}