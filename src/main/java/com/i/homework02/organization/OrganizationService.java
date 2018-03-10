package com.i.homework02.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


@Service
@Repository
@Transactional
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    public static Specification<Organization> findByParamNameInnIsactive(String organizationName, Long organizationInn, Boolean organizationIsactive) {
        return (r, cq, cb) -> cb.and(
            cb.equal(r.get("organizationName"), organizationName),
            cb.equal(r.get("organizationInn"), organizationInn),
            cb.equal(r.get("organizationIsactive"), organizationIsactive));
    }

    public static Specification<Organization> findByParamNameIsactive(String organizationName, Boolean organizationIsactive){
        return (r, cq, cb) -> cb.and(
                cb.equal(r.get("organizationName"), organizationName),
                cb.equal(r.get("organizationIsactive"), organizationIsactive));
    }

    public static Specification<Organization> findByParamNameInn(String organizationName, Long organizationInn) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.get("organizationName"), organizationName),
                cb.equal(r.get("organizationInn"), organizationInn));
    }

    public static Specification<Organization> findByParamName(String organizationName) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.get("organizationName"), organizationName));
    }

    @Transactional(readOnly = true)
    public List<Organization> search(String organizationName, Long organizationInn, Boolean organizationIsactive) {
        if (organizationInn == null){
            if(organizationIsactive==null){
                return organizationRepository.findAll(findByParamName(organizationName));
            }else {
                return organizationRepository.findAll(findByParamNameIsactive(organizationName, organizationIsactive));
            }
        } else {
            if(organizationIsactive==null){
                return organizationRepository.findAll(findByParamNameInn(organizationName, organizationInn));
            }else{
                return organizationRepository.findAll(findByParamNameInnIsactive(organizationName, organizationInn, organizationIsactive));
            }
        }
    }

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Transactional(readOnly=true)
    public Organization findById(Long id) {
        return organizationRepository.findOne(id);
    }

}
//    public static Specification<Organization> findByName(final String organizationName) {
//        return (r, cq, cb) -> cb.equal(r.get("organizationName"), organizationName);
//    }
//
//    public static Specification<Organization> findByInn(final Long organizationInn) {
//        return (r, cq, cb) -> cb.equal(r.get("organizationInn"), organizationInn);
//    }
//
//    public static Specification<Organization> findByIsactive(final boolean organizationIsactive) {
//        return (r, cq, cb) -> cb.equal(r.get("organizationIsactive"), organizationIsactive);
//    }
//    class SearchCriteria {
//        private String key;
//        private String operation;
//        private Object value;
//
//        public String getKey() {
//            return key;
//        }
//
//        public void setKey(String key) {
//            this.key = key;
//        }
//
//        public String getOperation() {
//            return operation;
//        }
//
//        public void setOperation(String operation) {
//            this.operation = operation;
//        }
//
//        public Object getValue() {
//            return value;
//        }
//
//        public void setValue(Object value) {
//            this.value = value;
//        }
//    }
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public List<Organization> searchUser(List<SearchCriteria> params) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Organization> query = builder.createQuery(Organization.class);
//        Root r = query.from(Organization.class);
//
//        Predicate predicate = builder.conjunction();
//
//        for (SearchCriteria param : params) {
//            if (param.getOperation().equalsIgnoreCase(">")) {
//                predicate = builder.and(predicate,
//                        builder.greaterThanOrEqualTo(r.get(param.getKey()),
//                                param.getValue().toString()));
//            } else if (param.getOperation().equalsIgnoreCase("<")) {
//                predicate = builder.and(predicate,
//                        builder.lessThanOrEqualTo(r.get(param.getKey()),
//                                param.getValue().toString()));
//            } else if (param.getOperation().equalsIgnoreCase(":")) {
//                if (r.get(param.getKey()).getJavaType() == String.class) {
//                    predicate = builder.and(predicate,
//                            builder.like(r.get(param.getKey()),
//                                    "%" + param.getValue() + "%"));
//                } else {
//                    predicate = builder.and(predicate,
//                            builder.equal(r.get(param.getKey()), param.getValue()));
//                }
//            }
//        }
//        query.where(predicate);
//
//        List<Organization> result = entityManager.createQuery(query).getResultList();
//        return result;
//    }
//    @Autowired
//    private EntityManager em;
//    public List<Organization> findByParams (Organization organization) {
//        CriteriaBuilder b = em.getCriteriaBuilder();
//        CriteriaQuery<Organization> cq = b.createQuery(Organization.class);
//        Root<Organization> organizations = cq.from(Organization.class);
//        if (organization.getOrganizationInn() != null) {
//            cq.select(organizations).where(b.and(
//                    b.equal(organizations.get("organizationName"), organization.getOrganizationName()),
//                    b.equal(organizations.get("organizationInn"), organization.getOrganizationInn()),
//                    b.equal(organizations.get("organizationIsactive"), organization.getOrganizationIsactive())
//            ));
//        } else {
//            cq.select(organizations).where(b.and(
//                    b.equal(organizations.get("organizationName"), organization.getOrganizationName()),
//                    b.equal(organizations.get("organizationIsactive"), organization.getOrganizationIsactive())
//            ));
//        }
//        TypedQuery<Organization> tq = em.createQuery(cq);
//        return tq.getResultList();
//    }

//    @Transactional(readOnly = true)
//    public List<Map<String, Object>> search(String organizationName, Long organizationInn, Boolean organizationIsactive) {
//        List<Organization> orgs = new ArrayList<>();
//        orgs=organizationRepository.findAll(where(findByName(organizationName)).and(findByInn(organizationInn)).and(findByIsactive(organizationIsactive)));

//        if(organizationInn == null){
//            if (organizationIsactive==null){
//                orgs=organizationRepository.findAll(where(findByName(organizationName)));
//            }
//            else {orgs=organizationRepository.findAll(where(findByName(organizationName)).and(findByIsactive(organizationIsactive)));
//            }
//        }
//        else if(organizationIsactive==null) {
//            orgs=organizationRepository.findAll(where(findByName(organizationName)).and(findByInn(organizationInn)));
//        }
//        orgs=organizationRepository.findAll(where(findByName(organizationName)).and(findByInn(organizationInn)).and(findByIsactive(organizationIsactive)));

//        List<Map<String, Object>> organizations = new ArrayList<>();

//        for (Organization organization: orgs) {
//            Map<String, Object> organizationMap = new LinkedHashMap<>();
//            organizationMap.put("id",orgs.get(0).getId());
//            organizationMap.put("organizationName",orgs.get(0).getOrganizationName());
//            organizationMap.put("organizationIsactive",orgs.get(0).getOrganizationIsactive());
//            organizations.add(organizationMap);
//        }
//        return organizations;
//    }
//        organizations.add(orgs.get(0).getId());
//        List<Object[]> objects = organizationRepository.findBySearchParams(organizationName, organizationInn, organizationIsactive);
//        List<Map<String, Object>> organizations = new ArrayList<>();
//        for (Object[] object: objects) {
//            Map<String, Object> organizationMap = new LinkedHashMap<>();
//            organizationMap.put("id", object[0]);
//            organizationMap.put("organizationName", object[1]);
//            organizationMap.put("organizationIsactive", object[2]);
//            organizations.add(organizationMap);
//        }
//        return organizations;
//    }


