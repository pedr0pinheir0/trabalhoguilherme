package edu.fema.TrabalhoTopicosSpring.model.dto;

public class CredencialDTOBuilder {

	private CredencialDTO credencialDTO;

	public static CredencialDTOBuilder builder() {
		return new CredencialDTOBuilder();
	}

	public CredencialDTOBuilder hash(String hash) {
		credencialDTO.setHash(hash);
		return this;
	}

	public CredencialDTOBuilder nomeDeUsuario(String nome) {
		credencialDTO.setNomeDeUsuario(nome);
		return this;
	}

	public CredencialDTOBuilder senha(String senha) {
		credencialDTO.setSenha(senha);
		return this;
	}

	public CredencialDTO build() {
		return this.credencialDTO;
	}

}
