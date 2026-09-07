public record RegisterRequest(
    String email,
    String password
) {

    @Override 
    public String toString(){
        return "RequitredRequest[email="+email+
        ", password=***]";
    }


}   
