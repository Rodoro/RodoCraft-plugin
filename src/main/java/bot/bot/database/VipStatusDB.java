package bot.bot.database;

import bot.bot.Secret;
import bot.bot.model.VipStatus;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class VipStatusDB {
    private MongoClient mongoClient;
    private MongoCollection<Document> collection;

    private static VipStatusDB instance = null;

    public static VipStatusDB getInstance() {
        if (instance == null) {
            instance = new VipStatusDB();
        }

        return instance;
    }

    public void VipStatusDAO() {
        MongoClientURI uri = new MongoClientURI(Secret.tokenDB);
        mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase(uri.getDatabase());
        collection = database.getCollection("VipStatus");
    }

    public void addVipStatus(VipStatus vip) {
        Document document = new Document()
                .append("memberId", vip.getMemberId())
                .append("money", vip.getMoney())
                .append("exp", vip.getExp())
                .append("lvl", vip.getLvl());
        collection.insertOne(document);
    }

    public VipStatus getVipStatusByName(String memberId) {
        Document document = collection.find(Filters.eq("memberId", memberId)).first();
        if (document == null) {
            return null;
        }
        return new VipStatus(
                document.getString("memberId"),
                document.getInteger("money"),
                document.getInteger("exp"),
                document.getInteger("lvl"));
    }

    public void updateVipStatusParameter(String memberId, String parameter, Object value) {
        Document update = new Document(parameter, value);
        collection.updateOne(Filters.eq("memberId", memberId), new Document("$set", update));
    }

    public void deleteVipStatus(String memberId) {
        collection.deleteOne(Filters.eq("memberId", memberId));
    }
}
