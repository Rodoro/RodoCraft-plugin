package bot.bot.database;
import bot.bot.Secret;
import bot.bot.model.Guild;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.List;

public class GuildDB {
    private MongoClient mongoClient;
    private MongoCollection<Document> collection;

    private static GuildDB instance = null;

    public static GuildDB getInstance() {
        if (instance == null) {
            instance = new GuildDB();
        }

        return instance;
    }
    public void GuildDAO() {
        MongoClientURI uri = new MongoClientURI(Secret.tokenDB);
        mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase(uri.getDatabase());
        collection = database.getCollection("Guild");
    }

    public void addGuild(Guild guild) {
        Document document = new Document()
                .append("id", guild.getId())
                .append("name", guild.getName())
                .append("prefix", guild.getPrefix())
                .append("description", guild.getDescription())
                .append("isPrivate", guild.isPrivate())
                .append("img", guild.getImg())
                .append("idOwner", guild.getIdOwner())
                .append("memberIds", guild.getMemberIDs())
                .append("data", guild.getData())
                .append("lvl", guild.getLvl())
                .append("exp", guild.getExp())
                .append("idMemberRole", guild.getIdMemberRole())
                .append("idSorocRole", guild.getIdSorocRole())
                .append("idStarRole", guild.getIdStarRole())
                .append("idOwnerRole", guild.getIdOwnerRole())
                .append("idTChannel", guild.getIdTChannel())
                .append("idVChannel", guild.getIdVChannel())
                .append("idTeChannel", guild.getIdTeChannel())
                .append("sorocIDs", guild.getSorocIDs())
                .append("starIDs", guild.getStarIDs())
                .append("tasksList", guild.getTasksList());
        collection.insertOne(document);
    }

    public Guild getGuildByName(String name) {
        Document document = collection.find(Filters.eq("name", name)).first();
        if (document == null) {
            return null;
        }
        return new Guild(
                document.getInteger("id"),
                document.getString("name"),
                document.getString("prefix"),
                document.getString("description"),
                document.getBoolean("isPrivate"),
                document.getString("img"),
                document.getString("idOwner"),
                (List<String>) document.get("memberIds"),
                document.getString("data"),
                document.getInteger("lvl"),
                document.getInteger("exp"),
                document.getString("idMemberRole"),
                document.getString("idSorocRole"),
                document.getString("idStarRole"),
                document.getString("idOwnerRole"),
                document.getString("idTChannel"),
                document.getString("idVChannel"),
                document.getString("idTeChannel"),
                (List<String>) document.get("sorocIDs"),
                (List<String>) document.get("starIDs"),
                (List<String>) document.get("tasksList"));
    }

    public boolean guildExistsName(String name) {
        Document document = collection.find(Filters.eq("name", name)).first();
        return document != null;
    }

    public boolean guildExistsPrefix(String prefix) {
        Document document = collection.find(Filters.eq("prefix", prefix)).first();
        return document != null;
    }

    public void deleteGuild(String name) {
        collection.deleteOne(Filters.eq("name", name));
    }

    public void addMember(String name, String memberId) {
        collection.updateOne(Filters.eq("name", name), new Document("$addToSet",
                new Document("memberIds", memberId)));
    }

    public int getMaxID() {

        Document document = collection.find().sort(Sorts.descending("id")).first();
        if (document != null) {
            int maxId = document.getInteger("id");
            return maxId;
        }
        return 0;
    }
}
