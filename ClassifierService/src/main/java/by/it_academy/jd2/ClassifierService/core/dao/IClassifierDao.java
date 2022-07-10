package by.it_academy.jd2.ClassifierService.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassifierDao <T, ID> extends JpaRepository <T,ID> {

}
