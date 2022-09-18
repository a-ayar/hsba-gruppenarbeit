package de.hsba.bi.demo.user;

        import static org.assertj.core.api.Assertions.assertThat;
        import static org.mockito.Mockito.*;

        import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoSettings;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

@MockitoSettings
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserService userServiceToTest;

    @Test
    void shouldFindAllTeachers(){
        //given
        List<User> teachers = new ArrayList<>();
        User teacher1 = new User("Teacher1", "teacherOne", "123", "LEHRER");
        User teacher2 = new User("Teacher2", "teacherTwo", "123", "LEHRER");
        User teacher3 = new User("Teacher3", "teacherThree", "123", "LEHRER");
        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);

        when(userRepositoryMock.findByRole("LEHRER")).thenReturn(teachers);

        //when
        List<User> foundTeachers = userServiceToTest.findAllTeacher();

        //then
        assertThat(foundTeachers).isEqualTo(teachers);
        verify(userRepositoryMock, never()).findAll();

    }

    @Test
    void shouldFindAllStudents(){
        //given
        List<User> studentsAndTeachers = new ArrayList<>();
        User t1 = new User("Teacher1", "teacherOne", "123", "LEHRER");
        User t2 = new User("Teacher2", "teacherTwo", "123", "LEHRER");
        User s1 = new User("Student1", "studentOne", "123", "SCHÜLER");
        studentsAndTeachers.add(t1);
        studentsAndTeachers.add(t2);
        studentsAndTeachers.add(s1);

        when(userRepositoryMock.findByRole("SCHÜLER")).thenReturn(Arrays.asList(s1));

        //when
        List<User> foundStudents = userServiceToTest.findAllStudents();

        //then
        assertThat(foundStudents).hasSize(1);
        assertThat(foundStudents).isEqualTo(Arrays.asList(s1));
        verify(userRepositoryMock, never()).findAll();

    }


}

