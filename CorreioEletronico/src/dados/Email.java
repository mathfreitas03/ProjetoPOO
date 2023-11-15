package dados;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Email {
    private int id;
    private String corpo;
    private String assunto;
    private String remetente;
    private String destinatario;
    private static int proximoId = 1;

    LocalDateTime data = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");

    public Email(String remetente, String destinatario, String corpo) {
        this.id = proximoId;
        proximoId++;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.corpo = corpo;
    }

    private String diaFormatado = String.format("%02d", data.getDayOfMonth());

    public String getDiaFormatado() {
        return diaFormatado;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public int getId() {
            return id;
    }
    
    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
    public String criptografar(String textoRecebido){
        String stringId = Integer.toString(id);
        char charId = stringId.charAt(stringId.length()-1);
        int unidadeId = Character.getNumericValue(charId);
        int deslocamento = unidadeId + Integer.parseInt(getDiaFormatado());
        String textoCriptografado = "";

        for(int n = textoRecebido.length() - 1; n >= 0; n--){
            char letra = textoRecebido.charAt(n);
            letra += deslocamento;
            textoCriptografado += letra;
        }
        return textoCriptografado;
    }

    public String descriptografar(String textoRecebido){
        String stringId = Integer.toString(id);
        char charId = stringId.charAt(stringId.length()-1);
        int unidadeId = Character.getNumericValue(charId);
        int deslocamento = unidadeId + Integer.parseInt(getDiaFormatado());
        String textoDescriptografado = "";

        for(int n = textoRecebido.length() - 1; n >= 0; n--){
            char letra = textoRecebido.charAt(n);
            letra -= deslocamento;
            textoDescriptografado += letra;
        }
        return textoDescriptografado;
    }

    public String getDataFormatada() {
        return data.format(formatter);
    }
}
