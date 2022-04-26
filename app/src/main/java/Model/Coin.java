package Model;

public class Coin {
    private String code, codein, name, timestamp, create_date;
    private Float high, low, varBid, pctChange, bid, ask;

    public Coin(String code, String codein, String name, String timestamp, String create_date, Float high, Float low, Float varBid, Float pctChange, Float bid, Float ask) {
        this.code = code;
        this.codein = codein;
        this.name = name;
        this.timestamp = timestamp;
        this.create_date = create_date;
        this.high = high;
        this.low = low;
        this.varBid = varBid;
        this.pctChange = pctChange;
        this.bid = bid;
        this.ask = ask;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodein() {
        return codein;
    }

    public void setCodein(String codein) {
        this.codein = codein;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public Float getHigh() {
        return high;
    }

    public void setHigh(Float high) {
        this.high = high;
    }

    public Float getLow() {
        return low;
    }

    public void setLow(Float low) {
        this.low = low;
    }

    public Float getVarBid() {
        return varBid;
    }

    public void setVarBid(Float varBid) {
        this.varBid = varBid;
    }

    public Float getPctChange() {
        return pctChange;
    }

    public void setPctChange(Float pctChange) {
        this.pctChange = pctChange;
    }

    public Float getBid() {
        return bid;
    }

    public void setBid(Float bid) {
        this.bid = bid;
    }

    public Float getAsk() {
        return ask;
    }

    public void setAsk(Float ask) {
        this.ask = ask;
    }
}
