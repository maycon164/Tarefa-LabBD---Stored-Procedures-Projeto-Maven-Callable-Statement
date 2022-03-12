package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente {

	private String cpf;
	private String nome;
	private String email;
	private double limiteCredito;
	private Date dataNascimento;

	public Cliente() {

	};

	public Cliente(String cpf, String nome, String email, double limiteCredito, Date dataNascimento) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.limiteCredito = limiteCredito;
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) throws ParseException {
		
		SimpleDateFormat formatter = null;
		if(dataNascimento.contains("-")) {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		}else {
			formatter = new SimpleDateFormat("dd/MM/yyyy");
		}
		
		
		this.dataNascimento = formatter.parse(dataNascimento);
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", limiteCredito=" + limiteCredito
				+ ", dataNascimento=" + dataNascimento + "]";
	}

}
