package edu.fema.TrabalhoTopicosSpring.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.fema.TrabalhoTopicosSpring.model.enums.TipoPontoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ponto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ponto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonBackReference
	private Funcionario funcionario;

	@JsonIgnore
	@Column(name="tipo")
	private TipoPontoEnum tipoPontoEnum;
	
	@JsonIgnore
	private LocalDateTime datahora;

	public String getHorario() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatador.format(datahora);
	}
	
	public String getTipo() {
		return tipoPontoEnum.getTipo();
	}

}
