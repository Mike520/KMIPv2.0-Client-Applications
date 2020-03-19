package com.ibm.model;

public class Key{

    private String TypeOfKey;
    private String Algorithm;
    private String KeySize;
    private String UniqueIdentifier;

    public Key() {
    }

    public String getTypeOfKey() {
        return TypeOfKey;
    }

    public void setTypeOfKey(String typeOfKey) {
        TypeOfKey = typeOfKey;
    }

    public String getAlgorithm() {
        return Algorithm;
    }

    public void setAlgorithm(String algorithm) {
        Algorithm = algorithm;
    }

    public String getKeySize() {
        return KeySize;
    }

    public void setKeySize(String keySize) {
        KeySize = keySize;
    }

    public String getUniqueIdentifier() {
        return UniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        UniqueIdentifier = uniqueIdentifier;
    }

   
    
}