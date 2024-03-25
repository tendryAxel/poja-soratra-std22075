package hei.school.soratra.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Soratra {
    public Soratra(String id, String original_url, String transformed_url) {
        this.id = id;
        this.original_url = original_url;
        this.transformed_url = transformed_url;
    }

    @Id String id;
    String original_url;
    String transformed_url;
}
