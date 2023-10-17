package model;

public class StudentService implements BaseCRUD<Student>, IDisplay{
    @Override
    public void create(Student student) {
        // trỏ xuosng db lấy data
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public String display() {
        return null;
    }
}