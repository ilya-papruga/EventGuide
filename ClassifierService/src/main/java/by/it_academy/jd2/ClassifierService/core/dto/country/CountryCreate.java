package by.it_academy.jd2.ClassifierService.core.dto.country;

public class CountryCreate {

    private String title;
    private String description;

    public CountryCreate() {
    }

    public CountryCreate(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
