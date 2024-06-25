package vn.edu.likelion.entities;

import java.time.LocalDateTime;

/**
 * Guest - to create guest object
 *
 * @param
 * @return
 * @throws
 * update: 1 khách hàng chỉ tham gia dược 1 sự kiện.
 */
public class Guest {
    private int id;
    private String name;
    private  int age;
    private Event nameEvent;
    private LocalDateTime registerDate;

    public Guest() {}
    public Guest(int id, String name, int age, Event nameEvent, LocalDateTime registerDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nameEvent = nameEvent;
        this.registerDate = registerDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Event getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(Event nameEvent) {
        this.nameEvent = nameEvent;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime resgisterDate) {
        this.registerDate = resgisterDate;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", nameEvent=" + nameEvent +
                ", registerDate=" + registerDate +
                '}';
    }
}
