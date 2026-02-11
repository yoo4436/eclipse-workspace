package tw.brad.spring04.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.brad.spring04.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Long> {
    /*
        動詞 + By + 屬性名稱
        findByEmail(String email) => Optional<member>
        findByAge(Integer age) => List<Member>

        countByAge(Integer age) => long
        deleteByAge(Integer age)

        And/Or
        findByNameAndAge(String name, Integer age)
        findByNameOrAge(String name, Integer age)

        Between/LessThan?GreatThanEqual
        findByAgeBetween(Integer min, Integer max)
        findByOrderDateBetween(Date/LocalDate start, Date/LocalDate end)

        IsNull/IsNotNull

        OrderBy + 屬性名稱 + Asc/Desc
        findByLastNameOrderByFirstNameAscAndTitleDesc(String lastname)
    */
}
