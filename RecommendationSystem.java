import org.apache.mahout.cf.taste.eval.*;
import org.apache.mahout.cf.taste.impl.eval.*;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.similarity.*;

import java.io.File;

public class RecommendationSystem {
    public static void main(String[] args) {
        try {
            DataModel model = new FileDataModel(new File("data.csv"));
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            System.out.println("Recommendations for user 1:");
            recommender.recommend(1, 3).forEach(r -> 
                System.out.println("Item: " + r.getItemID() + " Value: " + r.getValue())
            );
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
