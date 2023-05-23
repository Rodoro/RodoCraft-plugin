package bot.bot.model;

import java.util.List;

public class Guild {
    private final int id;
    private String name;
    private String prefix;
    private String description;
    private boolean isPrivate;
    private String img;
    private String idOwner;
    private List<String> memberIDs;
    private String data;
    private int lvl;
    private int exp;
    private String idMemberRole;
    private String idSorocRole;
    private String idStarRole;
    private String idOwnerRole;
    private String idTChannel;
    private String idVChannel;
    private String idTeChannel;

    private List<String> sorocIDs;

    private List<String> starIDs;

    private List<String> tasksList;

    public Guild(int id, String name, String prefix, String description, boolean isPrivate, String img, String idOwner, List<String> memberIDs, String data, int lvl, int exp, String idMemberRole, String idSorocRole, String idStarRole, String idOwnerRole, String idTChannel, String idVChannel, String idTeChannel, List<String> sorocIDs, List<String> starIDs, List<String> tasksList) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.description = description;
        this.isPrivate = isPrivate;
        this.img = img;
        this.idOwner = idOwner;
        this.memberIDs = memberIDs;
        this.data = data;
        this.lvl = lvl;
        this.exp = exp;
        this.idMemberRole = idMemberRole;
        this.idSorocRole = idSorocRole;
        this.idStarRole = idStarRole;
        this.idOwnerRole = idOwnerRole;
        this.idTChannel = idTChannel;
        this.idVChannel = idVChannel;
        this.idTeChannel = idTeChannel;
        this.setSorocIDs(sorocIDs);
        this.setStarIDs(starIDs);
        this.setTasksList(tasksList);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public List<String> getMemberIDs() {
        return memberIDs;
    }

    public void setMemberIDs(List<String> memberIDs) {
        this.memberIDs = memberIDs;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getIdMemberRole() {
        return idMemberRole;
    }

    public void setIdMemberRole(String idMemberRole) {
        this.idMemberRole = idMemberRole;
    }

    public String getIdSorocRole() {
        return idSorocRole;
    }

    public void setIdSorocRole(String idSorocRole) {
        this.idSorocRole = idSorocRole;
    }

    public String getIdStarRole() {
        return idStarRole;
    }

    public void setIdStarRole(String idStarRole) {
        this.idStarRole = idStarRole;
    }

    public String getIdOwnerRole() {
        return idOwnerRole;
    }

    public void setIdOwnerRole(String idOwnerRole) {
        this.idOwnerRole = idOwnerRole;
    }

    public String getIdTChannel() {
        return idTChannel;
    }

    public void setIdTChannel(String idTChannel) {
        this.idTChannel = idTChannel;
    }

    public String getIdVChannel() {
        return idVChannel;
    }

    public void setIdVChannel(String idVChannel) {
        this.idVChannel = idVChannel;
    }

    public String getIdTeChannel() {
        return idTeChannel;
    }

    public void setIdTeChannel(String idTeChannel) {
        this.idTeChannel = idTeChannel;
    }

    public List<String> getSorocIDs() {
        return sorocIDs;
    }

    public void setSorocIDs(List<String> sorocIDs) {
        this.sorocIDs = sorocIDs;
    }

    public List<String> getStarIDs() {
        return starIDs;
    }

    public void setStarIDs(List<String> starIDs) {
        this.starIDs = starIDs;
    }

    public List<String> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<String> tasksList) {
        this.tasksList = tasksList;
    }
}
