package dados;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Email {
    private int id;
    private int idUsuario;
    private String corpo;
    private String assunto;
    private String remetente;
    private String destinatario;
    private int diaFormatado;
    
    public Email(){}

    public void setDiaFormatado(int diaFormatado) {
        this.diaFormatado = diaFormatado;
    }

    LocalDateTime data = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalDateTime getData(){
        return data;
    }

    public String getDataFormatada() {
        return data.format(formatter);
    }

    public Email(String remetente, String destinatario, String corpo) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.corpo = corpo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
            return id;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getDiaFormatado() {
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
   
    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
    public String criptografar(int id, String textoRecebido){
        String stringId = Integer.toString(id);
        char charId = stringId.charAt(stringId.length()-1);
        int unidadeId = Character.getNumericValue(charId);
        int deslocamento = unidadeId + getDiaFormatado();
        String textoCriptografado = "";

        for(int n = textoRecebido.length() - 1; n >= 0; n--){
            char letra = textoRecebido.charAt(n);
            letra += deslocamento;
            textoCriptografado += letra;
        }
        return textoCriptografado;
    }

    public String descriptografar(int id, String textoRecebido){
        String stringId = Integer.toString(id);
        char charId = stringId.charAt(stringId.length()-1);
        int unidadeId = Character.getNumericValue(charId);
        int deslocamento = unidadeId + getDiaFormatado();
        String textoDescriptografado = "";

        for(int n = textoRecebido.length() - 1; n >= 0; n--){
            char letra = textoRecebido.charAt(n);
            letra -= deslocamento;
            textoDescriptografado += letra;
        }
        return textoDescriptografado;
    }

}
