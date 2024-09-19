package net.bcsoft.bcosft.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String BrowserClient;

    @Column
    private String IpAdress;

    @Column
    private String MacAdress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrowserClient() {
        return BrowserClient;
    }

    public void setBrowserClient(String browserClient) {
        BrowserClient = browserClient;
    }

    public String getIpAdress() {
        return IpAdress;
    }

    public void setIpAdress(String ipAdress) {
        IpAdress = ipAdress;
    }

    public String getMacAdress() {
        return MacAdress;
    }

    public void setMacAdress(String macAdress) {
        MacAdress = macAdress;
    }
}
