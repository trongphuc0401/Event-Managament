package vn.edu.likelion.entities;


import java.util.ArrayList;
import java.util.List;

/**
 * Event - To create event object
 *
 * @param
 * @return
 * @throws
 *
 * update: 1 khách hàng chỉ tham gia dược 1 sự kiện.
 */
public class Event {
    private int id;
    private String name;
    private String openDate;
    private long numberGuest;
    private List<Guest> guests = null;

    public Event() {}
    public Event( int id, String name, String openDate, long numberGuest) {
        this.id = id;
        this.name = name;
        this.openDate = openDate;
        this.numberGuest = numberGuest;
        this.guests = new ArrayList<>();
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

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public long getNumberGuest() {
        return numberGuest;
    }

    public void setNumberGuest(long numberGuest) {
        this.numberGuest = numberGuest;
    }

    public List<Guest> getGuests() {
        if (guests == null) {
            guests = new ArrayList<>();
        }
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }
    public void addGuest(Guest guest) {
        if (guest != null && !guests.contains(guest)) {
            guests.add(guest);
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", openDate='" + openDate + '\'' +
                ", numberGuest=" + numberGuest +
                '}';
    }
}
