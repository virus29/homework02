package com.i.homework02.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
@Repository
@Transactional
    public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;

        @Override
        public List<User> searchUser(Long officeId, String firstName, String secondName, String middleName, String position, String docCode, String citizenshipCode) {
            Specification<User> findUserByParam = new Specification<User>() {
                @Override
                public Predicate toPredicate(Root<User> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    List<Predicate> prs = new ArrayList<>();
                    if (officeId != null) prs.add(cb.equal(r.get("office").get("id"), officeId));
                    if (firstName != null) prs.add(cb.like(r.get("firstName"), "%" + firstName + "%"));
                    if (secondName != null) prs.add(cb.like(r.get("secondName"), "%" + secondName + "%"));
                    if (middleName != null) prs.add(cb.like(r.get("middleName"), "%" + middleName + "%"));
                    if (position != null) prs.add(cb.like(r.get("position"), "%" + position + "%"));
                    if (docCode != null) prs.add(cb.like(r.get("docCode"), "%" + docCode + "%"));
                    if (citizenshipCode != null) prs.add(cb.like(r.get("citizenshipCode"), "%" + citizenshipCode + "%"));
                    if (prs.isEmpty()) {return cb.createQuery(User.class).select(r).getRestriction();}
                    else {return cb.and(prs.toArray(new Predicate[prs.size()]));}
                }
            };
            return userRepository.findAll(findUserByParam);
        }

        @Override
        public User update(User user) {
            User updateUser = userRepository.findOne(user.getId());
            if (user.getFirstName() != null)
                updateUser.setFirstName(user.getFirstName());
            if (user.getSecondName() != null)
                updateUser.setSecondName(user.getSecondName());
            if (user.getMiddleName() != null)
                updateUser.setMiddleName(user.getMiddleName());
            if (user.getPosition() != null)
                updateUser.setPosition(user.getPosition());
            if (user.getPhone() != null)
                updateUser.setPhone(user.getPhone());
            if (user.getPhone() != null)
                updateUser.setPhone(user.getPhone());
            if (user.getIsIdentified() != null)
                updateUser.setIsIdentified(user.getIsIdentified());
            return userRepository.save(updateUser);
        }

        //Сохранение
        @Override
        public User save(User user) {
            return userRepository.save(user);
        }

        //Поиск по Id
        @Override
        public User findById(Long id) {
            return userRepository.findOne(id);
        }

        //Удаление
        @Override
        public void delete(User user) {
            userRepository.delete(user);
        }
    }
