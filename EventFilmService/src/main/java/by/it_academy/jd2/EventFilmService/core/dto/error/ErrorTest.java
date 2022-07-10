package by.it_academy.jd2.EventFilmService.core.dto.error;

import java.util.List;

public class ErrorTest {

        private List<String> errors;

        public ErrorTest(final List<String> errors) {
            this.errors = errors;
        }

        public List<String> getErrors() {
            return errors;
        }

        public void setErrors(final List<String> errors) {
            this.errors = errors;
        }


}
