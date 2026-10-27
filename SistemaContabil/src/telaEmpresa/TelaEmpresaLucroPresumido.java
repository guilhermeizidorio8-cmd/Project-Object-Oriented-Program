package telaEmpresa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;



public class TelaEmpresaLucroPresumido extends JFrame implements ActionListener {

	// Identificador de versao exigido por herdar de JFrame (Serializable); nao afeta o funcionamento do programa
	private static final long serialVersionUID = 1L;

	// Paineis: cada parte da tela fica em um painel separado
	private JPanel painelCampos, painelResultados, painelBotoes, painelMensagem, painelRodape;

	// Labels
	private JLabel textCnpj, textRs, textNF, textEST, textMUN, textATVP, textATVS, textDTA,
			textRECA, textRECT, textPERC, textAIRPJ, textACSLL,
			textResBase, textResIrpj, textResCsll, textResPis, textResCofins, textResTotal, textMensagem;

	// Campos para digitar
	private JTextField cnpj, rs, nf, mun, dta, reca, rect, perc, aliqIrpj, aliqCsll;

	// Campos de resultado (somente leitura: quem calcula e a classe)
	private JTextField resBase, resIrpj, resCsll, resPis, resCofins, resTotal;

	//Botoes
	private JButton cad, lim, sug, calB, calI, calC, calP, calCo, calT;
	
	// ComboBox
	private JComboBox<String> est, atvp, atvs;

	


	private static String[] estados = {
			"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
			"MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ",
			"RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
	};

	private static String[] principal = {
			"Revenda a varejo de combustiveis e gas natural",
			"Venda de mercadorias ou produtos",
			"Transporte de cargas",
			"Atividades imobiliarias",
			"Servicos hospitalares",
			"Atividade rural",
			"Industrializacao com materiais fornecidos pelo encomendante",
			"Outras atividades nao especificadas",
			"Servicos de transporte",
			"Servicos gerais",
			"Servicos profissionais",
			"Intermediacao de negocios",
			"Administracao, locacao ou cessao de bens",
			"Servicos de construcao civil",
			"Servicos em geral",
			"Comercializacao de veiculos usados"
	};
	private static String[] secundaria = {
			"Nenhuma",
			"Revenda a varejo de combustiveis e gas natural",
			"Venda de mercadorias ou produtos",
			"Transporte de cargas",
			"Atividades imobiliarias",
			"Servicos hospitalares",
			"Atividade rural",
			"Industrializacao com materiais fornecidos pelo encomendante",
			"Outras atividades nao especificadas",
			"Servicos de transporte",
			"Servicos gerais",
			"Servicos profissionais",
			"Intermediacao de negocios",
			"Administracao, locacao ou cessao de bens",
			"Servicos de construcao civil",
			"Servicos em geral",
			"Comercializacao de veiculos usados"
	};

	public static void main(String[] args) {
		new TelaEmpresaLucroPresumido();
	}

