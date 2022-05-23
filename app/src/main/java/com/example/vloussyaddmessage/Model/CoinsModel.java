package com.example.vloussyaddmessage.Model;

public class CoinsModel {
    public String idCoin;
    public String coinName;
    public double coinSale;
    public double coinBuy;

    public CoinsModel() {
    }


    public CoinsModel(String idCoin, String coinName, double coinSale, double coinbuy) {
        this.idCoin = idCoin;
        this.coinName = coinName;
        this.coinSale = coinSale;
        this.coinBuy = coinbuy;
    }


    public String getIdCoin() {
        return idCoin;
    }

    public void setIdCoin(String idCoin) {
        this.idCoin = idCoin;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public double getCoinSale() {
        return coinSale;
    }

    public void setCoinSale(double coinSale) {
        this.coinSale = coinSale;
    }

    public double getCoinBuy() {
        return coinBuy;
    }

    public void setCoinBuy(double coinBuy) {
        this.coinBuy = coinBuy;
    }
}