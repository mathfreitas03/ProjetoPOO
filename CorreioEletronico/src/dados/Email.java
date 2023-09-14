package dados;

public class Email {
    private String corpo;
    private String remetente;
    private String destinatario;
    private int hora;
    private int minutos;
    private int dia;
    private int mes;
    private int ano;

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

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void enviarEmail() {
        System.out.println("Email enviado de: " + remetente);
        System.out.println("Para: " + destinatario);
        System.out.println("Data do Envio: " + dia + "/" + mes + "/" + ano);
        System.out.println("Hora do Envio: " + hora + ":" + minutos);
        System.out.println("Corpo do Email:");
        System.out.println(corpo);
    }

}
