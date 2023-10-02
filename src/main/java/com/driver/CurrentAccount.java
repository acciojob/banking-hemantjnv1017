package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId=tradeLicenseId;

        if(balance<5000){
            throw new Exception("Insufficient Balance");
        }


    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(isvalid(tradeLicenseId)==false){

            if(cangenerat(tradeLicenseId)==false){
                throw new Exception("Valid License can not be generated");
            }

            this.tradeLicenseId=regenerate(tradeLicenseId);

        }

    }
    public String regenerate(String s){
        int[] arr=new int[26];
        char maxchar='-';
        int max=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            arr[c-'A']++;
            if(arr[c-'A']>max){
                max=arr[c-'A'];
                maxchar=c;
            }
        }
        char[] rarr=new char[s.length()];
        int index=0;
        while(arr[maxchar-'A']>0){
            rarr[index]=maxchar;
            index+=2;
            arr[maxchar-'A']--;
        }

        for(int i=0;i<26;i++){

            while(arr[i]-->0){
                if(index>=s.length()){
                    index=1;
                }
                rarr[index]=(char)('A'+i);
                index+=2;
            }
        }
        return new String(rarr);
    }
    public boolean cangenerat(String s){
        int[] arr=new int[26];
        int max=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            arr[c-'A']++;
            max=Math.max(max,arr[c-'A']);
        }
        if(s.length()%2==0 && max>s.length()/2){
            return false;
        }
        if(s.length()%2==1 && max>(s.length()+1)/2){
            return false;
        }
        return true;
    }
    public boolean isvalid(String  s){
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==s.charAt(i-1)){
                return false;
            }
        }
        return true;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }
}