package com.example.kawka.myfly.models;

/**
 * Created by kawka on 29.05.2017.
 */

public class ZibConfirm {

    private String Id;
    private String NrDokumentu;
    private String IdUzytkownika;
    private String DataZapoznania;

    public ZibConfirm(String id, String nrDokumentu, String idUzytkownika, String dataZapoznania) {
        Id = id;
        NrDokumentu = nrDokumentu;
        IdUzytkownika = idUzytkownika;
        DataZapoznania = dataZapoznania;
    }
}
