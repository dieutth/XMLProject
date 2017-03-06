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

import server.model.Book;
import server.model.Review;

public class BookDAOImpl implements BookDAO {
	
	public boolean insertReview(String title, String review){
		System.out.println(title);
		Cluster cluster = CouchbaseCluster.create("localhost");
	    Bucket bucket = cluster.openBucket("books");
	     
	    JsonObject rev = JsonObject.create()
	             .put("content", review)
	             .put("time", new Date().getTime());
	    if (bucket.get(title) == null){
	    	bucket.upsert(JsonDocument.create(title, JsonObject.empty().put("reviews", JsonArray.empty())));
	    }
	    JsonDocument jd = bucket.get(title);
	    JsonArray revList = jd.content().getArray("reviews"); 
	    revList.add(rev);

	    // Store the Document
	    bucket.upsert(JsonDocument.create(title, JsonObject.empty().put("reviews", revList)));
		return true;
	}
	
	public boolean deleteReview(){
		return true;
	}
	
	public boolean updateReview(){
		return true;
	}
	
	public List<Review> getReviews(String title){
		List<Review> ls = new ArrayList<Review>();
		Cluster cluster = CouchbaseCluster.create("localhost");
	    Bucket bucket = cluster.openBucket("books");
	    JsonArray ja = bucket.get(title).content().getArray("reviews");
	    for (Object js : ja){
	    	Review rv = new Review();
	    	rv.setContent((String)((JsonObject)js).get("content"));
	    	rv.setTime(new Date(2017, 3, 3));
	    }
	    
		return ls;
	}
}
