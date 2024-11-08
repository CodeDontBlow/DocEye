package org.codedontblow.gui;

import org.codedontblow.dao.CandidatoDAO;
import org.codedontblow.model.Candidato;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatabaseManagement extends JFrame{
    private JPanel TituloDatabase;
    private JButton btnCadastrar;
    private JButton btnLimpar;
    private JButton btnDeletar;
    private JButton btnAtualizar;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel Buttons;
    private JButton btnSair;
    private JPanel MainPanel;
    private JTable tableCandidato;
    private JLabel nomeDaTabela;


    public DatabaseManagement() {
        setContentPane(MainPanel);
        setTitle("Cadastro Candidato");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        setVisible(true);


        //Configurações para o botão de sair
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Configurações para o botão de limpar os textos dos campos
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
            }
        });

        //Configurações para o botão de cadastrar as informações no banco de dados pelas informações passadas
        //nos campos
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Candidato candidato = new Candidato();
                    candidato.setNome(textField2.getText());
                    candidato.setTipoDoc(textField3.getText());

                if(textField2.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "O campos não possuem nada");
                }

                else{
                    CandidatoDAO dao = new CandidatoDAO();
                    dao.cadastrar(candidato);
                    JOptionPane.showMessageDialog(null, "Candidato " + textField2.getText() + " inserido com sucesso");
                }
            }
        });


        btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Candidato candidatoDelete = new Candidato();
                candidatoDelete.setUniqueIDCandidato(Integer.parseInt(textField1.getText()));
                candidatoDelete.setNome(textField2.getText());
                candidatoDelete.setTipoDoc(textField3.getText());

                if(textField2.getText().isBlank()){
                    JOptionPane.showMessageDialog(null, "Nada foi apagado");
                }

                else{
                    CandidatoDAO daoDelete = new CandidatoDAO();
                    daoDelete.deletar(candidatoDelete);
                    JOptionPane.showMessageDialog(null, "Candidato " + textField2.getText() + " apagado com sucesso");
                }
            }
        });
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Candidato candidatoAtualiza = new Candidato();
                candidatoAtualiza.setUniqueIDCandidato(Integer.parseInt(textField1.getText()));
                candidatoAtualiza.setNome(textField2.getText());
                candidatoAtualiza.setTipoDoc(textField3.getText());

                if((textField1.getText().isBlank()) && (textField2.getText().isBlank()) && (textField3.getText().isBlank())){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                }

                else{
                    try{
                        CandidatoDAO daoUpdate = new CandidatoDAO();
                        daoUpdate.atualizar(candidatoAtualiza);
                        JOptionPane.showMessageDialog(null, "Candidato " + candidatoAtualiza.getNome() + " foi atualizado com sucesso.");
                    }catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID deve ser um número inteiro.");
                    }
                }
            }
        });
    }

    public static void main(String[] args){
        new DatabaseManagement();
    }
}


