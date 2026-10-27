package tipoEmpresa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.RoundingMode;

public class EmpresaLucroReal {
	/*
	 Parâmetros legais. 
		 Compensação de prejuízo fiscal limitada a 30% do lucro - Lei 9.065/1995.
		 Adicional de 10% sobre o lucro que exceder R$ 240.000 por ano - Lei 9.249/1995.
	 Fonte: planalto.gov.br / Receita Federal. Data da consulta: 20/09/2026 */
	private static final BigDecimal LIMITE_COMPENSACAO_PREJUIZO = new BigDecimal("0.30");
	private static final BigDecimal LIMITE_ADICIONAL_IRPJ = new BigDecimal("240000");
	private static final BigDecimal ALIQUOTA_ADICIONAL_IRPJ = new BigDecimal("0.10");

	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;
	private String municipio;
	private String estado;
	private String atividadePrincipal;
	private String atividadeSecundaria;
	private BigDecimal receitaBrutaAnual;
	private BigDecimal despesasDedutiveis;
	private BigDecimal aliquotaIRPJ;
	private BigDecimal aliquotaCSLL;
	private BigDecimal lucroContabil;
	private BigDecimal lucroAjustado;
	private BigDecimal prejuizoFiscal;
	private LocalDate dataDeInicio;

	private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public EmpresaLucroReal() {

	}

