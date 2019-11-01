package edu.fema.TrabalhoTopicosSpring.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.fema.TrabalhoTopicosSpring.util.Utils;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FuncionarioConsultaDTO {

	private Long codigo;
	private PessoalDTO dadosPessoais;
	private CredencialDTO credenciais;
	private String cargo;
	
	@JsonIgnore
	private Long saldoDeHoras;
	
	public String getTempoTrabalhado(){
		return Utils.formatarTempo(saldoDeHoras);
	}
}
