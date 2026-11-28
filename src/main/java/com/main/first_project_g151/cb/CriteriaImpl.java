package com.main.first_project_g151.cb;

import com.main.first_project_g151.model.Fighter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CriteriaImpl {

    private final EntityManager entityManager;
    public List<Fighter> findFightersByFullNameAndAge(String fullName, Integer age){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Fighter> query = criteriaBuilder.createQuery(Fighter.class);
        Root<Fighter> root = query.from(Fighter.class);

        List<Predicate> predicates = new ArrayList<>();

        if(fullName!=null && !fullName.isEmpty()){
            predicates.add(criteriaBuilder.like(root.get("fullName"), fullName));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        if(age!=null){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), age));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        return entityManager.createQuery(query).getResultList();

    }

    public List<Fighter> findFightersByAge(Integer age){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Fighter> query = criteriaBuilder.createQuery(Fighter.class);
        Root<Fighter> root = query.from(Fighter.class);

        Predicate predicate = criteriaBuilder.greaterThan(root.get("age"), age);

        query.select(root).where(predicate);

        return entityManager.createQuery(query).getResultList();


    }


}
