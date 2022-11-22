package com.nology.nsightapi.Classes;

public class PhoneNumber {

    private Integer countryCode;
    private String phoneDigits;

    public PhoneNumber(Integer countryCode, String phoneDigits) {
        this.countryCode = countryCode;
        this.phoneDigits = phoneDigits;
    }

    public PhoneNumber() {
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneDigits() {
        return phoneDigits;
    }

    public void setPhoneDigits(String phoneDigits) {
        this.phoneDigits = phoneDigits;
    }

    public String getPhoneNumber() {
        return "+" + countryCode + " " + phoneDigits;
    }
}