	public EmpresaLucroReal(String cnpj, String razaoSocial, String nomeFantasia, String municipio, String estado,
			String atividadePrincipal, String atividadeSecundaria, BigDecimal receitaBrutaAnual,
			BigDecimal despesasDedutiveis, BigDecimal aliquotaIRPJ, BigDecimal aliquotaCSLL, BigDecimal lucroContabil,
			BigDecimal lucroAjustado, BigDecimal prejuizoFiscal, LocalDate dataDeInicio) {
		this.setCnpj(cnpj);
		this.setRazaoSocial(razaoSocial);
		this.setNomeFantasia(nomeFantasia);
		this.setMunicipio(municipio);
		this.setEstado(estado);
		this.setAtividadePrincipal(atividadePrincipal);
		this.setAtividadeSecundaria(atividadeSecundaria);
		this.setReceitaBrutaAnual(receitaBrutaAnual);
		this.setDespesasDedutiveis(despesasDedutiveis);
		this.setAliquotaIRPJ(aliquotaIRPJ);
		this.setAliquotaCSLL(aliquotaCSLL);
		this.setLucroContabil(lucroContabil);
		this.setLucroAjustado(lucroAjustado);
		this.setPrejuizoFiscal(prejuizoFiscal);
		this.setDataDeInicio(dataDeInicio);
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
		if (razaoSocial == null || razaoSocial.isBlank()) {
			throw new IllegalArgumentException("Erro! Razao Social não pode ser vazia");
		} else {
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
		this.atividadePrincipal = atividadePrincipal;
	}

	public String getAtividadeSecundaria() {
		return atividadeSecundaria;
	}

	public void setAtividadeSecundaria(String atividadeSecundaria) {
		this.atividadeSecundaria = atividadeSecundaria;
	}

	public BigDecimal getReceitaBrutaAnual() {
		return receitaBrutaAnual;
	}

	public void setReceitaBrutaAnual(BigDecimal receitaBrutaAnual) {
		if (receitaBrutaAnual == null || receitaBrutaAnual.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! Receita Brutal Anual não pode ser Negativo!");
		} else {
			this.receitaBrutaAnual = receitaBrutaAnual;
		}
	}

	public BigDecimal getDespesasDedutiveis() {
		return despesasDedutiveis;
	}

	public void setDespesasDedutiveis(BigDecimal despesasDedutiveis) {
		if (despesasDedutiveis == null || despesasDedutiveis.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! Despesas Dedutiveis não pode ser Negativo!");
		} else
			this.despesasDedutiveis = despesasDedutiveis;
	}

	public BigDecimal getAliquotaIRPJ() {
		return aliquotaIRPJ;
	}

	public void setAliquotaIRPJ(BigDecimal aliquotaIRPJ) {
		if (aliquotaIRPJ == null || aliquotaIRPJ.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! A Aliquota IRPJ não pode ser Negativo!");
		} else {
			this.aliquotaIRPJ = aliquotaIRPJ;
		}
	}

	public BigDecimal getAliquotaCSLL() {
		return aliquotaCSLL;
	}

	public void setAliquotaCSLL(BigDecimal aliquotaCSLL) {
		if (aliquotaCSLL == null || aliquotaCSLL.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! A Aliquota CSLL não pode ser Negativo!");
		} else {
			this.aliquotaCSLL = aliquotaCSLL;
		}
	}

	public BigDecimal getLucroContabil() {
		return lucroContabil;
	}

	public void setLucroContabil(BigDecimal lucroContabil) {
		if (lucroContabil == null) {
			throw new IllegalArgumentException("Erro! o Lucro Contabil não pode estar vazio!");
		} else {
			this.lucroContabil = lucroContabil;
		}
	}

	public BigDecimal getLucroAjustado() {
		return lucroAjustado;
	}

	public void setLucroAjustado(BigDecimal lucroAjustado) {
		if (lucroAjustado == null) {
			throw new IllegalArgumentException("Erro! O Lucro Ajustado não pode estar vazio!");
		} else {
			this.lucroAjustado = lucroAjustado;
		}
	}

	public BigDecimal getPrejuizoFiscal() {
		return prejuizoFiscal;
	}

	public void setPrejuizoFiscal(BigDecimal prejuizoFiscal) {
		if (prejuizoFiscal == null || prejuizoFiscal.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! O Prejuizo Fical não pode ser Negativo ou estar vazio!");
		} else {
			this.prejuizoFiscal = prejuizoFiscal;
		}
	}

	public LocalDate getDataDeInicio() {
		return dataDeInicio;
	}

	public void setDataDeInicio(LocalDate dataDeInicio) {
		this.dataDeInicio = dataDeInicio;
	}

	private void validarDadosDoCalculo() {
		if (lucroContabil == null || despesasDedutiveis == null || prejuizoFiscal == null) {
			throw new IllegalStateException("Informe lucro contábil, despesas dedutíveis e prejuízo fiscal");
		}
	}

	public BigDecimal calcularLucroReal() {
		validarDadosDoCalculo();
		// lucro ajustado: lucro contábil com as despesas dedutíveis, antes de compensar
		// prejuízo
		lucroAjustado = lucroContabil.subtract(despesasDedutiveis).setScale(2, RoundingMode.HALF_UP);

		BigDecimal compensacao = BigDecimal.ZERO;
		if (lucroAjustado.signum() > 0) {
			compensacao = prejuizoFiscal.min(lucroAjustado.multiply(LIMITE_COMPENSACAO_PREJUIZO));
		}
		return lucroAjustado.subtract(compensacao).setScale(2, RoundingMode.HALF_UP); // lucro real
	}

	public BigDecimal calcularIRPJ() {
	   if (aliquotaIRPJ == null) { 
	       throw new IllegalStateException("Alíquota do IRPJ não informada");
	   }
		BigDecimal base = calcularLucroReal();
		if (base.signum() <= 0) {
			return BigDecimal.ZERO.setScale(2);
		}

		BigDecimal irpj = base.multiply(aliquotaIRPJ);
		if (base.compareTo(LIMITE_ADICIONAL_IRPJ) > 0) {
			BigDecimal excedente = base.subtract(LIMITE_ADICIONAL_IRPJ);
			irpj = irpj.add(excedente.multiply(ALIQUOTA_ADICIONAL_IRPJ));
		}
		return irpj.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal calcularCSLL() {
		   if (aliquotaCSLL == null) {  
		       throw new IllegalStateException("Alíquota do CSLL não informada");
		   }
		BigDecimal base = calcularLucroReal();
		if (base.signum() <= 0) {
			return BigDecimal.ZERO.setScale(2);
		}
		return base.multiply(aliquotaCSLL).setScale(2, RoundingMode.HALF_UP);
	}

	public void exibir() {
		String lucroReal, ajustado, irpj, csll;
	    /* aqui ele tenta calcular e transforma em string para exibir na tela, se der erro, 
		ele presenche para não aparecer bugaado*/
		try {
	        lucroReal = calcularLucroReal().toString();
	        ajustado = lucroAjustado.toString();   // calcularLucroReal() preenche o lucroAjustado
	        irpj = calcularIRPJ().toString();
	        csll = calcularCSLL().toString();
	    } catch (IllegalStateException erro) {
	        lucroReal = "não calculado";
	        ajustado = "não calculado";
	        irpj = "não calculado";
	        csll = "não calculada";
	    }
		String data;
		if (dataDeInicio == null) {
			data = "não informada";
		} else {
			data = dataDeInicio.format(formato);
		}
		System.out.println("================= Dados da empresa =================\n" 
					+ "- CNPJ: " + cnpj
					+ "\n- Razão Social: " + razaoSocial 
					+ "\n- Nome Fantasia: " + nomeFantasia 
					+ "\n- Estado: " + estado
					+ "\n- Municipio: " + municipio 
					+ "\n- Atividade Principal: " + atividadePrincipal
					+ "\n- Atividade Secundaria: " + atividadeSecundaria 
					+ "\n- Receita Anual: R$" + receitaBrutaAnual
					+ "\n- Despesas Dedutíveis: R$" + despesasDedutiveis 
					+ "\n- Prejuízo Fiscal: R$" + prejuizoFiscal
					+ "\n- Lucro Contabil: R$" + lucroContabil 
					+ "\n- Lucro Real: R$" + lucroReal
					+ "\n- Lucro Ajustado: R$" + ajustado
					+ "\n- Alíquota IRPJ: " + aliquotaIRPJ 
					+ "\n- Alíquota CSLL: " + aliquotaCSLL 
					+ "\n- IRPJ devido: R$" + irpj 
					+ "\n- CSLL devida: R$" + csll 
					+ "\n- Data de abertura: " + data
				+ "\n===================================================");
	}

}
