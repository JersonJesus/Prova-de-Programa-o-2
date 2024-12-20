/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.sql.*;
import controller.ModuloConexao;
import javax.swing.JOptionPane;

/**
 *
 * @author Jerson Vndré
 */
public class FormularioFuncionario extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form FormularioFuncionario
     */
    public FormularioFuncionario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void fazerConsulta() {
        String sql = "select * from tbfuncionarios where idusuario = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, campoTextoIdUsuario.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                campoTextoNome.setText(rs.getString(2));
                campoTextoTelefone.setText(rs.getString(3));
                campoTextoLogin.setText(rs.getString(4));
                campoTextoSenha.setText(rs.getString(5));
                conboBoxPerfil.setSelectedItem(rs.getString(6));
            } else if ((campoTextoIdUsuario.getText().isEmpty())
                    || (campoTextoNome.getText().isEmpty())
                    || (campoTextoLogin.getText().isEmpty())
                    || (campoTextoSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha o campo ID");
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                campoTextoNome.setText(null);
                campoTextoTelefone.setText(null);
                campoTextoLogin.setText(null);
                campoTextoSenha.setText(null);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void criarUsuario() {
        String sql = "insert into tbfuncionarios(idusuario,usuario,telefone,login,senha,perfil) values(?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            conexao = ModuloConexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, campoTextoIdUsuario.getText());
            pst.setString(2, campoTextoNome.getText());
            pst.setString(3, campoTextoTelefone.getText());
            pst.setString(4, campoTextoLogin.getText());
            String captura = new String(campoTextoSenha.getText());
            pst.setString(5, captura);
            pst.setString(6, conboBoxPerfil.getSelectedItem().toString());

            if ((campoTextoIdUsuario.getText().isEmpty())
                    || (campoTextoNome.getText().isEmpty())
                    || (campoTextoLogin.getText().isEmpty())
                    || (campoTextoSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
                    campoTextoIdUsuario.setText(null);
                    campoTextoNome.setText(null);
                    campoTextoTelefone.setText(null);
                    campoTextoLogin.setText(null);
                    campoTextoSenha.setText(null);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,  "Usuário já cadastrado");
        }

    }
    
    private void alterarUsuario() {

        String sql = "update tbfuncionarios set usuario=?,telefone=?,login=?,perfil=? where idusuario=?";
        try {
            conexao = ModuloConexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, campoTextoNome.getText());
            pst.setString(2, campoTextoTelefone.getText());
            pst.setString(3, campoTextoLogin.getText());
            pst.setString(4, conboBoxPerfil.getSelectedItem().toString());
            pst.setString(5, campoTextoIdUsuario.getText());
            if ((campoTextoIdUsuario.getText().isEmpty())
                    || (campoTextoNome.getText().isEmpty())
                    || (campoTextoLogin.getText().isEmpty())
                    || (campoTextoSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                    campoTextoIdUsuario.setText(null);
                    campoTextoNome.setText(null);
                    campoTextoTelefone.setText(null);
                    campoTextoLogin.setText(null);
                    campoTextoSenha.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    private void removerUsuario() {
        int confirma = JOptionPane.showConfirmDialog(null, 
                "Deseja remover este usuário?", "Atenção!", 
                JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbfuncionarios where idusuario=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, campoTextoIdUsuario.getText());
                int apagado = pst.executeUpdate();
                
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário remaovido");
                    campoTextoIdUsuario.setText(null);
                    campoTextoNome.setText(null);
                    campoTextoTelefone.setText(null);
                    campoTextoLogin.setText(null);
                    campoTextoSenha.setText(null);
                    
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        conboBoxPerfil = new javax.swing.JComboBox<>();
        campoTextoIdUsuario = new javax.swing.JTextField();
        campoTextoNome = new javax.swing.JTextField();
        campoTextoLogin = new javax.swing.JTextField();
        campoTextoSenha = new javax.swing.JTextField();
        campoTextoTelefone = new javax.swing.JTextField();
        botaoApagar = new javax.swing.JButton();
        botaoCriar = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JButton();
        botaoPesquisar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Funcuinário");
        setPreferredSize(new java.awt.Dimension(380, 0));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Nome");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Login");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Senha");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Perfil");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Telefone");

        conboBoxPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));

        campoTextoIdUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoIdUsuarioActionPerformed(evt);
            }
        });

        campoTextoSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoSenhaActionPerformed(evt);
            }
        });

        botaoApagar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botaoApagar.setText("APAGAR");
        botaoApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoApagarActionPerformed(evt);
            }
        });

        botaoCriar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botaoCriar.setText("CRIAR");
        botaoCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCriarActionPerformed(evt);
            }
        });

        botaoAtualizar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botaoAtualizar.setText("ATUALIZAR");
        botaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarActionPerformed(evt);
            }
        });

        botaoPesquisar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        botaoPesquisar.setText("PESQUISAR");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoTextoIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoPesquisar))
                            .addComponent(campoTextoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botaoCriar)
                                .addGap(33, 33, 33)
                                .addComponent(botaoAtualizar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoTextoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(conboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoTextoSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoTextoLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoApagar, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoTextoIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoTextoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(campoTextoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(campoTextoTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoTextoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(conboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCriar)
                    .addComponent(botaoAtualizar)
                    .addComponent(botaoApagar))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        setBounds(0, 0, 380, 285);
    }// </editor-fold>//GEN-END:initComponents

    private void campoTextoIdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoIdUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoIdUsuarioActionPerformed

    private void campoTextoSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoSenhaActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
       fazerConsulta();
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void botaoCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCriarActionPerformed
        criarUsuario();
    }//GEN-LAST:event_botaoCriarActionPerformed

    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed
        alterarUsuario();
    }//GEN-LAST:event_botaoAtualizarActionPerformed

    private void botaoApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoApagarActionPerformed
       removerUsuario();
    }//GEN-LAST:event_botaoApagarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoApagar;
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoCriar;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JTextField campoTextoIdUsuario;
    private javax.swing.JTextField campoTextoLogin;
    private javax.swing.JTextField campoTextoNome;
    private javax.swing.JTextField campoTextoSenha;
    private javax.swing.JTextField campoTextoTelefone;
    private javax.swing.JComboBox<String> conboBoxPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
