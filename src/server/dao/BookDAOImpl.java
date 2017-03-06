package server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;

import server.model.Review;

public class BookDAOImpl {
	static Cluster cluster; 
    static Bucket bucket; 
    public static final CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder()
            .keepAliveInterval(0).build();
    
    static{ 
	     if (cluster == null) {
	            cluster = CouchbaseCluster.create(env, "localhost");
	     }
	     if (bucket == null) {
	            bucket = cluster.openBucket("books"); 	     }
    }
	public boolean insertReview(String title, String review){
	    if (bucket.get(title) == null){
	    	bucket.upsert(JsonDocument.create(title, JsonObject.empty().put("reviews", JsonArray.empty())));
	    }
	    JsonDocument jd = bucket.get(title);
	    JsonArray revList = jd.content().getArray("reviews"); 
	    JsonObject rev = JsonObject.create()
	    		 .put("id", revList.size())
	             .put("content", review)
	             .put("time", new Date().getTime());
	    revList.add(rev);

	    // Store the Document
	    bucket.upsert(JsonDocument.create(title, JsonObject.empty().put("reviews", revList)));
		return true;
	}
	
	public boolean deleteReview(String title, int reviewID){
		JsonDocument jd = bucket.get(title);
	    JsonArray revList = jd.content().getArray("reviews"); 
	    JsonArray newList = JsonArray.empty();
	    for (int i = 0; i < revList.size(); i++){
	    	if (i != reviewID)
	    		newList.add(revList.get(i));
	    		
	    }
	    bucket.upsert(JsonDocument.create(title, JsonObject.empty().put("reviews", newList)));
		return true;
	}
	
	public boolean updateReview(String title, int reviewID, String review){
		JsonDocument jd = bucket.get(title);
	    JsonArray revList = jd.content().getArray("reviews"); 
	    JsonObject old = revList.getObject(reviewID);
	    old.put("content", review);
	    bucket.upsert(JsonDocument.create(title, JsonObject.empty().put("reviews", revList)));
		return true;
	}
	
	public List<Review> getReviews(String title){
		List<Review> ls = new ArrayList<Review>();
	    if (bucket.get(title)==null){
	    	return ls;
	    }
	    JsonArray ja = bucket.get(title).content().getArray("reviews");
	    for (Object js : ja){
	    	Review rv = new Review();
	    	rv.setContent((String)((JsonObject)js).get("content"));
	    	rv.setTime(new Date((long)((JsonObject)js).get("time")));
	    	ls.add(rv);
	    }
	    
		return ls;
	}
}
