package org.tallerjava.moduloGestionCliente.dominio.clases;

public class Tag {
    private String id;

    public Tag(String id) {
        this.id = id;
    }
    public Tag() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o.getClass() == String.class) {
            if(o.equals(id)) {
                return true;
            }
        }
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id.equals(tag.id);
    }
}
