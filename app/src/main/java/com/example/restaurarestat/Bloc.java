package com.example.restaurarestat;

public class Bloc {

    private String horaInici;
    private String temperatura;
    private String humitat;

    public Bloc(String horaInici, String temperatura, String tempsCalorFred){
        this.horaInici = horaInici;
        this.temperatura = temperatura;
        this.humitat = tempsCalorFred;

    }

    public String getHoraInici() {
        return horaInici;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getHumitat() {
        return humitat;
    }

    public void setHoraInici(String horaInici) {
        this.horaInici = horaInici;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public void setHumitat(String humitat) {
        this.humitat = humitat;
    }
}
