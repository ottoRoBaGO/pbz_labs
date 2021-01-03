package tech.ottorobago.dokb_lab_2.domain;

public interface Company {
    int getId();

    String getName();

    /**this method is not supposed to be used with purpose of change the id field
     * in a persistence storage, it's supposed to change the id field only in context of java*/
    void setId(int id);

    void setName(String name);
}
