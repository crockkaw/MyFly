package com.example.kawka.myfly.models;

/**
 * Created by kawka on 3/6/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NalotAktualnyItems {

        @SerializedName("Nw")
        @Expose
        private Integer nw;
        @SerializedName("Nazwa")
        @Expose
        private String nazwa;
        @SerializedName("Pesel")
        @Expose
        private String pesel;
        @SerializedName("Ogolny")
        @Expose
        private String ogolny;
        @SerializedName("Dv")
        @Expose
        private String dv;
        @SerializedName("Di")
        @Expose
        private String di;
        @SerializedName("Nv")
        @Expose
        private String nv;
        @SerializedName("Ni")
        @Expose
        private String ni;
        @SerializedName("Zk")
        @Expose
        private String zk;


        public Integer getNw() {
            return nw;
        }

        public void setNw(Integer nw) {
            this.nw = nw;
        }

        public String getNazwa() {
            return nazwa;
        }

        public void setNazwa(String nazwa) {
            this.nazwa = nazwa;
        }

        public String getPesel() {
            return pesel;
        }

        public void setPesel(String pesel) {
            this.pesel = pesel;
        }

        public String getOgolny() {
            return ogolny;
        }

        public void setOgolny(String ogolny) {
            this.ogolny = ogolny;
        }

        public String getDv() {
            return dv;
        }

        public void setDv(String dv) {
            this.dv = dv;
        }

        public String getDi() {
            return di;
        }

        public void setDi(String di) {
            this.di = di;
        }

        public String getNv() {
            return nv;
        }

        public void setNv(String nv) {
            this.nv = nv;
        }

        public String getNi() {
            return ni;
        }

        public void setNi(String ni) {
            this.ni = ni;
        }

        public String getZk() {
            return zk;
        }

        public void setZk(String zk) {
            this.zk = zk;
        }



}


