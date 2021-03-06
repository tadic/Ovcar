
package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ivantadic
 */
@Entity
public class User {
    @Id
    private Integer id; 
    private String username;
    private String password;
    private String userType;
    
        public User() {
        }
        public User(String username, String password, String userType){
            this.username = username;
            this.password = password;
            this.userType = userType;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
        

}
