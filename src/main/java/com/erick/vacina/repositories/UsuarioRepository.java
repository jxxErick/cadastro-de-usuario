package com.erick.vacina.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erick.vacina.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Transactional
	Usuario findByEmail(String email);
	@Transactional
	Usuario findByCpf(String cpf);
}
