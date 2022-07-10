package by.it_academy.jd2.ClassifierService.core.dto.category;

public class CategoryCreate {

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
