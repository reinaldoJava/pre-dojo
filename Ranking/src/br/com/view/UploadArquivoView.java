package br.com.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;

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

public class UploadArquivoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControllerRanking controller = new ControllerRanking();
	private Map<String, String> mapaDados = null;
	private List<Informacoes> listaInformacoes = null;
	private SortedSet<Entry<String, List<Integer>>> rankingUsuarios;
	JTable table = new JTable();

	public TableModel toTableModel(SortedSet<Entry<String, List<Integer>>> rankingUsuarios) {
		
		Map<String, List<Integer>> map = new LinkedHashMap<String, List<Integer>>();
		for (Entry<String, List<Integer>> entry : rankingUsuarios){
			map.put(entry.getKey(), entry.getValue());
		}
		DefaultTableModel model = new DefaultTableModel(new Object[] {
				"Usuario", "Matou", "Morreu" }, 0);
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			String[] temp = String.valueOf(entry.getValue()).replace("[", "")
					.replace("]", "").split(",");
			model.addRow(new Object[] { entry.getKey(), temp[0], temp[1] });
		}
		return model;
	}

	public UploadArquivoView() {
		super("Analise do Arquivo de Log");

		Container c = getContentPane();
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 300, 10);
		c.setLayout(layout);

		JButton btn = new JButton("Abrir Arquivo");
		final JLabel label = new JLabel("Teste");
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
					String nomeVencendor = rankingUsuarios.first().getKey();
					String armaPreferida = controller.IndentificarArmaPreferida(nomeVencendor, listaInformacoes);
					table.setModel(toTableModel(rankingUsuarios));
					table.setVisible(true);
					label.setText("A arma preferida de "+nomeVencendor+" é "+armaPreferida);
					label.setVisible(true);
				} else
					JOptionPane.showMessageDialog(null,
							"Voce nao selecionou nenhum arquivo.");
			}
		});
		
		c.add(btn);
		c.add(table);
		c.add(label);
		label.setVisible(false);
		JScrollPane scrollPane = new JScrollPane(table);
		c.add(scrollPane, BorderLayout.CENTER);
		setSize(550, 480);
		setVisible(true);
	}

	public static void main(String[] args) {
		UploadArquivoView app = new UploadArquivoView();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
