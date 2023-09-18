/*Disclaimer: esta classe foi projetada para rodar em versões antigas do Java, conforme
recomendação do professor de que seja utilizada a versão 1.8, isso pode ocasionar em warnings
caso o código seja rodado em versões acima da 8.0.*/
package dados;

import java.util.Date;

public class Email {
    private int id;
    private String corpo;
    private String remetente;
    private String destinatario;
    private static int proximoId = 1;

    long timestamp = System.currentTimeMillis();
    Date data = new Date(timestamp);
    public int dia = data.getDate();
    private String diaFormatado = String.format("%02d", dia);
    public String getDiaFormatado() {
        return diaFormatado;
    }

    public void setDiaFormatado(String diaFormatado) {
        this.diaFormatado = diaFormatado;
    }

    public int mes = data.getMonth() + 1;
    private String mesFormatado = String.format("%02d", mes);

    public String getMesFormatado() {
        return mesFormatado;
    }

    public void setMesFormatado(String mesFormatado) {
        this.mesFormatado = mesFormatado;
    }

    public int ano = data.getYear() + 1900;
    public int horas = data.getHours();
    private String horaFormatada = String.format("%02d", horas);
    public String getHoraFormatada() {
        return horaFormatada;
    }

    public void setHoraFormatada(String horaFormatada) {
        this.horaFormatada = horaFormatada;
    }

    public int minutos = data.getMinutes();
    private String minutosFormatados = String.format("%02d", minutos);

    public String getMinutosFormatados() {
        return minutosFormatados;
    }

    public void setMinutosFormatados(String minutosFormatados) {
        this.minutosFormatados = minutosFormatados;
    }

    public Email(String remetente, String destinatario, String corpo) {
        this.id = proximoId;
        proximoId++;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.corpo = corpo;
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

    public void setId(int id) {
            this.id = id;
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
}
