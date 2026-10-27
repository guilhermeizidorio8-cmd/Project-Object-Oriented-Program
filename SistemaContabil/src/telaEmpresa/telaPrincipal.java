package telaEmpresa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class telaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Paineis: titulo em cima, botoes centralizados no meio
	private JPanel painelTextos, painelCentro, painelBotaos;

	// Textos da tela
	private JLabel textTitulo, textInstrucao;

	// Um botao para cada tela de cadastro
	private JButton botaoMei, botaoSimples, botaoReal, botaoPresumido;

	public static void main(String[] args) {
		new telaPrincipal();
	}

	public telaPrincipal() {
		super("Sistema Contábil");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(10, 10));
		this.add(getPainelTextos(), BorderLayout.NORTH);
		this.add(getPainelCentro(), BorderLayout.CENTER);
		this.setVisible(true);
	}

	public JPanel getPainelTextos() {
		if (painelTextos == null) {
			painelTextos = new JPanel();
			painelTextos.setLayout(new GridLayout(2, 1, 0, 5));
			painelTextos.setBackground(Color.white);
			painelTextos.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
			painelTextos.add(getTextTitulo());
			painelTextos.add(getTextInstrucao());
		}
		return painelTextos;
	}


	public JPanel getPainelCentro() {
		if (painelCentro == null) {
			painelCentro = new JPanel();
			painelCentro.setLayout(new GridBagLayout());
			painelCentro.setBackground(Color.white);
			painelCentro.add(getPainelBotaos());
		}
		return painelCentro;
	}

	public JPanel getPainelBotaos() {
		if (painelBotaos == null) {
			painelBotaos = new JPanel();
			painelBotaos.setLayout(new GridLayout(4, 1, 0, 15));
			painelBotaos.setBackground(Color.white);
			painelBotaos.add(getBotaoMei());
			painelBotaos.add(getBotaoSimples());
			painelBotaos.add(getBotaoPresumido());
			painelBotaos.add(getBotaoReal());
		}
		return painelBotaos;
	}

	public JLabel getTextTitulo() {
		if (textTitulo == null) {
			textTitulo = new JLabel("Sistema Contábil", SwingConstants.CENTER);
			textTitulo.setFont(new Font("Arial", Font.BOLD, 26));
		}
		return textTitulo;
	}

	public JLabel getTextInstrucao() {
		if (textInstrucao == null) {
			textInstrucao = new JLabel("Escolha o tipo de empresa que deseja cadastrar:", SwingConstants.CENTER);
		}
		return textInstrucao;
	}



	public JButton getBotaoMei() {
		if (botaoMei == null) {
			botaoMei = new JButton("Empresa MEI");
			botaoMei.setPreferredSize(new Dimension(280, 50));
			botaoMei.addActionListener(this);
		}
		return botaoMei;
	}

	public JButton getBotaoSimples() {
		if (botaoSimples == null) {
			botaoSimples = new JButton("Empresa Simples Nacional");
			botaoSimples.setPreferredSize(new Dimension(280, 50));
			botaoSimples.addActionListener(this);
		}
		return botaoSimples;
	}

	public JButton getBotaoPresumido() {
		if (botaoPresumido == null) {
			botaoPresumido = new JButton("Empresa Lucro Presumido");
			botaoPresumido.setPreferredSize(new Dimension(280, 50));
			botaoPresumido.addActionListener(this);
		}
		return botaoPresumido;
	}

	public JButton getBotaoReal() {
		if (botaoReal == null) {
			botaoReal = new JButton("Empresa Lucro Real");
			botaoReal.setPreferredSize(new Dimension(280, 50));
			botaoReal.addActionListener(this);
		}
		return botaoReal;
	}

	// Cada botao abre a tela de cadastro correspondente (a tela principal continua aberta)
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getBotaoMei()) {
			new TelaEmpresaMEI();
		} else if (e.getSource() == getBotaoSimples()) {
			new TelaSimplesNacional();
		} else if (e.getSource() == getBotaoPresumido()) {
			new TelaEmpresaLucroPresumido();
		} else if (e.getSource() == getBotaoReal()) {
			new TelaEmpresaLucroReal();
		}
	}

}
