package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Formatter;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;

import algorithm.HuffmanAlgorithm;
import algorithm.ManipulateTextFile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import main.Main;

public class Controller implements Initializable {

	private String backupTxt;
	
	@FXML
    private Accordion accordion;

    @FXML
    private JFXTextArea txt;

    @FXML
    private JFXCheckBox checkBox;

    @FXML
    private JFXButton btnComprimir;

    @FXML
    private Label txtTamNormal;

    @FXML
    private Label txtTamComprimido;

    @FXML
    private Label txtPorcentagem;
    
    @FXML
    private Label txtCodificado;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		checkBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(checkBox.isSelected()) {
					backupTxt = txt.getText();
					txt.setText(Main.textoPadrao);
					txt.setDisable(true);
				} else {
					txt.setText(backupTxt);
					txt.setDisable(false);
				}
			}
		});
		
		btnComprimir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(validate()) {
					HuffmanAlgorithm huffmanAlgorithm = new HuffmanAlgorithm();
					String encoded = huffmanAlgorithm.coding(txt.getText());
					
					try {
						ManipulateTextFile.saveInText(Main.NOME_ARQUIVO_ORIGINAL, txt.getText());
						ManipulateTextFile.saveInBinary(Main.NOME_DO_ARQUIVO_COMPRIMIDO, encoded);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					float originalSize = (int) new File(Main.NOME_ARQUIVO_ORIGINAL + ".txt").length();
					float compressedSize = ManipulateTextFile.encodeToBinary(encoded).length;
					
					float rate = 100 - (compressedSize * 100 / originalSize);
					
					txtTamNormal.setText((originalSize * 8) + " bits : " + originalSize + " bytes");
					txtTamComprimido.setText((compressedSize * 8) + " bits : " + compressedSize + " bytes");
					txtPorcentagem.setText("Comprimido é " + new Formatter(Locale.US).format("%.2f", rate) + "% menor que o original");
					txtCodificado.setText(encoded);
					
					Alert alert = new Alert(AlertType.INFORMATION, "Dois arquivos foram criados na pasta do projeto referente ao texto codificado original e ao criptografado.", ButtonType.OK);
					alert.setTitle("Operação concluída");
					alert.setHeaderText("A operação foi concluída com êxito");
					alert.showAndWait();
					
					System.out.println("Arquivo comprimido decodificado >> \n\t" + huffmanAlgorithm.decoding(encoded) + "\n\n");
				}
			}
		});
		
		accordion.setExpandedPane(accordion.getPanes().get(2));
	}
	
	private boolean validate() {
		if(txt.getText().length() >= 2)
			return true;
		
		Alert alert = new Alert(AlertType.ERROR, "Digite um texto válido para efetuar a compressão.", ButtonType.OK);
		alert.setTitle("404 - Fatal error");
		alert.setHeaderText("O texto inserido não é valido");
		alert.showAndWait();
		
		return false;
	}
	
}
