package kz.aitu.restpro.restpro.entitiess;
import java.util.Objects;

class LibraryMember extends Person {
    private String memberID;

    public LibraryMember(String name, String memberID, int age) {
        super(name, age);
        this.memberID = memberID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    @Override
    public String toString() {
        return super.toString() + ", Member ID: " + memberID;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        LibraryMember that = (LibraryMember) obj;
        return Objects.equals(memberID, that.memberID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), memberID);
    }
}
