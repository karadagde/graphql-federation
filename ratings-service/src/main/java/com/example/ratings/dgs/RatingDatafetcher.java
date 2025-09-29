package com.example.ratings.dgs;


import com.example.contracts.codegen.types.Rating;
import com.example.contracts.codegen.types.Title;
import com.example.contracts.codegen.types.User;
import com.example.ratings.persistance.RatingEntity;
import com.example.ratings.persistance.RatingRepository;
import com.netflix.graphql.dgs.*;


import java.util.List;
import java.util.Map;

@DgsComponent
public class RatingDatafetcher {

    private final RatingRepository repo;

    public RatingDatafetcher(RatingRepository repo) {
        this.repo = repo;
    }

    @DgsEntityFetcher(name = "User")
    public User user(Map<String, Object> values) {
        String id = (String) values.get("id");
        //
        return User.newBuilder().id(id).build();
    }

    @DgsEntityFetcher(name = "Title")
    public Title title(Map<String, Object> values) {
        String id = (String) values.get("id");
        return Title.newBuilder().id(id).build();
    }

    // Field resolvers
    @DgsData(parentType = "User", field = "reviews")
    public List<Rating> reviews(DgsDataFetchingEnvironment dfe) {
        User user = dfe.getSource();
        return repo.findByUserId(Long.valueOf(user.getId()))
                .stream()
                .map(this::toContractRating)
                .toList();
    }

    @DgsData(parentType = "Title", field = "rating")
    public List<Rating> rating(DgsDataFetchingEnvironment dfe) {
        Title title = dfe.getSource();
        return repo.findByTitleId(Long.valueOf(title.getId()))
                .stream()
                .map(this::toContractRating)
                .toList();
    }

    private Rating toContractRating(RatingEntity entity) {
        return Rating.newBuilder()
                .id(entity.getId().toString())
                .rate(entity.getRate())
                .user(User.newBuilder().id(entity.getUserId().toString()).build())
                .title(Title.newBuilder().id(entity.getTitleId().toString()).build())
                .build();
    }
}
