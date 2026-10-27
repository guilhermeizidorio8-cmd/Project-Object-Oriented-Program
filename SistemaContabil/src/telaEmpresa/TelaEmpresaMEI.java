package telaEmpresa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class TelaEmpresaMEI extends JFrame implements ActionListener {

	// Identificador de versao exigido por herdar de JFrame (Serializable); nao afeta o funcionamento do programa
	private static final long serialVersionUID = 1L;

	// Paineis: cada parte da tela fica em um painel separado
	private JPanel painelCampos, painelBotoes, painelMensagem, painelRodape;

	// Labels
	private JLabel textCnpj, textRs, textNF, textEST, textMUN, textOCP, textOCS,
			textFA, textFM, textDTA, textFUNC, textDAS, textMensagem;

	// Campos de texto (das e somente leitura: quem calcula e a classe)
	private JTextField cnpj, rs, nf, mun, fa, fm, dta, das;
	
	// Botoes
		private JButton cad, lim, calDas, ver;

	// Checkbox (indicador de existencia de funcionario, ja que e um valor boolean)
	private JCheckBox funcionario;
	
	// ComboBox
		private JComboBox<String> est, ocp, ocs; 

	// Opcoes dos ComboBox
	private static String[] estados = {
			"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
			"MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ",
			"RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
	};
	private static String[] principal = {"Comercio", "Industria", "Servico"};
	private static String[] secundaria = {"Nenhuma", "Comercio", "Industria", "Servico"};


	public static void main(String[] args) {
		new TelaEmpresaMEI();
	}

	public TelaEmpresaMEI() {
		super("Cadastro Empresa MEI");
		this.setSize(900, 760);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout(10, 10));
		this.add(getPainelCampos(), BorderLayout.CENTER);
		this.add(getPainelRodape(), BorderLayout.SOUTH);
		this.setVisible(true);
	}

	// Painel dos campos: duas colunas (texto e campo)
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
			painelCampos.add(getTextOCP());
			painelCampos.add(getOcp());
			painelCampos.add(getTextOCS());
			painelCampos.add(getOcs());
			painelCampos.add(getTextFA());
			painelCampos.add(getFa());
			painelCampos.add(getTextFM());
			painelCampos.add(getFm());
			painelCampos.add(getTextDTA());
			painelCampos.add(getDta());
			painelCampos.add(getTextFUNC());
			painelCampos.add(getFuncionario());
			painelCampos.add(getTextDAS());
			painelCampos.add(getDas());
		}
		return painelCampos;
	}

	// Painel dos botoes
	public JPanel getPainelBotoes() {
		if (painelBotoes == null) {
			painelBotoes = new JPanel();
			painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
			painelBotoes.setBackground(Color.white);
			painelBotoes.add(getCad());
			painelBotoes.add(getLim());
			painelBotoes.add(getCalDas());
			painelBotoes.add(getVer());
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


	// Painel do rodape: botoes em cima, mensagem embaixo
	public JPanel getPainelRodape() {
		if (painelRodape == null) {
			painelRodape = new JPanel();
			painelRodape.setLayout(new GridLayout(2, 1));
			painelRodape.add(getPainelBotoes());
			painelRodape.add(getPainelMensagem());
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

	public JLabel getTextOCP() {
		if (textOCP == null) {
			textOCP = new JLabel("Ocupação Principal: ");
		}
		return textOCP;
	}

	public JLabel getTextOCS() {
		if (textOCS == null) {
			textOCS = new JLabel("Ocupação Secundária: ");
		}
		return textOCS;
	}

	public JLabel getTextFA() {
		if (textFA == null) {
			textFA = new JLabel("Faturamento Anual: ");
		}
		return textFA;
	}

	public JLabel getTextFM() {
		if (textFM == null) {
			textFM = new JLabel("Faturamento Mensal: ");
		}
		return textFM;
	}

	public JLabel getTextDTA() {
		if (textDTA == null) {
			textDTA = new JLabel("Data de Abertura (dd/MM/aaaa): ");
		}
		return textDTA;
	}

	public JLabel getTextFUNC() {
		if (textFUNC == null) {
			textFUNC = new JLabel("Possui Funcionário? ");
		}
		return textFUNC;
	}

	public JLabel getTextDAS() {
		if (textDAS == null) {
			textDAS = new JLabel("Valor Mensal do DAS: ");
		}
		return textDAS;
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

	public JTextField getFa() {
		if (fa == null) {
			fa = new JTextField();
			
		}
		return fa;
	}

	public JTextField getFm() {
		if (fm == null) {
			fm = new JTextField();
			
		}
		return fm;
	}

	public JTextField getDta() {
		if (dta == null) {
			dta = new JTextField();
			
		}
		return dta;
	}

	public JTextField getDas() {
		if (das == null) {
			das = new JTextField();
			das.setEditable(false);
		}
		return das;
	}

	public JComboBox<String> getEst() {
		if (est == null) {
			est = new JComboBox<>(estados);
		}
		return est;
	}

	public JComboBox<String> getOcp() {
		if (ocp == null) {
			ocp = new JComboBox<>(principal);
		}
		return ocp;
	}

	public JComboBox<String> getOcs() {
		if (ocs == null) {
			ocs = new JComboBox<>(secundaria);
		}
		return ocs;
	}

	public JCheckBox getFuncionario() {
		if (funcionario == null) {
			funcionario = new JCheckBox();
		}
		return funcionario;
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

	public JButton getCalDas() {
		if (calDas == null) {
			calDas = new JButton("Calcular DAS");
			calDas.addActionListener(this);
		}
		return calDas;
	}

	public JButton getVer() {
		if (ver == null) {
			ver = new JButton("Verificar Limite");
			ver.addActionListener(this);
		}
		return ver;
	}


	// Chamado quando um botao e acionado
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getCad()) {
			cadastrar();
		} else if (e.getSource() == getLim()) {
			limpar();
		} else if (e.getSource() == getCalDas()) {
			calcularDas();
		} else if (e.getSource() == getVer()) {
			verificarLimite();
		}
	}
	
	// Limpa todos os campos e esquece a empresa cadastrada
		private void limpar() {
			getCnpj().setText("");
			getRs().setText("");
			getNf().setText("");
			getMun().setText("");
			getFa().setText("");
			getFm().setText("");
			getDta().setText("");
			getDas().setText("");
			getEst().setSelectedIndex(0);
			getOcp().setSelectedIndex(0);
			getOcs().setSelectedIndex(0);
			getFuncionario().setSelected(false);
		}

	//Methods que nao estao funcionais, apenas para preencher no actionPerformed
	private void cadastrar() {
		getTextMensagem().setText("Botão de Cadastro Funcionando!");
	}

	private void calcularDas() {
		getTextMensagem().setText("Botão de Calcular DAS Funcionando!");
	}

	private void verificarLimite() {
		getTextMensagem().setText("Botão de Verificar Limite Funcionando!");
	}

}
