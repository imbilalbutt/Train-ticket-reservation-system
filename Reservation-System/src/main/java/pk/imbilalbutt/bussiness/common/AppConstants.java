package pk.imbilalbutt.bussiness.common;

public class AppConstants {

    public enum Roles {

        Admin("ADMIN"),
        User("USER"),
        Management("MANAGEMENT"),
        Visitor("VISITOR"),
        Manager("MANAGER");

        private String role;

        Roles(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }

    }

}
