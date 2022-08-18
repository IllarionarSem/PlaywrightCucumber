package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book extends Model {

    String author;
    String title;
    Integer year;
    String subAuthor;
}
