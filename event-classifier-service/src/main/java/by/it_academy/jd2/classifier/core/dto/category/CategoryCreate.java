package by.it_academy.jd2.classifier.core.dto.category;

import javax.validation.constraints.NotBlank;

public class CategoryCreate {
    @NotBlank
    private String title;

    public CategoryCreate() {
    }

    public CategoryCreate(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
