package postservice.model;

import database.model.BaseEntity;
import lombok.Data;

@Data
public class Post extends BaseEntity {

    String content;
    Integer upvotes;
}