	// O construtor so monta a janela: cada componente e criado no seu proprio getter
	public TelaEmpresaLucroPresumido() {
		super("Cadastro Empresa Lucro Presumido");
		this.setSize(1000, 900);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout(10, 10));
		this.add(getPainelCampos(), BorderLayout.CENTER);
		this.add(getPainelRodape(), BorderLayout.SOUTH);
		this.setVisible(true);
	}

	// Painel dos campos para digitar: duas colunas (texto e campo)
	public JPanel getPainelCampos() {
		if (painelCampos == null) {
			painelCampos = new JPanel();
			painelCampos.setLayout(new GridLayout(0, 2, 10, 8));
			painelCampos.setBackground(Color.white);
			painelCampos.add(getTextCnpj());
			painelCampos.add(getCnpj());
			painelCampos.add(getTextRs());
			painelCampos.add(getRs());
			painelCampos.add(getTextNF());
			painelCampos.add(getNf());
			painelCampos.add(getTextEST());
			painelCampos.add(getEst());
			painelCampos.add(getTextMUN());
			painelCampos.add(getMun());
			painelCampos.add(getTextATVP());
			painelCampos.add(getAtvp());
			painelCampos.add(getTextATVS());
			painelCampos.add(getAtvs());
			painelCampos.add(getTextDTA());
			painelCampos.add(getDta());
			painelCampos.add(getTextRECA());
			painelCampos.add(getReca());
			painelCampos.add(getTextRECT());
			painelCampos.add(getRect());
			painelCampos.add(getTextPERC());
			painelCampos.add(getPerc());
			painelCampos.add(getTextAIRPJ());
			painelCampos.add(getAliqIrpj());
			painelCampos.add(getTextACSLL());
			painelCampos.add(getAliqCsll());
		}
		return painelCampos;
	}

	// Painel dos resultados dos calculos: quatro colunas (texto, campo, texto, campo)
	public JPanel getPainelResultados() {
		if (painelResultados == null) {
			painelResultados = new JPanel();
			painelResultados.setLayout(new GridLayout(0, 4, 10, 8));
			painelResultados.setBackground(Color.white);
			painelResultados.add(getTextResBase());
			painelResultados.add(getResBase());
			painelResultados.add(getTextResTotal());
			painelResultados.add(getResTotal());
			painelResultados.add(getTextResIrpj());
			painelResultados.add(getResIrpj());
			painelResultados.add(getTextResCsll());
			painelResultados.add(getResCsll());
			painelResultados.add(getTextResPis());
			painelResultados.add(getResPis());
			painelResultados.add(getTextResCofins());
			painelResultados.add(getResCofins());
		}
		return painelResultados;
	}

	// Painel dos botoes
	public JPanel getPainelBotoes() {
		if (painelBotoes == null) {
			painelBotoes = new JPanel();
			painelBotoes.setLayout(new GridLayout(0, 5, 10, 5));
			painelBotoes.setBackground(Color.white);
			painelBotoes.add(getCad());
			painelBotoes.add(getLim());
			painelBotoes.add(getSug());
			painelBotoes.add(getCalB());
			painelBotoes.add(getCalI());
			painelBotoes.add(getCalC());
			painelBotoes.add(getCalP());
			painelBotoes.add(getCalCo());
			painelBotoes.add(getCalT());
		}
		return painelBotoes;
	}

	// Painel da mensagem, painel focado em mostrar futuramente os erros e acertos do usuario
	public JPanel getPainelMensagem() {
		if (painelMensagem == null) {
			painelMensagem = new JPanel();
			painelMensagem.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
			painelMensagem.setBackground(Color.white);
			painelMensagem.add(getTextMensagem());
		}
		return painelMensagem;
	}
	public JLabel getTextMensagem() {
		if (textMensagem == null) {
			textMensagem = new JLabel("Preencha os dados e clique em Cadastro.");
		}
		return textMensagem;
	}

	// Painel do rodape: resultados em cima, depois botoes e mensagem
	public JPanel getPainelRodape() {
		if (painelRodape == null) {
			painelRodape = new JPanel();
			painelRodape.setLayout(new BorderLayout(5, 5));
			painelRodape.add(getPainelResultados(), BorderLayout.NORTH);
			painelRodape.add(getPainelBotoes(), BorderLayout.CENTER);
			painelRodape.add(getPainelMensagem(), BorderLayout.SOUTH);
		}
		return painelRodape;
	}
	
	


	public JLabel getTextCnpj() {
		if (textCnpj == null) {
			textCnpj = new JLabel("CNPJ: ");
		}
		return textCnpj;
	}

	public JLabel getTextRs() {
		if (textRs == null) {
			textRs = new JLabel("Razão Social: ");
		}
		return textRs;
	}

	public JLabel getTextNF() {
		if (textNF == null) {
			textNF = new JLabel("Nome Fantasia: ");
		}
		return textNF;
	}

	public JLabel getTextEST() {
		if (textEST == null) {
			textEST = new JLabel("Estado: ");
		}
		return textEST;
	}

	public JLabel getTextMUN() {
		if (textMUN == null) {
			textMUN = new JLabel("Município: ");
		}
		return textMUN;
	}

	public JLabel getTextATVP() {
		if (textATVP == null) {
			textATVP = new JLabel("Atividade Principal: ");
		}
		return textATVP;
	}

	public JLabel getTextATVS() {
		if (textATVS == null) {
			textATVS = new JLabel("Atividade Secundária: ");
		}
		return textATVS;
	}

	public JLabel getTextDTA() {
		if (textDTA == null) {
			textDTA = new JLabel("Data de Início (dd/MM/aaaa): ");
		}
		return textDTA;
	}

	public JLabel getTextRECA() {
		if (textRECA == null) {
			textRECA = new JLabel("Receita Bruta Anual: ");
		}
		return textRECA;
	}

	public JLabel getTextRECT() {
		if (textRECT == null) {
			textRECT = new JLabel("Receita Bruta Trimestral: ");
		}
		return textRECT;
	}

	public JLabel getTextPERC() {
		if (textPERC == null) {
			textPERC = new JLabel("Percentual de Presunção em % (ex.: 32): ");
		}
		return textPERC;
	}

	public JLabel getTextAIRPJ() {
		if (textAIRPJ == null) {
			textAIRPJ = new JLabel("Alíquota do IRPJ em % (ex.: 15): ");
		}
		return textAIRPJ;
	}

	public JLabel getTextACSLL() {
		if (textACSLL == null) {
			textACSLL = new JLabel("Alíquota da CSLL em % (ex.: 9): ");
		}
		return textACSLL;
	}

	public JLabel getTextResBase() {
		if (textResBase == null) {
			textResBase = new JLabel("Base Presumida: ");
		}
		return textResBase;
	}

	public JLabel getTextResIrpj() {
		if (textResIrpj == null) {
			textResIrpj = new JLabel("IRPJ devido: ");
		}
		return textResIrpj;
	}

	public JLabel getTextResCsll() {
		if (textResCsll == null) {
			textResCsll = new JLabel("CSLL devida: ");
		}
		return textResCsll;
	}

	public JLabel getTextResPis() {
		if (textResPis == null) {
			textResPis = new JLabel("Valor do PIS: ");
		}
		return textResPis;
	}

	public JLabel getTextResCofins() {
		if (textResCofins == null) {
			textResCofins = new JLabel("Valor do COFINS: ");
		}
		return textResCofins;
	}

	public JLabel getTextResTotal() {
		if (textResTotal == null) {
			textResTotal = new JLabel("Total de Tributos: ");
		}
		return textResTotal;
	}

	public JTextField getCnpj() {
		if (cnpj == null) {
			cnpj = new JTextField();
		}
		return cnpj;
	}

	public JTextField getRs() {
		if (rs == null) {
			rs = new JTextField();
		}
		return rs;
	}

	public JTextField getNf() {
		if (nf == null) {
			nf = new JTextField();
		}
		return nf;
	}

	public JTextField getMun() {
		if (mun == null) {
			mun = new JTextField();
		}
		return mun;
	}

	public JTextField getDta() {
		if (dta == null) {
			dta = new JTextField();
		}
		return dta;
	}

	public JTextField getReca() {
		if (reca == null) {
			reca = new JTextField();
		}
		return reca;
	}

	public JTextField getRect() {
		if (rect == null) {
			rect = new JTextField();
		}
		return rect;
	}

	public JTextField getPerc() {
		if (perc == null) {
			perc = new JTextField();
		}
		return perc;
	}

	public JTextField getAliqIrpj() {
		if (aliqIrpj == null) {
			aliqIrpj = new JTextField();
		}
		return aliqIrpj;
	}

	public JTextField getAliqCsll() {
		if (aliqCsll == null) {
			aliqCsll = new JTextField();
		}
		return aliqCsll;
	}

	public JTextField getResBase() {
		if (resBase == null) {
			resBase = new JTextField();
			resBase.setEditable(false);
		}
		return resBase;
	}

	public JTextField getResIrpj() {
		if (resIrpj == null) {
			resIrpj = new JTextField();
			resIrpj.setEditable(false);
		}
		return resIrpj;
	}

	public JTextField getResCsll() {
		if (resCsll == null) {
			resCsll = new JTextField();
			resCsll.setEditable(false);
		}
		return resCsll;
	}

	public JTextField getResPis() {
		if (resPis == null) {
			resPis = new JTextField();
			resPis.setEditable(false);
		}
		return resPis;
	}

	public JTextField getResCofins() {
		if (resCofins == null) {
			resCofins = new JTextField();
			resCofins.setEditable(false);
		}
		return resCofins;
	}

	public JTextField getResTotal() {
		if (resTotal == null) {
			resTotal = new JTextField();
			resTotal.setEditable(false);
		}
		return resTotal;
	}

	public JComboBox<String> getEst() {
		if (est == null) {
			est = new JComboBox<>(estados);
		}
		return est;
	}

	public JComboBox<String> getAtvp() {
		if (atvp == null) {
			atvp = new JComboBox<>(principal);
		}
		return atvp;
	}

	public JComboBox<String> getAtvs() {
		if (atvs == null) {
			atvs = new JComboBox<>(secundaria);
		}
		return atvs;
	}

	public JButton getCad() {
		if (cad == null) {
			cad = new JButton("Cadastro");
			cad.addActionListener(this);
		}
		return cad;
	}

	public JButton getLim() {
		if (lim == null) {
			lim = new JButton("Limpar");
			lim.addActionListener(this);
		}
		return lim;
	}

	public JButton getSug() {
		if (sug == null) {
			sug = new JButton("Sugerir Percentual");
			sug.addActionListener(this);
		}
		return sug;
	}

	public JButton getCalB() {
		if (calB == null) {
			calB = new JButton("Calcular Base Presumida");
			calB.addActionListener(this);
		}
		return calB;
	}

	public JButton getCalI() {
		if (calI == null) {
			calI = new JButton("Calcular IRPJ");
			calI.addActionListener(this);
		}
		return calI;
	}

	public JButton getCalC() {
		if (calC == null) {
			calC = new JButton("Calcular CSLL");
			calC.addActionListener(this);
		}
		return calC;
	}

	public JButton getCalP() {
		if (calP == null) {
			calP = new JButton("Calcular PIS");
			calP.addActionListener(this);
		}
		return calP;
	}

	public JButton getCalCo() {
		if (calCo == null) {
			calCo = new JButton("Calcular COFINS");
			calCo.addActionListener(this);
		}
		return calCo;
	}

	public JButton getCalT() {
		if (calT == null) {
			calT = new JButton("Calcular Total");
			calT.addActionListener(this);
		}
		return calT;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getCad()) {
			cadastrar();
		} else if (e.getSource() == getLim()) {
			limpar();
		} else if (e.getSource() == getSug()) {
			sugerirPercentual();
		} else if (e.getSource() == getCalB()) {
			calcularBase();
		} else if (e.getSource() == getCalI()) {
			calcularIrpj();
		} else if (e.getSource() == getCalC()) {
			calcularCsll();
		} else if (e.getSource() == getCalP()) {
			calcularPis();
		} else if (e.getSource() == getCalCo()) {
			calcularCofins();
		} else if (e.getSource() == getCalT()) {
			calcularTotal();
		}
	}
	
	private void limpar() {
		getCnpj().setText("");
		getRs().setText("");
		getNf().setText("");
		getMun().setText("");
		getDta().setText("");
		getReca().setText("");
		getRect().setText("");
		getPerc().setText("");
		getAliqIrpj().setText("");
		getAliqCsll().setText("");
		getEst().setSelectedIndex(0);
		getAtvp().setSelectedIndex(0);
		getAtvs().setSelectedIndex(0);
		getResBase().setText("");
		getResIrpj().setText("");
		getResCsll().setText("");
		getResPis().setText("");
		getResCofins().setText("");
		getResTotal().setText("");
	}
	
	//Methods que nao estao funcionais, apenas para preencher no actionPerformed

	private void cadastrar() {
		getTextMensagem().setText("Botão de Cadastro Funcionando!");
	}
	private void sugerirPercentual() {
		getTextMensagem().setText("Botão de Sugerir Percentual Funcionando!");
	}

	private void calcularBase() {
		getTextMensagem().setText("Botão de Calcular Base Funcionando!");
	}

	private void calcularIrpj() {
		getTextMensagem().setText("Botão de Calcular IRPJ Funcionando!");
	}

	private void calcularCsll() {
		getTextMensagem().setText("Botão de Calcular CSLL Funcionando!");
	}

	private void calcularPis() {
		getTextMensagem().setText("Botão de Calcular PIS Funcionando!");
	}

	private void calcularCofins() {
		getTextMensagem().setText("Botão de Calcular COFINS Funcionando!");
	}

	private void calcularTotal() {
		getTextMensagem().setText("Botão de Calcular Total Funcionando!");
	}

}
