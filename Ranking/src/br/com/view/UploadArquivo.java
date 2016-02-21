package br.com.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.com.controller.ControllerRanking;
import br.com.model.Informacoes;

public class UploadArquivo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControllerRanking controller = new ControllerRanking();
	private Map<String, String> mapaDados = null;
	private List<Informacoes> listaInformacoes = null;
	private Map<String, List<Integer>> rankingUsuarios;
	JTable table = new JTable();

	public static TableModel toTableModel(Map<?,?> map) {
	    DefaultTableModel model = new DefaultTableModel(
	        new Object[] { "Usuario", "Matou", "Morreu" }, 0
	    );
	    for (Map.Entry<?,?> entry : map.entrySet()) {
	    	String[] temp = String.valueOf(entry.getValue()).replace("[", "").replace("]", "").split(",");
	        model.addRow(new Object[] { entry.getKey(), temp[0],temp[1]});
	    }
	    return model;
	}

	public UploadArquivo() {
		super("Analise do Arquivo de Log");

		Container c = getContentPane();
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 300, 10);
		c.setLayout(layout);

		JButton btn = new JButton("Abrir Arquivo");
		final JLabel label = new JLabel("Teste");

		Object columnNames[] = { "Column One", "Column Two", "Column Three" };
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int res = fc.showOpenDialog(null);
				if (res == JFileChooser.APPROVE_OPTION) {
					try {
						mapaDados = controller.gerarMapaDados(new FileReader(fc
								.getSelectedFile()));
						listaInformacoes = controller
								.retornarListaInformacoes(mapaDados);
						rankingUsuarios = controller
								.gerarRankingUsuarios(listaInformacoes);
					} catch (FileNotFoundException e1) {
						JOptionPane
								.showMessageDialog(null, "Arquivo Invalido.");
					}
					table.setModel(toTableModel(rankingUsuarios));
					table.setVisible(true);
					label.setText("Reinaldo Usando a M16");
					label.setVisible(true);
				} else
					JOptionPane.showMessageDialog(null,
							"Voce nao selecionou nenhum arquivo.");
			}
		});

		//table.setVisible(false);
		c.add(btn);
		c.add(table);
		c.add(label);
		label.setVisible(false);
		JScrollPane scrollPane = new JScrollPane(table);
		c.add(scrollPane, BorderLayout.CENTER);
		setSize(550, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		UploadArquivo app = new UploadArquivo();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
