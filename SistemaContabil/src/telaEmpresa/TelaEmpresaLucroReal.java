package telaEmpresa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class TelaEmpresaLucroReal extends JFrame implements ActionListener {

	// Identificador de versao exigido por herdar de JFrame (Serializable); nao afeta o funcionamento do programa
	private static final long serialVersionUID = 1L;

	// Paineis: cada parte da tela fica em um painel separado
	private JPanel painelCampos, painelResultados, painelBotoes, painelMensagem, painelRodape;

	// Labels
	private JLabel textCnpj, textRs, textNF, textEST, textMUN, textATVP, textATVS, textDTA,
			textREC, textDESP, textLC, textPREJ, textAIRPJ, textACSLL,
			textResAjustado, textResLucroReal, textResIrpj, textResCsll, textMensagem;

	// Campos para digitar
	private JTextField cnpj, rs, nf, mun, dta, rec, desp, lc, prej, aliqIrpj, aliqCsll;

	// Campos de resultado (somente leitura: quem calcula e a classe)
	private JTextField resAjustado, resLucroReal, resIrpj, resCsll;

	// Botoes
	private JButton cad, lim, calL, calI, calC;
	
	// ComboBox
	private JComboBox<String> est, atvp, atvs;

	// Opcoes dos ComboBox
	private static String[] estados = {
			"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
			"MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ",
			"RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
	};
	private static String[] principal = {"Comercio", "Industria", "Servico"};
	private static String[] secundaria = {"Nenhuma", "Comercio", "Industria", "Servico"};

	public static void main(String[] args) {
		new TelaEmpresaLucroReal();
	}

	// O construtor so monta a janela: cada componente e criado no seu proprio getter
	public TelaEmpresaLucroReal() {
		super("Cadastro Empresa Lucro Real");
		this.setSize(920, 880);
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
			painelCampos.add(getTextREC());
			painelCampos.add(getRec());
			painelCampos.add(getTextDESP());
			painelCampos.add(getDesp());
			painelCampos.add(getTextLC());
			painelCampos.add(getLc());
			painelCampos.add(getTextPREJ());
			painelCampos.add(getPrej());
			painelCampos.add(getTextAIRPJ());
			painelCampos.add(getAliqIrpj());
			painelCampos.add(getTextACSLL());
			painelCampos.add(getAliqCsll());
		}
		return painelCampos;
	}

	// Painel dos resultados dos calculos
	public JPanel getPainelResultados() {
		if (painelResultados == null) {
			painelResultados = new JPanel();
			painelResultados.setLayout(new GridLayout(0, 2, 10, 8));
			painelResultados.setBackground(Color.white);
			painelResultados.add(getTextResAjustado());
			painelResultados.add(getResAjustado());
			painelResultados.add(getTextResLucroReal());
			painelResultados.add(getResLucroReal());
			painelResultados.add(getTextResIrpj());
			painelResultados.add(getResIrpj());
			painelResultados.add(getTextResCsll());
			painelResultados.add(getResCsll());
		}
		return painelResultados;
	}

	// Painel dos botoes
	public JPanel getPainelBotoes() {
		if (painelBotoes == null) {
			painelBotoes = new JPanel();
			painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
			painelBotoes.setBackground(Color.white);
			painelBotoes.add(getCad());
			painelBotoes.add(getLim());
			painelBotoes.add(getCalL());
			painelBotoes.add(getCalI());
			painelBotoes.add(getCalC());
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

	public JLabel getTextREC() {
		if (textREC == null) {
			textREC = new JLabel("Receita Bruta Anual: ");
		}
		return textREC;
	}

	public JLabel getTextDESP() {
		if (textDESP == null) {
			textDESP = new JLabel("Despesas Dedutíveis: ");
		}
		return textDESP;
	}

	public JLabel getTextLC() {
		if (textLC == null) {
			textLC = new JLabel("Lucro Contábil (pode ser negativo): ");
		}
		return textLC;
	}

	public JLabel getTextPREJ() {
		if (textPREJ == null) {
			textPREJ = new JLabel("Prejuízo Fiscal: ");
		}
		return textPREJ;
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

	public JLabel getTextResAjustado() {
		if (textResAjustado == null) {
			textResAjustado = new JLabel("Lucro Ajustado: ");
		}
		return textResAjustado;
	}

	public JLabel getTextResLucroReal() {
		if (textResLucroReal == null) {
			textResLucroReal = new JLabel("Lucro Real: ");
		}
		return textResLucroReal;
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

	public JTextField getRec() {
		if (rec == null) {
			rec = new JTextField();

		}
		return rec;
	}

	public JTextField getDesp() {
		if (desp == null) {
			desp = new JTextField();

		}
		return desp;
	}

	public JTextField getLc() {
		if (lc == null) {
			lc = new JTextField();

		}
		return lc;
	}

	public JTextField getPrej() {
		if (prej == null) {
			prej = new JTextField();

		}
		return prej;
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


	public JTextField getResAjustado() {
		if (resAjustado == null) {
			resAjustado = new JTextField();
			resAjustado.setEditable(false);
		}
		return resAjustado;
	}

	public JTextField getResLucroReal() {
		if (resLucroReal == null) {
			resLucroReal = new JTextField();
			resLucroReal.setEditable(false);
		}
		return resLucroReal;
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

	public JButton getCalL() {
		if (calL == null) {
			calL = new JButton("Calcular Lucro Real");
			calL.addActionListener(this);
		}
		return calL;
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


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getCad()) {
			cadastrar();
		} else if (e.getSource() == getLim()) {
			limpar();
		} else if (e.getSource() == getCalL()) {
			calcularLucroReal();
		} else if (e.getSource() == getCalI()) {
			calcularIrpj();
		} else if (e.getSource() == getCalC()) {
			calcularCsll();
		}
	}
	
	// Limpa todos os campos e esquece a empresa cadastrada
		private void limpar() {
			getCnpj().setText("");
			getRs().setText("");
			getNf().setText("");
			getMun().setText("");
			getDta().setText("");
			getRec().setText("");
			getDesp().setText("");
			getLc().setText("");
			getPrej().setText("");
			getAliqIrpj().setText("");
			getAliqCsll().setText("");
			getEst().setSelectedIndex(0);
			getAtvp().setSelectedIndex(0);
			getAtvs().setSelectedIndex(0);
			getResAjustado().setText("");
			getResLucroReal().setText("");
			getResIrpj().setText("");
			getResCsll().setText("");
		}
	
	//Methods que nao estao funcionais, apenas para preencher no actionPerformed

	private void cadastrar() {
		getTextMensagem().setText("Botão de Cadastro Funcionando!");
	}

	private void calcularLucroReal() {
		getTextMensagem().setText("Botão de calcular Lucro Real Funcionando!");
	}

	private void calcularIrpj() {
		getTextMensagem().setText("Botão de Calcular IRPJ Funcionando!");
	}

	private void calcularCsll() {
		getTextMensagem().setText("Botão de Calcular CSLL	 Funcionando!");
	}
	
}
