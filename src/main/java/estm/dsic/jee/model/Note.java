
package estm.dsic.jee.model;

import java.time.LocalDate;

public class Note {
private int id;
private String subject;
private String body;
private String date;
private int user_id;
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getSubject() {
    return subject;
}
public void setSubject(String subject) {
    this.subject = subject;
}
public String getBody() {
    return body;
}
public void setBody(String body) {
    this.body = body;
}
public String getDate() {
    return date;
}
public void setDate(String date) {
    this.date = date;
}
public int getUser_id() {
    return user_id;
}
public void setUser_id(int user_id) {
    this.user_id = user_id;
}
     
}