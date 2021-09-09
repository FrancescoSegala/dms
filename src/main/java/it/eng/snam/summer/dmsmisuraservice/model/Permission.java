package it.eng.snam.summer.dmsmisuraservice.model;

public class Permission {


    public String name ;
    public String type ;
    public Integer maxLength ;
    public boolean single ;
    public boolean mandatory ;


    public Permission withName( String name ) {
        this.name = name ;
         return this;
    }

    public Permission withType( String type ) {
        this.type = type ;
         return this;
    }

    public Permission withMaxLength( Number maxLength ) {
        this.maxLength = maxLength.intValue();
         return this;
    }

    public Permission withSingle( boolean  single ) {
        this.single = single ;
         return this;
    }

    public Permission withMandatory( Boolean mandatory ) {
        this.mandatory = mandatory ;
         return this;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getMaxLength() {
        return maxLength;
    }
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
    public boolean isSingle() {
        return single;
    }
    public void setSingle(boolean single) {
        this.single = single;
    }
    public boolean isMandatory() {
        return mandatory;
    }
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }




}
