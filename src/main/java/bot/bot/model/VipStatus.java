package bot.bot.model;

public class VipStatus {
    private String memberId;
    private int money;
    private int exp;
    private int lvl;

    public VipStatus (String memberId, int money, int exp, int lvl){
        this.memberId = memberId;
        this.money = money;
        this.exp = exp;
        this.lvl = lvl;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
