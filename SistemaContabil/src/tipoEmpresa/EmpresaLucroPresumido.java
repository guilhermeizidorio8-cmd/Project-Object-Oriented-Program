package tipoEmpresa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmpresaLucroPresumido {

	/*
	  Parâmetros legais
	  - Adicional de IRPJ: 10% sobre a base trimestral que exceder R$ 60.000 (R$ 20.000 x 3 meses) - Lei 9.249/1995, art. 3º.
	  - PIS 0,65% e COFINS 3% (regime cumulativo) - Lei 9.718/1998, art. 8º.
	  - Percentuais de presunção - Lei 9.249/1995, arts. 15 (IRPJ) e 20 (CSLL).
	  Fonte: planalto.gov.br / Receita Federal. Data da consulta: 20/09/2026 
	 */
	private static final BigDecimal CEM = new BigDecimal("100");
	private static final BigDecimal LIMITE_ADICIONAL_IRPJ = new BigDecimal("60000");
	private static final BigDecimal ALIQUOTA_ADICIONAL_IRPJ = new BigDecimal("0.10");
	private static final BigDecimal ALIQUOTA_PIS = new BigDecimal("0.0065");
	private static final BigDecimal ALIQUOTA_COFINS = new BigDecimal("0.03");
	private static final BigDecimal LIMITE_SERVICOS_16 = new BigDecimal("120000");
	private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	// Atividades da tabela de percentuais de presunção da Receita Federal.
	// 1,6%
	private static final String REVENDA_COMBUSTIVEIS = "Revenda a varejo de combustiveis e gas natural";
	// 8%
	private static final String VENDA_MERCADORIAS = "Venda de mercadorias ou produtos";
	private static final String TRANSPORTE_CARGAS = "Transporte de cargas";
	private static final String ATIVIDADES_IMOBILIARIAS = "Atividades imobiliarias";
	private static final String SERVICOS_HOSPITALARES = "Servicos hospitalares";
	private static final String ATIVIDADE_RURAL = "Atividade rural";
	private static final String INDUSTRIALIZACAO_ENCOMENDANTE = "Industrializacao com materiais fornecidos pelo encomendante";
	private static final String OUTRAS_ATIVIDADES = "Outras atividades nao especificadas";
	// 16%
	private static final String SERVICOS_TRANSPORTE = "Servicos de transporte";
	private static final String SERVICOS_GERAIS = "Servicos gerais";
	// 32%
	private static final String SERVICOS_PROFISSIONAIS = "Servicos profissionais";
	private static final String INTERMEDIACAO_NEGOCIOS = "Intermediacao de negocios";
	private static final String ADMINISTRACAO_BENS = "Administracao, locacao ou cessao de bens";
	private static final String CONSTRUCAO_CIVIL = "Servicos de construcao civil";
	private static final String SERVICOS_EM_GERAL = "Servicos em geral";
	// depende da nota (2) da tabela
	private static final String VEICULOS_USADOS = "Comercializacao de veiculos usados";

	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;
	private String municipio;
	private String estado;
	private String atividadePrincipal;
	private String atividadeSecundaria;
	private BigDecimal receitaBrutaAnual;
	private BigDecimal receitaBrutaTrimestral;
	private BigDecimal percentualDePresuncao; // de 0 a 100 (ex.: 32 = 32%)
	private BigDecimal aliquotaIRPJ; // forma decimal (ex.: 0.15 = 15%)
	private BigDecimal aliquotaCSLL; // forma decimal (ex.: 0.09 = 9%)
	private BigDecimal valorPIS;
	private BigDecimal valorCOFINS;
	private LocalDate dataDeInicio;

	// CONSTRUTOR SEM PARAMETROS
	public EmpresaLucroPresumido() {

	}

	// CONSTRUTOR COMPLETO
	public EmpresaLucroPresumido(String cnpj, String razaoSocial, String nomeFantasia, String municipio,
			String estado, String atividadePrincipal, String atividadeSecundaria, BigDecimal receitaBrutaAnual,
			BigDecimal receitaBrutaTrimestral, BigDecimal percentualDePresuncao, BigDecimal aliquotaIRPJ,
			BigDecimal aliquotaCSLL, BigDecimal valorPIS, BigDecimal valorCOFINS, LocalDate dataDeInicio) {
		this.setCnpj(cnpj);
		this.setRazaoSocial(razaoSocial);
		this.setNomeFantasia(nomeFantasia);
		this.setMunicipio(municipio);
		this.setEstado(estado);
		this.setAtividadePrincipal(atividadePrincipal);
		this.setAtividadeSecundaria(atividadeSecundaria);
		this.setReceitaBrutaAnual(receitaBrutaAnual);
		this.setReceitaBrutaTrimestral(receitaBrutaTrimestral);
		this.setPercentualDePresuncao(percentualDePresuncao);
		this.setAliquotaIRPJ(aliquotaIRPJ);
		this.setAliquotaCSLL(aliquotaCSLL);
		this.setValorPIS(valorPIS);
		this.setValorCOFINS(valorCOFINS);
		this.setDataDeInicio(dataDeInicio);
	}

	// GETTERs E SETTERs
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		if (cnpj == null || cnpj.isBlank()) {
			throw new IllegalArgumentException("Erro! CNPJ não pode ser vazio");
		}
		// retira caracteres não numéricos, deixando apenas os números
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
			throw new IllegalArgumentException("Erro! Razão Social não pode ser vazia");
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
		if (atividadePrincipal != null && !atividadePrincipal.isBlank()
				&& (atividadePrincipal.equalsIgnoreCase(REVENDA_COMBUSTIVEIS)
				|| atividadePrincipal.equalsIgnoreCase(VENDA_MERCADORIAS)
				|| atividadePrincipal.equalsIgnoreCase(TRANSPORTE_CARGAS)
				|| atividadePrincipal.equalsIgnoreCase(ATIVIDADES_IMOBILIARIAS)
				|| atividadePrincipal.equalsIgnoreCase(SERVICOS_HOSPITALARES)
				|| atividadePrincipal.equalsIgnoreCase(ATIVIDADE_RURAL)
				|| atividadePrincipal.equalsIgnoreCase(INDUSTRIALIZACAO_ENCOMENDANTE)
				|| atividadePrincipal.equalsIgnoreCase(OUTRAS_ATIVIDADES)
				|| atividadePrincipal.equalsIgnoreCase(SERVICOS_TRANSPORTE)
				|| atividadePrincipal.equalsIgnoreCase(SERVICOS_GERAIS)
				|| atividadePrincipal.equalsIgnoreCase(SERVICOS_PROFISSIONAIS)
				|| atividadePrincipal.equalsIgnoreCase(INTERMEDIACAO_NEGOCIOS)
				|| atividadePrincipal.equalsIgnoreCase(ADMINISTRACAO_BENS)
				|| atividadePrincipal.equalsIgnoreCase(CONSTRUCAO_CIVIL)
				|| atividadePrincipal.equalsIgnoreCase(SERVICOS_EM_GERAL)
				|| atividadePrincipal.equalsIgnoreCase(VEICULOS_USADOS))) {
			this.atividadePrincipal = atividadePrincipal;
		} else {
			throw new IllegalArgumentException(
					"Erro! Atividade Principal inválida, escolha uma atividade da tabela da Receita Federal");
		}
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
			throw new IllegalArgumentException("Erro! Receita Bruta Anual não pode ser Negativa ou vazia!");
		} else {
			this.receitaBrutaAnual = receitaBrutaAnual;
		}
	}

	public BigDecimal getReceitaBrutaTrimestral() {
		return receitaBrutaTrimestral;
	}

	public void setReceitaBrutaTrimestral(BigDecimal receitaBrutaTrimestral) {
		if (receitaBrutaTrimestral == null || receitaBrutaTrimestral.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! Receita Bruta Trimestral não pode ser Negativa ou vazia!");
		} else {
			this.receitaBrutaTrimestral = receitaBrutaTrimestral;
		}
	}

	public BigDecimal getPercentualDePresuncao() {
		return percentualDePresuncao;
	}

	public void setPercentualDePresuncao(BigDecimal percentualDePresuncao) {
		if (percentualDePresuncao == null || percentualDePresuncao.compareTo(BigDecimal.ZERO) < 0
				|| percentualDePresuncao.compareTo(CEM) > 0) {
			throw new IllegalArgumentException("Erro! O Percentual de Presunção deve estar entre 0 e 100!");
		} else {
			this.percentualDePresuncao = percentualDePresuncao;
		}
	}

	public BigDecimal getAliquotaIRPJ() {
		return aliquotaIRPJ;
	}

	public void setAliquotaIRPJ(BigDecimal aliquotaIRPJ) {
		if (aliquotaIRPJ == null || aliquotaIRPJ.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! A Alíquota IRPJ não pode ser Negativa ou vazia!");
		} else {
			this.aliquotaIRPJ = aliquotaIRPJ;
		}
	}

	public BigDecimal getAliquotaCSLL() {
		return aliquotaCSLL;
	}

	public void setAliquotaCSLL(BigDecimal aliquotaCSLL) {
		if (aliquotaCSLL == null || aliquotaCSLL.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! A Alíquota CSLL não pode ser Negativa ou vazia!");
		} else {
			this.aliquotaCSLL = aliquotaCSLL;
		}
	}

	public BigDecimal getValorPIS() {
		return valorPIS;
	}

	public void setValorPIS(BigDecimal valorPIS) {
		if (valorPIS == null || valorPIS.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! Valor do PIS não pode ser Negativo ou vazio!");
		} else {
			this.valorPIS = valorPIS;
		}
	}

	public BigDecimal getValorCOFINS() {
		return valorCOFINS;
	}

	public void setValorCOFINS(BigDecimal valorCOFINS) {
		if (valorCOFINS == null || valorCOFINS.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Erro! Valor do COFINS não pode ser Negativo ou vazio!");
		} else {
			this.valorCOFINS = valorCOFINS;
		}
	}

	public LocalDate getDataDeInicio() {
		return dataDeInicio;
	}

	public void setDataDeInicio(LocalDate dataDeInicio) {
		this.dataDeInicio = dataDeInicio;
	}

	public BigDecimal sugerirPercentualPresuncao() {
		if (atividadePrincipal == null) {
			throw new IllegalStateException("Atividade principal não informada");
		}
		if (receitaBrutaAnual == null) {
			throw new IllegalStateException("Receita Bruta Anual não informada");
		}

		if (atividadePrincipal.equalsIgnoreCase(REVENDA_COMBUSTIVEIS)) {
			return new BigDecimal("1.6");
		} else if (atividadePrincipal.equalsIgnoreCase(VENDA_MERCADORIAS)
				|| atividadePrincipal.equalsIgnoreCase(TRANSPORTE_CARGAS)
				|| atividadePrincipal.equalsIgnoreCase(ATIVIDADES_IMOBILIARIAS)
				|| atividadePrincipal.equalsIgnoreCase(SERVICOS_HOSPITALARES)
				|| atividadePrincipal.equalsIgnoreCase(ATIVIDADE_RURAL)
				|| atividadePrincipal.equalsIgnoreCase(INDUSTRIALIZACAO_ENCOMENDANTE)
				|| atividadePrincipal.equalsIgnoreCase(OUTRAS_ATIVIDADES)) {
			return new BigDecimal("8");
		} else if (atividadePrincipal.equalsIgnoreCase(SERVICOS_TRANSPORTE)) {
			return new BigDecimal("16");
		} else if (atividadePrincipal.equalsIgnoreCase(SERVICOS_GERAIS)) {
			// 16% só vale para receita bruta de até R$ 120.000 por ano
			if (receitaBrutaAnual.compareTo(LIMITE_SERVICOS_16) <= 0) {
				return new BigDecimal("16");
			} else {
				return new BigDecimal("32");
			}
		} else if (atividadePrincipal.equalsIgnoreCase(SERVICOS_PROFISSIONAIS)
				|| atividadePrincipal.equalsIgnoreCase(INTERMEDIACAO_NEGOCIOS)
				|| atividadePrincipal.equalsIgnoreCase(ADMINISTRACAO_BENS)
				|| atividadePrincipal.equalsIgnoreCase(CONSTRUCAO_CIVIL)
				|| atividadePrincipal.equalsIgnoreCase(SERVICOS_EM_GERAL)) {
			return new BigDecimal("32");
		} else {
			// Comercialização de veículos usados: a tabela remete à nota (2)
			throw new IllegalStateException(
					"Percentual desta atividade depende da nota (2) da tabela, informe-o manualmente");
		}
	}

	// Base presumida = receita bruta trimestral x (percentual / 100)
	public BigDecimal calcularBasePresumida() {
		if (receitaBrutaTrimestral == null) {
			throw new IllegalStateException("Receita Bruta Trimestral não informada");
		}
		if (percentualDePresuncao == null) {
			throw new IllegalStateException("Percentual de Presunção não informado");
		}
		return receitaBrutaTrimestral.multiply(percentualDePresuncao).divide(CEM, 2, RoundingMode.HALF_UP);
	}

	public BigDecimal calcularIRPJ() {
		if (aliquotaIRPJ == null) {
			throw new IllegalStateException("Alíquota do IRPJ não informada");
		}
		BigDecimal base = calcularBasePresumida();
		BigDecimal irpj = base.multiply(aliquotaIRPJ);

		// adicional de 10% sobre o que exceder o limite trimestral
		if (base.compareTo(LIMITE_ADICIONAL_IRPJ) > 0) {
			BigDecimal excedente = base.subtract(LIMITE_ADICIONAL_IRPJ);
			irpj = irpj.add(excedente.multiply(ALIQUOTA_ADICIONAL_IRPJ));
		}
		return irpj.setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal calcularCSLL() {
		if (aliquotaCSLL == null) {
			throw new IllegalStateException("Alíquota da CSLL não informada");
		}
		return calcularBasePresumida().multiply(aliquotaCSLL).setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal calcularValorPIS() {
		if (receitaBrutaTrimestral == null) {
			throw new IllegalStateException("Receita Bruta Trimestral não informada");
		}
		this.setValorPIS(receitaBrutaTrimestral.multiply(ALIQUOTA_PIS).setScale(2, RoundingMode.HALF_UP));
		return valorPIS;
	}

	public BigDecimal calcularValorCOFINS() {
		if (receitaBrutaTrimestral == null) {
			throw new IllegalStateException("Receita Bruta Trimestral não informada");
		}
		this.setValorCOFINS(receitaBrutaTrimestral.multiply(ALIQUOTA_COFINS).setScale(2, RoundingMode.HALF_UP));
		return valorCOFINS;
	}

	public BigDecimal calcularTotalTributosTrimestrais() {
		return calcularIRPJ().add(calcularCSLL()).add(calcularValorPIS()).add(calcularValorCOFINS());
	}

	public void exibir() {
		String base, irpj, csll, total;
		// tenta calcular; se faltar dado, exibe "não calculado" para não quebrar a exibição
		try {
			base = calcularBasePresumida().toString();
			irpj = calcularIRPJ().toString();
			csll = calcularCSLL().toString();
			total = calcularTotalTributosTrimestrais().toString();
		} catch (IllegalStateException erro) {
			base = "não calculada";
			irpj = "não calculado";
			csll = "não calculada";
			total = "não calculado";
		}
		String data;
		if (dataDeInicio == null) {
			data = "não informada";
		} else {
			data = dataDeInicio.format(FORMATO);
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
				+ "\n- Receita Trimestral: R$" + receitaBrutaTrimestral
				+ "\n- Percentual de Presunção: " + percentualDePresuncao + "%"
				+ "\n- Base Presumida: R$" + base
				+ "\n- Alíquota IRPJ: " + aliquotaIRPJ
				+ "\n- Alíquota CSLL: " + aliquotaCSLL
				+ "\n- IRPJ devido: R$" + irpj
				+ "\n- CSLL devida: R$" + csll
				+ "\n- Valor do PIS: R$" + valorPIS
				+ "\n- Valor do COFINS: R$" + valorCOFINS
				+ "\n- Total de tributos trimestrais: R$" + total
				+ "\n- Data de início: " + data
				+ "\n===================================================");
	}

}