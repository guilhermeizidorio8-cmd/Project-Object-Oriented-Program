package tipoEmpresa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmpresaMEI {
	
	
	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;
	private String municipio;
	private String estado;
	private String ocupacaoPrincipal;
	private String ocupacaoSecundaria;
	private BigDecimal faturamentoAnual;
	private BigDecimal faturamentoMensal;
	private BigDecimal valorMensalDAS;
	private LocalDate datadeAbertura;
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private boolean indicadordeExistencia;
    private String existeEmpregador;
    
    /*Parâmetros legais
	    Limite de receita bruta anual: R$ 81.000,00 (média de R$ 6.750,00/mês) - Lei Complementar 123/2006, art. 18-A, § 1º.
	    Valor mensal do DAS-MEI: 5% do salário mínimo (R$ 1.621,00 × 5% = R$ 81,05 de INSS) + R$ 1,00 de ICMS (comércio/indústria) 
	    e/ou R$ 5,00 de ISS (serviços) - Lei Complementar 123/2006, art. 18-A, § 3º.
	    Funcionário: o MEI pode ter no máximo 1 empregado, com salário mínimo ou piso da categoria - Lei Complementar 123/2006, art. 18-C.
    Fonte: planalto.gov.br / Receita Federal (Portal do Simples Nacional). Data da consulta: 20/09/2026*/
    private static final BigDecimal LIMITEFATURAMENTO =  new BigDecimal("86000"); // tem que ser texto, se não não tem precisão
    private static final BigDecimal SALARIOMINIMO =  new BigDecimal("1621.00");
    private static final BigDecimal INSS = SALARIOMINIMO.multiply(new BigDecimal("0.05"));
    private static final BigDecimal ICMS = new BigDecimal("1.00");
    private static final BigDecimal ISS = new BigDecimal("5.00");
    private static final BigDecimal ICMS_E_ISS = ICMS.add(ISS);

    
    //CONSTRUTOR SEM PARAMETROS
    public EmpresaMEI() {
		
	}
    
  //CONSTRUTOR COMPLETO
	public EmpresaMEI(String cnpj, String razaoSocial, String nomeFantasia, String estado, String municipio, String ocupacaoPrincipal,
			String ocupacaoSecundaria, BigDecimal faturamentoAnual, BigDecimal faturamentoMensal, BigDecimal valorMensalDAS,
			LocalDate datadeAbertura, boolean indicadordeExistencia) {
		this.setCnpj(cnpj);
		this.setRazaoSocial(razaoSocial);
		this.setNomeFantasia(nomeFantasia);
		this.setEstado(estado);
		this.setMunicipio(municipio);
		this.setOcupacaoPrincipal(ocupacaoPrincipal);
		this.setOcupacaoSecundaria(ocupacaoSecundaria);
		this.setFaturamentoAnual(faturamentoAnual);
		this.setFaturamentoMensal(faturamentoMensal);
		this.setValorMensalDAS(valorMensalDAS);
		this.setDatadeAbertura(datadeAbertura);
		this.setIndicadordeExistencia(indicadordeExistencia);
	}
	
	//GETTERs E SETTERs
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
	    if (cnpj == null || cnpj.isBlank()) {
	        throw new IllegalArgumentException("Erro! cnpj não pode ser vazio");
	    }
	 // verifica se tem caracteres e retira eles, deixando apenas números.
	    String apenasNumeros = cnpj.replaceAll("[^0-9]", ""); 
	    if (apenasNumeros.length() != 14) {
	        throw new IllegalArgumentException("Erro! cnpj deve conter 14 números");
	    }
	    this.cnpj = apenasNumeros;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		if(razaoSocial == null || razaoSocial.isBlank()) {
			throw new IllegalArgumentException("Erro! Razao Social não pode ser vazia");
		}
		else {
			this.razaoSocial = razaoSocial;
		}
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getOcupacaoPrincipal() {
		return ocupacaoPrincipal;
	}
	public void setOcupacaoPrincipal(String ocupacaoPrincipal) {
		if (ocupacaoPrincipal != null && !ocupacaoPrincipal.isBlank()
	            && ((ocupacaoPrincipal.equalsIgnoreCase("Comercio")
	            || ocupacaoPrincipal.equalsIgnoreCase("Industria")
	            || ocupacaoPrincipal.equalsIgnoreCase("Servico")))) {
			this.ocupacaoPrincipal = ocupacaoPrincipal;
		} else {
			throw new IllegalArgumentException("Erro! Ocupação Principal Invalida, escolha entre Comercio, Industria ou Servico");
		}
	}
	public String getOcupacaoSecundaria() {
		return ocupacaoSecundaria;
	}
	public void setOcupacaoSecundaria(String ocupacaoSecundaria) {
		if (ocupacaoSecundaria != null && !ocupacaoSecundaria.isBlank()
	            && !ocupacaoSecundaria.equalsIgnoreCase("Comercio")
	            && !ocupacaoSecundaria.equalsIgnoreCase("Industria")
	            && !ocupacaoSecundaria.equalsIgnoreCase("Servico")) {
	        throw new IllegalArgumentException("Erro! Ocupação Secundária inválida, escolha entre Comercio, Industria, Servico ou deixe em branco");
	    }
	    this.ocupacaoSecundaria = ocupacaoSecundaria;
	}
	public BigDecimal getFaturamentoAnual() {
		return faturamentoAnual;
	}
	public void setFaturamentoAnual(BigDecimal faturamentoAnual) {
		//Verificacao de negativo
		if(faturamentoAnual == null || faturamentoAnual.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! Faturamento Anual não pode ser Negativo!");
		} else {
			this.faturamentoAnual = faturamentoAnual;
		}
	}
	public BigDecimal getFaturamentoMensal() {
		return faturamentoMensal;
	}
	public void setFaturamentoMensal(BigDecimal faturamentoMensal) {
		//Verificacao de negativo
		if(faturamentoMensal == null || faturamentoMensal.compareTo(BigDecimal.ZERO) < 0) { 
			throw new IllegalArgumentException("Erro! Faturamento Mensal não pode ser Negativo!");
		} else {
			this.faturamentoMensal = faturamentoMensal;
		}
	}
	public BigDecimal getValorMensalDAS() {
		return valorMensalDAS;
	}
	public void setValorMensalDAS(BigDecimal valorMensalDAS) {
		//Verificacao de negativo
		if(valorMensalDAS == null || valorMensalDAS.compareTo(BigDecimal.ZERO) < 0) { 
			throw new IllegalArgumentException("Erro! Calculo do DAS não pode ser Negativo!");
		} else {
			this.valorMensalDAS = valorMensalDAS;
		}
	}
	
	public LocalDate getDatadeAbertura() {
		return datadeAbertura;
	}

	public void setDatadeAbertura(LocalDate datadeAbertura) {
		this.datadeAbertura = datadeAbertura;
	}

	public boolean isIndicadordeExistencia() {
		return indicadordeExistencia;
	}
	public void setIndicadordeExistencia(boolean indicadordeExistencia) {
		this.indicadordeExistencia = indicadordeExistencia;
		if(indicadordeExistencia == true) {
			existeEmpregador = "Sim";
		} else {
			existeEmpregador = "Não";
		}
	}
	
	// Calculo do DAS do MEI
	public BigDecimal calculoDAS() {
		if (ocupacaoPrincipal == null) {
	        throw new IllegalStateException("Ocupação principal não informada");
	    }
		if(ocupacaoPrincipal.equalsIgnoreCase ("comercio") || ocupacaoPrincipal.equalsIgnoreCase("Industria")) {
			this.setValorMensalDAS(INSS.add(ICMS));
		} else if (ocupacaoPrincipal.equalsIgnoreCase("Servico") 
				&& (ocupacaoSecundaria != null || ocupacaoSecundaria.isBlank())) {
			this.setValorMensalDAS(INSS.add(ISS));
		} else {
			this.setValorMensalDAS(INSS.add(ICMS_E_ISS));
		}
		
		return valorMensalDAS;
	}
	
	public boolean verificarLimite() {
		if (faturamentoAnual == null) {
	        throw new IllegalStateException("Faturamento anual não informado");
	    }
		// aqui eu returno se a operação é verdadeira ou falsa por ser boolean
	    return faturamentoAnual.compareTo(LIMITEFATURAMENTO) <= 0;
	}
	
	// Metodo para exibir
	public void exibir() {
		//Verificação simples antes de exibir os valores de Data e funcionarios, para não quebrar a exibicao
				String data;
			    if (datadeAbertura == null) {
			        data = "não informada";
			    } else {
			        data = datadeAbertura.format(formato);
			    }
			    String funcionario;
			    if (existeEmpregador == null) {
			        funcionario = "Não";
			    } else {
			        funcionario = existeEmpregador;
			    }
		System.out.println("================= Dados da empresa =================\n"
				+ "- CNPJ: " + cnpj 
				+ "\n- Razão Social: " + razaoSocial
				+ "\n- Nome Fantasia: " + nomeFantasia
				+ "\n- Estado: " + estado
				+ "\n- Atividade Principal: " + ocupacaoPrincipal
				+ "\n- Atividade Secundaria: " + ocupacaoSecundaria
				+ "\n- Faturamento Anual: R$" + faturamentoAnual 
				+ "\n- Faturamento Mensal: R$" + faturamentoMensal
				+ "\n- Valor Mensal do DAS: R$" + valorMensalDAS
				+ "\n- Data de Abertura: " + data
				+ "\n- Possui funcionário? " + funcionario
				+ "\n==================================================="); 
	}
   
    
}
