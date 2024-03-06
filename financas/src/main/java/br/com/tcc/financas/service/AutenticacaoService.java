package br.com.tcc.financas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.tcc.financas.model.Pessoa;
import br.com.tcc.financas.repository.PessoaRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PessoaRepository pessoarepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Pessoa pessoa = this.pessoarepository.findByNome(username);
		if (pessoa == null) {
            throw new UsernameNotFoundException("Dados inválidos.");
		}
		return pessoa;
	}


	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}


	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
