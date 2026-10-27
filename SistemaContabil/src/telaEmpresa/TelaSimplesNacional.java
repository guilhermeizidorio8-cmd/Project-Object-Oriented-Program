package telaEmpresa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TelaSimplesNacional extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Paineis: cada parte da tela fica em um painel separado
	private JPanel painelCampos, painelBotoes, painelMensagem, painelRodape;

	// Labels
	private JLabel textCnpj, textRs, textNF, textEST, textMUN, textATVP, textATVS,
			textFA, textFM, textDTA, textNFUN, textAE, textTRIB, textMensagem;

	// Campos de texto (alie e trib sao somente leitura: quem calcula e a classe)
	private JTextField cnpj, rs, nf, mun, fa, fm, dta, nfun, alie, trib;

	// Botoes
	private JButton cad, lim, calA, calT, ver;
	
	// ComboBox
		private JComboBox<String> est, atvp, atvs;

	// Opcoes dos ComboBox
	private static String[] estados = {
			"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
			"MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ",
			"RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
	};
	private static String[] principal = {"Comercio", "Industria", "Servico"};
	private static String[] secundario = {"Nenhuma", "Anexo 3", "Anexo 4", "Anexo 5"};

	public static void main(String[] args) {
		new TelaSimplesNacional();
	}

	// O construtor so monta a janela: cada componente e criado no seu proprio getter
	public TelaSimplesNacional() {
		super("Cadastro Empresa Simples Nacional");
		this.setSize(920, 820);
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
			painelCampos.add(getTextATVP());
			painelCampos.add(getAtvp());
			painelCampos.add(getTextATVS());
			painelCampos.add(getAtvs());
			painelCampos.add(getTextFA());
			painelCampos.add(getFa());
			painelCampos.add(getTextFM());
			painelCampos.add(getFm());
			painelCampos.add(getTextDTA());
			painelCampos.add(getDta());
			painelCampos.add(getTextNFUN());
			painelCampos.add(getNfun());
			painelCampos.add(getTextAE());
			painelCampos.add(getAlie());
			painelCampos.add(getTextTRIB());
			painelCampos.add(getTrib());
		}
		return painelCampos;
	}

	// Painel dos botoes
	public JPanel getPainelBotoes() {
		if (painelBotoes == null) {
			painelBotoes = new JPanel();
			painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Faz os botões ficarem alinhas em uma linha de 
			painelBotoes.setBackground(Color.white);
			painelBotoes.add(getCad());
			painelBotoes.add(getLim());
			painelBotoes.add(getCalA());
			painelBotoes.add(getCalT());
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

	public JLabel getTextATVP() {
		if (textATVP == null) {
			textATVP = new JLabel("Atividade Principal: ");
		}
		return textATVP;
	}

	public JLabel getTextATVS() {
		if (textATVS == null) {
			textATVS = new JLabel("Atividade Secundária (Anexo): ");
		}
		return textATVS;
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
			textDTA = new JLabel("Data de Início (dd/MM/aaaa): ");
		}
		return textDTA;
	}

	public JLabel getTextNFUN() {
		if (textNFUN == null) {
			textNFUN = new JLabel("Número de Funcionários: ");
		}
		return textNFUN;
	}

	public JLabel getTextAE() {
		if (textAE == null) {
			textAE = new JLabel("Alíquota Efetiva: ");
		}
		return textAE;
	}

	public JLabel getTextTRIB() {
		if (textTRIB == null) {
			textTRIB = new JLabel("Tributo Mensal: ");
		}
		return textTRIB;
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

	public JTextField getNfun() {
		if (nfun == null) {
			nfun = new JTextField();
		}
		return nfun;
	}

	public JTextField getAlie() {
		if (alie == null) {
			alie = new JTextField();
			alie.setEditable(false);
		}
		return alie;
	}

	public JTextField getTrib() {
		if (trib == null) {
			trib = new JTextField();
			trib.setEditable(false);
		}
		return trib;
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
			atvs = new JComboBox<>(secundario); 
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

	public JButton getCalA() {
		if (calA == null) {
			calA = new JButton("Calcular Alíquota Efetiva");
			calA.addActionListener(this);
		}
		return calA;
	}

	public JButton getCalT() {
		if (calT == null) {
			calT = new JButton("Calcular Tributo Mensal");
			calT.addActionListener(this);
		}
		return calT;
	}

	public JButton getVer() {
		if (ver == null) {
			ver = new JButton("Verificar Limite");
			ver.addActionListener(this);
		}
		return ver;
	}

	// Chamado quando um botao (ou o ComboBox da atividade principal) e acionado
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getCad()) {
			cadastrar();
		} else if (e.getSource() == getLim()) {
			limpar();
		} else if (e.getSource() == getCalA()) {
			calcularAliquota();
		} else if (e.getSource() == getCalT()) {
			calcularTributo();
		} else if (e.getSource() == getVer()) {
			verificarLimite();
		}
	}
	
	// Limpa todos os campos
	private void limpar() {
		getCnpj().setText("");
		getRs().setText("");
		getNf().setText("");
		getMun().setText("");
		getFa().setText("");
		getFm().setText("");
		getDta().setText("");
		getNfun().setText("");
		getAlie().setText("");
		getTrib().setText("");
		getEst().setSelectedIndex(0);
		getAtvp().setSelectedIndex(0);
		getAtvs().setSelectedIndex(0);

	}

	//Methods que nao estao funcionais, apenas para preencher no actionPerformed
	private void cadastrar() {
		getTextMensagem().setText("Botão de Cadastro Funcionando!");
	}

	private void calcularAliquota() {
		getTextMensagem().setText("Botão de Calcular Aliquiota Funcionando!");
	}

	private void calcularTributo() {
		getTextMensagem().setText("Botão de calcular Tributo Funcionando!");
	}

	private void verificarLimite() {
		getTextMensagem().setText("Botão de Verificar Limite Funcionando!");
	}
	
}
