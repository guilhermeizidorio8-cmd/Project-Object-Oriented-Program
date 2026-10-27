package tipoEmpresa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmpresaSimplesNacional {
	/*Parâmetros legais
		Limite de receita bruta anual: R$ 4.800.000,00 - Lei Complementar 123/2006, art. 3º, II.
		Alíquota efetiva: (RBT12 × alíquota nominal − parcela a deduzir) ÷ RBT12, conforme o Anexo (I a V) da atividade - Lei Complementar 123/2006, art. 18 e Anexos I a V.
	Fonte: planalto.gov.br / Receita Federal (Portal do Simples Nacional). Data da consulta: 20/09/2026*/
	private static final BigDecimal[] LIMITES = {
	    new BigDecimal("180000"), new BigDecimal("360000"), new BigDecimal("720000"),
	    new BigDecimal("1800000"), new BigDecimal("3600000"), new BigDecimal("4800000")
	};
	// Alíquota nominal e parcela a deduzir de cada faixa, por anexo em varios vetores de string
	//Informaçoes pegadas da mesma fonte.
	private static final String[] ALIQ_ANEXO1 = {"0.04", "0.073", "0.095", "0.107", "0.143", "0.19"};
	private static final String[] DEDUC_ANEXO1 = {"0", "5940", "13860", "22500", "87300", "378000"};

	private static final String[] ALIQ_ANEXO2 = {"0.045", "0.078", "0.10", "0.112", "0.147", "0.30"};
	private static final String[] DEDUC_ANEXO2 = {"0", "5940", "13860", "22500", "85500", "720000"};

	private static final String[] ALIQ_ANEXO3 = {"0.06", "0.112", "0.135", "0.16", "0.21", "0.33"};
	private static final String[] DEDUC_ANEXO3 = {"0", "9360", "17640", "35640", "125640", "648000"};

	private static final String[] ALIQ_ANEXO4 = {"0.045", "0.09", "0.102", "0.14", "0.22", "0.33"};
	private static final String[] DEDUC_ANEXO4 = {"0", "8100", "12420", "39780", "183780", "828000"};

	private static final String[] ALIQ_ANEXO5 = {"0.155", "0.18", "0.195", "0.205", "0.23", "0.305"};
	private static final String[] DEDUC_ANEXO5 = {"0", "4500", "9900", "17100", "62100", "540000"};
	
	private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String municipio;
    private String estado;
    private String atividadePrincipal;
    private String atividadeSecundaria;
    private BigDecimal faturamentoAnual;
    private BigDecimal faturamentoMensal;
    private BigDecimal aliquotaEfetiva;
    private LocalDate dataDeInicio;
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int numeroFuncionarios;
    
    
	public EmpresaSimplesNacional() {


	}

	public EmpresaSimplesNacional(String cnpj, String razaoSocial, String nomeFantasia, String estado, String municipio,
			String atividadePrincipal, String atividadeSecundaria, BigDecimal faturamentoAnual, BigDecimal faturamentoMensal,
			BigDecimal aliquotaEfetiva, LocalDate dataDeInicio, int numeroFuncionarios) {
		//Chamando os Setters para validar os erros no cadastro
		this.setCnpj(cnpj);
		this.setRazaoSocial(razaoSocial);
		this.setNomeFantasia(nomeFantasia);
		this.setEstado(estado);
		this.setMunicipio(municipio);
		this.setAtividadePrincipal(atividadePrincipal);
		this.setAtividadeSecundaria(atividadeSecundaria);
		this.setFaturamentoAnual(faturamentoAnual);
		this.setFaturamentoMensal(faturamentoMensal);
		this.setAliquotaEfetiva(aliquotaEfetiva);
		this.setDataDeInicio(dataDeInicio);
		this.setNumeroFuncionarios(numeroFuncionarios);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
	    if (cnpj == null || cnpj.isBlank()) {
	        throw new IllegalArgumentException("Erro! CNPJ não pode ser vazio");
	    }
	 // verifica se tem caracteres e retira eles, deixando apenas números
	    String apenasNumeros = cnpj.replaceAll("[^0-9]", ""); 
	    if (apenasNumeros.length() != 14) {
	        throw new IllegalArgumentException("Erro! CNPJ deve conter 14 números");
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

	public String getAtividadePrincipal() {
		return atividadePrincipal;
	}

	public void setAtividadePrincipal(String atividadePrincipal) {
		if (atividadePrincipal != null && !atividadePrincipal.isBlank()
	            && (atividadePrincipal.equalsIgnoreCase("Comercio")
	            || atividadePrincipal.equalsIgnoreCase("Industria")
	            || atividadePrincipal.equalsIgnoreCase("Servico"))) {
			this.atividadePrincipal = atividadePrincipal;
		} else {
			throw new IllegalArgumentException("Erro! Atividade Principal Invalida, escolha entre Comercio, Industria ou Servico");
		}
	}

	public String getAtividadeSecundaria() {
		return atividadeSecundaria;
	}

	public void setAtividadeSecundaria(String atividadeSecundaria) {
		this.atividadeSecundaria = atividadeSecundaria;
	}

	public BigDecimal getFaturamentoAnual() {
		return faturamentoAnual;
	}

	public void setFaturamentoAnual(BigDecimal faturamentoAnual) {
		if(faturamentoAnual == null || faturamentoAnual.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! Faturamento Anual não pode ser Negativo nem Nulo!");
		} else {
			this.faturamentoAnual = faturamentoAnual;
		}
	}

	public BigDecimal getFaturamentoMensal() {
		return faturamentoMensal;
	}

	public void setFaturamentoMensal(BigDecimal faturamentoMensal) {
		if(faturamentoMensal == null ||faturamentoMensal.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! Faturamento Mensal não pode ser Negativo nem Nulo!");
		} else {
			this.faturamentoMensal = faturamentoMensal;
		}
	}

	public BigDecimal getAliquotaEfetiva() {
		return aliquotaEfetiva;
	}

	public void setAliquotaEfetiva(BigDecimal aliquotaEfetiva) {
		if(aliquotaEfetiva == null ||aliquotaEfetiva.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! Alíquota Efetiva não pode ser Negativo nem Nulo!");
		} else {
			this.aliquotaEfetiva = aliquotaEfetiva;
		}
	}

	public LocalDate getDataDeInicio() {
		return dataDeInicio;
	}

	public void setDataDeInicio(LocalDate dataDeInicio) {
		this.dataDeInicio = dataDeInicio;
	}

	public int getNumeroFuncionarios() {
		return numeroFuncionarios;
	}

	public void setNumeroFuncionarios(int numeroFuncionarios) {
		if(numeroFuncionarios < 0) {
			throw new IllegalArgumentException("Erro! Número de funcionarios não pode ser Negativo!");
		} else {
			this.numeroFuncionarios = numeroFuncionarios;
		}
	}
    
	public BigDecimal calcularAliquotaEfetiva() {
	    if (faturamentoAnual == null || faturamentoAnual.compareTo(BigDecimal.ZERO) <= 0) {
	        throw new IllegalArgumentException("Faturamento anual deve ser maior que zero");
	    }
	    if (atividadePrincipal == null) {
	        throw new IllegalStateException("Atividade principal não informada");
	    }
	    //Cria variaveis auxiliares para armazenar
	    String[] aliquotas;
	    String[] deducoes;

	    //Verifica cada tipo de atividade e armazena nas variaveis correspondentes para realizar o calculo
	    if (atividadePrincipal.equalsIgnoreCase("Comercio")) {
	        aliquotas = ALIQ_ANEXO1;
	        deducoes = DEDUC_ANEXO1;
	    } else if (atividadePrincipal.equalsIgnoreCase("Industria")) {
	        aliquotas = ALIQ_ANEXO2;
	        deducoes = DEDUC_ANEXO2;
	    } else if (atividadePrincipal.equalsIgnoreCase("Servico")) {
	        if ("Anexo 3".equalsIgnoreCase(atividadeSecundaria)) {
	            aliquotas = ALIQ_ANEXO3;
	            deducoes = DEDUC_ANEXO3;
	        } else if ("Anexo 4".equalsIgnoreCase(atividadeSecundaria)) {
	            aliquotas = ALIQ_ANEXO4;
	            deducoes = DEDUC_ANEXO4;
	        } else if ("Anexo 5".equalsIgnoreCase(atividadeSecundaria)) {
	            aliquotas = ALIQ_ANEXO5;
	            deducoes = DEDUC_ANEXO5;
	        } else {
	            throw new IllegalArgumentException("Serviço exige Anexo 3, 4 ou 5");
	        }
	    } else {
	        throw new IllegalArgumentException("Atividade principal inválida");
	    }

	    // descobre a faixa de limite baseada no faturamento, passando por cada uma
	    int faixa = -1;
	    for (int i = 0; i < LIMITES.length; i++) {
	        if (faturamentoAnual.compareTo(LIMITES[i]) <= 0) {
	            faixa = i;
	            break;
	        }
	    }
	    if (faixa == -1) {
	        throw new IllegalArgumentException("Faturamento acima do limite do Simples Nacional");
	    }

	    BigDecimal nominal = new BigDecimal(aliquotas[faixa]);
	    BigDecimal deducao = new BigDecimal(deducoes[faixa]);

	    // (RBT12 x alíquota nominal - parcela a deduzir) / RBT12
	    aliquotaEfetiva = faturamentoAnual.multiply(nominal)
	            .subtract(deducao)
	            .divide(faturamentoAnual, 6, RoundingMode.HALF_UP);// essa é o processo de divisão para não dar erro com numeros infinitos quebrados

	    return aliquotaEfetiva;
	}
	
	  public BigDecimal calcularTributoMensal() {
	      if (faturamentoMensal == null) {
	          throw new IllegalStateException("Faturamento mensal não informado");
	      }
	          calcularAliquotaEfetiva();   // recalcula sempre, para não dar merda
	      return faturamentoMensal.multiply(aliquotaEfetiva).setScale(2, RoundingMode.HALF_UP);
	  }
	
	public boolean verificarLimite() {
		if (faturamentoAnual == null) {
	        throw new IllegalStateException("Faturamento anual não informado");
	    }
		// aqui eu returno se a operação é verdadeira ou falsa por ser boolean
	    return faturamentoAnual.compareTo(LIMITES[5]) <= 0;
	}
	
	public void exibir() {
		//Verificação simples antes de exibir os valores de Data e do AliquiotaEfetiva, para não quebrar a exibicao
		String data;
	    if (dataDeInicio == null) {
	        data = "não informada";
	    } else {
	        data = dataDeInicio.format(formato);
	    }
	    String aliquota;
	    if (aliquotaEfetiva == null) {
	        aliquota = "não calculada";
	    } else {
	        aliquota = aliquotaEfetiva.multiply(new BigDecimal("100"))
	                .setScale(2, RoundingMode.HALF_UP) + "%";// Calculo para mostrar em porcentagem
	    }
		System.out.println("================= Dados da empresa =================\n"
				+ "- CNPJ: " + cnpj 
				+ "\n- Razão Social: " + razaoSocial
				+ "\n- Nome Fantasia: " + nomeFantasia
				+ "\n- Estado: " + estado
				+ "\n- Municipio: " + municipio
				+ "\n- Atividade Principal: " + atividadePrincipal
				+ "\n- Atividade Secundaria (Anexo): " + atividadeSecundaria
				+ "\n- Faturamento Anual: R$" + faturamentoAnual 
				+ "\n- Faturamento Mensal: R$" + faturamentoMensal
				+ "\n- Aliquota Efetiva: " + aliquota
				+ "\n- Data de Inicio: " + data
				+ "\n- Número de Funcionarios: " + numeroFuncionarios 
				+ "\n==================================================="); 
	}
    
}
