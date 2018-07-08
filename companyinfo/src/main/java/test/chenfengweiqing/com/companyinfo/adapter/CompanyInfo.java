package test.chenfengweiqing.com.companyinfo.adapter;

import android.net.Uri;

public class CompanyInfo {
    String name;
    String legalPerson;
    String info;
    String phone;
    boolean isCalled;
    boolean isHope;
    Uri uri;

    public CompanyInfo(String name, String legalPerson, String info, String phone, boolean isCalled, boolean isHope,Uri uri) {
        this.name = name;
        this.legalPerson = legalPerson;
        this.info = info;
        this.phone = phone;
        this.isCalled = isCalled;
        this.isHope = isHope;
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCalled() {
        return isCalled;
    }

    public void setCalled(boolean called) {
        isCalled = called;
    }

    public boolean isHope() {
        return isHope;
    }

    public void setHope(boolean hope) {
        isHope = hope;
    }
}
