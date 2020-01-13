package com.example.restaurarestat;

public class Bloc {

    private String horaInici;
    private String temperatura;
    private String tempsCalorFred;

    public Bloc(String horaInici, String temperatura, String tempsCalorFred){
        this.horaInici = horaInici;
        this.temperatura = temperatura;
        this.tempsCalorFred = tempsCalorFred;

    }

    public String getHoraInici() {
        return horaInici;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getTempsCalorFred() {
        return tempsCalorFred;
    }

    public void setHoraInici(String horaInici) {
        this.horaInici = horaInici;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public void setTempsCalorFred(String tempsCalorFred) {
        this.tempsCalorFred = tempsCalorFred;
    }
}
